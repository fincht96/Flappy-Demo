package com.finch.game.sprites;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.finch.game.FlappyDemo;
import com.finch.machine.learning.Brain;


/**
 *  Created by Thomas on 19/03/2019
 */

public class Bird extends Sprite{
    private static final int sGRAVITY = -15;
    public static final int sWIDTH = 32;
    public static final int sHEIGHT = 32;
  
    private Vector3 mVelocity;
  
    private Brain brain;


    
    
    public Bird(int x, int y)
    {
    	this.x = x;
    	this.y = y;

        mVelocity = new Vector3(0,0,0);
        this.tex = new Texture("bird1.png");
        
        
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

        if(this.y < 0)
        {
            this.y = 0;
        }
        
        if(this.y + Bird.sHEIGHT > FlappyDemo.sHEIGHT)
        {
            this.y = FlappyDemo.sHEIGHT - Bird.sHEIGHT;
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
    


}
