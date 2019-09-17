package com.example.myapplication;

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
    @Override
    public void create() {
        batch = new SpriteBatch(); //实例化
        texture = new Texture(Gdx.files.internal("data/panda.jpg"));
        //region = new TextureRegion(texture, 30,80, 200,200);
        sprite = new Sprite(texture, 80, 80, 400, 300);
        sprite.setPosition(10, 10); //位置
        sprite.setRotation(15);
    }
    @Override
    public void dispose() {
// TODO Auto-generated method stub
    }
    @Override
    public void pause() {
// TODO Auto-generated method stub
    }
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //清屏
        batch.begin();
        //batch.draw(sprite, 0, 0);
        for(int i = 0; i < 10; i++)
        {
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            sprite.setRotation(15 + i*15);
            sprite.draw(batch);
        }
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
