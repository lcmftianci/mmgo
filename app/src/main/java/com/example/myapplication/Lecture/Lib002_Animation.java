package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class Lib002_Animation extends ApplicationAdapter{

    Texture texture;
    Animation animation;
    SpriteBatch batch;
    float currentTime = 0;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
        texture = new Texture( Gdx.files.internal( "data/koalio.png" ) );
        TextureRegion region = new TextureRegion( texture );
        TextureRegion[] regions = region.split( 18, 26 )[0];
        
        animation = new Animation( 0.1f, regions[1], regions[2], regions[3], regions[4] );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        currentTime += Gdx.graphics.getDeltaTime();
        TextureRegion region = animation.getKeyFrame( currentTime, true );
        
        batch.begin();
        batch.draw( region, 100, 100, region.getRegionWidth(), region.getRegionHeight() );
        batch.end();        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}