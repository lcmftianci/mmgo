package com.example.myapplication.mario.debug;

//import nl.arjanfrans.mario.MarioGame;

import com.example.myapplication.mario.MarioGame;

public class D {

	public static void o(String msg) {
		if(MarioGame.DEBUG) {
			System.out.println(msg);
		}
	}
	
	public static void o(float msg) {
		if(MarioGame.DEBUG) {
			System.out.println(msg);
		}
	}
}
