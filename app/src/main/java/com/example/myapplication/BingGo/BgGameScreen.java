package com.example.myapplication.BingGo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class BgGameScreen extends ScreenAdapter {

    SuperBall game;
    OrthographicCamera guiCam;
    BgWorld world;
    BgWorld.BgWorldListener worldListener;
    BgWorldRenderer renderer;
    String scoreString;
    int state;
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;

    public BgGameScreen(SuperBall game){
        this.game = game;
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);

        worldListener = new BgWorld.BgWorldListener() {
            @Override
            public void jump () {
                BgAsserts.playSound(BgAsserts.jumpSound);
            }

            @Override
            public void highJump () {
                BgAsserts.playSound(BgAsserts.highJumpSound);
            }

            @Override
            public void hit () {
                BgAsserts.playSound(BgAsserts.hitSound);
            }

            @Override
            public void coin () {
                BgAsserts.playSound(BgAsserts.coinSound);
            }
        };

        world = new BgWorld(worldListener);
        renderer = new BgWorldRenderer(game.batcher, world);

        scoreString = "Hello World";
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.enableBlending();
        game.batcher.begin();

        game.batcher.draw(BgAsserts.pause, 320 - 64, 480 - 64, 64, 64);
        BgAsserts.font.draw(game.batcher, scoreString, 16, 480 - 20);
        //game.batcher.draw(BgAsserts.ball, 20, 20, 20, 20);

        game.batcher.end();
    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;
        world.update(Gdx.input.getX(), Gdx.input.getY());
    }

    @Override
    public void render (float delta) {
        update(delta);
        draw();
    }

    @Override
    public void pause () {
        if (state == GAME_RUNNING) state = GAME_PAUSED;
    }
}
