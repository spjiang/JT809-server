package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间处理工具类
 *
 * @author 凉意 2018年4月22日 下午3:04:07
 * @version 1.0
 */
public class DateUtil {

	/**
	 * 固定格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 调测日志记录器
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	/**
	 *
	 * yyyyMMddHHmmss 格式 转为 yyyy-MM-dd HH:mm:ss
	 *
	 * @author zx 2018年12月5日 上午10:27:15
	 * @param date
	 * @return
	 */
	public static String getStringDate(String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime ldt = LocalDateTime.parse(date, dtf);
		DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String datetime2 = ldt.format(fa);
		return datetime2;
	}
	/**
	 * 时间字符串转换为Date类型，自定义格式
	 *
	 * @author 凉意 2018年4月22日 下午3:10:14
	 * @param dateStr
	 *            待转换的时间字符串
	 * @param pattern
	 *            格式定义
	 * @return
	 */
	public static Date parseToDate(String dateStr, String pattern) {
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			LOG.error("时间转换失败,dateStr=" + dateStr + ",pattern=" + pattern, e);
		}
		return null;
	}

	/**
	 * 间字符串转换为Date类型，固定格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @author 凉意 2018年4月22日 下午3:12:30
	 * @param dateStr
	 *            待转换的时间字符串
	 * @return
	 */
	public static Date parseToDate(String dateStr) {
		try {
			return DATE_TIME_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			LOG.error("时间转换失败,dateStr=" + dateStr + "。转换格式为：yyyy-MM-dd HH:mm:ss", e);
		}
		return null;
	}

	/**
	 * 时间格式化处理方法，自定义格式
	 *
	 * @author 凉意 2018年4月25日 下午4:32:44
	 * @param date
	 *            需要转换的时间
	 * @param pattern
	 *            自定格式
	 * @return
	 */
	public static String formatToString(Date date, String pattern) {
		if (null == date || StringUtils.isBlank(pattern)) {
			LOG.warn("时间格式化失败{参数为空}：date=" + date + ",pattern=" + pattern);
			return null;
		}
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}
}
