// Cập nhật file login.js
document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    
    const submitBtn = document.getElementById("submitBtn");
    const originalText = submitBtn.querySelector(".button-text").textContent;
    
    try {
        // Vô hiệu hóa nút và hiển thị loading
        submitBtn.disabled = true;
        submitBtn.classList.add("loading");
        submitBtn.querySelector(".button-text").textContent = "Loading...";

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const apiUrl = `${apiBaseUrl}/api/user/login`;

        const response = await fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password }),
            credentials: "include"
        });

        if (response.ok) {
            window.location.href = "/";
        } else {
            const errorData = await response.json();
            alert(errorData.message || "Login failed!");
        }
    } catch (error) {
        alert("Login failed!");
    } finally {
        // Khôi phục trạng thái nút
        submitBtn.disabled = false;
        submitBtn.classList.remove("loading");
        submitBtn.querySelector(".button-text").textContent = originalText;
    }
});

//ẩn Hiện Form

function hideAllForm() {
    document.getElementById("loginForm").style.display = "none";
    document.getElementById("registerForm").style.display = "none";
    document.getElementById("forgotForm").style.display = "none";
    document.getElementById("navButtons").style.display = "none";
}

function showFormLogin() {
    hideAllForm();
    document.getElementById("loginForm").style.display = "block";
    document.getElementById("navButtons").style.display = "block";
}

function showFormRegister() {
    hideAllForm();
    document.getElementById("registerForm").style.display = "block";
}

function showFormForgot() {
    hideAllForm();
    document.getElementById("forgotForm").style.display = "block";
}

//end Ẩn Hiện Form

//regiter
    document.getElementById("registerForm").addEventListener("submit", function (e) {
        e.preventDefault()
        const formData = new FormData(e.target)
        // console.log(formData)
        const data = {
            email:formData.get("userName"),
            fullName:formData.get("email"),
            userName:formData.get("password"),
            password: formData.get("fullName"),
            dateOfBirth :formData.get("dateOfBirth"),
            phoneNumber :formData.get("phoneNumber"),
            address :formData.get("address"),
            roleName:formData.get("role")



        }
        console.log(data);
        fetch("http://localhost:3000/api/user/register",{

            method: "POST",
            headers : {
                "Content-Type" : "application/json",
            },
            body:JSON.stringify(data)
        })


            .then(res => {
                if (res.ok) {
                    alert("Thêm Thành Công Người Dùng !")
                    location.reload()

                } else {
                    return res.text().then(errmes => {
                        alert("Lỗi Từ Server " + errmes)
                        console.log("Chi TIết Lỗi " + errmes)
                    })

                }

            })
            .catch(err =>{
                alert("Lỗi Khi Gọi AP" + err.message)
                console.log("Lỗi " +  err)

            })
    })
//end regitter

//quên Mật Khẩu
const messageDiv = document.getElementById("message")
const forgotForm = document.getElementById("forgotForm")
const step2 = document.getElementById("step2")
const step3 = document.getElementById("step3")
console.log(step3)
function showMessage (msg , isError = true){
    messageDiv.textContent=msg
    if (isError) {
        messageDiv.classList.remove('success');
        messageDiv.classList.add('error');
    } else {
        messageDiv.classList.remove('error');
        messageDiv.classList.add('success');
    }
}
function clearMessage() {
    messageDiv.textContent = '';
}

forgotForm.addEventListener("submit" ,  async function (e) {
    e.preventDefault()
    clearMessage()
    const email = document.getElementById("forgorEmail").value.trim()
    // console.log(email)
    if (!email) {
        showMessage('Vui lòng nhập email');
        return;
    }
    try{
        const res = await fetch(`/api/user/forgot`, {
            method : "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({email})
        });
        const data = await res.text()
        if (res.ok){
            showMessage(data.message || 'OTP đã được gửi tới email.', false);
            forgotForm.style.display = 'none';
            step2.style.display = 'block';
        }else {
            showMessage(data.message || 'Gửi email thất bại');

        }

    }
    catch (e){
        showMessage('Lỗi kết nối server');

    }
})

step2.addEventListener("submit", async function (e) {
    e.preventDefault()
    const email = document.getElementById("forgorEmail").value.trim()
    const otp = document.getElementById("otp").value.trim()
    if (!otp) {
        showMessage('Vui lòng nhập mã OTP');
        return;
    }
    try{
        const res = await fetch(`/api/user/verifyOtp` , {
            method : "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                email,
                otp
            })
        })
        const data = await res.json()
        // console.log(data)
        if(res.ok && data.message === "done"){
            showMessage('OTP Hợp Lệ', false);
            step2.style.display = "none"
            step3.style.display = "block"
        }else {
            showMessage(data.message || 'OTP không đúng hoặc đã hết hạn');

        }

    }catch (e){
        showMessage('Lỗi kết nối server');

    }


})

step3.addEventListener("submit",async function (e){
    e.preventDefault()
    const newPassword = document.getElementById("newPassword").value.trim()
    const email = document.getElementById('forgorEmail').value.trim();
    const otp = document.getElementById('otp').value.trim();
    console.log(newPassword + email + otp )
    if(!newPassword){
        showMessage("Vui Lòng Nhập Mật Khẩu")
        return
    }

    try {
        const res = await fetch(`/api/user/reset-password`, {
            method : "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                email,
                otp,
                newPassword})
        })
        const data = await res.json()
        if(data.message === "done_p"){
            showMessage(data.message || 'Đặt lại mật khẩu thành công.', false);
            // Reset form về bước 1
            location.reload()

        }
        else {

        }

    }catch (e){
        showMessage('Lỗi kết nối server');
    }


})

//end Quên Maatj Khaaur