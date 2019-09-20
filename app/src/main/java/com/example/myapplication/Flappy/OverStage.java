package com.example.myapplication.Flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class OverStage extends Stage{
    
    Window window;
    TextButton button1;
    TextButton button2;
    Label label;
    GameScreen gameScreen;
    
    public OverStage(GameScreen gameScreen0){
        gameScreen = gameScreen0;
        
        window = new Window( "", Assets.skin );
        //window.add( "GAME OVER\n" );
        
        label = new Label( "GAME OVER", Assets.skin );
        //label.setFontScale( 1.0f );
        
        button1 = new TextButton( "Again", Assets.skin );
        button2 = new TextButton( "Exit", Assets.skin );
        
        
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                Constant.state = Constant.GameState.game_on;
                gameScreen.gameStage.Clear();
            }        
        });
        
        button2.addListener( new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                //Constant.state = Constant.GameState.game_on;
                Gdx.app.exit();
            }        
        });
        
        window.setSize( getWidth()/2, getHeight()/6 );
        window.addActor( button1 );
        window.addActor( button2 );
        window.addActor( label );
        
        button1.setSize( 60, 30 );
        button2.setSize( 60, 30 );
        label.setSize( 100, 50 );
        button1.setPosition( window.getWidth()/8, 10 );
        button2.setPosition( window.getWidth()*7/8-button2.getWidth(), 10 );
        label.setPosition( window.getWidth()/2-label.getWidth()/2, button2.getTop()+20 );
        
        this.addActor( window );
        Constant.CenterInStage( window, this );
    }
    
    

}