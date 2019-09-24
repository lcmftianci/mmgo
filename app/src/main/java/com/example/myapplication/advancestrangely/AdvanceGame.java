package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.microedition.khronos.opengles.GL10;

public class AdvanceGame implements ApplicationListener {

    AdvanceAsserts asserts;
    MainGameStage mainGameStage;

    //按钮测试例子
    ImageButton btnShow;
    ImageButton btnOk;
    ImageButton btnCaccel;
    BitmapFont font;
    Window dialogWindow;
    Texture texture;
    SpriteBatch batch;

    @Override
    public void create() {
        asserts = new AdvanceAsserts();
        mainGameStage = new MainGameStage(asserts);

        //按钮测试例子
//        font = new BitmapFont(Gdx.files.internal(""), Gdx.files.internal(""), false);
//        batch = new SpriteBatch();
//        this.setButton();
//        this.setListner();
//        this.setWindow();
//        mainGameStage.addActor(btnShow);

        Gdx.input.setInputProcessor(mainGameStage);
    }

    public void setButton(){
        texture = new Texture(Gdx.files.internal(""));
        TextureRegion[][] split = TextureRegion.split(texture, 64,64);
        TextureRegion[] regions = new TextureRegion[6];

        regions[0] = split[0][0];
        regions[1] = split[0][1];

        regions[2] = split[0][2];
        regions[3] = split[0][3];

        regions[4] = split[1][0];
        regions[5] = split[1][1];

        TextureRegionDrawable btn_show_up = new TextureRegionDrawable(regions[0]);
        TextureRegionDrawable btn_show_down = new TextureRegionDrawable(regions[1]);
        TextureRegionDrawable btn_ok_up = new TextureRegionDrawable(regions[2]);
        TextureRegionDrawable btn_ok_down = new TextureRegionDrawable(regions[3]);
        TextureRegionDrawable btn_caccel_up = new TextureRegionDrawable(regions[4]);
        TextureRegionDrawable btn_caccel_down = new TextureRegionDrawable(regions[5]);

        btnShow = new ImageButton(btn_show_up,btn_show_down);
        btnOk = new ImageButton(btn_ok_up,btn_ok_down);
        btnCaccel = new ImageButton(btn_caccel_up,btn_caccel_down);
    }

    public void setListner(){
        btnShow.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainGameStage.addActor(dialogWindow);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btnOk.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btnCaccel.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dialogWindow.remove();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void setWindow(){
        TextureRegionDrawable winDrable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(""))));
        Window.WindowStyle style = new Window.WindowStyle(font, Color.BLACK, winDrable);
        dialogWindow = new Window("Game", style);
        dialogWindow.setWidth(Gdx.graphics.getWidth()/1.5f);
        dialogWindow.setHeight(Gdx.graphics.getHeight()/1.5f);

        dialogWindow.setPosition(100,100);
        dialogWindow.setModal(true);

        btnOk.setPosition(Gdx.graphics.getWidth()/10.0f,0);
        btnCaccel.setPosition(Gdx.graphics.getHeight()/10.0f,0);

        dialogWindow.addActor(btnOk);
        dialogWindow.addActor(btnCaccel);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

//        batch.begin();
//        font.draw(batch, "touch me", 100, 100);
//        batch.end();

        mainGameStage.act();
        mainGameStage.draw();
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
