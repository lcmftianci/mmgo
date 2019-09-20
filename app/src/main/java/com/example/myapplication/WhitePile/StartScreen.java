//开始场景
package com.example.myapplication.WhitePile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import javax.microedition.khronos.opengles.GL10;

public class StartScreen extends ScreenAdapter{

    WhiteTile game;
    Stage stage;
    TextButton btnStart, btnLevel;
    
    SequenceAction actionTotal;
    
    public StartScreen(WhiteTile game0){
        game = game0;
        stage = new Stage();
        
        btnStart = new TextButton( "Start Game", WhiteTile.skin, "default" );
        btnStart.addListener( new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                stage.addAction( actionTotal );
            }                    
        });    
        
        btnLevel = new TextButton( "Select Mode", WhiteTile.skin, "default" );
        btnLevel.addListener( new InputListener(){    
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                game.setScreen(game.levelScreen);
            }            
        });            
        
        //stage.addAction( Actions.fadeOut( 2 ) );
        Action actionChange = new Action(){
            @Override
            public boolean act(float delta) {
                // TODO Auto-generated method stub
                game.setScreen( game.gameScreen );
                dispose();
                return false;
            }        
        };
        Action actionRotate = Actions.rotateBy( -360, 2 );
        //actionTotal = Actions.sequence( Actions.parallel( actionRotate, Actions.fadeOut(2) ), actionChange );
        actionTotal = Actions.sequence( Actions.fadeOut(0.2f), actionChange );

        btnStart.setSize( 150, 70 );
        btnStart.setPosition( stage.getWidth()/2-btnStart.getWidth()/2, stage.getHeight()/2-btnStart.getHeight()-50 );
        btnLevel.setSize( 150, 70 );
        btnLevel.setPosition( stage.getWidth()/2-btnStart.getWidth()/2, stage.getHeight()/2+50 );
        
        stage.addActor( btnStart );
        stage.addActor( btnLevel );
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
        //stage.dispose();
        super.dispose();
    }

}