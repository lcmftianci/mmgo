package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AdvanceAsserts {

    Texture texture;
    TextureRegion regionBack;
    TextureRegion regionFloor;
    TextureRegion regionad;
    TextureRegion regionBundle;
    TextureRegion regionReverseBundle;



    public AdvanceAsserts(){
        uniinit();
    }

    public void uniinit(){
        texture = new Texture(Gdx.files.internal("atlas/images.png"));
        regionBack = new TextureRegion(texture, 1,1,480,1000);

        regionFloor = new TextureRegion(texture, 481,842,480,160);

        regionad = new TextureRegion(texture, 817,618, 41,53);

        regionBundle = new TextureRegion(texture, 482, 263, 566,666);
        regionReverseBundle = new TextureRegion(texture, 482, 263, 566, 666);
        regionReverseBundle.flip(true, true);
    }
}
