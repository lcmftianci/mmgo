package com.example.myapplication.Flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bird extends Actor{

    Animation animation;
    float currentTime;
    float speedY;
    
    public Bird(){
        animation = new Animation( 0.15f, Assets.sRegionBird );
        setSize( Assets.sRegionBird[0].getRegionWidth(), Assets.sRegionBird[0].getRegionHeight() );
        setOrigin( getWidth()/2, getHeight()/2 );
        //setPosition(  );
        Clear();
    }
    
    public void Clear(){
        currentTime = 0;
        setPosition( 100, Gdx.graphics.getHeight()/2-getHeight()/2 );
        speedY = Constant.speedY;
    }
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub        
        currentTime += delta;
        
        //this.translate( 0, speedY  );
        //speedY -= 0.5f;
        
        if( speedY > -10f ){
            speedY += Constant.speedInc;
        }
        
        super.act(delta);
    }
    
    //@Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        
        TextureRegion keyFrame = animation.getKeyFrame( currentTime, true );
        
        batch.draw( keyFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
        
        super.draw(batch, parentAlpha);
    }

}