package com.example.myapplication.BingGo;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class BgWorldRenderer {

    private final String TAG = "BgWorldRenderer";
    BgWorld world;
    static final float FRUSTUM_WIDTH = 10;
    static final float FRUSTUM_HEIGHT = 15;
    OrthographicCamera cam;
    SpriteBatch batch;
    Vector3 touchPoint;
    ShapeRenderer shapeRenderer;
    Color lineColor;

    boolean torchflag;
    Vector3 touchPrePt;

    public BgWorldRenderer(SpriteBatch batch, BgWorld world) {
        this.world = world;
        this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        this.batch = batch;
        touchPoint = new Vector3();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        this.lineColor = Color.BLACK;
        torchflag = false;
        touchPrePt = new Vector3();
    }

    public void render () {
        if (world.ball.position.y > cam.position.y) cam.position.y = world.ball.position.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
        renderBezierLine();
    }

    public void renderBackground () {
        batch.disableBlending();
        batch.begin();
        batch.draw(BgAsserts.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        batch.end();
    }

    public void renderObjects(){
        batch.enableBlending();
        batch.begin();
        renderSpirit();
        batch.end();
    }

    public void renderSpirit(){
        this.cam.unproject(touchPoint.set(world.ball.position.x, world.ball.position.y, 0));
        batch.draw(BgAsserts.ball, touchPoint.x, touchPoint.y - 0.5f, 1, 1);
        Log.d(TAG, "spirit x:" + touchPoint.x + " y:" + touchPoint.y );

//        shapeRenderer.setProjectionMatrix(this.cam.combined);
//        shapeRenderer.setColor(this.lineColor);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.ellipse(5,5,touchPoint.x,touchPoint.y);
//        shapeRenderer.line(1,1,touchPoint.x,touchPoint.y);
//        shapeRenderer.end();
    }

    public void renderBezierLine(){
        //this.cam.update();
        //batch.begin();
        //batch.flush();
        shapeRenderer.setProjectionMatrix(this.cam.combined);
        if(!torchflag && Gdx.input.isTouched()) {
            this.cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            torchflag = true;
            touchPrePt.x = touchPoint.x;
            touchPrePt.y = touchPoint.y;
            Log.d(TAG, "torchflag isTouched x:" + touchPoint.x + " y:" + touchPoint.y );
        }else if(Gdx.input.isTouched()){
            //this.cam.unproject(touchPoint.set(world.ball.position.x, world.ball.position.y, 0));

            this.cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));


            //shapeRenderer.flush();

            shapeRenderer.setColor(this.lineColor);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            //绘制第一个点
            //shapeRenderer.ellipse(5,5,3,3);
            shapeRenderer.line(touchPrePt.x, touchPrePt.y, touchPoint.x, touchPoint.y);
            shapeRenderer.end();

//            this.cam.unproject(touchPoint.set(world.ball.position.x, world.ball.position.y, 0));
//            touchPrePt.x = touchPoint.x;
//            touchPrePt.y = touchPoint.y;
            Log.d(TAG, "isTouched x:" + touchPoint.x + " y:" + touchPoint.y );
            Log.d(TAG, "isTouched pre x:" + touchPrePt.x + " y:" + touchPrePt.y );
        }else{
            torchflag = false;
            Log.d(TAG, "torchflag x:" + touchPrePt.x + " y:" + touchPrePt.y );
        }
    }
}
