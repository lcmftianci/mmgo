package com.example.myapplication.advanceactor;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.example.myapplication.advancestrangely.AdvanceAsserts;

public class Ball extends Image implements GameObject{

    //创建body
    private Body body;
    private World world;
    Texture texture;
    Sprite spriteball;
    TextureRegion region;

    int xd;    //x方向
    int yd;    //y方向
    float weight; //斜率
    int xv;     //x方向速度
    int yv;     //y方向上速度
    int vul;    //运行速度
    int curPosX,curPosY;   //x，y坐标
    int staticPosX,staticPosY;  //x,y坐标
    boolean bleft,btop;    //方向标志
    boolean brun;          //是否还在移动
    Vector2 curPos;        //左下角位置点
    Vector2 nowPos;        //中心点
    ConstantRect cr;       //四个角的坐标点
    int inx;

    public Rectangle bounds;

    private final static String TAG = "BALL";

    AdvanceAsserts asserts;

    public Ball(World aWorld, int x, int y, AdvanceAsserts asserts){
        this.world = aWorld;
        inx = 0;
        this.asserts = asserts;
        //initBody(x,y);
        init(x,y);
        initBody(x,y);
    }

    private void initBody(int pos_x, int pos_y){
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.DynamicBody;

        // Set its world position
        groundBodyDef.position.set(new Vector2(pos_x, pos_y));

        // Create a body from the defintion and add it to the world
        body = world.createBody(groundBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high

        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(spriteball.getWidth(), spriteball.getHeight());
        //groundBox.setAsBox(this.getWidth()/2, this.getHeight()/2);
        //body.setTransform(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2, (float)Math.toRadians(angle));

        // Create a fixture from our polygon shape and add it to our ground body
        body.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
    }

    public void delay(int inx){
        this.inx = inx;
    }

    public void init(int x, int y){
        texture = new Texture(Gdx.files.internal("ball.jpg"));
        region = new TextureRegion(texture, 5,5,15,15);
        spriteball = new Sprite(region);
        spriteball.setSize(Gdx.graphics.getWidth()/20, Gdx.graphics.getWidth()/20);

        //spriteball.setScale(100,100);
        spriteball.setSize(Gdx.graphics.getWidth()/22, Gdx.graphics.getWidth()/22);
        xd = -10;
        yd = -10;
        vul = Gdx.graphics.getHeight()/50;
        xv = Gdx.graphics.getWidth()/90;
        yv = Gdx.graphics.getHeight()/35;

        brun = false;
        bleft = true;
        btop = true;

        //curPosX = Gdx.graphics.getWidth()/2;
        //curPosY = 0;

        curPosX = x;
        curPosY = y;
        staticPosX = curPosX;
        staticPosY = curPosY;
        curPos = new Vector2(curPosX, curPosY);
        nowPos = new Vector2(curPosX + spriteball.getWidth()/2, curPosY + spriteball.getHeight()/2);
        cr = new ConstantRect();
        cr.setPosRect(curPosX, curPosY, spriteball.getWidth(), spriteball.getHeight());
        bounds = new Rectangle(0,0,0,0);
        bounds.x = curPosX;
        bounds.y = curPosY;
        bounds.width = spriteball.getWidth();
        bounds.height = spriteball.getHeight();
    }

    public Vector2[] getRect(){
        return cr.getPosRect();
    }

    public void setvum(int x, int y, float weight){
        this.xd = x;
        this.yd = y;
        this.weight = weight;
        brun = true;

        //弧度＝(角度/180) *PI                   //弧度跟角度关系
        double hw = Math.atan(weight);           //斜率转弧度
        //double aw = Math.toDegrees(hw);        //弧度转角度
        //hw = Math.toRadians(aw);               //角度转弧度
        this.yv = (int)(weight*(float) xv);

        this.yv = (int)((double)vul*Math.sin(hw));
        this.xv = (int)((double)vul*Math.cos(hw));

        if(x > Gdx.graphics.getWidth()/2)
            bleft = false;
        else
            bleft = true;
        btop = true;

//        curPosX = Gdx.graphics.getWidth()/2;
//        curPosY = 0;
//        curPos.x = curPosX;
//        curPos.y = curPosY;
        curPosX = staticPosX;
        curPosY = staticPosY;
        curPos.x = staticPosX;
        curPos.y = staticPosY;
    }

    public boolean isrun(){
        return brun;
    }

    public void update(int x, int y){
        spriteball.setPosition(x - Gdx.graphics.getWidth()/13, y - Gdx.graphics.getWidth()/13);
    }

    public Vector2 getCurPos(){
        return curPos;
    }

    public Vector2 getNowPos(){
        return  nowPos;
    }

    public void setDirection(boolean bleft, boolean btop){
        this.bleft = bleft;
        this.btop = btop;
    }

    public void setReverseHorizenDir(){
        this.bleft = !bleft;
    }

    public void setReverseVerticalDir(){
        this.btop = !btop;
    }

    public void setLeftRightDirection(boolean bLeft){
        this.bleft = bLeft;
    }

    public void setTopBottomDirection(boolean bTop){
        this.btop = bTop;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //batch.draw(spriteball,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        //1.从 (width/2,0)出发，以wight方向移动，每次移动(xv，xv*wight)距离  //这种方法，有点问题每次都要重新计算权重
        //2.从起始点出发，每次在x,y方向移动xv,yv方向，碰到边界如何反弹
        //3.延迟发射
        if(brun)
        {
            if(inx != 0){
                inx--;
                return;
            }
            if(bleft){
                curPosX -= xv;
            }else{
                curPosX += xv;
            }

            if(btop){
                curPosY += yv;
            }else {
                curPosY -= yv;
            }

            if(curPosY >= Gdx.graphics.getHeight() - spriteball.getHeight()-100){
                asserts.clickSound.play(1);
                btop=false;
            }

            if(curPosY < 0){
                asserts.clickSound.play(1);
                btop=true;
            }

            if(curPosX >= Gdx.graphics.getWidth() - spriteball.getWidth()){
                asserts.clickSound.play(1);
                bleft=true;
            }

            if(curPosX < 0){
                asserts.clickSound.play(1);
                bleft=false;
            }

            //Log.d(TAG, "x:" + curPosX + " y:" + curPosY + " vx:" + this.xv + " vy:" + this.yv);

            spriteball.setPosition(curPosX,curPosY);

            spriteball.draw(batch);

            if(!btop){
                if(curPosY < staticPosY+10){
                    brun = false;
                }
            }

            curPos.x = curPosX;
            curPos.y = curPosY;
            cr.setPosRect(curPosX, curPosY, spriteball.getWidth(), spriteball.getHeight());
            bounds.x = curPosX;
            bounds.y = curPosY;
            bounds.width = spriteball.getWidth();
            bounds.height = spriteball.getHeight();
            nowPos.x = curPosX + spriteball.getWidth()/2;
            nowPos.y = curPosY + spriteball.getHeight()/2;
        }
    }

    //@Override
    public Rectangle geBounds() {
        return bounds;
    }
}
