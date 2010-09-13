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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import logistical.checkLogic;
import managment.GroupManagment;

import org.jdesktop.swingx.JXCollapsiblePane;

import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NewNetworkGroupDialog extends JDialog implements ActionListener
{
	private WorkareaCanvas canvas;

	private JTextField nameField;

	private JTextArea descArea;

	private JCheckBox permissionsBox;

	private JTable groupTable;


	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public NewNetworkGroupDialog(WorkareaCanvas canvas)
	{
		this.setTitle(PrimeMain.texts.getString("newGroupDialogTitleLabel"));

		this.canvas = canvas;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(400, 440);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		this.setPreferredSize(size);


		panelSetup();


		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);
		this.setResizable(false);
	}


	private void panelSetup()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());


		mainPanel.add(getNameDescPanel(), BorderLayout.NORTH);

		mainPanel.add(getCopyPermissionsPanel(), BorderLayout.SOUTH);


		this.add(mainPanel);
	}



	private JPanel getNameDescPanel()
	{
		nameField = new JTextField();
		descArea = new JTextArea(3, 1);


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




	private JPanel getCopyPermissionsPanel()
	{
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


		// The collapsible pane that will contain the
		JXCollapsiblePane collapsiblePane = new JXCollapsiblePane();
		collapsiblePane.setCollapsed(true);

		permissionsBox = new JCheckBox("Copy Permissions From Open Networks");
		permissionsBox.addActionListener(collapsiblePane.getActionMap().get(
				JXCollapsiblePane.TOGGLE_ACTION));

		panel.add(permissionsBox, c);



		JScrollPane scroll = new JScrollPane(getGroupCopyTable());
		scroll.setPreferredSize(new Dimension(scroll.getWidth(), 200));
		collapsiblePane.add(scroll);


		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.gridy = 1; // row
		panel.add(collapsiblePane, c);


		JButton setupPermissions = new JButton("Setup Permissions");
		setupPermissions.addActionListener(this);
		setupPermissions.setActionCommand("permSetup");
		c.weighty = 0; // request any extra vertical space
		c.gridy = 2; // row
		panel.add(setupPermissions, c);

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST; // location
		c.gridy = 3; // row
		panel.add(getButtonPanel(), c);

		return panel;
	}


	private JTable getGroupCopyTable()
	{
		String[] columnNames = { "Select", "Group Name" };

		String[][] data = GroupManagment
				.getGroupNamesWithNetworkName(PrimeMain.canvases);


		groupTable = new JTable(data, columnNames);
		groupTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		groupTable.setFillsViewportHeight(true);


		return groupTable;
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

		JButton addButton = new JButton(PrimeMain.texts.getString("new"));
		addButton.addActionListener(this);
		addButton.setActionCommand("new");
		buttonPanel.add(addButton, c);


		JButton removeButton = new JButton(PrimeMain.texts.getString("cancel"));
		removeButton.addActionListener(this);
		removeButton.setActionCommand("cancel");
		c.weighty = 1.0; // request any extra vertical space
		c.gridx = 1; // row
		buttonPanel.add(removeButton, c);

		return buttonPanel;
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
		if ( e.getActionCommand().equals("new") )
		{
			String groupName = nameField.getText();

			// If the field is not empty
			if ( !groupName.equals("") )
			{
				// If the group name is valid
				if ( checkLogic.validateName(groupName) )
				{
					// If the group does not already exists
					if ( !GroupManagment.groupNameExistsInNetwork(canvas,
							groupName) )
					{
						/**
						 * At this point in the function the name given has been
						 * validated and there exists no other group with the
						 * same name.
						 */
						// Copy Permission group
						Group permGroup = null;

						// If the check box is selected, the user wants to copy
						// the permissions of another group.
						if ( permissionsBox.isSelected() )
						{
							int row = groupTable.getSelectedRow();

							// If any row is selected
							if ( row > -1 )
							{
								String copyGroupName = (String) groupTable
										.getModel().getValueAt(row, 0);

								String canvasName = (String) groupTable
										.getModel().getValueAt(row, 1);

								permGroup = GroupManagment.getGroupWithName(
										canvasName, PrimeMain.canvases,
										copyGroupName);
							}
						}

						// If any group was found
						if ( permGroup != null )
						{
							GroupManagment.createNewGroup(canvas, groupName,
									descArea.getText(),
									permGroup.getDevicePermissions());
						}
						else
						{
							GroupManagment.createNewGroup(canvas, groupName,
									descArea.getText(), null);
						}


						PrimeMain.groupsDialog.reloadGroupInfo();

						this.dispose();
					}
					else
					{
						// TODO - MSG about groups existence
					}
				}
				else
				{
					// TODO - MSG about incorrect name
				}
			}
			else
			{
				// TODO - MSG about empty name
			}
		}
		else if ( e.getActionCommand().equals("permSetup") )
		{
			new PermissionsDialog(canvas);
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}
}
