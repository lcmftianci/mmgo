package com.example.myapplication.advanceactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.example.myapplication.advancestrangely.AdvanceAsserts;

//绘制静态球，撞击一次，子弹增加一个
public class StaticBall extends Actor {

    int inx;
    AdvanceAsserts asserts;
    Sprite staticball ;
    Vector2 curPos;
    ConstantRect cr;
    int curPosX, curPosY;
    Vector2 pos;

    public StaticBall(int x, int y, AdvanceAsserts asserts){
        inx = 0;
        this.asserts = asserts;
        uniinit(x,y);
    }

    //实时更新当前砖块的位置
    public void setCurPos(int x, int y){
        pos.x = x;
        pos.y = y;
        staticball.setPosition(x, y);
    }

    public Vector2 getCurPos(){
        return pos;
    }

    private void uniinit(int x, int y){
        staticball = new Sprite(this.asserts.regionBall);
        staticball.setSize(Gdx.graphics.getWidth()/22, Gdx.graphics.getWidth()/22);
        curPosX = x;
        curPosY = y;
        staticball.setPosition(x, y);
        cr = new ConstantRect();
        cr.setPosRect(x, y, staticball.getWidth(), staticball.getHeight());
        pos = new Vector2();
        pos.x = x;
        pos.y = y;
    }

    public Vector2[] getRect(){
        return cr.getPosRect();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //staticball.setPosition(curPosX,curPosY);
        staticball.draw(batch);
    }
}
