package com.seven.idouban.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.seven.idouban.app.ui.BaseFragment;

/**
 * @author seven
 * @version V1.0
 * @date 13-11-1
 * @description
 */
public class TabsAdapter extends FragmentPagerAdapter {
    
    private BaseFragment[] mFragments;
    
    public TabsAdapter(FragmentActivity activity, BaseFragment[] fragments) {
        super(activity.getSupportFragmentManager());
        mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    	// To prevent the adapter destroy fragment
    }
}