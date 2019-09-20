package com.example.myapplication.Flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public static Skin skin;
    public static AssetManager manager;
    public static TextureAtlas atlas; 
    public static TextureRegion[] sRegionBird;
    
    public static TextureRegion regionPipeUp;
    public static TextureRegion regionPipeDown;
    public static TextureRegion regionBack;
    
    public static void init(){
        skin = new Skin( Gdx.files.internal( "skin/uiskin.json" ) );
        atlas = new TextureAtlas( Gdx.files.internal( "data/bird.pack" ) );
        
        sRegionBird = new TextureRegion[3]; 
        for( int i=0; i<sRegionBird.length; ++i ){
            sRegionBird[i] = atlas.findRegion( "bird_blue", i );            
        }
        
        regionPipeUp = atlas.findRegion( "bound_up" );
        regionPipeDown = atlas.findRegion( "bound_down" );
        regionBack = atlas.findRegion( "background" );

    }
    
    public static void clean(){
        if( skin != null ) skin.dispose();
        if( atlas != null ) atlas.dispose();
        
    }
}