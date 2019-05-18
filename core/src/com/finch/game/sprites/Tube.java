package com.finch.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.finch.game.FlappyDemo;

import java.util.Random;

/**
 *  Created by Thomas on 20/03/2019
 */

public class Tube{

	
    private Texture mTopTube, mBotTube;
    private Vector2 mPosTopTube, mPosBotTube;
    
    private Vector2 mSizeTopTube, mSizeBotTube;


    public Tube(float x)
    {
        mTopTube = new Texture("tube.png");
        mBotTube = new Texture("tube.png");
     

        
        
        int minTubeHeight = 50;

        int screenHeight = FlappyDemo.sHEIGHT;
        int gap = MathUtils.random(Bird.sHEIGHT + 50, Bird.sHEIGHT + 300);
        int topTubeHeight = MathUtils.random(10, screenHeight - gap - minTubeHeight);
        int botTubeHeight = screenHeight - gap - topTubeHeight;
        
        
        
        mSizeTopTube = new Vector2(40,topTubeHeight);
        mSizeBotTube = new Vector2(40,botTubeHeight);
        
        
        
        mPosBotTube = new Vector2(x, 0);
        mPosTopTube = new Vector2(x, 800- mSizeTopTube.y);
    }

    // translate in x axis
    public void translateHorizontally(float offset)
    {
    	mPosTopTube.x += offset;
    	mPosBotTube.x += offset;
    }
    
    
    public Texture getTopTube() {
        return mTopTube;
    }

    public Texture getBotTube() {
        return mBotTube;
    }

    public Vector2 getPosTopTube() {
        return mPosTopTube;
    }

    public Vector2 getPosBotTube() {
        return mPosBotTube;
    }
    
    
    public Vector2 getSizeTopTube()
    {
    	return mSizeTopTube;
    }
    
    public Vector2 getSizeBotTube()
    {
    	return mSizeBotTube;
    }
}
