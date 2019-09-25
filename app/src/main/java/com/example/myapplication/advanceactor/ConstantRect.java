package com.example.myapplication.advanceactor;

import com.badlogic.gdx.math.Vector2;

public class ConstantRect {
    Vector2 lbVec;
    Vector2 rbVec;
    Vector2 ltVec;
    Vector2 rtVec;

    public Vector2[] getPosRect(){
        return new Vector2[]{lbVec, rbVec,  ltVec, rtVec};
    }

    public void setPosRect(int x, int y, float width, float height){
        this.lbVec = new Vector2(x,y);
        this.rbVec = new Vector2(x + width, y);
        this.ltVec = new Vector2(x, y+height);
        this.rtVec = new Vector2(x + width, y + height);
    }

    public void setPosRect(Vector2 lbVec, Vector2 rbVec, Vector2 ltVec, Vector2 rtVec){
        this.lbVec = lbVec;
        this.rbVec = rbVec;
        this.ltVec = ltVec;
        this.rtVec = rtVec;
    }
}
