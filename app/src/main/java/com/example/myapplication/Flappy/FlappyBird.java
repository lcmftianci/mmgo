package com.example.myapplication.Flappy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class FlappyBird extends Game{

    static Skin skin;
    StartScreen startScreen;
    GameScreen gameScreen;
    LevelScreen levelScreen;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        
        Assets.init();
        
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);
        levelScreen = new LevelScreen(this);
        
        setScreen( startScreen );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
    }
    
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        //skin.dispose();
        Assets.clean();
        super.dispose();
    }

}