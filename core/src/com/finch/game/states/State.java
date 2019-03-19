package com.finch.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


/**
 *  Created by Thomas on 19/03/2019
 */

public abstract class State {
    protected OrthographicCamera mCam;
    protected Vector3 mMouse;
    protected GameStateManager mGsm;


    protected State(GameStateManager gsm)
    {
        mGsm = gsm;
        mCam = new OrthographicCamera();
        mMouse = new Vector3();

    }


    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sB);
    public abstract void dispose();

}
