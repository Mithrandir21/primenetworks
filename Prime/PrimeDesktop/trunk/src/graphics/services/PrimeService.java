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
package graphics.services;


/**
 * The class contains all the service threads this system will be running. All services can be stopped and started, all
 * together or gotten and stop separately.
 * 
 * @author Bahram Malaekeh
 */
public class PrimeService
{
	/**
	 * 
	 */
	private CanvasService canvasService;


	/**
	 * Constructor for this class that creates new instances of the threads in this class.
	 */
	public PrimeService()
	{
		canvasService = new CanvasService();
	}



	/**
	 * Calls the Run function for the this classes threads.
	 */
	public synchronized void startCanvasService()
	{
		canvasService.run();
	}


	/**
	 * Calls the ServiceSuspend function for the this classes threads.
	 */
	public synchronized void stopCanvasService()
	{
		canvasService.serviceSuspend();
	}




	/**
	 * Calls the ServiceSuspend function for the all this classes threads.
	 */
	public void stopAll()
	{
		canvasService.serviceSuspend();
	}



	// GETTERS


	/**
	 * Gets the {@link CanvasService} thread.
	 */
	public CanvasService getCanvasService()
	{
		return canvasService;
	}

}
