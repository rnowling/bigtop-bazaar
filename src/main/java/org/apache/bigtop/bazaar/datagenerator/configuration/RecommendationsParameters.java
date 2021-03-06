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

public class RecommendationsParameters
{
	int numberLatentFactors;
	double interactionStrengthScaleFactor;
	double interactionStrengthOverride = -1.0;
	
	public int getNumberLatentFactors()
	{
		return numberLatentFactors;
	}
	
	public void setNumberLatentFactors(int numberLatentFactors)
	{
		this.numberLatentFactors = numberLatentFactors;
	}
	
	public double getInteractionStrengthScaleFactor()
	{
		return interactionStrengthScaleFactor;
	}
	
	public void setInteractionStrengthScaleFactor(double interactionStrength)
	{
		this.interactionStrengthScaleFactor = interactionStrength;
	}
	
	public double getInteractionStrengthOverride()
	{
		return interactionStrengthOverride;
	}

	public void setInteractionStrengthOverride(double interactionStrengthOverride)
	{
		this.interactionStrengthOverride = interactionStrengthOverride;
	}

	public String toString()
	{
		return "LatentFactors: " + numberLatentFactors + "\n" +
				"InteractionStrengthScaledFactor: " + interactionStrengthScaleFactor + "\n" +
				"InteractionStrengthOverride: " + interactionStrengthOverride + "\n";
	}
}
