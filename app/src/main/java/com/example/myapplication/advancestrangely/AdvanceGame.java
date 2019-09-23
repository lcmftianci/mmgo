package com.example.myapplication.advancestrangely;

import android.graphics.Bitmap;
import android.widget.ImageButton;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class AdvanceGame implements ApplicationListener {

    AdvanceAsserts asserts;
    MainGameStage mainGameStage;

    ImageButton btnShow;
    BitmapFont font;
    Window dialogWindow;

    SpriteBatch batch;

    @Override
    public void create() {
        asserts = new AdvanceAsserts();
        mainGameStage = new MainGameStage(asserts);

        batch = new SpriteBatch();


        //mainGameStage = new MainGameStage();
        Gdx.input.setInputProcessor(mainGameStage);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        mainGameStage.act();
        mainGameStage.draw();
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
