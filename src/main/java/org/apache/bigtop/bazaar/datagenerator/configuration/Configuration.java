package org.apache.bigtop.bazaar.datagenerator.configuration;

import java.util.Vector;

public interface Configuration
{

	public abstract int getNumberParticles();

	public abstract long getSteps();

	public abstract double getTimestep();

	public abstract double getTemperature();

	public abstract double getDamping();

	public abstract double getBoundaryRadius();

	public abstract double getBoundaryStrength();

	public abstract double getParticleMass();

	public abstract Vector<Booth> getBooths();

}