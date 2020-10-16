package com.l2jong.se.util;

import com.l2jong.se.vo.UsrVO;

public class ComnUtil {

	public static String nvl(Object obj, String s) {
		if (obj == null) {
			return s;
		} else {
			try {
				return (String) obj;
			} catch (Exception e) {
				return s;
			}
		}
	}

	public static boolean isEmptyOrNull(String str) {
		if (str == null) {
			return true;
		}
		return "".equals(str);
	}

	public static boolean isNull(Object sessionUser) {
		return sessionUser == null;
	}
}
