package com.example.myapplication.BingGo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BgAsserts {
    public static Texture background;
    public static TextureRegion backgroundRegion;

    public static Texture items;
    public static Texture bingo;

    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static TextureRegion arrow;
    public static TextureRegion pause;
    public static TextureRegion spring;
    public static TextureRegion castle;
    public static BgAnimation coinAnim;
    public static BgAnimation bobJump;
    public static BgAnimation bobFall;
    public static TextureRegion bobHit;
    public static BgAnimation squirrelFly;
    public static TextureRegion platform;
    public static BgAnimation brakingPlatform;

    public static TextureRegion ball;

    public static BitmapFont font;

    public static Music music;
    public static Sound jumpSound;
    public static Sound highJumpSound;
    public static Sound hitSound;
    public static Sound coinSound;
    public static Sound clickSound;

    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load () {
        background = loadTexture("bingo/bgbackground.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);

        bingo = loadTexture("bingo/bgspirit.png");
        ball = new TextureRegion(bingo, 28, 28, 56, 56);

        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);

        music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        if (BgSettings.soundEnabled) music.play();
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
        highJumpSound = Gdx.audio.newSound(Gdx.files.internal("data/highjump.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));

        items = loadTexture("data/items.png");
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
        pause = new TextureRegion(items, 64, 64, 64, 64);
        //ball = new TextureRegion(items, 64, 0, 64, 64);
    }

    public static void playSound (Sound sound) {
        if (BgSettings.soundEnabled) sound.play(1);
    }
}
