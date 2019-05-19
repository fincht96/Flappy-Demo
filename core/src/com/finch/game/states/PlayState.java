package com.finch.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import com.finch.game.sprites.Bird;
import com.finch.game.sprites.Tube;


import java.util.ArrayList;
import java.util.Iterator;




/**
 *  Created by Thomas on 19/03/2019
 */

public class PlayState extends State {

    //private Bird mBird;
   
    private Texture mBg;
    private Array<Tube> mTube;

    private ArrayList<Bird> mBirds;

    

    
    private long prevTubeSpawnTime;
    
    private void spawnTube()
    {
    	mTube.add(new Tube( MathUtils.random(480, 720)));
    	prevTubeSpawnTime = TimeUtils.millis();
    }
    
    
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        
        mBirds = new ArrayList<Bird>();
        
        for(int i = 0; i < 150; i++)
        {
        	mBirds.add(new Bird(50,300));
        }
        
 

        
      
        mCam.setToOrtho(false, 480,800);
        mBg = new Texture("bg1.png");
        

        mTube = new Array<Tube>();
        spawnTube();
        
        
 

  
        

    }

    @Override
    public void handleInput()
    {
//        if(Gdx.input.justTouched()) {
//            //mBird.jump();
//        }
        
        double yTopTube = 0.0;
        double yBotTube = 0.0;
        double xTube = 0.0;
        double yBird = 0.0;
        
        
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
            Tube tube = iter.next();
            
            yTopTube = tube.getTopTube().y;
            yBotTube = tube.getBotTube().y + tube.getBotTube().height;
            xTube = tube.getBotTube().x;
            
        }
        
        

        
       
       for (Iterator<Bird> iter = mBirds.iterator(); iter.hasNext(); ) {
           Bird bird = iter.next();
           

           
           yBird = bird.y;
       	
           	ArrayList<Double> inputs = new ArrayList<Double>();
       		inputs.add(yTopTube);
       		inputs.add(yBotTube);
       		inputs.add(xTube);
       		inputs.add(yBird);
       	
       		double resultDouble = bird.getBrain().predict(inputs);
       	
       		if(resultDouble > 0.5)
       		{
       			bird.jump();
       		}
           
       		System.out.println(Double.toString(resultDouble));
           
       }
       

     
        
      
    }

    @Override
    public void update(float dt) {
        handleInput();
        
        

        
        
        for (Iterator<Bird> iter = mBirds.iterator(); iter.hasNext(); ) {
            	Bird bird = iter.next();
            
 
            	bird.update(dt);

            
                
            }
            

            
        
        
        
        
        
        if(TimeUtils.millis() - prevTubeSpawnTime > 3000) spawnTube();
    
        
        
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
            Tube tube = iter.next();
            
            
            tube.translateHorizontally(-150 * Gdx.graphics.getDeltaTime());
            
   
            if(tube.getBotTube().x + tube.getBotTube().width < 0)
            {
            	iter.remove();
            }
            
            
            for (Iterator<Bird> iterBird = mBirds.iterator(); iterBird.hasNext(); ) {
                Bird bird = iterBird.next();
                
     
                	if(bird.overlaps(tube.getBotTube()) || bird.overlaps(tube.getTopTube()))
                	{
                		iterBird.remove();
                	}

                
                    
                }
         
         }
      
        

    }

    
    
    @Override
    public void render(SpriteBatch sB) {
        sB.setProjectionMatrix(mCam.combined);  //  use the coordinate system specified by the camera
        sB.begin();
        sB.draw(mBg, 0, 0, 480, 800);      // y = 0 is bottom left hand corner
        
        
        for (Iterator<Bird> iter = mBirds.iterator(); iter.hasNext(); ) {
            Bird bird = iter.next();
            
            sB.draw(bird.getTexture(), bird.x, bird.y, bird.width, bird.height);
            
        }
        
 
        
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
            Tube tube = iter.next();
            
            sB.draw(tube.getTopTube().getTexture(), tube.getTopTube().x, tube.getTopTube().y, tube.getTopTube().width, tube.getTopTube().height);
            sB.draw(tube.getBotTube().getTexture(), tube.getBotTube().x, tube.getBotTube().y, tube.getBotTube().width, tube.getBotTube().height);
            
        }
        
        sB.end();
    }

    @Override
    public void dispose() {

    }
}
