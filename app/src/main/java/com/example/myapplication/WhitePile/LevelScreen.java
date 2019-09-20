//模式选择场景
package com.example.myapplication.WhitePile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import javax.microedition.khronos.opengles.GL10;

public class LevelScreen extends ScreenAdapter{

    WhiteTile game;
    Stage stage;
    //Table table;
    TextButton[] sButton;
    
    public LevelScreen(WhiteTile game0){
        game = game0;
        stage = new Stage();
        
        //table = new Table( WhiteTile.skin );
        
        sButton = new TextButton[3];
        String[] arrStr = { "Simple", "Normal", "Hard" };
        for( int i=0; i<sButton.length; ++i ){
            sButton[i] = new TextButton( arrStr[i], WhiteTile.skin );
            sButton[i].setSize( 150, 60 );
            stage.addActor( sButton[i] );
            //table.row();
        }
        
        sButton[0].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                    Constant.level = 1;
                    game.setScreen( game.startScreen );                    
            }            
        });
        
        sButton[1].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                    Constant.level = 2;
                    game.setScreen( game.startScreen );                    
            }            
        });
        
        sButton[2].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                    Constant.level = 3;
                    game.setScreen( game.startScreen );                    
            }            
        });
                
        //stage.addActor( table );
        //table
        
        //sButton[i].setPosition( stage.getWidth()/2-sButton[i].getWidth()/2, stage.getHeight()/2 );
        sButton[0].setPosition( stage.getWidth()/2-sButton[0].getWidth()/2, stage.getHeight()/2+sButton[0].getHeight()/2+50 );
        sButton[1].setPosition( stage.getWidth()/2-sButton[1].getWidth()/2, stage.getHeight()/2-sButton[1].getHeight()/2 );
        sButton[2].setPosition( stage.getWidth()/2-sButton[2].getWidth()/2, stage.getHeight()/2-3*sButton[2].getHeight()/2-50 );
        
    }

    public boolean IsInside( Actor actor, float x, float y ){
        return x>actor.getX() && x<actor.getRight() && y>actor.getY() && y>actor.getTop();
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
    public void show() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor( stage );
        super.show();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
    
    
    
}