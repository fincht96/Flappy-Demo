package com.finch.game.states;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.utils.Array;
import com.finch.game.FlappyDemo;
import com.finch.game.sprites.Bird;
import com.finch.game.sprites.Tube;

import java.util.ArrayList;
import java.util.Iterator;




/**
 *  Created by Thomas on 19/03/2019
 */

public class PlayState extends State {

	private static int GEN_SIZE = 1000 ;
	
	
	BitmapFont font = new BitmapFont();
	
	
	
    private static Texture mBg = new Texture("bg1.png");
    
    private Array<Tube> mTube;
    
   
    private ArrayList<Bird> mBirds;
    private ArrayList<Bird> mSavedBirds;
    private int generation = 0;
    double yBird = 0;
    
    private long highestScore = 0;   
//    private void spawnEqualGeneration()
//    {
//    	Brain brain = new Brain();
//    	
//    	++generation;
//        for(int i = 0; i < GEN_SIZE; i++)
//        {
//        	Bird newBird = new Bird(50,300);
//        	newBird.setBrain(brain);
//        	newBird.y = 50;//MathUtils.random(500, 720);
//        	newBird.id = i;
//        	mBirds.add(newBird);
//        }
//
//    }
    
    
    
    private void spawnNewGeneration()
    {
    	
    	

    	
    	
    	
    	++generation;
        for(int i = 0; i < GEN_SIZE; i++)
        {
        	Bird newBird = new Bird(30,500);
        	mBirds.add(newBird);
        }
        
        
       
    }
    
    
    private void spawnNewGeneration(Bird parent) 
    {
    	
        for(int i = 0; i < GEN_SIZE; i++)
        {
        		Bird offspring = new Bird(parent);
				offspring.mutate();
	        	mBirds.add(offspring);
        }
        ++generation;

    }
    
    
    private void spawnTube()
    {
    	mTube.add(new Tube( MathUtils.random(500, 720)));
    }
    
    
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        mCam.setToOrtho(false, 480,800);
        
        
        mBirds = new ArrayList<Bird>();
        mSavedBirds = new ArrayList<Bird>();
        spawnNewGeneration();
        
        
        mTube = new Array<Tube>();
        spawnTube();
        
        
     
        

    }

    @Override
    public void handleInput()
    {

      

       
  
     
        
      
    }

    
    
    /*
     * Updates the birds, Tubes. Checks if birds have collided with tubes and whether any tubes have a left the scene
     */
    @Override
    public void update(float dt) {
        

    		
    	
        // spawns new tube, if only 1 tube and current x pos < 300
        if(mTube.size <= 1)
        {
        	
        	if(mTube.size == 0)
        	{
        		spawnTube();
        	}
        	
        	else
        	{
        		
        	
	        	for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
	                Tube tube = iter.next();
	                
	                if(tube.getBotTube().x < 200)
	                {
	                	spawnTube();
	                }
	        	}
        	}
        	
        }
        
        
        // update position of tubes and remove if out of frame
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) 
        {
            Tube tube = iter.next();
            
            
	        tube.translateHorizontally(-250 * dt);
	            
	   
	        if(tube.getBotTube().x + tube.getBotTube().width < 0)
	        {
	        	iter.remove();
	        }
            
        }
        
        

        
        
	      // iterate through each bird checks for collisions and removes if so
	      for (Iterator<Bird> iter = mBirds.iterator(); iter.hasNext(); ) 
	      {

	    	  
	           Bird bird = iter.next();
	           
	           
	         
		      	  // iterate through tubes checking for collisions
		      	  for(Iterator<Tube> iterTube = mTube.iterator(); iterTube.hasNext(); )
		      	  {
		      		  Tube tube = iterTube.next();
		      		  
		      		  // if collision has occurred remove bird
		          	  if(bird.overlaps(tube.getBotTube()) || bird.overlaps(tube.getTopTube()))
		          	  {
		          		  // saves the deleted bird
		          		  mSavedBirds.add(new Bird(bird));
		          		  iter.remove();
		          	  }
		      	  }
	           
	           
	           
	       		

	           
//	       		double output = bird.getBrain().predict(inputs);
	       		
	       		

//	            System.out.println("yTopTube: " + Double.toString(yTopTube));
//	            System.out.println("yBotTube: " + Double.toString(yBotTube));
//	            System.out.println("xTube: " + Double.toString(xTube));
//	            System.out.println("yBird: " +Double.toString(yBird));
//	            
//	       		System.out.println("prediction: " + Double.toString(output));
//	       		System.out.println("pop size: " + Integer.toString(mBirds.size()));
//	       		System.out.println("Highest fitness: " + Long.toString(bestBird.getFitness()));
	       		
	       		
//	       		// bird makes a decision
//	       		if(output > 0.5)
//	       		{
//	       			bird.jump();
//	       		}
	       		



	       }
        
	      
	      
	      
	    		
	      // Iterates through each bird, updates inputs, updates response and checks for collision
	      for (Iterator<Bird> iter = mBirds.iterator(); iter.hasNext(); ) 
	      {

	    	  
	           Bird bird = iter.next();
	           
	           // initial reference for closest tube
	           Tube closestTube = new Tube(10000);
	        
	           
	           // iterates through each tube to find closest
	           for (Iterator<Tube> iterTube = mTube.iterator(); iterTube.hasNext(); ) {
	               Tube tube = iterTube.next();
	               
	               
	               if((tube.getBotTube().x - bird.x) > 0.0)
	               {
	                   // if current tube is closer than current closest, it becomes the closest
	                   if(tube.getBotTube().x - bird.x < closestTube.getBotTube().x - bird.x)
	                   {
	                	   closestTube = tube;
	                   }
	               }
	           }
	           

	           
	           // update input information
		       double yTopTube = closestTube.getTopTube().y;
		       double yBotTube = closestTube.getBotTube().y + closestTube.getBotTube().height;
		       double xTube = closestTube.getBotTube().x;
		       double yBird = bird.y;
		       
		       
		
		       
	           	// creates input array for bird
	           	ArrayList<Double> inputs = new ArrayList<Double>();
	       		inputs.add((double)yTopTube);
	       		inputs.add((double)yBotTube);
	       		inputs.add((double)xTube);
	       		inputs.add((double)yBird);
	       		
	       		
	       		
	       		bird.think(inputs);
	       		bird.update(dt);
	       		
	       		
	       		
	       		
	       		if(bird.getScore() > highestScore)
	       		{
	       			highestScore = bird.getScore();
	       			//System.out.println("High score: " + highestScore);
	       		}
	       		
	       		
	       		
	       		
//	       		System.out.println("Bird prediction: " + bird.getBrain().predict(inputs));
//	       		System.out.println("Bird y: " + bird.y);
	       		

	      }
	      

        
        
        
        
        
        
        
        
        
        
        
        

      
      

      
      // if no birds left, spawn new generation
      if(mBirds.isEmpty())
      {

    	  mTube.clear();
    	  spawnTube();
    	  
	      // calculate total score
	      long totalScore = 0;
	      for(Iterator<Bird> iter = mSavedBirds.iterator(); iter.hasNext(); )
	      {
	      	Bird bird = iter.next();
	      	totalScore += bird.getScore();
	      }
	      	
	      // calculate fitness of each bird
	      for(Iterator<Bird> iter = mSavedBirds.iterator(); iter.hasNext(); )
	      {
	      	Bird bird = iter.next();
	      	
	      	double fitness = (double)bird.getScore()/totalScore;
	      	bird.setFitness((double)bird.getScore()/totalScore); 
	      }
	    	  
	    	  
	    
	      Bird newBird = new Bird(pickOne());
	      
	    	  
	      spawnNewGeneration(newBird);

	     }
      


      
    }
    
    private Bird pickOne() {
		
    	// creates a random bird with fitness = 0
    	Bird bestBird = new Bird(0,0);
    	
    	// find bird with highest fitness
	    for(Iterator<Bird> iter = mSavedBirds.iterator(); iter.hasNext(); )
	    {
	    	Bird bird = iter.next();
	    	
	    	if(bird.getFitness() > bestBird.getFitness())
	    	{
	    		bestBird = new Bird(bird);
	    	}
	     }
    	
    	// deletes the saved birds
    	mSavedBirds.clear();
    	
    	// reset the birds score and fitness
    	bestBird.setFitness(0.0);
    	bestBird.setScore(0);
    	
		return new Bird(bestBird);
	}


	/*
     * Draws the entire scene
     */
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
        
        
        font.draw(sB, "Gen: " + generation, FlappyDemo.sWIDTH - 150, FlappyDemo.sHEIGHT - 20);
        font.draw(sB, "HighScore: " + highestScore, FlappyDemo.sWIDTH - 150, FlappyDemo.sHEIGHT - 50);
        font.draw(sB, "Current Score: " + mBirds.iterator().next().getScore(), FlappyDemo.sWIDTH - 150, FlappyDemo.sHEIGHT - 80);
        font.draw(sB, "Pop size: " + mBirds.size(), FlappyDemo.sWIDTH - 150, FlappyDemo.sHEIGHT - 110);
        
        sB.end();
    }

    
    
    
    @Override
    public void dispose() {
    	
        for (Iterator<Bird> iter = mBirds.iterator(); iter.hasNext(); ) {
            Bird bird = iter.next();
            bird.dispose();
        }
        
        for (Iterator<Tube> iter = mTube.iterator(); iter.hasNext(); ) {
            Tube tube = iter.next();
            tube.dispose();
        }
        
        
    	mBg.dispose();
    }
}
