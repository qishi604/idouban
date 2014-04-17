package com.seven.idouban.service;

import com.common.util.Logs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.seven.idouban.util.Constants;

public class BaseService {

	protected static final String BASE_URL = Constants.BASE_URL;
	
	private static AsyncHttpClient HTTPCLIENT = new AsyncHttpClient();
	
	protected String getModule() {
		return "";
	}
	
	protected String getUrl(String relativeUrl) {
		return BASE_URL + getModule() + relativeUrl;
	}
	
	public RequestHandle get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
		Logs.i(url);
		return HTTPCLIENT.get(url, params, handler);
	}
	
	public RequestHandle post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
		return HTTPCLIENT.post(url, params, handler);
	}
	
	public RequestHandle put(String url, RequestParams params, AsyncHttpResponseHandler handler) {
		return HTTPCLIENT.put(url, params, handler);
	}
	
	public RequestHandle delete(String url, AsyncHttpResponseHandler handler) {
		return HTTPCLIENT.delete(url, handler);
	}
	
}
