package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import javax.microedition.khronos.opengles.GL10;

public class Lib032_CircleRect extends ApplicationAdapter{

    Rectangle rect;
    Circle circle;
    ShapeRenderer rend;
    SpriteBatch batch;
    BitmapFont font;
    
    public static boolean IsInside( float x, float y, Circle circle ){
        float disX = x - circle.x;
        float disY = y - circle.y;
        return disX*disX + disY*disY <= circle.radius*circle.radius;
    }
    
    public static float Distance( float x1, float y1, float x2, float y2 ){
        return (float)Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );
    }
    
    public static boolean IsOverlap( Rectangle rect0, Circle circle0 ){
        float x1 = rect0.x, y1 = rect0.y, w = rect0.width, h = rect0.height;
        float cx1 = x1+w/2, cy1 = y1+h/2;
        float x2 = circle0.x, y2 = circle0.y, r = circle0.radius;
        /////overlap with the corners
        if( IsInside(x1,y1,circle0) || IsInside(x1+w,y1,circle0) || IsInside(x1,y1+h,circle0) || IsInside(x1+w,y1+h,circle0) ){
            return true;
        }
        ////overlap with the left or the right edge or s inside of the rect
        if( Distance( cx1, cy1, x2, y2 )<w/2+r && Math.abs( cy1-y2 )<h/2 ){
            return true;
        }
        ////overlap with the top or the bottom edge or s inside of the rect
        if( Distance( cx1, cy1, x2, y2 )<h/2+r && Math.abs( cx1-x2 )<w/2 ){
            return true;
        }
        
        return false;
    }
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        rect = new Rectangle( 10, 10, 100, 50 );
        circle = new Circle( 400, 240, 100 );
        
        rend = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor( Color.BLACK );
        
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        float step = 1.5f;
        if( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ){
            rect.x -= step;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.RIGHT ) ){
            rect.x += step;
        }        
        else if( Gdx.input.isKeyPressed( Input.Keys.UP ) ){
            rect.y +=step;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.DOWN ) ){
            rect.y -= step;
        }
        
        if( Gdx.input.isKeyPressed( Input.Keys.A ) ){
            rect.width -= step;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.D ) ){
            rect.width += step;
        }        
        else if( Gdx.input.isKeyPressed( Input.Keys.W ) ){
            rect.height +=step;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.S ) ){
            rect.height -= step;
        }
        
        rend.begin( ShapeType.Line );
        rend.setColor( Color.BLUE );
        rend.rect( rect.x, rect.y, rect.width, rect.height );
        rend.circle( circle.x, circle.y, circle.radius );
        rend.end();
        
        String str;
        if( IsOverlap( rect, circle ) ){
            str = "true";
        }
        else{
            str = "false";
        }
        
        batch.begin();
        font.draw( batch, str, 750, 20 );
        batch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}