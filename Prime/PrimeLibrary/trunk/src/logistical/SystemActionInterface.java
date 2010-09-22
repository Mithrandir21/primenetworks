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
package logistical;


import java.awt.event.ActionEvent;


/**
 * This interface is meant to be used together with a an {@link ActionEvent} to
 * allow for a {@link Boolean} to be passed along
 * with the performAction method. A boolean determining if the action should be
 * undoable.
 * 
 * @author Bahram Malaekeh
 */
public interface SystemActionInterface
{
	/**
	 * Method that will perform the classes action, with a boolean saying
	 * whether or not the action should be set as undoable.
	 */
	void performAction(boolean undoable);
}
