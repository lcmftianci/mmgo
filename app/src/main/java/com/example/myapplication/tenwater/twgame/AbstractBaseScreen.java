package com.example.myapplication.tenwater.twgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.libgdx.tenwater.TenWaterGame;
//import com.libgdx.tenwater.game.ClassicGameScreen;
//import com.libgdx.tenwater.utils.AssetsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.example.myapplication.tenwater.TenWaterGame;
//import com.libgdx.tenwater.TenWaterGame;

public abstract class AbstractBaseScreen extends ScreenAdapter {

    private final String TAG = AbstractBaseScreen.class.getSimpleName();
    
    TenWaterGame game;
    
    private boolean keyHandled;   // ½«ËùÓÐScreen¶¼ÐèÒª´¦ÀíµÄÒ»Ð©ÊÂÇé¶¼·ÅÔÚ BaseScreenÀàÖÐ
                                  // ±ÈÈç²¶×½·µ»Ø£¬ menu²Ëµ¥event
    public AbstractBaseScreen() { }
    
    public AbstractBaseScreen(TenWaterGame game) {
        this.game = game;
        
        keyHandled = false; // ÒòÎªrenderË¢ÐÂËÙ¶È·Ç³£¿ì£¬Òò´ËÐèÒªÒ»¸ö±äÁ¿À´¸Ä±ä×´Ì¬£¬·ñÔò»á¶à´Î´¦Àí
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    }
    
    @Override
    public void show() { }

    @Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (Gdx.input.isKeyPressed(Keys.BACK)) {
            if (keyHandled) {
                return;
            }
            handledBackPress();
            keyHandled = true;
        } else {
            keyHandled = false;
        }
        
        if (Gdx.input.isKeyPressed(Keys.MENU)) {
            if (keyHandled) {
                return;
            }
            handledMenuPress();
            keyHandled = true;
        } else {
            keyHandled = false;
        }
    }

    protected void handledBackPress() {
        Gdx.app.log(TAG, "press Back");
    }
    
    protected void handledMenuPress() {
        Gdx.app.log(TAG, "press Menu");
    }
    
    public void setTenWaterGame(TenWaterGame game) {
    	this.game = game;
    }
    
    public TenWaterGame geTenWaterGame() {
    	return this.game;
    }
}
