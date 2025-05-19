fetch(`/admin/api/role/show`)
    .then(res => res.json())
    .then(data => {
        const tableBody = document.getElementById("permissionTableBody");
        tableBody.innerHTML = "";

        data.forEach(permission  => {
            const row = `
                <tr id="row-${permission.roleName}-${permission.permissionName}">
                    <td>${permission.roleName}</td>
                    <td id="permissionCell-${permission.roleName}-${permission.permissionName}">
                        ${permission.permissionName}
                    </td>
                    <td id="actionCell-${permission.roleName}-${permission.permissionName}">
                        <button class="btn btn-warning mx-2" onclick="editPermission('${permission.roleName}', '${permission.permissionName}')">Sửa</button>
                        <button class="btn btn-danger" onclick="softDeleteRolePermission('${permission.roleName}', '${permission.permissionName}')">Xóa</button>
                    </td>                                                     
                </tr>
            `;
            tableBody.innerHTML += row;
        });
    })
    .catch(err => {
        console.error("Lỗi khi lấy danh sách role:", err);
    });

function hideAllForms() {
    document.getElementById("RoleAddForm").style.display = "none";
    document.getElementById("rolePermissionTable").style.display = "none"; // r thường
}

function showRoleList() {
    hideAllForms();
    document.getElementById("rolePermissionTable").style.display = "block"; // r thường
}

function showAddRoleFormOnly() {
    hideAllForms();
    document.getElementById("RoleAddForm").style.display = "block";
}

//add permissions
document.getElementById("addRoleForm").addEventListener('submit',function (e){
    e.preventDefault();
    const formdata = new FormData(this);
    const data = {
        roleName: formdata.get("roleName"),
        permissionName: formdata.get("permissionName")
    }
    fetch('/admin/api/role/permissionsAdd',{
        method:"POST",
        headers:{
            'content-Type' : 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(res =>{
            if (res.ok){
                alert("Thêm Thành Công ")
                showRoleList()
                location.reload()
            }else {
                return res.json().then(err=>{throw err;})
            }
        })
        .catch(err => {
            console.log("Lỗi", err)
            alert("Lỗi")
        })
})


// Xóa
function softDeleteRolePermission(roleName , permissionName){
    const url = `/admin/api/role/delete?roleName=${encodeURIComponent(roleName)}&permissionName=${encodeURIComponent(permissionName)}`;
    console.log(roleName)
    console.log(permissionName)
    console.log(url)
    if (confirm("Bạn có chắc chắn muốn Xóa Quyền Này chứ ?")){
        fetch(url,{
            method:"DELETE",
            headers: {
                "Content-Type": "application/json"
            }


        })

            .then(res => {
                    if (res.ok) {
                        alert("Xóa thành công!");
                        location.reload();
                    } else {
                        return res.json().then(errorData => {
                            alert("Xóa thất bại: " + (errorData.message || "Không rõ lỗi"));
                            console.error("Chi tiết lỗi từ backend:", errorData);
                        });
                    }
                })


            .catch(err => {
                console.error("Lỗi khi gọi Api ",err)
                alert("Đã xảy ra Lỗi ")
            })

    }
}
// end Xóa
function editPermission(roleName, permissionName){
    const permissionCell = document.getElementById(`permissionCell-${roleName}-${permissionName}`)
    const actionCell = document.getElementById(`actionCell-${roleName}-${permissionName}`)
    // console.log(editPermission,actionCell)
//     Lưu Lại Giá Trị hiện tại để có thể khôi phục khi ấn nut Hủy
    const currentPermissionName = permissionCell.textContent.trim();
    // console.log(currentPermissionName)

//     tạo input để suawra
    const inputElement  = document.createElement('input');
    inputElement.type="text";
    inputElement.value=currentPermissionName;
    inputElement.classList.add('form-control','form-control-sm')

    permissionCell.innerHTML=''
    permissionCell.appendChild(inputElement)

//     taoj nut luu
    const saveButton = document.createElement('button');
    saveButton.textContent= 'Lưu';
    saveButton.classList.add("btn",'btn-success','mx-2');
    saveButton.onclick=function (){
        const newPermissionName = inputElement.value.trim();
        savePermission (roleName,currentPermissionName,newPermissionName)
    }

    // tạo nút đóng
    const cancelButton = document.createElement('button');
    cancelButton.textContent='Hủy';
    cancelButton.classList.add('btn','btn-secondary')
    cancelButton.onclick=function (){
    //     khôi phục lại data cho permissionName
        permissionCell.textContent = currentPermissionName
        actionCell.innerHTML=`
               
            <button class="btn btn-warning mx-2" onclick="editPermission('${roleName}', '${currentPermissionName}')">Sửa</button>
            <button class="btn btn-danger" onclick="softDeleteRolePermission('${roleName}', '${currentPermissionName}')">Xóa</button>
        `
    }

    // thay thế nút sửa
    actionCell.innerHTML=''
    actionCell.appendChild(saveButton)
    actionCell.appendChild(cancelButton)

    function savePermission(roleName , olePermissionName,newPermissionName){
        // console.log(roleName,olePermissionName,newPermissionName)

        fetch(`/admin/api/role/update`,{
            method: 'PUT',
            headers:{
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify({
                roleName: roleName,
                oldPermissionName: olePermissionName,
                newPermissionName: newPermissionName
            }),
        })
            .then(res=>res.json())
            .then(data => {
                alert("Thành Công")
                location.reload()
            } )
            .catch(er =>{
                console.error(er)
            })


    }




}






