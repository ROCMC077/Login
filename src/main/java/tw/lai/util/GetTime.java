package tw.lai.util;

import java.text.SimpleDateFormat;

public class GetTime {

	/**
	 * 取得當前電腦時間(發文...)
	 * 
	 * @param time
	 * @return
	 */
	public static Long getTime() {
		long time = System.currentTimeMillis();
		return time;

	}
	
	
	public static Long getLongFromString(String date) {

		// 字串轉毫秒
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(date).getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
}

	

