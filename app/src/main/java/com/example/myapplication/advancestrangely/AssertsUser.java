package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class AssertsUser implements ApplicationListener {

    AssetManager manager;
    BitmapFont font;
    SpriteBatch batch;
    BitmapFont font2;

    TextureAtlas tex2;
    Texture tex1;
    Texture progress_BG;
    Texture progress_TF;

    TextureRegion region;
    float progress = 0;
    boolean diagnosed = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal(""), false);

        manager = new AssetManager();

        progress_BG = new Texture("");
        progress_TF = new Texture("");

        region = new TextureRegion(progress_BG, 0, 0, 512,64);
        load();
    }

    private void load(){
        tex1 = new Texture("");

        tex2 = new TextureAtlas(Gdx.files.internal(""));

        font2 = new BitmapFont(Gdx.files.internal(""), false);

        manager.load("", Texture.class);
        manager.load("", TextureAtlas.class);
        manager.load("", BitmapFont.class);
    }

    private void unload(){
        tex1.dispose();
        tex2.dispose();
        font2.dispose();

        manager.unload("");
        manager.unload("");
        manager.unload("");
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        boolean result = manager.update();
        if(result&!diagnosed){
            diagnosed = true;
            unload();
            load();
            diagnosed = false;
        }

        batch.begin();
        if(manager.isLoaded(""))
            batch.draw(manager.get("", Texture.class), 50,50);

        if(manager.isLoaded(""))
            manager.get("", Sound.class).play();

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
