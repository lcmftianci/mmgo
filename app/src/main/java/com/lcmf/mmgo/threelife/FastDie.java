package com.lcmf.mmgo.threelife;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.microedition.khronos.opengles.GL10;

public class FastDie implements ApplicationListener {

    private static String TAG = "FastDie";
    Stage stage;
    Sprite sprite;
    SpriteBatch spriteBatch;
    Texture background;
    Texture airPlane;
    TextureAtlas atlas;
    Touchpad touchpad;  //控制面板
    Touchpad.TouchpadStyle touchpadStyle;
    TextureRegionDrawable knobRegion;
    TextureRegionDrawable touchBackground;
    Texture textureBack;
    Texture textureFront;
    Texture killer;     //精灵
    int speed;
    int speedX;
    int speedY;
    int x = 150;
    int y = 150;
    VulThread vtThread;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("mubu/mubu.jpg"));
        airPlane = new Texture(Gdx.files.internal("shoot/enemy1.png"));
        //atlas = new TextureAtlas(Gdx.files.internal(""));
        //sprite = atlas.createSprite("");
        //sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sprite = new Sprite(airPlane, 0,0,49,37);
        sprite.setSize(Gdx.graphics.getWidth()/15*4/3, Gdx.graphics.getWidth()/15);
        sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        textureBack = new Texture(Gdx.files.internal("controller/panzi.jpg"));
        textureFront = new Texture(Gdx.files.internal("controller/pn.jpg"));
        killer = new Texture(Gdx.files.internal("shoot/bomb.png"));

        touchBackground = new TextureRegionDrawable(new TextureRegion(textureBack, 50, 50, 400,400));
        knobRegion = new TextureRegionDrawable(new TextureRegion(textureFront, 170, 170, 155,155));
        touchpadStyle = new Touchpad.TouchpadStyle(touchBackground, knobRegion);
        touchpad = new Touchpad(15, touchpadStyle);
        touchpad.setBounds(0,0, 300,300);
        speed = 10;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(touchpad);
        speedX = 0;
        vtThread = new VulThread();
        vtThread.start();
    }

    @Override
    public void resize(int i, int i1) {

    }

    public void updateAipPlaneInfo(){
        //sprite.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        sprite.setPosition(x, y);
    }

    public void update(){
        if(touchpad.isTouched()){
            x += touchpad.getKnobPercentX()*speed;
            y += touchpad.getKnobPercentY()*speed;

            speedX = (int)(touchpad.getKnobPercentX()*speed);
            speedY = (int)(touchpad.getKnobPercentY()*speed);
            vtThread.setVulX(speedX);
            vtThread.setVulY(speedY);
            Log.d(TAG, " speedX: " + speedX + " speedY:" + speedY);

            if(x > Gdx.graphics.getWidth() - (int)sprite.getWidth()){
                x = Gdx.graphics.getWidth() - (int)sprite.getWidth();
            }
            if(x < 0){
                x = 0;
            }
            if(y > Gdx.graphics.getHeight() - (int)sprite.getHeight()){
                y = Gdx.graphics.getHeight() - (int)sprite.getHeight();
            }
            if(y < 0){
                y = 0;
            }
        }else{
            x += vtThread.getVulX();
            y += vtThread.getVulY();
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        this.update();
        updateAipPlaneInfo();

        spriteBatch.begin();
        spriteBatch.draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.draw(spriteBatch);
        spriteBatch.end();

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
