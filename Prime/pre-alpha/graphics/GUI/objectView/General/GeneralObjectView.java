package graphics.GUI.objectView.General;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class GeneralObjectView extends JPanel implements ActionListener
{
	public JTextField nametext;
	
	public JTextArea textarea;
	
	
	/**
	 * TODO - Description NEEDED!
	 *
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
		c.insets = new Insets(20,2,2,5);
		
		this.add(createGeneralInfoPanelLeft(),c);
		
		
		// Middle left text
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(20,2,2,2);
		
		this.add(createGeneralInfoPanelMiddleLeft(obj),c);
		
		
		// Middle right labels
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(20,2,2,2);
		
		this.add(createGeneralInfoPanelMiddleRight(),c);
		
		
		// Right text
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(20,2,2,2);
		
		this.add(createGeneralInfoPanelRight(obj),c);
		
		
		// Bottom textarea
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.25;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		
		this.add(createGeneralInfoPanelBottom(obj),c);
		
		
	}
	
	
	private JPanel createGeneralInfoPanelLeft()
	{
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		
		
		
		JLabel descLabel = new JLabel("Description");
		descLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		
		
		labels.add(typeLabel);
		labels.add(Box.createRigidArea(new Dimension(0,20)));
		labels.add(nameLabel);
		labels.add(Box.createVerticalGlue());
		labels.add(descLabel);
		
		return labels;
	}
	
	
	private JPanel createGeneralInfoPanelMiddleLeft(Object obj)
	{
		JPanel texts = new JPanel();
		texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));
		
		
		
		JTextField typetext = new JTextField(obj.getClass().getSimpleName());
		typetext.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		typetext.setMaximumSize(new Dimension(150,20));
		typetext.setEditable(false);
		
		nametext = new JTextField(obj.getObjectName());
		nametext.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		nametext.setMaximumSize(new Dimension(150,20));
		
		texts.add(typetext);
		texts.add(Box.createRigidArea(new Dimension(0,20)));
		texts.add(nametext);
		
		
		return texts;
	}
	
	
	
	private JPanel createGeneralInfoPanelMiddleRight()
	{
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
		
		
		JLabel supIntLabel = new JLabel("Supported Interfaces");
		supIntLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		
		JLabel conLabel = new JLabel("Number of connected devices");
		conLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		
		labels.add(supIntLabel);
		labels.add(Box.createRigidArea(new Dimension(0,50)));
		labels.add(conLabel);
		
		
		return labels;
	}
	
	
	
	private JPanel createGeneralInfoPanelRight(Object obj)
	{
		JPanel texts = new JPanel();
		texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));
		
		
		JComboBox comboBox = new JComboBox(obj.getSupportedConnectionInterfaces());
		comboBox.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		comboBox.setMaximumSize(new Dimension(150,20));
		comboBox.setEditable(false);
		
		JTextField numConDev = new JTextField("" + obj.getNumberOfConnectedDevices());
		numConDev.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		numConDev.setMaximumSize(new Dimension(150,20));
		numConDev.setEditable(false);
		
		texts.add(comboBox);
		texts.add(Box.createRigidArea(new Dimension(0,50)));
		texts.add(numConDev);
		
		return texts;
	}
	
	
	
	
	private JPanel createGeneralInfoPanelBottom(Object obj)
	{
		JPanel decsArea = new JPanel();
		decsArea.setLayout(new GridLayout(0,1));
		
		textarea = new JTextArea(obj.getDescription(),5,10);
		textarea.setEditable(true);

		JScrollPane scrollArea = new JScrollPane(textarea);
		scrollArea.setPreferredSize(new Dimension(150,15));
		
		decsArea.add(scrollArea);
		
		return decsArea;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
