package com.example.myapplication.mylecture.stageandactor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class MarioMainGame implements ApplicationListener {

    MarioGameStage gameStage;
    MarioStartStage startStage;
    MarioShopStage shopStage;

    @Override
    public void create() {
        shopStage = new MarioShopStage();
        gameStage = new MarioGameStage();
        startStage = new MarioStartStage();
    }

    public void SelectStageRender(){
        if(MarioConstants.StageFlag == MarioConstants.StageStartOn){
            Gdx.input.setInputProcessor(startStage);
            startStage.act();
            startStage.draw();
        }else if(MarioConstants.StageFlag == MarioConstants.StageGameOn){
            Gdx.input.setInputProcessor(gameStage);
            gameStage.act();
            gameStage.draw();
        }else if(MarioConstants.StageFlag == MarioConstants.StageShopOn){
            Gdx.input.setInputProcessor(shopStage);
            shopStage.act();
            shopStage.draw();
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        SelectStageRender();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
