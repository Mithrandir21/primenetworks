/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import java.awt.Component;

import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Views.CPUView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.DiscDriveView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.ExternaNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.GraphicsCardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HDDView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.InternalNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.MotherboardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.RAMView;

import javax.swing.JTabbedPane;

import managment.ComponentsManagment;

import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectViewTabbed extends JTabbedPane
{
	private Object mainobj;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public ObjectViewTabbed(Object obj)
	{
		 mainobj = obj;
		populateTabs(obj);
	}



	/**
	 * Creates and adds tabs to this JTabbedPane instance based on the class of the internal
	 * components of the given object, such as Motherboard, CPU or RAM.
	 * 
	 * @param obj
	 *            The object that holds the internal components which in turn are the basis for the
	 *            creation of the hardware views.
	 */
	public void populateTabs(Object obj)
	{
		Object[] components = obj.getComponents();


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] instanceof Motherboard )
			{
				String MBDesc = "Motherboard options and values";
				this.addTab("Motherboard", null, new MotherboardView(obj,
						(Motherboard) components[i]), MBDesc);
			}
			else if ( components[i] instanceof CPU )
			{
				String CPUDesc = "CPU options and values";
				this.addTab("CPU", null, new CPUView(obj, (CPU) components[i]),
						CPUDesc);
			}
			else if ( components[i] instanceof HDD )
			{
				String HDDDesc = "Harddisk options and values";
				this.addTab("Harddisc", null, new HDDView(obj,
						(HDD) components[i]), HDDDesc);
			}
			else if ( components[i] instanceof Ram )
			{
				String RAMDesc = "RAM options and values";
				this.addTab("RAM", null, new RAMView(obj, (Ram) components[i]),
						RAMDesc);
			}
			else if ( components[i] instanceof Discdrive )
			{
				String DDDesc = "Discdrive options and values";
				this.addTab("Discdrive", null, new DiscDriveView(obj,
						(Discdrive) components[i]), DDDesc);
			}
			else if ( components[i] instanceof GraphicsCard )
			{
				String GPUDesc = "Graphics options and values";
				this.addTab("Graphical Card", null, new GraphicsCardView(obj,
						(GraphicsCard) components[i]), GPUDesc);
			}
			else if ( components[i] instanceof InternalNetworksCard )
			{
				String IntNICDesc = "Networkcard options and values";
				this.addTab("Internal NIC", null, new InternalNICView(obj,
						(InternalNetworksCard) components[i]), IntNICDesc);
			}
			else if ( components[i] instanceof ExternalNetworksCard )
			{
				String ExtNICDesc = "External Networkcard options and values";
				this.addTab("External NIC", null, new ExternaNICView(obj,
						(ExternalNetworksCard) components[i]), ExtNICDesc);
			}
		}
	}
	
	
	/**
	 * This method calls the save methods on all the different HardwareViews and if the boolean
	 * given is true, calls also the validation methods on all views. If any of the validations
	 * fail, none off the save methods will be called.
	 * 
	 * @param verify
	 *            Boolean saying if validation should be run on the data.
	 * @return If the save methods in each of the views does not return false, which would mean that
	 *         it did not save, the method will return true. Else it will return false;
	 */
	public boolean save(boolean verify)
	{
		boolean validationFailed = false;


		// If verify is set, the views will be validated.
		if ( verify )
		{
			// Boolean array that contains the validation status of each view.
			boolean[] verified = new boolean[this.getComponentCount()];

			// FIXME
			/**
			 * Goes through all the views and gets the validation status of each one and places that
			 * boolean in the validation array.
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				verified[i] = ((HardwareViewInterface) comp)
						.validateNecessaryData();
			}

			/**
			 * If any of the validation function in any of the views return false the loop will end
			 * and none of the values for any of the views will be saved. The JFrame will also not
			 * exit so the user has a chance to change the value.
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
			 * Goes through all the views and saves the values since none of the views failed its
			 * validation.
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				((HardwareViewInterface) comp).save();
			}

			// // The motherboard save.
			// Component comp = this.getComponent(0);
			// ((HardwareView) comp).save();


			ComponentsManagment.processAllChanges(mainobj);


			// Updates the views of the object to correctly show the
			// current info.
			ObjectView view = PrimeMain1.getObjectView(mainobj);
			if ( view != null )
			{
				view.updateViewInfo();
			}

			// Returns a boolean showing that everything i saved.
			return true;
		}
		// Atleast one of the validations have failed.
		else
		{
			/**
			 * Shows that at least one of the validations have failed and that nothing has been
			 * saved.
			 */
			return false;
		}
	}
}
