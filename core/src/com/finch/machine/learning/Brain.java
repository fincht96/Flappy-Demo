package com.finch.machine.learning;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.finch.neuralnet.Matrix;
import com.finch.neuralnet.NeuralNet;

public class Brain implements Cloneable{

	private NeuralNet nn;
	
	
	public Brain()
	{
		nn = new NeuralNet();
		
		// 4 inputs, 7 hidden, 1 output, learning rate 0.3
		nn.init(4,7,1,0.3);
		
		
	}
	

	public void mutate()
	{
		
		
		 

		
		try 
		{
			NeuralNet clone = (NeuralNet) nn.clone();
			
			
			Matrix mat = clone.getWeightsHiddenOutput();
			mat.setElement(0, 0, 1.0);
			
			//Matrix weights = clone.getWeightsHiddenOutput();
			//weights.setElement(0, 0, 1.0);
	
			
			
			//clone.setWeightsHiddenOutput(weights);
//			clone = new NeuralNet();
			clone.init(4,7,1,0.3);

			
			
			
			this.nn = clone;
			
		} 
		catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		
//		
//		Matrix wHO = clone.getWeightsHiddenOutput();
//		
//		for(int r =0; r < wHO.getNumRows(); r++)
//		{
//			for(int c = 0; c < wHO.getNumColumns(); c++)
//			{
//				double newVal = wHO.getElement(r,c);
//				
//				//newVal *= 0.95;
//				
//				wHO.setElement(r, c, 1.0);
//			}
//		}
//		
//		clone.setWeightsHiddenOutput(wHO);
//		
//		
//		
//		
//		Matrix wIH = clone.getWeightsInputHidden();
//		
//		for(int r =0; r < wIH.getNumRows(); r++)
//		{
//			for(int c = 0; c < wIH.getNumColumns(); c++)
//			{
//				double newVal = wIH.getElement(r,c);
//				
//				//newVal *= 0.95;
//				
//				wIH.setElement(r, c, 1.0);
//			}
//		}
//		
//		clone.setWeightsInputHidden(wIH);
//		
//		this.nn = clone;
		
		//this.nn = new NeuralNet();
		//this.nn.init(4,7,1,0.3);
		
	
		
//		if(clone.equals(nn))
//			System.out.println("they are equal  still!!");
		
	}
	
	public double predict(ArrayList<Double> inputs)
	{
		Matrix resultMat = nn.query(inputs);
		return resultMat.getElement(0, 0);
	}
	
	
    public Object clone()throws CloneNotSupportedException{  
    	return super.clone();  
    	}  
	
	
}
