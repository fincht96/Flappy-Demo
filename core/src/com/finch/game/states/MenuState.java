package com.finch.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finch.game.FlappyDemo;


/**
 *  Created by Thomas on 19/03/2019
 */

public class MenuState extends State {

    private Texture mBackground;
    private Texture mPlayBtn;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        mBackground = new Texture("bg.png");
        mPlayBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            mGsm.set(new PlayState(mGsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sB) {
        sB.begin();
        sB.draw(mBackground, 0,0, FlappyDemo.sWIDTH, FlappyDemo.sHEIGHT);
        sB.draw(mPlayBtn,(FlappyDemo.sWIDTH/2 - mPlayBtn.getWidth()/2),(FlappyDemo.sHEIGHT/2));
        sB.end();
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mPlayBtn.dispose();
    }
}
