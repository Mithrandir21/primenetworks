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
package graphics.GUI.userGroups;


import groups.Permissions;

import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import managment.CanvasManagment;
import objects.Object;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ModelSelectionListener implements TableModelListener
{

	private JTable table;

	private WorkareaCanvas canvas;

	private HashMap<Long, Permissions> permissions;

	// It is necessary to keep the table since it is not possible
	// to determine the table from the event's source
	ModelSelectionListener(JTable table, WorkareaCanvas canvas,
			HashMap<Long, Permissions> permissions)
	{
		this.table = table;
		this.canvas = canvas;
		this.permissions = permissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.
	 * TableModelEvent)
	 */
	@Override
	public void tableChanged(TableModelEvent e)
	{
		// All rights
		if ( table.getSelectedColumn() == 4 )
		{
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			boolean allRights = (Boolean) table.getValueAt(row, column);
			PermissionsModel model = (PermissionsModel) table.getModel();
			model.selectAll(row, allRights);
			table.repaint();
			addObjectPermissions((String) table.getValueAt(row, 0), allRights,
					1);
			addObjectPermissions((String) table.getValueAt(row, 0), allRights,
					2);
			addObjectPermissions((String) table.getValueAt(row, 0), allRights,
					3);
		}
		// Other rights
		else if ( table.getSelectedColumn() > 0
				&& table.getSelectedColumn() < 4 )
		{
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			addObjectPermissions((String) table.getValueAt(row, 0),
					(Boolean) table.getValueAt(row, column), column);
		}
	}


	/**
	 * TODO - Description
	 * 
	 */
	public void addObjectPermissions(String objectName, boolean right,
			int column)
	{
		if ( objectName != null && column > 0 && column < 4 )
		{
			WidgetObject widObj = CanvasManagment.findWidgetObjectByObjectName(
					objectName, canvas);
			if ( widObj != null )
			{
				Object obj = widObj.getObject();

				// Gets the permission from the map
				Permissions perm = permissions.get(obj.getObjectSerial());

				if ( perm != null )
				{
					// Update read access
					if ( column == 1 )
					{
						perm.setReadAccess(right);
					}
					// Update write access
					else if ( column == 2 )
					{
						perm.setWriteAccess(right);
					}
					// Update execute access
					else if ( column == 3 )
					{
						perm.setExecuteAccess(right);
					}

					// Puts the new permissions
					permissions.put(obj.getObjectSerial(), perm);
				}
				// There was no permission for the selected device
				else
				{
					// Creates a new Permission
					Permissions newPerm = new Permissions(false, false, false);

					// Update read access
					if ( column == 1 )
					{
						newPerm.setReadAccess(right);
					}
					// Update write access
					else if ( column == 2 )
					{
						newPerm.setWriteAccess(right);
					}
					// Update execute access
					else if ( column == 3 )
					{
						newPerm.setExecuteAccess(right);
					}

					// Puts the new permissions
					permissions.put(obj.getObjectSerial(), newPerm);
				}
			}
		}
	}
}
