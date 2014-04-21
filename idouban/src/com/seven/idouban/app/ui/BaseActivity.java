package com.seven.idouban.app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.Window;

import com.common.util.AndroidUtil;
import com.seven.idouban.R;
import com.seven.idouban.app.background.AppExitReceiver;

/**
 * @author seven
 * @date 2013-10-25
 * @version V1.0
 * @description 
 *
 */
public abstract class BaseActivity extends ActionBarActivity {
	
	protected ActionBar mActionBar;
	
	protected boolean showHome() {
        return false;
    }
	
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
	
	protected void requestFeatures(Window window) {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestFeatures(getWindow());
		registerAppExitReceiver();
		mActionBar = getSupportActionBar();
		if (showHome()) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
		
		int res = getContentViewRes();
		if (0 != res) {
			setContentView(res);
		}
		findViews();
		initialize();
	}
	
	protected void onBack() {
		finish();
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case android.R.id.home:
			onBack();
            return true;

		default:
			break;
		}
    	
        return super.onOptionsItemSelected(item);
    }
	
	protected void toast(String msg) {
		AndroidUtil.toastS(this, msg);
	}
	
	protected void toast(int msgRes) {
		AndroidUtil.toastS(this, msgRes);
	}
	
	private ProgressDialog mProgressDialog;
	protected void showProgress(String msg) {
		if (null == mProgressDialog) {
			mProgressDialog = AndroidUtil.showProgress(this, null, msg);
		} else {
			mProgressDialog.show();
		}
	}
	
	protected void hideProgress() {
		if (null != mProgressDialog) {
			mProgressDialog.dismiss();
		}
	}
	
	private AppExitReceiver mAppExitReceiver = new AppExitReceiver() {
		
		public void onAppExit() {
			finish();
		}
	};
	
	private void registerAppExitReceiver() {
		IntentFilter filter = new IntentFilter(AppExitReceiver.ACTION_APP_EXIT);
		registerReceiver(mAppExitReceiver, filter);
	}
	
	private void unRegisterAppExitReceiver() {
		unregisterReceiver(mAppExitReceiver);
	}
	
	@Override
    public void startActivity(Intent intent) {
    	super.startActivity(intent);
		overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
    
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
    	super.startActivityForResult(intent, requestCode);
    	overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
    
    @Override
    public void finish() {
    	super.finish();
    	overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
    
    @Override
    protected void onDestroy() {
    	unRegisterAppExitReceiver();
    	super.onDestroy();
    }

}
