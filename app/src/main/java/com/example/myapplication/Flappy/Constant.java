package com.example.myapplication.Flappy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Constant {
    
    enum GameState{ game_ready, game_on, game_preover, game_over };
    public static GameState state = GameState.game_ready;
    
    public static float speedY = -1f;
    public static float speedInc = -0.5f;
    
    public static void CenterInStage( Actor actor, Stage stage ){
        actor.setPosition( stage.getWidth()/2-actor.getWidth()/2, stage.getHeight()/2-actor.getHeight()/2 );
    }
    
    public static Image CreateImage( TextureRegion region0 ){
        Image img = new Image( region0 );
        img.setSize( region0.getRegionWidth(), region0.getRegionHeight() );
        return img;
    }
    
    public static boolean IsCollsion( Actor actor1, Actor actor2 ){
        float x1 = actor1.getX(), y1 = actor1.getY(), w1 = actor1.getWidth(), h1 = actor1.getHeight();
        float x2 = actor2.getX(), y2 = actor2.getY(), w2 = actor2.getWidth(), h2 = actor2.getHeight();
        if( x1<x2 && x1+w1<x2 ){
            return false;
        }
        else if( x2<x1 && x2+w2<x1 ){
            return false;
        }
        else if( y1<y2 && y1+h1<y2 ){
            return false;
        }
        else if( y2<y1 && y2+h2<y1 ){
            return false;
        }
        
        return true;
    }

}