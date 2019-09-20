package com.example.myapplication.WhitePile;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.example.myapplication.GeniusDirection.LevelScreen;
//import com.example.myapplication.whitepile.LevelScreen;
//import com.example.myapplication.whitepile.StartScreen;

public class WhiteTile extends Game{

    static Skin skin;
    StartScreen startScreen;
    static WhiteTileGameScreen gameScreen;
    LevelScreen levelScreen;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub    
        skin = new Skin( Gdx.files.internal( "skin/uiskin.json" ) );
        startScreen = new StartScreen( this );
        gameScreen = new WhiteTileGameScreen( this );
        levelScreen = new LevelScreen( this );
        
        setScreen(startScreen);
    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        skin.dispose();
        startScreen.dispose();
        gameScreen.dispose();
        levelScreen.dispose();
        super.dispose();
    }
}