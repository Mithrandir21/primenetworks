/**
 * 
 */
package graphics.GUI.properties;


import graphics.GUI.SpringUtilities;
import graphics.GUI.properties.objectTypes.ClientsPropertiesView;
import graphics.GUI.properties.objectTypes.GeneralPropertiesView;
import graphics.GUI.properties.objectTypes.InfrastructuresPropertiesView;
import graphics.GUI.properties.objectTypes.PeripheralsPropertiesView;
import graphics.GUI.properties.objectTypes.ServersPropertiesView;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectProperties extends JPanel implements ActionListener
{
	Object objectViewed = null;

	// Number of components added to this JPanel
	int compCount = 0;


	// These are the standard Object fields.
	JLabel standardLabels[] = new JLabel[4];

	// Standard clients labels
	JLabel clientsLabels[] = new JLabel[1];

	// Standard servers labels
	JLabel serversLabels[] = new JLabel[4];

	// Standard peripherals labels
	JLabel peripheralsLabels[] = new JLabel[0];

	// Standard infrastructure labels
	JLabel infrastructuresLabels[] = new JLabel[0];

	// Standard connections labels
	JLabel connectionsLabels[] = new JLabel[3];

	

	/**
	 * TODO - Description NEEDED!
	 *
	 * @param canvas
	 */
	public ObjectProperties(WorkareaCanvas canvas)
	{
		this.setLayout(new SpringLayout());

		GeneralPropertiesView.getGeneralCanvasProperties(this,canvas);
		
		
		this.add(createButtons());


		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, 5, 1, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		
	}





	/**
	 * TODO - Description NEEDED!
	 *
	 * @param object
	 */
	public ObjectProperties(Object object)
	{

		objectViewed = object;


		this.setLayout(new SpringLayout());


		showStandardButtons(object);


		if ( object instanceof clients.Desktop
				|| object instanceof clients.Laptop )
		{
			showDesktopProperties(object);
		}
		else if ( object instanceof servers.HTTPServer
				|| object instanceof servers.BackupServer
				|| object instanceof servers.MailServer
				|| object instanceof servers.FirewallServer
				|| object instanceof servers.ProxyServer )
		{
			showServerProperties(object);
		}
		else if ( object instanceof hardware.HDD )
		{
		}
		else if ( object instanceof peripheral.Scanner )
		{
			showPeripheralProperties(object);
		}
		else if ( object instanceof infrastructure.Hub
				|| object instanceof infrastructure.Switch
				|| object instanceof infrastructure.Router )
		{
			showInfrastructurProperties(object);
		}
		

		this.add(createButtons());
		
		// Lay out the panel.
		graphics.GraphicalFunctions.make1xGrid(this, (compCount*2)+1, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

	}

	
	/**
	 * TODO - Description
	 * 
	 */
	private void showStandardButtons(Object object)
	{
		GeneralPropertiesView.getGeneralObjectProperties(this, object);
	}


	
	/**
	 * TODO - Description
	 * 
	 */
	private void showDesktopProperties(Object object)
	{ 
		ClientsPropertiesView.getClientsPropertiesView(this, object);
	}

	/**
	 * TODO - Description
	 * 
	 */
	private void showServerProperties(Object object)
	{
		ServersPropertiesView.getServersPropertiesView(this, object);
	}

	private void showPeripheralProperties(Object object)
	{
		PeripheralsPropertiesView.getPeripheralsPropertiesView(this, object);
	}

	private void showInfrastructurProperties(Object object)
	{
		InfrastructuresPropertiesView.getInfrastructuresPropertiesView(this, object);
	}
	
	
	
	/**
	 * Creates a JPanel with two buttons that are listened for by actionlisteners.
	 * 
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");


		buttons.add(save);

		return buttons;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			
		}
	}
}
