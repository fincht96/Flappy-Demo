package com.finch.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import com.finch.game.FlappyDemo;



/**
 *  Created by Thomas on 20/03/2019
 */

public class Tube{

	private static Texture tex = new Texture("tube.png");
	private Sprite topTube, botTube;
	

    public Tube(float x)
    {

     
        topTube = new Sprite();
        botTube = new Sprite();
        
        
        topTube.setTexture(tex);
        botTube.setTexture(tex);
        
        int minTubeHeight = 50;

        int screenHeight = FlappyDemo.sHEIGHT;
        int gap = MathUtils.random(Bird.sHEIGHT + 50, Bird.sHEIGHT + 300);
        int topTubeHeight = MathUtils.random(minTubeHeight, screenHeight - gap - minTubeHeight);
        int botTubeHeight = screenHeight - gap - topTubeHeight;
        
   
        
        
        
        topTube.width = 40;
        topTube.height = topTubeHeight;
        
        botTube.width = 40;
        botTube.height = botTubeHeight;
        
        
        topTube.x = x;
        topTube.y = screenHeight - topTube.height;
        
        botTube.x = x;
        botTube.y = 0;
        
        
        
    }

    // translate in x axis
    public void translateHorizontally(float offset)
    {

    	
    	topTube.x += offset;
    	botTube.x += offset;
    }
    
    
    public Sprite getTopTube()
    {
    	return topTube;
    }
    

    public Sprite getBotTube()
    {
    	return botTube;
    }
    
    public void dispose()
    {
    	tex.dispose();
    	topTube.dispose();
    	botTube.dispose();
    	
    }

   
}
