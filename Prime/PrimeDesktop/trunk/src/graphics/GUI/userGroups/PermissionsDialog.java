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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import logistical.cleanup;
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
public class PermissionsDialog extends JDialog implements ActionListener
{
	private WorkareaCanvas canvas;

	private JTable groupTable;


	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public PermissionsDialog(WorkareaCanvas canvas)
	{
		this.setTitle("Permissions");

		this.canvas = canvas;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(550, 600);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		this.setPreferredSize(size);


		panelSetup();


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
		c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		mainPanel.add(getOverallPermissions(), c);



		c.weighty = 1.0; // request any extra vertical space
		c.gridy = 1; // row
		mainPanel.add(getDevicesPanel(), c);


		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST; // location
		c.weighty = 0; // request any extra vertical space
		c.gridy = 2; // row
		mainPanel.add(getButtonPanel(), c);


		this.add(mainPanel);
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


		JLabel overallPermissions = new JLabel("General Permissions");
		buttonPanel.add(overallPermissions, c);


		ButtonGroup buttongroup = new ButtonGroup();
		JRadioButton readBox = new JRadioButton("Read", false);
		readBox.addActionListener(this);
		c.anchor = GridBagConstraints.EAST; // location
		c.weightx = 0; // request any extra horizontal space
		c.gridx = 1; // column
		buttongroup.add(readBox);
		buttonPanel.add(readBox, c);


		JRadioButton writeBox = new JRadioButton("Write", false);
		writeBox.addActionListener(this);
		c.gridx = 2; // column
		buttongroup.add(writeBox);
		buttonPanel.add(writeBox, c);


		JRadioButton executeBox = new JRadioButton("Execute", false);
		executeBox.addActionListener(this);
		c.gridx = 3; // column
		buttongroup.add(executeBox);
		buttonPanel.add(executeBox, c);


		JRadioButton allBox = new JRadioButton("All Rights", false);
		allBox.addActionListener(this);
		c.gridx = 4; // column
		buttongroup.add(allBox);
		buttonPanel.add(allBox, c);




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


		Object[] desktopObjects = new Object[objects.length];
		Object[] serverObjects = new Object[objects.length];
		Object[] externalDevicesObjects = new Object[objects.length];


		for ( int i = 0; i < objects.length; i++ )
		{
			if ( objects[i] != null )
			{
				if ( objects[i] instanceof Clients )
				{
					desktopObjects[i] = objects[i];
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
		desktopObjects = cleanup.cleanObjectArray(desktopObjects);
		serverObjects = cleanup.cleanObjectArray(serverObjects);
		externalDevicesObjects = cleanup
				.cleanObjectArray(externalDevicesObjects);



		JXTaskPaneContainer tpc = new JXTaskPaneContainer();

		// Adds the group panel to the collapsible container
		tpc.add(getObjectGroup("Clients", true, desktopObjects));

		// Adds the group panel to the collapsible container
		tpc.add(getObjectGroup("Servers", true, serverObjects));

		// Adds the group panel to the collapsible container
		tpc.add(getObjectGroup("Peripherals", true, externalDevicesObjects));


		JScrollPane scrollArea = new JScrollPane(tpc);
		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scrollArea.getVerticalScrollBar().setUnitIncrement(30);


		// Adds the container to this panel
		devicesPanel.add(scrollArea, c);


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
	 * TODO - Description
	 */
	private JXTaskPane getObjectGroup(String groupName, boolean expanded,
			Object[] objects)
	{
		JXTaskPane group = new JXTaskPane();
		group.setTitle(groupName);
		group.setSpecial(true);
		group.setCollapsed(!expanded);


		JPanel panel = new JPanel(new BorderLayout());

		String[] columnNames = { "Device Name", "Read", "Write", "Execute",
				"All Rights" };

		java.lang.Object[][] data = getObjectData(objects);


		MyTableModel model = new MyTableModel(columnNames, data);
		groupTable = new JTable(model);
		groupTable.setRowSelectionAllowed(false);
		groupTable.setColumnSelectionAllowed(false);
		groupTable.setCellSelectionEnabled(false);

		panel.add(groupTable.getTableHeader(), BorderLayout.NORTH);
		panel.add(groupTable, BorderLayout.SOUTH);



		group.add(panel);
		return group;
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
		// TODO Auto-generated method stub

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
					data[i][0] = objects[i].getObjectName();
					data[i][1] = new Boolean(false);
					data[i][2] = new Boolean(false);
					data[i][3] = new Boolean(false);
					data[i][4] = new Boolean(false);
				}
			}


			return data;
		}


		// No objects in the given array
		return new java.lang.Object[0][5];
	}



	class MyTableModel extends AbstractTableModel
	{

		private String[] columnNames;

		private java.lang.Object[][] data;


		/**
		 * TODO - Description NEEDED!
		 * 
		 */
		public MyTableModel(String[] columnNames, java.lang.Object[][] data)
		{
			this.columnNames = columnNames;
			this.data = data;
		}

		public int getColumnCount()
		{
			return columnNames.length;
		}

		public int getRowCount()
		{
			return data.length;
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public java.lang.Object getValueAt(int row, int col)
		{
			return data[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/
		 * editor for each cell. If we didn't implement this method,
		 * then the last column would contain text ("true"/"false"),
		 * rather than a check box.
		 */
		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}


		/*
		 * Don't need to implement this method unless your table's
		 * editable.
		 */
		public boolean isCellEditable(int row, int col)
		{
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if ( col < 1 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's
		 * data can change.
		 */
		public void setValueAt(Object value, int row, int col)
		{
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

	}









}
