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
package graphics.GUI.workareaCanvas;


import graphics.PrimeMain;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import managment.DesktopFileManagment;
import managment.NetworkManagment;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CreateNewWorkareaCanvas extends JFrame implements ActionListener
{
	JLabel nameLabel = new JLabel();

	JTextField nameField = new JTextField();


	JCheckBox settingsCheck = new JCheckBox();


	JLabel netmaskLabel = new JLabel();

	JComboBox netmaskCombo = new JComboBox();


	JLabel rangeFrom = new JLabel();

	JTextField rangeFromField = new JTextField();


	JLabel rangeTo = new JLabel();

	JTextField rangeToField = new JTextField();


	JScrollPane jScrollPane1 = new JScrollPane();

	JTextArea descTextarea = new JTextArea();


	public CreateNewWorkareaCanvas()
	{
		super(PrimeMain.texts.getString("newWorkareaCanvasFrameLabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		// // Get the content pane for this object
		// Container c = this.getContentPane();

		// The panel for the whole JFrame
		JPanel panel = new JPanel();
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		// The panel for the settings
		JPanel settings = new JPanel();


		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);


		// The name Label
		nameLabel.setText(PrimeMain.texts
				.getString("newWorkareaCanvasNetworkNameLabel"));



		// The activate settings check box
		settingsCheck.addActionListener(this);
		settingsCheck.setActionCommand("settings");
		settingsCheck.setText(PrimeMain.texts
				.getString("newWorkareaCanvasIPsettingsLabel"));


		// The netmask label and combo box
		netmaskLabel.setText(PrimeMain.texts
				.getString("newWorkareaCanvasIPnetmaskLabel"));
		netmaskCombo.setModel(new DefaultComboBoxModel(new String[] {
				"255.255.255.0", "255.255.0.0", "255.0.0.0" }));
		netmaskLabel.setEnabled(false);
		netmaskCombo.setEnabled(false);


		// The range from label and textfield
		rangeFrom.setText(PrimeMain.texts
				.getString("newWorkareaCanvasIPfromLabel"));
		rangeFrom.setEnabled(false);
		rangeFromField.setEnabled(false);



		// The range to label and textfield
		rangeTo
				.setText(PrimeMain.texts
						.getString("newWorkareaCanvasIPtoLabel"));
		rangeTo.setEnabled(false);
		rangeToField.setEnabled(false);


		// The textfield for the network comment
		jScrollPane1.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("newWorkareaCanvasNetworkDescriptionLabel")));
		descTextarea.setColumns(20);
		descTextarea.setRows(5);
		jScrollPane1.setViewportView(descTextarea);










		GroupLayout layout = new GroupLayout(settings);
		settings.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(66, 66, 66).addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addComponent(
								netmaskLabel).addComponent(rangeTo,
								GroupLayout.PREFERRED_SIZE, 75,
								GroupLayout.PREFERRED_SIZE).addComponent(
								rangeFrom, GroupLayout.PREFERRED_SIZE, 91,
								GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(rangeToField,
												GroupLayout.DEFAULT_SIZE, 126,
												Short.MAX_VALUE).addComponent(
												netmaskCombo, 0, 126,
												Short.MAX_VALUE).addComponent(
												rangeFromField,
												GroupLayout.DEFAULT_SIZE, 126,
												Short.MAX_VALUE)).addGap(173,
								173, 173)).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(32,
						Short.MAX_VALUE).addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addComponent(
								jScrollPane1, GroupLayout.PREFERRED_SIZE, 412,
								GroupLayout.PREFERRED_SIZE).addComponent(
								settingsCheck).addGroup(
								layout.createSequentialGroup().addComponent(
										nameLabel, GroupLayout.PREFERRED_SIZE,
										88, GroupLayout.PREFERRED_SIZE).addGap(
										18, 18, 18).addComponent(nameField,
										GroupLayout.PREFERRED_SIZE, 115,
										GroupLayout.PREFERRED_SIZE))).addGap(
						30, 30, 30)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(22, 22, 22)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																nameLabel,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																23,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																nameField,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(31, 31, 31)
										.addComponent(settingsCheck)
										.addGap(10, 10, 10)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addGroup(
																GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				netmaskLabel,
																				GroupLayout.PREFERRED_SIZE,
																				14,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				rangeFrom,
																				GroupLayout.PREFERRED_SIZE,
																				23,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				rangeTo,
																				GroupLayout.PREFERRED_SIZE,
																				23,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addGap(
																				28,
																				28,
																				28)
																		.addComponent(
																				rangeFromField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				rangeToField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																netmaskCombo,
																GroupLayout.Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(49, 49, 49).addComponent(
												jScrollPane1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));


		panel.add(settings);




		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		final JButton save = new JButton(PrimeMain.texts
				.getString("newWorkareaCanvasButtonCreateNetwork"));
		save.addActionListener(this);
		save.setActionCommand("create_network");

		JButton cancel = new JButton(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);



		// Sets up the "Enter" button for the namefield
		nameField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if ( key == KeyEvent.VK_ENTER )
				{
					save.doClick();
				}
			}
		});


		this.add(panel);


		int initYLocation = (scrnsize.height - this.getSize().height) / 4;
		int initXLocation = (scrnsize.width - this.getSize().width) / 3;


		this.setSize(454, 440);
		this.setLocation(initXLocation, initYLocation);
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
		// The IP network settings checkbox
		if ( e.getActionCommand().equalsIgnoreCase("settings") )
		{
			if ( settingsCheck.isSelected() )
			{
				netmaskLabel.setEnabled(true);
				netmaskCombo.setEnabled(true);
				rangeFrom.setEnabled(true);
				rangeFromField.setEnabled(true);
				rangeTo.setEnabled(true);
				rangeToField.setEnabled(true);
			}
			else
			{
				netmaskLabel.setEnabled(false);
				netmaskCombo.setEnabled(false);
				rangeFrom.setEnabled(false);
				rangeFromField.setEnabled(false);
				rangeTo.setEnabled(false);
				rangeToField.setEnabled(false);
			}
		}
		// The create network button
		else if ( e.getActionCommand().equals("create_network") )
		{
			if ( !(nameField.getText().equals("")) )
			{
				if ( settingsCheck.isSelected() )
				{
					if ( !(rangeFromField.getText().equals("")) )
					{
						if ( !(rangeToField.getText().equals("")) )
						{
							try
							{
								// Checks whether or not the range between the
								// two is valid
								if ( NetworkManagment.processRange(
										rangeFromField.getText(), rangeToField
												.getText()) )
								{
									boolean created = DesktopFileManagment
											.newWorkareaCanvas(
													PrimeMain.glassPane,
													nameField.getText(),
													netmaskCombo
															.getSelectedItem()
															.toString(),
													rangeFromField.getText(),
													rangeToField.getText(),
													descTextarea.getText());

									if ( created )
									{
										this.dispose();
									}

									// Gets the index of the Tab where the newly
									// created WorkareaCanvas is placed
									int index = PrimeMain.workTab
											.indexOfTabWithCanvas(nameField
													.getText());

									// If the index is not -1, which means that
									// the tab was found
									if ( index != -1 )
									{
										// Sets the focused on tab that contains
										// the new WorkareaCanvas
										PrimeMain.workTab
												.setSelectedIndex(index);
									}
								}
							}
							catch ( Exception exp )
							{
								String output = exp.getMessage();

								JOptionPane.showMessageDialog(null, output,
										PrimeMain.texts.getString("error"),
										JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane
									.showMessageDialog(
											null,
											PrimeMain.texts
													.getString("newWorkareaCanvasIPendErrorMsg"),
											PrimeMain.texts.getString("error"),
											JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("newWorkareaCanvasIPstartErrorMsg"),
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					boolean created = DesktopFileManagment.newWorkareaCanvas(
							PrimeMain.glassPane, nameField.getText());

					if ( created )
					{
						this.dispose();
					}

					// Gets the index of the Tab where the newly created
					// WorkareaCanvas is placed
					int index = PrimeMain.workTab
							.indexOfTabWithCanvas(nameField.getText());

					// If the index is not -1, which means that the tab was
					// found
					if ( index != -1 )
					{
						// Sets the focused on tab that contains the new
						// WorkareaCanvas
						PrimeMain.workTab.setSelectedIndex(index);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("newWorkareaCanvasNoNameErrorMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);
			}

		}
		// The cancel button has been pressed
		else
		{
			this.dispose();
		}
	}
}
