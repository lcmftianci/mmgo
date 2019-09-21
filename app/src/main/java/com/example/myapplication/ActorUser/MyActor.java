package com.example.myapplication.ActorUser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

class MyActor extends Actor {
    TextureRegion region;
    
    public MyActor(){
        Texture texture = new Texture( Gdx.files.internal( "badlogic.jpg" ) );
        region = new TextureRegion( texture );
        setSize( region.getRegionWidth()/2, region.getRegionHeight()/2 );
        setOrigin( getWidth()/2, getHeight()/2 );
    }

    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
    }
    
    //@Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        batch.draw( region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
        super.draw(batch, parentAlpha);
    }

    public void dispose(){
        region.getTexture().dispose();
    }
}