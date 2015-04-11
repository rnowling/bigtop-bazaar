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

import java.util.Arrays;

public class Matrix
{
	int rows;
	int columns;
	double[][] matrix;
	
	public Matrix(int rows, int columns, double fillValue)
	{
		this.rows = rows;
		this.columns = columns;
		
		matrix = new double[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			Arrays.fill(matrix[i], fillValue);
		}
	}
	
	public void setElement(int row, int column, double value)
	{
		matrix[row][column] = value;
	}
	
	public double getElement(int row, int column)
	{
		return matrix[row][column];
	}
	
	public void copyToRow(int row, Vec vector)
	{
		for(int i = 0; i < vector.getLength(); i++)
		{
			setElement(row, i, vector.getElement(i));
		}
	}
	
	public void copyToColumn(int column, Vec vector)
	{
		for(int i = 0; i < vector.getLength(); i++)
		{
			setElement(i, column, vector.getElement(i));
		}
	}
	
	public Vec getColumn(int column)
	{
		Vec vector = new Vec(rows, 0.0);
		for(int i = 0; i < vector.getLength(); i++)
		{
			vector.setElement(i, getElement(i, column));
		}
		
		return vector;
	}
	
	public Vec getRow(int row)
	{
		Vec vector = new Vec(columns, 0.0);
		for(int i = 0; i < vector.getLength(); i++)
		{
			vector.setElement(i, getElement(row, i));
		}
		
		return vector;
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}
	
	public Matrix multiply(Matrix other)
	{
		if(getColumns() != other.getRows())
		{
			throw new IllegalArgumentException("Columns of this matrix do not match rows of other matrix");
		}
		
		Matrix result = new Matrix(getRows(), other.getColumns(), 0.0);
		
		for(int r = 0; r < rows; r++)
		{
			Vec row = getRow(r);
			for(int c = 0; c < other.getColumns(); c++)
			{
				Vec col = other.getColumn(c);
				
				result.setElement(r, c, row.dot(col));
			}
		}
		
		return result;
	}
	
	public Matrix transpose()
	{
		Matrix transpose = new Matrix(columns, rows, 0.0);
		for(int r = 0; r < rows; r++)
		{
			transpose.copyToColumn(r, getRow(r));
		}
		
		return transpose;
	}
	
	public Matrix scalarMultiply(double scalar)
	{
		Matrix scaled = new Matrix(rows, columns, 0.0);
		for(int r = 0; r < rows; r++)
		{
			scaled.copyToRow(r, getRow(r).scalarMult(scalar));
		}
		
		return scaled;
	}
}
