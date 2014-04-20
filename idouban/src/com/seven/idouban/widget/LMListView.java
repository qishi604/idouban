package com.seven.idouban.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.seven.idouban.R;

/**
 * 
 * @author seven
 * @description Load more ListView
 */
public class LMListView extends ListView implements OnScrollListener, OnClickListener {
	
	private View mFooter;
	
	private ProgressBar mFooterProgressBar;
	
	private TextView mTvLoadMore;
	
	private OnLoadMoreListener mLoadMoreListener;
	
	private int mFooterHeight;

	public LMListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LMListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LMListView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		mFooterHeight = getResources().getDimensionPixelSize(R.dimen.list_footer_height);
		mFooter = LayoutInflater.from(getContext()).inflate(R.layout.list_footer_loading, null);
//		mFooter.setVisibility(View.GONE);
		addFooterView(mFooter);
		mFooterProgressBar = (ProgressBar) mFooter.findViewById(R.id.mFooterProgressBar);
		mTvLoadMore = (TextView) mFooter.findViewById(R.id.mTvLoadMore);
		mTvLoadMore.setOnClickListener(this);
	}
	
	public void setOnLoadMoreListener(OnLoadMoreListener listener) {
		mLoadMoreListener = listener;
	}
	
	public void onLoadFinish() {
		mTvLoadMore.setVisibility(View.VISIBLE);
		mFooterProgressBar.setVisibility(View.GONE);
	}
	
	public void hideFooter() {
		mFooter.setPadding(0, 0, 0, -mFooterHeight);
	}
	
	public interface OnLoadMoreListener {
		
		void onLoadMore();
	}

	private void loadMore() {
		mTvLoadMore.setVisibility(View.GONE);
		mFooterProgressBar.setVisibility(View.VISIBLE);
		if (null != mLoadMoreListener) {
			mLoadMoreListener.onLoadMore();
		}
	}
	
	@Override
	public void onClick(View v) {
		if (v == mTvLoadMore) {
			loadMore();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (firstVisibleItem + visibleItemCount == totalItemCount) {
			mFooter.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

}
