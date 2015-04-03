package org.apache.bigtop.bazaar.datagenerator.potentials;

import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;

public class SphericalBoundaryConditions implements Potential
{
	Vec2D center;
	double radius;
	double strength;
	double totalEnergy;
	Vec2D[] forces;
	
	public SphericalBoundaryConditions(Vec2D center, double radius, double strength)
	{
		this.center = center;
		this.radius = radius;
		this.strength = strength;
	}
	
	@Override
	public void update(Vec2D[] positions)
	{
		totalEnergy = 0.0;
		forces = new Vec2D[positions.length];
		
		for(int i = 0; i < positions.length; i++)
		{
			Vec2D pos = positions[i];
			Vec2D diff = pos.sub(center);
			double distCenter = diff.norm();
			
			double diffCircum = distCenter - radius;
			forces[i] = new Vec2D(0.0, 0.0);
			
			// outside circumference
			if(diffCircum > 0.0)
			{
				totalEnergy += strength * diffCircum * diffCircum;
				double scalarForce = 2.0 * strength * diffCircum;
				forces[i] = diff.scalarMult(-1.0 * scalarForce / distCenter);
			}
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
