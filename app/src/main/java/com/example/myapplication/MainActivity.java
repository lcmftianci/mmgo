package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import cdc.sed.yff.AdManager;
import cdc.sed.yff.nm.sp.SplashViewSettings;
import cdc.sed.yff.nm.sp.SpotManager;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AdManager.getInstance(MainActivity.this.getApplicationContext()).init("98b79534d27dd504", "a44a477c4f5c8b0a", true);
//        SpotManager.getInstance(MainActivity.this.getApplicationContext()).requestSpot(SpotRequestListener spotRequestListener);
//
//        SplashViewSettings splashViewSettings = new SplashViewSettings();
//        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
//        splashViewSettings.setTargetClass(HelloGameActivity.class.getClass());
//        // 使用默认布局参数
//        splashViewSettings.setSplashViewContainer(ViewGroup splashViewContainer);
//        // 使用自定义布局参数
//        splashViewSettings.setSplashViewContainer(ViewGroup splashViewContainer, ViewGroup.LayoutParams splashViewLayoutParams);
//        SpotManager.getInstance(MainActivity.this).showSplash(MainActivity.this, splashViewSettings, SpotListener spotListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 开屏展示界面的 onDestroy() 回调方法中调用
        SpotManager.getInstance(MainActivity.this).onDestroy();
    }
}
