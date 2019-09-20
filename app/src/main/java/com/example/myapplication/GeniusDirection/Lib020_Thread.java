package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import javax.microedition.khronos.opengles.GL10;

public class Lib020_Thread extends ApplicationAdapter{

    float currentTime;
    //float lastTime;
    
    SpriteBatch batch;
    BitmapFont font;
    int count = 0;
    
    Array<TextureRegion> array1 = new Array<TextureRegion>();

    
    Thread thread = new Thread(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //final int a = 2;
            
            try{
                while(true)
                {
                    Thread.sleep( 1000 );        
                    count++;    
                    
                    if( count == 3 ){
                        Gdx.app.postRunnable(new Runnable(){
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                TextureRegion region = new TextureRegion( new Texture( Gdx.files.internal( "data/pal4_1.jpg" ) ) );
                                array1.add( region );
                            }
                        });
                    }
                    if( count == 5 ){
                        Gdx.app.postRunnable(new Runnable(){
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                TextureRegion region = new TextureRegion( new Texture( Gdx.files.internal( "data/badlogic.jpg" ) ) );
                                array1.add( region );
                            }
                        });
                    }
                }            
            }
            catch(Exception e){
                e.printStackTrace();
            }            
        }
    };
    
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor( Color.BLACK );
        
        thread.start();
        
        //array1.add( new TextureRegion( new Texture( Gdx.files.internal( "data/badlogic.jpg" ) ) ) );        
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        
        
        float width = 0;
        for( int i=0; i<array1.size; ++i ){
            batch.draw( array1.get(i), width, 0 );
            width += array1.get(i).getRegionWidth()+5;
        }
        font.draw( batch, ""+count, 100, 280 );    
        batch.end();

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        font.dispose();
        batch.dispose();
    }
    
}