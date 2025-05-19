const apiShow = `${apiBaseUrl}/admin/api/user/show`;
fetch(apiShow)

    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById("userTableBody");
        tableBody.innerHTML = ""; // Xóa dữ liệu cũ

        data.forEach(user => {
            const row = `
                    <tr>
                        <td>${user.userID}</td>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <td>${user.userRole}</td>
                        <td>
                        <button class="btn btn-success" onclick="showDetailFormOnly(${user.userID}, '${user.userName}', '${user.email}', '${user.userRole}')">Chi tiết</button>
                        <button class="btn btn-warning mx-2" onclick="showEditFormOnly(${user.userID})">Sửa</button>
                        <button class="btn btn-danger " onclick="softDeleteUser(${user.userID})">Xóa</button>

                        </td>
                    </tr>
                `;
            tableBody.innerHTML += row;
        });
    })
    .catch(error => {
        console.error("Lỗi khi lấy danh sách user:", error);
    });

// ẩn hiện form
function hideAllForms() {
    document.getElementById("userAddForm").style.display = "none";
    document.getElementById("userDetailForm").style.display = "none";
    document.getElementById("userEditForm").style.display = "none";
    document.getElementById("userList").style.display = "none";
}

function showUserList() {
    hideAllForms();
    document.getElementById("userList").style.display = "block";
}

function showAddFormOnly() {
    hideAllForms();
    document.getElementById("userAddForm").style.display = "block";
}

function showDetailFormOnly() {
    hideAllForms();
    document.getElementById("userDetailForm").style.display = "block";
}

function showEditFormOnly() {
    hideAllForms();
    document.getElementById("userEditForm").style.display = "block";
}


// ẩn hiện form
// thêm người DÙng
document.getElementById("addUserForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    const data = {
        userName: formData.get("userName"),
        email: formData.get("email"),
        password: formData.get("password"),
        fullName: formData.get("fullName"),
        dateOfBirth: formData.get("dateOfBirth"),
        phoneNumber: formData.get("phoneNumber"),
        address: formData.get("address"),
        roleName: formData.get("roleName") // nếu form chỉ chọn 1 quyền
    };

    fetch("/admin/api/user/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (res.ok) {
                alert("Thêm thành công!");
                location.reload()
                document.getElementById("userAddForm").style.display = "none";
            } else {
                return res.text().then(errorMessage => {
                    alert("Lỗi từ server: " + errorMessage);
                    console.error("Chi tiết lỗi:", errorMessage);
                });
            }
        })
        .catch(error => {
            alert("Lỗi khi gọi API: " + error.message);
            console.error("Lỗi mạng hoặc lỗi khác:", error);
        });
});

//end thêm người dùng

//upload hình ảnh
async function uploadImage(file) {
    const formData = new FormData();
    formData.append("file", file);

    const res = await fetch("/admin/api/upload", {
        method: "POST",
        body: formData
    });

    const data = await res.json();
    document.getElementById("avatarUrl").value = data.url;
    document.getElementById("preview").src = data.url;
    document.getElementById("preview").style.display = "block";
}

//end upluad hình ảnh

//update người dùng

// show edit form
document.getElementById("image").addEventListener("change",function (){
    const file = this.file[0];
    if (file){
        const reader = new FileReader()
        reader.onload = function (e){
            const preview = document.getElementById("preview");
            preview.src = e.target.result;
            preview.style.display = "block";
        };
        reader.readAsDataURL(file);
    }
});
// Hiển thị form sửa người dùng và đổ dữ liệu vào form
async function showEditFormOnly(userId) {
    try {
        const response = await fetch(`/admin/api/user/showUpdate/${userId}`);

        // Kiểm tra xem API có trả về thành công không


        const user = await response.json();
        // console.log(user)
        // console.log("User Data:", user); // Kiểm tra dữ liệu người dùng

        // Ẩn form danh sách và hiển thị form sửa
        hideAllForms()// Ẩn các form khác
        document.getElementById("userEditForm").style.display = "block"; // Hiển thị form sửa

        // Điền dữ liệu vào form
        const form = document.forms["editUserForm"];
        form.userId.value = user.userId;
        form.userName.value = user.userName;
        form.email.value = user.email;
        form.fullName.value = user.fullName || "";
        form.dateOfBirth.value = user.dateOfBirth || "";
        form.phoneNumber.value = user.phoneNumber || "";
        form.address.value = user.address || "";

        // console.log(form)

        const allRoles = ["ADMIN", "STUDENT", "TEACHER"]; // Các quyền có sẵn
        const checkboxContainer = document.getElementById("roleCheckboxes");
        checkboxContainer.innerHTML = ""; // Xóa các checkbox cũ

// Kiểm tra và lấy quyền từ API trả về
        const userRoles = Array.isArray(user.roleName) ? user.roleName : [];

// Kiểm tra dữ liệu
//         console.log("Quyền của user (mảng):", userRoles);

// Tạo checkbox cho mỗi quyền
        allRoles.forEach(role => {
            const label = document.createElement("label");
            label.style.display = "block";

            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.name = "roleNames"; // để backend nhận nhiều quyền
            checkbox.value = role;

            // Tích sẵn nếu user đang có quyền này
            if (userRoles.includes(role)) {
                checkbox.checked = true;
            }

            label.appendChild(checkbox);
            label.append(" " + role);
            checkboxContainer.appendChild(label);
        });






        // Hiển thị ảnh đại diện nếu có
        const preview = document.getElementById("preview");
        if (user.image) {
            preview.src = user.image; // Đặt src của ảnh bằng URL từ Cloudinary
            preview.style.display = "block"; // Hiển thị ảnh
        }

    } catch (error) {


    }
}

// end show edit fom
// update
async function submitEditForm() {
    const btn = document.getElementById("submitBtn");
    const form = document.forms["editUserForm"];
    const userId = form.userId.value;
    // console.log(userId)
    // Kiểm tra userId hợp lệ
    if (!userId) {
        alert("User ID không hợp lệ!");
        return;
    }

    // Lấy ảnh nếu có
    const file = form.image.files[0];
    let imageUrl = document.getElementById("preview").src;

    // Nếu có file ảnh mới, upload lên server
    if (file) {
        try {
            const formData = new FormData();
            formData.append("file", file);

            const res = await fetch("/admin/api/upload", {
                method: "POST",
                body: formData
            });

            if (!res.ok) {
                const error = await res.text();
                alert("Lỗi khi tải ảnh lên: " + error);
                return;
            }

            const data = await res.json();
            imageUrl = data.url;
        } catch (error) {
            alert("Lỗi khi upload ảnh: " + error.message);
            return;
        }
    }

    // Lấy danh sách role đã chọn
    const roleNames = Array.from(document.querySelectorAll("#roleCheckboxes input[type='checkbox']:checked"))
        .map(cb => cb.value);

    // Tạo DTO gửi lên backend
    const userUpdateDTO = {
        userName: form.userName.value,
        email: form.email.value,
        fullName: form.fullName.value,
        dateOfBirth: form.dateOfBirth.value,
        phoneNumber: form.phoneNumber.value,
        address: form.address.value,
        image: imageUrl,
        roleName: roleNames
    };
    // console.log(userUpdateDTO)

    try {
        const res = await fetch(`/admin/api/user/update/${userId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userUpdateDTO)
        });

        if (res.ok) {
            alert("Cập nhật thành công!");
            location.reload()
            showUserList(); // Hiển thị lại danh sách user
        } else {
            const error = await res.text();
            alert("Lỗi khi cập nhật: " + error);
        }
    } catch (err) {
        alert("Lỗi kết nối server: " + err.message);
    }
}





// end edit form



//end update nguời dùng


//xóa mềm



function softDeleteUser(userId) {
    if (confirm("Bạn có chắc muốn xóa người dùng này không?")) {
        const a = "/admin/api/user/delete/${userId}"
        console.log(userId , a)
        fetch(`/admin/api/user/delete/${userId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert("Đã xóa người dùng (mềm) thành công!");
                    location.reload()
                    // Có thể load lại danh sách người dùng nếu muốn:
                    // loadUserList();
                } else {
                    alert("Xóa thất bại!");
                }
            })
            .catch(error => {
                console.error("Lỗi khi gọi API:", error);
                alert("Đã xảy ra lỗi!");
            });
    }
}
//Xóa Mềm

//Phân Trang
let currentPage =  0
let totalPage = 0
function loadUsers(page){
    fetch(`/admin/api/user?page=${page}&size=4`)
        .then(res => res.json())
        .then(data =>{
            totalPage = data.totalPage;
            displayUser(data.conten)
        })
}
//phân Trang