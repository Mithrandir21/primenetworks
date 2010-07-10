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
package exceptions;


/**
 * ConnectionDoesNotExist exception will be thrown when a connection is searched for between two objects that do not
 * have a connection between them.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ConnectionDoesNotExist extends Exception
{
	// Container for names of the two objects that have no connection between
	// them
	String a = null;

	String b = null;



	/**
	 * Constructs a new exception with parameters for two {@link Object Objects} . The two parameters will be the two
	 * objects that should, but do not, have a connection between them.
	 * 
	 * @param a
	 *            An {@link Object Objects} in the connection.
	 * @param b
	 *            An {@link Object Objects} in the connection.
	 */
	public ConnectionDoesNotExist(String a, String b)
	{
		// Sets the names of the objects that have connection between the
		this.a = a;
		this.b = b;
	}




	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "There exists no connection between object a, " + a
				+ " and object b, " + b + ".";

		return output;
	}

}
