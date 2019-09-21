package com.example.myapplication.tzfe.tzfeactor.base;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.example.myapplication.tzfe.TZFEMainGame;
//import com.example.myapplication.tzfe.MainGame;
//import cn.appkf.game2048.MainGame;

/**
 * 演员组基类
 *
 * @author xietansheng
 */
public abstract class BaseGroup extends Group {

    private TZFEMainGame mainGame;

    public BaseGroup(TZFEMainGame mainGame) {
        this.mainGame = mainGame;
    }

    public TZFEMainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(TZFEMainGame mainGame) {
        this.mainGame = mainGame;
    }
}















