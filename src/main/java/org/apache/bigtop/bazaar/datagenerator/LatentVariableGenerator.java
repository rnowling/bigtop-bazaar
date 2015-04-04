package org.apache.bigtop.bazaar.datagenerator;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.configuration.RecommendationsParameters;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.LatentVariables;

public class LatentVariableGenerator
{
	int booths;
	int latentFactors;
	double fillValue;
	Random rng;
	
	public LatentVariableGenerator(RecommendationsParameters parameters, Random rng)
	{
		this.booths = parameters.getNumberBooths();
		this.fillValue = parameters.getInteractionStrength();
		this.rng = rng;
	}
	
	public LatentVariables generate()
	{
		return new LatentVariables(booths, latentFactors, fillValue);
	}
}
