package org.apache.bigtop.bazaar.datagenerator.latentvariablemodel;

import java.util.Arrays;

public class UserWeights
{
	int latentVariables;
	int users;
	double[][] weights;
	
	public UserWeights(int latentVariables, int users, double strength)
	{
		this.latentVariables = latentVariables;
		this.users = users;
		weights = new double[latentVariables][users];
		
		for(int i = 0; i < latentVariables; i++)
		{
			Arrays.fill(weights[i], strength);
		}
	}
	
	public int getUsers()
	{
		return users;
	}
	
	public int getLatentVariables()
	{
		return latentVariables;
	}
}
