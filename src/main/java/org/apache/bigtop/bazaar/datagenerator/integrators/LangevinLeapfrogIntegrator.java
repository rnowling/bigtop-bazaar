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
package org.apache.bigtop.bazaar.datagenerator.integrators;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.base.Constants;
import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.potentials.Potential;

public class LangevinLeapfrogIntegrator implements Integrator
{
	double timestep;
	double temperature;
	double gamma;
	double[] masses;
	Potential[] potentials;
	Random rng;
	
	/**
	 * Langevin Leapfrog integrator for the Langevin stochastic differential
	 * equation.
	 * 
	 * @param timestep Timestep in units of ps
	 * @param temperature Temperature in units of K
	 * @param gamma Damping in units of 1/ps
	 */
	public LangevinLeapfrogIntegrator(double timestep, double temperature, double gamma,
			double[] masses, Potential[] potentials, Random rng)
	{
		this.timestep = timestep;
		this.temperature = temperature;
		this.gamma = gamma;
		this.masses = masses;
		this.potentials = potentials;
		this.rng = rng;
	}
	
	protected void updatePotentials(Vec2D[] positions)
	{
		for(int i = 0; i < potentials.length; i++)
		{
			potentials[i].update(positions);
		}
	}
	
	protected double getPotentialEnergy()
	{
		double potentialEnergy = 0.0;
		for(int i = 0; i < potentials.length; i++)
		{
			potentialEnergy += potentials[i].getEnergy();
		}
		
		return potentialEnergy;
	}
	
	protected Vec2D[] getForces(int numberParticles)
	{
		Vec2D[] forces = new Vec2D[numberParticles];
		
		for(int i = 0; i < numberParticles; i++)
		{
			forces[i] = new Vec2D(0.0, 0.0);
		}
		
		for(int i = 0; i < potentials.length; i++)
		{
			Vec2D[] nextForces = potentials[i].getForces();
			for(int j = 0; j < nextForces.length; j++)
			{
				forces[j] = forces[i].add(nextForces[j]);
			}
		}
		
		return forces;
	}
	
	protected double computeKineticEnergy(Vec2D[] velocities)
	{
		double kineticEnergy = 0.0;
		for(int i = 0; i < velocities.length; i++)
		{
			kineticEnergy += 0.5 * velocities[i].squaredNorm();
		}
		
		return kineticEnergy;
	}
	
	protected Vec2D gaussianVector()
	{
		return new Vec2D(rng.nextGaussian(),
				rng.nextGaussian());
	}
	
	@Override
	public SimulationState integrate(SimulationState state)
	{	
		Vec2D[] newPositions = new Vec2D[state.getNumberParticles()];
		Vec2D[] newVelocitiesHalf = new Vec2D[state.getNumberParticles()];
		Vec2D[] newVelocitiesFull = new Vec2D[state.getNumberParticles()];
		
		double a = Math.exp(-1.0 * gamma * timestep / 2.0);
		double b = (1.0 - a) / gamma;
		double c = Math.sqrt(2.0 * Constants.BOLTZMANN * temperature * gamma);
		double d = Math.sqrt((1.0 - Math.exp(-1.0 * gamma * timestep)) / (2.0 * gamma));
		
		Vec2D[] positions = state.getPositions();
		updatePotentials(positions);
		Vec2D[] forces = getForces(state.getNumberParticles());
		for(int i = 0; i < state.getNumberParticles(); i++)
		{
			newVelocitiesHalf[i] = state.getVelocities()[i].scalarMult(a)
					.add(forces[i].scalarMult(b / masses[i]))
					.add(gaussianVector().scalarMult(c * d / Math.sqrt(masses[i])));
			
			newPositions[i] = positions[i].add(newVelocitiesHalf[i].scalarMult(timestep));
		}
		
		updatePotentials(positions);
		forces = getForces(state.getNumberParticles());
		for(int i = 0; i < state.getNumberParticles(); i++)
		{
			newVelocitiesFull[i] = newVelocitiesHalf[i].scalarMult(a)
					.add(forces[i].scalarMult(b / masses[i]))
					.add(gaussianVector().scalarMult(c * d / Math.sqrt(masses[i])));
		}
		
		state.setForces(forces);
		state.setPositions(newPositions);
		state.setVelocities(newVelocitiesFull);
		state.setKineticEnergy(computeKineticEnergy(newVelocitiesFull));
		state.setPotentialEnergy(getPotentialEnergy());
		state.setStep(state.getStep() + 1L);
		state.setTime(state.getStep() * timestep);
		
		return state;
	}

}
