package com.example.myapplication.algorithm;

import com.badlogic.gdx.math.Vector2;

public class Box2dDetection {

    //判断两个目标是否碰撞
    public boolean checkTwoBox(Vector2 vec1, Vector2 vec2, Vector2 vec3, Vector2 vec4, Vector2 vec5){
        return checkCross(vec1,vec2,vec5) * checkCross(vec3,vec4,vec5) >= 0 && checkCross(vec2,vec3,vec5) * checkCross(vec4,vec1,vec5) >= 0;
    }

    // 计算 |p1 p2| X |p1 p|
    float checkCross(Vector2 vec1, Vector2 vec2, Vector2 vec5)
    {
        return (vec2.x - vec1.x) * (vec5.y - vec1.y) -(vec5.x - vec1.x) * (vec2.y - vec1.y);
    }
}
