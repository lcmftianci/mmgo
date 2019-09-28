package com.example.myapplication;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.baidu.mobstat.StatService;
import com.example.myapplication.advanbox.MyGdxGameBox2d;
import com.example.myapplication.advancestrangely.ActionUser;
import com.example.myapplication.advancestrangely.AdvanceGame;
import com.example.myapplication.advancestrangely.FirstGame;

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
        StatService.start(this);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;
//        initialize(new MyGdxGameCamera(), config);
//        initialize(new MyGdxGameLabel(), config);
//        initialize(new MyGdxGameButton(), config);
//        initialize(new MyGdxGameImage(), config);
//        initialize(new MyGdxGameAction(), config);
//        initialize(new MyGdxGameScreen(), config);
//        initialize(new MyGdxGameBox2d(), config);
//        initialize(new MyGdxGameBox2dModel(), config);
//        initialize(new MyGdxGameBox2dLight(), config);
//        initialize(new MyGdxGameMulti(), config);
//        initialize(new MyGdxGameParallax(), config);
//        initialize(new MyGdxGameParallaEffect(), config);
//        initialize(new MyGdxGameRunnableAction(), config);
//        initialize(new MyGdxGameShader(), config);   //Shader 例子
//        initialize(new MainGame(), config);      //flappy bird 例子
//        initialize(new SuperBall(), config);     //旱地冰壶例子，未完成
//        initialize(new MarioGame(), config);     //马里奥例子
//        initialize(new SuperJumper(), config);   //跳跳例子
//        initialize(new MyAdapter(), config);       //画线例子
//        initialize(new TZFEMainGame(), config);       //2048
//        initialize(new TenWaterGame(), config);       //10滴水游戏
//        initialize(new SnakeGame(), config);       //贪吃蛇
//        initialize(new Lib004_Actor(), config);       //Actor
//        initialize(new Lib004_Actor(), config);       //Actor

//        initialize(new GamePad(), config); //虚拟摇杆

        //initialize(new AdvanceGame(), config); //勇往直前
//       initialize(new ActionUser(), config); //勇往直前
        //initialize(new FirstGame(), config); //勇往直前
        initialize(new MyGdxGameBox2d(), config);
    }
}
