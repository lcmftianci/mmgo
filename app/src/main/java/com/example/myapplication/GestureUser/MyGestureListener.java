package com.example.myapplication.GestureUser;
 
import android.util.Log;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
 
public class MyGestureListener implements GestureListener {

	private final String TAG = "--- MyGestureListener";

	@Override
	public boolean panStop(float v, float v1, int i, int i1) {
		Log.d(TAG, "panStop");
		return false;
	}

	@Override
	public boolean fling(float arg0, float arg1, int arg2) {//fling和pan差不多
		//System.out.println("------->fling");
		if(arg0 > 0)Log.d(TAG, "right");
		else if(arg0 < 0)Log.d(TAG, "left");

		if(arg1 > 0)Log.d(TAG, "top");
		else if(arg1 < 0) Log.d(TAG, "bottom");
		Log.d(TAG, "fling");
		return false;
	}
 
	@Override
	public boolean longPress(float arg0, float arg1) {//长按
		//System.out.println("------->longPress");
		Log.d(TAG, "longPress");
		return false;
	}
 
	@Override
	public boolean pan(float arg0, float arg1, float arg2, float arg3) {
		//System.out.println("------->pan");
		Log.d(TAG, "pan");
		return false;
	}
 
	@Override
	public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2, Vector2 arg3) {//pinch和zoom差不多
		//System.out.println("------->pinch");
		Log.d(TAG, "pinch");
		return false;
	}
 
	@Override
	public boolean tap(float arg0, float arg1, int arg2, int arg3) {//tap和touchdown差不多
		//System.out.println("------->tap");
		Log.d(TAG, "tap");
		return false;
	}
 
	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
		//System.out.println("gesturedetector------->touchDown");
		Log.d(TAG, "touchDown");
		return true;
	}
 
	@Override
	public boolean zoom(float arg0, float arg1) {
		//System.out.println("------->zoom");
		Log.d(TAG, "zoom");
		return false;
	}
 
}