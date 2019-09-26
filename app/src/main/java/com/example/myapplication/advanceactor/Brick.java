package com.example.myapplication.advanceactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.example.myapplication.advancestrangely.AdvanceAsserts;

import javax.microedition.khronos.opengles.GL10;

public class Brick extends Actor {
    Sprite brickSprite;         //需要碰撞的砖
    Texture texture;            //砖块精灵
    //AdvanceAsserts asserts;     //精灵图集
    int score;
    Vector2 pos;
    Vector2 size;
    BitmapFont font;
    ConstantRect cr;

    public Brick(int x, int y, int sw, int sh, int score, TextureRegion region){
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
        font.draw(batch, str, this.pos.x + size.x/3, this.pos.y+size.y/2);
        cr.setPosRect((int)this.pos.x, (int)this.pos.y, size.x, size.y);
    }
}
