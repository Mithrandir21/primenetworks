/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain1;
import graphics.GUI.objectView.Hardware.HardwareView.Views.CPUView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.DiscDriveView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.ExternaNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.GraphicsCardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HDDView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.InternalNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.MotherboardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.RAMView;

import javax.swing.JTabbedPane;

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
	public ObjectViewTabbed()
	{
		// mainobj = obj;
		populateTabs(PrimeMain1.objectlist.get(1));
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
}
