//所选时间
let date;
//搜索账户
let account="";
//禁用日期
let isForbidDate=false;

var mainApp = {

    initFunction: function () {
        /*MENU
        ------------------------------------*/
        $('#main-menu').metisMenu();

        $(window).bind("load resize", function () {
            if ($(this).width() < 768) {
                $('div.sidebar-collapse').addClass('collapse')
            } else {
                $('div.sidebar-collapse').removeClass('collapse')
            }
        });

        $.ajax({
            type:'post',
            url:'/administrators/sign_state',
            sync:true,
            data:{},
            success:function (data) {
                let today=new Date();
                let year=today.getFullYear();
                let month=today.getMonth();
                let list=new Array();
                for (let i=0;i<6;i++){
                    if(month==0){
                        year-=1;
                        month=12;
                    }
                    list.push(year+"-"+month);
                    month-=1;
                }
                Morris.Line({

                    element: 'morris-line-chart',
                    data: [
                        { y: list[5], a: data.exceptions[5], b: data.normals[5]},
                        { y: list[4], a: data.exceptions[4], b: data.normals[4]},
                        { y: list[3], a: data.exceptions[3], b: data.normals[3]},
                        { y: list[2], a: data.exceptions[2], b: data.normals[2]},
                        { y: list[1], a: data.exceptions[1], b: data.normals[1]},
                        { y: list[0], a: data.exceptions[0], b: data.normals[0]}
                    ],

                    xkey: 'y',
                    ykeys: ['a', 'b'],
                    labels: ['签到无异常', '签到异常'],
                    fillOpacity: 0.6,
                    hideHover: 'auto',
                    behaveLikeLine: true,
                    resize: true,
                    pointFillColors:['#ffffff'],
                    pointStrokeColors: ['black'],
                    lineColors:['gray','#24C2CE']

                });
            },
        });

    },

    initialization: function () {
        mainApp.initFunction();

    }

}
// Initializing ///
$(function () {
    mainApp.initFunction();

    $("#sideNav").click(function () {
        if ($(this).hasClass('closed')) {
            $('.navbar-side').animate({left: '0px'});
            $(this).removeClass('closed');
            $('#page-wrapper').animate({'margin-left': '260px'});
        } else {
            $(this).addClass('closed');
            $('.navbar-side').animate({left: '-260px'});
            $('#page-wrapper').animate({'margin-left': '0px'});
        }
    });

    date=new Date();
    document.getElementById("date_selcet").innerText=dateFtt("yyyy-MM",date);

    init_table();

    creatJeDate ("#date_selcet");
});



//初始化datatable
function init_table() {

    let type=document.getElementById("type_sel").selectedIndex;
    $('#dataTables-example').dataTable({
        "processing": true,
        "serverSide": true,
        'bStateSave': true,

        ajax : function(data, callback, settings) {
            // paga param
            var size = data.length;
            var page = data.start/data.length + 1;
            $.ajax({
                url: "/administrators/SearchSignItem",
                type: "POST",
                data: {
                    account:account,
                    type:type,
                    date:date,
                    isForbidDate:isForbidDate,
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
            {"data": "accountnumber",orderable:false},
            {"data": "signinTime",orderable:false},
            {"data": "signoutTime",orderable:false},
            {"data": "signDuration",orderable:false},
            {"data": "extraduration",orderable:false},
            {"data": "exception",orderable:false},
            {"data": "exceptionDetail",orderable:false},
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
            if (data.signinTime!=null){
                $('td:eq(2)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.signinTime)));
            }
            if (data.signoutTime!=null){
                $('td:eq(3)', row).html(dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.signoutTime)));
            }

            $('td:eq(8)', row).html( '<button type="reset" class="btn btn-default" onclick="update_item(this,'+data.employeeId+')">修改</button>');

        },
    });
    add_search_button();
}

function add_search_button() {
    $("#dataTables-example_filter").children().remove();

    $("#dataTables-example_filter").append('<div class="input-group pull-right"><input id="searchforme" placeholder="账户名" value="'+account+'" ' +
        'type="text" class="form-control" value=""><span class="input-group-btn">' +
        '<button id="go" onclick="searchitem(\'search\')" class="btn btn-default" type="button"><i class="fa fa-search"></i></button></span></div>');
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

function searchitem(b) {
    let year = date.getFullYear();
    let month=date.getMonth();
    if (b=='last'){
        if (month==1){
            year-=1;
            month=12;
        }else {
            month-=1;
        }
        date=new Date(year,month,1);
        document.getElementById("date_selcet").innerText=dateFtt("yyyy-MM",date);
        $('#dataTables-example').dataTable().fnDestroy();
        init_table();
    }

    if (b=='next'){
        if (month==12){
            year+=1;
            month=1;
        }else {
            month+=1;
        }
        date=new Date(year,month,1);
        document.getElementById("date_selcet").innerText=dateFtt("yyyy-MM",date);
        $('#dataTables-example').dataTable().fnDestroy();
        init_table();
    }

    if (b=='type'){
        $('#dataTables-example').dataTable().fnDestroy();
        init_table();
    }

    if (b=='search'){
        account=document.getElementById("searchforme").value;
        $('#dataTables-example').dataTable().fnDestroy();
        init_table();
    }
}

//初始化jeDate
function creatJeDate (parmId){
    jeDate(parmId,{
        format:"YYYY-MM",               //日期格式
        isTime:true,                                //是否开启时间选择
        minDate:"2014-09-19 00:00:00",              //最小日期 或者 "2014-09-19 00:00:00"
        maxDate:"2099-12-31 23:59:59",              //最大日期 或者 "2099-12-31" 或者 "16:35:25"
        zIndex:2099,                                //弹出层的层级高度
        skinCell:"jedateblue",                      //日期风格样式，默认蓝色
        donefun: function(obj){
            //this    而this指向的都是当前实例
            date=new Date(obj.val);
            $('#dataTables-example').dataTable().fnDestroy();
            init_table();
        }
    })
};

function changeState() {
    isForbidDate=document.getElementById("forbidDate").checked;
    if (isForbidDate){
        document.getElementById("last").disabled="disabled";
        document.getElementById("next").disabled="disabled";
        document.getElementById("date_selcet").disabled="disabled";
    }else {
        document.getElementById("last").disabled="";
        document.getElementById("next").disabled="";
        document.getElementById("date_selcet").disabled="";
    }
}