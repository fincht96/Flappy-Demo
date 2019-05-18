package com.finch.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finch.game.states.GameStateManager;
import com.finch.game.states.MenuState;
import com.finch.game.states.PlayState;

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
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		// wipes screen
		mGsm.update(Gdx.graphics.getDeltaTime());
		mGsm.render(mSb);
	}
	
	@Override
	public void dispose () {
		mSb.dispose();

	}
}
