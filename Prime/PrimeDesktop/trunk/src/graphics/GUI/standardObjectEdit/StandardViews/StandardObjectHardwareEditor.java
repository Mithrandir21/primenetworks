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
package graphics.GUI.standardObjectEdit.StandardViews;


import graphics.PrimeMain;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.standardObjectEdit.ObjectHardwareViewTabbed;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
 * A JFrame, implementing the {@link ActionListener} interface, that will
 * display standard {@link Object Objects} that can be clicked and manipulated.
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjectHardwareEditor extends JFrame implements
		ActionListener
{
	private Object givenObject = null;

	private ObjectHardwareViewTabbed view;

	/**
	 * A constructor for the class that passes the given {@link Object} over to
	 * the private methods which set up the JFrame.
	 * 
	 * @param obj
	 *            The standard {@link Object} that can be edited.
	 */
	public StandardObjectHardwareEditor(Object obj)
	{
		super("Edit Standard Object");

		createNewEditorTab(obj);

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				if ( PrimeMain.stdObjView != null )
				{
					PrimeMain.stdObjView.getSplitView().getObjView()
							.getHardStdObjView().disposeHardwareView();
				}
			}
		});
	}



	public void createNewEditorTab(Object obj)
	{
		givenObject = obj;


		Dimension size = new Dimension(750, 600);

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


		view = new ObjectHardwareViewTabbed(obj);

		panel.add(view);



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);



		c.add(panel);


		this.setLocation(initXLocation, initYLocation);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setVisible(true);
	}


	/**
	 * Creates a JPanel that shows an Icon representing the standard object and
	 * two fields with the
	 * name and description of the object.
	 * 
	 * @param hw
	 *            The actual hardware object.
	 * @param icon
	 *            The Icon representing the component.
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


		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 2;

		JLabel image = new JLabel(icon);
		genPanel.add(image, c);


		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		d.gridx = 1;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.5;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.CENTER;

		JLabel nameLabel = new JLabel("Hardware name");
		genPanel.add(nameLabel, d);

		d.gridx = 2;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.5;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.LINE_START;

		name.setName("Name");
		name.setText(hw.getObjectName());
		JLabel t = new JLabel();
		name.setFont(t.getFont());
		// name.setBorder(BorderFactory.createEmptyBorder());
		genPanel.add(name, d);


		d.fill = GridBagConstraints.NONE;
		d.gridx = 1;
		d.gridy = 1;
		d.weightx = 1;
		d.weighty = 0.1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.CENTER;

		JLabel descLabel = new JLabel("Description");
		genPanel.add(descLabel, d);


		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 2;
		d.gridy = 1;
		d.weightx = 1;
		d.weighty = 0.1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.LINE_START;

		// Description
		JScrollPane descScroll = new JScrollPane();
		desc.setName("Description");
		desc.setText(hw.getDescription());
		desc.setFont(t.getFont());
		descScroll.setViewportView(desc);

		genPanel.add(descScroll, d);


		return genPanel;
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
		if ( e.getActionCommand().equals("save") )
		{
			boolean verify = true;

			// If the object is an instance of infrastructure.
			if ( givenObject instanceof Infrastructure
					|| givenObject instanceof ExternalHardware
					|| givenObject instanceof Internet )
			{
				verify = false;
			}

			// If the information is saved a true is returned and the
			// JFrame is closed.
			if ( view.save(verify) )
			{
				// this.dispose();
				if ( PrimeMain.stdObjView != null )
				{
					PrimeMain.stdObjView.getSplitView().getObjView()
							.getHardStdObjView().disposeHardwareView();

					PrimeMain.stdObjView.getSplitView().getObjView()
							.getHardStdObjView().updateTabInfo();
				}
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

			if ( PrimeMain.stdObjView != null )
			{
				PrimeMain.stdObjView.getSplitView().getObjView()
						.getHardStdObjView().updateTabInfo();
			}
		}
		else
		{
			assert (e.getActionCommand().equals("cancel"));

			if ( PrimeMain.stdObjView != null )
			{
				PrimeMain.stdObjView.getSplitView().getObjView()
						.getHardStdObjView().disposeHardwareView();
			}
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
	 * component is added
	 * or removed from an object. It redraws the views that show all current
	 * hardware information.
	 */
	public void HardwarePanelRevalidate()
	{
		view.removeAll();

		view.populateTabs(givenObject);
	}
}
