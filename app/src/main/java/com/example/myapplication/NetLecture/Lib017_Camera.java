package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.microedition.khronos.opengles.GL10;

public class Lib017_Camera extends ApplicationAdapter{

    OrthographicCamera camera;
    Texture texture;
    SpriteBatch batch;
    
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        batch = new SpriteBatch();
        texture = new Texture( Gdx.files.internal( "data/pal4_0.jpg" ) );
        
        camera = new OrthographicCamera();
        camera.setToOrtho( false, 480, 320 );

        camera.rotate( 45 );
        camera.zoom = 2f;
    }

    public void HandleInput(){
        if( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ){
            camera.position.add( 2f, 0, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.RIGHT )  ){
            camera.position.add( -2f, 0, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.UP )  ){
            camera.position.add( 0, -2f, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.DOWN )  ){
            camera.position.add( 0, 2f, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.D )  ){
            camera.zoom -= 0.05f;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.F )  ){
            camera.zoom += 0.05f;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.A )  ){
            camera.rotate( 1 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.S )  ){
            camera.rotate( -1 );
        }        
    }
    
    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor( 0, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        HandleInput();
        //camera.position.add( 0.5f, 0, 0 );
        camera.update();
        
        batch.setProjectionMatrix( camera.combined );
        batch.begin();
        batch.draw( texture, 0, 0 );
        batch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
        texture.dispose();
        super.dispose();
    }

}