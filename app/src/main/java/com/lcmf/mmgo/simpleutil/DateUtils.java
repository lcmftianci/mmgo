package com.lcmf.mmgo.simpleutil;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static long getCurentTimes(){
        Calendar c = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String suffix = sdf.format(new Date());
        c.setTime(new Date());
        long sec=c.getTimeInMillis();
        return sec;
    }
}
