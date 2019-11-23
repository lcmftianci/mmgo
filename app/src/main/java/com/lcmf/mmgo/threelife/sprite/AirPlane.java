package com.lcmf.mmgo.threelife.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class AirPlane extends Sprite implements IGameObject {

    Vector2 pos;
    Vector2 rect;

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Vector2 getRect() {
        return rect;
    }
}
