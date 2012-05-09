/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package jUnitTests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * JUnit Suite Test
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ ObjectCreationTests.class,
		ObjectSManipulationsTests.class, ArrayManagmentTests.class,
		CanvasManagementTests.class })
public class AllTests
{
}