package com.example.myapplication.BingGo;

public class BgSpirit extends BgDynamicGameObject  {

    public static final int BOB_STATE_JUMP = 0;
    public static final int BOB_STATE_FALL = 1;
    public static final int BOB_STATE_HIT = 2;
    public static final float BOB_JUMP_VELOCITY = 11;
    public static final float BOB_MOVE_VELOCITY = 20;
    public static final float BOB_WIDTH = 0.8f;
    public static final float BOB_HEIGHT = 0.8f;

    int state;
    float stateTime;

    public BgSpirit (float x, float y) {
        super(x, y, BOB_WIDTH, BOB_HEIGHT);
        state = BOB_STATE_FALL;
        stateTime = 0;
    }

    public void update(int x, int y){
        position.x = x;
        position.y = y;
    }
}
