/**
 * 
 */
package graphics.GUI.properties.objectTypes;

import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class GeneralPropertiesView
{

	/**
	 * TODO - Description
	 * 
	 */
	public static void getGeneralCanvasProperties(JPanel panel, WorkareaCanvas canvas)
	{
		Dimension tfSize = new Dimension(5, 20);
		
		
		// Name
		JLabel nameLabel = new JLabel("Name", SwingConstants.TRAILING);
		nameLabel.setToolTipText("The name of the network map.");
		panel.add(nameLabel);
		
		JTextField nameField = new JTextField(canvas.getCanvasName());
		nameField.setMaximumSize(tfSize);
		nameField.setPreferredSize(tfSize);
		nameLabel.setLabelFor(nameField);
		nameLabel.setName("Name Canvas");
		panel.add(nameField);
		
		
		
		// The number of objects on the network map.
		JLabel objectCountLabel = new JLabel("Objects count", SwingConstants.TRAILING);
		objectCountLabel.setToolTipText("The number of objects in the network.");
		panel.add(objectCountLabel);
		
		JTextField objectCountField = new JTextField("" + canvas.getNumberOfWidgetsOnTheScene());
		objectCountField.setMaximumSize(tfSize);
		objectCountField.setPreferredSize(tfSize);
		objectCountField.setEditable(false);
		objectCountLabel.setLabelFor(objectCountField);
		panel.add(objectCountField);
		
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public static void getGeneralObjectProperties(JPanel panel, Object obj)
	{
		Dimension tfSize = new Dimension(5, 20);
		
		
		// Name
		JLabel nameLabel = new JLabel("Name",SwingConstants.TRAILING);
		nameLabel.setToolTipText("The name of the device.");
		panel.add(nameLabel);
		
		JTextField nameField = new JTextField(obj.getObjectName());
		nameField.setMaximumSize(tfSize);
		nameField.setPreferredSize(tfSize);
		nameField.setName("Name Object");
		nameLabel.setLabelFor(nameField);
		panel.add(nameField);


		// Supported connection interfaces
		JLabel supConIntLabel = new JLabel("Supported Connection Interfaces", SwingConstants.TRAILING);
		supConIntLabel.setToolTipText("The types of connections the device is capable"
				+ " of connecting to, like USB og RJ-45.");
		panel.add(supConIntLabel);
		
		JTextField supConIntField = new JTextField(10);
		supConIntField.setMaximumSize(tfSize);
		supConIntField.setPreferredSize(tfSize);
		supConIntField.setName("supConInt");
		supConIntLabel.setLabelFor(supConIntField);
		panel.add(supConIntField);
		
		

		// Number of components
		JLabel numConLabel = new JLabel("Number of devices connected", SwingConstants.TRAILING);
		numConLabel.setToolTipText("The number of devices connected to this devices.");
		panel.add(numConLabel);
		
		JTextField numConField = new JTextField(Integer.toString((obj.getNumberOfConnectedDevices())));
		numConField.setEditable(false);
		numConField.setMaximumSize(tfSize);
		numConField.setPreferredSize(tfSize);
		numConField.setName("numCon");
		numConLabel.setLabelFor(numConField);
		panel.add(numConField);
		

		// Number of nodes
		JLabel numbJumpsLabel = new JLabel("Number of jumps", SwingConstants.TRAILING);
		numbJumpsLabel
				.setToolTipText("The numbers of devices between this device and a router outside"
						+ " of this system.");
		panel.add(numbJumpsLabel);

		JTextField numbJumpsField = new JTextField("0");
		numbJumpsField.setEditable(false);
		numbJumpsField.setMaximumSize(tfSize);
		numbJumpsField.setPreferredSize(tfSize);
		numbJumpsField.setName("numbJumps");
		numbJumpsLabel.setLabelFor(numbJumpsField);
		panel.add(numbJumpsField);
	}
	
}
