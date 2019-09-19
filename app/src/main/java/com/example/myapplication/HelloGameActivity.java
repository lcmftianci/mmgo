package com.example.myapplication;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.myapplication.GeniusDirection.MyBezier;

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
        initialize(new MyBezier.Box2dUser(), config);
    }
}
