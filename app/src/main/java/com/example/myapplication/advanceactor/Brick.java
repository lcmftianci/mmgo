package com.example.myapplication.advanceactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

import javax.microedition.khronos.opengles.GL10;

public class Brick extends Image implements GameObject{
    World world;
    Body body;
    Sprite brickSprite;         //需要碰撞的砖
    Texture texture;            //砖块精灵
    //AdvanceAsserts asserts;     //精灵图集
    int score;
    Vector2 pos;
    Vector2 size;
    BitmapFont font;
    ConstantRect cr;
    public Rectangle bounds;

    Vector2 ltPos;
    Vector2 lbPos;
    Vector2 rtPos;
    Vector2 rbPos;

    int iBrickWidth;
    int iBrickHeight;

    public Vector2[] getBrickRect(){
        return new Vector2[]{lbPos,rbPos,rtPos,ltPos};
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
        groundBox.setAsBox(brickSprite.getWidth(), brickSprite.getHeight());
        //groundBox.setAsBox(this.getWidth()/2, this.getHeight()/2);
        //body.setTransform(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2, (float)Math.toRadians(angle));

        // Create a fixture from our polygon shape and add it to our ground body
        body.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
    }

    /**
     * @param aWorld
     * @param x   砖左下角x
     * @param y   砖左下角y
     * @param sw  砖的宽
     * @param sh  砖的高
     * @param bw  球的宽
     * @param bh  球的高
     * @param score 砖的分数
     * @param region 砖的图片
     */
    public Brick(World aWorld, int x, int y, int sw, int sh, int bw, int bh, int score, TextureRegion region){
        //this.asserts = asserts;
        pos = new Vector2();
        size = new Vector2();
        this.pos.x = x;
        this.pos.y = y;
        this.score = score;
        brickSprite = new Sprite(region);
        size.x = sw;
        size.y = sh;
        brickSprite.setSize(size.x, size.y);
        brickSprite.setPosition(x, y);
        font = new BitmapFont( Gdx.files.internal( "font/bitmap_font.fnt" ), Gdx.files.internal( "font/bitmap_font.png" ), false );
        font.setColor(Color.BLUE);
        cr = new ConstantRect();
        cr.setPosRect(x, y, size.x, size.y);
        bounds = new Rectangle(0,0,0,0);
        bounds.x = x;
        bounds.y = y;
        bounds.width = size.x;
        bounds.height = size.y;
        this.world = aWorld;

        position.x = bounds.x + bounds.width/2;
        position.y = bounds.y + bounds.height/2;

        //initBody(x,y);
        ltPos = new Vector2(x - bw/2, y + bh/2 + bounds.height);
        lbPos = new Vector2(x - bw/2, y - bh/2);
        rbPos = new Vector2(x + bw/2 + bounds.width, y - bh/2);
        rtPos = new Vector2(x + bw/2 + bounds.width, y + bh/2 + bounds.height);
        iBrickWidth = bw;
        iBrickHeight = bh;
    }

    public boolean setNum(int num){
        //this.score = num;
        this.score--;
        if(this.score == 0)
            return true;
        else
            return false;
    }

    public Vector2[] getRect(){
        return cr.getPosRect();
    }

    //实时更新当前砖块的位置
    public void setCurPos(int x, int y){
        pos.x = x;
        pos.y = y;
        brickSprite.setPosition(pos.x, pos.y);
    }

    public Vector2 getCurPos(){
        return pos;
    }

    public int getCurSocre(){
        return this.score;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClearColor(1,1,1,1);
        brickSprite.draw(batch);
        String str = "";
        str += score;
        font.draw(batch, str, this.pos.x + size.x/3, this.pos.y+size.y);
        cr.setPosRect((int)this.pos.x, (int)this.pos.y, size.x, size.y);

        bounds.x = this.pos.x;
        bounds.y = this.pos.y;
        bounds.width = size.x;
        bounds.height = size.y;

        position.x = bounds.x + bounds.width/2;
        position.y = bounds.y + bounds.height/2;

        //更新外围图框
        ltPos.x = bounds.x - iBrickWidth/2;
        ltPos.y = bounds.y + iBrickHeight/2 + bounds.height;

        lbPos.x = bounds.x - iBrickWidth/2;
        lbPos.y = bounds.y - iBrickHeight/2;

        rbPos.x = bounds.x + iBrickWidth/2 + bounds.width;
        rbPos.y = bounds.y - iBrickHeight/2;

        rtPos.x = bounds.x + iBrickWidth/2 + bounds.width;
        rtPos.y = bounds.y + iBrickHeight/2 + bounds.height;
    }

    //@Override
    public Rectangle geBounds() {
        return bounds;
    }

    public Vector2 getNowPos(){return position;}
}
