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
 * This exception this thrown when a IP range is attempted create with only one IP.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class RangeNotBigEnoughException extends Exception
{
	// The IP address
	String ip;



	/**
	 * A constructor with the single IP address as the given text string.
	 * 
	 * @param text
	 *            The single IP address.
	 */
	public RangeNotBigEnoughException(String text)
	{
		ip = text;
	}



	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "You can not have a IP range with only a single IP, such as the given ip("
				+ ip + ").";

		return output;
	}
}
