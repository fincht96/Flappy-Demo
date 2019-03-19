package com.finch.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finch.game.FlappyDemo;

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

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sB) {
        sB.begin();
        sB.draw(mBackground, 0,0, FlappyDemo.sWIDTH, FlappyDemo.sHEIGHT);
        sB.draw(mPlayBtn,(FlappyDemo.sWIDTH/2 - mPlayBtn.getWidth()/2),(FlappyDemo.sHEIGHT/2));
        sB.end();
    }
}
