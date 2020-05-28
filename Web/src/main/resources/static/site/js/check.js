let ischeck="";
let type="";
let condition="";

$(function () {
    init_table("","","");
});

function init_table(type,ischecked,condition) {
    $('#dataTables-example').dataTable({
        "processing": true,
        "serverSide": true,
        'bStateSave': true,

        ajax : function(data, callback, settings) {
            // paga param
            var size = data.length;
            var page = data.start/data.length + 1;

            //query params
            // var params = $('#form-params').serializeArray();
            // for (var i=0;i<params.length;i++) {
            //     data[params[i].name] = params[i].value;
            // }


            $.ajax({
                url : "/administrators/check_item_get",
                type: "POST",
                sync: true,
                data: {
                    type:type,
                    ischecked:ischecked,
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
            {"data": "employeeId",orderable:false},
            {"data": "",orderable:false},
            {"data": "applicationType",orderable:false},
            {"data": "createTime",orderable:false},
            {"data": "applicationReason",orderable:false},
            {"data": "startTime",orderable:false},
            {"data": "endTime",orderable:false},
            {"data": "reviewTime",orderable:false},
            {"data": "reviewResult",orderable:false},
            {"data": "","width": 60,orderable:false},
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

            $.ajax({
                type:"post",
                url:'/administrators/GetAccount',
                sync:true,
                data:{
                    employeeId:data.employeeId,
                },
                success:function (element) {
                    $('td:eq(2)', row).html(element.accountEntity.accountnumber);

                }

            });

            $('td:eq(4)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.createTime)));
            $('td:eq(6)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.startTime)));
            $('td:eq(7)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.endTime)));
            if (data.reviewTime!="" && data.reviewTime!=null){
                $('td:eq(8)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.reviewTime)));
            }
            $('td:eq(10)', row).html( '<button type="reset" class="btn btn-default" onclick="cheeck_item(this,'+data.id+')">审核</button>');

        },
    });
    add_search_button();
}

function add_search_button() {
    $("#dataTables-example_filter").children().remove();

    $("#dataTables-example_filter").append('<div class="input-group pull-right"><input id="searchforme" ' +
        'type="text" class="form-control" value="'+condition+'"><span class="input-group-btn">' +
        '<button id="go" onclick="check_search()" class="btn btn-default" type="button"><i class="fa fa-search"></i></button></span></div>');
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

function cheeck_item(ele,id) {
    let ta = document.getElementById("savePlanWork");
    let list_data = ele.parentNode.parentNode.getElementsByTagName("td");

    document.getElementById("check_content").innerHTML='' +
        '编号：' +list_data[0].innerHTML+'\n' +
        '员工号：' +list_data[1].innerHTML+'\n' +
        '姓名：' +list_data[2].innerHTML+'\n' +
        '创建时间：'+list_data[3].innerHTML+'\n' +
        '申请原因：'+list_data[4].innerHTML+'\n' +
        '开始时间：'+list_data[5].innerHTML+'\n' +
        '截止时间：'+list_data[6].innerHTML+'\n' +
        '审核时间：'+list_data[7].innerHTML+'\n' +
        '审核结果：'+list_data[8].innerHTML+'\n'

    document.getElementById("rwDialog").style.display="block";
    ta.dataset.id=id;
}

function close_dia(element){
    document.getElementById(element).style.display="none";
    document.getElementById("check_content").innerHTML="";
    document.getElementById("savePlanWork").dataset.id="";

}

function save_application(element) {
    let id = parseInt(document.getElementById("savePlanWork").dataset.id);
    let result=0;
    if(element=='agree'){
        result=1;
    }else if(element=='disagree'){
        result=2;
    }

    $.ajax({
        type: 'POST',
        url: '/administrators/check_item',
        sync: true,
        data:{
            id:id,
            result:result,
        },
        success:function (date) {
            alert(date.message);
            document.getElementById("rwDialog").style.display="none";
            window.location.reload();
        }
    })

}

function check_search() {
    condition=document.getElementById("searchforme").value;
    type=document.getElementById("type_sel").value;
    ischeck=document.getElementById("check_sel").value;
    $('#dataTables-example').dataTable().fnDestroy();
    init_table(type,ischeck,condition);
}