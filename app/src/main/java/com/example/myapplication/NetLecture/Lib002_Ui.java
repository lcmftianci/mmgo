package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
//import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import javax.microedition.khronos.opengles.GL10;

public class Lib002_Ui implements ApplicationListener{

    Stage stage;
    LabelStyle labelStyle;
    Label label, label2;
    BitmapFont font;
    ButtonStyle buttonStyle;
    TextButtonStyle textbuttonStyle;
    Button button;
    TextButton textbutton, textbutton2;
        
    Skin skin;
        
    @Override
    public void create() {
        // TODO Auto-generated method stub
        
        stage = new Stage();
        skin = new Skin();
        
        Pixmap pixmap = new Pixmap( 1, 1, Format.RGBA8888 );
        pixmap.setColor( Color.DARK_GRAY );
        pixmap.fill();
        Texture texture = new Texture( pixmap );
        Drawable draw1 = new TextureRegionDrawable( new TextureRegion( texture ) );
        
        pixmap.setColor( Color.GRAY );
        pixmap.fill();
        texture = new Texture( pixmap );
        Drawable draw2 = new TextureRegionDrawable( new TextureRegion( texture ) );
        
        font = new BitmapFont();
        labelStyle = new LabelStyle( font, Color.GREEN );
        labelStyle.background = draw1;
        
        label = new Label( "Very Good!", labelStyle );
        label.setAlignment( Align.right );
        //label.setSize( 100, 50 );
        label.setBounds( 10, 0, 100, 50 );
        
        skin.add( "gray", texture, Texture.class );
        skin.add( "gray", labelStyle, LabelStyle.class );
        
        label2 = new Label( "Label2", skin, "gray" );
        label2.setSize( 100, 50 );
        label2.setPosition( 120, 0 );
        
        buttonStyle = new ButtonStyle( draw1, draw2, null );
        textbuttonStyle = new TextButtonStyle( draw1, draw2, null, font );
        textbuttonStyle.fontColor = Color.CYAN;
        
        textbutton = new TextButton( "TextButton", textbuttonStyle );
        textbutton.setBounds( 0, 100, 100, 50 );
        
        skin.add( "gray", textbuttonStyle, TextButtonStyle.class );
        textbutton2 = new TextButton( "TextButton2", skin, "gray" );
        textbutton2.setBounds( 150, 100, 100, 50 );
                
        stage.addActor( label );
        stage.addActor( label2 );
        stage.addActor( textbutton );
        stage.addActor( textbutton2 );
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub    
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        stage.act();
        stage.draw();        
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
        stage.dispose();
        skin.dispose();
    }

}