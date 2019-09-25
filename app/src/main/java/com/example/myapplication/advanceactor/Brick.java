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

public class Brick extends Actor {
    Sprite brickSprite;         //需要碰撞的砖
    Texture texture;            //砖块精灵
    //AdvanceAsserts asserts;     //精灵图集
    int score;
    Vector2 pos;
    Vector2 size;
    BitmapFont font;
    ConstantRect cr;

    public Brick(int x, int y, int score, TextureRegion region){
        //this.asserts = asserts;
        pos = new Vector2();
        size = new Vector2();
        this.pos.x = x;
        this.pos.y = y;
        this.score = score;
        brickSprite = new Sprite(region);
        size.x = Gdx.graphics.getWidth()/10;
        size.y = Gdx.graphics.getHeight()/10;
        brickSprite.setSize(size.x, size.y);
        brickSprite.setPosition(x, y);
        font = new BitmapFont( Gdx.files.internal( "font/bitmap_font.fnt" ), Gdx.files.internal( "font/bitmap_font.png" ), false );
        font.setColor(Color.BLUE);
        cr = new ConstantRect();
        cr.setPosRect(x, y, size.x, size.y);
    }

    public void setNum(int num){
        //this.score = num;
        this.score--;
    }

    public Vector2[] getRect(){
        return cr.getPosRect();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        brickSprite.draw(batch);
        String str = "";
        str += score;
        font.draw(batch, str, this.pos.x + size.x/3, this.pos.y+size.y/2);
        cr.setPosRect((int)this.pos.x, (int)this.pos.y, size.x, size.y);
    }
}
