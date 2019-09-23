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
    Texture textureMiddle;//控制面板远点
    Texture killer;     //精灵
    TextureRegion region;
    TextureRegionDrawable knobRegion;
    TextureRegionDrawable background;

    Touchpad touchpad;  //控制面板
    Touchpad.TouchpadStyle touchpadStyle;

    SpriteBatch batch;
    //int speed = Gdx.graphics.getHeight()*Gdx.graphics.getWidth()/Math.sqrt((double)(Math.pow((double)Gdx.graphics.getWidth(),(double)2) + Math.pow((double)Gdx.graphics.getHeight(),(double) 2)));
    int speed = 6;
    int x = 150;
    int y = 150;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("joystick/joystick1.png"));
        textureMiddle = new Texture(Gdx.files.internal("joystick/smallbg.png"));
        killer = new Texture(Gdx.files.internal("joystick/plane.png"));

        background = new TextureRegionDrawable(new TextureRegion(texture, 0, 0, 131,131));
        knobRegion = new TextureRegionDrawable(new TextureRegion(textureMiddle, 0, 0, 50,50));
        touchpadStyle = new Touchpad.TouchpadStyle(background, knobRegion);
        touchpad = new Touchpad(15, touchpadStyle);
        touchpad.setSize(Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
        //batch = new SpriteBatch();
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
        batch.draw(killer,x,y,Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
        batch.end();
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
