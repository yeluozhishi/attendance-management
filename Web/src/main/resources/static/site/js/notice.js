let condition="";

let text="";

$(function () {

    init_table(condition);

});

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
                url : "/administrators/notice_get",
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
            {"data": "id",orderable:false},
            {"data": "accountnumber_name",orderable:false},
            {"data": "accountnumber",orderable:false},
            {"data": "createTime",orderable:false},
            {"data": "missionContent","width": 500,orderable:false},
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

            $('td:eq(3)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.createTime)));

            $('td:eq(5)', row).html( '<button type="reset" class="btn btn-default" onclick="update_item(this,'+data.id+')">修改</button>' +
                '<button type="reset" class="btn btn-default" onclick="delete_item('+data.id+')" >删除</button>'  );

        },
    });
    add_search_button();
}

function add_search_button() {
    $("#dataTables-example_filter").children().remove();

    $("#dataTables-example_filter").append('<div class="input-group pull-right"><input id="searchforme" ' +
        'type="text" class="form-control" value="'+text+'"><span class="input-group-btn">' +
        '<button id="go" onclick="notice_search()" class="btn btn-default" type="button"><i class="fa fa-search"></i></button></span></div>');
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

function delete_item(id) {
    let d = document.getElementById("delete_Dialog");
    d.style.display="block";
    d.dataset.id=id;
}

function update_item(ele,id) {
    let ta = document.getElementById("savePlanWork");
    let list_table = ta.getElementsByClassName("form-control");
    let list_data = ele.parentNode.parentNode.getElementsByTagName("td");

    list_table[0].value=list_data[1].innerHTML;
    list_table[1].value=list_data[4].innerHTML;

    document.getElementById("rwDialog").style.display="block";
    ta.dataset.id=id;
}

function save_notice() {
    let ta = document.getElementById("savePlanWork");
    let list_table = ta.getElementsByClassName("form-control");
    let id = ta.dataset.id;

    if(id!=null && id!=""){

        $.ajax({
            type:'POST',
            url:'/administrators/notice_update',
            sync: true,
            data:{
                id:id,
                name:list_table[0].value,
                content:list_table[1].value,
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

        $.ajax({
            type:'POST',
            url:'/administrators/notice_add',
            sync: true,
            data:{
                name:list_table[0].value,
                content:list_table[1].value,
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

function certain() {
    let d = document.getElementById("delete_Dialog");
    let id=d.dataset.id;
    $.ajax({
        type: 'POST',
        url:'/administrators/notice_delete',
        sync:true,
        data:{
            id:id,
        },
        success:function (date) {
            d.style.display="none";
            window.location.reload();
        },
    });
}

function add_notice() {
    document.getElementById("rwDialog").style.display="block";
}

function notice_search() {
    let req=document.getElementById("searchforme").value;
    text=req;
    $('#dataTables-example').dataTable().fnDestroy();
    init_table(req);
}