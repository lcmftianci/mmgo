package com.example.myapplication.Flappy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PipeUp extends Actor{
    
    TextureRegion region;
    
    public PipeUp(){
        region = new TextureRegion( Assets.regionPipeUp );
        setSize( region.getRegionWidth(), region.getRegionHeight() );
    }
    
    /*
    public UpdateRegion(){
        region = new TextureRegion( Assets.regionPipeUp );
    }
    */
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
    }
    
    
    //@Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        
        //batch.draw( region, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight() );
        batch.draw( region, getX(), getY(), getWidth(), getHeight() );
        
        super.draw(batch, parentAlpha);
    }
}