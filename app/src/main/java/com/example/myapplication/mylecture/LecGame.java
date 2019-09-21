package com.example.myapplication.mylecture;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class LecGame implements ApplicationListener {
    Texture texture;
    TextureRegion textureRegion;
    SpriteBatch batch;
    Sprite sprite;
    @Override
    public void create() {
        texture = new Texture(Gdx.files.internal("data/panda.jpg"));
        textureRegion = new TextureRegion(texture, 110, 240, 280, 269);
        batch = new SpriteBatch();
        sprite = new Sprite();
        sprite.setRegion(textureRegion);
        sprite.setOrigin(20, 20);
        sprite.setRotation(20);
        sprite.setPosition(20,20);
        sprite.setAlpha(2);
        sprite.setColor(1,0,1,1);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);   //设置背景颜色为白色
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);         //清屏

        batch.begin();
        //batch.draw(textureRegion,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batch.draw(texture,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.draw(batch);
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
