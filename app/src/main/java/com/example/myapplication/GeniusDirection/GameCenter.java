package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Vector;

public class GameCenter {
    public static Vector<Texture> balls;
    public GameCenter(){
        balls.add(new Texture(Gdx.files.internal("ball.jpg")));
    }
}
