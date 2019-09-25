package com.example.myapplication.advanceactor;

import com.badlogic.gdx.math.Vector2;

public class ConstantRect {
    Vector2 lbVec;
    Vector2 rbVec;
    Vector2 ltVec;
    Vector2 rtVec;

    boolean bInit;

    public ConstantRect(){
        bInit = false;
    }

    public Vector2[] getPosRect(){
        return new Vector2[]{lbVec, rbVec,  ltVec, rtVec};
    }

    public void setPosRect(int x, int y, float width, float height){
        if(!bInit){
            this.lbVec = new Vector2(x,y);
            this.rbVec = new Vector2(x + width, y);
            this.ltVec = new Vector2(x, y+height);
            this.rtVec = new Vector2(x + width, y + height);
            bInit = true;
        }else {
            this.lbVec.x = x;
            this.lbVec.y = y;

            this.rbVec.x = x + width;
            this.rbVec.y = y;

            this.ltVec.x = x;
            this.ltVec.y = y + height;

            this.rtVec.x = x + width;
            this.rtVec.y = y + height;
        }

    }

    public void setPosRect(Vector2 lbVec, Vector2 rbVec, Vector2 ltVec, Vector2 rtVec){
        this.lbVec = lbVec;
        this.rbVec = rbVec;
        this.ltVec = ltVec;
        this.rtVec = rtVec;
    }
}
