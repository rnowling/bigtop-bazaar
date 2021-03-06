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


public class SimulationState
{
	int numberParticles;
	double time;
	Vec2D[] positions;
	Vec2D[] velocities;
	Vec2D[] forces;
	double kineticEnergy;
	double potentialEnergy;
	long step;
	
	public SimulationState(int nParticles)
	{
		this.numberParticles = nParticles;
		positions = new Vec2D[nParticles];
		velocities = new Vec2D[nParticles];
		forces = new Vec2D[nParticles];
	}
	
	public int getNumberParticles()
	{
		return this.numberParticles;
	}
	
	public double getTime()
	{
		return time;
	}
	
	public void setTime(double time)
	{
		this.time = time;
	}
	
	public Vec2D[] getPositions()
	{
		return positions;
	}
	
	public void setPositions(Vec2D[] positions)
	{
		this.positions = positions;
	}
	
	public Vec2D[] getVelocities()
	{
		return velocities;
	}
	
	public void setVelocities(Vec2D[] velocities)
	{
		this.velocities = velocities;
	}
	
	public Vec2D[] getForces()
	{
		return forces;
	}
	
	public void setForces(Vec2D[] forces)
	{
		this.forces = forces;
	}
	
	public double getKineticEnergy()
	{
		return kineticEnergy;
	}
	
	public void setKineticEnergy(double kineticEnergy)
	{
		this.kineticEnergy = kineticEnergy;
	}
	
	public double getPotentialEnergy()
	{
		return potentialEnergy;
	}
	
	public void setPotentialEnergy(double potentialEnergy)
	{
		this.potentialEnergy = potentialEnergy;
	}

	public long getStep()
	{
		return step;
	}

	public void setStep(long step)
	{
		this.step = step;
	}
}
