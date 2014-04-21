package com.seven.idouban.util;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.seven.idouban.R;

public class ImageUtil {


	private static ImageLoader mImageLoader;

	private static DisplayImageOptions mBigImageDisplayImageOptions;
	
	private static DisplayImageOptions mFadeInDisplayImageOptions;
	
	public static void init(Context context) {
		initImageCache(context);
	}

	public static void reset(Context context) {

	}

	private static void initImageCache(Context context) {

		DisplayImageOptions options = new DisplayImageOptions.Builder()
		 		.showImageOnLoading(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisc(true).build();
		
		mBigImageDisplayImageOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true).cacheOnDisc(true).build();
		
		FadeInBitmapDisplayer fadeInBitmapDisplayer = new FadeInBitmapDisplayer(1000);
		mFadeInDisplayImageOptions = new DisplayImageOptions.Builder()
		.displayer(fadeInBitmapDisplayer)
		.cacheInMemory(true).cacheOnDisc(true).build();

		int memCacheSize = (int) Math.round(0.25 * Runtime.getRuntime()
				.maxMemory());
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(options)
				.memoryCacheSize(memCacheSize).build();
		ImageLoader.getInstance().init(configuration);

		mImageLoader = ImageLoader.getInstance();
	}

	/**
	 * Load thumb image
	 * 
	 * @param url
	 * @param imageView
	 */
	public static void loadThumb(String url, ImageView imageView) {
		mImageLoader.displayImage(url, imageView);
	}

	/**
	 * Load image
	 * 
	 * @param url
	 * @param imageView
	 */
	public static void loadImage(String url, ImageView imageView) {
		mImageLoader.displayImage(url, imageView, mBigImageDisplayImageOptions);
	}
	
	public static void loadFadeInImage(String url, ImageView imageView) {
		mImageLoader.displayImage(url, imageView, mFadeInDisplayImageOptions);
	}
	
	public static void loadFadeInImage(String url, ImageView imageView, ImageLoadingListener listener) {
		mImageLoader.displayImage(url, imageView, mFadeInDisplayImageOptions, listener);
	}

	public static void loadImage(String url, ImageView imageView,
			DisplayImageOptions options) {
		mImageLoader.displayImage(url, imageView, options);
	}

	/**
	 * Clear the image cache
	 */
	public static void clearCache() {
		mImageLoader.clearMemoryCache();
	}
}
