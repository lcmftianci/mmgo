package com.example.myapplication.advanceactor;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.example.myapplication.algorithm.BallSpring;

/*
* 绘制人间大炮，打出去直线并带有反弹效果
* */

public class Bomb extends Actor {
    private final static String TAG = "=== Bomb";
    Texture textureBomb;
    Sprite spriteBomb;
    float statetime;

    BallSpring bs;
    Texture result;
    SpriteBatch spriteBatch;
    Pixmap pixmap;

    public Bomb(){
        textureBomb = new Texture(Gdx.files.internal("bomb/bomb.jpg"));
        spriteBomb = new Sprite(textureBomb, 138, 158, 306 - 138,378 - 157);
        spriteBomb.setSize(Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/6);
        //spriteBomb.setOrigin(spriteBomb.getWidth()/2, spriteBomb.getHeight()/4);
        spriteBomb.setOrigin(spriteBomb.getWidth()/2, 0);
        spriteBomb.setPosition(Gdx.graphics.getWidth()/2 - spriteBomb.getWidth()/2,0);
        statetime = 0.0f;

        pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        bs = new BallSpring();
        result = new Texture(pixmap);
        spriteBatch = new SpriteBatch();
    }

    //更改大炮的方向
    public void update(){
        if(Gdx.input.isTouched()){
            int touchX = Gdx.input.getX();
            int touchY =  Gdx.graphics.getHeight() - Gdx.input.getY();

            if(touchX > Gdx.graphics.getWidth()/2){
                int touchCutX = touchX - Gdx.graphics.getWidth()/2;
                float dgre = (float)touchY/(float)touchCutX;
                float degress = (float) (Math.atan(dgre)/Math.PI*180.0f);
                spriteBomb.setRotation(degress-90);
                Log.d(TAG, "l degress:" + (degress-90) + " dgre:" + dgre + " touchCutX:" + touchCutX + " touchY:" + touchY);
            }else if(touchX == Gdx.graphics.getWidth()/2){
                spriteBomb.setRotation(0);
                Log.d(TAG, "degress:" + 0);
            }else {
                int touchCutX = Gdx.graphics.getWidth()/2 - touchX;
                float dgre = (float) touchY/(float) touchCutX;
                float degress = (float) (Math.atan(dgre)/Math.PI*180.0f);
                spriteBomb.setRotation(90 - degress);
                Log.d(TAG, "s degress:" + (90 -degress)  + " dgre:" + dgre + " touchX:" + touchX + " Gdx.input.getY():" + Gdx.input.getY());
            }
            //pixmap.drawPixel(0,0, GL20.GL_COLOR_BUFFER_BIT);
            pixmap.dispose();
            pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLACK);
            Bresenham2 bresenham = new Bresenham2();
            float weight = 0;
            if(touchX > Gdx.graphics.getWidth()/2) {
                weight = bs.Yaxb(0, 0, touchX - Gdx.graphics.getWidth() / 2, touchY);
                for (GridPoint2 point : bresenham.line(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - (int)(weight*(float) Gdx.graphics.getWidth()/2.0f)))
                {
                    if(point.x%6 == 0 || point.x%7 == 0 || point.x%8 == 0 || point.x%9 == 0)
                        continue;
                    pixmap.drawPixel(point.x, point.y);
                }
            }else if(touchX < Gdx.graphics.getWidth()/2){
                weight = bs.Yaxb(0, 0, Gdx.graphics.getWidth() / 2 - touchX, touchY);
                for (GridPoint2 point : bresenham.line(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight(), 0, Gdx.graphics.getHeight() - (int)(weight*(float) Gdx.graphics.getWidth()/2.0f)))
                {
                    if(point.x%6 == 0 || point.x%7 == 0 || point.x%8 == 0 || point.x%9 == 0)
                        continue;
                    pixmap.drawPixel(point.x, point.y);
                }
            }

            result.draw(pixmap,0,0);
        }
        else{
            pixmap.dispose();
            pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
            result.draw(pixmap,0,0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        statetime += Gdx.graphics.getDeltaTime();
        this.update();

        //batch.disableBlending();
        batch.draw(result,0,0);
        spriteBomb.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
