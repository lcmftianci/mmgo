package com.lcmf.mmgo.algorithm;

import com.badlogic.gdx.math.Vector2;

public class Box2dDetection {

    //就需要实现一下，对于一个点是否在矩形内的判断。
    //只需要判断该点是否在上下两条边和左右两条边之间就行，判断一个点是否在两条线段之间夹着，就转化成，判断一个点是否在某条线段的一边上，就可以利用叉乘的方向性，来判断夹角是否超过了180度
    //判断两个目标是否碰撞
    public boolean checkTwoBox(Vector2 vec1, Vector2 vec2, Vector2 vec3, Vector2 vec4, Vector2 vec5){
        boolean bRet = checkCross(vec1,vec2,vec5) * checkCross(vec3,vec4,vec5) >= 0 && checkCross(vec2,vec3,vec5) * checkCross(vec4,vec1,vec5) >= 0;
        return bRet;
    }

    // 计算 |p1 p2| X |p1 p|
    float checkCross(Vector2 vec1, Vector2 vec2, Vector2 vec5)
    {
        return (vec2.x - vec1.x) * (vec5.y - vec1.y) -(vec5.x - vec1.x) * (vec2.y - vec1.y);
    }
}
