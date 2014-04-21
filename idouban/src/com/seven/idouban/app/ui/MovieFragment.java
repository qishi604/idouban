package com.seven.idouban.app.ui;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.util.JsonUtil;
import com.common.util.Logs;
import com.common.view.MyArrayAdapter;
import com.common.view.ViewHolder;
import com.loopj.android.http.TextHttpResponseHandler;
import com.seven.idouban.R;
import com.seven.idouban.model.Image;
import com.seven.idouban.model.movie.SubjectS;
import com.seven.idouban.model.movie.SubjectsResponse;
import com.seven.idouban.service.MTService;
import com.seven.idouban.util.ImageUtil;
import com.seven.idouban.widget.LMListView;
import com.seven.idouban.widget.LMListView.OnLoadMoreListener;

public class MovieFragment extends BaseListFragment {
	
	private LMListView mListView;
	
	private ImageView mIvBackground;
	
	private MtAdapter mAdapter;
	
	private MTService mService;
	
	private int mStart = 0;
	
	private int mTotal = 0;
	
	@Override
	protected int getContentViewRes() {
		return R.layout.fragment_movie;
	}

	@Override
	protected void findViews(View contentView) {
		super.findViews(contentView);
		mListView = (LMListView) contentView.findViewById(R.id.mListView);
		mIvBackground = (ImageView) contentView.findViewById(R.id.mIvBackground);
	}

	@Override
	protected void initialize() {
		super.initialize();
		
		View header = mInflater.inflate(R.layout.list_action_header, null);
		mListView.addHeaderView(header);
		
		mAdapter = new MtAdapter(mContext);
		mListView.setAdapter(mAdapter);
		mService = MTService.getInstance();
		
		mListView.setOnLoadMoreListener(new OnLoadMoreListener() {
			
			@Override
			public void onLoadMore() {
				getData(mStart);
			}
		});
		
		getData(mStart);
	}
	
	private void loadBackground(String path) {
		ImageUtil.loadFadeInImage(path, mIvBackground);
	}
	
	@Override
	protected void reloadData() {
		getData(0);
	}
	
	private void getData(final int start) {
		if (0 != mTotal && start >= mTotal) {
			return;
		}
		
		mService.getTop250(start, 20, new TextHttpResponseHandler() {
			
			@Override
			public void onStart() {
				super.onStart();
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				mListView.onLoadFinish();
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				mCenterLoading.hide();
				Logs.i(responseString);
				if (!TextUtils.isEmpty(responseString)) {
					
					try {
						SubjectsResponse response = JsonUtil.fromJson(responseString, SubjectsResponse.class);
						if (null != response) {
							if (mTotal != response.total) {
								mTotal = response.total;
							}
							int end = response.start + response.count;
							if (end < mTotal) {
								mStart = end;
							} else {
								mListView.hideFooter();
							}
							SubjectS[] subjectSs = response.subjects;
							if (subjectSs.length > 0) {
								loadBackground(subjectSs[0].images.large);
								if (start == 0) {
									mAdapter.clear();
								}
								mAdapter.addAll(subjectSs);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						toast("Some error");
					}
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				toast(responseString);
				if (start == 0) {
					mCenterLoading.showText("Error");
				}
			}
		});
	}
	
	static class MtAdapter extends MyArrayAdapter<SubjectS> {

		public MtAdapter(Context context) {
			super(context);
		}

		@Override
		public int getItemViewResource() {
			return R.layout.item_movie;
		}

		@Override
		public ViewHolder<SubjectS> getViewHolder(View convertView) {
			return new MTHolder(convertView);
		}
	}
	
	static class MTHolder extends ViewHolder<SubjectS> {
		
		ImageView ivThumb;
		TextView tvName;
		TextView tvOriginalName;
		TextView tvYear;
		TextView tvRating;

		public MTHolder(View convertView) {
			super(convertView);
			ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
			tvName = (TextView) convertView.findViewById(R.id.tvName);
			tvOriginalName = (TextView) convertView.findViewById(R.id.tvOriginalName);
			tvYear = (TextView) convertView.findViewById(R.id.tvYear);
			tvRating = (TextView) convertView.findViewById(R.id.tvRating);
		}

		@Override
		public void setData(SubjectS d) {
			Image images = d.images;
			if (null != images) {
				loadThumb(images.small, ivThumb);
			}
			tvName.setText(d.title);
			tvOriginalName.setText("原名：" + d.original_title);
			tvYear.setText("年代：" + d.year);
			tvRating.setText("评分：" + d.rating.average);
			
			mConvertView.setTag(R.id.data, d);
			mConvertView.setOnClickListener(ITEMCLICKCLICKLISTENER);
			
		}
	}
	
	private static OnClickListener ITEMCLICKCLICKLISTENER = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			SubjectS s = (SubjectS) v.getTag(R.id.data);
			Intent intent = new Intent(context, MTDetailActivity.class);
			intent.putExtra("subjectS", s);
			context.startActivity(intent);
		}
	};

}
