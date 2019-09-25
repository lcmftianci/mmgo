package com.example.myapplication.advancestrangely;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.myapplication.advanceactor.Ball;
import com.example.myapplication.advanceactor.Bomb;
import com.example.myapplication.advanceactor.Brick;
import com.example.myapplication.algorithm.Box2dDetection;

import java.util.ArrayList;
import java.util.List;

public class MainGameStage extends Stage {

    SpriteBatch batch;
    AdvanceAsserts asserts;
    Image background;
    Image floor;
    Texture texture;
    TextureRegion regionBack;
    Bomb bomb;
    Ball ball;
    Brick brick;
    float[] ballxy;
    boolean bDrawBall;
    Box2dDetection b2d;

    public final List<Ball> balls;

    private final static String TAG = "MainGameStage";


//    public MainGameStage() {
//        super();
//        texture = new Texture(Gdx.files.internal("atlas/images.png"));
//        regionBack = new TextureRegion(texture, 1,1,480,1000);
//        background = new Image(regionBack);
//        background.setHeight(Gdx.graphics.getHeight());
//        background.setWidth(Gdx.graphics.getWidth());
//        //background.setScale(20,20);
//        //setViewport(new ScreenViewport());
//        //background.setPosition(0,0);
//        this.addActor(background);
//    }

    public MainGameStage(AdvanceAsserts asserts){
        this.asserts = asserts;
        background = new Image(asserts.regionBack);
        floor = new Image(asserts.regionFloor);
        bomb = new Bomb(Gdx.graphics.getWidth()/2, (int)floor.getHeight());
        ball = new Ball(Gdx.graphics.getWidth()/2, 0);
        brick = new Brick(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/10*9, 100, asserts.regionBrick);
        b2d = new Box2dDetection();

        this.balls = new ArrayList<Ball>();

        bDrawBall = false;

        floor.setWidth(Gdx.graphics.getWidth());
        floor.setHeight(Gdx.graphics.getHeight()/4);

        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());
        //background.setScale(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.setPosition(0,0);
        floor.setPosition(0,0);
        this.addActor(background);
        this.addActor(floor);
        //this.addActor(ball);

        for(int i =0; i < 10; i++){
            this.balls.add(new Ball(Gdx.graphics.getWidth()/2, 0));
            this.addActor(this.balls.get(i));
        }

        this.addActor(brick);
        this.addActor(bomb);
    }

    public boolean changeAllBall(){
        if(bomb.getState()&&bomb.getTouchState()){
            ballxy = bomb.AlreadyGet();
            bomb.setBubble(true);
            bDrawBall = true;

            for (int i =0; i < balls.size(); i++){
                balls.get(i).setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);
                balls.get(i).delay(i*3);
            }

            return true;
        }
        return false;
    }

    public boolean changeBall(){
        if(bomb.getState()&&bomb.getTouchState()){
            ballxy = bomb.AlreadyGet();
            bomb.setBubble(true);
            bDrawBall = true;
            ball.setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);
            return true;
        }
        return false;
    }

    public boolean checkAllBallBrick(){
        if(!bDrawBall)
            return false;
        Vector2[] arrBrickRect = brick.getRect();

        for (int bi = 0; bi < balls.size(); bi++){
            Vector2[] arrBallRect = balls.get(bi).getRect();
            int lb = 0, rb = 0, rt = 0, lt = 0;
            for(int i = 0; i < arrBallRect.length; i++){
                if(b2d.checkTwoBox(arrBrickRect[0], arrBrickRect[1], arrBrickRect[2], arrBrickRect[3], arrBallRect[i])){
                    if(i == 0)lb = 1;
                    if(i == 1)rb = 1;
                    if(i == 2)rt = 1;
                    if(i == 3)lt = 1;
                }
            }

            if(lb == 1 && rb == 1){
                balls.get(bi).setTopBottomDirection(true);
            }

            if((lb == 1 && lt == 1)||lb == 1 || lt == 1){
                balls.get(bi).setLeftRightDirection(false);
            }

            if((rb == 1 && rt == 1) || rb == 1 || rt == 1){
                balls.get(bi).setLeftRightDirection(true);
            }

            if((rt == 1 && lt == 1)|| (rt == 1 && lt == 0) || (rt == 0 && lt == 1) || rt == 1 || lt == 1){
                balls.get(bi).setTopBottomDirection(false);
            }

            if(rt == 1 || lt == 1 || lb == 1 || rb == 1) {
                Log.d(TAG, "==>>rt:" + rt + " lt:" + lt + " lb:" + lb + " rb:" + rb);
                brick.setNum(0);
            }
        }

        return true;
    }

    public boolean checkBallBrick(){
        if(!bDrawBall)
            return false;
        Vector2[] arrBallRect = ball.getRect();
        Vector2[] arrBrickRect = brick.getRect();

        int lb = 0, rb = 0, rt = 0, lt = 0;
        for(int i = 0; i < arrBallRect.length; i++){
            if(b2d.checkTwoBox(arrBrickRect[0], arrBrickRect[1], arrBrickRect[2], arrBrickRect[3], arrBallRect[i])){
                if(i == 0)lb = 1;
                if(i == 1)rb = 1;
                if(i == 2)rt = 1;
                if(i == 3)lt = 1;
            }
        }

        if(lb == 1 && rb == 1){
            ball.setTopBottomDirection(true);
        }

        if((lb == 1 && lt == 1)||lb == 1 || lt == 1){
            ball.setLeftRightDirection(false);
        }

        if((rb == 1 && rt == 1) || rb == 1 || rt == 1){
            ball.setLeftRightDirection(true);
        }

        if((rt == 1 && lt == 1)|| (rt == 1 && lt == 0) || (rt == 0 && lt == 1) || rt == 1 || lt == 1){
            ball.setTopBottomDirection(false);
        }

        if(rt == 1 || lt == 1 || lb == 1 || rb == 1) {
            Log.d(TAG, "==>>rt:" + rt + " lt:" + lt + " lb:" + lb + " rb:" + rb);
            brick.setNum(0);
        }

        return true;
    }

    public void update(){
        //batch.draw(asserts.regionBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //if(bDrawBall)
        //    ball.setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);

        /*
        changeBall();
        checkBallBrick();
        if(!ball.isrun())
            bomb.setBubble(false);
        */
        changeAllBall();
        checkAllBallBrick();
        for(int i = 0; i < balls.size(); i++){
            if(balls.get(i).isrun())
                continue;
            if(i == balls.size() - 1)
                bomb.setBubble(false);
        }
    }
}
