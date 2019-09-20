package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FlappyBird extends ApplicationAdapter{
    
    Texture texture;
    SpriteBatch batch;
    
    TextureRegion region1;
    TextureRegion region2;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        texture = new Texture( Gdx.files.internal("data/badlogic.jpg") );
        batch = new SpriteBatch();
        
        region1 = new TextureRegion( texture );
        region2 = new TextureRegion( region1, 0, 0, region1.getRegionWidth()/2, region1.getRegionHeight()/2 );
        
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw( texture, 50, 50 );
        batch.draw( region1, 400, 50, region1.getRegionWidth()/2, region1.getRegionHeight()/2 );
        batch.draw( region2, 600, 50 );
        batch.end();
    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
    
}