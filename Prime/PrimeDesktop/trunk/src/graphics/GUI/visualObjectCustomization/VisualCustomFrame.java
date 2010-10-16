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
package graphics.GUI.visualObjectCustomization;


import graphics.PrimeMain;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import objects.Object;
import widgets.WidgetButton;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class VisualCustomFrame extends JDialog implements ActionListener
{
	// A simple border that is gray
	private Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// The listener that listens for ImageIcon buttons pressed.
	private VisualCustomListener listener = new VisualCustomListener(this);

	// The HashMap that contains object ImageIcons temporary
	public HashMap<Class, ImageIcon> tempImageIcons = new HashMap<Class, ImageIcon>();

	// The JPanel all WidgetButtons will place in.
	JPanel iconPanel;

	// A boolean on whether or not anything has been changed
	boolean changed = false;


	/**
	 * TODO - Description NEEDED!
	 */
	public VisualCustomFrame()
	{
		this.setTitle(PrimeMain.texts.getString("visObjCustomFrameTitle"));

		tempImageIcons.putAll(PrimeMain.objectImageIcons);

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(456, 480);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;


		JPanel panel = new JPanel();
		panel.setBorder(grayline);

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 5, 10);

		iconPanel = createVisualPanel();
		JScrollPane scrollPane = new JScrollPane();
		iconPanel.setPreferredSize(new Dimension(scrollPane.getWidth(), 1000));
		scrollPane.setViewportView(iconPanel);

		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);


		// scrollPane
		// .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBorder(null);

		panel.add(scrollPane, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 0;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createButtons();

		panel.add(p2, c);


		this.add(panel);


		this.setSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);


		/**
		 * When the windows is closed by pressing the exit button, the pointer
		 * to this JFrame is set to null.
		 */
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				if ( changed )
				{
					String question = PrimeMain.texts
							.getString("imageIconsChangedQuestion");


					// Custom button text
					java.lang.Object[] options = {
							PrimeMain.texts.getString("yes"),
							PrimeMain.texts.getString("no") };


					int answer = JOptionPane.showOptionDialog(null, question,
							PrimeMain.texts.getString("confirm"),
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);


					if ( answer == 0 )
					{
						PrimeMain.vcf = null;
					}
				}

			}
		});
	}


	/**
	 * TODO - Description
	 */
	private JPanel createVisualPanel()
	{
		JPanel visPanel = new JPanel(new GridLayout(0, 3, 25, 25));


		Iterator<Object> iterator = PrimeMain.objectlist.iterator();

		while ( iterator.hasNext() )
		{
			visPanel.add(createImageButton(iterator.next()));
		}

		visPanel.setBackground(Color.WHITE);

		return visPanel;
	}


	/**
	 * TODO - Description
	 */
	private JButton createImageButton(Object obj)
	{
		WidgetButton button = new WidgetButton(obj);

		button.addActionListener(listener);
		button.setVerticalTextPosition(AbstractButton.BOTTOM);
		button.setHorizontalTextPosition(AbstractButton.CENTER);
		button.setPreferredSize(new Dimension(50, 70));

		return button;
	}


	/**
	 * Creates a JPanel with two buttons that are listened for by
	 * actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button reset = new Button(PrimeMain.texts.getString("reset"));
		reset.addActionListener(this);
		reset.setActionCommand("reset");

		Button cancel = new Button(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(reset);
		buttons.add(cancel);

		return buttons;
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
			saveImages();

			this.dispose();
		}
		else if ( e.getActionCommand().equals("reset") )
		{
			// FIXME - Fix reset button on Custom Visual Icons
		}
		else
		{
			assert (e.getActionCommand().equals("cancel"));

			if ( changed )
			{
				String question = PrimeMain.texts
						.getString("imageIconsChangedQuestion");


				// Custom button text
				java.lang.Object[] options = {
						PrimeMain.texts.getString("yes"),
						PrimeMain.texts.getString("no") };


				int answer = JOptionPane
						.showOptionDialog(null, question,
								PrimeMain.texts.getString("confirm"),
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);


				if ( answer == 0 )
				{
					this.dispose();
					// Sets the pointer to this JFrame to null.
					PrimeMain.vcf = null;
				}
			}
			else
			{
				this.dispose();
				// Sets the pointer to this JFrame to null.
				PrimeMain.vcf = null;
			}
		}
	}


	/**
	 * Saving function for saving icon images.
	 */
	private void saveImages()
	{
		// String question = PrimeMain1.texts
		// .getString("overwriteIconFileQuestions")
		// + "\n" + PrimeMain1.texts.getString("thisCannotBeUndoneMsg");
		//
		//
		// // Custom button text
		// java.lang.Object[] options = { PrimeMain1.texts.getString("yes"),
		// PrimeMain1.texts.getString("no") };
		//
		//
		// int answer = JOptionPane.showOptionDialog(null, question,
		// PrimeMain1.texts.getString("overwrite"),
		// JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
		// null, options, options[1]);


		for ( int i = 0; i < iconPanel.getComponentCount(); i++ )
		{
			WidgetButton button = (WidgetButton) iconPanel.getComponent(i);

			Object obj = button.getObject();


			ImageIcon icon = tempImageIcons.get(obj.getClass());


			button.getObject().setVisualImage(icon);

			// FIXME - Image Override
			// if ( answer == 0 )
			// {
			// // The File pointer to the Image file
			// File imageFile = GraphicalFunctions.getImageIconFile(icon);
			//
			// // If there was a File found
			// if ( imageFile != null )
			// {
			// // Attempts to write
			// SystemFunctions.copyIconIntoTheSystem(imageFile);
			// }
			// }
		}


		PrimeMain.objectImageIcons.putAll(tempImageIcons);

		PrimeMain.updateObjectSelectionArea();
	}



	/**
	 * Updates the icons on the buttons.
	 */
	public void updateButtonImages()
	{
		for ( int i = 0; i < iconPanel.getComponentCount(); i++ )
		{
			WidgetButton button = (WidgetButton) iconPanel.getComponent(i);

			button.setIcon(tempImageIcons.get(button.getObject().getClass()));

			button.repaint();
		}

		changed = true;
	}
}
