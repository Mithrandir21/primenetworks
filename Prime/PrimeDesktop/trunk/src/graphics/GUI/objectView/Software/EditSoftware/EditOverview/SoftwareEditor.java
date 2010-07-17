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
package graphics.GUI.objectView.Software.EditSoftware.EditOverview;


import graphics.PrimeMain;
import graphics.GUI.objectView.Software.SoftwareView;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Object;
import objects.Software;


public class SoftwareEditor extends JDialog implements ActionListener
{
	private Object givenObject = null;

	private SoftwareEditorTabbed view;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public SoftwareEditor(Object obj)
	{
		this.setTitle(PrimeMain.texts.getString("swTabFrameLabel"));

		givenObject = obj;

		Dimension size = new Dimension(770, 620);

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		int initYLocation = (scrnsize.height - size.height) / 2;
		int initXLocation = (scrnsize.width - size.width) / 2;


		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		view = new SoftwareEditorTabbed(obj);
		panel.add(view);



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button(PrimeMain.texts.getString("apply"));
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension(size.width, 1));

		panel.add(buttons);



		c.add(panel);

		this.setLocation(initXLocation, initYLocation);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setVisible(true);
	}



	/**
	 * Creates a JPanel that shows an Icon representing the hardware object and
	 * two fields with the name and description of the hardware object.
	 * 
	 * @param sw
	 *            The actual hardware object.
	 * @param icon
	 *            The Icon representing the hardware component.
	 * @param name
	 *            A JTextField that will contain the name of the object.
	 * @param desc
	 *            A JTextArea that holds the description of the object.
	 * @return Returns the created JPanel with all the information about the
	 *         hardware object.
	 */
	public static JPanel GeneralInfo(Software sw, ImageIcon icon,
			JTextField name, JTextArea desc)
	{
		JPanel genPanel = new JPanel();
		genPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTHWEST; // location
		c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 2 row wide
		c.gridheight = 2; // 2 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		JLabel image = new JLabel(icon);
		genPanel.add(image, c);


		JLabel nameLabel = new JLabel(PrimeMain.texts
				.getString("swTabSWnameLabel"));
		c.gridheight = 1; // 2 columns wide
		c.gridx = 1;
		c.gridy = 0;
		genPanel.add(nameLabel, c);


		name.setName("Name");
		name.setText(sw.getObjectName());
		JLabel t = new JLabel();
		name.setFont(t.getFont());
		// name.setBorder(BorderFactory.createEmptyBorder());
		c.gridx = 2;
		c.gridy = 0;
		genPanel.add(name, c);



		JLabel descLabel = new JLabel(PrimeMain.texts
				.getString("swTabSWdescriptionLabel"));
		c.gridx = 1;
		c.gridy = 1;
		genPanel.add(descLabel, c);


		// Description
		JScrollPane descScroll = new JScrollPane();
		desc.setName("Description");
		desc.setText(sw.getDescription());
		desc.setFont(t.getFont());
		descScroll.setViewportView(desc);

		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 2;
		c.gridy = 1;
		genPanel.add(descScroll, c);

		return genPanel;
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			view.save();

			this.dispose();
		}
		else if ( e.getActionCommand().equals("apply") )
		{
			// Saves the information with the option of verification.
			view.save();
		}
		else
		{
			assert (e.getActionCommand().equals("cancel"));

			this.dispose();
		}
	}



	/**
	 * Tries to set the tab which contains the given object as the focused tab.
	 * 
	 * @param obj
	 *            The hardware object which the search for tab should contain.
	 */
	public void setTabFocus(Software obj)
	{
		int tabCount = view.getTabCount();

		for ( int i = 0; i < tabCount; i++ )
		{
			// Gets the component at the given index tab
			Component comp = view.getComponent(i);

			// If the hardware object returned by the component, which
			// implements HardwareViewInterface, equals the
			// given object.
			if ( ((SoftwareView) comp).getViewSoftware().equals(obj) )
			{
				view.setSelectedIndex(i);
				return;
			}
		}

	}




	/**
	 * This function is used for when software information is changed or when a
	 * component is added or removed from an object. It redraws the views that
	 * show all current software information.
	 */
	public void SoftwarePanelRevalidate()
	{
		view.removeAll();

		view.populateTabs(givenObject);
	}
}
