package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AdvanceAsserts {

    Texture texture;
    Texture texturebrick;

    TextureRegion regionBrick;
    TextureRegion regionBack;
    TextureRegion regionFloor;
    TextureRegion regionad;
    TextureRegion regionBundle;
    TextureRegion regionReverseBundle;

    public Sound bitSound;
    public Sound clickSound;
    public Music music;


    public AdvanceAsserts(){
        uniinit();
    }

    public void uniinit(){
        texture = new Texture(Gdx.files.internal("atlas/images.png"));
        texturebrick = new Texture(Gdx.files.internal("spirite/wood.jpg"));

        regionBrick = new TextureRegion(texturebrick,22,18,102-22,65-18);
        regionBack = new TextureRegion(texture, 1,1,480,1000);
        regionFloor = new TextureRegion(texture, 481,842,480,160);
        regionad = new TextureRegion(texture, 817,618, 41,53);
        regionBundle = new TextureRegion(texture, 482, 263, 566,666);
        regionReverseBundle = new TextureRegion(texture, 482, 263, 566, 666);
        regionReverseBundle.flip(true, true);

        //加载所有用到的声音
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        //if (Settings.soundEnabled) music.play();
        bitSound = Gdx.audio.newSound(Gdx.files.internal("audio/coin.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("audio/jump.wav"));
    }
}
