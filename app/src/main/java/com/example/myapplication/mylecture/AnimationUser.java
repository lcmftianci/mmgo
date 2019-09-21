package com.example.myapplication.mylecture;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class AnimationUser implements ApplicationListener {
    Animation animation;
    TextureRegion curFrame;
    TextureRegion[] arrFrame;
    SpriteBatch batch;
    Texture texture;
    private final int FRAME_COLS = 2;
    private final int FRAME_ROWS = 5;
    float stateTime;
    private static final String TAG = "AnimationUser";

    @Override
    public void create() {
        texture = new Texture(Gdx.files.internal("heart/girlheart.png"));
        TextureRegion[][] temp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS, texture.getHeight()/FRAME_ROWS);
        arrFrame = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int inx = 0;
        for(int i = 0; i < FRAME_ROWS; i++){
            for(int j = 0; j < FRAME_COLS; j++){
                arrFrame[inx++] = temp[i][j];
            }
        }
        animation = new Animation(0.15f, arrFrame);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        batch = new SpriteBatch();
        stateTime = 0;
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);   //设置背景颜色为白色
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);         //清屏
        stateTime += Gdx.graphics.getDeltaTime();
        Log.d(TAG, "delta:" + stateTime);
        curFrame = animation.getKeyFrame(stateTime, true);
        batch.begin();
        batch.draw(curFrame, 20, 20, 200,200);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
