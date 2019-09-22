package com.example.myapplication.mylecture.stageandactor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.microedition.khronos.opengles.GL10;

public class MarioGame implements ApplicationListener {

    Image image;
    MarioActor marioActor;
    Stage stage;


    @Override
    public void create() {
        image = new Image(new Texture(Gdx.files.internal("mario/background.png")));
        image.setPosition(0,170);

        stage = new Stage();
        stage.setViewport(new Viewport() {
        });
        Gdx.input.setInputProcessor(stage);

        marioActor = new MarioActor(100,190);

        stage.addActor(image);
        stage.addActor(marioActor);
        stage.addActor(marioActor.buttonR);
        stage.addActor(marioActor.buttonL);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
