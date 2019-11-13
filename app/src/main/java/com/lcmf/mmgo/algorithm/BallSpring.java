package com.lcmf.mmgo.algorithm;

public class BallSpring {

    public int[] ClickSpring(int vx, int vy){
        return new int[]{1,2};
    }

    //传入x,y,x1,y1 得出斜率，进而获得最终Y值
    public float Yaxb(float x, float y, float x1, float y1){
        float ret = (y1-y)/(x1-x);
        return ret;
    }
}
