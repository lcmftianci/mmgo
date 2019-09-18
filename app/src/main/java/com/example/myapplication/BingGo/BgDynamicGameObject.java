package com.example.myapplication.BingGo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BgDynamicGameObject  extends BgGameObject {
    public final Vector2 velocity;
    public final Vector2 accel;

    public BgDynamicGameObject (float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
