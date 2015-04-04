package org.apache.bigtop.bazaar.datagenerator;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.BoothRecommendations;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.LatentVariables;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.UserWeights;

public class RecommendationsGenerator
{
	LatentVariables latentVariables;
	double strength;
	Random rng;
	
	public RecommendationsGenerator(LatentVariables latentVariables, double strength, Random rng)
	{
		this.latentVariables = latentVariables;
		this.strength = strength;
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
