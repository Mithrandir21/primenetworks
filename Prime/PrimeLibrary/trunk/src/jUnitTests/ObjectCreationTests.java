/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jUnitTests;


import objects.Object;
import objects.clientObjects.Desktop;
import objects.hardwareObjects.Motherboard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectCreationTests
{
	private static Object[] objects = null;


	/**
	 * TODO - Description
	 */
	@Before
	public void setUp() throws Exception
	{
		objects = ObjectCreationClass.ObjectCreationClass();
	}


	@Test
	public void objectDataReading()
	{
		Desktop desk = (Desktop) objects[0];
		Assert.assertNotNull(desk);

		// The Object serial is not empty
		Assert.assertNotNull(desk.getObjectSerial());

		// The Object name is not empty
		Assert.assertNotNull(desk.getObjectName());

		// The Object description is not empty
		Assert.assertNotNull(desk.getDescription());

		// The Object supported interfaces is not empty
		Assert.assertNotNull(desk.getSupportedConnectionInterfaces());

		// The Components array is not empty
		Assert.assertNotNull(desk.getComponents());

		// The Components array is not empty
		Assert.assertNotNull(desk.getComponents()[0]);

		// The first component is a Motherboard component
		Assert.assertEquals(Motherboard.class, desk.getComponents()[0]
				.getClass());

		Motherboard mb = (Motherboard) desk.getComponents()[0];


		// The Motherboard serial is not empty
		Assert.assertNotNull(mb.getObjectSerial());

		// The Motherboard name is not empty
		Assert.assertNotNull(mb.getObjectName());

		// The Motherboard description is not empty
		Assert.assertNotNull(mb.getDescription());

		// The Motherboard supported interfaces is empty
		Assert.assertNull(mb.getSupportedConnectionInterfaces());
	}
}
