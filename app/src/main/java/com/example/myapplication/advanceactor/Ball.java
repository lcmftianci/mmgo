package com.example.myapplication.advanceactor;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor {
    Texture texture;
    Sprite spriteball;
    TextureRegion region;

    int xd;
    int yd;
    float weight;
    int xv;
    int yv;

    int curPosX,curPosY;

    boolean bleft,btop;

    boolean brun;

    private final static String TAG = "BALL";

    public Ball(){
        init();
    }

    public void init(){
        texture = new Texture(Gdx.files.internal("ball.jpg"));
        region = new TextureRegion(texture, 5,5,15,15);
        spriteball = new Sprite(region);
        //spriteball.setScale(100,100);
        spriteball.setSize(Gdx.graphics.getWidth()/13, Gdx.graphics.getWidth()/13);
        xd = -10;
        yd = -10;
        xv = Gdx.graphics.getWidth()/100;
        yv = Gdx.graphics.getHeight()/40;
        brun = false;

        bleft = true;
        btop = true;

        curPosX = Gdx.graphics.getWidth()/2;
        curPosY = 0;
    }

    public void setvum(int x, int y,float weight){
        this.xd = x;
        this.yd = y;
        this.weight = weight;
        brun = true;
        this.yv = (int)(weight*(float) xv);

        bleft = true;
        btop = true;
    }

    public boolean isrun(){
        return brun;
    }

    public void update(int x, int y){
        spriteball.setPosition(x - Gdx.graphics.getWidth()/13/2, y - Gdx.graphics.getWidth()/13/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //batch.draw(spriteball,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        //1.从 (width/2,0)出发，以wight方向移动，每次移动(xv，xv*wight)距离  //这种方法，有点问题每次都要重新计算权重
        //2.从起始点出发，每次在x,y方向移动xv,yv方向，碰到边界如何反弹
        if(brun)
        {
            if(bleft){
                curPosX -= xv;
            }else{
                curPosX += xv;
            }

            if(btop){
                curPosY += yv;
            }else {
                curPosY -= yv;
            }

            if(curPosY > Gdx.graphics.getHeight())
                btop=false;

            if(curPosY < 0)
                btop=true;

            if(curPosX > Gdx.graphics.getWidth())
                bleft=true;

            if(curPosX < 0)
                bleft=false;

            Log.d(TAG, "x:" + curPosX + " y:" + curPosY + " vx:" + this.xv + " vy:" + this.yv);

            spriteball.setPosition(curPosX,curPosY);

            spriteball.draw(batch);
        }
    }
}
