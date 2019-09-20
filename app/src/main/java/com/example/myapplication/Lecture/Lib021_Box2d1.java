package com.example.myapplication.Lecture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.utils.Array;

import javax.microedition.khronos.opengles.GL10;

public class Lib021_Box2d1 extends ApplicationAdapter{

    World world;
    OrthographicCamera camera;
    //DebugRenderer debugRenderer;
    Box2DDebugRenderer debugRenderer;
    Body bodyBox, bodyCircle;
    TextureRegion region;
    Sprite sprite;
    SpriteBatch batch;
    final Array<Body> arrBody = new Array<Body>();
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        camera = new OrthographicCamera();
        camera.setToOrtho( false, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10 );
        debugRenderer = new Box2DDebugRenderer();
        world = new World( new Vector2(0, -10), true  );
        
        batch = new SpriteBatch();
        region = new TextureRegion( new Texture( Gdx.files.internal( "ball.jpg" ) ) );
        sprite = new Sprite( region );
        sprite.setSize( 4, 4 );
        
        
        BodyDef bodyGroundDef = new BodyDef();
        bodyGroundDef.type = BodyType.StaticBody;
        bodyGroundDef.position.set( 0, 2 );
        Body bodyGround = world.createBody( bodyGroundDef );
        
        PolygonShape shapeGround = new PolygonShape();
        shapeGround.setAsBox( camera.viewportWidth, 1 );
        bodyGround.createFixture( shapeGround, 1 );
        shapeGround.dispose();
        
        
        BodyDef bodyBoxDef = new BodyDef();
        bodyBoxDef.type = BodyType.DynamicBody;
        bodyBoxDef.position.set( 5, 60 );
        bodyBox = world.createBody( bodyBoxDef );
        PolygonShape shapeBox = new PolygonShape();
        shapeBox.setAsBox( 2, 2 );    
        FixtureDef fixtureDefBox = new FixtureDef();
        fixtureDefBox.friction = 0.1f;
        fixtureDefBox.shape = shapeBox;
        fixtureDefBox.restitution = 0.3f;
        //bodyBox.createFixture( shapeBox, 1 );
        bodyBox.createFixture( fixtureDefBox );
        shapeBox.dispose();
        //bodyBox.setUserData( sprite );
            
        BodyDef bodyCircleDef = new BodyDef();
        bodyCircleDef.type = BodyType.DynamicBody;
        bodyCircleDef.position.set( 20, 30 );
        bodyCircle = world.createBody( bodyCircleDef );
        CircleShape shapeCircle = new CircleShape();
        shapeCircle.setRadius(2);    
        FixtureDef fixtureDefCir = new FixtureDef();
        fixtureDefCir.friction = 0.1f;
        fixtureDefCir.shape = shapeCircle;
        fixtureDefCir.restitution = 0.3f;
        bodyCircle.createFixture( fixtureDefCir );
        shapeCircle.dispose();
                    
        InputAdapter processor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                if( keycode == Input.Keys.A ){
                    //bodyBox.applyForce( 2f, 0, bodyBox.getPosition().x, bodyBox.getPosition().y, true );
                    //bodyBox.applyForceToCenter( 2f, 2f, true );
                    bodyBox.applyLinearImpulse( 12f, 0, bodyBox.getPosition().x, bodyBox.getPosition().y, true );
                    System.out.println( "a" );
                }
                return super.keyDown(keycode);
            }        
        };
        Gdx.input.setInputProcessor( processor );
        
        
        world.setContactListener( new ContactListener(){
            @Override
            public void beginContact(Contact contact) {
                // TODO Auto-generated method stub
                System.out.println( "begin" );
                Body body1 = contact.getFixtureA().getBody();
                Body body2 = contact.getFixtureB().getBody();
                System.out.println( body1.getPosition().x + "," + body1.getPosition().y );
                System.out.println( body2.getPosition().x + "," + body2.getPosition().y );
                
                if( body1.getType() == BodyType.DynamicBody ){
                    body1.setUserData( sprite );
                }
                if( body2.getType() == BodyType.DynamicBody ){
                    body2.setUserData( sprite );                    
                }
            }

            @Override
            public void endContact(Contact contact) {
                // TODO Auto-generated method stub
                System.out.println( "end" );
                
                Body body1 = contact.getFixtureA().getBody();
                Body body2 = contact.getFixtureB().getBody();    
                if( body1.getType() == BodyType.DynamicBody ){
                    body1.setUserData( null );
                }
                if( body2.getType() == BodyType.DynamicBody ){
                    body2.setUserData( null );                    
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                // TODO Auto-generated method stub            
            }
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                // TODO Auto-generated method stub                
            }
            
        });
        
        
        DistanceJointDef jointDefDis = new DistanceJointDef();
        jointDefDis.bodyA = bodyBox;
        jointDefDis.bodyB = bodyCircle;
        jointDefDis.type = JointType.DistanceJoint;
        jointDefDis.length = 10;
        Joint jointDis = world.createJoint( jointDefDis );
        
        
/*        RevoluteJointDef jointDefRevo = new RevoluteJointDef();
        //jointDefRevo.bodyA = bodyBox;
        //jointDefRevo.bodyB = bodyCircle;
        jointDefRevo.initialize( bodyBox, bodyCircle, bodyCircle.getWorldCenter() );
        jointDefRevo.type = JointType.RevoluteJoint;
        Joint jointRevo = world.createJoint( jointDefRevo );*/        
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        
        if( Gdx.input.isKeyPressed( Input.Keys.D ) ){
            bodyBox.applyForce( 12f, 0, bodyBox.getPosition().x, bodyBox.getPosition().y, true );
        }
        
        world.step( Gdx.graphics.getDeltaTime(), 6, 2 );
        camera.update();
    
        batch.setProjectionMatrix( camera.combined );
        batch.begin();

        world.getBodies( arrBody );
        for( int i=0; i<arrBody.size; ++i ){
            Body body = arrBody.get(i);
            Sprite sprite0 = (Sprite)body.getUserData();
            if( sprite0 != null ){
                sprite0.setPosition( body.getPosition().x-2, body.getPosition().y-2 );
                sprite0.setRotation( MathUtils.radiansToDegrees*body.getAngle() );
                sprite0.draw( batch );
            }
        }
        batch.end();
        
        debugRenderer.render( world, camera.combined );
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}