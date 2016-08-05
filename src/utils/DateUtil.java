package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtil {

	/**
	 * 设置date为某一天的开始，零点
	 * @param date
	 * @return date
	 */
    public static Date toDayStart(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 设置date为某一天的最后一毫秒前
     * @param date
     * @return Date
     */
    public static Date toDayEnd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmp = null;
        try {
            tmp = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return null;
        }
        return new Date(tmp.getTime() + 24 * 60 * 60 * 1000 - 1);
    }

    
    /**
     * 格式化时间到天
     * @param date
     * @return 字符表示
     */
    public static String fmtDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    /**
     * 格式化时间到秒
     * @param date
     * @return 字符表示
     */
    public static String fmtDateToSecond(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    
    /**
     * 格式化时间到毫秒
     * @param date
     * @return 字符表示
     */
    public static String fmtDateToMillisecond(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSS");
        return sdf.format(date);
    }
    
    /**
     * 根据所给日期返回两日期相差的秒数
     * @param d1
     * @param d2
     * @return 返回两个日期间隔的毫秒数
     */
    public static long getSecond(Date d1,Date d2)
    {
        long a1 = d1.getTime();
        long a2 = d2.getTime();
        long a3 = (a1 - a2)/1000;

        return a3;
    }

    /**
     * 根据秒数,格式化以**时**分**秒字符串形式返回
     * @return
     */
    public static String getBeapartDate(long m)
    {
        String beapartdate="";
        int nDay = (int)m/(24*60*60);
        int nHour = (int)(m-nDay*24*60*60)/(60*60);
        int nMinute = (int)(m-nDay*24*60*60-nHour*60*60)/60;
		int nSecond = (int)m-nDay*24*60*60-nHour*60*60-nMinute*60;
        if(nDay>=0) {
            beapartdate= nDay +"天";
        }
        if(nHour>=0) {
            beapartdate= beapartdate+nHour+"小时";
        }
        if(nMinute>=0) {
            beapartdate= beapartdate + nMinute+"分";
        }
        if(nSecond>=0)
        	beapartdate= beapartdate + nSecond+"秒";
        
        return beapartdate;
    }

    /**
     * 根据开始时间和结束时间获取时间差，以字符串形式返回
     * @param begin
     * @param end
     * @return
     */
    public static String getDateDeviation(Date begin,Date end) {
        if(begin==null || end==null) {
            return "输入时间有误";
        }
        return getBeapartDate(getSecond(end,begin));
    }
    
    //测试
    @Test
    public void testDate() {
    	
    	Date now = new Date();
    	String str = fmtDateToSecond(now);
    	System.out.println(str);
    }
}















