document.addEventListener("DOMContentLoaded", function(){
    const lessonListDiv = document.getElementById("CourseLessonList")
    // console.log(lessonListDiv)
    fetch('/admin/api/lesson/show',{
        method :'GET',
        headers:{
            'Content-Type' : 'application/json'
        }

    })
        .then(res => res.json())
        .then(data => {
            // console.log(data)
            if (data.length === 0 ){
                lessonListDiv.innerHTML="<p>Không có câu hỏi nào !</p>"

            }
            const isAdmin = data.some(lesson => lesson.rolename === "ADMIN")
            // console.log(isAdmin)
            let html = `
            <table>
                <thead>
                    <tr>
        `;

            if (isAdmin) {
                html += `<th>STT</th>`;
            }

            html += `
                        <th>Tên</th>
                        <th>Mô Tả</th>
                        <th>Thuộc Tính</th>
                        <th>Thời Gian</th>
        `;

            if (isAdmin) {
                html += `<th>Người Tạo</th>`;
            }

            html += `
                    </tr>
                </thead>
                <tbody>
        `;

            data.forEach(lesson => {
                html += `<tr>`;

                if (isAdmin) {
                    html += `<td>${lesson.lessonId}</td>`;
                }

                html += `
                <td>${lesson.title}</td>
                <td>${lesson.description}</td>
                <td>${lesson.type}</td>
                <td>${lesson.duration}</td>
            `;

                if (isAdmin) {
                    html += `<td>${lesson.userName}</td>`;
                }

                html += `</tr>`;
            });

            html += `
                </tbody>
            </table>
        `;

            lessonListDiv.innerHTML = html;

        })
        .catch(err=> {
            console.log("Lỗi Khi gọi API ",err)
            lessonListDiv.innerHTML="<p>Đã Xảy Ra Lỗi !</p>"
        })
});


//ẩn hiện form
function showAddlessonFormOnly(){
    var addForm = document.getElementById("lesson_add")
    var showform = document.getElementById("CourseLessonList")

    addForm.style.display="block"
    showform.style.display="none"
}

function cancelAddLesson() {
    document.getElementById("lesson_add").style.display = "none";
    document.getElementById("CourseLessonList").style.display = "block";
}
//end ẩn hiện form

//call api khoas hoc
fetch('http://localhost:3000/admin/api/lesson/add')
    .then(response =>{
        if(! response.ok){
            throw new Error("Failed to fetch course list")
        }
        return response.json()
    })
    .then(data =>{
        const select = document.getElementById("Course")
        const label = document.getElementById('courseLabel')
        // console.log(data)

        if (data && data.length > 0 ){
            label.style.display ="block"
            select.innerHTML = '<option disabled selected>-- Select a Course --</option>'
            data.forEach(function(entry) {
                const option = document.createElement('option');
                option.value = entry.slug;
                option.textContent = entry.title;
                select.appendChild(option);
            });

        }else {
            label.style.display = 'none'
        }
    })
    .catch(err =>{
        console.log("Lỗi gọi API" , err)
    })
//end api khoa hojc

// updaloaf ảnh
async function uploadImage(file) {
    const formData = new FormData();
    formData.append("file", file);

    const res = await fetch("/admin/api/upload", {
        method: "POST",
        body: formData
    });

    const data = await res.json();
    return data.url;

}

// upload ảnh

// api thêm lesson
const abc = document.getElementById("lesson-form-add")
// console.log(abc)
abc.addEventListener("submit",async function (event) {
    event.preventDefault();
    const formdata = new FormData(event.target)
    const fileInput = document.getElementById('image');
    const file = fileInput.files[0];
    let imgadd;
    if (file) {
        imgadd = await uploadImage(file)
    }
    const duration = formdata.get("duration");
    const OrderIndex = formdata.get("OrderIndex")
    console.log(OrderIndex)
    console.log(duration)

    const data = {
        courseName: formdata.get("course"),
        title: formdata.get("title"),
        description: formdata.get("description"),
        type: formdata.get("type"),
        content: formdata.get("content"),
        duration: duration,
        image: imgadd,
        orderIndex:OrderIndex


    }
    fetch("/admin/api/lesson/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (res.ok ) {
                alert("Thêm Thành Công ")
                location.reload()
            }
        })
        .then(err => {
            alert("Lỗi khi gọi api " + err)

        })
});


// end api them lesson

// hiện ảnh

document.getElementById('image').addEventListener('change', function(e) {
    var file = e.target.files[0];
    if (file) {
        var fileName = file.name;
        document.getElementById('file-name').textContent = fileName;

        // Hiển thị ảnh xem trước
        var reader = new FileReader();
        reader.onload = function(e) {
            var preview = document.getElementById('preview');
            preview.src = e.target.result;
            preview.style.display = 'block';
        }
        reader.readAsDataURL(file);
    } else {
        document.getElementById('file-name').textContent = 'Chưa có file nào được chọn';
        document.getElementById('preview').style.display = 'none';
    }
});
// end hiện ảnh
