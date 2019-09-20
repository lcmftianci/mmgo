package com.example.myapplication.Flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import javax.microedition.khronos.opengles.GL10;

public class StartScreen extends ScreenAdapter{
    
    FlappyBird game;
    Stage stage;
    TextButton btnStart, btnMode;
    
    public StartScreen(FlappyBird game0){
        game = game0;
        stage = new Stage();
        
        btnStart = new TextButton( "Start Game", Assets.skin, "default" );
        btnStart.setSize( 150, 70 );
        Constant.CenterInStage( btnStart, stage );
        stage.addActor( btnStart );
        
        btnStart.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                game.setScreen( game.gameScreen );
                Constant.state = Constant.GameState.game_on;
            }            
        });
        
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor( stage );
    }
    
    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 0, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        stage.act();
        stage.draw();        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        stage.dispose();
        super.dispose();
    }
    
}