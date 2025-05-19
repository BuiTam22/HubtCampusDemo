async function showCourse(){
    try {
        const apiCourseShow = 'admin/api/couese/show'
        // const response = await

    }catch (err){
        console.error("Lỗi Khi Tải Khóa Học",err);
        document.getElementById('courseList').innerHTML='<p>Không Thể Tải Danh Sách Khóa Học</p>'
    }
}



document.addEventListener("DOMContentLoaded", function () {
    const courseListDiv = document.getElementById("courseList");

    fetch('/admin/api/course/show', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                courseListDiv.innerHTML = "<p>Không có khóa học nào.</p>";
                return;
            }

            const isAdmin = data.some(course => course.roleName === "ADMIN");

            let html = `<table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>`;
            if (isAdmin) {
                html += `<th>ID</th>`;
            }
            html += `
                    <th>Tiêu đề</th>
                    <th>Giá</th>
                    <th>Giảm giá</th>
                    `;
            if (isAdmin){
                html += `<th>Người Tạo</th>`;
        }html +=`
                </tr>
            </thead>
            <tbody>`;

            data.forEach(course => {
                html += `<tr>`;
                if (isAdmin) {
                    html += `<td>${course.courseID}</td>`;
                }
                html += `
                    <td>${course.title}</td>
                    <td>${course.price}</td>
                    <td>${course.discountPrice ?? 0}</td>`;
                if (isAdmin) {
                    html += `<td>${course.instructorUserName}</td>`;
                }
                html += `</tr>`;

            });

            html += `</tbody></table>`;
            courseListDiv.innerHTML = html;
        })
        .catch(error => {
            console.error("Lỗi khi gọi API:", error);
            courseListDiv.innerHTML = "<p>Đã xảy ra lỗi khi tải khóa học.</p>";
        });
});