package com.finch.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sprite extends Rectangle{

	protected Texture tex;
	
	

	
	
	public void setTexture(String internalPath)
	{
		tex = new Texture(internalPath);
	}
	
	public Texture getTexture()
	{
		return tex;
	}
	
}
