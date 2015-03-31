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

public class TopologyImpl implements Topology
{
	int numberParticles;
	double temperature;
	double[] particleMasses;
	Vec2D[] initialPositions;
	
	/* (non-Javadoc)
	 * @see org.apache.bigtop.bazaar.datagenerator.base.Topology#getNumberParticles()
	 */
	@Override
	public int getNumberParticles()
	{
		return numberParticles;
	}
	
	public void setNumberParticles(int numberParticles)
	{
		this.numberParticles = numberParticles;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.bigtop.bazaar.datagenerator.base.Topology#getParticleMasses()
	 */
	@Override
	public double[] getParticleMasses()
	{
		return particleMasses;
	}
	
	public void setParticleMasses(double[] particleMasses)
	{
		this.particleMasses = particleMasses;
	}

	public double getTemperature()
	{
		return temperature;
	}

	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}

	public Vec2D[] getInitialPositions()
	{
		return initialPositions;
	}

	public void setInitialPositions(Vec2D[] initialPositions)
	{
		this.initialPositions = initialPositions;
	}
}
