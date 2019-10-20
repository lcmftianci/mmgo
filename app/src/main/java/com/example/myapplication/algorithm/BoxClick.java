package com.example.myapplication.algorithm;

import com.badlogic.gdx.math.Vector2;

public class BoxClick {

    public enum BxoPos{
        LEFTTOP,
        LFET,
        LEFTBOTTOM,
        BOTTOM,
        RIGHTBOTTOM,
        RIGHT,
        RIGHTTOP,
        TOP
    }

    private  static double distance(float x1, float y1, float x2, float y2){
        return Math.sqrt(Math.pow(x2-x1, 2.0f) + Math.pow(y2-y1, 2.0f));
    }

    private static double rectagle(float width1, float height1, float width2, float height2){
        return (Math.sqrt(Math.pow(width1/2, 2.0f) + Math.pow(height1/2, 2.0f)) + Math.sqrt(Math.pow(width2/2, 2.0f) + Math.pow(height2/2, 2.0f)));
    }

    public static boolean isClick(float x1, float y1, float x2, float y2, float width1, float height1, float width2, float height2){
        if(distance(x1,y1,x2,y2) > (rectagle(width1,height1,width2,height2) - 10))
            return false;
        return true;
    }

    public static boolean isInsideSector(Vector2 pot, Vector2 center, Vector2 sectorStart, Vector2 sectorEnd, double radiusSquared) {
        Vector2 relPoint = new Vector2(pot.x - center.x, pot.y - center.y);
        return !areClockwise(sectorStart, relPoint) &&
                areClockwise(sectorEnd, relPoint) &&
                isWithinRadius(relPoint, radiusSquared);
    }

    private static boolean  areClockwise(Vector2 v1, Vector2 v2) {
        return -v1.x*v2.y + v1.y*v2.x > 0;
    }

    private static boolean  isWithinRadius(Vector2 v, double radiusSquared) {
        return v.x*v.x + v.y*v.y <= radiusSquared;
    }

    public static BxoPos ClickPos(){
        return BxoPos.BOTTOM;
    }
}
