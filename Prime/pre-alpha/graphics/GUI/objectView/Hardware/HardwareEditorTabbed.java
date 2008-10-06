/**
 * 
 */
package graphics.GUI.objectView.Hardware;


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

import java.awt.Component;

import javax.swing.JTabbedPane;

import objects.Object;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HardwareEditorTabbed extends JTabbedPane
{

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public HardwareEditorTabbed(Object obj)
	{
		populateTabs(obj);
	}


	/**
	 * Creates and adds tabs to this JTabbedPane instance based on the class of
	 * the internal components of the given object, such as Motherboard, CPU or
	 * RAM.
	 * 
	 * @param obj
	 *            The object that holds the internal components which in turn
	 *            are the basis for the creation of the hardware views.
	 */
	public void populateTabs(Object obj)
	{
		Object[] components = obj.getComponents();


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] instanceof Motherboard )
			{
				String MBDesc = "General information and option";
				this.addTab("Motherboard", null, new MotherboardView(obj,
						(Motherboard) components[i]), MBDesc);
			}
			else if ( components[i] instanceof CPU )
			{
				String CPUDesc = "General information and option";
				this.addTab("CPU", null, new CPUView(obj, (CPU) components[i]), CPUDesc);
			}
			else if ( components[i] instanceof HDD )
			{
				String HDDDesc = "General information and option";
				this.addTab("Harddisc", null, new HDDView(obj, (HDD) components[i]), HDDDesc);
			}
			else if ( components[i] instanceof Ram )
			{
				String RAMDesc = "General information and option";
				this.addTab("RAM", null, new RAMView(obj, (Ram) components[i]), RAMDesc);
			}
			else if ( components[i] instanceof Discdrive )
			{
				String DDDesc = "General information and option";
				this.addTab("Discdrive", null, new DiscDriveView(obj, (Discdrive) components[i]),
						DDDesc);
			}
			else if ( components[i] instanceof GraphicsCard )
			{
				String GPUDesc = "General information and option";
				this.addTab("Graphical Card", null, new GraphicsCardView(obj,
						(GraphicsCard) components[i]), GPUDesc);
			}
			else if ( components[i] instanceof InternalNetworksCard )
			{
				String IntNICDesc = "General information and option";
				this.addTab("Internal NIC", null, new InternalNICView(obj,
						(InternalNetworksCard) components[i]), IntNICDesc);
			}
			else if ( components[i] instanceof ExternalNetworksCard )
			{
				String ExtNICDesc = "General information and option";
				this.addTab("External NIC", null, new ExternaNICView(obj,
						(ExternalNetworksCard) components[i]), ExtNICDesc);
			}
		}
	}



	/**
	 * This method calls the save methods on all the different HardwareViews and
	 * if the boolean given is true, calls also the validation methods on all
	 * views. If any of the validations fail, none save methods will be called.
	 * 
	 * @param verify
	 *            Boolean saying if validation should be run on the data.
	 * @return If the save methods in each of the views does not return false,
	 *         which would mean that it did not save, the method will return
	 *         true. Else it will return false;
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
			 * Goes through all the views and gets the validation status of each
			 * one and places that boolean in the validation array.
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				verified[i] = ((HardwareView) comp).validateNecessaryData();
			}

			/**
			 * If any of the validation function in any of the views return
			 * false the loop will end and none of the values for any of the
			 * views will be saved. The JFrame will also not exit so the user
			 * has a chance to change the value.
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
			 * Goes through all the views and saves the values since none of the
			 * views failed its validation. All except the motherboard which
			 * will be saved last.
			 */
			for ( int i = 1; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				((HardwareView) comp).save();
			}

			// The motherboard save.
			Component comp = this.getComponent(0);
			((HardwareView) comp).save();


			// Returns a boolean showing that everything i saved.
			return true;
		}
		// Atleast one of the validations have failed.
		else
		{
			/**
			 * Shows that at least one of the validations have failed and that
			 * nothing has been saved.
			 */
			return false;
		}
	}
}
