package com.lcmf.mmgo.advanceactor;

import com.badlogic.gdx.math.Vector2;

public class ConstantRect {
    Vector2 lbVec;
    Vector2 rbVec;
    Vector2 rtVec;
    Vector2 ltVec;

    boolean bInit;

    public ConstantRect(){
        bInit = false;
    }

    public Vector2[] getPosRect(){
        return new Vector2[]{lbVec, rbVec, rtVec, ltVec};
    }

    public void setPosRect(int x, int y, float width, float height){
        if(!bInit){
            this.lbVec = new Vector2(x,y);
            this.rbVec = new Vector2(x + width, y);
            this.rtVec = new Vector2(x + width, y+height);
            this.ltVec = new Vector2(x, y + height);
            bInit = true;
        }else {
            this.lbVec.x = x;
            this.lbVec.y = y;

            this.rbVec.x = x + width;
            this.rbVec.y = y;

            this.rtVec.x = x + width;
            this.rtVec.y = y + height;

            this.ltVec.x = x;
            this.ltVec.y = y + height;
        }

    }

    public void setPosRect(Vector2 lbVec, Vector2 rbVec, Vector2 ltVec, Vector2 rtVec){
        this.lbVec = lbVec;
        this.rbVec = rbVec;
        this.ltVec = ltVec;
        this.rtVec = rtVec;
    }
}
