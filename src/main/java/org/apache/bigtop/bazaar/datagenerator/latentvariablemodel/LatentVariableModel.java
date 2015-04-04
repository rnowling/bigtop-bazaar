package org.apache.bigtop.bazaar.datagenerator.latentvariablemodel;

import java.util.Arrays;

public class LatentVariableModel
{
	int products;
	int users;
	double[][] matrix;
	
	public LatentVariableModel(int products, int users, double fillValue)
	{
		this.products = products;
		this.users = users;
		matrix = new double[users][products];
		
		for(int i = 0; i < users; i++)
		{
			Arrays.fill(matrix[i], fillValue);
		}
	}
	
	public double getRating(int product, int user)
	{
		return matrix[user][product];
	}
	
	public double[] getRatings(int user)
	{
		return matrix[user];
	}
	
	public int getProducts()
	{
		return products;
	}
	
	public int getUsers()
	{
		return users;
	}
}
