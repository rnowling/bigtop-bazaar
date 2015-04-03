package org.apache.bigtop.bazaar.datagenerator.potentials;

import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;

public interface Potential
{
	public abstract void update(Vec2D[] positions);
	
	public abstract double getEnergy();
	
	public abstract Vec2D[] getForces();
}
