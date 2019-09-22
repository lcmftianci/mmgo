package com.example.myapplication.mylecture.stageandactor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.microedition.khronos.opengles.GL10;

public class GamePad implements ApplicationListener {

    Stage stage;        //舞台
    Texture texture;    //控制面板图片
    Texture killer;     //精灵
    TextureRegionDrawable knobRegion;
    TextureRegionDrawable background;

    Touchpad touchpad;  //控制面板
    Touchpad.TouchpadStyle touchpadStyle;

    SpriteBatch batch;
    int speed = 3;
    int x = 150;
    int y = 150;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal(""));
        killer = new Texture(Gdx.files.internal(""));

        background = new TextureRegionDrawable(new TextureRegion(texture, 0, 0, 128,128));
        knobRegion = new TextureRegionDrawable(new TextureRegion(texture, 128, 0, 128,128));
        touchpadStyle = new Touchpad.TouchpadStyle(background, knobRegion);
        touchpad = new Touchpad(15, touchpadStyle);

        stage.addActor(touchpad);
    }

    public void update(){
        if(touchpad.isTouched()){
            x += touchpad.getKnobPercentX()*speed;
            y += touchpad.getKnobPercentY()*speed;
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        this.update();

        batch.begin();
        batch.draw(killer,x,y);
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
