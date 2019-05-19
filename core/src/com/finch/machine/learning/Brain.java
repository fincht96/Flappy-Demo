package com.finch.machine.learning;

import java.util.ArrayList;

import com.finch.neuralnet.Matrix;
import com.finch.neuralnet.NeuralNet;

public class Brain {

	NeuralNet nn;
	
	
	public Brain()
	{
		nn = new NeuralNet();
		
		// 4 inputs, 4 hidden, 1 output, learning rate 0.3
		nn.init(4,4,1,0.3);
		
	}
	
	
	
	public double predict(ArrayList<Double> inputs)
	{
		Matrix resultMat = nn.query(inputs);
		return resultMat.getElement(0, 0);
	}
	
	
	
	
}
