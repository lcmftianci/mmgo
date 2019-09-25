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
        ball = new Ball();
        brick = new Brick(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/10*9, 0, asserts.regionBrick);
        b2d = new Box2dDetection();
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
        this.addActor(ball);
        this.addActor(brick);
        this.addActor(bomb);
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

    public boolean checkBallBrick(){
        if(!bDrawBall)
            return false;
        Vector2[] arrBallRect = ball.getRect();
        Vector2[] arrBrickRect = brick.getRect();

        int inx = -1;
        for(int i = 0; i < arrBallRect.length; i++){
            Log.d(TAG, "==>> x:" + arrBallRect[i].x + " y:" + arrBallRect[i].y);
            Log.d(TAG, "==>>1 x:" + arrBrickRect[0].x + " y:" + arrBrickRect[0].y
                    + "==>>2 x:" + arrBrickRect[1].x + " y:" + arrBrickRect[1].y
                    + "==>>3 x:" + arrBrickRect[2].x + " y:" + arrBrickRect[2].y
                    + "==>>3 x:" + arrBrickRect[3].x + " y:" + arrBrickRect[3].y);
            if(b2d.checkTwoBox(arrBrickRect[0], arrBrickRect[1], arrBrickRect[2], arrBrickRect[3], arrBallRect[i])){
                inx = i;
                Log.d(TAG, "===>>" + inx);
                //break;
            }
        }

//        if(inx == 0)
//            ball.setDirection(false,false);
//        else if(inx ==1)
//            ball.setDirection(false, true);
//        else if(inx == 2)
//            ball.setDirection(true, false);
//        else if(inx == 3)
//            ball.setDirection(true, true);
        return true;
    }

    public void update(){
        //batch.draw(asserts.regionBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        changeBall();
        //if(bDrawBall)
        //    ball.setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);
        checkBallBrick();
        if(!ball.isrun())
            bomb.setBubble(false);
    }
}
