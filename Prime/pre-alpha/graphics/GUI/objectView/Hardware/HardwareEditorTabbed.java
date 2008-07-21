/**
 * 
 */
package graphics.GUI.objectView.Hardware;


import graphics.GUI.objectView.Hardware.HardwareView.CPUView;
import graphics.GUI.objectView.Hardware.HardwareView.DiscDriveView;
import graphics.GUI.objectView.Hardware.HardwareView.ExternaNICView;
import graphics.GUI.objectView.Hardware.HardwareView.GraphicsCardView;
import graphics.GUI.objectView.Hardware.HardwareView.HDDView;
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
}
