package com.seven.idouban.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.widget.Container;
import com.seven.idouban.R;
import com.seven.idouban.util.ImageUtil;
import com.seven.idouban.view.TabsAdapter;

public class MainActivity extends BaseActivity implements OnClickListener {

	private DrawerLayout mDrawerLayout;

	private ActionBarDrawerToggle mDrawerToggle;
	
	private Container mContainer;

	private View mLeftDrawer;
	
	private View mVUser;
	
	private ImageView mIvAvatar;
	
	private TextView mTvUser;
	
	private TabsAdapter mAdapter;
	
	private BaseFragment[] mFragments;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	protected int getContentViewRes() {
		return R.layout.activity_main;
	}

	@Override
	protected void findViews() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    	mLeftDrawer = findViewById(R.id.left_drawer);
    	mContainer = (Container) findViewById(R.id.fragment_container);
    	mVUser = findViewById(R.id.mVUser);
	}

	@Override
	protected void initialize() {
		initImage();
		mDrawerLayout.setDrawerListener(mDrawerListener);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		
		mFragments = new BaseFragment[1];
		mFragments[0] = new MovieFragment();
		mAdapter = new TabsAdapter(this, mFragments);
		
		mContainer.setAdapter(mAdapter);
		
		mVUser.setOnClickListener(this);
	}
	
	private void initImage() {
		ImageUtil.init(getApplicationContext());
	}
	
	DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerToggle.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            mDrawerToggle.onDrawerStateChanged(newState);
        }
    };
    
    public void onClick(View v) {
    	switch (v.getId()) {
		case R.id.mVUser:
			Intent intent = new Intent(this, AuthActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
    }
    
    @Override
	public void onBackPressed() {
		long delay = System.currentTimeMillis() - lastTime;
		if (100 < delay && delay < 2000) {
			 super.onBackPressed();
		} else {
			lastTime = System.currentTimeMillis();
			toast(R.string.double_press_back_exit_msg);
		}
	}
	private long lastTime = 0;
    
    protected void onDestroy() {
    	ImageUtil.clearCache();
    	super.onDestroy();
    }
	
}
