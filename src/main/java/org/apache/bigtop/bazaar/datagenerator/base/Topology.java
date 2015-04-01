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
package org.apache.bigtop.bazaar.datagenerator.base;

public class Topology
{
	int numberParticles;
	double temperature;
	double[] particleMasses;
	Vec2D[] initialPositions;
	Vec2D[] initialVelocities;
	
	public Topology(int numberParticles, double temperature)
	{
		this.numberParticles = numberParticles;
		this.temperature = temperature;
		this.particleMasses = new double[numberParticles];
		this.initialPositions = new Vec2D[numberParticles];
		this.initialVelocities = new Vec2D[numberParticles];
	}
	
	public int getNumberParticles()
	{
		return numberParticles;
	}
	
	public double getParticleMass(int particle)
	{
		return particleMasses[particle];
	}
	
	public void setParticleMass(int particle, double particleMass)
	{
		this.particleMasses[particle] = particleMass;
	}

	public double getTemperature()
	{
		return temperature;
	}

	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}

	public Vec2D getInitialPositions(int particle)
	{
		return initialPositions[particle];
	}

	public void setInitialPositions(int particle, Vec2D positions)
	{
		this.initialPositions[particle] = positions;
	}
	
	public Vec2D getInitialVelocities(int particle)
	{
		return initialVelocities[particle];
	}

	public void setInitialVelocities(int particle, Vec2D velocities)
	{
		this.initialVelocities[particle] = velocities;
	}
}
