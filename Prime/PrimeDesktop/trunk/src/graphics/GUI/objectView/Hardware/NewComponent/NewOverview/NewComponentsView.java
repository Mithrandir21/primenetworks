/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.objectView.Hardware.NewComponent.NewOverview;


import graphics.PrimeMain;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareObjectView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.CPUNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.DiscDriveNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.ExternalNICNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.GraphicsCardNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.HDDNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.InternalNICNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.MotherboardNewView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.RAMNewView;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
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

	Motherboard mbObj = null;

	CPU cpuObj = null;

	HDD hddObj = null;

	Ram ramObj = null;

	Discdrive discObj = null;

	GraphicsCard gpuObj = null;

	InternalNetworksCard intNICObj = null;

	ExternalNetworksCard extNICObj = null;



	private Object mainObj;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 */
	public NewComponentsView(Object obj)
	{
		mainObj = obj;

		this.setLayout(new GridLayout(0, 2, 3, 5));


		ImageIcon mbtemp = PrimeMain.objectImageIcons.get(Motherboard.class);

		String[] mbinfo = new String[5];

		mbObj = PrimeMain.standard_internal_components.getSt_MB();

		String text = null;

		text = mbObj.getObjectName();
		if ( text != "" && text != null )
		{
			mbinfo[0] = text;
		}

		text = mbObj.getForm();
		if ( text != "" && text != null )
		{
			mbinfo[1] = PrimeMain.texts.getString("hwTabFormLabel") + ": "
					+ text;
		}

		text = mbObj.getSocket();
		if ( text != "" && text != null )
		{
			mbinfo[2] = PrimeMain.texts.getString("hwTabSocketLabel") + ": "
					+ text;
		}

		text = mbObj.getGraphicalPort();
		if ( text != "" && text != null )
		{
			mbinfo[3] = PrimeMain.texts.getString("hwTabGPUslotLabel") + ": "
					+ text;
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
		ImageIcon cputemp = PrimeMain.objectImageIcons.get(CPU.class);

		cpuObj = PrimeMain.standard_internal_components.getSt_CPU();

		String[] cpuinfo = new String[4];

		text = cpuObj.getObjectName();
		if ( text != "" && text != null )
		{
			cpuinfo[0] = text;
		}

		text = cpuObj.getProducer();
		if ( text != "" && text != null )
		{
			cpuinfo[1] = PrimeMain.texts.getString("hwTabProducerLabel") + ": "
					+ text;
		}

		text = cpuObj.getSocket();
		if ( text != "" && text != null )
		{
			cpuinfo[2] = PrimeMain.texts.getString("hwTabSocketLabel") + ": "
					+ text;
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
		ImageIcon hddtemp = PrimeMain.objectImageIcons.get(HDD.class);

		hddObj = PrimeMain.standard_internal_components.getSt_HDD();

		String[] hddinfo = new String[5];

		text = hddObj.getObjectName();
		if ( text != "" && text != null )
		{
			hddinfo[0] = text;
		}

		text = hddObj.getProducer();
		if ( text != "" && text != null )
		{
			hddinfo[1] = PrimeMain.texts.getString("hwTabProducerLabel") + ": "
					+ text;
		}

		text = hddObj.getSubtype();
		if ( text != "" && text != null )
		{
			hddinfo[2] = PrimeMain.texts.getString("hwTabTypeLabel") + ": "
					+ text;
		}

		if ( hddObj.getSize() != 0 )
		{
			hddinfo[3] = PrimeMain.texts.getString("hwTabSizeLabel") + ": "
					+ Integer.toString(hddObj.getSize());
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
		ImageIcon ramtemp = PrimeMain.objectImageIcons.get(Ram.class);

		ramObj = PrimeMain.standard_internal_components.getSt_RAM();

		String[] raminfo = new String[5];

		text = ramObj.getObjectName();
		if ( text != "" && text != null )
		{
			raminfo[0] = text;
		}

		text = ramObj.getProducer();
		if ( text != "" && text != null )
		{
			raminfo[1] = PrimeMain.texts.getString("hwTabProducerLabel") + ": "
					+ text;
		}

		text = ramObj.getSubtype();
		if ( text != "" && text != null )
		{
			raminfo[2] = PrimeMain.texts.getString("hwTabTypeLabel") + ": "
					+ text;
		}

		if ( ramObj.getSize() != 0 )
		{
			raminfo[3] = PrimeMain.texts.getString("hwTabSizeLabel") + ": "
					+ Integer.toString(ramObj.getSize());
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
		ImageIcon disctemp = PrimeMain.objectImageIcons.get(Discdrive.class);

		discObj = PrimeMain.standard_internal_components.getSt_DVDRW();

		String[] discinfo = new String[5];

		text = discObj.getObjectName();
		if ( text != "" && text != null )
		{
			discinfo[0] = text;
		}

		text = discObj.getProducer();
		if ( text != "" && text != null )
		{
			discinfo[1] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + text;
		}

		text = discObj.getSubtype();
		if ( text != "" && text != null )
		{
			discinfo[2] = PrimeMain.texts.getString("hwTabTypeLabel") + ": "
					+ text;
		}

		if ( discObj.getSpeed() != 0 )
		{
			discinfo[3] = PrimeMain.texts.getString("hwTabSpeedLabel") + ": "
					+ Integer.toString(discObj.getSpeed());
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
		ImageIcon gputemp = PrimeMain.objectImageIcons.get(GraphicsCard.class);

		gpuObj = PrimeMain.standard_internal_components.getSt_GPU();

		String[] gpuinfo = new String[5];

		text = gpuObj.getObjectName();
		if ( gpuObj.getObjectName() != "" && gpuObj.getObjectName() != null )
		{
			gpuinfo[0] = gpuObj.getObjectName();
		}

		text = gpuObj.getProducer();
		if ( text != "" && text != null )
		{
			gpuinfo[1] = PrimeMain.texts.getString("hwTabProducerLabel") + ": "
					+ text;
		}

		text = gpuObj.getOutputInterface();
		if ( text != "" && text != null )
		{
			gpuinfo[2] = PrimeMain.texts.getString("hwTabOutputLabel") + ": "
					+ text;
		}

		if ( gpuObj.getSpeed() != 0 )
		{
			gpuinfo[3] = PrimeMain.texts.getString("hwTabSpeedLabel") + ": "
					+ Integer.toString(gpuObj.getSpeed());
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
		ImageIcon intNICtemp = PrimeMain.objectImageIcons
				.get(InternalNetworksCard.class);

		intNICObj = PrimeMain.standard_internal_components.getSt_IntNIC();

		String[] intNICinfo = new String[5];

		text = intNICObj.getObjectName();
		if ( text != "" && text != null )
		{
			intNICinfo[0] = text;
		}

		text = intNICObj.getProducer();
		if ( text != "" && text != null )
		{
			intNICinfo[1] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + text;
		}

		text = intNICObj.getProtocol();
		if ( text != "" && text != null )
		{
			intNICinfo[2] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + text;
		}

		if ( intNICObj.getSpeed() != 0 )
		{
			intNICinfo[3] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + Integer.toString(intNICObj.getSpeed());
		}

		text = intNICObj.getDescription();
		if ( text != "" && text != null )
		{
			intNICinfo[4] = text;
		}

		assert intNICtemp != null;

		// Create internal NIC JPanel
		intNICPanel = HardwareObjectView.createHardwareJPanel(intNICinfo,
				intNICtemp);
		intNICPanel.addMouseListener(this);
		intNICPanel.setName("Int NIC");




		// extNIC
		ImageIcon extNICtemp = PrimeMain.objectImageIcons
				.get(ExternalNetworksCard.class);

		extNICObj = PrimeMain.standard_internal_components.getSt_ExtNIC();

		String[] extNICinfo = new String[5];

		text = extNICObj.getObjectName();
		if ( text != "" && text != null )
		{
			extNICinfo[0] = text;
		}

		text = extNICObj.getProducer();
		if ( text != "" && text != null )
		{
			extNICinfo[1] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + text;
		}

		text = extNICObj.getProtocol();
		if ( text != "" && text != null )
		{
			extNICinfo[2] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + text;
		}

		if ( extNICObj.getSpeed() != 0 )
		{
			extNICinfo[3] = PrimeMain.texts.getString("hwTabProducerLabel")
					+ ": " + Integer.toString(extNICObj.getSpeed());
		}

		text = extNICObj.getDescription();
		if ( text != "" && text != null )
		{
			extNICinfo[4] = text;
		}

		assert extNICtemp != null;

		// Create external NIC JPanel
		extNICPanel = HardwareObjectView.createHardwareJPanel(extNICinfo,
				extNICtemp);
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


		if ( panel.getName().equals("Motherboard") )
		{
			int answer = JOptionPane.showConfirmDialog(this, PrimeMain.texts
					.getString("newHWnewMotherboardQuestionMsg"),
					PrimeMain.texts.getString("caution"),
					JOptionPane.YES_NO_OPTION);

			// If the answer is not No.
			if ( answer != 1 )
			{
				new MotherboardNewView(mainObj, mbObj);
				// Creates a new object after the first object is passed to the
				// view.
				mbObj = PrimeMain.standard_internal_components.getSt_MB();
			}
		}
		else if ( panel.getName().equals("CPU") )
		{
			new CPUNewView(mainObj, cpuObj);
			// Creates a new object after the first object is passed to the
			// view.
			cpuObj = PrimeMain.standard_internal_components.getSt_CPU();
		}
		else if ( panel.getName().equals("HDD") )
		{
			new HDDNewView(mainObj, hddObj);
			// Creates a new object after the first object is passed to the
			// view.
			hddObj = PrimeMain.standard_internal_components.getSt_HDD();
		}
		else if ( panel.getName().equals("RAM") )
		{
			new RAMNewView(mainObj, ramObj);
			// Creates a new object after the first object is passed to the
			// view.
			ramObj = PrimeMain.standard_internal_components.getSt_RAM();
		}
		else if ( panel.getName().equals("Discdrive") )
		{
			new DiscDriveNewView(mainObj, discObj);
			// Creates a new object after the first object is passed to the
			// view.
			discObj = PrimeMain.standard_internal_components.getSt_DVDRW();
		}
		else if ( panel.getName().equals("GPU") )
		{
			new GraphicsCardNewView(mainObj, gpuObj);
			// Creates a new object after the first object is passed to the
			// view.
			gpuObj = PrimeMain.standard_internal_components.getSt_GPU();
		}
		else if ( panel.getName().equals("Int NIC") )
		{
			new InternalNICNewView(mainObj, intNICObj);
			// Creates a new object after the first object is passed to the
			// view.
			intNICObj = PrimeMain.standard_internal_components.getSt_IntNIC();
		}
		else if ( panel.getName().equals("Ext NIC") )
		{
			new ExternalNICNewView(mainObj, extNICObj);
			// Creates a new object after the first object is passed to the
			// view.
			extNICObj = PrimeMain.standard_internal_components.getSt_ExtNIC();
		}
	}




	@Override
	public void mouseEntered(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();


		if ( panel.getName().equals("Motherboard") )
		{
			mbPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("CPU") )
		{
			cpuPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("HDD") )
		{
			hddPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("RAM") )
		{
			ramPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("Discdrive") )
		{
			discPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("GPU") )
		{
			gpuPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("Int NIC") )
		{
			intNICPanel.setBorder(raisedbevel);
		}
		else if ( panel.getName().equals("Ext NIC") )
		{
			extNICPanel.setBorder(raisedbevel);
		}

	}




	@Override
	public void mouseExited(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border normal = BorderFactory.createEtchedBorder();


		if ( panel.getName().equals("Motherboard") )
		{
			mbPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("CPU") )
		{
			cpuPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("HDD") )
		{
			hddPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("RAM") )
		{
			ramPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Discdrive") )
		{
			discPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("GPU") )
		{
			gpuPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Int NIC") )
		{
			intNICPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Ext NIC") )
		{
			extNICPanel.setBorder(normal);
		}
	}




	@Override
	public void mousePressed(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border lowered = BorderFactory.createLoweredBevelBorder();


		if ( panel.getName().equals("Motherboard") )
		{
			mbPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("CPU") )
		{
			cpuPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("HDD") )
		{
			hddPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("RAM") )
		{
			ramPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Discdrive") )
		{
			discPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("GPU") )
		{
			gpuPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Int NIC") )
		{
			intNICPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Ext NIC") )
		{
			extNICPanel.setBorder(lowered);
		}
	}






	@Override
	public void mouseReleased(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border raised = BorderFactory.createRaisedBevelBorder();


		if ( panel.getName().equals("Motherboard") )
		{
			mbPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("CPU") )
		{
			cpuPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("HDD") )
		{
			hddPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("RAM") )
		{
			ramPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Discdrive") )
		{
			discPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("GPU") )
		{
			gpuPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Int NIC") )
		{
			intNICPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Ext NIC") )
		{
			extNICPanel.setBorder(raised);
		}
	}

}
