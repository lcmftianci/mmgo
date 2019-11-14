package com.lcmf.mmgo.threelife;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import javax.microedition.khronos.opengles.GL10;

public class FastDie implements ApplicationListener {

    Sprite sprite;
    SpriteBatch spriteBatch;
    Texture background;
    Texture airPlane;
    TextureAtlas atlas;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("mubu/mubu.jpg"));
        airPlane = new Texture(Gdx.files.internal("shoot/enemy1.png"));
        //atlas = new TextureAtlas(Gdx.files.internal(""));
        //sprite = atlas.createSprite("");
        //sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sprite = new Sprite(airPlane, 0,0,49,37);
        sprite.setSize(Gdx.graphics.getWidth()/15*4/3, Gdx.graphics.getWidth()/15);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        sprite.draw(spriteBatch);
        spriteBatch.end();
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
