package com.example.myapplication;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.myapplication.BingGo.SuperBall;
import com.example.myapplication.BirdCore.SuperJumper;
import com.example.myapplication.GeniusDirection.CHBezierLine;
import com.example.myapplication.GeniusDirection.Lib025_PicChange;
import com.example.myapplication.GeniusDirection.MyAdapter;
import com.example.myapplication.GeniusDirection.OrthographicCameraExample;

import android.os.Bundle;
import android.util.Log;


public class HelloGameActivity extends AndroidApplication {
    private String TAG = "HelloGameActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Login Main");
//        setContentView(R.layout.activity_main);
//        initialize(new FirstGame());

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;
        initialize(new SuperBall(), config);
    }
}
