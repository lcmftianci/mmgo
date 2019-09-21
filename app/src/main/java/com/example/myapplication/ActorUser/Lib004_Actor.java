package com.example.myapplication.ActorUser;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.microedition.khronos.opengles.GL10;

public class Lib004_Actor extends ApplicationAdapter{
private final static String TAG = "Lib004_Actor";
    BitmapFont font;
    Stage stage;
    MyActor actor;
    //String strShow;
    int count;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        stage = new Stage();
        font = new BitmapFont();
        font.setColor( Color.DARK_GRAY );
        
        actor = new MyActor();
        stage.addActor(actor);
        actor.setPosition( stage.getWidth()/2-actor.getWidth()/2, stage.getHeight()/2-actor.getHeight()/2 );
        
        count = 0;
        actor.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                count++;
                Log.d(TAG, "touch : " + count);
                return true;
            }            
        });
        
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        stage.act();
        stage.draw();
        
        //SpriteBatch batch = stage.getSpriteBatch();
        Batch batch = stage.getBatch();
        batch.begin();
        //batch.draw( font, actor.getX(),  );
        font.draw( batch, "You have clicked  " + count + "  times!", actor.getX()-25, actor.getY()-20 );
        batch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}