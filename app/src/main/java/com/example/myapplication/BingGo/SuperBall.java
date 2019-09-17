package com.example.myapplication.BingGo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SuperBall extends Game {
    // used by all screens
    public SpriteBatch batcher;

    @Override
    public void create () {
        batcher = new SpriteBatch();
        BgSettings.load();
        BgAsserts.load();
        setScreen(new BgMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
