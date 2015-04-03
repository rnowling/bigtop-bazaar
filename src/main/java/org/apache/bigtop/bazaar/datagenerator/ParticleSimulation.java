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

import java.util.Arrays;
import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.base.VelocitySampler;
import org.apache.bigtop.bazaar.datagenerator.configuration.Configuration;
import org.apache.bigtop.bazaar.datagenerator.integrators.Integrator;
import org.apache.bigtop.bazaar.datagenerator.integrators.LangevinLeapfrogIntegrator;
import org.apache.bigtop.bazaar.datagenerator.potentials.Potential;

public class ParticleSimulation
{
	Integrator integrator;
	SimulationState currentState;
	Configuration configuration;
	Random rng;
	
	public ParticleSimulation(Configuration configuration, Random rng)
	{
		this.configuration = configuration;
		this.rng = rng;
		
		initialize();
	}
	
	protected void initialize()
	{
		buildSimulationState();
		buildIntegrator();
	}
	
	protected void buildIntegrator()
	{
		double[] masses = new double[configuration.getNumberParticles()];
		Arrays.fill(masses, configuration.getParticleMass());
		integrator = new LangevinLeapfrogIntegrator(configuration.getTimestep(),
				configuration.getTemperature(), configuration.getDamping(),
				masses, new Potential[] {}, rng);
	}
	
	protected void buildSimulationState()
	{
		currentState = new SimulationState(configuration.getNumberParticles());
		currentState.setTime(0.0);
		currentState.setPotentialEnergy(0.0);
		currentState.setKineticEnergy(0.0);
		
		VelocitySampler velocitySampler = new VelocitySampler(configuration.getTemperature(),
				configuration.getParticleMass(), rng);
		
		Vec2D[] velocities = new Vec2D[configuration.getNumberParticles()];
		Vec2D[] positions = new Vec2D[configuration.getNumberParticles()];
		Vec2D[] forces = new Vec2D[configuration.getNumberParticles()];
		for(int i = 0; i < configuration.getNumberParticles(); i++)
		{
			velocities[i] = velocitySampler.sample();
			positions[i] = new Vec2D(0.0, 0.0);
			forces[i] = new Vec2D(0.0, 0.0);
		}
		
		currentState.setForces(forces);
		currentState.setPositions(positions);
		currentState.setVelocities(velocities);
	}
	
	public SimulationState step()
	{
		currentState = integrator.integrate(currentState);
		
		return currentState;
	}
}
