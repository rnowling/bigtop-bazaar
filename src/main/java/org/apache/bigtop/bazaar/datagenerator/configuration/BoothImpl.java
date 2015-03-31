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

public class BoothImpl implements Booth
{
	double positionX;
	double positionY;
	double radius;
	double strength;
	
	public void setPositionX(double positionX)
	{
		this.positionX = positionX;
	}

	public void setPositionY(double positionY)
	{
		this.positionY = positionY;
	}

	public void setRadius(double radius)
	{
		this.radius = radius;
	}

	public void setStrength(double strength)
	{
		this.strength = strength;
	}

	/* (non-Javadoc)
	 * @see org.apache.bigtop.bazaar.datagenerator.configuration.Booth#getPositionX()
	 */
	@Override
	public double getPositionX()
	{
		return positionX;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.bigtop.bazaar.datagenerator.configuration.Booth#getPositionY()
	 */
	@Override
	public double getPositionY()
	{
		return positionY;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.bigtop.bazaar.datagenerator.configuration.Booth#getRadius()
	 */
	@Override
	public double getRadius()
	{
		return radius;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.bigtop.bazaar.datagenerator.configuration.Booth#getStrength()
	 */
	@Override
	public double getStrength()
	{
		return strength;
	}
	
	public String toString()
	{
		return "Booth: position(" + positionX + ", " + positionY + "), radius(" + radius + "), strength(" + strength + ")";
	}
}
