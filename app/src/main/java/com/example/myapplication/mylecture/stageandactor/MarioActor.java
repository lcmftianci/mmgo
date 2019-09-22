package com.example.myapplication.mylecture.stageandactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MarioActor extends Actor {
    public static float x;
    public static float y;
    public static float statetime;

    Texture texture;
    TextureRegion region;
    ImageButton buttonL;
    ImageButton buttonR;

    Animation aniRight;
    Animation aniLeft;
    Animation aniIdle;

    enum STATE{
        Left, Right, Idle
    }

    STATE state;

    public MarioActor(float x, float y){
        this.x = x;
        this.y = y;
        state = STATE.Idle;
        this.show();
    }

    public void update(){
        if(state == STATE.Left){
            this.x  -= 1.5f;
            if(x < 20)this.x = 20;
        }else if(state == STATE.Right){
            this.x += 1.5f;
            if(this.x > 400)this.x = 400;
        }
        this.x = x;
    }

    public void aniCheck(){
        if(state == STATE.Left){
            region = aniLeft.getKeyFrame(statetime, true);
        }else if(state == STATE.Right){
            region = aniRight.getKeyFrame(statetime, true);
        }else {
            region = aniIdle.getKeyFrame(statetime, true);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        statetime += Gdx.graphics.getDeltaTime();
        this.update();
        this.aniCheck();
        batch.draw(region, x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void show(){
        texture = new Texture(Gdx.files.internal("mario/mario.png"));
        TextureRegion[][] split = TextureRegion.split(texture, 64, 64);
        TextureRegion[][] mirror = TextureRegion.split(texture, 64, 64);
        for(TextureRegion[] region1 : mirror)
            for(TextureRegion region2 : region1)
                region2.flip(true,false);

        TextureRegion[] regionR = new TextureRegion[3];
        regionR[0] = split[0][1];
        regionR[1] = split[0][2];
        regionR[2] = split[0][0];
        aniRight = new Animation(0.1f, regionR);

        TextureRegion[] regionL = new TextureRegion[3];
        regionL[0] = mirror[0][1];
        regionL[1] = mirror[0][2];
        regionL[2] = mirror[0][0];
        aniRight = new Animation(0.1f, regionL);

        TextureRegion[] regionI = new TextureRegion[1];
        regionI[0] = split[0][0];
        aniIdle = new Animation(0.1f, regionI);

        buttonL = new ImageButton(new TextureRegionDrawable(split[1][0]), new TextureRegionDrawable(split[1][1]));
        buttonR = new ImageButton(new TextureRegionDrawable(mirror[1][0]), new TextureRegionDrawable(mirror[1][1]));

        buttonL.setPosition(20,20);
        buttonR.setPosition(100,20);

        buttonL.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Left;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idle;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        buttonR.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Right;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idle;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }
}
