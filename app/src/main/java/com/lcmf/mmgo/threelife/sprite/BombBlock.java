package com.lcmf.mmgo.threelife.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BombBlock implements IGameObject {

    Vector2 pos;
    Vector2 rect;
    Sprite bomb;
    boolean bleft = false;
    boolean btop = true;
    int xv = Gdx.graphics.getWidth()/160;
    int yv = Gdx.graphics.getHeight()/160;

    int sizeX = 0;
    int sizeY = 0;

    public Vector2 getRect(){
        return rect;
    }

    float getHeight(){
        return bomb.getHeight();
    }

    public  Sprite getSprite(){
        return bomb;
    }

    void setPosition(float x, float y){
        bomb.setPosition(x,y);
    }

    float getWidth(){
        return bomb.getWidth();
    }

    public void setSize(float height, float width){
        bomb.setSize(height, width);
    }

    public BombBlock(Sprite se,int posX, int posY, int sizeX, int sizeY) {
        pos = new Vector2(posX, posY);
        rect = new Vector2(sizeX,sizeY);
        bomb = se;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        bomb.setSize(sizeX, sizeY);
    }

    public void update(){
        if(pos.y >= Gdx.graphics.getHeight() - bomb.getHeight() - 300){
            //asserts.clickSound.play(1);
            btop=false;
        }

        if(pos.y < 300){
            //asserts.clickSound.play(1);
            btop=true;
        }

        if(pos.x >= Gdx.graphics.getWidth() - bomb.getWidth()){
            //asserts.clickSound.play(1);
            bleft=true;
        }

        if(pos.x < 0){
            //asserts.clickSound.play(1);
            bleft=false;
        }

        if(bleft){
            pos.x -= xv;
        }else{
            pos.x += xv;
        }

        if(btop){
            pos.y += yv;
        }else {
            pos.y -= yv;
        }

        bomb.setPosition(pos.x, pos.y);
    }

    public Vector2 getPos(){
        return pos;
    }
}
