# codeHUBT

## Giới thiệu
codeHUBT là một hệ thống quản lý khóa học trực tuyến, hỗ trợ quản lý khóa học, bài giảng, người dùng và phân quyền chi tiết. Dự án được xây dựng với Spring Boot, sử dụng cơ sở dữ liệu MySQL và bảo mật qua JWT (JSON Web Token).

## Tính năng

| Tính năng                 | Mô tả                                                         |
|---------------------------|---------------------------------------------------------------|
| **Quản lý khóa học**       | Tạo mới, cập nhật, xóa và xem chi tiết các khóa học.          |
| **Quản lý bài giảng**      | Tạo, chỉnh sửa và phân phối bài giảng cho học viên.           |
| **Quản lý người dùng**     | Hệ thống phân quyền người dùng, cập nhật tài khoản, mật khẩu, và vai trò. |
| **Phân quyền**             | Tính năng phân quyền chi tiết dựa trên JWT và Spring Security. |

## Cài đặt

### Yêu cầu
- Java 17 trở lên
- MySQL 8.0 trở lên
- Maven

### Cài đặt Dự án

1. Clone dự án về máy của bạn:

   ```bash
   git clone https://github.com/yourusername/codeHUBT.git
