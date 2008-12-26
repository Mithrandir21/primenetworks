/**
 * 
 */
package graphics.GUI.properties;


import graphics.GUI.SpringUtilities;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Color;
import java.awt.Dimension;

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
public class ObjectProperties extends JPanel
{
	Object objectViewed = null;


	int numPairs = 0;


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



	public ObjectProperties()
	{
		init();
	}


	public ObjectProperties(WorkareaCanvas canvas)
	{
		this.setLayout(new SpringLayout());

		JLabel canvasLabels[] = new JLabel[3];


		JLabel temp = null;

		// Index 0 - Name
		temp = new JLabel("Name", SwingConstants.TRAILING);
		temp.setName(temp.getText());
		temp.setToolTipText("The name of the network map.");
		canvasLabels[0] = temp;

		this.add(canvasLabels[0]);
		JTextField textField0 = new JTextField(canvas.getCanvasName());
		Dimension tfSize0 = new Dimension(5, 20);
		textField0.setMaximumSize(tfSize0);
		textField0.setPreferredSize(tfSize0);
		canvasLabels[0].setLabelFor(textField0);
		this.add(textField0);

		temp = null;


		// Index 1 - The number of objects on the network map.
		temp = new JLabel("Objects count", SwingConstants.TRAILING);
		temp.setName(temp.getText());
		temp.setToolTipText("The number of objects in the network.");
		canvasLabels[1] = temp;

		this.add(canvasLabels[1]);
		JTextField textField1 = new JTextField(""
				+ canvas.getNumberOfWidgetsOnTheScene());
		Dimension tfSize1 = new Dimension(5, 20);
		textField1.setMaximumSize(tfSize1);
		textField1.setPreferredSize(tfSize1);
		canvasLabels[0].setLabelFor(textField1);
		this.add(textField1);

		temp = null;



		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, 4, 1, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
	}



	private void init()
	{
		JLabel temp = null;

		// -------------------- Object --------------------
		// Index 0 - Name
		temp = new JLabel("Name");
		temp.setName(temp.getText());
		temp.setToolTipText("The name of the device.");
		standardLabels[0] = temp;

		temp = null;

		// // Index 1 - Description
		// temp = new JLabel("Description");
		// temp.setName(temp.getText());
		// temp.setToolTipText("The description of the device.");
		// standardLabels[1] = temp;
		//
		// temp = null;

		// Index 1 - Description
		temp = new JLabel("Supported Connection Interfaces");
		temp.setName(temp.getText());
		temp.setToolTipText("The types of connections the device is capable"
				+ " of connecting to, like USB og RJ-45.");
		standardLabels[1] = temp;

		temp = null;

		// Index 2 - Number of components
		temp = new JLabel("Number of devices connected");
		temp.setName(temp.getText());
		temp.setToolTipText("The number of devices connected to this devices.");
		standardLabels[2] = temp;

		temp = null;

		// Index 3 - Number of nodes
		temp = new JLabel("Number of jumps");
		temp.setName(temp.getText());
		temp
				.setToolTipText("The numbers of devices between this device and a router outside"
						+ " of this system.");
		standardLabels[3] = temp;

		temp = null;
		// /////////////////////////////////////////////////



		// -------------------- Clients --------------------
		// Index 0 - Desktop Rate
		temp = new JLabel("Desktop Rate");
		temp.setName(temp.getText());
		temp.setToolTipText("The desktop rating...");
		clientsLabels[0] = temp;

		temp = null;
		// /////////////////////////////////////////////////



		// -------------------- Servers --------------------
		// Index 0 - Supports On-Site-Access
		temp = new JLabel("Supports On-Site-Access");
		temp.setName(temp.getText());
		temp.setToolTipText("Whether or not this device supports "
				+ "on-site-access, i.e. it has a keyboard, mouse and screen.");
		serversLabels[0] = temp;

		temp = null;

		// Index 1 - supportsRemoteAccess
		temp = new JLabel("Supports Remote Access");
		temp.setName(temp.getText());
		temp
				.setToolTipText("Whether or not this device supports remote access.");
		serversLabels[1] = temp;

		temp = null;

		// Index 2 - Supported Remote Access Protocols
		temp = new JLabel("Supported Remote Protocols");
		temp.setName(temp.getText());
		temp.setToolTipText("The supported remote access protocols.");
		serversLabels[2] = temp;

		temp = null;

		// Index 3 - Software name
		temp = new JLabel("Software Name");
		temp.setName(temp.getText());
		temp
				.setToolTipText("The name of the main software running on this device.");
		serversLabels[3] = temp;

		temp = null;
		// /////////////////////////////////////////////////


		// -------------------- Peripherals --------------------

		// /////////////////////////////////////////////////



	}




	public ObjectProperties(Object object)
	{
		init();

		objectViewed = object;

		// Class g = new Class();

		// Desktop ffa = ObjectDefiner.defineObjectClass(object);

		// this.setPreferredSize(new Dimension(200,500));
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


		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, numPairs * 2, 1, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
	}

	private void showStandardButtons(Object object)
	{
		numPairs = standardLabels.length;

		// Name
		JLabel l = new JLabel(standardLabels[0].getText(),
				SwingConstants.TRAILING);
		this.add(l);
		JTextField textField = new JTextField(object.getObjectName());
		Dimension tfSize = new Dimension(5, 20);
		textField.setMaximumSize(tfSize);
		textField.setPreferredSize(tfSize);
		l.setLabelFor(textField);
		this.add(textField);

		// Sup int
		l = new JLabel(standardLabels[1].getText(), SwingConstants.TRAILING);
		this.add(l);
		textField = new JTextField(10);
		textField.setMaximumSize(tfSize);
		textField.setPreferredSize(tfSize);
		l.setLabelFor(textField);
		this.add(textField);

		// Number of comp
		l = new JLabel(standardLabels[2].getText(), SwingConstants.TRAILING);
		this.add(l);
		textField = new JTextField(Integer.toString((object
				.getNumberOfConnectedDevices())));
		textField.setEditable(false);
		textField.setMaximumSize(tfSize);
		textField.setPreferredSize(tfSize);
		l.setLabelFor(textField);
		this.add(textField);

		// Number of jumps
		l = new JLabel(standardLabels[3].getText(), SwingConstants.TRAILING);
		this.add(l);
		textField = new JTextField("0");
		textField.setEditable(false);
		textField.setMaximumSize(tfSize);
		textField.setPreferredSize(tfSize);
		l.setLabelFor(textField);
		this.add(textField);
	}


	private void showDesktopProperties(Object object)
	{
		numPairs += clientsLabels.length;

		String[] rates = new String[20];
		int temp = 5;

		for ( int i = 0; i < rates.length; i++ )
		{
			rates[i] = Integer.toString(temp);
			temp = temp + 5;
		}


		JComboBox comboBox = new JComboBox(rates);
		comboBox.setBackground(Color.white);
		comboBox.setEditable(false);
		Dimension tfSize = new Dimension(5, 20);
		comboBox.setMaximumSize(tfSize);
		comboBox.setPreferredSize(tfSize);


		JLabel l = new JLabel(clientsLabels[0].getText(),
				SwingConstants.TRAILING);
		this.add(l);
		this.add(comboBox);
	}

	private void showServerProperties(Object object)
	{
		numPairs += serversLabels.length;

		// Create and populate the panel.
		for ( int i = 0; i < serversLabels.length; i++ )
		{
			JLabel l = new JLabel(serversLabels[i].getText(),
					SwingConstants.TRAILING);
			this.add(l);
			JTextField textField = new JTextField(10);
			Dimension tfSize = new Dimension(5, 20);
			textField.setMaximumSize(tfSize);
			textField.setPreferredSize(tfSize);
			l.setLabelFor(textField);
			this.add(textField);
		}
	}

	private void showPeripheralProperties(Object object)
	{
		numPairs += peripheralsLabels.length;

		// Create and populate the panel.
		for ( int i = 0; i < peripheralsLabels.length; i++ )
		{
			JLabel l = new JLabel(peripheralsLabels[i].getText(),
					SwingConstants.TRAILING);
			this.add(l);
			JTextField textField = new JTextField(10);
			Dimension tfSize = new Dimension(5, 20);
			textField.setMaximumSize(tfSize);
			textField.setPreferredSize(tfSize);
			l.setLabelFor(textField);
			this.add(textField);
		}
	}

	private void showInfrastructurProperties(Object object)
	{
		numPairs += infrastructuresLabels.length;

		// Create and populate the panel.
		for ( int i = 0; i < infrastructuresLabels.length; i++ )
		{
			JLabel l = new JLabel(infrastructuresLabels[i].getText(),
					SwingConstants.TRAILING);
			this.add(l);
			JTextField textField = new JTextField(10);
			Dimension tfSize = new Dimension(5, 20);
			textField.setMaximumSize(tfSize);
			textField.setPreferredSize(tfSize);
			l.setLabelFor(textField);
			this.add(textField);
		}
	}

	private void showConnectionsProperties(Object object)
	{

	}
}
