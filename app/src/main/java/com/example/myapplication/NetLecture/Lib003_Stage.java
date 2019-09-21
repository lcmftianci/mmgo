package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.microedition.khronos.opengles.GL10;

public class Lib003_Stage extends ApplicationAdapter{

    Stage stage;
    Image img;
    float leftX = 0;
    float bottomY = 0;
    
    enum Direct{ move_right, move_left, move_up, move_down };
    Direct direct = Direct.move_right;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        //stage = new Stage( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        //stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage();
        img = new Image( new Texture( Gdx.files.internal( "data/pal_4.jpg" ) ) );
        
        img.setPosition(0,0);
        stage.addActor( img );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
            
        if(  direct==Direct.move_right ){
            if( leftX + stage.getWidth()<img.getRight() ){
                stage.getCamera().translate( 2f, 0, 0 );
                leftX += 2f;
            }
            else{
                direct = Direct.move_up;
            }
        }        
        else if( direct == Direct.move_left ){
            if( leftX > 0 ){
                stage.getCamera().translate( -2f, 0, 0 );
                leftX -= 2f;                
            }
            else{
                direct = Direct.move_down;
            }
        }
        else if( direct == Direct.move_up ){
            if( bottomY + stage.getHeight() < img.getTop() ){
                stage.getCamera().translate( 0, 1.5f, 0 );
                bottomY += 1.5f;                
            }
            else{
                direct = Direct.move_left;
            }
        }
        else if( direct == Direct.move_down ){
            if( bottomY > 0 ){
                stage.getCamera().translate( 0, -1.5f, 0 );
                bottomY -= 1.5f;                
            }
            else{
                direct = Direct.move_right;
            }
        }        
        
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}