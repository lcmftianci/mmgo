package com.example.myapplication.tenwater.twactor.base;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.example.myapplication.tenwater.TenWaterGame;
//import com.libgdx.tenwater.TenWaterGame;

public class BaseGroup extends Group{
	private TenWaterGame game;
	
	public BaseGroup(TenWaterGame game) {
		this.game = game;
	}
	
	public TenWaterGame getGame() {
		return this.game;
	}
	
	public void setGame(TenWaterGame game) {
		this.game = game;
	}
}
