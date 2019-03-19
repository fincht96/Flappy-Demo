package com.finch.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finch.game.FlappyDemo;
import com.finch.game.sprites.Bird;


/**
 *  Created by Thomas on 19/03/2019
 */

public class PlayState extends State {

    private Bird mBird;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        mBird = new Bird(50,100);
        mCam.setToOrtho(false, FlappyDemo.sWIDTH/2,FlappyDemo.sHEIGHT/2);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        mBird.update(dt);

    }

    @Override
    public void render(SpriteBatch sB) {
        sB.setProjectionMatrix(mCam.combined);  //  use the coordinate system specified by the camera
        sB.begin();
        sB.draw(mBird.getTexture(), mBird.getPosition().x, mBird.getPosition().y);
        sB.end();
    }

    @Override
    public void dispose() {

    }
}
