//游戏中元素块，白块或灰块
package com.example.myapplication.WhitePile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Block extends Image{
    
    Color color;
    boolean isTouch;
    //GameScreen gameScreen;
    
    public Block(){
        Clear();
            
        addListener( new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                isTouch = true;
                if( color == Color.WHITE ){
                    //Constant.state = Constant.GameState.game_preover;
                    //gameScreen.game. overStage.Show();
                    //gameScreen.overStage.Show();
                }
                return true;
            }        
        });
        
    }
    
    public void Clear(){
        isTouch = false;
    }
    
    
    
}