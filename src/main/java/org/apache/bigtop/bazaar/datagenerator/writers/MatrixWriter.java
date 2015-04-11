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
package org.apache.bigtop.bazaar.datagenerator.writers;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.bigtop.bazaar.datagenerator.base.Matrix;

public class MatrixWriter
{
	String flname;
	
	public MatrixWriter(String flname)
	{
		this.flname = flname;
	}
	
	public void write(Matrix matrix) throws IOException
	{
		Path path = Paths.get(flname);
		Writer writer = Files.newBufferedWriter(path, Charset.forName("US-ASCII"));
		
		writer.write("rows " + matrix.getRows() + "\n");
		writer.write("columns " + matrix.getColumns() + "\n");
		for(int r = 0; r < matrix.getRows(); r++)
		{
			for(int c = 0; c < matrix.getColumns(); c++)
			{
				writer.write(matrix.getElement(r, c) + " ");
			}
			writer.write("\n");
		}
		
		writer.close();
	}
}
