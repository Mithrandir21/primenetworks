/**
 * 
 */
package graphics.GUI.objectView.Hardware.NewComponent;


import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.Hardware.HardwareObjectView;
import hardware.CPU;
import hardware.Discdrive;
import hardware.ExternalNetworksCard;
import hardware.GraphicsCard;
import hardware.HDD;
import hardware.InternalNetworksCard;
import hardware.Motherboard;
import hardware.Ram;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NewComponentsView extends JPanel implements MouseListener
{
	JPanel mbPanel = null;
	JPanel cpuPanel = null;
	JPanel hddPanel = null;
	JPanel ramPanel = null;
	JPanel discPanel = null;
	JPanel gpuPanel = null;
	JPanel intNICPanel = null;
	JPanel extNICPanel = null;
	
	

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 */
	public NewComponentsView()
	{
		this.setLayout(new GridLayout(0, 2, 3, 5));





		ImageIcon mbtemp = ImageLocator.getImageIconObject("Motherboard");

		String[] mbinfo = new String[5];

		Motherboard mbObj = PrimeMain1.standard_internal_components.getSt_MB();

		String text = null;

		text = mbObj.getObjectName();
		if ( text != "" && text != null )
		{
			mbinfo[0] = text;
		}

		text = mbObj.getForm();
		if ( text != "" && text != null )
		{
			mbinfo[1] = "Form: " + text;
		}

		text = mbObj.getSocket();
		if ( text != "" && text != null )
		{
			mbinfo[2] = "Socket: " + text;
		}

		text = mbObj.getGraphicalPort();
		if ( text != "" && text != null )
		{
			mbinfo[3] = "GPU slot: " + text;
		}

		text = mbObj.getDescription();
		if ( text != "" && text != null )
		{
			mbinfo[4] = text;
		}

		assert mbtemp != null;

		// Create motherboard JPanel
		mbPanel = HardwareObjectView.createHardwareJPanel(mbinfo, mbtemp);
		mbPanel.addMouseListener(this);
		mbPanel.setName("Motherboard");




		// CPU
		ImageIcon cputemp = ImageLocator.getImageIconObject("CPU");

		CPU cpuObj = PrimeMain1.standard_internal_components.getSt_CPU();

		String[] cpuinfo = new String[4];

		text = cpuObj.getObjectName();
		if ( text != "" && text != null )
		{
			cpuinfo[0] = text;
		}

		text = cpuObj.getProducer();
		if ( text != "" && text != null )
		{
			cpuinfo[1] = "Producer: " + text;
		}

		text = cpuObj.getSocket();
		if ( text != "" && text != null )
		{
			cpuinfo[2] = "Socket: " + text;
		}

		text = cpuObj.getDescription();
		if ( text != "" && text != null )
		{
			cpuinfo[3] = text;
		}


		assert cputemp != null;

		// Create cpu JPanel
		cpuPanel = HardwareObjectView.createHardwareJPanel(cpuinfo, cputemp);
		cpuPanel.addMouseListener(this);
		cpuPanel.setName("CPU");



		// HDD
		ImageIcon hddtemp = ImageLocator.getImageIconObject("Harddisc");

		HDD hddObj = PrimeMain1.standard_internal_components.getSt_HDD();

		String[] hddinfo = new String[5];

		text = hddObj.getObjectName();
		if ( text != "" && text != null )
		{
			hddinfo[0] = text;
		}

		text = hddObj.getProducer();
		if ( text != "" && text != null )
		{
			hddinfo[1] = "Producer: " + text;
		}

		text = hddObj.getSubtype();
		if ( text != "" && text != null )
		{
			hddinfo[2] = "Type: " + text;
		}

		if ( hddObj.getSize() != 0 )
		{
			hddinfo[3] = "Size: " + Integer.toString(hddObj.getSize());
		}

		text = hddObj.getDescription();
		if ( text != "" && text != null )
		{
			hddinfo[4] = text;
		}

		assert hddtemp != null;

		// Create cpu JPanel
		hddPanel = HardwareObjectView.createHardwareJPanel(hddinfo, hddtemp);
		hddPanel.addMouseListener(this);
		hddPanel.setName("HDD");



		// RAM
		ImageIcon ramtemp = ImageLocator.getImageIconObject("RAM");

		Ram ramObj = PrimeMain1.standard_internal_components.getSt_RAM();

		String[] raminfo = new String[5];

		text = ramObj.getObjectName();
		if ( text != "" && text != null )
		{
			raminfo[0] = text;
		}

		text = ramObj.getProducer();
		if ( text != "" && text != null )
		{
			raminfo[1] = "Producer: " + text;
		}

		text = ramObj.getSubtype();
		if ( text != "" && text != null )
		{
			raminfo[2] = "Type: " + text;
		}

		if ( ramObj.getSize() != 0 )
		{
			raminfo[3] = "Size: " + Integer.toString(ramObj.getSize());
		}

		text = ramObj.getDescription();
		if ( text != "" && text != null )
		{
			raminfo[4] = text;
		}

		assert ramtemp != null;

		// Create ram JPanel
		ramPanel = HardwareObjectView.createHardwareJPanel(raminfo, ramtemp);
		ramPanel.addMouseListener(this);
		ramPanel.setName("RAM");



		// Discdrive
		ImageIcon disctemp = ImageLocator.getImageIconObject("Optical-Drive");

		Discdrive discObj = PrimeMain1.standard_internal_components.getSt_DVDRW();

		String[] discinfo = new String[5];

		text = discObj.getObjectName();
		if ( text != "" && text != null )
		{
			discinfo[0] = text;
		}

		text = discObj.getProducer();
		if ( text != "" && text != null )
		{
			discinfo[1] = "Producer: " + text;
		}

		text = discObj.getSubtype();
		if ( text != "" && text != null )
		{
			discinfo[2] = "Type: " + text;
		}

		if ( discObj.getSpeed() != 0 )
		{
			discinfo[3] = "Speed: " + Integer.toString(discObj.getSpeed());
		}

		text = discObj.getDescription();
		if ( text != "" && text != null )
		{
			discinfo[4] = text;
		}

		assert disctemp != null;

		// Create discdrive JPanel
		discPanel = HardwareObjectView.createHardwareJPanel(discinfo, disctemp);
		discPanel.addMouseListener(this);
		discPanel.setName("Discdrive");



		// GPU
		ImageIcon gputemp = ImageLocator.getImageIconObject("GPU");

		GraphicsCard gpuObj = PrimeMain1.standard_internal_components.getSt_GPU();

		String[] gpuinfo = new String[5];

		text = gpuObj.getObjectName();
		if ( gpuObj.getObjectName() != "" && gpuObj.getObjectName() != null )
		{
			gpuinfo[0] = gpuObj.getObjectName();
		}

		text = gpuObj.getProducer();
		if ( text != "" && text != null )
		{
			gpuinfo[1] = "Producer: " + text;
		}

		text = gpuObj.getOutputInterface();
		if ( text != "" && text != null )
		{
			gpuinfo[2] = "Output: " + text;
		}

		if ( gpuObj.getSpeed() != 0 )
		{
			gpuinfo[3] = "Speed: " + Integer.toString(gpuObj.getSpeed());
		}

		text = gpuObj.getDescription();
		if ( text != "" && text != null )
		{
			gpuinfo[4] = text;
		}

		assert gputemp != null;

		// Create discdrive JPanel
		gpuPanel = HardwareObjectView.createHardwareJPanel(gpuinfo, gputemp);
		gpuPanel.addMouseListener(this);
		gpuPanel.setName("GPU");




		// intNIC
		ImageIcon intNICtemp = ImageLocator.getImageIconObject("NIC");

		InternalNetworksCard intNICObj = PrimeMain1.standard_internal_components.getSt_IntNIC();

		String[] intNICinfo = new String[5];

		text = intNICObj.getObjectName();
		if ( text != "" && text != null )
		{
			intNICinfo[0] = text;
		}

		text = intNICObj.getProducer();
		if ( text != "" && text != null )
		{
			intNICinfo[1] = "Producer: " + text;
		}

		text = intNICObj.getProtocol();
		if ( text != "" && text != null )
		{
			intNICinfo[2] = "Output: " + text;
		}

		if ( intNICObj.getSpeed() != 0 )
		{
			intNICinfo[3] = "Speed: " + Integer.toString(intNICObj.getSpeed());
		}

		text = intNICObj.getDescription();
		if ( text != "" && text != null )
		{
			intNICinfo[4] = text;
		}

		assert intNICtemp != null;

		// Create internal NIC JPanel
		intNICPanel = HardwareObjectView.createHardwareJPanel(intNICinfo, intNICtemp);
		intNICPanel.addMouseListener(this);
		intNICPanel.setName("Int NIC");




		// extNIC
		ImageIcon extNICtemp = ImageLocator.getImageIconObject("NIC");

		ExternalNetworksCard extNICObj = PrimeMain1.standard_internal_components.getSt_ExtNIC();

		String[] extNICinfo = new String[5];

		text = extNICObj.getObjectName();
		if ( text != "" && text != null )
		{
			extNICinfo[0] = text;
		}

		text = extNICObj.getProducer();
		if ( text != "" && text != null )
		{
			extNICinfo[1] = "Producer: " + text;
		}

		text = extNICObj.getProtocol();
		if ( text != "" && text != null )
		{
			extNICinfo[2] = "Output: " + text;
		}

		if ( extNICObj.getSpeed() != 0 )
		{
			extNICinfo[3] = "Speed: " + Integer.toString(extNICObj.getSpeed());
		}

		text = extNICObj.getDescription();
		if ( text != "" && text != null )
		{
			extNICinfo[4] = text;
		}

		assert extNICtemp != null;

		// Create external NIC JPanel
		extNICPanel = HardwareObjectView.createHardwareJPanel(extNICinfo, extNICtemp);
		extNICPanel.addMouseListener(this);
		extNICPanel.setName("Ext NIC");



		this.add(mbPanel);
		this.add(cpuPanel);
		this.add(hddPanel);
		this.add(ramPanel);
		this.add(discPanel);
		this.add(gpuPanel);
		this.add(intNICPanel);
		this.add(extNICPanel);
	}




	@Override
	public void mouseClicked(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();
		
		
		if( panel.getName().equals("Motherboard") )
		{
			System.out.println("Clicked");
		}
		else if( panel.getName().equals("CPU") )
		{
			
		}
		else if( panel.getName().equals("HDD") )
		{
			
		}
		else if( panel.getName().equals("RAM") )
		{
			
		}
		else if( panel.getName().equals("Discdrive") )
		{
			
		}
		else if( panel.getName().equals("GPU") )
		{
			
		}
		else if( panel.getName().equals("Int NIC") )
		{
			
		}
		else if( panel.getName().equals("Ext NIC") )
		{
			
		}
	}






	@Override
	public void mouseEntered(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		
		
		if( panel.getName().equals("Motherboard") )
		{
			mbPanel.setBorder(raisedbevel);
		}
		else if( panel.getName().equals("CPU") )
		{
			
		}
		else if( panel.getName().equals("HDD") )
		{
			
		}
		else if( panel.getName().equals("RAM") )
		{
			
		}
		else if( panel.getName().equals("Discdrive") )
		{
			
		}
		else if( panel.getName().equals("GPU") )
		{
			
		}
		else if( panel.getName().equals("Int NIC") )
		{
			
		}
		else if( panel.getName().equals("Ext NIC") )
		{
			
		}
		
	}




	@Override
	public void mouseExited(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();
		
		Border normal = BorderFactory.createEtchedBorder();
		
		if( panel.getName().equals("Motherboard") )
		{
			mbPanel.setBorder(normal);
		}
		else if( panel.getName().equals("CPU") )
		{
			
		}
		else if( panel.getName().equals("HDD") )
		{
			
		}
		else if( panel.getName().equals("RAM") )
		{
			
		}
		else if( panel.getName().equals("Discdrive") )
		{
			
		}
		else if( panel.getName().equals("GPU") )
		{
			
		}
		else if( panel.getName().equals("Int NIC") )
		{
			
		}
		else if( panel.getName().equals("Ext NIC") )
		{
			
		}
	}






	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}






	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}
