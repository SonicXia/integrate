package com.atsonic.integrate.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * <p>
 * <b>Title：</b> 日期工具类
 * </p>
 * <p>
 * <b>gaogao：</b>
 * </p>
 * 
 */
public abstract class DateUtils {

	public final static String FORMAT_DATE_LONG = "yyyyMMddHHmmss";

	public final static String FORMAT_DATE_SHORT = "yyyyMMdd";

	public final static String FORMAT_DATE = "yyyy-MM-dd";
	public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	public final static String FORMAT_DATETIME_1 = "yyyy/MM/dd HH:mm:ss";
	
	public final static String FORMAT_DATETIME_CST = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public final static String FORMAT_DATE_SPRIT = "dd/MM/yyyy";
	public final static String FORMAT_DATETIME_SPRIT = "dd/MM/yyyy HH:mm:ss";

	public final static String FORMAT_DATE_ZH = "yyyy年MM月dd日";
	public final static String FORMAT_DATETIME_ZH = "yyyy年MM月dd日 HH时mm分ss秒";

	public final static String STARTTIME = " 00:00:00";
	public final static String ENDTIME = " 23:59:59";

	public final static String FORMAT_DATE_HOUR_MINUTE = "HH:mm";

	public final static String FORMAT_DATE_HOUR_MINUTE_SECOND = "HH:mm:ss";

	private static final String MIN_SQL_DATE = "1753-01-01 00:00:00";
	private static final String MAX_SQL_DATE = "9999-12-31 23:59:59";
    public static final String FORMAT_DATE_HM="yyyy-MM-dd HH:mm";
    private static final String ONE_SPACE = " ";
    
	// 上限时间，如7,0
	public static int[] UPPERTIME = {7,0};
	// 下限时间，如23,0
	public static int[] LOWERTIME = {23,0};


	public static String getFormatSeconds(long seconds) {
		String timeStr = seconds + "";
		if (seconds > 60) {
			long second = seconds % 60;
			long min = seconds / 60;
			String minStr = String.valueOf(min);
			String secondStr = String.valueOf(second);
			if (min < 10) {
				minStr = "0" + min;
			}
			if (second < 10) {
				secondStr = "0" + second;
			}
			timeStr = minStr + "分" + secondStr + "秒";
			if (min > 60) {
				min = (seconds / 60) % 60;
				long hour = (seconds / 60) / 60;
				String hourStr = String.valueOf(hour);
				String minStr2 = String.valueOf(min);
				if (hour < 10) {
					hourStr = "0" + hour;
				}
				if (min < 10) {
					minStr2 = "0" + min;
				}
				timeStr = hourStr + "时" + minStr2 + "分" + secondStr + "秒";
			}
		}
		return timeStr;
	}
	
	/**
	 * 用字符串获得日期
	 * 
	 * @throws ParseException
	 * @dateValue 日期字符串 format 格式化的类型
	 */
	public static Date getDate(String dateValue, String format){
		Date result =null;
		if (dateValue == null) {
			return null;
		}
		SimpleDateFormat sfdate = new SimpleDateFormat(format);
		try {
			result =	 sfdate.parse(dateValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断日期合法，并处于Sql Server支持的范围内
	 * @param date yyyy-mm-dd/yyyy-mm-dd hh:mm:ss
	 * @return
	 */
	public static boolean isValidDbDate(String dateStr) {
		boolean res = true;
		// 短日期补时间部分
		if(10 == dateStr.length()) {
			dateStr = dateStr + " 00:00:00";
		}
		Date minSqlSvrDate = DateUtils.getDate(MIN_SQL_DATE, DateUtils.FORMAT_DATETIME);
		Date maxSqlSvrDate = DateUtils.getDate(MAX_SQL_DATE, DateUtils.FORMAT_DATETIME);
		Date date = DateUtils.getDate(dateStr, DateUtils.FORMAT_DATETIME);
		if (date.before(minSqlSvrDate) || date.after(maxSqlSvrDate)) {
			res = false;
		}
		return res;
	}
	/**
	 * 将日期加上某些天或减去天数，返回字符串
	 * 
	 * @param date 待处理日期
	 * @param to 加减的天数
	 * @return 日期
	 */
	public static Date dateAdd(Date date, int to) {
		Calendar strDate = Calendar.getInstance();
		strDate.setTime(date);
		strDate.add(Calendar.DATE, to); // 日期减 如果不够减会将月变动
		return new Date(strDate.getTime().getTime());
	}

	/**
	 * 格式化日期
	 * 
	 * @param date 日期对象
	 * @param split 分隔字符
	 * @return
	 */
	public static String formatDate(Date date, char split) {
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyy" + split + "MM" + split + "dd");
		return sfdate.format(date);
	}

	/**
	 * 
	 * @param date 日期对象，可以是java.util.Date和Date
	 * @param format 日期格式
	 * @return
	 */
	public static String format(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat sfdate = new SimpleDateFormat(format);
		return sfdate.format(date);
	}

	/**
	 * 按指定的格式返回格式化的日期
	 * @param date　日期对象，可以是java.util.Date和Date
	 * @param format　日期格式
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat sfdate = new SimpleDateFormat(format);
		return sfdate.format(date);
	}

	/**
	 * 转化成年月日期
	 * 
	 * @param sDate 字符型日期：2009-02-02
	 * @param DelimeterChar 分割符号比如 / -
	 * @return 年月日期 :2009年02月02日
	 */
	public static String chDateChange(String sDate, String DelimeterChar) {
		String tmpArr[] = sDate.split(DelimeterChar);
		tmpArr[0] = tmpArr[0] + "年";
		tmpArr[1] = tmpArr[1] + "月";
		tmpArr[2] = tmpArr[2] + "日";
		return tmpArr[0] + tmpArr[1] + tmpArr[2];
	}

	/**
	 * 得到某天是周几
	 * 
	 * @param strDay 日期字符
	 * @param format 日期格式
	 * @return 周几
	 */
	public static int getWeekDay(String strDay, String format) throws ParseException {
		Date date = DateUtils.getDate(strDay, format);
		return getWeekDay(date);
	}

	/**
	 * 得到某天是周几
	 * 
	 * @param strDay
	 * @return 周几
	 */
	public static int getWeekDay(Date date) {
		Date day = DateUtils.dateAdd(date, -1);
		Calendar strDate = Calendar.getInstance();
		strDate.setTime(day);
		int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
		return meStrDate;
	}

	/**
	 * 取得两个日期段的日期间隔
	 * 
	 * @param t1 时间1
	 * @param t2 时间2
	 * @param format 时间格式
	 * @return t2 与t1的间隔天数
	 * @throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(String t1, String t2, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		int betweenDays = 0;
		Date d1 = df.parse(t1);
		Date d2 = df.parse(t2);
		betweenDays = getBetweenDays(d1, d2);
		return betweenDays;
	}

	/**
	 * 取得两个日期段的日期间隔
	 * 
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return t2 与t1的间隔天数
	 */
	public static int getBetweenDays(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return -1;
		}
		int betweenDays;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c2.setTime(d1);
			c1.setTime(d2);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}

	
	/**
	 * 取得两个日期段的日期间隔
	 * 
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return t2 与t1的间隔分钟
	 */
	public static int getBetweenMinutes(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return -1;
		}
		int betweenMinutes;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c2.setTime(d1);
			c1.setTime(d2);
		}
		int betweenHours = c2.get(Calendar.HOUR_OF_DAY) - c1.get(Calendar.HOUR_OF_DAY);
		betweenMinutes = c2.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
		for (int i = 0; i < betweenHours; i++) {
			betweenMinutes += (i+1)*60;
		}
		return betweenMinutes;
	}

	/**
	 * 比较两个时间差 毫秒
	 *
	 * @param before 前面一个时间
	 * @param after 后面一个时间
	 * @return 毫秒数
	 */
	public static long compareDateMs(Date before, Date after) {

		long result = 0;

		if (before != null && after != null) {
			long t1 = before.getTime();
			long t2 = after.getTime();

			result = Long.parseLong("" + (t2 - t1));	// 返回毫秒数
		}

		return result;
	}

	/**
	 * 比较两个时间差 秒
	 *
	 * @param before 前面一个时间
	 * @param after 后面一个时间
	 * @return 秒数
	 */
	public static int compareDateSs(Date before, Date after) {

		int result = 0;

		if (before != null && after != null) {
			long t1 = before.getTime();
			long t2 = after.getTime();

			result = Integer.parseInt("" + (t2 - t1)) / 1000;		// 返回秒数
		}

		return result;
	}

	/**
	 * 判断指定日期是否在一个日期范围内
	 * 
	 * @param fromDate 范围开始日期
	 * @param toDate 范围结束日期
	 * @param testDate 测试日期
	 * @return 在范围内true,否则false
	 */
	public static boolean betweenDays(Date fromDate, Date toDate, Date testDate) {
		if (fromDate == null || toDate == null || testDate == null) {
			return false;
		}

		// 1、 交换开始和结束日期
		if (fromDate.getTime() > toDate.getTime()) {
			Date tempDate = fromDate;
			fromDate = toDate;
			toDate = tempDate;
		}

		// 2、缩小范围
		long testDateTime = testDate.getTime();
		if (testDateTime > toDate.getTime() || testDateTime < fromDate.getTime()) {
			return false;
		}

		return true;
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param d1 日期一
	 * @param d2 日期二
	 * @return 同一天true，不是同一天false
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		boolean result = false;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
			result = true;
		}
		return result;
	}

	/**
	 * 是否为周末
	 * 
	 * @param strDate
	 * @return true|false
	 */
	public static boolean isWeekend(Date date) {
		int weekDay = getWeekDay(date);
		if (weekDay == 6 || weekDay == 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取年月，例如 201009
	 * 
	 * @param dateObj
	 * @return
	 */
	public static String getYearMonth(Date dateObj) {
		if (dateObj == null) {
			return "";
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(dateObj);
		int month = ca.get(Calendar.MONTH) + 1;
		String strMonth = month < 10 ? ("0" + month) : String.valueOf(month);
		String yearMonth = ca.get(Calendar.YEAR) + strMonth;
		return yearMonth;
	}

	/**
	 * 根据指定年月计算上月年月标示<br/>
	 * 
	 * @param yearMonth
	 * @return 201010返回201009，201001返回200912
	 */
	public static String getPreYearMonth(String yearMonth) {
		if (yearMonth.length() == 6) {
			int year = Integer.valueOf(yearMonth.substring(0, 4));
			int month = Integer.valueOf(yearMonth.substring(4));
			if (month != 1) {
				month -= 1;
			} else {
				year -= 1;
				month = 12;
			}
			return year + (month < 10 ? "0" + month : String.valueOf(month));
		}
		return "";
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String toGMT(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(date);
	}

	/**
	 * 将一个Date数据转换成GMT字符串，如果当前北京时间为2007-03-30T10:21:00.000，则格式成GMT后为2007-03-30
	 * T02:21:00.000+08:00
	 * @param i Date,北京时间，正8区
	 * @return GMT字符串
	 */
	public static Date toDate(String date, String format) {
		if (StringUtils.isEmpty(date))
			return null; // @I1A
		Date ret = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);// "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00"
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			ret = (Date) df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 获取当前年份
	 */
	public static Integer getCurrentYear() {
		Calendar ca = Calendar.getInstance();
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 */
	public static Integer getCurrentMonth() {
		Calendar ca = Calendar.getInstance();
		return ca.get(Calendar.MONTH) + 1;
	}

	/**
	 * 时间比较
	 */
	public static boolean compareDate(String dateFrom, String dateTo, String format) throws ParseException {
		Date dateFromD = getDate(dateFrom, format);
		Date dateToD = getDate(dateTo, format);
		if (null == dateFromD || null == dateToD) {
			return false;
		}
		if (dateFromD.getTime() <= dateToD.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据时间获取到X分钟前的时间
	 * @param date 时间
	 * @param minute 分钟 X
	 * @return X分钟前的时间 格式为12:00
	 * @throws Exception
	 */
	public static String getAheadOfIimeStr(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -minute);
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	/**
	 * 根据时间获取到X分钟前的时间
	 * @param date 时间
	 * @param minute 分钟 X
	 * @return X分钟前的时间 格式为12:00
	 * @throws Exception
	 */
	public static Date getAheadOfIime(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -minute);
		return cal.getTime();
	}

	/**
	 * 将long型转换成指定格式的日期字符串
	 * @param dateTime 日期数值
	 * @param pattern 指定格式
	 * @return  指定格式的日期字符串
	 */
	public static String convertToFormatString(long dateTime,String pattern){
		try{
			Date date=new Date(dateTime);
			return DateUtils.format(date, pattern);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
     * 判断指定时间是否在指定时间范围之内
     * 
     * @param startTime 开始时间(HH:mm)
     * @param endTime 结束时间(HH:mm)
     * @param dateTime 需要判断的时间(yyyy-MM-dd HH:mm:ss)
     * @param buffMinute 缓冲时间
     * @return true:在指定时间范围之内;false:不在指定时间范围之内
     * @throws MenuServiceException 例外对象
     */
    public static boolean isValidTime(String startTime, String endTime, String dateTime, int buffMinute) {
        try {
            String[] dateTimeArr = dateTime.split(ONE_SPACE);
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_DATE_HM);
            Date tempDatetime = sdf.parse(dateTime);
            Date tempStartTime = sdf.parse(dateTimeArr[0] + ONE_SPACE + startTime);
            Date tempEndTime = sdf.parse(dateTimeArr[0] + ONE_SPACE + endTime);
            tempEndTime = getAfterOfDateTime(tempEndTime, buffMinute);
            // Start>End 跨天的时段
            if (tempStartTime.compareTo(tempEndTime) > 0) {
                // dateTime>Start
                if (tempDatetime.compareTo(tempStartTime) >= 0) {
                    return true;
                }
                // dateTime<End
                else if (tempDatetime.compareTo(tempEndTime) <= 0) {
                    return true;
                } else {
                    return false;
                }
            }
            if (tempDatetime.compareTo(tempStartTime) >= 0 && tempEndTime.compareTo(tempDatetime) >= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static Date getAfterOfDateTime(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }
    
	public static String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}


	/**
	 * Date转换为字符串
	 *
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat(FORMAT_DATETIME).format(date);
	}

	/**
	 * 获取今天的指定字符串格式
	 * @param format 要格式化时间的格式
	 * @return
	 */
	public static String getDateStr(String format) {

		return new SimpleDateFormat(format).format(new Date());
	}


	/**
	 * 获取第二天的零点
	 *
	 * @return
	 */
	public static String getTomorrowZero() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = calendar.getTime();
		String tomorrowZero = new SimpleDateFormat(FORMAT_DATE + STARTTIME).format(tomorrow);
		return tomorrowZero;
	}

	/**
	 * 获取当天零点00:00:00
	 *
	 * @return
	 */
	public static Date getTodayZero() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取当天最后一刻23:59:59
	 *
	 * @return
	 */
	public static Date getTodayLastMoment() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取指定日期月份第一天
	 * @param format
	 * @return
	 */
	public static String getFirstDay(Date date,String format) {
		SimpleDateFormat sf=new SimpleDateFormat(format);
		GregorianCalendar gcLast= (GregorianCalendar)Calendar.getInstance();
		gcLast.setTime(date);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		return sf.format(gcLast.getTime());
	}

	/**
	 * 获取指定日期月份最后一天
	 * @param format
	 * @return
	 */
	public static String getLastDay(Date date,String format) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		SimpleDateFormat sf=new SimpleDateFormat(format);
		return sf.format(calendar.getTime());
	}

}
