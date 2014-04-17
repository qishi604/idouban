package com.seven.idouban.service;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

public final class MTService extends BaseService {
	
	private static final String URL_SEARCH = "/search";
	
	private static final String URL_SUBJECT = "/subject/";
	
	private static final String URL_TOP250 = "/top250";
	
	private MTService() {}
	
	private static MTService SERVICE = new MTService();
	
	public static MTService getInstance() {
		return SERVICE;
	}
	
	@Override
	protected String getModule() {
		return "movie";
	}
	
	
	/**
	 * 
	 * @param id
	 * @param handler SubjectS
	 * @return
	 */
	public RequestHandle getMT(String id, AsyncHttpResponseHandler handler) {
		String url = getUrl(URL_SUBJECT + id);
		
		
		return get(url, null, handler);
	}
	
	/**
	 * 
	 * @param params q, start, count...
	 * @param handler SubjectsResponse
	 * @return
	 */
	public RequestHandle searchMT(RequestParams params, AsyncHttpResponseHandler handler) {
		String url = getUrl(URL_SEARCH);
		return get(url, params, handler);
	}
	
	/**
	 * 
	 * @param start
	 * @param count
	 * @param handler SubjectsResponse
	 * @return
	 */
	public RequestHandle getTop250(int start, int count, AsyncHttpResponseHandler handler) {
		String url = getUrl(URL_TOP250);
		RequestParams params = new RequestParams();
		params.put("start", start);
		params.put("count", count);
		return get(url, params, handler);
	}
}
