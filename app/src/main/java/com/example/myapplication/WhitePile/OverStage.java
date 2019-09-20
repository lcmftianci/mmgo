//游戏结束舞台
package com.example.myapplication.WhitePile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class OverStage extends Stage{
    
    Dialog dialogOver;
    WhiteTileGameScreen gameScreen;
    
    public OverStage(){
    
        dialogOver = new Dialog( "", WhiteTile.skin, "dialog" ){
            @Override
            protected void result(Object object) {
                // TODO Auto-generated method stub
                //System.out.println( "Chosen" + object );
                if( object.toString().equals( "true" ) ){
                    Constant.state = Constant.GameState.game_ready;
                    
                }else{
                    System.out.println( "No" );
                    Constant.state = Constant.GameState.game_over;
                    Gdx.app.exit();
                }
                
            }
        };
        
        dialogOver.text("GAME OVER").button("Again", true).button("Exit", false);
        addActor( dialogOver );
        dialogOver.setSize( 150, 100 );
        dialogOver.setPosition( this.getWidth()/2-dialogOver.getWidth()/2, this.getHeight()/2-dialogOver.getHeight()/2 );
        //dialogOver.show( this );
    }
    
    
    public void Show(){
        //addActor( dialogOver );
        dialogOver.show(this);
    }
    
}