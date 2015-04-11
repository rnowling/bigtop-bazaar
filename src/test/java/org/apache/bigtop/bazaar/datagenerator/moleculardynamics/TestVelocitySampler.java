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
package org.apache.bigtop.bazaar.datagenerator.moleculardynamics;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.moleculardynamics.Constants;
import org.apache.bigtop.bazaar.datagenerator.moleculardynamics.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.moleculardynamics.VelocitySampler;
import org.junit.Test;


public class TestVelocitySampler
{
	static final double EPS = 1e-3;
	
	@Test
	public void testKineticEnergy()
	{
		final int N_SAMPLES = 100000;
		final int N_PARTICLES = 100;
		final double mass = 2.0;
		final double temperature = 300.0; // Kelvin
		
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
		VelocitySampler sampler = new VelocitySampler(temperature, mass, rng);
		
		double averageKE = 0.0;
		for(int i = 0; i < N_SAMPLES; i++)
		{
			double totalKE = 0.0;
			for(int j = 0; j < N_PARTICLES; j++)
			{
				Vec2D velocities = sampler.sample();
				totalKE += 0.5 * mass * velocities.squaredNorm();
			}
			
			averageKE += totalKE / (double) N_SAMPLES;
		}
		
		double percentError = (expectedKE - averageKE) / expectedKE;
		assertEquals("Observed KE: " + averageKE + ", expected KE: " + expectedKE + ", percent error: " + percentError,
				0.0, percentError, EPS);
	}
}


