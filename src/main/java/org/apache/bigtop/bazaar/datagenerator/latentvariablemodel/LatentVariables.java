package org.apache.bigtop.bazaar.datagenerator.latentvariablemodel;

import java.util.Arrays;

public class LatentVariables
{
	int booths;
	int latentVariables;
	double[][] matrix;
	
	public LatentVariables(int booths, int latentVariables, double fillValue)
	{
		this.booths = booths;
		this.latentVariables = latentVariables;
		matrix = new double[booths][latentVariables];
		
		for(int i = 0; i < booths; i++)
		{
			Arrays.fill(matrix[i], fillValue);
		}
	}
	
	public int getBooths()
	{
		return booths;
	}
	
	public int getLatentVariables()
	{
		return latentVariables;
	}
}
