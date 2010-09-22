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
package graphics.GUI.objectView.General;


import graphics.PrimeMain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import objects.Object;


/**
 * This is a general view class. It will display, inside a JPanel, the general
 * information of an object, such as name and description. It also shows the
 * number of connection device this object is connected to.
 * 
 * @author Bahram Malaekeh
 */
public class GeneralObjectView extends JPanel
{
	public JTextField nametext;

	public JTextArea textarea;


	/**
	 * Creates and sets up the general information place within this JPanel
	 * instance.
	 * 
	 * @param obj
	 *            The object that will be used to gather information from.
	 */
	public GeneralObjectView(final Object obj)
	{
		// Panel that will hold everything, and then be added to this JPanel
		// class
		JPanel jPanel1 = new JPanel();

		// The Class of the object
		JLabel classLabel = new JLabel();
		classLabel.setText(PrimeMain.texts.getString("genTabClassLabel"));
		JTextField classField = new JTextField();
		classField.setEditable(false);
		classField.setText(obj.getClass().getSimpleName());


		// The name of the object
		JLabel nameLabel = new JLabel();
		nameLabel.setText(PrimeMain.texts.getString("genTabNameLabel"));
		nametext = new JTextField();
		nametext.setText(obj.getObjectName());
		nametext.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if ( key == KeyEvent.VK_ENTER )
				{
					// Saves the object info(when the user presses enter)
					PrimeMain.getObjectView(obj).save(true);
				}
			}
		});


		// The supported connections interfaces
		JLabel supConLabel = new JLabel();
		supConLabel.setText(PrimeMain.texts
				.getString("genTabSupInterfacesLabel"));
		JComboBox subConCombo = new JComboBox();
		subConCombo.setModel(new DefaultComboBoxModel(obj
				.getSupportedConnectionInterfaces()));

		// The number of jumps to the closest Internet object
		JLabel jumpsLabel = new JLabel();
		jumpsLabel.setText(PrimeMain.texts.getString("genTabNrOfJumpsLabel"));
		JTextField jumpsField = new JTextField();
		jumpsField.setEditable(false);
		jumpsField.setText("0");


		// The description of the object
		JScrollPane descScroll = new JScrollPane();
		JLabel descLabel = new JLabel();
		descLabel.setText(PrimeMain.texts.getString("genTabDescriptionLabel"));
		textarea = new JTextArea();
		textarea.setColumns(20);
		textarea.setRows(5);
		textarea.setText(obj.getDescription());
		descScroll.setViewportView(textarea);



		// The layout of the Panel
		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(36, 36, 36)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(nameLabel)
														.addComponent(
																classLabel)
														.addComponent(descLabel))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								nametext)
																						.addComponent(
																								classField,
																								GroupLayout.DEFAULT_SIZE,
																								117,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED,
																				145,
																				Short.MAX_VALUE)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								supConLabel)
																						.addComponent(
																								jumpsLabel))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jumpsField)
																						.addComponent(
																								subConCombo,
																								0,
																								82,
																								Short.MAX_VALUE)))
														.addComponent(
																descScroll,
																GroupLayout.DEFAULT_SIZE,
																457,
																Short.MAX_VALUE))
										.addGap(119, 119, 119)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(41, 41, 41)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																classLabel)
														.addComponent(
																classField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																supConLabel)
														.addComponent(
																subConCombo,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(30, 30, 30)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(nameLabel)
														.addComponent(
																nametext,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jumpsLabel)
														.addComponent(
																jumpsField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												23, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(descLabel)
														.addComponent(
																descScroll,
																GroupLayout.PREFERRED_SIZE,
																180,
																GroupLayout.PREFERRED_SIZE))
										.addGap(67, 67, 67)));


		// The layout of this JPanel class
		javax.swing.GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE));
	}
}