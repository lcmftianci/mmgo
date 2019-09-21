package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.microedition.khronos.opengles.GL10;


public class FreeType implements ApplicationListener{

    BitmapFont[] font;
    FreeTypeFontGenerator.FreeTypeBitmapFontData fontData;
    FreeTypeFontGenerator generator;    
    SpriteBatch batch;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
        font = new BitmapFont[3];
        Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
        for( int i=0; i<3; ++i ){
            generator = new FreeTypeFontGenerator( Gdx.files.internal( "type/" + "simhei" + ".ttf" ) );
            font[i] = generator.generateFont( 25, FreeTypeFontGenerator.DEFAULT_CHARS + "今天是个好日子，大家心情都很", false );
            font[i].setColor( colors[i] );
            generator.dispose();
        }
        //font[0] = new BitmapFont(fontData,)
        fontData = generator.generateData( 25, FreeTypeFontGenerator.DEFAULT_CHARS + "今天是个好日子，大家心情都很", false );
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        for( int i=0; i<3; ++i ){
            //font[i].drawMultiLine( batch, "今天是个好日子，\n大家心情都很好\nVery Good! 20140521!!", 120, 100*(3-i) );
            font[i].draw( batch, "今天是个好日子，\n大家心情都很好\nVery Good!" + df.format(new Date()).toString() + "!!", 120, 100*(3-i) );
        }
        batch.end();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
    }
    
}