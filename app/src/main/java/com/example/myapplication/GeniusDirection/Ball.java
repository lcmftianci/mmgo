package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class Ball {
    private static final float BALL_RADIUS = 0.15f;// 球半径
    private final Random rand = new Random();
    public Body ballModels;     //小球body
    public Sprite ballSprite;//精灵
    public int type;         //小球的类型
    public Ball(World world, int type, Camera camera) {
        BodyDef ballBodyDef = new BodyDef();    //生成一个def
        ballBodyDef.type = BodyDef.BodyType.DynamicBody;  //定义成小球是不受控制的动态物体
        CircleShape shape = new CircleShape(); //定义小球的形状
        shape.setRadius(BALL_RADIUS);
 
        FixtureDef fd = new FixtureDef();    //生成一个Fixture
        fd.density = 1;                          //密度
        fd.friction = 0f;                     //摩擦力
        fd.restitution = 0.5f;                 //弹力
        fd.shape = shape;                     
        ballModels = world.createBody(ballBodyDef);  //为body加载def
            ballModels.createFixture(fd);//为body创建fixture
            shape.dispose();
        reset(type, camera);
    }
 
    public void reset(int type, Camera camera){
        this.type = type;
        float tx = rand.nextFloat() * 1.0f - 0.4f;
        float ty = camera.position.y + camera.viewportHeight/2 + BALL_RADIUS;
        float angle = rand.nextFloat() * MathUtils.PI * 2;
        Vector2 vec = new Vector2();
        ballModels.setActive(true);
        ballModels.setAwake(true);
        ballModels.setLinearVelocity(vec.set(0, 0));
        ballModels.setAngularVelocity(0);
        ballModels.setTransform(vec.set(0.3f, 10), angle);  //小球创建的初始位置
        Texture tex = GameCenter.balls.elementAt(type);
        ballSprite = new Sprite(tex);   //生成一个精灵
        ballSprite = new Sprite();
        ballSprite.setSize(BALL_RADIUS*2, BALL_RADIUS*2);
        ballSprite.setOrigin(BALL_RADIUS, BALL_RADIUS);
    }
 
}