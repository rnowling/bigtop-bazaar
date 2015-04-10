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

import java.util.Random;

public class BoundedBiGaussianMixtureSampler implements Sampler<Double>
{
	double lowerbound;
	double upperbound;
	double mean1;
	double mean2;
	double weight1;
	double weight2;
	double std;
	Random rng;
	
	public BoundedBiGaussianMixtureSampler(double lowerbound, double upperbound,
			double mean1, double mean2, double weight1, double weight2, double std, Random rng)
	{
		this.lowerbound = lowerbound;
		this.upperbound = upperbound;
		this.mean1 = mean1;
		this.mean2 = mean2;
		this.weight1 = weight1;
		this.weight2 = weight2;
		this.std = std;
		this.rng = rng;
	}
	
	public Double sample()
	{
		while(true)
		{
			double x;
			
			if(rng.nextDouble() < weight1)
			{
				x = rng.nextGaussian() * std + mean1;
			}
			else
			{
				x = rng.nextGaussian() * std + mean2;
			}
			
			if(x >= lowerbound && x <= upperbound)
			{
				return x;
			}
		}
	}
}
