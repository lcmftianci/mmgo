package com.example.myapplication;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.myapplication.ActorUser.Lib004_Actor;
import com.example.myapplication.BingGo.SuperBall;
import com.example.myapplication.BirdCore.SuperJumper;
import com.example.myapplication.Flappy.FlappyBird;
import com.example.myapplication.GeniusDirection.MyAdapter;
import com.example.myapplication.Snake.SnakeGame;
import com.example.myapplication.flappybird.MainGame;
import com.example.myapplication.mario.MarioGame;
import com.example.myapplication.mylecture.AnimationUser;
import com.example.myapplication.mylecture.LabelStyleUser;
import com.example.myapplication.mylecture.LecGame;
import com.example.myapplication.mylecture.LibgdxSpineEffectView;
import com.example.myapplication.mylecture.MyGdxGameAction;
import com.example.myapplication.mylecture.MyGdxGameButton;
import com.example.myapplication.mylecture.MyGdxGameCamera;
import com.example.myapplication.mylecture.MyGdxGameImage;
import com.example.myapplication.mylecture.MyGdxGameLabel;
import com.example.myapplication.mylecture.MyGdxGameMulti;
import com.example.myapplication.mylecture.MyGdxGameRunnableAction;
import com.example.myapplication.mylecture.MyGdxGameScreen;
import com.example.myapplication.mylecture.box2dlightbasic.MyGdxGameBox2dLight;
import com.example.myapplication.mylecture.box2dmodel.MyGdxGameBox2dModel;
import com.example.myapplication.mylecture.box2duser.MyGdxGameBox2d;
import com.example.myapplication.mylecture.parallaeffect.MyGdxGameParallaEffect;
import com.example.myapplication.mylecture.parallax.MyGdxGameParallax;
import com.example.myapplication.mylecture.shadershockwave.MyGdxGameShader;
import com.example.myapplication.tenwater.TenWaterGame;
import com.example.myapplication.tzfe.TZFEMainGame;

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
        initialize(new MyGdxGameParallaEffect(), config);
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
    }
}
