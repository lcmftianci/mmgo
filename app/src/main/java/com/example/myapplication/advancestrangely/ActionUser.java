package com.example.myapplication.advancestrangely;

import android.media.audiofx.DynamicsProcessing;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.microedition.khronos.opengles.GL10;

public class ActionUser implements ApplicationListener, InputProcessor {

    Texture starTexture;
    Texture ballTexture;
    Image background;

    Stage stage;
    Image image;
    float delta;

    @Override
    public void create() {
        stage = new Stage();
        starTexture = new Texture(Gdx.files.internal("ball.jpg"));
        ballTexture = new Texture(Gdx.files.internal("bubble.png"));

        background = new Image(new TextureRegion(new Texture(Gdx.files.internal("background.jpg")), 0, 0, 100,100));
        background.setWidth(Gdx.graphics.getWidth());
        background.setHeight(Gdx.graphics.getHeight());
        stage.addActor(background);
        Gdx.input.setInputProcessor(this);
    }

    public void createStar(boolean star, int x, int y, int size){
        if(star == true){
            image = new Image(starTexture);
        }else {
            image = new Image(ballTexture);
        }

        this.setAction(image);
        image.setPosition(x-size/2,y-size/2);
        image.setSize(size,size);
        stage.addActor(image);
    }

    public void setAction(final Image image){
        float x = image.getX();
        float y = image.getY();

        float duration = MathUtils.random(3,10);
        //动作实例化
        MoveToAction moveto = Actions.moveTo(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),duration);

        float scale = MathUtils.random(0.5f, 2.0f);
        ScaleToAction scaleto = Actions.scaleTo(scale,scale,duration);

        float rotation = MathUtils.random(0.5f, 2.0f);
        RotateToAction rotateto = Actions.rotateTo(rotation, duration);

        Action endaction = Actions.run(new Runnable() {
            @Override
            public void run() {
                System.out.println("Action is Over");
            }
        });

        SequenceAction alpha = Actions.sequence(Actions.fadeIn(duration), Actions.fadeOut(duration), endaction);

        ParallelAction parallel = Actions.parallel(moveto, scaleto, rotateto, endaction, alpha);
        RepeatAction repeatAction = Actions.repeat(3, parallel);
        RepeatAction foreverAction = Actions.forever(repeatAction);
        image.addAction(parallel);
        image.addAction(foreverAction);
        image.addAction(repeatAction);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1,1,1,1);
        delta += Gdx.graphics.getDeltaTime();
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

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int i2) {
        int roll = (int)(delta * 1000000);
        if(roll%4==0){
            createStar(MathUtils.randomBoolean(), screenX,Gdx.graphics.getHeight() - screenY, MathUtils.random(10,20));
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
