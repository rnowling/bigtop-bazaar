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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ConfigurationReader
{
	String filename;
	
	public ConfigurationReader(String filename)
	{
		this.filename = filename;
	}
	
	protected Booth parseBooth(Object tree)
	{
		Map<String, Object> jsonBooth = (Map<String, Object>) tree;
		
		Booth booth = new Booth();
		
		for(Map.Entry<String, Object> entry : jsonBooth.entrySet())
		{
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(key.equalsIgnoreCase("positionX"))
			{
				booth.setPositionX((double) value);
			}
			else if(key.equalsIgnoreCase("positionY"))
			{
				booth.setPositionY((double) value);
			}
			else if(key.equalsIgnoreCase("radius"))
			{
				booth.setRadius((double) value);
			}
			else if(key.equalsIgnoreCase("strength"))
			{
				booth.setStrength((double) value);
			}
		}
		
		return booth;
	}
	
	protected SimulationParameters parseSimulationParameters(Object tree)
	{
		Map<String, Object> jsonConfiguration = (Map<String, Object>) tree;
		
		SimulationParameters config = new SimulationParameters();
		
		for(Map.Entry<String, Object> entry : jsonConfiguration.entrySet())
		{
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(key.equalsIgnoreCase("steps"))
			{
				config.setSteps(((Double) value).longValue());
			}
			else if(key.equalsIgnoreCase("timestep"))
			{
				config.setTimestep((double) value);
			}
			else if(key.equalsIgnoreCase("temperature"))
			{
				config.setTemperature((double) value);
			}
			else if(key.equalsIgnoreCase("damping"))
			{
				config.setDamping((double) value);
			}
			else if(key.equalsIgnoreCase("numberParticles"))
			{
				config.setNumberParticles(((Double) value).intValue());
			}
			else if(key.equalsIgnoreCase("particleMass"))
			{
				config.setParticleMass((double) value);
			}
			else if(key.equalsIgnoreCase("boundaryRadius"))
			{
				config.setBoundaryRadius((double) value);
			}
			else if(key.equalsIgnoreCase("boundaryStrength"))
			{
				config.setBoundaryStrength((double) value);
			}
		}
		
		return config;
	}
	
	public Configuration parseConfiguration(Object tree)
	{
		Map<String, Object> jsonConfiguration = (Map<String, Object>) tree;
		
		Configuration config = new Configuration();
		
		
		
		for(Map.Entry<String, Object> entry : jsonConfiguration.entrySet())
		{
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(key.equalsIgnoreCase("simulationParameters"))
			{
				SimulationParameters params = parseSimulationParameters(value);
				config.setSimulationParameters(params);
			}
			else if(key.equalsIgnoreCase("booths"))
			{
				BoothParameters params = new BoothParameters();
				
				for(Object boothObj : (List<Booth>) value)
				{
					Booth booth = parseBooth(boothObj);
					params.addBooth(booth);
				}
				
				config.setBoothParameters(params);
			}
		}
		
		LatentVariableModelParameters lvmParams = new LatentVariableModelParameters();
		lvmParams.setNumberBooths(config.getBoothParameters().getBooths().size());
		lvmParams.setNumberLatentFactors(20);
		lvmParams.setInteractionStrength(2.0);
		config.setLatentVariableModelParameters(lvmParams);
		
		return config;
	}
	
	public Configuration readConfiguration() throws IOException
	{
		Gson gson = new Gson();
		
		Reader reader = new BufferedReader(new FileReader(filename));
		Object json = gson.fromJson(reader, Object.class);
		reader.close();
		
		Configuration config = parseConfiguration(json);
		
		return config;
	}
}
