package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.microedition.khronos.opengles.GL10;


public class Lib054_SegmentOverlap extends ApplicationAdapter{

    ShapeRenderer rend;
    
    Vector2 p1 = new Vector2(300, 100);
    Vector2 p2 = new Vector2(500, 200);
    Vector2 p3 = new Vector2(300, 200);
    Vector2 p4 = new Vector2(400, 300);
    
    Rectangle rect = new Rectangle( 100, 100, 200, 200 );
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        rend = new ShapeRenderer();
        Gdx.input.setInputProcessor(adapter);
    }

    
    public static float min(float x, float y) { return x<y? x: y; }
    public static float max(float x, float y) { return x>y? x: y; }
    
    public static boolean isSegmentOverlap(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4){
        if(p3.x == p4.x){
            float x = p3.x;
            float y = p1.y + (p3.x-p1.x)*(p2.y-p1.y)/(p2.x-p1.x);
            //System.out.println(y);
            if( x>min(p1.x, p2.x) && x<max(p1.x, p2.x)  &&  y>min(p1.y, p2.y) && y<max(p1.y, p2.y)  && 
                x>=min(p3.x, p4.x) && x<=max(p3.x, p4.x)  &&  y>min(p3.y, p4.y) && y<max(p3.y, p4.y)    ){
                return true;
            }
        }
        else if(p3.y == p4.y){
            float x = p1.x + (p3.y-p1.y)*(p2.x-p1.x)/(p2.y-p1.y);
            float y = p3.y;            
            if( x>min(p1.x, p2.x) && x<max(p1.x, p2.x)  &&  y>min(p1.y, p2.y) && y<max(p1.y, p2.y)  && 
                x>min(p3.x, p4.x) && x<max(p3.x, p4.x)  &&  y>=min(p3.y, p4.y) && y<=max(p3.y, p4.y) ){
                return true;
            }
        }
        else if(p1.x==p2.x || p1.y==p2.y){
            return isSegmentOverlap(p3, p4, p1, p2);
        }
        else{
            float k1 = (p2.y-p1.y)/(p2.x-p1.x);
            float k2 = (p4.y-p3.y)/(p4.x-p3.x);
            float x = (k2*p3.x-k1*p1.x+p1.y-p3.y)/(k2-k1);
            float y = k1*(x-p1.x) + p1.y;            
            //System.out.println( k1 + "," + k2 + "," + x + "," + y );            
            if( x>min(p1.x, p2.x) && x<max(p1.x, p2.x)  &&  y>min(p1.y, p2.y) && y<max(p1.y, p2.y)  && 
                x>min(p3.x, p4.x) && x<max(p3.x, p4.x)  &&  y>min(p3.y, p4.y) && y<max(p3.y, p4.y) ){
                return true;
            }            
        }
        
        return false;
    }
    
    
    public static boolean isSegRectOverlap(Vector2 p1, Vector2 p2, Rectangle rect){
        float x = rect.x, y = rect.y, w = rect.width, h = rect.height;
        Vector2 rp1 = new Vector2(x, y);
        Vector2 rp2 = new Vector2(x+w, y);
        Vector2 rp3 = new Vector2(x+w, y+h);
        Vector2 rp4 = new Vector2(x, y+h);
        //return isSegmentOverlap(p1, p2, rp1, rp2) || isSegmentOverlap(p1, p2, rp2, rp3) || 
        //        isSegmentOverlap(p1, p2, rp3, rp4) || isSegmentOverlap(p1, p2, rp4, rp1);
        
        if( rect.contains(p1) || rect.contains(p2) ){
            return true;
        }
        
        return isSegmentOverlap(p1, p2, rp1, rp2) || isSegmentOverlap(p1, p2, rp2, rp3) || 
                isSegmentOverlap(p1, p2, rp3, rp4) || isSegmentOverlap(p1, p2, rp4, rp1);
    }
    
    
    
    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        rend.begin(ShapeType.Line);
        
        if(isSegmentOverlap(p1, p2, p3, p4)){            
            rend.setColor(Color.RED);
        }
        else{
            rend.setColor(Color.BLUE);
        }
        rend.line(p1, p2);
        rend.line(p3, p4);
        
        
//        if(isSegRectOverlap(p1, p2, rect)){
//            rend.setColor(Color.RED);
//        }
//        else{
//            rend.setColor(Color.BLUE);
//        }    
//        rend.line(p1, p2);
//        rend.rect(rect.x, rect.y, rect.width, rect.height);
        
        
        rend.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
    
    
    InputAdapter adapter = new InputAdapter(){
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {        
            p1.set(screenX, 480-screenY);            
            return super.touchDown(screenX, screenY, pointer, button);
        }
        
        
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            p2.set(screenX, 480-screenY);
            return super.touchDragged(screenX, screenY, pointer);
        }


        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            p2.set(screenX, 480-screenY);
            return super.touchUp(screenX, screenY, pointer, button);
        }
    };
}