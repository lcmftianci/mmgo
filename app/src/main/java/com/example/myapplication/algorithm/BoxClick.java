package com.example.myapplication.algorithm;

public class BoxClick {
    private double distance(float x1, float y1, float x2, float y2){
        return Math.sqrt(Math.pow(x2-x1, 2.0f) + Math.pow(y2-y1, 2.0f));
    }

    private double rectagle(float width1, float height1, float width2, float height2){
        return Math.sqrt(Math.pow(width1, 2.0f) + Math.pow(height1, 2.0f)) + Math.sqrt(Math.pow(width2, 2.0f) + Math.pow(height2, 2.0f));
    }

    public boolean isClick(float x1, float y1, float x2, float y2, float width1, float height1, float width2, float height2){
        if(distance(x1,y1,x2,y2) > (rectagle(width1,height1,width2,height2) - 10))
            return false;
        return true;
    }
}
