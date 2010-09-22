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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

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

	private JRadioButton noPerm;

	private JRadioButton copyPermBox;

	private JRadioButton customPermBox;

	private JTable groupTable;

	private JXCollapsiblePane collapsibleCustomPane;

	private JXCollapsiblePane collapsibleCopyPane;

	private CustomPermissionsPanel CustomPermissions;



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
		Dimension size = new Dimension(510, 650);

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
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


		mainPanel.add(getNameDescPanel());


		mainPanel.add(getCopyPermissionsPanel());


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
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(5, 5, 5, 5); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		// No permissions JCheckBox
		noPerm = new JRadioButton(
				PrimeMain.texts.getString("newGroupNoPermRadioLable"));

		noPerm.addActionListener(this);
		noPerm.setSelected(true);


		panel.add(noPerm, c);



		// The collapsible pane that will contain the
		collapsibleCopyPane = new JXCollapsiblePane();
		collapsibleCopyPane.setCollapsed(true);

		copyPermBox = new JRadioButton(
				PrimeMain.texts.getString("newGroupCopyPermRadioLabel"));
		copyPermBox.addActionListener(this);

		c.weighty = 0; // request any extra vertical space
		c.gridy = 1; // row
		panel.add(copyPermBox, c);



		JScrollPane scroll = new JScrollPane(getGroupCopyTable());
		scroll.setPreferredSize(new Dimension(scroll.getWidth(), 350));
		collapsibleCopyPane.add(scroll);


		c.weighty = 0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.gridy = 2; // row
		panel.add(collapsibleCopyPane, c);



		// The collapsible pane that will contain the
		collapsibleCustomPane = new JXCollapsiblePane();
		collapsibleCustomPane.setCollapsed(true);

		customPermBox = new JRadioButton(
				PrimeMain.texts
						.getString("newGroupCustomPermissionsRadioLabel"));
		customPermBox.addActionListener(this);

		c.gridy = 3; // row
		panel.add(customPermBox, c);


		CustomPermissions = new CustomPermissionsPanel(canvas);
		JScrollPane scrollPerm = new JScrollPane(CustomPermissions);
		scrollPerm.setPreferredSize(new Dimension(scrollPerm.getWidth(), 350));
		scrollPerm.getVerticalScrollBar().setUnitIncrement(10);
		collapsibleCustomPane.add(scrollPerm);

		c.gridy = 4; // row
		panel.add(collapsibleCustomPane, c);


		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.SOUTHEAST; // location
		c.weighty = 1.0; // request any extra vertical space
		c.gridy = 5; // row
		panel.add(getButtonPanel(), c);


		ButtonGroup boxes = new ButtonGroup();
		boxes.add(customPermBox);
		boxes.add(copyPermBox);
		boxes.add(noPerm);


		return panel;
	}


	private JTable getGroupCopyTable()
	{
		String[] columnNames = {
				PrimeMain.texts
						.getString("newGroupCopyPermTableColumnNameLabel"),
				PrimeMain.texts
						.getString("newGroupCopyPermTableColumnDescLabel") };

		String[][] data = GroupManagment.getGroupDataWithDesription(canvas);


		groupTable = new JTable(data, columnNames);
		groupTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		groupTable.setFillsViewportHeight(true);

		// Sets up the column sizes
		TableColumn column = null;
		for ( int i = 0; i < groupTable.getModel().getColumnCount(); i++ )
		{
			column = groupTable.getColumnModel().getColumn(i);
			if ( i == 0 )
			{
				// first column is smaller
				column.setPreferredWidth(40);
			}
			else
			{
				column.setPreferredWidth(250);
			}
		}


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


						// If the check box is selected, the user wants to copy
						// the permissions of another group.
						if ( copyPermBox.isSelected() )
						{
							HashMap<Long, Permissions> copiedPermissions = null;

							// Copy Permission group
							Group permGroup = null;

							int row = groupTable.getSelectedRow();

							// If any row is selected
							if ( row > -1 )
							{
								String copyGroupName = (String) groupTable
										.getModel().getValueAt(row, 0);

								permGroup = GroupManagment.getGroupWithName(
										canvas, copyGroupName);

								copiedPermissions = GroupManagment
										.deepCopyPermissionsMap(permGroup
												.getDevicePermissions());
							}

							// Attempts to create the group
							if ( GroupManagment.createNewGroup(canvas,
									groupName, descArea.getText(),
									copiedPermissions) )
							{
								canvas.setChanged(true);
							}
						}
						// If the custom permissions box is selected
						else if ( customPermBox.isSelected() )
						{
							// Gets the custom permissions
							HashMap<Long, Permissions> permissions = CustomPermissions
									.getCustomPermissions();

							// Attempts to create the group
							if ( GroupManagment.createNewGroup(canvas,
									groupName, descArea.getText(), permissions) )
							{
								canvas.setChanged(true);
							}
						}
						// Either no button has been chosen or noPerm
						else
						{
							// Attempts to create the group
							if ( GroupManagment.createNewGroup(canvas,
									groupName, descArea.getText(), null) )
							{
								canvas.setChanged(true);
							}
						}


						PrimeMain.groupsDialog.reloadGroupInfo();

						this.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("groupAlreadExistsWithNameMsg"),
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							PrimeMain.texts.getString("groupNameNotValidMsg"),
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						PrimeMain.texts.getString("groupNameIsEmptyMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if ( e.getSource().equals(customPermBox) )
		{
			collapsibleCustomPane.setCollapsed(!customPermBox.isSelected());
			collapsibleCopyPane.setCollapsed(customPermBox.isSelected());
		}
		else if ( e.getSource().equals(copyPermBox) )
		{
			collapsibleCopyPane.setCollapsed(!copyPermBox.isSelected());
			collapsibleCustomPane.setCollapsed(copyPermBox.isSelected());
		}
		else if ( e.getSource().equals(noPerm) )
		{
			if ( noPerm.isSelected() )
			{
				collapsibleCopyPane.setCollapsed(true);
				collapsibleCustomPane.setCollapsed(true);
			}
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}
}
