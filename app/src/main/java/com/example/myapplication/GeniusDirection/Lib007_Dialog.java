package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
//import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

import javax.microedition.khronos.opengles.GL10;

public class Lib007_Dialog implements ApplicationListener{

    Stage stage;
    Dialog dialog;
    Drawable draw1;
    Drawable draw2;
    Pixmap pixmap;
    Texture texture;
    Skin skin;
    BitmapFont font;
    Dialog dialog2;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
    
        stage = new Stage();
        skin = new Skin();
        pixmap = new Pixmap( 1, 1, Format.RGBA8888 );
        pixmap.setColor( Color.GRAY );
        pixmap.fill();
        //draw1 = new TextureRegionDrawable(  );
        //texture = new Texture( pixmap );
        skin.add( "gray", new Texture(pixmap) );
        
        pixmap.setColor( Color.LIGHT_GRAY );
        pixmap.fill();
        skin.add( "light_gray", new Texture(pixmap) );
        
        font = new BitmapFont();
        WindowStyle windowStyle = new WindowStyle( font, Color.GREEN, skin.getDrawable( "light_gray" ) );
        dialog = new Dialog( "DialogTest", windowStyle );
        //dialog.setTitleAlignment( Align.center | Align.top );
        dialog.setBounds( 10, 10, 100, 50 );
        
        LabelStyle labelStyle = new LabelStyle( font, Color.RED );
        ButtonStyle buttonStyle = new ButtonStyle( skin.getDrawable("gray"), skin.getDrawable("light_gray"), null );
        TextButtonStyle textbuttonStyle = new TextButtonStyle( skin.getDrawable("gray"), skin.getDrawable("light_gray"), null, font );
        skin.add( "default", buttonStyle );
        skin.add( "default", textbuttonStyle );
        skin.add( "default", windowStyle );
        skin.add( "default", labelStyle );
        
        Button button1 = new Button( skin );
        button1.setBounds( 10, 10, 30, 20 );
        TextButton textbutton1 = new TextButton( "Click", skin );
        textbutton1.setBounds( 90-30, 10, 30, 20 );
        dialog.addActor( button1 );
        dialog.addActor( textbutton1 );
                
        stage.addActor( dialog );
        //dialog2.row().fill().expand();
            
        dialog2 = new Dialog( "Dialog2", skin, "default" ){
            @Override
            protected void result(Object object) {
                // TODO Auto-generated method stub
                //System.out.println( "Chosen" + object );
                if( object.toString().equals( "true" ) ){
                    System.out.println( "Yes" );
                    Gdx.app.exit();
                }else{
                    System.out.println( "No" );
                }            
            }        
        };            
        dialog2.text("\nDo you want to exit?").button("Yes", true).button("No",false).key(Keys.ENTER, true).key(Keys.ESCAPE,false);
        //dialog2.setTitleAlignment( Align.top );
        dialog2.pack();
        //dialog2.show( stage );
            
        //TextButton textbutton2 = new TextButton( "Click", skin );
        textbutton1.addListener( new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                dialog2.show( stage );
            }
        });
                    
        //stage.addActor( textbutton2 );
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
        skin.dispose();
        stage.dispose();
        texture.dispose();
    }

}