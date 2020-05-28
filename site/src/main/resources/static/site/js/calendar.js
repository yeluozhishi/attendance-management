$(function () {
    var initialLocaleCode = 'zh-cn';
    let d=new Date();
    let loginUser = document.getElementById("loginUser")

    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay,listMonth'
        },

        displayEventEnd: true,
        eventTextColor:'#000000',
        defaultDate: d,
        locale: initialLocaleCode,
        buttonIcons: false, // show the prev/next text
        weekNumbers: true,
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events

        events: function(start, end, timezone, callback) {

        $.ajax({
            type:"POST",
            url:'/site/server/SearchSignItem',
            cache:false,
            async:true,
            data:{
                start:start._d,
                end:end._d,
            },
            success:function(doc) {
                // eval("var j=" + doc);
                var events = [];
                var info = doc.content;
                for (var i = 0; i < info.length; i++) {
                    var ev = info[i];
                    var title;
                    var evtstart = new Date(Date.parse(ev.signinTime));
                    var evtend = new Date(Date.parse(ev.signoutTime));
                    var discription;
                    var bgc;
                    switch (ev.exception) {
                        case "1":
                            title="正常";
                            discription = "加班时长："+ev.extraduration;
                            bgc='#ffffff';
                            break;
                        case "2":
                            title="异常";
                            discription = ev.exceptionDetail;
                            bgc='#ffff00';
                            break;

                        default:
                            break;
                    }
                    events.push({
                        title:title,
                        start:evtstart,
                        end:evtend,
                        content:discription,
                        color:bgc,
                    });
                }
                callback(events);
            },
            error:function() {
            }
        })
        },

        eventRender: function (event, element) {
            element.find('.fc-list-item-title a').append("<br/>" + event.content);
        }
    });

    // build the locale selector's options
    $.each($.fullCalendar.locales, function(localeCode) {
        $('#locale-selector').append(
            $('<option/>')
                .attr('value', localeCode)
                .prop('selected', localeCode == initialLocaleCode)
                .text(localeCode)
        );
    });

    // when the selected option changes, dynamically change the calendar option
    $('#locale-selector').on('change', function() {
        if (this.value) {
            $('#calendar').fullCalendar('option', 'locale', this.value);
        }
    });
});


