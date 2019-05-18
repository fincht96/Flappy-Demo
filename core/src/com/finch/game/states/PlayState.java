package com.finch.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.finch.game.FlappyDemo;
import com.finch.game.sprites.Bird;
import com.finch.game.sprites.Tube;

import java.util.Iterator;

import javax.xml.soap.Text;


/**
 *  Created by Thomas on 19/03/2019
 */

public class PlayState extends State {

    private Bird mBird;
    private Texture mBg;
    private Array<Tube> mTube;



    
    private long prevTubeSpawnTime;
    
    private void spawnTube()
    {
    	mTube.add(new Tube( MathUtils.random(480, 780)));
    	prevTubeSpawnTime = TimeUtils.millis();
    }
    
    
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        mBird = new Bird(50,300);		// draws bird at positio (50,300)
        //mCam.setToOrtho(false, FlappyDemo.sWIDTH/2,FlappyDemo.sHEIGHT/2);
        mCam.setToOrtho(false, 480,800);
        mBg = new Texture("bg1.png");
        //mTube = new Tube(120);

        mTube = new Array<Tube>();
        spawnTube();


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
        
        
        if(TimeUtils.millis() - prevTubeSpawnTime > 3000) spawnTube();
    
        
        
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
            Tube tube = iter.next();
            
            //raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            //tube.x -= ;
            
            tube.translateHorizontally(-200 * Gdx.graphics.getDeltaTime());
            
           // if(raindrop.y + 64 < 0) iter.remove();
            if(tube.getPosBotTube().x + tube.getSizeBotTube().x < 0) iter.remove();
         
//            if(raindrop.overlaps(bucket)) {
//               //dropSound.play();
//               iter.remove();
//            }
         }
      
        

    }

    
    
    @Override
    public void render(SpriteBatch sB) {
        sB.setProjectionMatrix(mCam.combined);  //  use the coordinate system specified by the camera
        sB.begin();
        sB.draw(mBg, 0, 0, 480, 800);      // y = 0 is bottom left hand corner
        sB.draw(mBird.getTexture(), mBird.getPosition().x, mBird.getPosition().y, mBird.sWIDTH, mBird.sHEIGHT);

        
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
            Tube tube = iter.next();
            
            sB.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y, tube.getSizeTopTube().x, tube.getSizeTopTube().y);
            sB.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y, tube.getSizeBotTube().x, tube.getSizeBotTube().y);
            
        }
        
        sB.end();
    }

    @Override
    public void dispose() {

    }
}
