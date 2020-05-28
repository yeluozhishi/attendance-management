package com.whk.resourceserver.service;

import com.whk.resourceserver.entity.accountEntity;
import com.whk.resourceserver.entity.signDiaryEntity;
import com.whk.resourceserver.entity.signEntity;
import com.whk.resourceserver.repository.AccountEntityRepository;
import com.whk.resourceserver.repository.SignDiaryEntityRepository;
import com.whk.resourceserver.repository.SignEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Time;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Configuration      //主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 开启定时任务
public class SaticScheduleTask {
    boolean isworkday=true;//是否为工作日

    @Autowired
    private AccountEntityRepository accountEntityRepository;
    @Autowired
    private SignDiaryEntityRepository signDiaryEntityRepository;
    @Autowired
    private SignEntityRepository signEntityRepository;

//    添加定时任务
    @Scheduled(cron = "0 0 1 * * ?")
    //或直接指定时间间隔，例如：60秒
//    @Scheduled(fixedRate=60000)
    private void configureTasks() throws ParseException {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        festiavl();
        sign_deal();
    }

    /**
     * 计算清明节的日期(可计算范围: 1700-3100)
     * @param year 需要计算的年份
     * @return 清明节在公历中的日期
     */
    public static int qingming(int year) {
        if (year == 2232) {
            return 4;
        }
        if (year < 1700) {
            throw new RuntimeException("1700年以前暂时不支持");
        }
        if (year >= 3100) {
            throw new RuntimeException("3100年以后暂时不支持");
        }
        double[] coefficient = {5.15, 5.37, 5.59, 4.82, 5.02, 5.26, 5.48, 4.70, 4.92, 5.135, 5.36, 4.60, 4.81, 5.04,
                5.26};
        int mod = year % 100;
        return (int) (mod * 0.2422 + coefficient[year / 100 - 17] - mod / 4);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    //节日处理
    public void festiavl() throws ParseException {
        isworkday=true;
        Map<String,Integer> festival = new HashMap<>();
        festival.put("元旦",1);
        festival.put("春节",7);
        festival.put("清明节",3);
        festival.put("劳动节",5);
        festival.put("端午节",3);
        festival.put("国庆节",7);
        festival.put("中秋节",1);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH)+1;//获取月份
        int day=cal.get(Calendar.DATE);//获取日

        //处理清明节
        if (month==4){
            int q = qingming(year);
            if (day==q || day==q+1 || day==q+2){
                isworkday=false;
            }
        }

        if (isworkday){
            String s=year+"-"+month+"-"+day;
            SimpleCalendar.Element element=SimpleCalendar.getCalendarDetail(s,"yyyy-MM-dd");
            //验证今日
            if (festival.containsKey(element.solarFestival) || festival.containsKey(element.lunarFestival)){
                isworkday=false;
            }

            if (isworkday){
                for(int i=1;i<7;i++){
                    day-=1;
                    if (day<=0){
                        if (month==1){
                            year-=1;
                            month=12;
                        }else {
                            month-=1;
                        }
                        day+=getDaysByYearMonth(year,month);
                    }
                    s=year+"-"+month+"-"+day;
                    element=SimpleCalendar.getCalendarDetail(s,"yyyy-MM-dd");
                    if (festival.get(element.solarFestival)!=null){
                        if ((i+1)<=festival.get(element.solarFestival)){
                            isworkday=false;
                            break;
                        }
                    }else if (festival.get(element.lunarFestival)!=null){
                        if ((i+1)<=festival.get(element.lunarFestival)){
                            isworkday=false;
                            break;
                        }
                    }
                }
            }
        }
    }

    //签到处理任务
    public void sign_deal() {
        //昨日当前时间
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();

        //格式化时间yyyy-MM-dd 00:00:00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(time);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, pos);//昨日00：00：00

        //今日00：00：00
        Date today = new Date(strtodate.getTime()+24*60*60*1000L);

        //签到条目
        List<signDiaryEntity> sign_list=signDiaryEntityRepository.findByTime(strtodate,today);

        //账户条目
        Iterable<accountEntity> account_list=accountEntityRepository.findAll();
        Iterator it = account_list.iterator();

        ArrayList<Date> time_list;

        while (it.hasNext()) {
            accountEntity account = (accountEntity) it.next();
            time_list = new ArrayList<>();
            if (sign_list.size()!=0){
                for (signDiaryEntity signDiary : sign_list) {
                    if (account.getAccountnumber().equals(signDiary.getAccountnumber())) {
                        time_list.add(signDiary.getSignTime());
                    }
                }
            }else {
                time_list.add(strtodate);
            }
            time_list.sort(new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    if (o1 == null || o2 == null) return 0;

                    return o1.compareTo(o2);
                }
            });
            Date pre = time_list.get(0);
            Date last = time_list.get(time_list.size() - 1);

            //实际打卡时长
            Time work_time = new Time(last.getTime() - pre.getTime());

            signEntity signEntity = new signEntity();

            signEntity.setAccountnumber(account.getAccountnumber());
            signEntity.setSigninTime(pre);
            signEntity.setSignoutTime(last);

            //格林尼治时间1970-01-01 08:00:00,作为显示结果 +16小时(16*60*60*1000L)
            signEntity.setSignDuration(new Time(work_time.getTime()+16*60*60*1000L));
            //9小时工作时长

            if (isworkday){
                if (work_time.getTime() < 9*60*60*1000L) {
                    signEntity.setExtraduration(new Time(16*60*60*1000L));
                    signEntity.setException("2");
                    signEntity.setExceptionDetail("上班时长不够，还差" + (new Time(9*60*60*1000L - work_time.getTime()+16*60*60*1000L) ));
                } else if (work_time.getTime() == 9*60*60*1000L) {
                    signEntity.setExtraduration(new Time(16*60*60*1000L));
                    signEntity.setException("1");
                    signEntity.setExceptionDetail("无异常");
                } else if (work_time.getTime() > 9*60*60*1000L) {
                    signEntity.setExtraduration(new Time(work_time.getTime() - 9*60*60*1000L+16*60*60*1000L));
                    signEntity.setException("1");
                    signEntity.setExceptionDetail("无异常");
                }
            }else {
                signEntity.setExtraduration(new Time(work_time.getTime()+16*60*60*1000L));
                signEntity.setException("1");
                signEntity.setExceptionDetail("无异常");
            }

            signEntityRepository.save(signEntity);

        }

    }
}