package graphics.GUI.objectView.Software;

import graphics.GUI.objectView.Hardware.HardwareView.Views.CPUView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.DiscDriveView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.ExternaNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.GraphicsCardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HDDView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.InternalNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.MotherboardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.RAMView;
import hardware.CPU;
import hardware.Discdrive;
import hardware.ExternalNetworksCard;
import hardware.GraphicsCard;
import hardware.HDD;
import hardware.InternalNetworksCard;
import hardware.Ram;

import javax.swing.JTabbedPane;

import objects.Object;
import objects.Software;
import software.Antivirus;
import software.Firewall;
import software.OperatingSystem;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class SoftwareEditorTabbed extends JTabbedPane
{

	private Object mainobj;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public SoftwareEditorTabbed(Object obj)
	{
		mainobj = obj;
		populateTabs(obj);
	}
	
	
	/**
	 * Creates and adds tabs to this JTabbedPane instance based on the class of
	 * the software on the given object, such as {@link OperatingSystem}, {@link Antivirus} or
	 * {@link Firewall}.
	 * 
	 * @param obj
	 *            The object that holds the software which in turn
	 *            are the basis for the creation of the hardware views.
	 */
	public void populateTabs(Object obj)
	{
		Software[] software = obj.getSoftware();
		
		
		for ( int i = 0; i < software.length; i++ )
		{
			if ( software[i] instanceof Antivirus )
			{
				String MBDesc = "General information and option";
				this.addTab("Motherboard", null, new MotherboardView(obj,
						(Antivirus) software[i]), MBDesc);
			}
			else if ( software[i] instanceof CPU )
			{
				String CPUDesc = "General information and option";
				this.addTab("CPU", null, new CPUView(obj, (CPU) software[i]), CPUDesc);
			}
			else if ( software[i] instanceof HDD )
			{
				String HDDDesc = "General information and option";
				this.addTab("Harddisc", null, new HDDView(obj, (HDD) software[i]), HDDDesc);
			}
			else if ( software[i] instanceof Ram )
			{
				String RAMDesc = "General information and option";
				this.addTab("RAM", null, new RAMView(obj, (Ram) software[i]), RAMDesc);
			}
			else if ( software[i] instanceof Discdrive )
			{
				String DDDesc = "General information and option";
				this.addTab("Discdrive", null, new DiscDriveView(obj, (Discdrive) software[i]),
						DDDesc);
			}
			else if ( software[i] instanceof GraphicsCard )
			{
				String GPUDesc = "General information and option";
				this.addTab("Graphical Card", null, new GraphicsCardView(obj,
						(GraphicsCard) software[i]), GPUDesc);
			}
			else if ( software[i] instanceof InternalNetworksCard )
			{
				String IntNICDesc = "General information and option";
				this.addTab("Internal NIC", null, new InternalNICView(obj,
						(InternalNetworksCard) software[i]), IntNICDesc);
			}
			else if ( software[i] instanceof ExternalNetworksCard )
			{
				String ExtNICDesc = "General information and option";
				this.addTab("External NIC", null, new ExternaNICView(obj,
						(ExternalNetworksCard) software[i]), ExtNICDesc);
			}
		}
		
	}
	
	
}
