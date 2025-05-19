package com.codecampushubt.NCKH2024TQQD.security;

import java.io.IOException;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codecampushubt.NCKH2024TQQD.service.JWTServices.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter { // Kế thừa từ OncePerRequestFilter để đảm bảo filter chỉ được thực thi một lần cho mỗi request

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String token = null;
        String requestPath = request.getRequestURI(); // Lấy đường dẫn của request hiện tại

        // Kiểm tra xem đường dẫn có phải là public path không (không cần xác thực)
        if (isPublicPath(requestPath)) {
            chain.doFilter(request, response); // Cho phép request đi tiếp trong filter chain
            return;
        }

        // Lấy token từ cookie
        if (request.getCookies() != null) { // Kiểm tra xem request có chứa cookie không
            for (Cookie cookie : request.getCookies()) { // Duyệt qua từng cookie
                if ("token".equals(cookie.getName())) { // Tìm cookie có tên là "token"
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && jwtService.validateToken(token)) { // Kiểm tra token không null và token hợp lệ
            String username = jwtService.extractUsername(token); // Trích xuất username từ token
            List<String> permissions = jwtService.extractPermissions(token); // Trích xuất danh sách permissions từ token
            List<String> roles = jwtService.extractRoles(token);

            if (username != null) {
                // Kiểm tra xem người dùng có quyền truy cập vào đường dẫn hiện tại không
                if (hasPermission(permissions, requestPath, roles)) {
                    // Tạo đối tượng Authentication cho người dùng
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    username, null, Collections.emptyList()); // username là principal, null là credentials, emptyList là authorities

                    // Đặt authentication vào SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    chain.doFilter(request, response); // Cho phép request đi tiếp trong filter chain
                } else {
                    // Người dùng không có quyền truy cập
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Đặt status code 403 Forbidden
                    response.getWriter().write("Access denied: You don't have permission to access this resource. Please contact ADMIN => ");
                    response.getWriter().write("Truy cap bi tu choi: Ban khong co quyen truy cap vao tai nguyen nay. Vui long lien he voi quan tri vien.");// Gửi thông báo lỗi
                }
            } else {
                // Username null, token không hợp lệ
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Đặt status code 401 Unauthorized
                response.getWriter().write("Invalid token"); // Gửi thông báo lỗi
            }
        } else {
            // Token null hoặc không hợp lệ
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Đặt status code 401 Unauthorized
            response.sendRedirect("/login/show");
            return;
        }
    }

    /**
     * Kiểm tra xem đường dẫn có phải là public path không
     * @param path Đường dẫn cần kiểm tra
     * @return true nếu là public path, false nếu không phải
     */
    private boolean isPublicPath(String path) {
        return path.equals("/api/user/login") ||// Kiểm tra endpoint đăng nhập API
                path.equals("/api/user/register") ||
                path.equals("/api/user/forgot") ||
                path.equals("/api/user/verifyOtp") ||
                path.equals("/api/user/reset-password") ||
                path.equals("/api/user/logout") || // Kiểm tra endpoint đăng xuất API
                path.equals("/login/show") ||     // Kiểm tra endpoint trang đăng nhập
                path.startsWith("/AdminStatic/") || // Kiểm tra tài nguyên static cho admin
                path.startsWith("/ClientStatic/") ||  // Kiểm tra tài nguyên static cho client
                path.equals("/blog") ||
                path.equals("/") ||
                path.equals("/about");

    }

    /**
     * Kiểm tra xem người dùng có quyền truy cập đường dẫn không
     * @param permissions Danh sách permissions của người dùng
     * @param requestPath Đường dẫn yêu cầu
     * @return true nếu có quyền, false nếu không có quyền
     */
    private boolean hasPermission(List<String> permissions, String requestPath, List<String> roles) {
        // Kiểm tra ADMIN role trước
        if (roles != null && !roles.isEmpty()) {
            for (String role : roles) {
                if (role.equalsIgnoreCase("ADMIN")) {
                    System.out.println("Admin role found - granting access to: " + requestPath);
                    return true;
                }
            }
        }
//        System.out.println(roles);
        if (permissions == null || permissions.isEmpty()) {
            return false; // Không có permissions nào, không có quyền truy cập
        }
//        System.out.println("ROLE: " + roles);

        // Kiểm tra xem requestPath có trong danh sách permissions không
        for (String permittedPath : permissions) { // Duyệt qua từng permission
            // Xử lý các trường hợp có path params như "/api/user/basic-info/{id}"
            if (permittedPath.contains("{") && permittedPath.contains("}")) {
                // Chuyển đổi permittedPath thành regex pattern
                String pattern = permittedPath.replaceAll("\\{[^}]+\\}", "[^/]+"); // Thay thế {param} bằng regex [^/]+ (một hoặc nhiều ký tự không phải dấu /)
                pattern = "^" + pattern + "$"; // Thêm ^ và $ để đảm bảo khớp toàn bộ chuỗi
                if (requestPath.matches(pattern)) { // Kiểm tra xem requestPath có khớp với pattern không
                    return true; // Có quyền truy cập
                }
            } else if (permittedPath.equals(requestPath)) { // Kiểm tra khớp chính xác nếu không có path param
                return true; // Có quyền truy cập
            }
        }

        return false; // Không tìm thấy permission phù hợp, không có quyền truy cập
    }
}