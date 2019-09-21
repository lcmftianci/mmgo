package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.microedition.khronos.opengles.GL10;

public class Lib001_Font extends ApplicationAdapter{

    BitmapFont font;
    SpriteBatch batch;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
        font = new BitmapFont( Gdx.files.internal( "font/test.fnt" ), Gdx.files.internal( "font/test.png" ), false );
        font.setColor(Color.BLUE);
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        font.draw( batch, "5月20号，天空晴朗，蓝天里飘着白云", 10, 100 );
        //font.drawMultiLine( batch, "5月20号\n天空晴朗\n蓝天里飘着白云", 10, 250 );
        font.draw(batch, "5月20号\n天空晴朗\n蓝天里飘着白云", 10, 250);
        batch.end();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        font.dispose();
        batch.dispose();
        super.dispose();
    }

}