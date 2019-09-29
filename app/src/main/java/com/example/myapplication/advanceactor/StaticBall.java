package com.example.myapplication.advanceactor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.example.myapplication.advancestrangely.AdvanceAsserts;

//绘制静态球，撞击一次，子弹增加一个
public class StaticBall extends Actor {

    int inx;
    AdvanceAsserts asserts;
    public StaticBall(int x, int y, AdvanceAsserts asserts){
        inx = 0;
        this.asserts = asserts;
        init(x,y);
    }

    private void init(int x, int y){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
