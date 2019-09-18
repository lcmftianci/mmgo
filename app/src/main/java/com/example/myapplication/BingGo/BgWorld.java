package com.example.myapplication.BingGo;

import android.util.Log;

import com.badlogic.gdx.ScreenAdapter;

public class BgWorld{

    private final String TAG = "BgWorld";
    BgSpirit ball;

    public interface BgWorldListener {
        public void jump();

        public void highJump();

        public void hit();

        public void coin();
    }
    public BgWorld (BgWorldListener listener) {
        this.ball = new BgSpirit(5, 1);
//        this.platforms = new ArrayList<Platform>();
//        this.springs = new ArrayList<Spring>();
//        this.squirrels = new ArrayList<Squirrel>();
//        this.coins = new ArrayList<Coin>();
//        this.listener = listener;
//        rand = new Random();
//        generateLevel();
//
//        this.heightSoFar = 0;
//        this.score = 0;
//        this.state = WORLD_STATE_RUNNING;
    }

    public void update(int x, int y) {
//        ball.position.x = x;
//        ball.position.y = y;
//        Log.d(TAG, "x:" + x + " y:" + y );
    }
}
