package com.example.myapplication.mylecture.stageandactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MarioGameStage extends Stage {
    Texture texture;
    TextureRegion backgropRegion;
    Image backgrop;

    TextureRegion mashroomRegion;
    Image mashroom;

    MarioActor marioActor;

    public void init(){
        texture = new Texture(Gdx.files.internal("mario/shop.jpg"));

        backgropRegion = new TextureRegion(texture, 512,256,512,128);
        backgrop = new Image(backgropRegion);

        mashroomRegion = new TextureRegion(texture, 512,256,512,128);
        mashroom = new Image(mashroomRegion);

        marioActor = new MarioActor(10,10);
        backgrop.setPosition(0,170);
        mashroom.setPosition(0,20);

        this.addActor(backgrop);
        this.addActor(marioActor);
        this.addActor(marioActor.buttonL);
        this.addActor(marioActor.buttonR);
        this.addActor(mashroom);

        mashroom.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MarioConstants.StageFlag = MarioConstants.StageShopOn;
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
