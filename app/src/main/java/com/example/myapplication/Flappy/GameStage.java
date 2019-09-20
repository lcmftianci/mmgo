package com.example.myapplication.Flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class GameStage extends Stage{

    GameScreen gameScreen;
    Group groupUp, groupDown;
    Pool<PipeUp> poolUp;
    Pool<PipeDown> poolDown;
    Bird bird;
    Image imgBack;
    OrthographicCamera camera;
    
    int xIndex;
    float leftX = 0;
    float speed;
    float currentTime;
    float lastTouchTime;
    
    public GameStage( GameScreen gameScreen0 ){
        gameScreen = gameScreen0;
        
        groupUp = new Group();
        groupDown = new Group();
        
        poolUp = Pools.get( PipeUp.class );
        poolDown = Pools.get( PipeDown.class );
        
        bird = new Bird();
        imgBack = Constant.CreateImage( Assets.regionBack );
        camera = new OrthographicCamera( Assets.regionBack.getRegionWidth(), Assets.regionBack.getRegionHeight() );
        camera.position.set( Assets.regionBack.getRegionWidth()/2, Assets.regionBack.getRegionHeight()/2, 0 );
        
        //addActor( imgBack );
        addActor( bird );
        addActor( groupUp );
        addActor( groupDown );
                        
        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                if( currentTime - lastTouchTime > 0.15f ){
                    if( bird.speedY < 0 ){
                        bird.speedY += 15;                        
                    }
                    else if( bird.speedY < 5 ){
                        bird.speedY += 15;
                    }
                    else if( bird.speedY < 8 ){
                        bird.speedY += 8;
                    }
                    else{
                        bird.speedY += 4;
                    }

                    if( bird.speedY > 25 ){
                        bird.speedY = 25;
                    }
                    lastTouchTime = currentTime;
                }
                return true;
            }        
        });
        
        
        Clear();        
    }
    
    public void Clear(){
        xIndex = 3;
        //this.camera.translate( -leftX, 0 );
        //this.camera.position.set( getWidth()/2, getHeight()/2, 0 );
        this.getCamera().translate( -leftX, 0, 0 );
        leftX = 0;
        
        speed = 2f;
        currentTime = 0;
        lastTouchTime = -1f;
        
        bird.Clear();
        
        groupUp.clear();
        groupDown.clear();
        poolUp.clear();
        poolDown.clear();
        
        for( int i=0; i<xIndex; ++i ){
            AddPipe(i);            
        }
            
        Gdx.input.setInputProcessor(this);
    }
    
    public void AddPipe( int xIndex0 ){
        PipeUp pipeup = poolUp.obtain();        
        PipeDown pipedown = poolDown.obtain();        
                
        groupUp.addActor( pipeup );
        groupDown.addActor( pipedown );
        
        float rate1 = MathUtils.random( 0.2f, 1.3f );
        float rate2 = 1.5f - rate1;
        
        //rate1 = 0.8f;
        //rate2 = 0.2f;
        
        int height1 = (int)( Assets.regionPipeUp.getRegionHeight()*rate1 );
        int height2 = (int)( Assets.regionPipeDown.getRegionHeight()*rate2 );
        
        if( rate1 < 1 ){
            pipeup.region = new TextureRegion( Assets.regionPipeUp, 0, Assets.regionPipeUp.getRegionHeight()-height1, Assets.regionPipeUp.getRegionWidth(), height1 );
            pipeup.setHeight( height1 );
        }
        else{
            pipeup.region = Assets.regionPipeUp;
            pipeup.setHeight( height1 );
        }
        
        if( rate2 < 1 ){
            pipedown.region = new TextureRegion( Assets.regionPipeDown, 0, 0, Assets.regionPipeDown.getRegionWidth(), height2 );
            pipedown.setHeight( height2 );
        }
        else{
            pipedown.region = Assets.regionPipeDown;
            pipedown.setHeight( height2 );
        }
        
        //pipedown        
        pipeup.setPosition( getWidth()+xIndex0*300, getHeight()-pipeup.getHeight() );
        pipedown.setPosition( getWidth()+xIndex0*300, 0 );
    }
        
    @Override
    public void act() {
        // TODO Auto-generated method stub
        
        leftX += speed;
        this.getCamera().translate( speed, 0, 0 );
        //bird.translate( speed, bird.speedY );
        
        currentTime += Gdx.graphics.getDeltaTime();
        
        boolean isDis = false;
        for( int i=0; i<groupUp.getChildren().size; ++i ){
            PipeUp pipeup = (PipeUp)groupUp.getChildren().items[i];
            if( pipeup.getRight() < leftX ){
                groupUp.removeActor( pipeup );
                poolUp.free( pipeup );
                if( !isDis ){
                    isDis = true;                    
                }
            }
            
            if( Constant.IsCollsion( pipeup, bird )  ){
                //Gdx.app.exit();
                Constant.state = Constant.GameState.game_preover;
            }
        }

        for( int i=0; i<groupDown.getChildren().size; ++i ){
            PipeDown pipedown = (PipeDown)groupDown.getChildren().items[i];
            if( pipedown.getRight() < leftX ){
                groupDown.removeActor( pipedown );
                poolDown.free( pipedown );
                if( !isDis ){
                    isDis = true;                    
                }
            }
            
            if( Constant.IsCollsion( pipedown, bird )  ){
                //Gdx.app.exit();
                Constant.state = Constant.GameState.game_preover;
            }
        }
        
        if( isDis ){
            AddPipe( xIndex );
            xIndex++;
        }
        
        if( bird.getY() < 0 ){
            Constant.state = Constant.GameState.game_preover;
        }
        
        
        super.act();
    }
    
    @Override
    public void draw() {
        // TODO Auto-generated method stub    
        camera.update();
        
        //SpriteBatch batch = this.getSpriteBatch();
        Batch batch = this.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw( Assets.regionBack, 0, 0 );
        batch.end();
            
        super.draw();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
    
}