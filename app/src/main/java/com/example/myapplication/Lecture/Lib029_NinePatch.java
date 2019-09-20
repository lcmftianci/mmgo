package com.example.myapplication.Lecture;

import android.util.Log;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;

import javax.microedition.khronos.opengles.GL10;

public class Lib029_NinePatch extends ApplicationAdapter{

    private final String TAG = "Lib029_NinePatch";
    NinePatch patch;
    SpriteBatch batch;
    TextureRegion region;
    int width;
    int height;

    int vLeft, vRight, vTop, vBottom;
    float NdeltaX, NdeltaY;
    int add = 0;

    GestureDetector.GestureAdapter gestureAdapter = new GestureDetector.GestureAdapter(){

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            // TODO Auto-generated method stub
            if( velocityX > 0 ){
                Log.d(TAG, "fling right");
            }
            else{
                Log.d(TAG, "fling left" );
            }

            return super.fling(velocityX, velocityY, button);
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            // TODO Auto-generated method stub
            Log.d(TAG, "pan" );
            NdeltaX = deltaX;
            NdeltaY = deltaY;
            return super.pan(x, y, deltaX, deltaY);
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            // TODO Auto-generated method stub
            Log.d(TAG, "pan stop" );
            NdeltaX = 0;
            NdeltaY = 0;
            return super.panStop(x, y, pointer, button);
        }
    };

    GestureDetector detector = new GestureDetector( gestureAdapter );

    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();

        Gdx.input.setInputProcessor( detector );

        //TextureRegion region = new TextureRegion( new Texture( Gdx.files.internal( "data/badlogic.jpg" ) ) );
        region = new TextureRegion( new Texture( Gdx.files.internal( "data/panda.jpg" ) ) );
        patch = new NinePatch( region, 60, 60, 60, 60 );
        batch = new SpriteBatch();
        
        width = 100;
        height = 30;
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Application.ApplicationType appType = Gdx.app.getType();
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            width += NdeltaX;
            height += NdeltaY;
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                width -= 2;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                width += 2;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                height -= 2;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                height += 2;
            }
        }
        
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        patch.draw( batch, 40, 300, width, height );
        batch.draw( region, 10, 20+height, width, height );
        batch.end();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
}