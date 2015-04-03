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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.base.Constants;
import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.base.VelocitySampler;
import org.apache.bigtop.bazaar.datagenerator.potentials.Potential;
import org.junit.Test;


public class TestLangevinLeapfrogIntegrator
{
	static final double EPS = 1e-3;
	
	@Test
	public void testKineticEnergy()
	{
		final int N_SAMPLES = 100000;
		final int N_PARTICLES = 100;
		final double mass = 2.0;
		final double temperature = 300.0; // Kelvin
		final double timestep = 0.1;
		final double damping = 10.0;
		
		/*
		 * From kinetic theory:
		 * avg KE = D / 2 * k_B * T 
		 * 
		 * where KE = kinetic energy
		 * T = temperature
		 * D = degrees of freedom (2 * number of particles in our case)
		 * k_B = Boltzmann's constant
		 */
		final double expectedKE = N_PARTICLES * Constants.BOLTZMANN * temperature;
		
		Random rng = new Random();
		double[] masses = new double[N_PARTICLES];
		Arrays.fill(masses, mass);
		Integrator integrator = new LangevinLeapfrogIntegrator(timestep,
				temperature, damping, masses, new Potential[] {}, rng);
		
		SimulationState state = new SimulationState(N_PARTICLES);
		Vec2D[] positions = new Vec2D[N_PARTICLES];
		Vec2D[] velocities = new Vec2D[N_PARTICLES];
		Vec2D[] forces = new Vec2D[N_PARTICLES];
		VelocitySampler sampler = new VelocitySampler(temperature, mass, rng);
		for(int i = 0; i < N_PARTICLES; i++)
		{
			positions[i] = new Vec2D(0.0, 0.0);
			velocities[i] = sampler.sample();
			forces[i] = new Vec2D(0.0, 0.0);
		}
		state.setForces(forces);
		state.setVelocities(velocities);
		state.setPositions(positions);
		
		double averageKE = 0.0;
		for(int i = 0; i < N_SAMPLES; i++)
		{
			state = integrator.integrate(state);
			
			averageKE += state.getKineticEnergy() / (double) N_SAMPLES;
		}
		
		double percentError = (expectedKE - averageKE) / expectedKE;
		assertEquals("Observed KE: " + averageKE + ", expected KE: " + expectedKE + ", percent error: " + percentError,
				0.0, percentError, EPS);
	}
}


