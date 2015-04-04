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

public class Booth
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

	public double getPositionX()
	{
		return positionX;
	}
	
	public double getPositionY()
	{
		return positionY;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public double getStrength()
	{
		return strength;
	}
	
	public String toString()
	{
		return "Booth: position(" + positionX + ", " + positionY + "), radius(" + radius + "), strength(" + strength + ")";
	}
}