//游戏主场景
package com.example.myapplication.WhitePile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.graphics.GL10;

import javax.microedition.khronos.opengles.GL10;

public class WhiteTileGameScreen extends ScreenAdapter{

    WhiteTile game;
    GameStage gameStage;
    OverStage overStage;
    
    public WhiteTileGameScreen( WhiteTile game0 ){
        game = game0;
        gameStage = new GameStage(this);
        overStage = new OverStage();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor( gameStage );
        //Constant.state = Constant.GameState.game_on;
    }
    
    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
            
        
        if( Constant.state == Constant.GameState.game_ready ){
            gameStage.act();
            gameStage.draw();
            gameStage.Clear();
            Constant.state = Constant.GameState.game_on;
            Gdx.input.setInputProcessor( gameStage );
        }
        else if( Constant.state == Constant.GameState.game_on ){
            Gdx.gl.glClearColor( 0, 1, 1, 1 );
            Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
            
            gameStage.act();
            gameStage.draw();            
        }
        else if( Constant.state == Constant.GameState.game_preover ){
            Gdx.gl.glClearColor( 0, 1, 1, 1 );
            Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
            
            gameStage.draw();
            overStage.act();
            overStage.draw();
            Gdx.input.setInputProcessor( overStage );
        }    
        else if( Constant.state == Constant.GameState.game_over ){
            //dispose();
            Gdx.app.exit();
        }
        
    }
    
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        gameStage.dispose();
        super.dispose();
    }

    
}