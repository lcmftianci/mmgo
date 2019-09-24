package com.example.myapplication.advancestrangely;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FirstGame implements ApplicationListener {
    //绘图用的SpriteBatch
    private SpriteBatch batch;
    private Texture texture;
    //private TextureRegion region;
    private Sprite sprite;

    int inx = 0;

    @Override
    public void create() {
        batch = new SpriteBatch(); //实例化
        texture = new Texture(Gdx.files.internal("bomb/bomb.jpg"));
        //region = new TextureRegion(texture, 30,80, 200,200);
        sprite = new Sprite(texture, 138, 158, 306 - 138,378 - 157);
        sprite.setPosition(100, 10); //位置
        sprite.setRotation(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }
    @Override
    public void dispose() {
// TODO Auto-generated method stub
    }
    @Override
    public void pause() {
// TODO Auto-generated method stub
    }

    public void update(){
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            inx++;
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //清屏
        batch.begin();
        //batch.draw(sprite, 0, 0);
        sprite.setRotation(15 + inx*15);
        sprite.draw(batch);
        batch.end();
    }
    @Override
    public void resize(int width, int height) {
// TODO Auto-generated method stub
    }
    @Override
    public void resume() {
// TODO Auto-generated method stub
    }
}
