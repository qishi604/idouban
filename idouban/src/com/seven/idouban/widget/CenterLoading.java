package com.seven.idouban.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seven.idouban.R;

public class CenterLoading extends RelativeLayout {
	
	public static final String NO_NETWORK = "无连接";
	
	public static final String LOAD_DATA_FAIL = "加载失败";
	
	public static final String NO_DATA = "数据为空";

	private ProgressBar mProgressBar;

	private TextView mTextView;

	private View mBtnRetry;

	public CenterLoading(Context context) {
		super(context);
		init();
	}

	public CenterLoading(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CenterLoading(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
//		setVisibility(View.GONE);
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		View contentView = LayoutInflater.from(getContext()).inflate(
				R.layout.layout_center_progressbar, null);

		mProgressBar = (ProgressBar) contentView
				.findViewById(R.id.pb_center_progress);
		mTextView = (TextView) contentView.findViewById(R.id.tv_center);
		mBtnRetry = contentView.findViewById(R.id.btn_center);
		mTextView.setVisibility(View.GONE);
		mBtnRetry.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.GONE);

		RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		addView(contentView, lParams);

		mBtnRetry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != mOnRetryListener) {
					showProgress();
					mOnRetryListener.onRetry();
				}
			}
		});
		showProgress();
	}

	public void showProgress() {
		hideViews(mTextView, mBtnRetry);
		showViews(this, mProgressBar);
	}

	public void showText(String msg) {
		hideViews(mProgressBar);
		showViews(this, mTextView, mBtnRetry);
		
		mTextView.setText(msg);
	}
	
	public void showText(int res) {
		hideViews(mProgressBar);
		showViews(this, mTextView, mBtnRetry);
		
		mTextView.setText(res);
	}
	
	public void hideBtn() {
		hideViews(mBtnRetry);
	}
	
	private void hideViews(View...views) {
		for (View view : views) {
			view.setVisibility(View.GONE);
		}
	}

	private void showViews(View... views) {
		for (View view : views) {
			view.setVisibility(View.VISIBLE);
		}
	}

	private OnRetryListener mOnRetryListener;

	public void setOnRetryListener(OnRetryListener listener) {
		mOnRetryListener = listener;
	}

	public void hide() {
		setVisibility(View.GONE);
	}

	public static interface OnRetryListener {

		void onRetry();
	}

}
