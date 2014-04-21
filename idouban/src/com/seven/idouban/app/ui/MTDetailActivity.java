package com.seven.idouban.app.ui;

import uk.co.chrisjenx.paralloid.views.ParallaxScrollView;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.common.util.AndroidUtil;
import com.seven.idouban.R;
import com.seven.idouban.model.movie.Subject;
import com.seven.idouban.model.movie.SubjectS;
import com.seven.idouban.service.MTService;
import com.seven.idouban.util.ImageUtil;

public class MTDetailActivity extends BaseActivity {
	
	private ImageView mIvBackground;
	
	private ParallaxScrollView mScrollView;
	
	private View mVContent;
	
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
		
	}

	@Override
	protected void initialize() {
		mSubjectS = (SubjectS) getIntent().getSerializableExtra("subjectS");
		setTitle(mSubjectS.title);
		if (null != mSubjectS.images && !AndroidUtil.isEmpty(mSubjectS.images.large)) {
			ViewGroup.LayoutParams lParams = mIvBackground.getLayoutParams();
			Point point = AndroidUtil.getScreenWH(this);
			lParams.width = point.x;
			lParams.height = point.y;
			mIvBackground.setLayoutParams(lParams);
			mVContent.setPadding(0, lParams.height, 0, 0);
			
			loadBackground(mSubjectS.images.large);
			mScrollView.parallaxViewBy(mIvBackground, 0.6f);
		}
		
		mService = MTService.getInstance();
		
		getSubject();
	}
	
	private void loadBackground(String path) {
		ImageUtil.loadFadeInImage(path, mIvBackground);
	}
	
	private void getSubject() {
		
	}

}
