/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
