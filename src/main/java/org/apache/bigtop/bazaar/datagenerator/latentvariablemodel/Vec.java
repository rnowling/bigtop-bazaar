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
package org.apache.bigtop.bazaar.datagenerator.latentvariablemodel;

import java.util.Arrays;

public class Vec
{
	int length;
	double[] elements;
	
	public Vec(int length, double fillValue)
	{
		this.length = length;
		this.elements = new double[length];
		Arrays.fill(elements, fillValue);
	}
	
	public void setElement(int idx, double value)
	{
		elements[idx] = value;
	}
	
	public double getElement(int idx)
	{
		return elements[idx];
	}
	
	public Vec add(Vec other)
	{
		Vec vector = new Vec(length, 0.0);
		
		for(int i = 0; i < length; i++)
		{
			vector.setElement(i, elements[i] + other.getElement(i));
		}
		
		return vector;
	}
	
	public Vec sub(Vec other)
	{
		Vec vector = new Vec(length, 0.0);
		
		for(int i = 0; i < length; i++)
		{
			vector.setElement(i, elements[i] - other.getElement(i));
		}
		
		return vector;
	}
	
	public Vec scalarMult(double scalar)
	{
		Vec vector = new Vec(length, 0.0);
		
		for(int i = 0; i < length; i++)
		{
			vector.setElement(i, elements[i] * scalar);
		}
		
		return vector;
	}
	
	public Vec normalize()
	{
		return scalarMult(1.0 / norm());
	}
	
	public double norm()
	{
		return Math.sqrt(squaredNorm());
	}
	
	public double squaredNorm()
	{
		double squaredNorm = 0.0;
		
		for(int i = 0; i < length; i++)
		{
			squaredNorm += elements[i] * elements[i];
		}
		
		return squaredNorm;
	}
	
	public double distance(Vec other)
	{
		Vec diff = this.sub(other);
		return diff.norm();
	}
	
	public void fill(double value)
	{
		Arrays.fill(elements, value);
	}
	
	public Vec copy()
	{
		return scalarMult(1.0);
	}
	
	public double dot(Vec other)
	{
		double dot = 0.0;
		
		for(int i = 0; i < length; i++)
		{
			dot += elements[i] * other.getElement(i);
		}
		
		return dot;
	}
	
	@Override
	public boolean equals(Object other)
	{
		Vec otherVec = (Vec) other;
		
		if(length != otherVec.length)
		{
			return false;
		}
		
		for(int i = 0; i < length; i++)
		{
			if(elements[i] != otherVec.elements[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public int getLength()
	{
		return length;
	}
}
