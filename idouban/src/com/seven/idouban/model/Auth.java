package com.seven.idouban.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import android.content.Context;

import com.common.util.AndroidUtil;

public class Auth {
	
	public static final String TOKEN_URL = "https://www.douban.com/service/auth2/token";
	
	private static final String URL = "https://www.douban.com/service/auth2/auth?";
	
	private static final String CLIENT_ID = "09fa8d84bb5389c71defa88e31fa9d55";
	
	private static final String CLIENT_SECRET = "2344456bbbbddf96";
	
	public static final String REDIRECT_URI = "http://movie.seven.com/back";
	
	private static final String RESPONSE_TYPE = "code";
	
	private static final String GRANT_TYPE_CODE = "authorization_code";
	
	private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

	public static String getCodeUrl() {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("client_id", CLIENT_ID);
		params.put("redirect_uri", REDIRECT_URI);
		params.put("response_type", RESPONSE_TYPE);
		StringBuilder sb = new StringBuilder(URL);
		Set<Entry<String, String>> sets = params.entrySet();
		boolean isFirst = true;
		for (Entry<String, String> entry : sets) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append("&");
			}
			sb.append(entry.getKey())
			.append("=")
			.append(entry.getValue());
		}
		return sb.toString();
	}
	
	public static Map<String, String> getParams(String code) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("client_id", CLIENT_ID);
		params.put("redirect_uri", REDIRECT_URI);
		params.put("client_secret", CLIENT_SECRET);
		params.put("grant_type", GRANT_TYPE_CODE);
		params.put("code", code);
		return params;
	}
	
	public static String getAccesTokenUrl(String code) {
		String url = "https://www.douban.com/service/auth2/token?";
		TreeMap<String, String> params = new TreeMap<>();
		params.put("client_id", CLIENT_ID);
		params.put("redirect_uri", REDIRECT_URI);
		params.put("client_secret", CLIENT_SECRET);
		params.put("grant_type", GRANT_TYPE_CODE);
		params.put("code", code);
		StringBuilder sb = new StringBuilder(url);
		Set<Entry<String, String>> sets = params.entrySet();
		boolean isFirst = true;
		for (Entry<String, String> entry : sets) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append("&");
			}
			sb.append(entry.getKey())
			.append("=")
			.append(entry.getValue());
			
		}
		return sb.toString();
	}
	
	public static String getCode(Context context) {
		return AndroidUtil.getStringPref(context, "code");
	}
	
	public static void saveCode(Context context, String code) {
		AndroidUtil.savePref(context, "code", code);
	}
	
	public static String getAccessToken(Context context) {
		return AndroidUtil.getStringPref(context, "access_token");
	}
	
	public static void saveAccessToken(Context context, String access_token) {
		AndroidUtil.savePref(context, "access_token", access_token);
	}
	
	public static String getRefreshToken(Context context) {
		return AndroidUtil.getStringPref(context, "refresh_token");
	}
	
	public static void saveRefreshToken(Context context, String refresh_token) {
		AndroidUtil.savePref(context, "refresh_token", refresh_token);
	}
	
	public static String getUserId(Context context) {
		return AndroidUtil.getStringPref(context, "douban_user_id");
	}
	
	public static void saveUserId(Context context, String douban_user_id) {
		AndroidUtil.savePref(context, "douban_user_id", douban_user_id);
	}
}
