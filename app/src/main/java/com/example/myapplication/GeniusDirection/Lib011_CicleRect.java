package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import javax.microedition.khronos.opengles.GL10;

public class Lib011_CicleRect extends ApplicationAdapter{

    ShapeRenderer rend;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        rend = new ShapeRenderer();
    }

    public void DrawCircleRect( float x, float y, float width, float height, float radius, ShapeRenderer rend, Color color ){
        rend.setColor( color );
        rend.begin(ShapeType.Line);
        
        //rend.rect( x, y, width, height );
        rend.line( x+radius, y, x+width-radius, y );
        rend.line( x+radius, y+height, x+width-radius, y+height );
        rend.line( x, y+radius, x, y+height-radius );
        rend.line( x+width, y+radius, x+width, y+height-radius );
/*        
        rend.arc( x+width-radius, y+height-radius, radius, 0, 90 );
        rend.arc( x+radius, y+height-radius, radius, 90, 90 );        
        rend.arc( x+radius, y+radius, radius, 180, 90 );
        rend.arc( x+width-radius, y+radius, radius, 270, 90 );
        */
    
        int segments = (int)( 6*(float)Math.cbrt( radius ) );
        
        for( int i=0; i<segments; i++ ){
            float x0 = x+width-radius;
            float y0 = y+height-radius;
            //float angle1 = (float)Math.PI*i*9f/180f;
            //float angle2 = (float)Math.PI*(i+1)*9f/180f;
            float angle1 = (float)Math.PI*i/(2*segments);
            float angle2 = (float)Math.PI*(i+1)/(2*segments);
            rend.line( x0+radius*(float)Math.cos(angle1), y0+radius*(float)Math.sin(angle1), x0+radius*(float)Math.cos(angle2), y0+radius*(float)Math.sin(angle2) );
        }
        
        for( int i=0; i<segments; i++ ){
            float x0 = x+radius;
            float y0 = y+height-radius;
            float angle1 = (float)Math.PI*(i+segments)/(2*segments);
            float angle2 = (float)Math.PI*(i+segments+1)/(2*segments);
            rend.line( x0+radius*(float)Math.cos(angle1), y0+radius*(float)Math.sin(angle1), x0+radius*(float)Math.cos(angle2), y0+radius*(float)Math.sin(angle2) );
        }
        
        for( int i=0; i<segments; i++ ){
            float x0 = x+radius;
            float y0 = y+radius;
            float angle1 = (float)Math.PI*(i+segments*2)/(2*segments);
            float angle2 = (float)Math.PI*(i+segments*2+1)/(2*segments);
            rend.line( x0+radius*(float)Math.cos(angle1), y0+radius*(float)Math.sin(angle1), x0+radius*(float)Math.cos(angle2), y0+radius*(float)Math.sin(angle2) );
        }        
        for( int i=0; i<segments; i++ ){
            float x0 = x+width-radius;
            float y0 = y+radius;
            float angle1 = (float)Math.PI*(i+segments*3)/(2*segments);
            float angle2 = (float)Math.PI*(i+segments*3+1)/(2*segments);
            rend.line( x0+radius*(float)Math.cos(angle1), y0+radius*(float)Math.sin(angle1), x0+radius*(float)Math.cos(angle2), y0+radius*(float)Math.sin(angle2) );
        }    
        
        rend.end();
    }
    
    @Override
    public void render() {
        // TODO Auto-generated method stub    
        Gdx.gl.glClearColor( 0.5f, 0.5f, 0.5f, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        DrawCircleRect( 10, 10, 300, 200, 10, rend, Color.RED );
        DrawCircleRect( 100, 60, 300, 200, 20, rend, Color.BLUE );
        DrawCircleRect( 200, 110, 300, 200, 30, rend, Color.CYAN );
        DrawCircleRect( 450, 160, 300, 200, 50, rend, Color.YELLOW );
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        rend.dispose();
        super.dispose();
    }

}