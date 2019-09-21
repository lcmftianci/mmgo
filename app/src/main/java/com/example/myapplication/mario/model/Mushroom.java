package com.example.myapplication.mario.model;

//import nl.arjanfrans.mario.actions.MoveableActions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.example.myapplication.mario.actions.MoveableActions;


public abstract class Mushroom extends MovingActor {

	public Mushroom(World world, float x, float y, float max_velocity) {
		super(world, x, y, max_velocity);
	}

	public void appear() {
		this.setVisible(true);
		this.addAction(Actions.sequence(Actions.moveTo(this.getX(), this.getY() + this.getHeight(),
				0.3f, Interpolation.linear), MoveableActions.startMovingAction(this)));
	}
	
	public abstract void dispose();

}
