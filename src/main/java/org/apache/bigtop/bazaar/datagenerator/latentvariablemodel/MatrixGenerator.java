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

import org.apache.bigtop.bazaar.datagenerator.base.Vec;



public class MatrixGenerator
{
	Sampler<Double> sampler;
	
	public MatrixGenerator(Sampler<Double> sampler)
	{
		this.sampler = sampler;
	}
	
	private Vec project(Vec u, Vec v)
	{
		return u.scalarMult(u.dot(v) / u.dot(u));
	}
	
	public Matrix generate(int rows, int columns)
	{
		Matrix matrix = new Matrix(rows, columns, 0);
		Vec vector = new Vec(rows, 0.0);
		
		
		int column = 0;
		while(column < columns)
		{
			// sample new vector
			vector.fill(0.0);
			for(int i = 0; i < rows; i++)
			{
				vector.setElement(i, sampler.sample());
			}
			
			// copy first vector to matrix
			if(column == 0)
			{
				matrix.copyToColumn(column, vector.normalize());
				column += 1;
				continue;
			}
			
			// modified Gram Schmidt process
			Vec orthogVector = vector.copy();
			for(int i = 0; i < column; i++)
			{
				Vec colVec = matrix.getColumn(i);
				orthogVector = orthogVector.sub(project(colVec, orthogVector));
			}
			
			// only keeps vectors that are
			// sufficiently different
			if(orthogVector.norm() > 0.05)
			{
				matrix.copyToColumn(column, orthogVector.normalize());
				column += 1;
			}
		}
		
		return matrix;
	}
}
