package com.finch.game.sprites;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.finch.game.FlappyDemo;
import com.finch.machine.learning.Brain;


/**
 *  Created by Thomas on 19/03/2019
 */

public class Bird extends Sprite implements Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int sGRAVITY = -15;
    public static final int sWIDTH = 32;
    public static final int sHEIGHT = 32;
    public int id;
    public int uniqueID = MathUtils.random(0,200000);
    
    private static Texture sTex = new Texture("bird1.png");
    private Vector3 mVelocity;
  
    private Brain brain;

    private long fitness = 0;
    
    
    
    
    public Bird(int x, int y)
    {
    	this.x = x;
    	this.y = y;

        mVelocity = new Vector3(0,0,0);
        this.tex = sTex;
        
        
        this.width = sWIDTH;
        this.height = sHEIGHT;
        
     
        brain = new Brain();

    }
    


    public void update(float dt)
    {
        if(this.y > 0)
        {
            mVelocity.add(0,sGRAVITY,0);       // y velocity increased by gravity
        }

        mVelocity.scl(dt);                       // multiples all components by dt
    
        this.y += mVelocity.y;		// adds distance (gravity*dt) to y
        
        mVelocity.scl(1/dt);                    // converts distance back to velocity

        if(this.y <= 0)
        {
            this.y = 1;
        }
        
        if(this.y + Bird.sHEIGHT > FlappyDemo.sHEIGHT)
        {
            this.y = FlappyDemo.sHEIGHT - Bird.sHEIGHT;
        }
        
        
        ++fitness;
        
    }

    public void think(ArrayList<Double> inputs)
    {
    	double output = brain.predict(inputs);
    	
   		// bird makes a decision
   		if(output > 0.5)
   		{
   			jump();
   		}
    }
    
    
    public void jump()
    {
        mVelocity.y = 250;
    }
    
    
    public Brain getBrain()
    {
    	return brain;
    }
    
    public void setBrain(Brain b)
    {
    	brain = b;
    }
    
    public long getFitness()
    {
    	return fitness;
    }
    
    public void setFitness(long fitness)
    {
    	this.fitness = fitness;
    }

  
    
    
    public Object clone()throws CloneNotSupportedException{  
    	return super.clone();  
    	}




    
}
