package com.lcmf.mmgo.threelife.PhysicsUtil;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.lcmf.mmgo.threelife.sprite.IGameObject;

public class BombCollision {
    /**
     *  圆心，矩形斜边长
     */
    public static boolean isCollisionWithRect(double x1, double y1, double w1, double h1,
                                       double x2, double y2, double w2, double h2) {
        if((Math.pow(x1-x2, 2.0) + Math.pow(y1-y2, 2.0)) < (Math.pow(w1/2 + w2/2, 2.0) + Math.pow(h1/2 + h2/2, 2.0))){
            return true;
        }
        return false;
    }

    public static boolean checkBombCollision(IGameObject bomb, Sprite airPlane){
        Vector2 pos = bomb.getPos();
        Vector2 rect = bomb.getRect();

        float height = airPlane.getHeight();
        float width  = airPlane.getWidth();
        float posX  = airPlane.getX();
        float posY  = airPlane.getY();

        return isCollisionWithRect(pos.x+rect.x/2, pos.y + rect.y/2, rect.x, rect.y, posX+width/2, posY+height/2, width,height);
    }
}
