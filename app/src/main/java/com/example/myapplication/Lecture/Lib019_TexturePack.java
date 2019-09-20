package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class Lib019_TexturePack extends ApplicationAdapter{

    TextureAtlas atlas;
    TextureRegion region1, region2;
    SpriteBatch batch;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        PixmapPacker packer = new PixmapPacker( 512, 512, Format.RGB565, 2, true );
        //packer.pack( "first", pixmap1 );
        Pixmap pixmap1 = new Pixmap( Gdx.files.internal( "data/badlogic.jpg" ) );
        Pixmap pixmap2 = new Pixmap( Gdx.files.internal( "data/pal4_1.jpg" ) );
        packer.pack( "first", pixmap1 );
        packer.pack( "second", pixmap2 );
        
        atlas = packer.generateTextureAtlas( TextureFilter.Nearest, TextureFilter.Nearest, false );
        region1 = atlas.findRegion( "first" );
        region2 = atlas.findRegion( "second" );
        
        pixmap1.dispose();
        pixmap2.dispose();
        
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor( 0, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        batch.draw( region1, 10, 10 );
        batch.draw( region2, 20+region1.getRegionWidth(), 10 );
        batch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        atlas.dispose();
        super.dispose();
    }

}