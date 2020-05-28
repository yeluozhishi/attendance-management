//添加账户按钮
let add_button;
//账户详细按钮
let detail_div;

let condition="";

let text="";

$(function () {

    init_table(condition);

});
//初始化datatable
function init_table(condition) {

    $('#dataTables-example').dataTable({
        "processing": true,
        "serverSide": true,
        'bStateSave': true,

        ajax : function(data, callback, settings) {
            // paga param
            var size = data.length;
            var page = data.start/data.length + 1;
            $.ajax({
                url: "/administrators/SearchEmployee",
                type: "POST",
                data: {
                    condition:condition,
                    pageNo: page,
                    pageSize: size,
                },
                success: function(data) {
                    data.recordsTotal = data.totalElements;
                    data.recordsFiltered = data.totalElements;
                    data.data = data.content;
                    callback(data);
                }
            });
        } ,
        "columns": [
            {"data": "employeeId",orderable:false},
            {"data": "name",orderable:false},
            {"data": "sex",orderable:false},
            {"data": "age",orderable:false},
            {"data": "position",orderable:false},
            {"data": "salary",orderable:false},
            {"data": "entryTime",orderable:false},
            {"data": "quitTime",orderable:false},
            {"data": "",orderable:false},
            {"data": "","width": 120,orderable:false},
        ],
        "aoColumnDefs": [
            {
                sDefaultContent: '',
                aTargets: [ '_all' ]
            }
        ],
        "aaSorting": [[ 1, "asc" ]],
        "bPaginate": true,
        "sPaginationType": "full_numbers",
        "bJQueryUI": false,

        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "对不起，没有匹配的数据",
            "sInfo": "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
            "sInfoEmpty": "没有匹配的数据",
            "sInfoFiltered": "(数据表中共 _MAX_ 条记录)",
            "sProcessing": "正在加载中...",
            "sSearch": "搜索：",
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": " 上一页 ",
                "sNext": " 下一页 ",
                "sLast": " 最后一页 "
            }
        },
        // 每一行创建完调用的函数
        "createdRow": function (row, data, dataIndex) {
            // row : tr dom
            // data: row data
            // dataIndex:row data's index
            // if (data[4] == "A") {

            $.ajax({
                type:"post",
                url:'/administrators/GetAccount',
                sync:true,
                data:{
                    employeeId:data.employeeId,
                },
                success:function (element) {
                    if (element.accountEntity!="" && element.accountEntity!=null){
                        $('td:eq(8)', row).html('<div onclick="detail_account(this,'+data.employeeId+')">'+element.accountEntity.accountnumber+'</div>');
                    }else {
                        $('td:eq(8)', row).html('<button type="reset" class="btn btn-default" onclick="add_account(this,'+data.employeeId+')">添加账户</button>');
                    }
                }

            });

            $('td:eq(6)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.entryTime)));
            if (data.quitTime!=null){
                $('td:eq(7)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.quitTime)));
            }

            $('td:eq(9)', row).html( '<button type="reset" class="btn btn-default" onclick="update_item(this,'+data.employeeId+')">修改</button>' +
                '<button type="reset" class="btn btn-default" onclick="delete_item('+data.employeeId+')" >删除</button>'  );

        },
    });
    add_search_button();
}

function add_search_button() {
    $("#dataTables-example_filter").children().remove();

    $("#dataTables-example_filter").append('<div class="input-group pull-right"><input id="searchforme" ' +
        'type="text" class="form-control" value="'+text+'"><span class="input-group-btn">' +
        '<button id="go" onclick="employee_search()" class="btn btn-default" type="button"><i class="fa fa-search"></i></button></span></div>');
}

function delete_item(id) {
    document.getElementById("delete_Dialog").style.display="block";
    document.getElementById("message").innerHTML="标号"+id+"员工";
    document.getElementById("message").dataset.type="employee";
}
//确定删除
function certain() {
    let Str= document.getElementById("message").innerHTML;
    //删除员工信息
    if (document.getElementById("message").dataset.type=="employee"){
        let id=parseInt(Str.substring(2,Str.length-2));
        $.ajax({
            type:"POST",
            url:'/administrators/DeleteEmployee',
            sync:true,
            data:{
                employeeId:id,
            },
            success:function (data) {
                alert("删除成功！");
                document.getElementById("delete_Dialog").style.display="none";
                //删除账户
                $.ajax({
                    type:"POST",
                    url:'/administrators/DeleteAccount',
                    sync:true,
                    data:{
                        employeeId:id,
                    },
                    success:function (data) {
                    }
                });
                //刷新当前页
                window.location.reload();
            }
        });

    }

    //删除账户
    if(document.getElementById("message").dataset.type=="account"){
        let id=parseInt(Str.substring(2,Str.length-5));
        $.ajax({
            type:"POST",
            url:'/administrators/DeleteAccount',
            sync:true,
            data:{
                employeeId:id,
            },
            success:function (data) {
                alert("删除成功！");
                document.getElementById("delete_Dialog").style.display="none";
                document.getElementById("account_Dialog").style.display="none";
                let td = detail_div.parentElement;
                add_button.remove();
                td.innerHTML='<button type="reset" class="btn btn-default" onclick="add_account(this,'+id+')">添加账户</button>';
            }
        });
    }
}
//显示修改员工信息dialog
function update_item(ele,id) {
    let ta = document.getElementById("savePlanWork");
    let list_table = ta.getElementsByClassName("form-control");
    let list_data = ele.parentNode.parentNode.getElementsByTagName("td");
    for (let i=0; i<list_data.length-3;i++){
        list_table[i].value=list_data[i+1].innerHTML;
    }
    document.getElementById("rwDialog").style.display="block";
    ta.dataset.id=id;
}
//关闭dialog
function close_dia(ele) {
    document.getElementById(ele).style.display="none";
    if (ele!='delete_Dialog'){
        let ac = document.getElementById(ele);
        let list = ac.getElementsByClassName("form-control");
        for (let i=0; i<list.length;i++){
            list[i].value="";
        }
    }
    document.getElementById("savePlanWork").dataset.id="";
}
//保存修改后员工信息
function save_employee() {
    let ta = document.getElementById("savePlanWork");
    let list_table = ta.getElementsByClassName("form-control");
    let id = ta.dataset.id;

    if(id!=null && id!=""){
        let entryTime = new Date(list_table[5].value);
        let quitTime;
        if (list_table[6].value!=""){
            quitTime = new Date(list_table[6].value);
        }

        $.ajax({
            type:'POST',
            url:'/administrators/UpdateEmployee',
            sync: true,
            data:{
                employeeId:id,
                name:list_table[0].value,
                sex:list_table[1].value,
                age:list_table[2].value,
                position:list_table[3].value,
                salary:list_table[4].value,
                entryTime:entryTime,
                quitTime:quitTime,
            },
            success:function (data) {
                if (data.code=="10000"){
                    document.getElementById("rwDialog").style.display="none";
                    window.location.reload();
                }else {
                    alert("失败！");
                }
            }
        });
    }else {
        for (let i=0;i<list_table.length-2;i++){
            if (list_table[i].value==null){
                alert("不能有空");
                return;
            }
        }
        let age = parseInt(list_table[2].value);
        $.ajax({
            type:'POST',
            url:'/administrators/AddEmployee',
            sync: true,
            data:{
                name:list_table[0].value,
                sex:list_table[1].value,
                age:age,
                position:list_table[3].value,
                salary:list_table[4].value,
            },
            success:function (data) {
                if (data.code=="10000"){
                    document.getElementById("rwDialog").style.display="none";
                    window.location.reload();
                }else {
                    alert("失败！");
                }
            }
        });
    }
}

//时间格式化
function dateFtt(fmt,date)
{
    var o = {
        "M+" : date.getMonth()+1,     //月份
        "d+" : date.getDate(),     //日
        "H+" : date.getHours(),     //小时
        "m+" : date.getMinutes(),     //分
        "s+" : date.getSeconds(),     //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S" : date.getMilliseconds()    //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

function detail_account(ele,id) {
    detail_div=ele;
    let ac = document.getElementById("account_Dialog");
    let list = ac.getElementsByClassName("form-control");
    $.ajax({
        type:"post",
        url:'/administrators/GetAccount',
        sync:true,
        data:{
            employeeId:id,
        },
        success:function (element) {
            if (element.accountEntity!="" && element.accountEntity!=null){
                list[0].value=id;
                list[1].value=element.accountEntity.accountnumber;
                list[2].value=element.accountEntity.password;
                list[3].value=element.accountEntity.createTime;
            }
        }
    });
    ac.style.display="block";
    document.getElementById("delete_ac").style.display="inline-block";

}

function add_account(ele,id) {
    document.getElementById("account_Dialog").style.display="block";
    document.getElementById("createTime").style.display="none";
    document.getElementById("account").value=id;
    add_button=ele;
}

function certain_add() {
    let ac = document.getElementById("account_Dialog");
    let list = ac.getElementsByClassName("form-control");
    $.ajax({
        type:"post",
        url:'/administrators/AddAccount',
        sync:true,
        data:{
            employeeId:list[0].value,
            account:list[1].value,
            password:list[2].value,
        },
        success:function (element) {
            if (element.code=="10001" && element.message=="已存在"){
                alert("账户名"+element.message);
            }else if (element.code=="10000"){

                ac.style.display="none";
                //替换按钮
                let td = add_button.parentElement;
                add_button.remove();
                td.innerHTML='<div onclick="detail_account('+list[0].value+')">'+list[1].value+'</div>';
                for (let i=0;i<list.length;i++){
                    list[i].value="";
                }

            }else {
                alert("非法参数");
            }
        }
    });
}
//删除账户
function delete_account() {
    let id = document.getElementById("account").value;
    document.getElementById("delete_Dialog").style.display="block";
    document.getElementById("message").innerHTML="编号"+id+"员工的账户";
    document.getElementById("message").dataset.type="account";
}
//显示添加职员dialog
function add_employee() {
    document.getElementById("rwDialog").style.display="block";
    document.getElementById("entryTime").style.display="none";
    document.getElementById("quitTime").style.display="none";
}

function employee_search() {
    let req=document.getElementById("searchforme").value;
    text=req;
    $('#dataTables-example').dataTable().fnDestroy();
    init_table(req);
}