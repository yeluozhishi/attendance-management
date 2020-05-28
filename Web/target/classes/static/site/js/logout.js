function manager_logout() {
    $.ajax({
        url: '/administrators/logout',
        type: 'POST',
        sync:true,
        date:{},
        success:function (date) {
            window.location.href="/admin/administrators_login";
        }

    })
}

function update_psw() {

    $("#page-inner").append('' +
        '<div class="" data-backdrop="false" id="psw_Dialog" style="z-index: 50; width: 1580px;height: 1200px;position: absolute;top: 0px;left: 0px;">\n' +
        '                <div class="modal-dialog" >\n' +
        '                    <div class="modal-content" style="margin-top: 200px;">\n' +
        '                        <div class="modal-header">\n' +
        '                            <span style="font-size: 12px;"><strong>修改密码</strong></span>\n' +
        '                        </div>\n' +
        '                        <div class="modal-body">\n' +
        '                            <div class="form-group">\n' +
        '                                <label>原密码：</label>\n' +
        '                                <input type="password" id="psw_old" class="form-control" style="width: 300px;">\n' +
        '                            </div>\n' +
        '                            <div class="form-group">\n' +
        '                                <label>新密码：</label>\n' +
        '                                <input type="password" id="psw_new" class="form-control" style="width: 300px;">\n' +
        '                            </div>\n' +
        '                            <div class="form-group">\n' +
        '                                <label>再次输入新密码：</label>\n' +
        '                                <input type="password" id="psw_new1" class="form-control" style="width: 300px;">\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <div class="modal-footer">\n' +
        '                            <button class="btn btn-default" data-dismiss="modal" onclick="close_dia(\'psw_Dialog\')">关闭</button>\n' +
        '                            <button class="btn btn-primary" onclick="certain_update()">确定</button>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>');

}

function certain_update() {
    let loginUser=document.getElementById("loginUser").value;
    let psw_old=document.getElementById("psw_old").value;
    let psw_new=document.getElementById("psw_new").value;
    let psw_new1=document.getElementById("psw_new1").value;
    if (psw_new==psw_new1){
        $.ajax({
            type: 'POST',
            url:'/administrators/update_psw',
            sync:true,
            data:{
                loginUser:loginUser,
                psw_old:psw_old,
                psw_new:psw_new,
            },
            success:function (date) {
                if (date.code=="10000"){
                    alert("修改成功");
                    window.location.reload();
                }else {
                    alert("原密码错误");
                }
            },
        });
    }else {
        alert("新密码不一致");
    }

}
