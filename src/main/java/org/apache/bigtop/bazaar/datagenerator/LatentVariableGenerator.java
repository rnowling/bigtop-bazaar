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

import org.apache.bigtop.bazaar.datagenerator.base.Matrix;
import org.apache.bigtop.bazaar.datagenerator.configuration.RecommendationsParameters;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.BoundedBiGaussianMixtureSampler;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.MatrixGenerator;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.Sampler;

public class LatentVariableGenerator
{
	int booths;
	int latentFactors;
	MatrixGenerator generator;
	
	public LatentVariableGenerator(RecommendationsParameters parameters, int booths, Random rng)
	{
		this.booths = booths;
		this.latentFactors = parameters.getNumberLatentFactors();
		Sampler<Double> sampler = new BoundedBiGaussianMixtureSampler(0.0, 1.0, 0.25, 0.75, 0.1, 0.9, 0.2, rng);
		generator = new MatrixGenerator(sampler);
	}
	
	public Matrix generate()
	{
		return generator.generate(booths, latentFactors);
	}
}
