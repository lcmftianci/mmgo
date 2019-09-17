package com.example.myapplication.BingGo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class BgMenuScreen extends ScreenAdapter {
    SuperBall game;
    OrthographicCamera guiCam;
    Rectangle soundBounds;
    Rectangle playBounds;
    Rectangle highscoresBounds;
    Rectangle helpBounds;
    Vector3 touchPoint;
    public BgMenuScreen (SuperBall game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        soundBounds = new Rectangle(0, 0, 64, 64);
        playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
        highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
        touchPoint = new Vector3();
    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                BgAsserts.playSound(BgAsserts.clickSound);
                game.setScreen(new BgGameScreen(game));
                return;
            }
            if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
                BgAsserts.playSound(BgAsserts.clickSound);
                game.setScreen(new BgHighscoresScreen(game));
                return;
            }
            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
                BgAsserts.playSound(BgAsserts.clickSound);
                game.setScreen(new BgHelpScreen(game));
                return;
            }
            if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
                BgAsserts.playSound(BgAsserts.clickSound);
                BgSettings.soundEnabled = !BgSettings.soundEnabled;
                if (BgSettings.soundEnabled)
                    BgAsserts.music.play();
                else
                    BgAsserts.music.pause();
            }
        }
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);

        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(BgAsserts.backgroundRegion, 0, 0, 320, 480);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(BgAsserts.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
        game.batcher.draw(BgAsserts.mainMenu, 10, 200 - 110 / 2, 300, 110);
        game.batcher.draw(BgSettings.soundEnabled ? BgAsserts.soundOn : BgAsserts.soundOff, 0, 0, 64, 64);
        game.batcher.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    @Override
    public void pause () {
        BgSettings.save();
    }
}
