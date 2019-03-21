package com.finch.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 *  Created by Thomas on 20/03/2019
 */

public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture mTopTube, mBotTube;
    private Vector2 mPosTopTube, mPosBotTube;
    private Random mRand;


    public Tube(float x)
    {
        mTopTube = new Texture("toptube.png");
        mBotTube = new Texture("bottomtube.png");
        mRand = new Random();

        mPosBotTube = new Vector2(x, -(mRand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING));
        mPosTopTube = new Vector2(x, mPosBotTube.y + TUBE_GAP + mBotTube.getHeight());
        //mPosTopTube = new Vector2(x, 100);
        //mPosTopTube = new Vector2(x, mPosBotTube.y - TUBE_GAP - mBotTube.getHeight());
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
}
