package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.microedition.khronos.opengles.GL10;

public class Lib010_TiledMap extends ApplicationAdapter{

    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    TextureRegion regionPurple;
    TextureRegion regionBlue;
    TextureRegion regionGreen;
    
    TiledMapTileLayer layer1;
    
    Stage stage;
    Image imgGreen;
    
    enum StateX{ move_still, move_right, move_left };
    enum StateY{ move_still, move_up, move_down };

    StateX stateX = StateX.move_still;
    StateY stateY = StateY.move_still;
    
    float clicktimeX = 0;
    float clicktimeY = 0;
    float currentTime = 0;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        
        map = new TmxMapLoader().load( "map1/test1.tmx" );
        renderer = new OrthogonalTiledMapRenderer( map );

        stage = new Stage();
        //stage = new Stage( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, renderer.getSpriteBatch() );
        
        regionPurple = new TextureRegion( new Texture( Gdx.files.internal( "map1/bullet_purple_32.png" ) ) );
        regionBlue = new TextureRegion( new Texture( Gdx.files.internal( "map1/bullet_blue_32.png" ) ) );
        regionGreen = new TextureRegion( new Texture( Gdx.files.internal( "map1/bullet_green_32.png" ) ) );
        
        camera = new OrthographicCamera( 32*30, 32*12 );
        //camera.setToOrtho( false, 32*30, 32*12 );
        camera.position.set( 32*30/2, 32*12/2, 0 );
        camera.update();
        
        layer1 = (TiledMapTileLayer)map.getLayers().get(0);
        MapLayer layer2 = map.getLayers().get(1);
        
        System.out.println( layer1.getName()+"\n"+layer2.getName() );
        
        int count =  layer2.getObjects().getCount();
        System.out.println(count);
        for( int i=0; i<count; ++i ){
            RectangleMapObject obj = (RectangleMapObject)layer2.getObjects().get(i);
            String strName = obj.getName();
            
            
            if( strName != null ){
                System.out.print( strName+"\t" );
                if( obj.getProperties() != null ){
                    if( obj.getProperties().get("level")!=null ){
                        String strProperty = obj.getProperties().get("level").toString();
                        System.out.println( strProperty );    
                        
                        float x = obj.getRectangle().getX();
                        float y = obj.getRectangle().getY();
                        //System.out.println( x/32+","+y/32 );
                        Image tempImg = new Image( regionPurple );
                        tempImg.setSize( 32, 32 );
                        tempImg.setPosition( x, y );
                        stage.addActor( tempImg );                    
                    }
                    else if( obj.getProperties().get("time")!=null ){
                        float x = obj.getRectangle().getX();
                        float y = obj.getRectangle().getY();
                        //System.out.println( x/32+","+y/32 );
                        Image tempImg = new Image( regionBlue );
                        tempImg.setSize( 32, 32 );
                        tempImg.setPosition( x, y );
                        stage.addActor( tempImg );                        
                    }
                    
                }
                

            }
        }
        
        
        imgGreen = new Image( regionGreen );
        imgGreen.setSize( 32, 32 );
        stage.addActor( imgGreen );
        imgGreen.setPosition( 0, 0 );
        
        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                // TODO Auto-generated method stub
                if( keycode == Input.Keys.UP ){
                    //imgGreen.translate( 0, 32 );
                    stateY = StateY.move_up;
                    MoveY();
                    clicktimeY = currentTime;
                    //state = State.move_still;
                }
                else if( keycode == Input.Keys.DOWN ){
                    //imgGreen.translate( 0, -32 );
                    stateY = StateY.move_down;
                    MoveY();
                    clicktimeY = currentTime;
                    //state = State.move_still;
                }
                
                if( keycode == Input.Keys.LEFT ){
                    //imgGreen.translate( -32, 0 );
                    stateX = StateX.move_left;
                    MoveX();
                    clicktimeX = currentTime;
                    //state = State.move_still;
                }                
                else if( keycode == Input.Keys.RIGHT ){
                    //imgGreen.translate( 32, 0 );
                    stateX = StateX.move_right;
                    MoveX();
                    clicktimeX = currentTime;
                    //state = State.move_still;
                }                
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                // TODO Auto-generated method stub
                if( keycode == Input.Keys.UP ){
                    //imgGreen.translate( 0, 32 );
                    stateY = StateY.move_still;
                }
                else if( keycode == Input.Keys.DOWN ){
                    //imgGreen.translate( 0, -32 );
                    stateY = StateY.move_still;
                }
                
                if( keycode == Input.Keys.LEFT ){
                    //imgGreen.translate( -32, 0 );
                    stateX = StateX.move_still;
                }                
                else if( keycode == Input.Keys.RIGHT ){
                    //imgGreen.translate( 32, 0 );
                    stateX = StateX.move_still;
                }                
                return true;
            }
            
            
        });
        
        Gdx.input.setInputProcessor( stage );
    }

    public void MoveX(){
        float x0 = imgGreen.getX();
        float y0 = imgGreen.getY();
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;
        int x3 = 0, y3 = 0;
        
        float speed = 2;
        
        switch( stateX ){
            case move_right: 
                //imgGreen.translate( speed, 0 );
                imgGreen.setPosition(imgGreen.getImageX() - speed,imgGreen.getImageY());
                x1 = (int)( (imgGreen.getX()+31)/32 );
                x2 = x3 = x1;
                
                y1 = (int)( (imgGreen.getY()+1)/32 );
                y2 = (int)( (imgGreen.getY()+15)/32 );
                y3 = (int)( (imgGreen.getY()+31)/32 );
                break;
            case move_left:
                imgGreen.setPosition(imgGreen.getImageX() + speed,imgGreen.getImageY());
                //imgGreen.translate( -speed, 0 );
                x1 = (int)( (imgGreen.getX()+1)/32 );
                x2 = x3 = x1;
                
                y1 = (int)( (imgGreen.getY()+1)/32 );
                y2 = (int)( (imgGreen.getY()+15)/32 );
                y3 = (int)( (imgGreen.getY()+31)/32 );
                break;
            default:
                break;
        }
        if( layer1.getCell( x1, y1 )!=null || layer1.getCell( x2, y2 )!=null || layer1.getCell( x3, y3 )!=null ){
            imgGreen.setPosition( x0, y0 );
        }
        
    }
    
    public void MoveY(){
        float x0 = imgGreen.getX();
        float y0 = imgGreen.getY();
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;
        int x3 = 0, y3 = 0;
        
        float speed = 2;
        switch( stateY ){
        case move_up: 
            //imgGreen.translate( 0, speed );
            imgGreen.setPosition(imgGreen.getImageX(),imgGreen.getImageY() + speed);
            x1 = (int)( (imgGreen.getX()+1)/32 );            
            x2 = (int)( (imgGreen.getX()+15)/32 );
            x3 = (int)( (imgGreen.getX()+31)/32 );
            
            y1 = (int)( (imgGreen.getTop()-1)/32 );
            y2 = y3 = y1;
            //x2 = x3 = x1;
            break;
        case move_down: 
            //imgGreen.translate( 0, -speed );
            imgGreen.setPosition(imgGreen.getImageX(),imgGreen.getImageY() - speed);
            x1 = (int)( (imgGreen.getX()+1)/32 );
            x2 = (int)( (imgGreen.getX()+15)/32 );
            x3 = (int)( (imgGreen.getX()+31)/32 );
            
            y1 = (int)( (imgGreen.getY()+1)/32 );
            y2 = y3 = y1;
            break;
        default:
            break;
        }

        if( layer1.getCell( x1, y1 )!=null || layer1.getCell( x2, y2 )!=null || layer1.getCell( x3, y3 )!=null ){
            imgGreen.setPosition( x0, y0 );
        }                
    }
    
    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 0.5f, 0.5f, 0.5f, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        renderer.setView(camera);
        renderer.render();
        
    
        currentTime += Gdx.graphics.getDeltaTime();
        if( currentTime - clicktimeX > 0.15f ){
            MoveX();
        }
        if( currentTime - clicktimeY > 0.15f ){
            MoveY();
        }
            
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

    
}