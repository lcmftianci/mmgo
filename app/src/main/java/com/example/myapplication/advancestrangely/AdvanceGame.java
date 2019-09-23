package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class AdvanceGame implements ApplicationListener {

    AdvanceAsserts asserts;
    MainGameStage mainGameStage;

    @Override
    public void create() {
        asserts = new AdvanceAsserts();
        mainGameStage = new MainGameStage(asserts);
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
