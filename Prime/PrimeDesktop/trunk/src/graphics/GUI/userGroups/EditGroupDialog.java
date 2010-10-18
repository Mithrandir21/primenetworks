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


import graphics.PrimeMain;
import groups.Group;
import groups.Permissions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import logistical.checkLogic;
import logistical.cleanup;
import managment.GroupManagment;
import objects.Clients;
import objects.ExternalHardware;
import objects.Object;
import objects.Servers;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class EditGroupDialog extends JDialog implements ActionListener
{
	private WorkareaCanvas canvas;

	private Group group;

	private JTextField nameField;

	private JTextArea descArea;

	private JTable clientJTable;

	private JTable serverJTable;

	private JTable externalHardwareJTable;

	private HashMap<Long, Permissions> permissions;

	private JCheckBox readBox;

	private JCheckBox writeBox;

	private JCheckBox executeBox;

	private JCheckBox allRightsBox;



	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public EditGroupDialog(WorkareaCanvas canvas, Group group)
	{
		this.setTitle("Edit Group" + " - " + group.getGroupName());


		this.group = group;
		permissions = GroupManagment.deepCopyPermissionsMap(group
				.getDevicePermissions());
		this.canvas = canvas;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(510, 650);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		this.setPreferredSize(size);


		panelSetup();
		// loadDevicePermissionsIntoTables();

		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);
		this.setResizable(false);
	}


	/**
	 * TODO - Description
	 * 
	 */
	private void panelSetup()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(5, 5, 5, 5); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		mainPanel.add(getNameDescPanel(), c);

		JScrollPane scroll = new JScrollPane(setupCustomPermissions());
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		c.weighty = 1.0; // request any extra vertical space
		c.gridy = 1; // row
		mainPanel.add(scroll, c);



		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.SOUTHEAST; // location
		c.weighty = 0; // request any extra vertical space
		c.gridy = 2; // row
		mainPanel.add(getButtonPanel(), c);


		this.add(mainPanel);

		// Resets the NetworkGroupsDialog object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				// Removes the pointer to this object in the system registry.
				PrimeMain.groupsDialog.editDialog = null;
			}
		});
	}


	private JPanel getNameDescPanel()
	{
		nameField = new JTextField();
		nameField.setText(group.getGroupName());
		descArea = new JTextArea(3, 1);
		descArea.setText(group.getGroupDescription());


		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(5, 5, 5, 5); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		// The group name label
		JLabel nameLabel = new JLabel(
				PrimeMain.texts.getString("newGroupNameLabel"));
		panel.add(nameLabel, c);


		c.gridx = 1; // column
		panel.add(nameField, c);


		// The description name label
		JLabel descLabel = new JLabel(
				PrimeMain.texts.getString("newGroupDescLabel"));
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(descLabel, c);


		JScrollPane scroll = new JScrollPane(descArea);


		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 1; // column
		panel.add(scroll, c);


		return panel;
	}

	private JPanel setupCustomPermissions()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		panel.add(getOverallPermissions(), c);



		c.weighty = 1.0; // request any extra vertical space
		c.gridy = 1; // row
		panel.add(getDevicesPanel(), c);


		return panel;
	}





	private JPanel getOverallPermissions()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.WEST; // location
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



		// Gets all the objects in the Network(on the scene).
		Object[] objects = canvas.getObjectsOnTheScene();


		Object[] clientObjects = new Object[objects.length];
		Object[] serverObjects = new Object[objects.length];
		Object[] externalDevicesObjects = new Object[objects.length];


		for ( int i = 0; i < objects.length; i++ )
		{
			if ( objects[i] != null )
			{
				if ( objects[i] instanceof Clients )
				{
					clientObjects[i] = objects[i];
				}
				else if ( objects[i] instanceof Servers )
				{
					serverObjects[i] = objects[i];
				}
				else if ( objects[i] instanceof ExternalHardware )
				{
					externalDevicesObjects[i] = objects[i];
				}
			}
		}


		// Cleans up the empty pointers in the arrays
		clientObjects = cleanup.cleanObjectArray(clientObjects);
		serverObjects = cleanup.cleanObjectArray(serverObjects);
		externalDevicesObjects = cleanup
				.cleanObjectArray(externalDevicesObjects);


		String[] columnNames = {
				PrimeMain.texts
						.getString("newGroupCustomPermissionsDeviceNameLabel"),
				PrimeMain.texts.getString("newGroupCustomPermissionsReadLabel"),
				PrimeMain.texts
						.getString("newGroupCustomPermissionsWriteLabel"),
				PrimeMain.texts
						.getString("newGroupCustomPermissionsExecuteLabel"),
				PrimeMain.texts
						.getString("newGroupCustomPermissionsAllRightsLabel") };

		java.lang.Object[][] clientData = getObjectData(clientObjects);
		java.lang.Object[][] serverData = getObjectData(serverObjects);
		java.lang.Object[][] externalDevicesData = getObjectData(externalDevicesObjects);


		clientJTable = new JTable(new PermissionsModel(clientData, columnNames));
		clientJTable.setName("clientTable");
		clientJTable.getModel().addTableModelListener(
				new EditGroupModelSelectionListener(clientJTable, canvas,
						permissions));
		clientJTable
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		serverJTable = new JTable(new PermissionsModel(serverData, columnNames));
		serverJTable.setName("serverTable");
		serverJTable.getModel().addTableModelListener(
				new EditGroupModelSelectionListener(serverJTable, canvas,
						permissions));
		serverJTable
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		externalHardwareJTable = new JTable(new PermissionsModel(
				externalDevicesData, columnNames));
		externalHardwareJTable.setName("externalHardwareTable");
		externalHardwareJTable.getModel().addTableModelListener(
				new EditGroupModelSelectionListener(externalHardwareJTable,
						canvas, permissions));
		externalHardwareJTable
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);



		JXTaskPaneContainer tpc = new JXTaskPaneContainer();

		// Adds the group panel to the collapsible container
		tpc.add(getObjectGroup(PrimeMain.texts.getString("clients"), true,
				clientJTable));

		// Adds the group panel to the collapsible container
		tpc.add(getObjectGroup(PrimeMain.texts.getString("servers"), false,
				serverJTable));

		// Adds the group panel to the collapsible container
		tpc.add(getObjectGroup(PrimeMain.texts.getString("externalHardware"),
				false, externalHardwareJTable));


		devicesPanel.add(tpc, c);


		return devicesPanel;
	}




	private JPanel getButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.EAST; // location
		c.insets = new Insets(2, 2, 2, 2); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		JButton addButton = new JButton(PrimeMain.texts.getString("save"));
		addButton.addActionListener(this);
		addButton.setActionCommand("save");
		buttonPanel.add(addButton, c);


		JButton removeButton = new JButton(PrimeMain.texts.getString("cancel"));
		removeButton.addActionListener(this);
		removeButton.setActionCommand("cancel");
		c.weighty = 1.0; // request any extra vertical space
		c.gridx = 1; // row
		buttonPanel.add(removeButton, c);

		return buttonPanel;
	}




	/**
	 * This function returns a {@link JXTaskPane} that will contain a
	 * {@link JTable} with data about a certain type of {@link Object} on the
	 * classes {@link WorkareaCanvas}.
	 */
	private JXTaskPane getObjectGroup(String groupName, boolean expanded,
			JTable table)
	{
		JXTaskPane group = new JXTaskPane();
		group.setTitle(groupName);
		group.setSpecial(true);
		group.setCollapsed(!expanded);


		JPanel panel = new JPanel(new BorderLayout());

		// Sets up the column sizes
		TableColumn column = null;
		for ( int i = 0; i < table.getModel().getColumnCount(); i++ )
		{
			column = table.getColumnModel().getColumn(i);
			if ( i == 0 )
			{
				column.setPreferredWidth(120); // first column is bigger
			}
			else
			{
				column.setPreferredWidth(65);
			}
		}


		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(true);

		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.SOUTH);



		group.add(panel);
		return group;
	}

	private java.lang.Object[][] getObjectData(Object[] objects)
	{
		if ( objects != null && objects.length > 0 )
		{
			java.lang.Object[][] data = new java.lang.Object[objects.length][5];

			for ( int i = 0; i < objects.length; i++ )
			{
				if ( objects[i] != null )
				{
					Permissions tempPerm = permissions.get(objects[i]
							.getObjectSerial());

					boolean read = false;
					boolean write = false;
					boolean execute = false;

					// Sets the booleans if the permission exists.
					if ( tempPerm != null )
					{
						read = tempPerm.hasReadAccess();
						write = tempPerm.hasWriteAccess();
						execute = tempPerm.hasExecuteAccess();
					}


					boolean all = false;
					if ( read && write && execute )
					{
						all = true;
					}


					data[i][0] = objects[i].getObjectName();
					data[i][1] = new Boolean(read);
					data[i][2] = new Boolean(write);
					data[i][3] = new Boolean(execute);
					data[i][4] = new Boolean(all);
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
			// CLIENTS
			PermissionsModel clientModel = (PermissionsModel) clientJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener clientLis = (EditGroupModelSelectionListener) ((AbstractTableModel) clientJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < clientModel.getRowCount(); i++ )
			{
				clientModel.selectRead(i, readBox.isSelected());

				// Sets the READ permission
				clientLis.addObjectPermissions(
						(String) clientJTable.getValueAt(i, 0),
						readBox.isSelected(), read);
			}

			// SERVER
			PermissionsModel serverModel = (PermissionsModel) serverJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener serverLis = (EditGroupModelSelectionListener) ((AbstractTableModel) serverJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < serverModel.getRowCount(); i++ )
			{
				serverModel.selectRead(i, readBox.isSelected());

				// Sets the READ permission
				serverLis.addObjectPermissions(
						(String) serverJTable.getValueAt(i, 0),
						readBox.isSelected(), read);
			}

			// CLIENTS
			PermissionsModel externalHardwareModel = (PermissionsModel) externalHardwareJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener extHardLis = (EditGroupModelSelectionListener) ((AbstractTableModel) externalHardwareJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < externalHardwareModel.getRowCount(); i++ )
			{
				externalHardwareModel.selectRead(i, readBox.isSelected());

				// Sets the READ permission
				extHardLis.addObjectPermissions(
						(String) externalHardwareJTable.getValueAt(i, 0),
						readBox.isSelected(), read);
			}

			clientJTable.repaint();
			serverJTable.repaint();
			externalHardwareJTable.repaint();
		}
		else if ( e.getSource().equals(writeBox) )
		{
			// CLIENTS
			PermissionsModel clientModel = (PermissionsModel) clientJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener clientLis = (EditGroupModelSelectionListener) ((AbstractTableModel) clientJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < clientModel.getRowCount(); i++ )
			{
				clientModel.selectWrite(i, writeBox.isSelected());

				// Sets the WRITE permission
				clientLis.addObjectPermissions(
						(String) clientJTable.getValueAt(i, 0),
						writeBox.isSelected(), write);
			}

			// SERVER
			PermissionsModel serverModel = (PermissionsModel) serverJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener serverLis = (EditGroupModelSelectionListener) ((AbstractTableModel) serverJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < serverModel.getRowCount(); i++ )
			{
				serverModel.selectWrite(i, writeBox.isSelected());

				// Sets the WRITE permission
				serverLis.addObjectPermissions(
						(String) serverJTable.getValueAt(i, 0),
						writeBox.isSelected(), write);
			}

			// CLIENTS
			PermissionsModel externalHardwareModel = (PermissionsModel) externalHardwareJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener extHardLis = (EditGroupModelSelectionListener) ((AbstractTableModel) externalHardwareJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < externalHardwareModel.getRowCount(); i++ )
			{
				externalHardwareModel.selectWrite(i, writeBox.isSelected());

				// Sets the WRITE permission
				extHardLis.addObjectPermissions(
						(String) externalHardwareJTable.getValueAt(i, 0),
						writeBox.isSelected(), write);
			}

			clientJTable.repaint();
			serverJTable.repaint();
			externalHardwareJTable.repaint();
		}
		else if ( e.getSource().equals(executeBox) )
		{
			// CLIENTS
			PermissionsModel clientModel = (PermissionsModel) clientJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener clientLis = (EditGroupModelSelectionListener) ((AbstractTableModel) clientJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < clientModel.getRowCount(); i++ )
			{
				clientModel.selectExecute(i, executeBox.isSelected());

				// Sets the EXECUTE permission
				clientLis.addObjectPermissions(
						(String) clientJTable.getValueAt(i, 0),
						executeBox.isSelected(), execute);
			}

			// SERVER
			PermissionsModel serverModel = (PermissionsModel) serverJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener serverLis = (EditGroupModelSelectionListener) ((AbstractTableModel) serverJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < serverModel.getRowCount(); i++ )
			{
				serverModel.selectExecute(i, executeBox.isSelected());

				// Sets the EXECUTE permission
				serverLis.addObjectPermissions(
						(String) serverJTable.getValueAt(i, 0),
						executeBox.isSelected(), execute);
			}

			// EXTERNAL HARDWARE
			PermissionsModel externalHardwareModel = (PermissionsModel) externalHardwareJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener extHardLis = (EditGroupModelSelectionListener) ((AbstractTableModel) externalHardwareJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < externalHardwareModel.getRowCount(); i++ )
			{
				externalHardwareModel.selectExecute(i, executeBox.isSelected());

				// Sets the EXECUTE permission
				extHardLis.addObjectPermissions(
						(String) externalHardwareJTable.getValueAt(i, 0),
						executeBox.isSelected(), execute);
			}

			clientJTable.repaint();
			serverJTable.repaint();
			externalHardwareJTable.repaint();
		}
		else if ( e.getSource().equals(allRightsBox) )
		{
			// CLIENTS
			PermissionsModel clientModel = (PermissionsModel) clientJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener clientLis = (EditGroupModelSelectionListener) ((AbstractTableModel) clientJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < clientModel.getRowCount(); i++ )
			{
				clientModel.selectAll(i, allRightsBox.isSelected());

				// Sets the READ permission
				clientLis.addObjectPermissions(
						(String) clientJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), read);

				// Sets the WRITE permission
				clientLis.addObjectPermissions(
						(String) clientJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), write);

				// Sets the EXECUTE permission
				clientLis.addObjectPermissions(
						(String) clientJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), execute);
			}

			// SERVER
			PermissionsModel serverModel = (PermissionsModel) serverJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener serverLis = (EditGroupModelSelectionListener) ((AbstractTableModel) serverJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < serverModel.getRowCount(); i++ )
			{
				serverModel.selectAll(i, allRightsBox.isSelected());

				// Sets the READ permission
				serverLis.addObjectPermissions(
						(String) serverJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), read);

				// Sets the WRITE permission
				serverLis.addObjectPermissions(
						(String) serverJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), write);

				// Sets the EXECUTE permission
				serverLis.addObjectPermissions(
						(String) serverJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), execute);
			}

			// CLIENTS
			PermissionsModel externalHardwareModel = (PermissionsModel) externalHardwareJTable
					.getModel();

			/**
			 * Gets the ModelSelectionListener that contains the
			 * addObjectPermissions function.
			 */
			EditGroupModelSelectionListener extHardlis = (EditGroupModelSelectionListener) ((AbstractTableModel) externalHardwareJTable
					.getModel()).getTableModelListeners()[0];

			for ( int i = 0; i < externalHardwareModel.getRowCount(); i++ )
			{
				externalHardwareModel.selectAll(i, allRightsBox.isSelected());

				// Sets the READ permission
				extHardlis.addObjectPermissions(
						(String) externalHardwareJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), read);

				// Sets the WRITE permission
				extHardlis.addObjectPermissions(
						(String) externalHardwareJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), write);

				// Sets the EXECUTE permission
				extHardlis.addObjectPermissions(
						(String) externalHardwareJTable.getValueAt(i, 0),
						allRightsBox.isSelected(), execute);
			}

			readBox.setSelected(allRightsBox.isSelected());
			writeBox.setSelected(allRightsBox.isSelected());
			executeBox.setSelected(allRightsBox.isSelected());


			clientJTable.repaint();
			serverJTable.repaint();
			externalHardwareJTable.repaint();
		}
		else if ( e.getActionCommand().equals("save") )
		{
			if ( group != null && permissions != null )
			{
				boolean dataValid = false;

				String newGroupName = nameField.getText();

				// If the name is not same as the group name
				if ( !newGroupName.equals(group.getGroupName()) )
				{
					// If the name is not empty and is a valid name
					if ( !newGroupName.equals("") )
					{
						// If the new name is valid
						if ( checkLogic.validateName(newGroupName) )
						{
							// If a group does not already exists with the same
							// name
							if ( !GroupManagment.groupNameExistsInNetwork(
									canvas, newGroupName) )
							{
								// At this point the name is valid and unique
								dataValid = true;
							}
							else
							{
								JOptionPane
										.showMessageDialog(
												null,
												PrimeMain.texts
														.getString("groupAlreadExistsWithNameMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								dataValid = false;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, PrimeMain.texts
									.getString("groupNameNotValidMsg"),
									PrimeMain.texts.getString("error"),
									JOptionPane.ERROR_MESSAGE);

							dataValid = false;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("groupNameIsEmptyMsg"),
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);

						dataValid = false;
					}
				}
				else
				{
					// The name has not changed
					dataValid = true;
				}


				if ( dataValid )
				{
					// Sets the name
					group.setGroupName(newGroupName);

					// Sets the description
					group.setGroupDescription(descArea.getText());

					// Sets the groups permissions
					group.setDevicePermissions(permissions);

					// Sets the current as changed(for the save-on-exit
					// feature).
					canvas.setChanged(true);

					PrimeMain.groupsDialog.editDialog = null;

					this.dispose();
				}
			}
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			PrimeMain.groupsDialog.editDialog = null;

			this.dispose();
		}

	}
}
