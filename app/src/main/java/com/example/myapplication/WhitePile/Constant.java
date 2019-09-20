//全局参数类
package com.example.myapplication.WhitePile;

public class Constant {
    enum GameState{ game_ready, game_on, game_pause, game_over, game_preover };
    public static GameState state = GameState.game_ready;
    
    public static float initSpeed = 5;
    public static float speedInc = 0.25f;
    
    public static int level = 2;
    
}


 
//游戏写的很粗糙，自己试了下，在android手机下能运行，但有些bug，还有待改进。还望大家多多指正，谢谢！！！