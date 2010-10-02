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
package graphics.GUI.objectView.Network;


import graphics.PrimeMain;
import graphics.GUI.userGroups.PermissionsModel;
import groups.Group;
import groups.Permissions;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import logistical.cleanup;
import objects.Object;
import widgets.WorkareaCanvas;


/**
 * This class will create a JPanel that will contain a {@link JTable} with a
 * list of permissions for a given object in connection to the networks groups.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectPermissionsPanel extends JPanel implements ActionListener
{
	private JTable permTable;

	private WorkareaCanvas canvas;

	private Object object;

	private HashMap<String, Permissions> permissions = new HashMap<String, Permissions>();

	private JCheckBox readBox;

	private JCheckBox writeBox;

	private JCheckBox executeBox;

	private JCheckBox allRightsBox;


	/**
	 * This is a constructor for the class that takes the {@link Object} that
	 * will be checked against groups permissions.
	 */
	public ObjectPermissionsPanel(WorkareaCanvas canvas, Object object)
	{
		this.canvas = canvas;
		this.object = object;

		panelSetup();
	}


	private void panelSetup()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		// c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		this.add(getOverallPermissions(), c);



		c.weighty = 1.0; // request any extra vertical space
		c.gridy = 1; // row
		this.add(getDevicesPanel(), c);
	}


	private JPanel getOverallPermissions()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(2, 2, 2, 2); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		JLabel overallPermissions = new JLabel(
				PrimeMain.texts
						.getString("newGroupCustomPermissionsGeneralPermLabel"));
		buttonPanel.add(overallPermissions, c);


		readBox = new JCheckBox(
				PrimeMain.texts.getString("newGroupCustomPermissionsReadLabel"),
				false);
		readBox.addActionListener(this);
		c.anchor = GridBagConstraints.EAST; // location
		c.weightx = 0; // request any extra horizontal space
		c.gridx = 1; // column
		buttonPanel.add(readBox, c);


		writeBox = new JCheckBox(
				PrimeMain.texts
						.getString("newGroupCustomPermissionsWriteLabel"),
				false);
		writeBox.addActionListener(this);
		c.gridx = 2; // column
		buttonPanel.add(writeBox, c);


		executeBox = new JCheckBox(
				PrimeMain.texts
						.getString("newGroupCustomPermissionsExecuteLabel"),
				false);
		executeBox.addActionListener(this);
		c.gridx = 3; // column
		buttonPanel.add(executeBox, c);


		allRightsBox = new JCheckBox(
				PrimeMain.texts
						.getString("newGroupCustomPermissionsAllRightsLabel"),
				false);
		allRightsBox.addActionListener(this);
		c.gridx = 4; // column
		buttonPanel.add(allRightsBox, c);




		return buttonPanel;
	}



	private JPanel getDevicesPanel()
	{
		JPanel devicesPanel = new JPanel();
		devicesPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.WEST; // location
		c.insets = new Insets(2, 2, 2, 2); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		// The groups of the network(with possible null pointers)
		Group[] groups = canvas.getNetworkGroups().toArray(new Group[0]);

		// Cleans up the empty pointers in the arrays
		groups = cleanup.cleanObjectArray(groups);

		String[] columnNames = {
				PrimeMain.texts.getString("newGroupNameLabel"),
				PrimeMain.texts.getString("newGroupCustomPermissionsReadLabel"),
				PrimeMain.texts
						.getString("newGroupCustomPermissionsWriteLabel"),
				PrimeMain.texts
						.getString("newGroupCustomPermissionsExecuteLabel"),
				PrimeMain.texts
						.getString("newGroupCustomPermissionsAllRightsLabel") };

		// Gets the data about the permission for this object
		java.lang.Object[][] data = getObjectData(groups);


		// Sets up the JTable
		permTable = new JTable(new PermissionsModel(data, columnNames));
		permTable.getModel().addTableModelListener(
				new EditObjectModelSelectionListener(permTable, permissions));
		permTable
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);


		// Sets up the column sizes
		TableColumn column = null;
		for ( int i = 0; i < permTable.getModel().getColumnCount(); i++ )
		{
			column = permTable.getColumnModel().getColumn(i);
			if ( i == 0 )
			{
				column.setPreferredWidth(150); // first column is bigger
			}
		}


		permTable.setRowSelectionAllowed(false);
		permTable.setColumnSelectionAllowed(false);
		permTable.setCellSelectionEnabled(true);


		JScrollPane scrollArea = new JScrollPane(permTable);
		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scrollArea.getVerticalScrollBar().setUnitIncrement(10);

		devicesPanel.add(scrollArea, c);
		return devicesPanel;
	}




	private java.lang.Object[][] getObjectData(Group[] groups)
	{
		if ( groups != null && groups.length > 0 )
		{
			java.lang.Object[][] data = new java.lang.Object[groups.length][5];

			for ( int i = 0; i < groups.length; i++ )
			{
				if ( groups[i] != null )
				{
					// Gets all the permissions of the group
					HashMap<Long, Permissions> groupPerms = groups[i]
							.getDevicePermissions();

					/**
					 * Since there can only exist one permission in any given
					 * group with the serial number of this classes Object it is
					 * correct to find the first permission in the map with the
					 * same serial number.
					 */
					Permissions tempPerm = groupPerms.get(object
							.getObjectSerial());

					// If no permission was found in the group for this classes
					// object
					if ( tempPerm == null )
					{
						tempPerm = new Permissions(false, false, false);
					}

					boolean read = false;
					boolean write = false;
					boolean execute = false;

					read = tempPerm.hasReadAccess();
					write = tempPerm.hasWriteAccess();
					execute = tempPerm.hasExecuteAccess();


					boolean all = false;
					if ( read && write && execute )
					{
						all = true;
					}

					data[i][0] = groups[i].getGroupName();
					data[i][1] = new Boolean(read);
					data[i][2] = new Boolean(write);
					data[i][3] = new Boolean(execute);
					data[i][4] = new Boolean(all);


					// Places a clone of the permissions in the temporary map
					permissions.put(groups[i].getGroupName(), tempPerm.clone());
				}
			}

			return data;
		}


		// No objects in the given array
		return new java.lang.Object[0][5];
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int read = 1;
		int write = 2;
		int execute = 3;


		if ( e.getSource().equals(readBox) )
		{
			PermissionsModel tableModel = (PermissionsModel) permTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditObjectModelSelectionListener tableLis = (EditObjectModelSelectionListener) ((AbstractTableModel) permTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < tableModel.getRowCount(); i++ )
			{
				tableModel.selectRead(i, readBox.isSelected());

				// Sets the READ permission
				tableLis.addObjectPermissions(
						(String) permTable.getValueAt(i, 0),
						readBox.isSelected(), read);
			}

			permTable.repaint();
		}
		else if ( e.getSource().equals(writeBox) )
		{
			PermissionsModel tableModel = (PermissionsModel) permTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditObjectModelSelectionListener tableLis = (EditObjectModelSelectionListener) ((AbstractTableModel) permTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < tableModel.getRowCount(); i++ )
			{
				tableModel.selectWrite(i, writeBox.isSelected());

				// Sets the WRITE permission
				tableLis.addObjectPermissions(
						(String) permTable.getValueAt(i, 0),
						writeBox.isSelected(), write);
			}

			permTable.repaint();
		}
		else if ( e.getSource().equals(executeBox) )
		{
			PermissionsModel tableModel = (PermissionsModel) permTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditObjectModelSelectionListener tableLis = (EditObjectModelSelectionListener) ((AbstractTableModel) permTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < tableModel.getRowCount(); i++ )
			{
				tableModel.selectExecute(i, executeBox.isSelected());

				// Sets the EXECUTE permission
				tableLis.addObjectPermissions(
						(String) permTable.getValueAt(i, 0),
						executeBox.isSelected(), execute);
			}

			permTable.repaint();
		}
		else if ( e.getSource().equals(allRightsBox) )
		{
			// CLIENTS
			PermissionsModel tableModel = (PermissionsModel) permTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditObjectModelSelectionListener tableLis = (EditObjectModelSelectionListener) ((AbstractTableModel) permTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < tableModel.getRowCount(); i++ )
			{
				tableModel.selectAll(i, allRightsBox.isSelected());

				// Sets the READ permission
				tableLis.addObjectPermissions(
						(String) permTable.getValueAt(i, 0),
						allRightsBox.isSelected(), read);

				// Sets the WRITE permission
				tableLis.addObjectPermissions(
						(String) permTable.getValueAt(i, 0),
						allRightsBox.isSelected(), write);

				// Sets the EXECUTE permission
				tableLis.addObjectPermissions(
						(String) permTable.getValueAt(i, 0),
						allRightsBox.isSelected(), execute);
			}

			readBox.setSelected(allRightsBox.isSelected());
			writeBox.setSelected(allRightsBox.isSelected());
			executeBox.setSelected(allRightsBox.isSelected());


			permTable.repaint();
		}
	}


	/**
	 * This function save the permissions from the temporary permissions map to
	 * the correct {@link Group} permission.
	 */
	public void saveObjectPermissions()
	{
		// The groups of the network(with possible null pointers)
		Group[] groups = canvas.getNetworkGroups().toArray(new Group[0]);

		// Cleans up the empty pointers in the arrays
		groups = cleanup.cleanObjectArray(groups);


		PermissionsModel tableModel = (PermissionsModel) permTable.getModel();


		for ( int i = 0; i < tableModel.getRowCount(); i++ )
		{
			// Gets the name of the group
			String groupName = (String) permTable.getValueAt(i, 0);

			if ( groupName != null )
			{
				// Gets the group permissions from the map of permissions
				Permissions groupNewPermission = permissions.get(groupName);

				if ( groupNewPermission != null )
				{
					// Goes through the
					for ( int j = 0; j < groups.length; j++ )
					{
						// If the name of the array group is the same as
						// group name
						if ( groups[j].getGroupName().equals(groupName) )
						{
							// Sets the new permissions
							groups[j].getDevicePermissions().put(
									object.getObjectSerial(),
									groupNewPermission);
						}
					}
				}
			}
		}

		permTable.repaint();
	}
}
