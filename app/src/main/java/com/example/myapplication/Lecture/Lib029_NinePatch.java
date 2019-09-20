package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class Lib029_NinePatch extends ApplicationAdapter{

    NinePatch patch;
    SpriteBatch batch;
    TextureRegion region;
    
    int width;
    int height;
        
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        //TextureRegion region = new TextureRegion( new Texture( Gdx.files.internal( "data/badlogic.jpg" ) ) );
        region = new TextureRegion( new Texture( Gdx.files.internal( "data/button_green.png" ) ) );
        patch = new NinePatch( region, 13, 13, 13, 13 );
        batch = new SpriteBatch();
        
        width = 100;
        height = 30;
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        
        if( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ){
            width-=2;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.RIGHT ) ){
            width+=2;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.DOWN ) ){
            height-=2;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.UP ) ){
            height+=2;
        }
        
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        patch.draw( batch, 10, 10, width, height );
        batch.draw( region, 10, 20+height, width, height );
        batch.end();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}