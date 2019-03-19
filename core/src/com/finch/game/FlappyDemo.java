package com.finch.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finch.game.States.GameStateManager;
import com.finch.game.States.MenuState;

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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mGsm.push(new MenuState(mGsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);		// wipes screen
		mGsm.update(Gdx.graphics.getDeltaTime());
		mGsm.render(mSb);
	}
	
	@Override
	public void dispose () {
		mSb.dispose();

	}
}
