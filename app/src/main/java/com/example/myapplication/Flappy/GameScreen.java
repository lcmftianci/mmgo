package com.example.myapplication.Flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

import javax.microedition.khronos.opengles.GL10;
//import com.badlogic.gdx.graphics.GL10;

public class GameScreen extends ScreenAdapter{
    
    FlappyBird game;
    GameStage gameStage;
    OverStage overStage;
    
    
    public GameScreen(FlappyBird game0){
        game = game0;
        gameStage = new GameStage(this);
        overStage = new OverStage(this);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor( gameStage );
    }
    
    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 0, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        if( Constant.state == Constant.GameState.game_on ){
            gameStage.act();
            gameStage.draw();    
            Gdx.input.setInputProcessor( gameStage );
        }
        else if( Constant.state == Constant.GameState.game_preover ){
            gameStage.draw();
            overStage.act();
            overStage.draw();
            Gdx.input.setInputProcessor( overStage );
        }

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
    
}