package com.example.myapplication.BingGo;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class BgWorldRenderer {

    private final String TAG = "BgWorldRenderer";
    BgWorld world;
    static final float FRUSTUM_WIDTH = 10;
    static final float FRUSTUM_HEIGHT = 15;
    OrthographicCamera cam;
    SpriteBatch batch;
    Vector3 touchPoint;

    public BgWorldRenderer(SpriteBatch batch, BgWorld world) {
        this.world = world;
        this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        this.batch = batch;
        touchPoint = new Vector3();
    }

    public void render () {
        if (world.ball.position.y > cam.position.y) cam.position.y = world.ball.position.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
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
        this.cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        batch.draw(BgAsserts.ball, touchPoint.x, touchPoint.y - 0.5f, 1, 1);
        Log.d(TAG, "x:" + touchPoint.x + " y:" + touchPoint.y );
    }
}
