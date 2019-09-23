package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainGameStage extends Stage {

    SpriteBatch batch;
    AdvanceAsserts asserts;
    Image background;
    Texture texture;
    TextureRegion regionBack;

    public MainGameStage() {
        super();
        texture = new Texture(Gdx.files.internal("atlas/images.png"));
        regionBack = new TextureRegion(texture, 1,1,480,1000);
        background = new Image(regionBack);
        background.setPosition(0,0);
        this.addActor(background);
    }
    public MainGameStage(AdvanceAsserts asserts){
        this.asserts = asserts;
        background = new Image(asserts.regionBack);
        //background.setScale(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.setPosition(0,0);
        this.addActor(background);
    }

    public void update(){
        //batch.draw(asserts.regionBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
