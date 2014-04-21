package com.seven.idouban.app.ui;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.common.util.AndroidUtil;
import com.common.util.Logs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.seven.idouban.R;
import com.seven.idouban.model.Auth;

public class AuthActivity extends BaseActivity {
	
	private LinearLayout mLayoutContent;
	
	private WebView mWebView;
	
	private Activity mContext;
	
	@Override
	protected int getContentViewRes() {
		return R.layout.activity_login;
	}

	@Override
	protected void findViews() {
		mLayoutContent = (LinearLayout) findViewById(R.id.layout_content);
		
	}

	@Override
	protected void initialize() {
		if (hasToken()) {
			goMain();
			return;
		}
		
		mWebView = new WebView(getApplicationContext());
		mLayoutContent.addView(mWebView);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSavePassword(false);
		
		mWebView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				mContext.setProgress(progress * 100);
			}
		});
		
		mWebView.setWebViewClient(new WebViewClient() {
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith(Auth.REDIRECT_URI)) {
					String paramString = url.substring(Auth.REDIRECT_URI.length());
					String[] array = paramString.split("=");
					if (array[0].equals("code")) {
						String code = array[1];
						Auth.saveCode(mContext, code);
						AndroidUtil.savePref(mContext, "code", code);
						getToken(code);
					}
				}
				Logs.v("shouldOverrideUrlLoading " + url);
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				Logs.i("onpagestart " + url);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}
			
		});
		
		String codeUrl = Auth.getCodeUrl();
		mWebView.loadUrl(codeUrl);
	
	}
	
	@Override
	protected void requestFeatures(Window window) {
		super.requestFeatures(window);
		window.requestFeature(Window.FEATURE_PROGRESS);
		setProgressBarVisibility(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_PROGRESS);
		setProgressBarVisibility(true);
		mContext = this;
		findViews();
		initialize();
	}
	
	private boolean hasToken() {
		String accessToken = Auth.getAccessToken(mContext);
		if (TextUtils.isEmpty(accessToken)) {
			return false;
		}
		return true;
	}
	
	private void getToken(String code) {
		String url = Auth.TOKEN_URL;
		AsyncHttpClient client = new AsyncHttpClient();
		Logs.e(url);
		RequestParams params = new RequestParams(Auth.getParams(code));
		
		client.post(url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onStart() {
				super.onStart();
				showProgress("正在获取access_token");
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				Logs.v(responseString);
				if (!TextUtils.isEmpty(responseString)) {
					try {
						JSONObject object = new JSONObject(responseString);
						String access_token = object.getString("access_token");
						String refresh_token = object.getString("refresh_token");
						int expires_in = object.getInt("expires_in");
						String douban_user_id = object.getString("douban_user_id");
						Auth.saveAccessToken(mContext, access_token);
						Auth.saveRefreshToken(mContext, refresh_token);
						Auth.saveUserId(mContext, douban_user_id);
						goMain();
					} catch (JSONException e) {
						toast("解析失败！");
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				toast("获取access_token失败！");
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				hideProgress();
			}
		});
	}
	
	private void goMain() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	 @Override
		public void onBackPressed() {
			if (mWebView.canGoBack()) {
				mWebView.goBack();
				return;
			}
			
			super.onBackPressed();
		}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != mWebView) {
			mWebView.destroy();
			mWebView = null;
		}
	}
}
