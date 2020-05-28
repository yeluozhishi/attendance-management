//用户
let loginUser;
//右侧子页面
let right_content;
//员工Id
let employeeId;
//用户姓名
let userName;
//左侧菜单是否显示二级菜单
let isdisplay=false;

$(function () {
    loginUser=document.getElementById("loginUser").innerText;
    right_content = document.getElementById("iframe_content");
    userName = document.getElementById("userName").innerText;
    employeeId=document.getElementById("employeeId").value;
})

function funllCalendar() {
    right_content.src="/site/fullclendar?loginUser="+loginUser;

}

//右侧区域高度自适应
function setIframeHeight(iframe) {
    if (iframe) {
        iframe.height = iframe.parentElement.clientHeight+"px";
    }
}

//页面刷新
function page_reload() {
    location.reload();
}

//申请页
function application_page() {
    right_content.src="/site/application_page?employeeId="+employeeId+"&userName="+userName;
}

function logout() {
    $.ajax({
        type:"POST",
        url:'/site/server/logout',
        cache:true,
        async:true,
        data:{
        },
        success:function (data) {
            location.reload();
        }
    })
}

function update_psw() {

    $("#rightContent").append('' +
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
    let loginUser=document.getElementById("loginUser").innerText;
    let psw_old=document.getElementById("psw_old").value;
    let psw_new=document.getElementById("psw_new").value;
    let psw_new1=document.getElementById("psw_new1").value;
    if (psw_new==psw_new1){
        $.ajax({
            type: 'POST',
            url:'/site/server/update_psw',
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

function display_second() {
    if (isdisplay){
        document.getElementById('collapse_zh').style.display='none';
        isdisplay=false;
    }else {
        document.getElementById('collapse_zh').style.display='block';
        isdisplay=true;
    }

}

//关闭dialog
function close_dia(ele) {
    document.getElementById(ele).remove();
}
