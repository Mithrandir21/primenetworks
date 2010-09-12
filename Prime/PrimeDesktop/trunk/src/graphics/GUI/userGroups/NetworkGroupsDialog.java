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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import widgets.WorkareaCanvas;

/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NetworkGroupsDialog extends JDialog {
	/**
	 * A constructor for the class that sets up the window.
	 */
	public NetworkGroupsDialog(WorkareaCanvas canvas) {
		this.setTitle(canvas.getCanvasName() + " Groups");

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(500, 220);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		this.setPreferredSize(size);
		
		
		panelSetup(canvas);

		
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void panelSetup(WorkareaCanvas canvas) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		mainPanel.add(getButtonPanel(), c);

		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 1; // column
		mainPanel.add(getGroupsPanel(canvas), c);

		JButton closeButton = new JButton("Close");
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST; // location
		c.weighty = 0; // request any extra vertical space
		c.weightx = 0; // request any extra horizontal space
		c.gridwidth = 2; // 2 row wide
		c.gridy = 1; // row
		c.gridx = 1; // column
		mainPanel.add(closeButton, c);

		this.add(mainPanel);
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
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

		JButton addButton = new JButton("New");
		buttonPanel.add(addButton, c);

		JButton editButton = new JButton("Edit");
		c.gridy = 1; // row
		buttonPanel.add(editButton, c);

		JButton removeButton = new JButton("Delete");
		c.weighty = 1.0; // request any extra vertical space
		c.gridy = 2; // row
		buttonPanel.add(removeButton, c);

		return buttonPanel;
	}

	private JPanel getGroupsPanel(WorkareaCanvas canvas) {
		JPanel groupPanel = new JPanel();
		groupPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// groupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		// c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(getGroupTable(canvas));

		groupPanel.add(scroll, c);

		return groupPanel;
	}

	private JTable getGroupTable(WorkareaCanvas canvas) {

		String[] columnNames = { "Group Name", "Group Description" };

		Object[][] data = { { "Math Studens", "Smith" }, { "Physics Students", "Doe" },
				{ "Teachers", "Black" }, { "Administrators", "White" }, { "Guests", "Brown" } };

		JTable groupTable = new JTable(data, columnNames);
		groupTable.setFillsViewportHeight(true);

		TableColumn nameColumn = groupTable.getColumnModel().getColumn(0);
		nameColumn.setPreferredWidth(this.getPreferredSize().width / 2);
		TableColumn descColumn = groupTable.getColumnModel().getColumn(1);
		descColumn.setPreferredWidth(this.getPreferredSize().width);

		
		return groupTable;
	}
}
