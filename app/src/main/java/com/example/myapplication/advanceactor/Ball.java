package com.example.myapplication.advanceactor;

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

    public Ball(){
        init();
    }

    public void init(){
        texture = new Texture(Gdx.files.internal("ball.jpg"));
        region = new TextureRegion(texture, 5,5,15,15);
        spriteball = new Sprite(region);
        //spriteball.setScale(100,100);
        spriteball.setSize(Gdx.graphics.getWidth()/13, Gdx.graphics.getWidth()/13);
    }

    public void update(int x, int y){
        spriteball.setPosition(x - Gdx.graphics.getWidth()/13/2, y - Gdx.graphics.getWidth()/13/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //batch.draw(spriteball,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        spriteball.draw(batch);
    }
}
