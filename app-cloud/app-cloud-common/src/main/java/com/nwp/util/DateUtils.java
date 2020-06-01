package com.nwp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 *
 */
public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	public static final String FORMAT_YYYYMM = "yyyy_MM";
	public static String[] format = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日 HH:mm:ss", "yyyy/MM/dd HH:mm:ss",
			"yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy年MM月dd日 HH:mm", "yyyy-MM-dd HH", "yyyy/MM/dd HH",
			"yyyy年MM月dd日 HH", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy年MM月dd日", "yyyy-MM", "yyyyMMdd", "yyyyMM", "yyyy年-第",
			"yyyy", "yyyy年MM月" };

	public static Date getDate(String date) {
		Date d = null;
		for (int i = 0; i < format.length; i++) {
			SimpleDateFormat sdf = new SimpleDateFormat(format[i]);
			try {
				d = sdf.parse(date);
			} catch (ParseException e) {
				continue;
			}
			break;
		}
		return d;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getDate() {
		return dateFormat(new Date());
	}

	/**
	 * 日期月份相加或者相减
	 * 
	 * @param i        几个月
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String dateFormat(String dateTime, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			logger.error("异常:" + e.getMessage(), e);
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, i);
		date = cl.getTime();
		return sdf.format(date);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return format
	 */
	public static String dateFormatByOwn(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 判断是否为日期格式
	 * 
	 * @param str
	 * @param status 0.年月 1.年月日
	 * @return
	 */
	public static boolean isValidDate(String str, int status) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
		String date = "yyyy-MM";
		if (1 == status) {
			date = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(date);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			logger.error("异常:" + e.getMessage(), e);
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 获取两个日期中的日期列表
	 * @param dateS
	 * @param dateE
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDateDays(String dateS, String dateE) throws ParseException {
		List<String> list = new ArrayList<String>();
		Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateS);// 定义起始日期
		Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateE);// 定义结束日期
		Calendar dd = Calendar.getInstance();// 定义日期实例
		dd.setTime(d1);// 设置日期起始时间
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sdf.format(dd.getTime());
			list.add(str);
			dd.add(Calendar.DAY_OF_MONTH, 1);// 进行当前日期月份加1
		}
		list.add(dateE);
		return list;

	}

	/**
	 * 方法说明：获取两个日期月份之间的间隔，返回列表 . <BR>
	 * 
	 * @param dateS
	 * @param dateE
	 * @return 格式:'2017-01'
	 * @return: List<String>
	 * @Author: Pangchengyong <BR>
	 */
	public static List<String> getDateMonthList(String dateS, String dateE) throws Exception {
		List<String> list = new ArrayList<String>();
		Date d1 = new SimpleDateFormat("yyyy-MM").parse(dateS);// 定义起始日期
		Date d2 = new SimpleDateFormat("yyyy-MM").parse(dateE);// 定义结束日期
		Calendar dd = Calendar.getInstance();// 定义日期实例
		dd.setTime(d1);// 设置日期起始时间
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String str = sdf.format(dd.getTime());
			list.add(str);
			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
		}
		// list.add(dateE);
		return list;
	}

	/**
	 * 方法说明：获取两个日期月份之间的间隔，返回列表 . <BR>
	 * 
	 * @param dateS
	 * @param dateE
	 * @return 格式:'2017-01'
	 * @return: List<String>
	 * @Author: Pangchengyong <BR>
	 */
	public static List<String> getDateMonthList(String dateS, String dateE, String format) throws Exception {
		List<String> list = new ArrayList<String>();
		Date d1 = new SimpleDateFormat(format).parse(dateS);// 定义起始日期
		Date d2 = new SimpleDateFormat(format).parse(dateE);// 定义结束日期
		Calendar dd = Calendar.getInstance();// 定义日期实例
		dd.setTime(d1);// 设置日期起始时间
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String str = sdf.format(dd.getTime());
			list.add(str);
			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
		}
		list.add(dateE);
		return list;
	}

	/**
	 * 方法说明：获取两个日期年份之间的间隔，返回列表 . <BR>
	 * 
	 * @param dateS
	 * @param dateE
	 * @return 格式:'2017'
	 * @return: List<String>
	 * @Author: Pangchengyong <BR>
	 */
	public static List<String> getDateYearList(String dateS, String dateE) throws Exception {
		List<String> list = new ArrayList<String>();
		Date d1 = new SimpleDateFormat("yyyy").parse(dateS);// 定义起始日期
		Date d2 = new SimpleDateFormat("yyyy").parse(dateE);// 定义结束日期
		Calendar dd = Calendar.getInstance();// 定义日期实例
		dd.setTime(d1);// 设置日期起始时间
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String str = sdf.format(dd.getTime());
			list.add(str);
			dd.add(Calendar.YEAR, 1);// 进行当前日期年份加1
		}
		list.add(dateE);
		return list;
	}

	/**
	 * 方法说明：获取两个日期季度之间的间隔，返回列表 . 最多查询30年 <BR>
	 * 
	 * @param dateS
	 * @param dateE
	 * @return 格式:'20171'
	 * @return: List<String>
	 * @Author: Pangchengyong <BR>
	 */
	public static List<String> getDateQuarterList(String dateS, String dateE) {
		List<String> list = new ArrayList<String>();
		int Y = Integer.parseInt(dateS.substring(0, 4)), Q = Integer.parseInt(dateS.substring(4));
		for (int i = 0; i < 360; i++) {
			list.add(Y + "" + Q);
			Q++;
			if (Y == Integer.valueOf(dateE.substring(0, 4)) && Q > Integer.parseInt(dateE.substring(4))) {
				break;
			}
			if (Q > 4) {
				Q = 1;
				Y++;
			}
		}
		return list;
	}

	/***
	 * 日期年份加几年
	 * 
	 * @param datetime 日期(2017) + 1
	 * @return 2018
	 */
	public static String yearAdd(String datetime, int i, String sdf) {
		SimpleDateFormat sdfs = new SimpleDateFormat(sdf);
		Date date = null;
		try {
			date = sdfs.parse(datetime);
		} catch (ParseException e) {
			logger.error("异常:" + e.getMessage(), e);
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.YEAR, i);
		date = cl.getTime();
		return sdfs.format(date);
	}

	/***
	 * 日期月份加几个月
	 * 
	 * @param datetime sdf:格式 日期(2017-01) + 1
	 * @return 2017-02
	 */
	public static String monthAdd(String datetime, int i, String sdf) {
		return monthAdd(datetime, "yyyy-MM-dd HH:mm:ss", i, sdf);
	}

	/***
	 * 日期月份加几个月
	 * 
	 * @param datetime sdf:格式 日期(2017-01) + 1
	 * @return 2017-02
	 */
	public static String monthAdd(String datetime, String format, int i, String sdf) {
		SimpleDateFormat sdfs = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdfs.parse(datetime);
		} catch (ParseException e) {
			logger.error("异常:" + e.getMessage(), e);
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, i);
		date = cl.getTime();
		return dateFormatByOwn(date, sdf);
	}

	/**
	 * 获取当年当前时间之前每月最后一天
	 * 
	 * @return
	 */
	public static List<String> getLastDayList() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateDay = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateMonth = new SimpleDateFormat("MM");
		List<String> slist = new ArrayList<String>();
		String year = dateYear.format(new Date());
		int month = Integer.valueOf(dateMonth.format(new Date())).intValue();
//		int month = 13;
		for (int i = 1; i < month; i++) {
			if (i < 10) {
				slist.add(year + "0" + i + "01");
			} else {
				slist.add(year + i + "01");
			}
		}
		// 定义当前期间的1号的date对象
		List<String> lastDayList = new ArrayList<String>();
		try {
			for (String string : slist) {
				Date parse = dateDay.parse(string);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(parse);
				calendar.add(Calendar.MONTH, 1);// 月增加1天
				calendar.add(Calendar.DAY_OF_MONTH, -1);// 日期倒数一日,既得到本月最后一天
				Date voucherDate = calendar.getTime();
				String lastDay = dateFormat.format(voucherDate).toString();
				lastDayList.add(lastDay);
			}
		} catch (ParseException e) {
			logger.error("异常:" + e.getMessage(), e);
		}

		return lastDayList;
	}

	/**
	 * 获取当前月份的第一天和最后一天
	 * 
	 * @return
	 */
	public static String getLastDay(int status) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfMonth = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();
		if (status == 0) {
			return dateFormat.format(firstDayOfMonth);
		}
		return dateFormat.format(lastDayOfMonth);
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 获取传入月份的所有天数
	 * 
	 * @param strTime 格式 2018-01
	 * @return List
	 */
	public static List<String> getMonthFullDay(String strTime) {
		List<String> fullDayList = new ArrayList<String>();
		String[] split = strTime.split("-");
		int year = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]);
		int day = 01;
		Calendar cal = Calendar.getInstance();// 获得当前日期对象
		cal.clear();// 清除信息
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// 1月从0开始
		cal.set(Calendar.DAY_OF_MONTH, day);// 设置为1号,当前日期既为本月第一天
		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int j = 0; j <= (count - 1);) {
			if (sdf.format(cal.getTime()).equals(getLastDay(year, month)))
				break;
			cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);
			j++;
			fullDayList.add(sdf.format(cal.getTime()));
		}
		return fullDayList;
	}

	/**
	 * 获取最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return sdf.format(cal.getTime());
	}

	/**
	 * 返回制定月份最后一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String getLastDay(String date, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date d = dateFormat.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return sdf.format(cal.getTime());
	}

	/**
	 * 返回制定月份最后一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String getLastDay1(String date, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		int ss = Integer.valueOf(date.indexOf("-") + 1);
		Integer sss = Integer.valueOf(date.substring(ss)) + 1;
		String month = sss < 10 ? "0" + sss : sss + "";
		date = date.substring(0, date.indexOf("-")) + "-" + month;
		Date d = dateFormat.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获取当前时间前三十天日期List
	 * 
	 * @return
	 */
	public static List<String> getThirtyDaysAgo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
//		String endDate = sdf.format(today);// 当前日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = calendar.getTime();
		String lastDay = sdf.format(lastDate);
//		String maxDateStr = "2017-02-08";
		String minDateStr = "";
		Calendar calc = Calendar.getInstance();
		// 存所有日期的list
		List<String> list = new ArrayList<String>();
		try {
			for (int i = 0; i < 30; i++) {
				calc.setTime(sdf.parse(lastDay));
				calc.add(Calendar.DATE, -i);
				Date minDate = calc.getTime();
				minDateStr = sdf.format(minDate);
				list.add(minDateStr);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return list;
	}

	/**
	 * 获取当前时间之前或者之后的时间
	 * 
	 * @param number 参数 int 正数往后推，负数往前推
	 * @return
	 */
	public static String getYesterdayMethod(int number) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, number);// 正数往后增加.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获取当前时间N天日期List
	 * 
	 * @return
	 */
	public static List<String> getDateListByDays(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
//		String endDate = sdf.format(today);// 当前日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, num);
		Date lastDate = calendar.getTime();
		String lastDay = sdf.format(lastDate);
//		String maxDateStr = "2017-02-08";
		String minDateStr = "";
		Calendar calc = Calendar.getInstance();
		// 存所有日期的list
		List<String> list = new ArrayList<String>();
		try {
			calc.setTime(sdf.parse(lastDay));
			minDateStr = sdf.format(calc.getTime());
			list.add(minDateStr);
			for (int i = 0; i < 29; i++) {
				calc.add(Calendar.DATE, 1);
				Date minDate = calc.getTime();
				minDateStr = sdf.format(minDate);
				list.add(minDateStr);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据传入的开始结束日期，获取其中所有的月份list
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getWithinMonthList(String startDate, String endDate) {

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
			d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		} catch (ParseException e) {
			logger.error("异常:" + e.getMessage(), e);
		}

		Calendar dd = Calendar.getInstance();// 定义日期实例

		dd.setTime(d1);// 设置日期起始时间
		List<String> list = new ArrayList<String>();
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(dd.getTime());
			list.add(str);
			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
		}
		return list;
	}

	/**
	 * 字符串日期格式的计算
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days)) + 1;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 * 
	 * @throws ParseException
	 * 
	 */
	public static int getDaysByYearMonth(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date parse = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取昨天
	 * 
	 * @param format
	 * @return
	 */
	public static String getYesterday(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return new SimpleDateFormat(format).format(time);
	}

	/**
	 * 获取指定时间的昨天
	 * 
	 * @param format
	 * @return
	 */
	public static String getYesterday(Object date, String format) {
		Calendar cal = Calendar.getInstance();
		if (date instanceof Date) {
			cal.setTime((Date) date);
		} else {
			cal.setTime(getDate(String.valueOf(date)));
		}
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return new SimpleDateFormat(format).format(time);
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getBetweenDays(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int days = (int) ((sdf.parse(date1).getTime() - sdf.parse(date2).getTime()) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 给出指定 yyyy - MM - dd，返回 MM 月的最后一天字符串
	 * 
	 * @param nextMothDay
	 * @return
	 */
	public static String getLastDay(String nextMothDay) {
		SimpleDateFormat dateDay = new SimpleDateFormat("yyyy-MM-dd");
		Date voucherDate = null;
		try {
			Date parse = dateDay.parse(nextMothDay);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);
			calendar.add(Calendar.MONTH, 1);// 月增加1天
			calendar.add(Calendar.DAY_OF_MONTH, -1);// 日期倒数一日,既得到本月最后一天
			voucherDate = calendar.getTime();
		} catch (Exception e) {
			logger.error("异常:" + e.getMessage(), e);
		}
		String lastDay = dateDay.format(voucherDate);
		return lastDay;
	}

	/**
	 * 获取上个月
	 * 
	 * @return
	 */
	public static String getPrevMonth() {
		SimpleDateFormat dateDay = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, -1);
		String prevMonth = dateDay.format(now.getTime());
		return prevMonth;
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param dateType
	 * @return
	 */
	public static Date parse(String date, String dateType) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateType);
			return sdf.parse(date);
		} catch (Exception e) {
			logger.error("异常:" + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 年份序列化
	 * 
	 * @param stm
	 * @param etm
	 * @param mpls
	 */
	public static void getYtm(String stm, String etm, List<Map<String, String>> mpls) {
		int stmi = Integer.parseInt(stm);
		int etmi = Integer.parseInt(etm);
		Calendar calNow = Calendar.getInstance();// 当前时间
		calNow.setTimeInMillis(System.currentTimeMillis());
		int yn = calNow.get(Calendar.YEAR);// 当前年份
		for (int i = stmi; i <= etmi; i++) {
			Map<String, String> mp = new HashMap<String, String>();
			mp.put("o", i + "");
			mp.put("a", i + "年");
			mp.put("now", yn + "");
			mpls.add(mp);
		}

	}

	/**
	 * 季度序列化
	 * 
	 * @param stm
	 * @param etm
	 * @param mpls
	 */
	public static void getStm(String stm, String etm, List<Map<String, String>> mpls) {
		/*
		 * String[] stms = stm.split("-"); String[] etms = etm.split("-"); if
		 * (stms.length != 2 || etms.length != 2) return;
		 */
		int stmi = Integer.parseInt(stm.substring(0, 4));
		int stmj = Integer.parseInt(stm.substring(4));
		int etmi = Integer.parseInt(etm.substring(0, 4));
		int etmj = Integer.parseInt(etm.substring(4));
		Calendar calNow = Calendar.getInstance();// 当前时间
		calNow.setTimeInMillis(System.currentTimeMillis());
		int yn = calNow.get(Calendar.YEAR);// 当前年份
		int mn = calNow.get(Calendar.MONTH);// 当前月份
		String szn = "";
		if (mn < 4)
			szn = "1";
		else if (mn > 3 && mn < 7)
			szn = "2";
		else if (mn > 6 && mn < 10)
			szn = "3";
		else if (mn > 9 && mn < 13)
			szn = "4";
		int i = stmi, j = stmj;
		while (true) {
			Map<String, String> mp = new HashMap<String, String>();
			String mth = "";
			String jstr = j < 10 ? ("" + j) : "" + j;
			if (j == 1)
				mth = i + "01," + i + "02," + i + "03";
			else if (j == 2)
				mth = i + "04," + i + "05," + i + "06";
			else if (j == 3)
				mth = i + "07," + i + "08," + i + "09";
			else if (j == 4)
				mth = i + "10," + i + "11," + i + "12";
			String mtha = "";
			if (j == 1)
				mtha = "01,02,03";
			else if (j == 2)
				mtha = "04,05,06";
			else if (j == 3)
				mtha = "07,08,09";
			else if (j == 4)
				mtha = "10,11,12";
			String mthb = "";
			if (j == 1)
				mthb = "03";
			else if (j == 2)
				mthb = "06";
			else if (j == 3)
				mthb = "09";
			else if (j == 4)
				mthb = "12";
			mp.put("now", yn + "" + szn);
			mp.put("o", i + "" + jstr);
			mp.put("a", i + "年" + j + "季度");
			mp.put("b", mth);
			mp.put("c", mtha);
			mp.put("d", mthb);
			mp.put("e", i + "");
			String mthg = "";// 环比时间序列
			String mthh = "";
			if (j == 1) {
				mthg = "10,11,12";
				mthh = (i - 1) + "";
			} else if (j == 2) {
				mthg = "01,02,03";
				mthh = i + "";
			} else if (j == 3) {
				mthg = "04,05,06";
				mthh = i + "";
			} else if (j == 4) {
				mthg = "07,08,09";
				mthh = i + "";
			}
			mp.put("g", mthg);
			mp.put("h", mthh);
			mpls.add(mp);
			if (i == etmi && j == etmj)
				break;
			if (j == 4) {
				j = 1;
				i++;
			} else
				j++;
		}
	}

	/**
	 * 月度序列化
	 * 
	 * @param stm
	 * @param etm
	 * @param mpls
	 */
	public static void getMtm(String stm, String etm, List<Map<String, String>> mpls) {
		int stmi = Integer.parseInt(stm.substring(0, 4));
		int stmj = Integer.parseInt(stm.substring(4));
		int etmi = Integer.parseInt(etm.substring(0, 4));
		int etmj = Integer.parseInt(etm.substring(4));
		Calendar calNow = Calendar.getInstance();// 当前时间
		calNow.setTimeInMillis(System.currentTimeMillis());
		int yn = calNow.get(Calendar.YEAR);// 当前年份
		int mn = calNow.get(Calendar.MONTH);// 当前月份
		String mnstr = mn < 10 ? ("0" + mn) : "" + mn;
		int i = stmi, j = stmj;
		while (true) {
			Map<String, String> mp = new HashMap<String, String>();
			String jstr = j < 10 ? ("0" + j) : "" + j;
			mp.put("o", i + "" + jstr);
			mp.put("a", i + "年" + jstr + "月");
			mp.put("b", i + "");
			mp.put("c", jstr + "");
			mp.put("now", yn + "" + mnstr);
			mpls.add(mp);
			if (i == etmi && j == etmj)
				break;
			if (j == 12) {
				j = 1;
				i++;
			} else
				j++;
		}
	}

}
