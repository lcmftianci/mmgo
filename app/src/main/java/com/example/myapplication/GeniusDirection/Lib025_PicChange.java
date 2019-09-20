package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.microedition.khronos.opengles.GL10;

public class Lib025_PicChange extends ApplicationAdapter{
    
    GestureAdapter gestureAdapter = new GestureAdapter(){
        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            // TODO Auto-generated method stub
/*            if( velocityX > 0 ){
                System.out.println( "fling right" );
                stage.getCamera().translate( -stage.getWidth(), 0, 0 );
            }
            else{
                System.out.println( "fling left" );
                stage.getCamera().translate( stage.getWidth(), 0, 0 );
            }*/
            
            return super.fling(velocityX, velocityY, button);
        }

        
        
        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            // TODO Auto-generated method stub
            System.out.println( "pan" );
            if( index>0 && deltaX>0 || index<imgs.length-1 && deltaX<0 ){
                stage.getCamera().translate( -deltaX, 0, 0 );
                add = deltaX > 0? -1: 1; 
            }            
            
            return super.pan(x, y, deltaX, deltaY);
        }



        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            // TODO Auto-generated method stub
            System.out.println( "pan stop" );
            if( index>0 && add==-1 || index<imgs.length-1 && add==1 ){
                index += add;
                stage.getCamera().position.set( index*500+stage.getWidth()/2, stage.getHeight()/2, 0 );                
            }
            return super.panStop(x, y, pointer, button);
        }    
        
        
        
        
    };
    GestureDetector detector = new GestureDetector( gestureAdapter );
    
    Stage stage;
    Image img1, img2, img3, img4;
    Image[] imgs;
    int index;
    int add = 0;
    ShapeRenderer rend;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        Gdx.input.setInputProcessor( detector );
        
        img1 = new Image( new Texture( Gdx.files.internal( "back/ck0.jpg" ) ) );
        img2 = new Image( new Texture( Gdx.files.internal( "back/ck1.jpg" ) ) );
        img3 = new Image( new Texture( Gdx.files.internal( "back/ck2.jpg" ) ) );
        img4 = new Image( new Texture( Gdx.files.internal( "back/ck3.jpg" ) ) );
        
        stage = new Stage();
        stage.addActor( img1 );
        stage.addActor( img2 );
        stage.addActor( img3 );
        stage.addActor( img4 );
        
        imgs = new Image[]{ img1, img2, img3, img4 };
        
        for( int i=0; i<imgs.length; ++i ){
            imgs[i].setSize( 400, 240 );
            imgs[i].setPosition( i*500 + stage.getWidth()/2-imgs[i].getWidth()/2, stage.getHeight()/2-imgs[i].getHeight()/2 );
        }
            
        //imgs[1].setVisible( false );
        //imgs[2].setVisible( false );
        index = 0;
        rend = new ShapeRenderer();
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        stage.act();
        stage.draw();
        
        rend.begin( ShapeType.Filled );
        rend.setColor( Color.LIGHT_GRAY );
        rend.rect( 0, 0, 100, 480 );
        rend.rect( 700, 0, 100, 480 );
        rend.rect( 100, 0, 700, 60 );
        rend.rect( 100, 420, 700, 60 );
        rend.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        rend.dispose();
        stage.dispose();
        super.dispose();
    }

}