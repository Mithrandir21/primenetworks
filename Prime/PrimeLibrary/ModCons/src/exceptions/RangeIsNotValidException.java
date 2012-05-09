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
 * RangeIsNotValidException exception will be thrown when two IP address does not make a valid IP rang. (This exception
 * should be used when trying to validate and verify IP ranges.)
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class RangeIsNotValidException extends Exception
{
	// The two different IPs
	String from = null;

	String to = null;



	/**
	 * Constructs a new exception with parameters for two {@link Object Objects} . The two parameters will be the two
	 * different IP addresses that can create a valid IP range.s
	 * 
	 * @param from
	 *            Start IP address.
	 * @param to
	 *            End IP address.
	 */
	public RangeIsNotValidException(String from, String to)
	{
		this.from = from;
		this.to = to;
	}



	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "There can not be created a valid IP range between these two IP address, "
				+ this.from + " and " + this.to + ".";

		return output;
	}
}
