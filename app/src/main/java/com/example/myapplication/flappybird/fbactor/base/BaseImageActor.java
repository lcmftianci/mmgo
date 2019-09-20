package com.example.myapplication.flappybird.fbactor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.example.myapplication.flappybird.MainGame;
import com.example.myapplication.flappybird.fbactor.frameworks.ImageActor;

//import cn.appkf.flappybird.MainGame;
//import cn.appkf.flappybird.actor.framework.ImageActor;

/**
 * 演员基类
 * 
 * @xietansheng
 */
public abstract class BaseImageActor extends ImageActor {

    private MainGame mainGame;

    public BaseImageActor(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public BaseImageActor(MainGame mainGame, TextureRegion region) {
        super(region);
        this.mainGame = mainGame;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

}















