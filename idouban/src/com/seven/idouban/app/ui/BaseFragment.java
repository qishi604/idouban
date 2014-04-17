package com.seven.idouban.app.ui;

import com.common.util.AndroidUtil;
import com.seven.idouban.util.ImageUtil;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class BaseFragment extends Fragment{
	
	protected Context mContext;
	
	protected LayoutInflater mInflater;

	protected abstract int getContentViewRes();
	
	protected abstract void findViews(View contentView);
	
	protected abstract void initialize();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater = inflater;
		View contentView = mInflater.inflate(getContentViewRes(), container, false);
		findViews(contentView);
		return contentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContext = getActivity();
		initialize();
	}
	
	protected static void LoadThumb(String url, ImageView imageView) {
		ImageUtil.loadThumb(url, imageView);
	}
	
	public void toast(String msg) {
    	AndroidUtil.toastS(mContext, msg);
	}
	
    public void toast(int msgRes) {
		AndroidUtil.toastS(mContext, msgRes);
	}
}
