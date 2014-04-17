package com.seven.idouban.app.ui;

import android.view.View;

import com.seven.idouban.R;
import com.seven.idouban.widget.CenterLoading;
import com.seven.idouban.widget.CenterLoading.OnRetryListener;

public abstract class BaseListFragment extends BaseFragment {
	
	protected CenterLoading mCenterLoading;

	@Override
	protected void findViews(View contentView) {
		mCenterLoading = (CenterLoading) contentView
				.findViewById(R.id.mCenterLoading);
	}

	@Override
	protected void initialize() {
		mCenterLoading.setOnRetryListener(new OnRetryListener() {

			@Override
			public void onRetry() {
				reloadData();
			}
		});
	}
	
	/**
	 * 重新加载数据
	 */
	protected abstract void reloadData();
	
}
