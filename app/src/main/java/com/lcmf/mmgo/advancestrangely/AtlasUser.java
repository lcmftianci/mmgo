package com.lcmf.mmgo.advancestrangely;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import javax.microedition.khronos.opengles.GL10;

public class AtlasUser implements ApplicationListener {

    SpriteBatch batch;
    TextureAtlas atlas;
    Animation AlienAnimation;
    float statetime = 0;
    Sprite region;

    @Override
    public void create() {
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal(""));

        region = atlas.createSprite("");
        region.setPosition(0,0);
        region.setSize(100,100);
        AlienAnimation = new Animation(0.05f, atlas.findRegions(""));
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        statetime = Gdx.graphics.getDeltaTime();
        batch.begin();
        region.draw(batch);

        batch.draw(AlienAnimation.getKeyFrame(statetime,true),270,270,100,100);
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
