package com.lcmf.mmgo.advanbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.example.myapplication.mylecture.box2duser.screens.TitleScreen;
//import com.mygdx.game.Screens.TitleScreen;

public class MyGdxGameBox2d extends Game {
    static public Skin skin;
    static public TextureAtlas textureAtlas;

    @Override
    public void create () {
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("note",new TextureRegion(new Texture("spirite/note.png")));
        this.setScreen(new GameScreen(this));

    }

    @Override
    public void render () {
        super.render();
    }

    public void dispose () {
        skin.dispose();
    }

}
