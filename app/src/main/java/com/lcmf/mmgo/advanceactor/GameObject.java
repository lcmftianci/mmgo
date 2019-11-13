package com.lcmf.mmgo.advanceactor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface GameObject {

//    public final Rectangle bounds;
//    public final Vector2 position;

//    public GameObject (float x, float y, float width, float height) {
//        this.position = new Vector2(x, y);
//        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
//    }

    public Rectangle bounds = new Rectangle(0,0,0,0);
    public Vector2 position = new Vector2(0,0);

    public Rectangle geBounds();
    public Vector2 getNowPos();
}
