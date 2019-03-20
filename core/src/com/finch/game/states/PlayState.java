package com.finch.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.finch.game.FlappyDemo;
import com.finch.game.sprites.Bird;
import com.finch.game.sprites.Tube;

import javax.xml.soap.Text;


/**
 *  Created by Thomas on 19/03/2019
 */

public class PlayState extends State {

    private Bird mBird;
    private Texture mBg;
    private Tube mTube;

    private Texture topTubeTexture;
    private Vector2 topTubePos;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        mBird = new Bird(50,300);
        mCam.setToOrtho(false, FlappyDemo.sWIDTH/2,FlappyDemo.sHEIGHT/2);
        mBg = new Texture("bg.png");
        //mTube = new Tube(120);


        topTubeTexture = new Texture("toptube.png");
        topTubePos = new Vector2(100, 120);

    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched()) {
            mBird.jump();
        }
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
        sB.draw(mBg, (mCam.position.x - mCam.viewportWidth/2), 0);      // y = 0 is bottom left hand corner
        sB.draw(mBird.getTexture(), mBird.getPosition().x, mBird.getPosition().y);
        //sB.draw(mTube.getTopTube(), mTube.getPosTopTube().x, mTube.getPosTopTube().y);
        //sB.draw(mTube.getBotTube(), mTube.getPosBotTube().x, mTube.getPosBotTube().y);
        sB.draw(topTubeTexture, topTubePos.x, topTubePos.y);
        sB.end();
    }

    @Override
    public void dispose() {

    }
}
