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
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.BoothRecommendations;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.LatentVariables;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.UserWeights;

public class RecommendationsGenerator
{
	LatentVariables latentVariables;
	double strength;
	Random rng;
	
	public RecommendationsGenerator(RecommendationsParameters params, LatentVariables latentVariables, Random rng)
	{
		this.latentVariables = latentVariables;
		this.strength = params.getInteractionStrength();
		this.rng = rng;
	}
	
	protected UserWeights generateWeights(int users)
	{
		return new UserWeights(latentVariables.getLatentVariables(), users, strength);
	}
	
	protected BoothRecommendations project(UserWeights weights)
	{
		return new BoothRecommendations(latentVariables.getBooths(), weights.getUsers(), strength);
	}
	
	public BoothRecommendations generate(int users)
	{
		UserWeights weights = generateWeights(users);
		BoothRecommendations recommendations = project(weights);
		
		return recommendations;
	}
}
