//申请条目块
let application_content;
//申请条目
let application_item;
//员工id
let employeeId;
//页面计数
let counter;
//页面总数
let pages;
//当前页页号标签
let currentPage
//起止时间
let plan_start;
let plan_end;
//父容器
let body;
//申请条目
let items;

$(function () {
    application_content=document.getElementById("application_item");
    employeeId=document.getElementById("employeeId").value;
    currentPage=document.getElementById("currentPage");
    body = document.getElementById("body");
    counter=1;
    init();
    gearBox();
    creatJeDate ("#plan_start");
    creatJeDate ("#plan_end");
})

function init() {
    $.ajax({
        type:"POST",
        url:'/site/server/GetOffworkApplicationItem',
        cache:true,
        async:true,
        data:{
            employeeId:employeeId,
            pageNo:1,
        },
        success:function (data) {
            pages=data.totalPages;
            items=data.content;
            for (var i=0;i<data.content.length;i++){
                let content=items[i];
                let reviewTime;
                let reviewResult;
                let createTime=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(content.createTime));
                if (content.reviewTime!=null){
                    reviewTime=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(content.reviewTime));
                    reviewResult=content.reviewResult;
                }else {
                    reviewTime="未审核";
                    reviewResult="无";
                }
                $("#application_item").append("<tbody name=\"it\">" +
                    "            <tr>" +
                    "                <td>"+content.applicationType+"</td>" +
                    "                <td>"+content.applicationReason+"</td>" +
                    "                <td>"+createTime+"</td>" +
                    "                <td>"+reviewTime+"</td>" +
                    "                <td>"+reviewResult+"</td>" +
                    "                <td class=\"text-center\">" +
                    "                    <a href=\"javascript:void(0)\" data-id="+i+" onclick=\"showHC(this)\"><span class=\"glyphicon glyphicon-th\"></span></a>" +
                    "                    <textarea style=\"display: none;\" rows=\"\" cols=\"\" id=\"${item.plan_id}\">" +
                    "                                ${item.plan_content}" +
                    "                            </textarea>" +
                    "                </td>" +
                    "            </tr>" +
                    "        </tbody>");
            }
        }
    })
}

//时间格式化
function dateFtt(fmt,date)
{ //author: meizz
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

function goPageHC(state) {
    if (state=="qian"){
        if (counter==1){
            return;
        }else {
            counter--;
            currentPage.innerHTML=counter;
            display_items(counter);
        }
    }else if (state=="hou"){
        if (counter==pages){
            return;
        }else {
            counter++;
            currentPage.innerHTML=counter;
            display_items(counter);
        }
    }

}

function display_items(pageNo) {
    //移除旧条目
    var obj = document.getElementsByName("it");
    var l=obj.length;
    for(var i = 0;i<l;i++){
        obj[0].remove();//remove为jQuery方法。
    }

    $.ajax({
        type:"POST",
        url:'/site/server/GetOffworkApplicationItem',
        cache:true,
        async:true,
        data:{
            employeeId:employeeId,
            pageNo:pageNo,
        },
        success:function (data) {
            let content;
            let reviewTime;
            let reviewResult;
            let createT;
            items=data.content;
            for (var i=0;i<data.content.length;i++){
                content=items[i];
                createT=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(content.createTime));
                if (content.reviewTime!=null){
                    reviewTime=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(content.reviewTime));
                    reviewResult=content.reviewResult;
                }else {
                    reviewTime="未审核";
                    reviewResult="无";
                }
                $("#application_item").append("<tbody name=\"it\">" +
                    "            <tr>" +
                    "                <td>"+content.applicationType+"</td>" +
                    "                <td>"+content.applicationReason+"</td>" +
                    "                <td>"+createT+"</td>" +
                    "                <td>"+reviewTime+"</td>" +
                    "                <td>"+reviewResult+"</td>" +
                    "                <td class=\"text-center\">" +
                    "                    <a href=\"javascript:void(0)\" data-id="+i+" onclick=\"showHC(this)\"><span class=\"glyphicon glyphicon-th\"></span></a>" +
                    "                </td>" +
                    "            </tr>" +
                    "        </tbody>");
            }
        }
    })
}

//齿轮按钮
function gearBox() {
    var canvas = document.getElementById("myCanvas");
    var context = canvas.getContext("2d");

//位置
    var centerX = canvas.width/2;
    var centerY = canvas.height/2;

//齿轮半径
    var outerRadius = 15;

//齿轮交叉口半径
    var innerRadius = 5;

//除去牙齿的齿轮半径
    var midRadius = innerRadius*1.6;

//圆孔半径
    var holeRadius = 5;

// NUM点是需要点的数量
//使轮齿。齿轮齿数
//等于一半的点数。在这个配方，
//我们将用时50分，这相当于25个齿轮齿。
    var numPoints = 16;

//绘制齿轮的齿数
    context.beginPath();

//设置的lineJoine属性圆滑，轮齿是平的。
    context.lineJoin = "bevel";

//通过循环点的数量，以创建齿轮形状
    for (var n=0; n<numPoints; n++)
    {
        var radius = null;
        if (n%2==0)
        {
            radius = outerRadius;
        }
        else{
            radius = innerRadius;
        }

        var theta = ((Math.PI*2)/numPoints)*(n+1);
        var x = (radius*Math.sin(theta))+centerX;
        var y = (radius*Math.cos(theta))+centerY;
        if (n==0)
        {
            context.moveTo(x,y);
        }
        else {
            context.lineTo(x,y);
        }
    }

    context.closePath();

    context.lineWidth = 5;
    context.strokeStyle="#004CB3";
    context.stroke();

    context.beginPath();
    context.arc(centerX,centerY,midRadius,0,Math.PI*2,false);

    var grd = context.createLinearGradient(230,0,370,200);
    grd.addColorStop(0,"#8ED6FF");
    grd.addColorStop(1,"#004CB3");
    context.fillStyle = grd;
    context.fill();
    context.lineWidth = 5;
    context.strokeStyle="#004CB3";
    context.stroke();

    context.beginPath();
    context.arc(centerX,centerY,holeRadius,0,Math.PI*2,false);
    context.fillStyle = "white";
    context.fill();
    context.strokeStyle = "#004CB3";
    context.stroke();
}

//显示申请表单
function application_button(isClosed) {
    if (isClosed=="display"){
        $("#rwDialog").removeClass("modal fade");
        $("#rwDialog").addClass("application_dialog");
    }else {
        $("#rwDialog").removeClass("application_dialog");
        $("#rwDialog").addClass("modal fade");
    }
}

//初始化jeDate
function creatJeDate (parmId){
    jeDate(parmId,{
        format:"YYYY-MM-DD hh:mm:ss",               //日期格式
        isTime:true,                                //是否开启时间选择
        minDate:"2014-09-19 00:00:00",              //最小日期 或者 "2014-09-19 00:00:00"
        maxDate:"2099-12-31 23:59:59",              //最大日期 或者 "2099-12-31" 或者 "16:35:25"
        zIndex:2099,                                //弹出层的层级高度
        skinCell:"jedateblue",                      //日期风格样式，默认蓝色
    })
};

function formSubmit() {
    var a=document.getElementById("Type");
    var applicationType=a.options[a.selectedIndex].text;
    if (document.getElementById("plan_start").value!="" && document.getElementById("plan_end").value!=""
    && document.getElementById("plan_content").value!=""){
        var startTime=new Date(document.getElementById("plan_start").value);
        var endTime=new Date(document.getElementById("plan_end").value);
        var applicationReason=document.getElementById("plan_content").value;
    }else {
        alert("请填写完整");
    }

    $.ajax({
        type:"POST",
        url:'/site/server/OffworkApplicationItem',
        cache:true,
        async:true,
        data:{
            employeeId:employeeId,
            applicationType:applicationType,
            applicationReason:applicationReason,
            startTime:startTime,
            endTime:endTime,
        },
        success:function (data) {}
    });
    application_button("close");
}

function showHC(item_id) {
    var id=parseInt(item_id.dataset.id);
    var content=items[id];
    var userName=document.getElementById("userName").innerText;
    $("#body").append("<div class='application_dialog' data-backdrop=\"false\" id=\"ckinDialog\" style=\"z-index: 0;\">" +
        "<div class=\"modal-dialog\" >" +
        "<div class=\"modal-content\">" +
        "<div class=\"modal-header\">" +
        "<span style=\"font-size: 12px;\"><strong>详细信息</strong></span>" +
        "</div>" +
        "<div class=\"modal-body\">" +
        "<div class=\"form-group\">" +
        "<input id=\"ckin_id\" name=\"ckin_id\" type=\"hidden\" value=\"\">" +
        "<label for=\"ckin_content\">" +
        "内容" +
        "</label>" +
        "<textarea rows=\"8\" class=\"form-control\" style='resize: none;' disabled='disabled' name=\"ckin_content\" id=\"ckin_content\" >" +
        "申请人：" +userName+"\n" +
        "申请类型："+content.applicationType+"\n" +
        "创建时间：" +content.createTime+"\n" +
        "起止时间："+content.startTime+"至"+content.endTime+"\n" +
        "审批时间：" +content.reviewTime+"\n" +
        "审批结果：" +content.reviewResult+"\n" +
        "申请原因：" +content.applicationReason+"\n" +
        "</textarea>" +
        "</div>" +
        "</div>" +
        "<div class=\"modal-footer\">" +
        "<button class=\"btn btn-default\" onclick='ckinDialog_clse()' data-dismiss=\"modal\">关闭</button>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>")
}

function ckinDialog_clse() {
    $("#ckinDialog").remove()
}