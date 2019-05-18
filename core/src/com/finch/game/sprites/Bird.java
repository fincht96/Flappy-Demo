package com.finch.game.sprites;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.finch.game.FlappyDemo;


/**
 *  Created by Thomas on 19/03/2019
 */

public class Bird{
    private static final int sGRAVITY = -15;
    public static final int sWIDTH = 32;
    public static final int sHEIGHT = 32;
    private Vector3 mPosition;
    private Vector3 mVelocity;
  
    

    private Texture mBird;
  
    
 
    
 
    
    

    public Vector3 getPosition() {
        return mPosition;
    }

    public Texture getTexture() {
        return mBird;
    }

    
    
    public Bird(int x, int y)
    {
        mPosition = new Vector3(x,y,0);
        mVelocity = new Vector3(0,0,0);
        mBird = new Texture("bird1.png");
        
        
 


    }

    public void update(float dt)
    {
        if(mPosition.y > 0)
        {
            mVelocity.add(0,sGRAVITY,0);       // y velocity increased by gravity
        }

        mVelocity.scl(dt);                       // multiples all components by dt
        mPosition.add(0,mVelocity.y,0);    // adds distance (gravity*dt) to y
        mVelocity.scl(1/dt);                    // converts distance back to velocity

        if(mPosition.y < 0)
        {
            mPosition.y = 0;
        }
        
        if(mPosition.y + Bird.sHEIGHT > FlappyDemo.sHEIGHT)
        {
            mPosition.y = FlappyDemo.sHEIGHT - Bird.sHEIGHT;
        }
        
    }


    public void jump()
    {
        mVelocity.y = 250;
    }
    
    


}
