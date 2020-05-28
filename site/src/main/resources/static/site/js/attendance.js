//获取当前时间
let date;
//格式化为本地时间格式
let date1;
//获取时间div
let div1;
//用户名
let loginUser;
//打卡时间
let signTime;
//日历
let myCalendar
//日历设置
let options={
    width: '29%',
    height: '100%',
    mark: {
        '2020-3-02': '采购'
    },
    onSelect: () => {writeContent()},
};
//备忘录编辑
let isEdit = false;
//备忘录标题
let r_title;
//备忘录内容
let r_content;
//选择时间
let select_time;
//备忘录表
let addList={};
//任务条目
let items;
//页面计数
let counter;
//页面总数
let pages;
//当前页
let currentPage;


$(function () {
    currentPage=document.getElementById("currentPage");
    counter=1;
    getMemorandum();
    mission_get(1);
    signState();
})

//获取备忘录标题和日期
function getMemorandum() {
    $.ajax({
        type:"POST",
        data:{
        },
        url: '/site/server/memorandum_search',
        async:true,
        success:function (data) {
            if(data.code=="10000"){
                let con = data.content;
                let info;
                for (let i in con) {

                    let time = con[i].belongDate.substring(0,10);
                    let posion=time.indexOf("-0")+1;
                    for (;posion;){
                        time=changeStr(time,posion);
                        posion=time.indexOf("-0")+1;
                    }
                    if (con[i].title!="" || con[i].content!=""){
                        if (con[i].title!=""){
                            info=con[i].title;
                        }else {
                            info=con[i].content.substring(0,5);
                        }
                    }else {
                        info=con[i].title;
                    }
                    addList[time]=con[i];
                    options.mark[time]=info;
                }

                myCalendar = new Simple_calendar('#container',options);
            }
        }
    });
}

//str:原始字符串，index,开始位置,changeStr，改变后的字
function changeStr(str,index){
    return str.substr(0, index) + str.substr(index+1,index + changeStr.length);
}

//写入备忘录面板
function writeContent() {
    select_time=myCalendar.getSelectedDay();
    document.getElementById("time").innerText=select_time.getFullYear()+"-"+(select_time.getMonth()+1)+"-"+select_time.getDate();
    let t = select_time.getFullYear()+"-"+(select_time.getMonth()+1)+"-"+select_time.getDate();
    document.getElementById("record_title").value =document.getElementById("record_title_p").innerText=addList[t].title;
    document.getElementById("record_content").innerText = addList[t].content;
}

//得到时间并写入div
function getDate(){
    //获取当前时间
    date = new Date();
    //格式化为本地时间格式
    date1 = date.toLocaleString();
    //获取div
    div1 = document.getElementById("sstime");
    //将时间写入div
    div1.innerHTML = date1;
}
//使用定时器每秒向div写入当前时间
setInterval("getDate()",1000);

function ckinOpen() {
    $.ajax({
        type:"POST",
        data:{
        },
        url: '/site/server/sign',
        async:true,
        success:function (data) {
            if(data.code=="10000"){
                alert(data.message);
                signState();
            }
        }
    });
}

function signState() {
    $.ajax({
        type:"POST",
        data:{
        },
        url: '/site/server/signState',
        async:true,
        success:function (data) {
            if(data.code=="10000"){
                if (data.msg!=null){
                    document.getElementById("ckinP1").innerHTML=data.msg;
                }else {
                    signTime=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.signTime_first));
                    if (data.signTime_last!=null){
                        let signTime_L=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(data.signTime_last));
                        document.getElementById("ckinP1").innerHTML="第一次打卡时间："+signTime;
                        document.getElementById("ckinP2").innerHTML="最后打卡时间："+signTime_L;
                    }else {
                        document.getElementById("ckinP1").innerHTML="第一次打卡时间："+signTime;
                    }
                }

            }
        }
    });
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

//备忘录编辑或保存
function Edit_or_Save(title,content,btn){
    if (isEdit==false){
        //变为可编辑状态
        btn.innerHTML="保存";
        document.getElementById("record_title_p").style.display="none";
        title.style.display="block";
        content.disabled=false;
        isEdit=true;
    }else {
        //变为不可编辑状态
        btn.innerHTML="编辑";
        document.getElementById("record_title_p").innerText=title.value;
        document.getElementById("record_title_p").style.display="block";
        title.style.display="none";
        content.disabled=true;
        r_title=title.value;
        r_content=content.value;
        select_time=myCalendar.getSelectedDay();
        $.ajax({
            type:"POST",
            data:{
                title:r_title,
                content:r_content,
                belongDate:select_time,
            },
            url: '/site/server/memorandum_save',
            async:true,
            success:function (data) {
                if (data.code=="10000"){
                    //日历标记
                    let strTime=select_time.getFullYear()+"-"+(select_time.getMonth()+1)+"-"+select_time.getDate();
                    if(r_title!="" || r_content!=""){
                        if (r_title!=""){
                            myCalendar.addMark(strTime, r_title);
                        }else {
                            let str=r_content.substring(0,5)
                            myCalendar.addMark(strTime, str);
                        }
                    }else {
                        myCalendar.addMark(strTime, r_title);
                    }

                }else{
                    alert("保存失败！");
                }
            }
        });
        isEdit=false;
    }

}

function mission_get(pageNo) {
    //移除旧条目
    var obj = document.getElementsByName("it");
    var l=obj.length;
    for(var i = 0;i<l;i++){
        obj[0].remove();//remove为jQuery方法。
    }
    $.ajax({
        type:"POST",
        data:{
            pageNo:pageNo,
        },
        url: '/site/server/notice_get',
        async:true,
        success:function (data) {
            pages=data.totalPages;
            let content;
            let createT;
            items=data.content;
            for (var i=0;i<data.content.length;i++){
                content=items[i];
                createT=dateFtt("yyyy-MM-dd HH:mm:ss",new Date(content.createTime));
                $("#mission_item").append("<tbody name=\"it\">" +
                    "            <tr style='height: 40px;'>" +
                    "                <td>"+content.accountnumber_name+"</td>" +
                    "                <td>"+createT+"</td>" +
                    "                <td style='display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp: 1;overflow: hidden;'>"+content.missionContent+"</td>" +
                    "                <td class=\"text-center\">" +
                    "                    <a href=\"javascript:void(0)\" data-id="+i+" onclick=\"showHC(this)\"><span class=\"glyphicon glyphicon-th\"></span></a>" +
                    "                </td>" +
                    "            </tr>" +
                    "        </tbody>");
            }
        }
    });
}

function goPageHC(state) {
    if (state=="qian"){
        if (counter==1){
            return;
        }else {
            counter--;
            currentPage.innerHTML=counter;
            mission_get(counter);
        }
    }else if (state=="hou"){
        if (counter==pages){
            return;
        }else {
            counter++;
            currentPage.innerHTML=counter;
            mission_get(counter);
        }
    }

}

function showHC(item_id) {
    var id=parseInt(item_id.dataset.id);
    var content=items[id];
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
        "创建人：" +content.accountnumber_name+"\n" +
        "创建时间：" +content.createTime+"\n" +
        "内容：" +content.missionContent+"\n"+
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
