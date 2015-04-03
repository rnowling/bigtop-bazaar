package org.apache.bigtop.bazaar.datagenerator.potentials;

import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;

public class GaussianPotential implements Potential
{
	Vec2D center;
	double variance;
	double scale;
	Vec2D[] forces;
	double totalEnergy;
	
	public GaussianPotential(Vec2D center, double centerPE, double radius)
	{
		// use 3 std devs distance
		variance = (radius / 3.0) * (radius  / 3.0);
		scale = centerPE;
	}
	
	@Override
	public void update(Vec2D[] positions)
	{
		totalEnergy = 0.0;
		
		// -1.0 b/c we want it rotated 180 degrees
		// scale because we want to control height
		double constant = 1.0 * scale;
		
		for(int i = 0; i < positions.length; i++)
		{
			Vec2D pos = positions[i];
			
			Vec2D diff = pos.sub(center);
			double r = diff.norm();
			
			double exp = Math.exp(-1.0 * r * r / (2.0 * variance));
			double dexpdr = -2.0 * r / (2.0 * variance);
			double forceScalar = constant * exp * dexpdr;
			totalEnergy += constant * exp;
			
			forces[i] = diff.scalarMult(-1.0 * forceScalar);
		}

	}

	@Override
	public double getEnergy()
	{
		return totalEnergy;
	}

	@Override
	public Vec2D[] getForces()
	{
		return forces;
	}

}
