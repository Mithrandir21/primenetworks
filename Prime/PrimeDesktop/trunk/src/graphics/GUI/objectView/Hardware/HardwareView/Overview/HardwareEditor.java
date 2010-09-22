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
package graphics.GUI.objectView.Hardware.HardwareView.Overview;


import graphics.PrimeMain;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;

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

import objects.ExternalHardware;
import objects.Hardware;
import objects.Infrastructure;
import objects.Object;
import objects.infrastructureObjects.Internet;


/**
 * CLASSDesc - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HardwareEditor extends JDialog implements ActionListener
{
	private Object givenObject = null;

	private HardwareEditorTabbed view;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public HardwareEditor(Object obj)
	{
		this.setTitle(PrimeMain.texts.getString("hwTabFrameLabel"));

		createNewEditorTab(obj);
	}

	/**
	 * Creates a JPanel that shows an Icon representing the hardware object and
	 * two fields with the name and description of the hardware object.
	 * 
	 * @param hw
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
	public static JPanel GeneralInfo(Hardware hw, ImageIcon icon,
			JTextField name, JTextArea desc)
	{
		JPanel genPanel = new JPanel();
		genPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.WEST; // location
		c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 2 row wide
		c.gridheight = 2; // 2 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		JLabel image = new JLabel(icon);
		genPanel.add(image, c);



		JLabel nameLabel = new JLabel(PrimeMain.texts
				.getString("hwTabHWnameLabel"));
		c.gridheight = 1; // 2 columns wide
		c.gridx = 1;
		c.gridy = 0;
		genPanel.add(nameLabel, c);


		name.setName("Name");
		name.setText(hw.getObjectName());
		JLabel t = new JLabel();
		name.setFont(t.getFont());
		// name.setBorder(BorderFactory.createEmptyBorder());
		c.gridx = 2;
		c.gridy = 0;
		genPanel.add(name, c);



		JLabel descLabel = new JLabel(PrimeMain.texts
				.getString("hwTabHWdescriptionLabel"));
		c.gridx = 1;
		c.gridy = 1;
		genPanel.add(descLabel, c);



		// Description
		JScrollPane descScroll = new JScrollPane();
		desc.setName("Description");
		desc.setText(hw.getDescription());
		desc.setFont(t.getFont());
		descScroll.setViewportView(desc);

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 2;
		c.gridy = 1;
		genPanel.add(descScroll, c);

		return genPanel;
	}


	public void createNewEditorTab(Object obj)
	{
		this.givenObject = obj;

		Dimension size = new Dimension(870, 600);

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


		this.view = new HardwareEditorTabbed(obj);
		panel.add(this.view);



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



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			boolean verify = true;

			// If the object is an instance of infrastructure, external hardware or internet class.
			if ( this.givenObject instanceof Infrastructure
					|| this.givenObject instanceof ExternalHardware
					|| this.givenObject instanceof Internet )
			{
				verify = false;
			}

			// If the information is saved a true is returned and the JFrame is closed.
			if ( this.view.save(verify) )
			{
				this.dispose();
			}
		}
		else if ( e.getActionCommand().equals("apply") )
		{
			boolean verify = true;

			// If the object is an instance of infrastructure.
			if ( givenObject instanceof Infrastructure
					|| givenObject instanceof ExternalHardware
					|| givenObject instanceof Internet )
			{
				verify = false;
			}

			// Saves the information with the option of verification.
			view.save(verify);
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
	public void setTabFocus(Hardware obj)
	{
		int tabCount = view.getTabCount();

		for ( int i = 0; i < tabCount; i++ )
		{
			// Gets the component at the given index tab
			Component comp = view.getComponent(i);

			// If the hardware object returned by the component, which
			// implements HardwareViewInterface, equals the
			// given object.
			if ( ((HardwareViewInterface) comp).getViewHardware().equals(obj) )
			{
				view.setSelectedIndex(i);
				return;
			}
		}

	}



	/**
	 * This function is used for when hardware information is changed or when a
	 * component is added or removed from an object. It redraws the views that
	 * show all current hardware information.
	 */
	public void HardwarePanelRevalidate()
	{
		view.removeAll();

		view.populateTabs(givenObject);
	}
}
