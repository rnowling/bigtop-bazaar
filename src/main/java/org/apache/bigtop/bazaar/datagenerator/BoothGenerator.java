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
package org.apache.bigtop.bazaar.datagenerator;

import java.util.Vector;

import org.apache.bigtop.bazaar.datagenerator.configuration.Booth;
import org.apache.bigtop.bazaar.datagenerator.configuration.BoothParameters;

public class BoothGenerator
{
	BoothParameters params;
	
	public BoothGenerator(BoothParameters params)
	{
		this.params = params;
	}
	
	public Vector<Booth> generate()
	{
		Vector<Booth> booths = new Vector<Booth>();
		double xSum = 0.0;
		double ySum = 0.0;
		
		for(int row = 0; row < params.getRows(); row++)
		{
			for(int column = 0; column < params.getColumns(); column++)
			{
				double x = params.getRadii() + (params.getRadii() + params.getRowSpacing()) * row;
				double y = params.getRadii() + (params.getRadii() + params.getColumnSpacing()) * column;
				
				xSum += x;
				ySum += y;
				
				Booth booth = new Booth();
				booth.setPositionX(x);
				booth.setPositionY(y);
				booth.setRadius(params.getRadii());
				
				booths.add(booth);
			}
		}
		
		double xCenter = xSum / booths.size();
		double yCenter = ySum / booths.size();
		
		for(Booth booth : booths)
		{
			booth.setPositionX(booth.getPositionX() - xCenter);
			booth.setPositionY(booth.getPositionY() - yCenter);
		}
		
		return booths;
	}
}
