package com.lcmf.mmgo;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import cdc.sed.yff.AdManager;
import cdc.sed.yff.nm.bn.BannerManager;
import cdc.sed.yff.nm.sp.SpotManager;

/**
 * 主窗口
 *
 * @author Alian Lee
 * @since 2016-11-25
 */
public class SplamActivity extends BaseActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AdManager.getInstance(mContext).init("98b79534d27dd504", "a44a477c4f5c8b0a", true);
		// 设置应用版本信息
		//setupAppVersionInfo();
		// 初始化视图
		//initView();
	}
	
	/**
	 * 设置应用版本信息
	 */
//	private void setupAppVersionInfo() {
//		TextView textVersionInfo = (TextView) findViewById(R.id.tv_main_version_info);
//		if (textVersionInfo != null) {
//			textVersionInfo.append(getAppVersionName());
//		}
//	}
	
	/**
	 * 初始化视图
	 */
//	private void initView() {
//		findViewById(R.id.btn_main_show_spot_ad).setOnClickListener(this);
//		findViewById(R.id.btn_main_show_slideable_spot_ad).setOnClickListener(this);
//		findViewById(R.id.btn_main_show_native_spot_ad).setOnClickListener(this);
//	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 展示广告条窗口的 onDestroy() 回调方法中调用
		BannerManager.getInstance(mContext).onDestroy();
		
		// 退出应用时调用，用于释放资源
		// 如果无法保证应用主界面的 onDestroy() 方法被执行到，请移动以下接口到应用的退出逻辑里面调用
		
		// 插屏广告（包括普通插屏广告、轮播插屏广告、原生插屏广告）
		SpotManager.getInstance(mContext).onAppExit();
	}
	
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		// 插屏广告
//		case R.id.btn_main_show_spot_ad:
//			startActivity(new Intent(mContext, SpotAdActivity.class));
//			break;
//		// 轮播插屏广告
//		case R.id.btn_main_show_slideable_spot_ad:
//			startActivity(new Intent(mContext, SlideableSpotAdActivity.class));
//			break;
//		// 原生插屏广告
//		case R.id.btn_main_show_native_spot_ad:
//			startActivity(new Intent(mContext, NativeSpotAdActivity.class));
//			break;
//		default:
//			break;
//		}
//	}
	
	/**
	 * 获取应用版本号
	 *
	 * @return 应用当前的版本号
	 */
	private String getAppVersionName() {
		try {
			PackageManager packageManager = getPackageManager();
			return packageManager.getPackageInfo(getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			return null;
		}
	}

	@Override
	public void onClick(View view) {

	}
}