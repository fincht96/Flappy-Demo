package com.finch.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finch.game.sprites.Bird;
import com.finch.game.states.GameStateManager;
import com.finch.game.states.MenuState;
import com.finch.game.states.PlayState;
import com.finch.machine.learning.Brain;

public class FlappyDemo extends ApplicationAdapter {


	public static final int sWIDTH = 480;
	public static final int sHEIGHT = 800;
	public static final String sSTRING_TITLE = "Flappy Bird";

	private GameStateManager mGsm;
	private SpriteBatch mSb;

	@Override
	public void create () {
		mSb = new SpriteBatch();
		mGsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		mGsm.push(new PlayState(mGsm));
		
		
		
		
		
		Brain parent = new Brain();
		Brain unmodifiedChild = new Brain();
		Brain modifiedChild = new Brain();
		
		try {
			unmodifiedChild = (Brain) parent.clone();
			modifiedChild = (Brain) parent.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		modifiedChild.mutate();
		
		ArrayList<Double> inputs = new ArrayList<Double>();
		
		inputs.add(0.1);
		inputs.add(0.18);
		inputs.add(0.5);
		inputs.add(0.5);
		
		double parentPredic = parent.predict(inputs);
		double unmodChildPredic = unmodifiedChild.predict(inputs);
		double modChildPredic = modifiedChild.predict(inputs);
		
		
		System.out.println("Parent predicition: " + Double.toString(parentPredic));
		System.out.println("Unmodified Child predicition: " + Double.toString(unmodChildPredic));
		System.out.println("Modified Child predicition: " + Double.toString(modChildPredic));
		
		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		// wipes screen
//		mGsm.update(Gdx.graphics.getDeltaTime());
		mGsm.render(mSb);
		

		
	}
	
	@Override
	public void dispose () {
		mSb.dispose();

	}
}
