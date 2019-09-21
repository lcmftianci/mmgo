package com.example.myapplication.mylecture;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.example.myapplication.mylecture.camerauser.TitleScreen;
//import com.mygdx.game.Screens.TitleScreen;

public class MyGdxGameCamera extends Game {
    static public Skin skin;

    @Override
    public void create () {
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        this.setScreen(new TitleScreen(this));

    }

    @Override
    public void render () {
        super.render();

    }

    public void dispose () {
        skin.dispose();

    }

}
