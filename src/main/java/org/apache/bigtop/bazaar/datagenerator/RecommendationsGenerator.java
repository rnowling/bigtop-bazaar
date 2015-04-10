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
package org.apache.bigtop.bazaar.datagenerator;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.configuration.RecommendationsParameters;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.BoundedBiGaussianMixtureSampler;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.Matrix;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.MatrixGenerator;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.Sampler;

public class RecommendationsGenerator
{
	Matrix latentVariables;
	MatrixGenerator generator;
	RecommendationsParameters params;
	
	public RecommendationsGenerator(RecommendationsParameters params, Matrix latentVariables, Random rng)
	{
		this.params = params;
		this.latentVariables = latentVariables;
		Sampler<Double> sampler = new BoundedBiGaussianMixtureSampler(0.0, 1.0, 0.25, 0.75, 0.1, 0.9, 0.2, rng);
		generator = new MatrixGenerator(sampler);
	}
	
	protected Matrix generateUserWeights(int users)
	{
		return generator.generate(users, params.getNumberLatentFactors());
	}
	
	protected Matrix project(Matrix weights, int users)
	{
		return new Matrix(params.getNumberBooths(), users, params.getInteractionStrength());
	}
	
	public Matrix generate(int users)
	{

		Matrix recomm = project(null, users);
		
		return recomm;
	}
}
