package com.example.myapplication.GestureUser;
 
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;

import javax.microedition.khronos.opengles.GL10;

public class MyGame implements ApplicationListener {
 
	private final String TAG = "--- MyGame";
	InputProcessor inputProcessor;
	
	@Override
	public void create() {
		inputProcessor = new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				Log.d(TAG, "touchUp");
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				Log.d(TAG, "touchDragged");
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				Log.d(TAG, "touchDown");
				//System.out.println("inputprocessor:--->towndown" );
				return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				Log.d(TAG, "scrolled");
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				Log.d(TAG, "touchDown");
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				Log.d(TAG, "keyUp");
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				Log.d(TAG, "keyTyped");
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				Log.d(TAG, "keyDown");
				return false;
			}
		};
		
		//GestureDetector同样适用于inputprocessor的链式传递规则(因为GestureDetector就是实现了inputprocessor接口的)
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new GestureDetector(new MyGestureListener()));
		inputMultiplexer.addProcessor(inputProcessor);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
 
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void pause() {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
	}
 
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void resume() {
		// TODO Auto-generated method stub
 
	}
 
}