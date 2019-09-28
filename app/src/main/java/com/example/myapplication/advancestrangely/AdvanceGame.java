package com.example.myapplication.advancestrangely;

import android.util.Log;

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
    GameOverStage gameOverStage;

    //按钮测试例子
    ImageButton btnShow;
    ImageButton btnOk;
    ImageButton btnCaccel;
    BitmapFont font;
    Window dialogWindow;
    Texture texture;
    SpriteBatch batch;
    boolean bGameOver;

    private final static String TAG = "AdvanceGame";

    public void SelectStageRender(){
        if(MarioConstants.StageFlag == MarioConstants.StageStartOn){
            Gdx.input.setInputProcessor(gameOverStage);
            gameOverStage.act();
            gameOverStage.draw();
        }else if(MarioConstants.StageFlag == MarioConstants.StageGameOn){
            Gdx.input.setInputProcessor(mainGameStage);
            mainGameStage.act();
            mainGameStage.draw();
        }
//        }else if(MarioConstants.StageFlag == MarioConstants.StageShopOn){
//            Gdx.input.setInputProcessor(shopStage);
//            shopStage.act();
//            shopStage.draw();
//        }
    }

    @Override
    public void create() {
        asserts = new AdvanceAsserts();
        mainGameStage = new MainGameStage(asserts);
        gameOverStage = new GameOverStage();

        //按钮测试例子
        font = new BitmapFont(Gdx.files.internal("font/bitmap_font.fnt"), Gdx.files.internal("font/bitmap_font.png"), false);
        batch = new SpriteBatch();
        this.setButton();
        this.setListner();
        this.setWindow();
        this.bGameOver = false;
        //mainGameStage.addActor(btnShow);

        Gdx.input.setInputProcessor(mainGameStage);
    }

    public void setButton(){
        texture = new Texture(Gdx.files.internal("button/button.png"));
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
        TextureRegionDrawable winDrable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("background.png"))));
        Window.WindowStyle style = new Window.WindowStyle(font, Color.BLACK, winDrable);
        dialogWindow = new Window("Game Over", style);
        dialogWindow.setWidth(Gdx.graphics.getWidth()/5 * 3);
        dialogWindow.setHeight(Gdx.graphics.getHeight()/5);

        dialogWindow.setPosition(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/5 *2);
        dialogWindow.setModal(true);

        Log.d(TAG, "-=-= y1:" + dialogWindow.getY());
        Log.d(TAG, "-=-= x:" + (int)Gdx.graphics.getWidth()/5);
        Log.d(TAG, "-=-= x:" + (int)Gdx.graphics.getWidth()/5 +" " + 10);
        Log.d(TAG, "-=-= y:" + (int)Gdx.graphics.getHeight()/5 *2);
        Log.d(TAG, "-=-= y:" + dialogWindow.getY() + " " + (int)Gdx.graphics.getHeight()/5/2);

        btnOk.setSize(Gdx.graphics.getWidth()/5 * 3/2, Gdx.graphics.getHeight()/5/2);
        btnCaccel.setSize(Gdx.graphics.getWidth()/5 * 3/2, Gdx.graphics.getHeight()/5/2);
        btnOk.setPosition(10,10);
        btnCaccel.setPosition(Gdx.graphics.getWidth()/5 * 3/3 + 10,10);

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

        mainGameStage.update();
        if(mainGameStage.checkGameOver() && bGameOver == false){
            bGameOver = true;
            mainGameStage.addActor(dialogWindow);
        }
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
