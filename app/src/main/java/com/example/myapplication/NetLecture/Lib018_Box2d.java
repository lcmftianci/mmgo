package com.example.myapplication.NetLecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import javax.microedition.khronos.opengles.GL10;

public class Lib018_Box2d extends ApplicationAdapter{

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    ShapeRenderer rend;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        world = new World( new Vector2( 0, -10f ), true );
        debugRenderer =new Box2DDebugRenderer();
        
        camera = new OrthographicCamera();
        camera.setToOrtho( false, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10 );
            
        
        ////create the body of box
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = BodyType.DynamicBody;
        //boxBodyDef.position.x = -24 + (float)(Math.random() * 48);
        //boxBodyDef.position.y = 10 + (float)(Math.random() * 100);
        boxBodyDef.position.set( 40, 50 );
        Body boxBody = world.createBody(boxBodyDef);
        
        PolygonShape boxPoly = new PolygonShape();
        boxPoly.setAsBox(2, 1);        
        boxBody.createFixture(boxPoly, 1);
        boxPoly.dispose();
            
        
        /////create the body of circle
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set( 60, 100 );
        Body bodyCircle = world.createBody( bodyDef );
        
        CircleShape circle = new CircleShape();
        circle.setRadius( 2 );    
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        //fixtureDef.restitution = 0.6f;
        
        bodyCircle.createFixture( fixtureDef );    
        circle.dispose();
        
            
        
        /////create the body of ground, static body, can't move
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyType.StaticBody;
        groundBodyDef.angle = (float)Math.PI*15/180;
        groundBodyDef.position.set( 0, 0 );
        Body groundBody = world.createBody( groundBodyDef );
        
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox( camera.viewportWidth+5, 0.5f );
        groundBody.createFixture( groundBox, 0.0f );
        groundBox.dispose();
            
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        //Gdx.gl.glClearColor( 1, 1, 1, 0.2f );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        world.step( Gdx.graphics.getDeltaTime(), 6, 2 );
        camera.update();
        
        debugRenderer.render( world, camera.combined );
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        debugRenderer.dispose();
        world.dispose();
        super.dispose();
    }

}