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
package org.apache.bigtop.bazaar.datagenerator.configuration;

public class Configuration
{
	ParticleSimulationParameters simulationParameters;
	BoothParameters boothParameters;
	RecommendationsParameters recommendationsParameters;
	int booths;
	int customers;
	
	public Configuration()
	{
		booths = 0;
		customers = 0;
		
		simulationParameters = new ParticleSimulationParameters();
		boothParameters = new BoothParameters();
		recommendationsParameters = new RecommendationsParameters();
	}
	
	public ParticleSimulationParameters getSimulationParameters()
	{
		return simulationParameters;
	}
	
	public void setSimulationParameters(ParticleSimulationParameters params)
	{
		this.simulationParameters = params;
	}

	public BoothParameters getBoothParameters()
	{
		return boothParameters;
	}

	public void setBoothParameters(BoothParameters boothParameters)
	{
		this.boothParameters = boothParameters;
	}

	public RecommendationsParameters getRecommendationsParameters()
	{
		return recommendationsParameters;
	}

	public void setRecommendationsParameters(
			RecommendationsParameters recommendationsParameters)
	{
		this.recommendationsParameters = recommendationsParameters;
	}

	public int getCustomers()
	{
		return customers;
	}

	public void setCustomers(int customers)
	{
		this.customers = customers;
	}

	public int getBooths()
	{
		return booths;
	}

	public void setBooths(int booths)
	{
		this.booths = booths;
	}
}
