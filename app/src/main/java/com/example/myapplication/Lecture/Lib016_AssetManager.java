package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import javax.microedition.khronos.opengles.GL10;

public class Lib016_AssetManager extends ApplicationAdapter{

    AssetManager manager;
    SpriteBatch batch;
    BitmapFont font;
    Texture texture;
    Music music;
    
    ShapeRenderer rend;
    boolean isPlay = false;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        manager = new AssetManager();
        batch = new SpriteBatch();
        rend = new ShapeRenderer();
        
        manager.load( "data/pal4_2.jpg", Texture.class );
        manager.load( "audio/xjwq.mp3", Music.class );
        manager.load( "font/default.fnt", BitmapFont.class );        
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor( 0, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        if( !manager.update() ){
            rend.setColor( Color.BLUE );
            rend.begin( ShapeType.Line );
            rend.rect( Gdx.graphics.getWidth()/4-1, (Gdx.graphics.getHeight()-20)/2-1, Gdx.graphics.getWidth()/2+2, 20+2 );
            rend.end();
            
            rend.setColor( Color.GREEN );
            rend.begin( ShapeType.Filled );
            rend.rect( Gdx.graphics.getWidth()/4, (Gdx.graphics.getHeight()-20)/2, Gdx.graphics.getWidth()/2*manager.getProgress(), 20  );
            rend.end();
        }
        else{
            texture = manager.get( "data/pal4_2.jpg", Texture.class );
            music = manager.get( "audio/xjwq.mp3", Music.class );
            font = manager.get( "font/default.fnt", BitmapFont.class );
            
            batch.begin();
            batch.draw( texture, Gdx.graphics.getWidth()/2-texture.getWidth()/2, 10 );
            //TextBounds bounds = font.getBounds( "AssetManager Test" );
            //font.draw( batch, "AssetManager Test", Gdx.graphics.getWidth()/2-bounds.width/2 ,10+texture.getHeight()+bounds.height+5 );

            //Rectangle bounds = font.ge
            font.draw( batch, "AssetManager Test", Gdx.graphics.getWidth()/2 - font.getSpaceWidth()/2 ,10 + texture.getHeight() + font.getXHeight() + 5 );
            batch.end();
            
            if( !isPlay ){
                music.setVolume( 0.8f );
                music.setLooping( true );
                music.play();
                isPlay = true;
            }

        }
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        manager.dispose();
        rend.dispose();
        batch.dispose();
        super.dispose();
    }

}