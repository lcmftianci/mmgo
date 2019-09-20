package com.example.myapplication;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.myapplication.GestureUser.MyGame;
import com.example.myapplication.Lecture.FreeType;
import com.example.myapplication.Lecture.Lib021_Box2d1;
import com.example.myapplication.Lecture.Lib029_NinePatch;
import com.example.myapplication.Snake.SnakeGame;
import com.example.myapplication.flappybird.MainGame;
import com.example.myapplication.tenwater.TenWaterGame;

import android.os.Bundle;
import android.util.Log;


public class HelloGameActivity extends AndroidApplication {
    private String TAG = "--- HelloGameActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Login Main");
//        setContentView(R.layout.activity_main);
//        initialize(new FirstGame());

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;
        initialize(new MainGame(), config);
    }
}
