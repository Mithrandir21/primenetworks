/**
 * 
 */
package graphics.GUI.workareaCanvas;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

import exceptions.NotValidIPAddress;
import exceptions.RangeIsNotValidException;

import managment.FileManagment;
import managment.NetworkManagment;
import managment.RangeNotBigEnoughException;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
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
		this.setTitle("Create New Network");
		this.setResizable(false);


		// The name Label
		nameLabel.setText("Network Name");


		// The activate settings check box
		settingsCheck.addActionListener(this);
		settingsCheck.setActionCommand("settings");
		settingsCheck.setText("IP Network Settings");


		// // The netmask label and combo box
		// netmaskLabel.setText("IP Netmask");
		// netmaskCombo.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		// netmaskLabel.setVisible(false);
		// netmaskLabel.setEnabled(false);
		netmaskCombo.setVisible(false);
		netmaskCombo.setEnabled(false);


		// The range from label and textfield
		rangeFrom.setText("IP Range From");
		rangeFrom.setEnabled(false);
		rangeFromField.setEnabled(false);



		// The range to label and textfield
		rangeTo.setText("IP Range To");
		rangeTo.setEnabled(false);
		rangeToField.setEnabled(false);


		// The textfield for the network comment
		jScrollPane1.setBorder(BorderFactory.createTitledBorder("Network Description"));
		descTextarea.setColumns(20);
		descTextarea.setRows(5);
		jScrollPane1.setViewportView(descTextarea);










		GroupLayout layout = new GroupLayout(settings);
		settings.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(66, 66, 66).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(netmaskLabel)
								.addComponent(rangeTo, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(rangeFrom, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(rangeToField,
										GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE).addComponent(netmaskCombo, 0,
										126, Short.MAX_VALUE).addComponent(rangeFromField, GroupLayout.DEFAULT_SIZE, 126,
										Short.MAX_VALUE)).addGap(173, 173, 173)).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1,
								GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
								.addComponent(settingsCheck).addGroup(
										layout.createSequentialGroup().addComponent(nameLabel,
												GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addGap(18,
												18, 18).addComponent(nameField, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE))).addGap(30, 30, 30)));
		layout
				.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
						layout.createSequentialGroup().addGap(22, 22, 22).addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(nameLabel,
										GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE).addComponent(nameField,
										GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(31, 31, 31)
								.addComponent(settingsCheck).addGap(10, 10, 10).addGroup(
										layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(
												GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addComponent(netmaskLabel,
														GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(rangeFrom, GroupLayout.PREFERRED_SIZE, 23,
																GroupLayout.PREFERRED_SIZE).addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED).addComponent(
																rangeTo, GroupLayout.PREFERRED_SIZE, 23,
																GroupLayout.PREFERRED_SIZE)).addGroup(
												GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addGap(28, 28, 28).addComponent(
														rangeFromField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(rangeToField, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(netmaskCombo, GroupLayout.Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)).addGap(49, 49, 49).addComponent(
										jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));


		panel.add(settings);




		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		JButton save = new JButton("Create Network");
		save.addActionListener(this);
		save.setActionCommand("create_network");

		JButton cancel = new JButton("cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);


		this.add(panel);


		int initYLocation = (scrnsize.height - this.getSize().height) / 3;
		int initXLocation = (scrnsize.width - this.getSize().width) / 2;


		this.setSize(454, 440);
		this.setLocation(initXLocation, initYLocation);
		this.setVisible(true);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// The IP network settings checkbox
		if ( e.getActionCommand().equalsIgnoreCase("settings") )
		{
			if ( settingsCheck.isSelected() )
			{
				rangeFrom.setEnabled(true);
				rangeFromField.setEnabled(true);
				rangeTo.setEnabled(true);
				rangeToField.setEnabled(true);
			}
			else
			{
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
								// Checks whether or not the range between the two is valid
								if ( NetworkManagment.processRange(rangeFromField.getText(), rangeToField.getText()) )
								{
									FileManagment.newWorkareaCanvas(nameField.getText(), rangeFromField.getText(),
											rangeToField.getText(), descTextarea.getText());
									
									this.dispose();
								}
							}
							catch ( Exception exp )
							{
								String output = exp.getMessage();

								JOptionPane.showMessageDialog(null, output, "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				else
				{
					FileManagment.newWorkareaCanvas(nameField.getText());
					
					this.dispose();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The network most have a name.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		// The cancel button has been pressed
		else
		{
			this.dispose();
		}

	}
}
