package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	/*
	 * @para formatter yyyyMMddHHmmssSSS 年-毫秒
	 */
	public static String getTimeByFormatter(String formatter) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
}
