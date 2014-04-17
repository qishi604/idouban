package com.seven.idouban.app.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * @author seven
 * @date 2013-10-25
 * @version V1.0
 * @description 
 *
 */
public abstract class BaseActivity extends ActionBarActivity {
	
	protected ActionBar mActionBar;
	
	/**
	 * Find Views in this method
	 */
	protected abstract int getContentViewRes();
	/**
	 * Find Views in this method
	 */
	protected abstract void findViews();
	
	/**
	 * Initialize views or data in this method
	 */
	protected abstract void initialize();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getSupportActionBar();
		int res = getContentViewRes();
		if (0 != res) {
			setContentView(res);
		}
		findViews();
		initialize();
	}

}
