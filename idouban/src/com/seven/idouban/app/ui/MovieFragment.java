package com.seven.idouban.app.ui;

import org.apache.http.Header;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.view.MyArrayAdapter;
import com.common.view.ViewHolder;
import com.loopj.android.http.TextHttpResponseHandler;
import com.seven.idouban.R;
import com.seven.idouban.model.Image;
import com.seven.idouban.model.movie.SubjectS;
import com.seven.idouban.model.movie.SubjectsResponse;
import com.seven.idouban.service.MTService;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;
import com.common.util.JsonUtil;
import com.common.util.Logs;

public class MovieFragment extends BaseListFragment {
	
	private JazzyListView mListView;
	
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
		mListView = (JazzyListView) contentView.findViewById(R.id.mListView);

	}

	@Override
	protected void initialize() {
		super.initialize();
		mListView.setTransitionEffect(JazzyHelper.CARDS);
		mAdapter = new MtAdapter(mContext);
		mListView.setAdapter(mAdapter);
		mService = MTService.getInstance();
		
		getData(mStart);
	}
	
	@Override
	protected void reloadData() {
		getData(0);
	}
	
	private void getData(int start) {
		if (0 != mTotal && start >= mTotal) {
			return;
		}
		
		mService.getTop250(start, 100, new TextHttpResponseHandler() {
			
			@Override
			public void onStart() {
				super.onStart();
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
							}
							mAdapter.addAll(response.subjects);
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
				mCenterLoading.showText("Error");
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

		public MTHolder(View convertView) {
			super(convertView);
			ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
			tvName = (TextView) convertView.findViewById(R.id.tvName);
		}

		@Override
		public void setData(SubjectS d) {
			Image images = d.images;
			if (null != images) {
				LoadThumb(images.small, ivThumb);
			}
			tvName.setText(d.title);
		}
	}

}
