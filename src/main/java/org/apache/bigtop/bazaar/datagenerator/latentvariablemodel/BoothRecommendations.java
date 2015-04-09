package org.apache.bigtop.bazaar.datagenerator.latentvariablemodel;

import java.util.Arrays;

public class BoothRecommendations
{
	int products;
	int users;
	double[][] matrix;
	
	public BoothRecommendations(int products, int users, double fillValue)
	{
		this.products = products;
		this.users = users;
		matrix = new double[products][users];
		
		for(int i = 0; i < products; i++)
		{
			Arrays.fill(matrix[i], fillValue);
		}
	}
	
	public double getRating(int product, int user)
	{
		return matrix[product][user];
	}
	
	public double[] getRatings(int product)
	{
		return matrix[product];
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
