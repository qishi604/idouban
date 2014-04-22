package com.seven.idouban.app.ui;

import org.apache.http.Header;

import uk.co.chrisjenx.paralloid.views.ParallaxScrollView;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.util.AndroidUtil;
import com.common.util.JsonUtil;
import com.loopj.android.http.TextHttpResponseHandler;
import com.seven.idouban.R;
import com.seven.idouban.model.Celebrity;
import com.seven.idouban.model.movie.Subject;
import com.seven.idouban.model.movie.SubjectS;
import com.seven.idouban.service.MTService;
import com.seven.idouban.util.ImageUtil;
import com.seven.idouban.widget.CenterLoading;

public class MTDetailActivity extends BaseActivity {

	private ImageView mIvBackground;

	private ParallaxScrollView mScrollView;

	private View mVContent;

	private TextView mTvOriginalTitle, mTvAka, mTvGenres, mTvYear,
			mTvCountries, mTvRating, mTvSummary;
	
	private LinearLayout mLDirectors, mLCasts;

	private CenterLoading mCenterLoading;

	private SubjectS mSubjectS;

	private Subject mSubject;

	private MTService mService;

	@Override
	protected boolean showHome() {
		return true;
	}

	@Override
	protected int getContentViewRes() {
		return R.layout.activitity_movie_detail;
	}

	@Override
	protected void findViews() {
		mScrollView = (ParallaxScrollView) findViewById(R.id.mScrollView);
		mIvBackground = (ImageView) findViewById(R.id.mIvBackground);
		mVContent = findViewById(R.id.mVContent);
		mCenterLoading = (CenterLoading) findViewById(R.id.mCenterLoading);

		mTvOriginalTitle = (TextView) findViewById(R.id.mTvOriginalTitle);
		mTvAka = (TextView) findViewById(R.id.mTvAka);
		mTvGenres = (TextView) findViewById(R.id.mTvGenres);
		mTvYear = (TextView) findViewById(R.id.mTvYear);
		mTvCountries = (TextView) findViewById(R.id.mTvCountries);
		mTvRating = (TextView) findViewById(R.id.mTvRating);
		mTvSummary = (TextView) findViewById(R.id.mTvSummary);
		mLDirectors = (LinearLayout) findViewById(R.id.mLDirectors);
		mLCasts = (LinearLayout) findViewById(R.id.mLCasts);
	}

	@Override
	protected void initialize() {
		mSubjectS = (SubjectS) getIntent().getSerializableExtra("subjectS");
		setTitle(mSubjectS.title);
		if (null != mSubjectS.images
				&& !AndroidUtil.isEmpty(mSubjectS.images.large)) {
			ViewGroup.LayoutParams lParams = mIvBackground.getLayoutParams();
			Point point = AndroidUtil.getScreenWH(this);
			lParams.width = point.x;
			lParams.height = point.y;
			mIvBackground.setLayoutParams(lParams);
			mVContent.setPadding(0, lParams.height, 0, 0);

			loadBackground(mSubjectS.images.large);
			mScrollView.parallaxViewBy(mIvBackground, 0.4f);
			
			mTvOriginalTitle.setText(mSubjectS.original_title);
		}

		mService = MTService.getInstance();

		getSubject();
	}

	private void setData() {
		setTextArray(mTvAka, mSubject.aka, "又名：");
		setTextArray(mTvGenres, mSubject.genres, "题材：");
		setTextArray(mTvCountries, mSubject.countries, null);
		mTvYear.setText("年代：" + mSubject.year);
		if (null != mSubject.rating) {
			mTvRating.setText("评分：" + mSubject.rating.average);
		}
		mTvSummary.setText("简介\n" + mSubject.summary);
		loadItem(mSubject.directors, mLDirectors);
		loadItem(mSubject.casts, mLCasts);
	}
	
	private void setTextArray(TextView textView, String[] array, String prefix) {
		if (null != array && array.length > 0) {
			String string = TextUtils.join(", ", array);
			if (null != prefix) {
				string = prefix + string;
			}
			textView.setText(string);
		}
	}
	
	private void loadItem(Celebrity[] celebrities, ViewGroup parent) {
		if (null != celebrities && celebrities.length > 0) {
			LayoutInflater inflater = LayoutInflater.from(this);
			for (int i = 0, len = celebrities.length; i < len; i++) {
				View view = inflater.inflate(R.layout.item_cast, null);
				parent.addView(view);
				ImageView ivThumb = (ImageView) view.findViewById(R.id.ivThumb);
				TextView tvName = (TextView) view.findViewById(R.id.tvName);
				Celebrity celebrity = celebrities[i];
				if (null != celebrity.avatars) {
					loadThumb(celebrity.avatars.small, ivThumb);
				}
				tvName.setText(celebrity.name);
				final String id = celebrity.id;
				view.setTag(id);
				view.setOnClickListener(mCelebrityClickListener);
			}
		}
	}
	
	private OnClickListener mCelebrityClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String id = (String) v.getTag();
			showCelebrity(id);
		}
	};
	
	private void showCelebrity(String id) {
		toast("" + id);
	}

	private void loadBackground(String path) {
		ImageUtil.loadFadeInImage(path, mIvBackground);
	}

	private void getSubject() {
		// TODO detect network

		mService.getMT(mSubjectS.id, new TextHttpResponseHandler() {

			@Override
			public void onStart() {
				super.onStart();
				mCenterLoading.showProgress();
			}

			@Override
			public void onFinish() {
				super.onFinish();
				mCenterLoading.hide();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				if (!TextUtils.isEmpty(responseString)) {

					try {
						Subject response = JsonUtil.fromJson(responseString,
								Subject.class);
						if (null != response) {
							mSubject = response;
							setData();
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
				if (TextUtils.isEmpty(responseString)) {
					toast("error");
				} else {
					toast(responseString);
				}
			}
		});
	}

}
