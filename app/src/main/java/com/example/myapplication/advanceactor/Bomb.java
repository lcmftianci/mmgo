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

    float mWeight;
    boolean bTouch;
    boolean bCanGet;
    float mTouchX;
    float mTouchY;

    boolean bBubble;

    int xPos;
    int yPos;

    public Bomb(int x, int y){
        this.xPos = x;
        this.yPos = y;

        textureBomb = new Texture(Gdx.files.internal("icon/newicon.png"));
        //spriteBomb = new Sprite(textureBomb, 138, 158, 306 - 138,378 - 157);
        spriteBomb = new Sprite(textureBomb, 88*2, 78*2, (387 - 88)*2,(437 - 78)*2);
        spriteBomb.setSize(Gdx.graphics.getWidth()/8,Gdx.graphics.getWidth()/8);
        //spriteBomb.setOrigin(spriteBomb.getWidth()/2, spriteBomb.getHeight()/4);
        spriteBomb.setOrigin(spriteBomb.getWidth()/2, 0);
        spriteBomb.setPosition(this.xPos - spriteBomb.getWidth()/2, this.yPos);
        statetime = 0.0f;

        pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        bs = new BallSpring();
        result = new Texture(pixmap);
        spriteBatch = new SpriteBatch();

        mTouchX = 0;
        mTouchY = 0;
        bTouch = false;
        bCanGet = false;
        bBubble = false;
        mWeight = 0;
    }

    //更改大炮的方向
    public void update(){
        if(bBubble)
            return;
        if(Gdx.input.isTouched()){
            //绘制大炮
            //x坐标
            int touchX = Gdx.input.getX();
            //触摸点相对于地面的位置
            int touchY =  Gdx.graphics.getHeight() - Gdx.input.getY() - this.yPos;

            //触摸点 左上角为（0，0），绘制图形左下角为（0，0）

            Log.d(TAG, "==>> tourchX:" + touchX + " touchY:" + touchY + " inputY:" + Gdx.input.getY());

            mTouchX = touchX;
            mTouchY = touchY;

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

            //int originPosX = this.xPos;//Gdx.graphics.getWight();
            //int originPosY = Gdx.graphics.getHeight() - this.yPos;
            //绘制描绘线,这里需要修改绘制曲线位置不准确，无法反射
            //pixmap.drawPixel(0,0, GL20.GL_COLOR_BUFFER_BIT);
            pixmap.dispose();
            pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLACK);
            Bresenham2 bresenham = new Bresenham2();
            float weight = 0;

            //pixmap的绘制曲线（0，0）点在左上角
            if(touchX > Gdx.graphics.getWidth()/2) {
                weight = bs.Yaxb(0, 0, touchX - Gdx.graphics.getWidth()/2, touchY);
                for (GridPoint2 point : bresenham.line(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-this.yPos, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - this.yPos -(int)(weight*(float) Gdx.graphics.getWidth()/2.0)))
                {
                    if(point.x%6 == 0 || point.x%7 == 0 || point.x%8 == 0 || point.x%9 == 0)
                        continue;
                    //pixmap.drawPixel(point.x, point.y);
                    pixmap.drawCircle(point.x, point.y, 2);
                }
            }else if(touchX < Gdx.graphics.getWidth()/2){
                weight = bs.Yaxb( 0, 0,  Gdx.graphics.getWidth()/2 - touchX, touchY);
                for (GridPoint2 point : bresenham.line(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-this.yPos,0, Gdx.graphics.getHeight() - this.yPos - (int)(weight*(float) Gdx.graphics.getWidth()/2.0f)))
                {
                    if(point.x%6 == 0 || point.x%7 == 0 || point.x%8 == 0 || point.x%9 == 0)
                        continue;
                    //pixmap.drawPixel(point.x, point.y);
                    pixmap.drawCircle(point.x, point.y, 2);
                }
            }
            mWeight = weight;
            result.draw(pixmap,0,0);
            bTouch = true;
        } else{
            //Log.d(TAG, "get no touch");
            pixmap.dispose();
            pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
            result.draw(pixmap,0,0);
            if(bTouch)
                bCanGet = true;   //这里逻辑有错误,点击时直接就被重置并绘制
        }
    }

    public boolean getState(){
        return bCanGet;
    }

    public boolean getTouchState(){
        return bTouch;
    }

    public void setBubble(boolean bBubble){
        this.bBubble = bBubble;
    }

    public float[] AlreadyGet(){
        bCanGet = false;
        bTouch = false;
        return new float[]{mTouchX,mTouchY,mWeight};
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
