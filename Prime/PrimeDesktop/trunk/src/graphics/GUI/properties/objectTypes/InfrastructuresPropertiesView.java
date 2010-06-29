/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.PrimeMain;
import graphics.GUI.properties.ObjectProperties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import objects.Infrastructure;
import objects.Object;


/**
 * This class creates a properties JPanel for {@link Infrastructure}.
 * 
 * @author Bahram Malaekeh
 */
public class InfrastructuresPropertiesView extends AbstractObjectPropertiesView implements ActionListener
{

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public InfrastructuresPropertiesView(Object obj)
	{
		super(obj);

		d.gridy = 1;
		this.add(getInfrastructuresPropertiesView(obj), d);


		d.weighty = 1.0; // request any extra vertical space
		d.gridy = 2;
		this.add(ObjectProperties.createButtons(this), d);
	}

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public static JPanel getInfrastructuresPropertiesView(Object obj)
	{

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints panelCons = new GridBagConstraints();

		panelCons.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		panelCons.weightx = 0.8; // request any extra horizontal space
		panelCons.anchor = GridBagConstraints.NORTH; // location
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		panelCons.gridy = 0; // row
		panelCons.gridx = 0; // column






		return panel;
	}


	/**
	 * Resets all specific fields to the info gotten from the {@link Object} viewed.
	 */
	private void resetFields()
	{
		resetGeneralFields();
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @author Bahram Malaekeh
	 * 
	 */
	public class SaveKey extends KeyAdapter
	{
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			{
				if ( key == KeyEvent.VK_ENTER )
				{
					saveAction();
				}
			}
		}
	}



	/**
	 * TODO - Description
	 * 
	 */
	private void saveAction()
	{
		generalSaveAction();

		// If any errors occur during the saving process
		if ( !errorDuringSaving )
		{
			PrimeMain.updatePropertiesObjectArea(objectViewed, true);
		}
	}



	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals(PrimeMain.texts.getString("reset")) )
		{
			resetFields();
		}
		else if ( e.getActionCommand().equals(
				PrimeMain.texts.getString("save")) )
		{
			saveAction();
		}
	}
}
