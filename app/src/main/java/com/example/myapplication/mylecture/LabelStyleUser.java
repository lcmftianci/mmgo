package com.example.myapplication.mylecture;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LabelStyleUser implements ApplicationListener {
    Label.LabelStyle labelStyle;
    Stage stage;
    Label label;
    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont( Gdx.files.internal( "font/bitmap_font.fnt" ), Gdx.files.internal( "font/bitmap_font.png" ), false );
        labelStyle = new Label.LabelStyle(font, font.getColor());
        label = new Label("go away!!", labelStyle);
        //stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new ScreenViewport());
        label.setPosition(0, 0);
        label.setScale(200);
        label.setFontScale(200);
        label.setColor(Color.GREEN);
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
