package com.finch.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sprite extends Rectangle{

	protected Texture tex;
	
	

	
	
	public void setTexture(Texture tex)
	{
		this.tex = tex;
	}
	
	public Texture getTexture()
	{
		return tex;
	}
	
	public void dispose()
	{
		tex.dispose();
	}
	
}
