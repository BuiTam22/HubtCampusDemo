package com.codecampushubt.NCKH2024TQQD.service.UserServices;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.cloudinary.Cloudinary;
import com.codecampushubt.NCKH2024TQQD._enum.Admin.User.AccountStatus;
import com.codecampushubt.NCKH2024TQQD.dao.RoleRepository;
import com.codecampushubt.NCKH2024TQQD.dao.UserRoleRepository;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserCreateDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserUpdateDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Role;
import com.codecampushubt.NCKH2024TQQD.entity.UserRole;
import com.codecampushubt.NCKH2024TQQD.entity.UserRoleId;
import com.codecampushubt.NCKH2024TQQD.service.Cloudinary.CloudinaryService;
import com.codecampushubt.NCKH2024TQQD.util.BCryptPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Service;

import com.codecampushubt.NCKH2024TQQD.dao.UserRepository;
import com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginBasicDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserBasicInfoDTO;
import com.codecampushubt.NCKH2024TQQD.entity.User;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository,
                           CloudinaryService cloudinaryService
    ) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public ArrayList<UserBasicInfoDTO> getUserBasicInfo(Long userID) {
        return userRepository.getUserBasicInfo(userID);
    }

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public LoginBasicDTO getLoginBasicDTO(String userName) {
        return (LoginBasicDTO) userRepository.getLoginBasicDTO(userName);
    }


    @Override
    public Long findUserIDByUserName(String userName) {
        return userRepository.getUserIDByUserName(userName);
    }

    @Override
    public User getUserEntityByID(Long userID) {
        return userRepository.getUserEntityByID(userID);
    }

    //show user
    @Override
    public List<UserShowDTO> getAllUsers() {
        return userRepository.findAllActiveUsers().stream()  // Chỉ lấy người dùng có trạng thái ACTIVE
                .map(user -> {
                    List<String> rolename = user.getUserRoles().stream()
                            .map(userRole -> userRole.getRole().getRoleName())
                            .collect(Collectors.toList());
                    return new UserShowDTO(
                            user.getUserId(),
                            user.getuserName(),  // Lưu ý là phương thức getter phải đúng chính tả (getUserName() thay vì getuserName())
                            user.getEmail(),
                            rolename
                    );
                })
                .collect(Collectors.toList());
    }


    //    end show user
//    create user
    @Override
    public User addUser(UserCreateDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UsernameNotFoundException("Email đã tồn tại");
        }
        if (userRepository.findByUserName(dto.getUserName()).isPresent()) {
            throw new RuntimeException("Tên người dùng đã tồn tại");
        }


        // Khởi tạo user
        User user = new User();
        user.setuserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setStatus("ACTIVE");
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setProvider("LOCAL");
        user.setEmailVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        if (dto.getDateOfBirth() != null) {
            user.setDateOfBirth(dto.getDateOfBirth());
        }
        // Lưu user trước để có ID
        User savedUser = userRepository.save(user);

        // Tìm Role
        Role role = roleRepository.findByRoleName(dto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role không tồn tại: " + dto.getRoleName()));

        // Tạo UserRoleId và UserRole
        UserRoleId userRoleId = new UserRoleId(savedUser.getUserId(), role.getRoleID());

        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);
        userRole.setUser(savedUser);
        userRole.setRole(role);

        userRoleRepository.save(userRole);

        return savedUser;
    }

//end create user
    public String getFullName(String userName) {
        return userRepository.getFullName(userName);
    }

    //    update user ------------------------------------------------------------------------------------
//    show
  @Override
  public UserUpdateDTO getUserUpdateDTOById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong Tim Thay User"));
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());
        return new UserUpdateDTO(
                user.getUserId(),
                user.getuserName(),             // userName
                user.getEmail(),                // email
                "",                             // password (để trống khi hiển thị form)
                user.getFullName(),             // fullName
                user.getDateOfBirth(),          // dateOfBirth
                user.getPhoneNumber(),          // phoneNumber
                user.getAddress(),              // address
                user.getImage(),                // image (lưu URL ảnh)
                roles


        );
  }

//  end show
@Override
public User updateUser(Long userId, UserUpdateDTO dto) {
//    System.out.println(dto);
//    System.out.println(userId);

    // Tìm người dùng theo userId
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

    // Cập nhật các trường thông tin của người dùng từ DTO
    user.setuserName(dto.getUserName());
    user.setEmail(dto.getEmail());
    user.setFullName(dto.getFullName());
    user.setDateOfBirth(dto.getDateOfBirth());
    user.setPhoneNumber(dto.getPhoneNumber());
    user.setAddress(dto.getAddress());

    // Cập nhật ảnh đại diện nếu có
    if (dto.getImage() != null && !dto.getImage().isEmpty()) {
        user.setImage(dto.getImage());
    }

    // Cập nhật vai trò (roles)
    if (dto.getRoleName() != null && !dto.getRoleName().isEmpty()) {
        // Xóa hết các quyền cũ của người dùng
        userRoleRepository.deleteByUserId(userId);

        // Lấy danh sách Role từ tên (roleName) gửi lên từ frontend
        List<Role> roles = roleRepository.findByRoleNames(dto.getRoleName());
//        System.out.println(roles);

        // Tạo UserRole mới với các Role được chọn
        for (Role role : roles) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setAssignedAt(LocalDateTime.now());

            UserRoleId userRoleId = new UserRoleId();
            userRoleId.setUserId(user.getUserId().longValue()); // nếu userID là Long
            userRoleId.setRoleId(role.getRoleID()); // đảm bảo dùng đúng getter

            userRole.setId(userRoleId); // 👉 dòng quan trọng!

            userRoleRepository.save(userRole);
        }

    }

    // Lưu thông tin người dùng sau khi cập nhật
    return userRepository.save(user);
}

//    end update user ---------------------------------------------------------------------------------------

//    xóa mềm ---------------------------------------------------------------------------------------
    @Override
    public User softDeleteUser(Long userid){
        System.out.println(userid);
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("Khong Tim Thay User"));
        user.setAccountStatus(AccountStatus.DELETED);
        User UpdatedUser = userRepository.save(user);

        return UpdatedUser;

    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    // end    xóa mềm ---------------------------------------------------------------------------------------


    @Override
    public Page<UserShowDTO> getAllUsers(int page, int size) {
        // Tạo Pageable cho phân trang, sắp xếp theo ID giảm dần
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        // Lấy dữ liệu từ repository, chỉ lấy user không bị xóa
        Page<User> userPage = userRepository.findAllByAccountStatusNot("DELETED", pageable);

        // Chuyển đổi từ Page<User> thành Page<UserListDTO>
        return userPage.map(user -> {
            UserShowDTO dto = new UserShowDTO();
            dto.setUserID(user.getUserId());
            dto.setUserName(user.getuserName());
            dto.setEmail(user.getEmail());
            return dto;
        });
    }

}
