/**
 * 
 */
package graphics.GUI.objectView.Hardware;


import java.awt.Component;

import graphics.GUI.objectView.Hardware.HardwareView.CPUView;
import graphics.GUI.objectView.Hardware.HardwareView.DiscDriveView;
import graphics.GUI.objectView.Hardware.HardwareView.ExternaNICView;
import graphics.GUI.objectView.Hardware.HardwareView.GraphicsCardView;
import graphics.GUI.objectView.Hardware.HardwareView.HDDView;
import graphics.GUI.objectView.Hardware.HardwareView.HardwareView;
import graphics.GUI.objectView.Hardware.HardwareView.InternalNICView;
import graphics.GUI.objectView.Hardware.HardwareView.MotherboardView;
import graphics.GUI.objectView.Hardware.HardwareView.RAMView;
import hardware.CPU;
import hardware.Discdrive;
import hardware.ExternalNetworksCard;
import hardware.GraphicsCard;
import hardware.HDD;
import hardware.InternalNetworksCard;
import hardware.Motherboard;
import hardware.Ram;

import javax.swing.JTabbedPane;

import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HardwareEditorTabbed extends JTabbedPane
{

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param components
	 */
	public HardwareEditorTabbed(Object[] components)
	{
		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] instanceof Motherboard )
			{
				String MBDesc = "General information and option";
				this.addTab("Motherboard", null, new MotherboardView((Motherboard) components[i]),
						MBDesc);
			}
			else if ( components[i] instanceof CPU )
			{
				String CPUDesc = "General information and option";
				this.addTab("CPU", null, new CPUView((CPU) components[i]), CPUDesc);
			}
			else if ( components[i] instanceof HDD )
			{
				String HDDDesc = "General information and option";
				this.addTab("Harddisc", null, new HDDView((HDD) components[i]), HDDDesc);
			}
			else if ( components[i] instanceof Ram )
			{
				String RAMDesc = "General information and option";
				this.addTab("RAM", null, new RAMView((Ram) components[i]), RAMDesc);
			}
			else if ( components[i] instanceof Discdrive )
			{
				String DDDesc = "General information and option";
				this
						.addTab("Discdrive", null, new DiscDriveView((Discdrive) components[i]),
								DDDesc);
			}
			else if ( components[i] instanceof GraphicsCard )
			{
				String GPUDesc = "General information and option";
				this.addTab("Graphical Card", null, new GraphicsCardView(
						(GraphicsCard) components[i]), GPUDesc);
			}
			else if ( components[i] instanceof InternalNetworksCard )
			{
				String IntNICDesc = "General information and option";
				this.addTab("Internal NIC", null, new InternalNICView(
						(InternalNetworksCard) components[i]), IntNICDesc);
			}
			else if ( components[i] instanceof ExternalNetworksCard )
			{
				String ExtNICDesc = "General information and option";
				this.addTab("External NIC", null, new ExternaNICView(
						(ExternalNetworksCard) components[i]), ExtNICDesc);
			}
		}
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public boolean save(boolean verify)
	{
		boolean validationFailed = false;
		
		
		// If verify is set, the views will be validated.
		if ( verify )
		{
			// Boolean array that contains the validation status of each view.
			boolean[] verified = new boolean[this.getComponentCount()];
			/**
			 * Goes through all the views and gets the validation status 
			 * of each one and places that boolean in the validation array. 
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				verified[i] = ((HardwareView) comp).validateData();
			}
			/**
			 * If any of the validation function in any of the views return 
			 * false the loop will end and none of the values will be saved.
			 * The JFrame will also not exit so the user has a chance to
			 * change the value.
			 */
			for ( int i = 0; i < verified.length; i++ )
			{
				if ( verified[i] == false )
				{
					validationFailed = true;
					i = verified.length;
				}
			}
		}		
		// Checks if any of the views failed its validation.
		if ( validationFailed == false )
		{
			/**
			 * Goes through all the views and saves the values since
			 * none of the views failed its validation. 
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				((HardwareView) comp).save();
			}
			
			// Returns a boolean showing that everything i saved.
			return true;
		}
		// Atleast one of the validations have failed.
		else
		{
			/**
			 * Shows that atleast one of the validations have failed
			 * and that nothing has been saved. 
			 */
			return false;
		}
	}
}
