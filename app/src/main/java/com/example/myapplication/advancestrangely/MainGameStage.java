package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainGameStage extends Stage {

    SpriteBatch batch;
    AdvanceAsserts asserts;

    public MainGameStage() {
        super();
    }
    public MainGameStage(AdvanceAsserts asserts){
        super();
        this.asserts = asserts;
    }

    public void update(){
        batch.draw(asserts.regionBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
