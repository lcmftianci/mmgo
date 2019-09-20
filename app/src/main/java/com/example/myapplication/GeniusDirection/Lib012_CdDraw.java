package com.example.myapplication.GeniusDirection;

//import sun.security.provider.certpath.Vertex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.microedition.khronos.opengles.GL10;

public class Lib012_CdDraw extends ApplicationAdapter{

    ShapeRenderer rend;
    
    float[] vertexs;
    
    Pixmap pixmap;
    float angle = 0;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        rend = new ShapeRenderer();
        
        vertexs = new float[]{ 100, 100, 250, 100, 200, 200, 100, 250 };
        
    }
    
    
    public void FillPolygon( float[] vertexs, ShapeRenderer rend, Color color ){
        if( vertexs.length%2 != 0 ){
            return;
        }
        rend.begin( ShapeType.Filled );
        rend.setColor( color );
        for( int i=2; i<=vertexs.length-4; i+=2 ){
            rend.triangle( vertexs[0], vertexs[1], vertexs[i], vertexs[i+1], vertexs[i+2], vertexs[i+3] );
        }
        rend.end();
    }
    
    public float Change( float angle0 ){
        return (float)Math.tan( angle0*(float)Math.PI/180 );
    }
    
    public void FillRect( float x, float y, float w, float h, float start, float angle, ShapeRenderer rend, Color color ){
        
        rend.begin( ShapeType.Line );
        rend.setColor( color );
        rend.rect( x-w, y-h, 2*w, 2*h );
        rend.end();
        
        rend.begin( ShapeType.Filled );
        rend.setColor( color );
        //angle = angle*(float)Math.PI/180;
        
        if( angle <= 45 ){
            rend.triangle( x, y, x+w, y, x+w, y+w*(float)Math.tan( angle*(float)Math.PI/180 ) );    
        }
        else if( angle <= 135 ){
            rend.triangle( x, y, x+w, y, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+h*(float)Math.tan( (90-angle)*(float)Math.PI/180 ), y+h );
        }
        else if( angle <= 135+90 ){
            rend.triangle( x, y, x+w, y, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x-w, y+h );
            rend.triangle( x, y, x-w, y+h, x-w, y-w*Change( angle ) );
        }
        else if( angle <= 135+180 ){
            rend.triangle( x, y, x+w, y, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x-w, y+h );
            rend.triangle( x, y, x-w, y+h, x-w, y-h );
            rend.triangle( x, y, x-w, y-h, x-h*Change( 270-angle ), y-h );
        }
        else if( angle <= 360 ){
            rend.triangle( x, y, x+w, y, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x-w, y+h );
            rend.triangle( x, y, x-w, y+h, x-w, y-h );
            rend.triangle( x, y, x-w, y-h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x+w, y+w*Change(angle) );
        }

        rend.end();        
    }
    
    public float GetAtan( float angle ){
        return (float)( 180*Math.atan( angle )/Math.PI );
    }

    public Vector2 GetPoint( float x, float y, float w, float h, float angle ){
        Vector2 point = new Vector2();
        while( angle >= 360 ){
            angle -= 360;
        }
        while( angle < 0 ){
            angle += 360;
        }
        
        System.out.println( GetAtan( h/w ) );
        
        if( angle>=0 && angle<GetAtan( h/w ) || angle>=360-GetAtan( h/w ) && angle<360 ){
            point.x = x+w;
            point.y = y+w*Change( angle );
        }
        else if( angle>=GetAtan( h/w ) && angle<180-GetAtan( h/w ) ){
            point.x = x+h*Change( 90-angle );
            point.y = y+h;            
        }
        else if( angle>=180-GetAtan( h/w ) && angle<180+GetAtan( h/w ) ){
            point.x = x-w;
            point.y = y-w*Change( angle );            
        }
        else if( angle>=180+GetAtan( h/w ) && angle<360-GetAtan( h/w ) ){
            point.x = x-h*Change( 90-angle );
            point.y = y-h;                
        }
        
        return point;
    }
    
    
    public void FillRect1( float x, float y, float w, float h, float start, float angle, ShapeRenderer rend, Color color ){
        
        rend.begin( ShapeType.Line );
        rend.setColor( color );
        rend.rect( x-w, y-h, 2*w, 2*h );
        rend.end();
        
        rend.begin( ShapeType.Filled );
        rend.setColor( color );
        //angle = angle*(float)Math.PI/180;
        
        if( angle <= 45 ){
            rend.triangle( x, y, x, y+h, x+h*Change( angle ), y+h );    
        }
        else if( angle <= 135 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y+w*Change( 90-angle ) );
        }
        else if( angle <= 135+90 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x-h*Change( angle ), y-h );
        }
        else if( angle <= 135+180 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x-w, y-h );
            rend.triangle( x, y, x-w, y-h, x-w, y-w*Change( 270-angle ) );
        }
        else if( angle <= 360 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x-w, y-h );
            rend.triangle( x, y, x-w, y-h, x-w, y+h );
            rend.triangle( x, y, x-w, y+h, x+h*Change( angle ), y+h );
        }

        rend.end();        
    }
    

    public void FillRect2( float x, float y, float w, float h, float start, float angle, ShapeRenderer rend, Color color ){
        
        rend.begin( ShapeType.Line );
        rend.setColor( color );
        rend.rect( x-w, y-h, 2*w, 2*h );
        rend.end();
        
        rend.begin( ShapeType.Filled );
        rend.setColor( color );
        //angle = angle*(float)Math.PI/180;
        rend.rect( x-w, y-h, 2*w, 2*h );
        
        //rend.setColor( color.mul( 0.5f ) );
        
        if( angle <= 45 ){
            rend.triangle( x, y, x, y+h, x+h*Change( angle ), y+h );    
        }
        else if( angle <= 135 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y+w*Change( 90-angle ) );
        }
        else if( angle <= 135+90 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x-h*Change( angle ), y-h );
        }
        else if( angle <= 135+180 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x-w, y-h );
            rend.triangle( x, y, x-w, y-h, x-w, y-w*Change( 270-angle ) );
        }
        else if( angle <= 360 ){
            rend.triangle( x, y, x, y+h, x+w, y+w );
            rend.triangle( x, y, x+w, y+h, x+w, y-h );
            rend.triangle( x, y, x+w, y-h, x-w, y-h );
            rend.triangle( x, y, x-w, y-h, x-w, y+h );
            rend.triangle( x, y, x-w, y+h, x+h*Change( angle ), y+h );
        }

        rend.end();        
    }
    
    
    public void FillPartRect( float x, float y, float w, float h, float start, float angle, ShapeRenderer rend, Color color  )
    {
        rend.begin( ShapeType.Line );
        rend.setColor( color );
        rend.rect( x-w, y-h, 2*w, 2*h );
        rend.end();
        
        rend.begin( ShapeType.Filled );
        
        for( int i=0; i<angle-1; i++ ){
            Vector2 point1 = GetPoint( x, y, w, h, start+i );
            Vector2 point2 = GetPoint( x, y, w, h, start+i+1 );
            rend.triangle( x, y, point1.x, point1.y, point2.x, point2.y );
        } 
        rend.end();
    }
    
    public void FillCircle(  ){
        
    }
    
    

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 0, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        Gdx.gl.glEnable( GL10.GL_BLEND );
        Gdx.gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
        
/*        rend.begin( ShapeType.Filled );
        rend.setColor( Color.BLUE );
        //rend.polyline( vertexs );
        //rend.line( 100, 100, 200, 200 );
        //rend.triangle( 100, 100, 200, 100, 100, 200 );
        
        rend.arc( 100, 100, 100, 0, 60 );
        rend.circle( 300, 300, 100 );    
        rend.end();*/

        //FillPolygon( vertexs, rend, Color.BLUE );
        
        angle += 0.5f;
/*        if( angle > 360 ){
            angle -= 360;
        }*/
        
        //angle = 
        //FillRect( 300, 300, 100, 100, 0, angle, rend, Color.BLUE );
            
        
        //FillRect1( 300, 300, 100, 100, 0, angle, rend, new Color( 1f, 0.7f, 0.7f, 0.5f ));
        //FillRect( 300, 300, 100, 100, 0, 90, rend, new Color( 1, 1, 1, 0 ) );
        //FillRect2( 300, 300, 100, 100, 0, angle, rend, Color.BLUE);
        
        FillPartRect( 300, 300, 100, 100, 90, 360-angle, rend, Color.BLUE);
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        rend.dispose();
        super.dispose();
    }

}