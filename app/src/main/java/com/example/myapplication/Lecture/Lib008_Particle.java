package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import javax.microedition.khronos.opengles.GL10;

public class Lib008_Particle extends ApplicationAdapter{

    ParticleEffect effect;
    InputProcessor processor;
    
    float positionX;
    float positionY;
    
    SpriteBatch batch;
    
    Array<ParticleEmitter> sEmitter;
    int index = 0;
    int maxSize;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub    
        batch = new SpriteBatch();
        effect = new ParticleEffect();
        effect.load( Gdx.files.internal("particle/test.p"), Gdx.files.internal("particle/") );
        maxSize = effect.getEmitters().size;
        
        positionX = Gdx.graphics.getWidth()/2;
        positionY = Gdx.graphics.getHeight()/2;
        
        //processor = new InputProcessor(){
        processor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                // TODO Auto-generated method stub
                if( keycode == Input.Keys.SPACE ){
                    index = (++index)%maxSize;
                    effect.getEmitters().clear();
                    effect.getEmitters().add( sEmitter.get(index) );
                }
                return false;
            }
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                // TODO Auto-generated method stub
                positionX = screenX;
                positionY = Gdx.graphics.getHeight()-screenY;
                return false;
            }
        };
        
        Gdx.input.setInputProcessor( processor );
        
        sEmitter = new Array<ParticleEmitter>();
        for( ParticleEmitter emitter : effect.getEmitters() ){
            sEmitter.add( emitter );
        }
        effect.getEmitters().clear();
        effect.getEmitters().add( sEmitter.get(index) );
    }


    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        effect.setPosition( positionX, positionY );
        
        float delta = Gdx.graphics.getDeltaTime();
        batch.begin();
        effect.draw( batch, delta );
        batch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
        effect.dispose();
    }

}