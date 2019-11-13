package com.lcmf.mmgo.algorithm;

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

    //两个正方形的中心点之间的距离
    public static boolean isClick(float x1, float y1, float x2, float y2, float width1, float height1, float width2, float height2){
        if(distance(x1,y1,x2,y2) > (rectagle(width1,height1,width2,height2) + 10))
            return false;
        return true;
    }

    //判断一个动态点是否在扇形内部
    public static boolean isInsideSector(Vector2 pot, Vector2 center, Vector2 sectorStart, Vector2 sectorEnd, double radiusSquared) {
        Vector2 relPoint = new Vector2(pot.x - center.x, pot.y - center.y);
        return !areClockwise(sectorStart, relPoint) && areClockwise(sectorEnd, relPoint) && isWithinRadius(relPoint, radiusSquared);
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


    //用向量锐角判断点在夹角内，并根据在半径内判断圆内
    public static boolean isInSelect(Vector2 pot, Vector2 center, Vector2 sectorStart, Vector2 sectorEnd){
        if(isCosPlus(pot, center, sectorStart) > 0 && isCosPlus(pot, center, sectorEnd) > 0)
            return true;
        return false;
    }

    public static double isCosPlus(Vector2 pot, Vector2 center, Vector2 selectStart){
        double vecX = selectStart.x - center.x;
        double vecY = selectStart.y - center.y;

        double potX = pot.x - center.x;
        double potY = pot.y - center.y;

        return (vecX*potX + vecY*potY)/Math.sqrt((Math.pow(vecX, 2.0f) + Math.pow(vecY, 2.0f))*(Math.pow(potX, 2.0f) + Math.pow(potY, 2.0f)));
    }
}
