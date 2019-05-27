package com.finch.machine.learning;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.finch.neuralnet.Matrix;
import com.finch.neuralnet.NeuralNet;

public class Brain{

	private NeuralNet nn;
	
	
	
	public Brain(Brain b)
	{
		nn = new NeuralNet(b.nn);
	}
	
	public Brain()
	{
		nn = new NeuralNet();
		
		// 4 inputs, 7 hidden, 1 output, learning rate 0.3
		nn.init(4,7,1,0.3);
		
	}
	

	public void mutate()
	{
		//NeuralNet clone = new NeuralNet(nn);
		

		Matrix wHO = nn.getHiddenOutputWeights();
		
		for(int r =0; r < wHO.getNumRows(); r++)
		{
			for(int c = 0; c < wHO.getNumColumns(); c++)
			{
				double newVal = wHO.getElement(r,c);
				
				int prob = MathUtils.random(0, 100);
				
				if(prob < 1)
				{
					newVal *= MathUtils.random(0.50f, 1.50f);
				}
				
				
				if(prob < 5)
				{
					newVal *= MathUtils.random(0.95f, 1.05f);
				}
				
				
				wHO.setElement(r, c, newVal);
			}
		}
		
		nn.setHiddenOutputWeights(wHO);
		
		
		
		
		Matrix wIH = nn.getInputHiddenWeights();
		
		for(int r =0; r < wIH.getNumRows(); r++)
		{
			for(int c = 0; c < wIH.getNumColumns(); c++)
			{
				double newVal = wIH.getElement(r,c);
				
				
				int prob = MathUtils.random(0, 100);
				
				if(prob < 5)
				{
					newVal *= MathUtils.random(0.95f, 1.05f);
				}
				
				wIH.setElement(r, c, newVal);
			}
		}
		
		nn.setInputHiddenWeights(wIH);

		
	}
	
	public double predict(ArrayList<Double> inputs)
	{
		Matrix resultMat = nn.query(inputs);
		return resultMat.getElement(0, 0);
	}
	
	
 
	
	
}
