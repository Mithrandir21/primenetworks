package graphics.GUI.objectView.General;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Object;


/**
 * This is a general view class. It will display, inside a JPanel, the general information of an object, such as name
 * and description. It also shows the number of connection device this object is connected to.
 * 
 * @author Bahram Malaekeh
 */
public class GeneralObjectView extends JPanel
{
	public JTextField nametext;

	public JTextArea textarea;


	/**
	 * Creates and sets up the general information place within this JPanel instance.
	 * 
	 * @param obj
	 *            The object that will be used to gather information from.
	 */
	public GeneralObjectView(Object obj)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;


		// Left labels.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.10;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.insets = new Insets(20, 2, 2, 5);

		this.add(createGeneralInfoPanelLeft(), c);


		// Middle left text
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(20, 2, 2, 2);

		this.add(createGeneralInfoPanelMiddleLeft(obj), c);


		// Middle right labels
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(20, 2, 2, 2);

		this.add(createGeneralInfoPanelMiddleRight(), c);


		// Right text
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(20, 2, 2, 2);

		this.add(createGeneralInfoPanelRight(obj), c);


		// Bottom textarea
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.25;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);

		this.add(createGeneralInfoPanelBottom(obj), c);


	}


	/**
	 * Creates a JPanel that only contains JLabels. Specifically the label for type, name and description.
	 * 
	 * @return Returns a JPanel with only Labels.
	 */
	private JPanel createGeneralInfoPanelLeft()
	{
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);



		JLabel descLabel = new JLabel("Description");
		descLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);


		labels.add(typeLabel);
		labels.add(Box.createRigidArea(new Dimension(0, 20)));
		labels.add(nameLabel);
		labels.add(Box.createVerticalGlue());
		labels.add(descLabel);

		return labels;
	}


	/**
	 * Creates and returns a JPanel for text input. Specifically for type, name and description.
	 * 
	 * @param obj
	 *            The object from which the fields get their original data from.
	 * @return The JPanel with the fields and the data from the object.
	 */
	private JPanel createGeneralInfoPanelMiddleLeft(Object obj)
	{
		JPanel texts = new JPanel();
		texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));



		JTextField typetext = new JTextField(obj.getClass().getSimpleName());
		typetext.setAlignmentX(Component.LEFT_ALIGNMENT);
		typetext.setMaximumSize(new Dimension(150, 20));
		typetext.setEditable(false);

		nametext = new JTextField(obj.getObjectName());
		nametext.setAlignmentX(Component.LEFT_ALIGNMENT);
		nametext.setMaximumSize(new Dimension(150, 20));

		texts.add(typetext);
		texts.add(Box.createRigidArea(new Dimension(0, 20)));
		texts.add(nametext);


		return texts;
	}



	/**
	 * Creates and returns a JPanel with labels. Specifically the labels for supported interfaces and number of
	 * connected devices.
	 * 
	 * @return Returns the JPanel with the labels.
	 */
	private JPanel createGeneralInfoPanelMiddleRight()
	{
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));


		JLabel supIntLabel = new JLabel("Supported Interfaces");
		supIntLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JLabel conLabel = new JLabel("Number of connected devices");
		conLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		labels.add(supIntLabel);
		labels.add(Box.createRigidArea(new Dimension(0, 50)));
		labels.add(conLabel);


		return labels;
	}



	/**
	 * Creates and returns a JPanel with text fields that can not be edited. Specifically for number of connected
	 * devices and supported interfaces.
	 * 
	 * @param obj
	 *            The object where the fields get their data from.
	 * @return The JPanel that contains the fields and the data.
	 */
	private JPanel createGeneralInfoPanelRight(Object obj)
	{
		JPanel texts = new JPanel();
		texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));


		JComboBox comboBox = new JComboBox(obj.getSupportedConnectionInterfaces());
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboBox.setMaximumSize(new Dimension(150, 20));
		comboBox.setEditable(false);

		JTextField numConDev = new JTextField("" + obj.getNumberOfConnectedDevices());
		numConDev.setAlignmentX(Component.LEFT_ALIGNMENT);
		numConDev.setMaximumSize(new Dimension(150, 20));
		numConDev.setEditable(false);

		texts.add(comboBox);
		texts.add(Box.createRigidArea(new Dimension(0, 50)));
		texts.add(numConDev);

		return texts;
	}




	/**
	 * Creates and returns a JPanel with only a JTextArea that will hold the description of the object.
	 * 
	 * @param obj
	 *            The object where the JTextArea will get its data from.
	 * @return The JPanel that hold the JTextArea with the information.
	 */
	private JPanel createGeneralInfoPanelBottom(Object obj)
	{
		JPanel decsArea = new JPanel();
		decsArea.setLayout(new GridLayout(0, 1));

		textarea = new JTextArea(obj.getDescription(), 5, 10);
		textarea.setEditable(true);

		JScrollPane scrollArea = new JScrollPane(textarea);
		scrollArea.setPreferredSize(new Dimension(150, 15));

		decsArea.add(scrollArea);

		return decsArea;
	}
}
