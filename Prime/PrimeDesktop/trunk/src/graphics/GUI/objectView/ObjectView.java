/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.objectView;


import graphics.PrimeMain;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logistical.checkLogic;
import managment.DesktopCanvasManagment;
import managment.RulesManagment;
import objects.Clients;
import objects.ExternalHardware;
import objects.Object;
import objects.Servers;
import widgetManipulation.WidgetNetworkInfo;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectView extends JFrame implements ActionListener
{
	private ObjectViewTabbed view;

	private WidgetObject widgetObj;

	private Object currentObject;

	private WorkareaCanvas canvas;


	/**
	 * TODO - Description NEEDED!
	 */
	public ObjectView(WidgetObject obj)
	{
		super(obj.getObject().getObjectName());

		widgetObj = obj;

		currentObject = obj.getObject();

		canvas = PrimeMain.currentCanvas;

		this.setIconImage(widgetObj.getImageWidget().getImage());

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(650, 625);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		view = new ObjectViewTabbed(canvas, obj);

		panel.add(view);



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button(PrimeMain.texts.getString("apply"));
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);



		c.add(panel);

		this.setPreferredSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);


		// Resets the objectView object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				PrimeMain.removeObjectView(currentObject);
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			save(true);
		}
		else if ( e.getActionCommand().equals("apply") )
		{
			save(false);
		}
		else
		{
			assert e.getActionCommand().equals("cancel");

			PrimeMain.removeObjectView(currentObject);

			this.dispose();
		}

	}



	/**
	 * Javadoc-TODO - Description
	 */
	public void save(boolean closeObjectView)
	{
		boolean errorFound = false;

		// Saves the information in the general view
		if ( saveGeneralView() )
		{
			// Saves the network information about the widget
			errorFound = saveNetworkView();
		}

		// Determines the supported connection interface on the device.
		currentObject.revalidateSupportedConnectionInterfaces();


		// If closeObjectView is true, this JFrame is closed
		if ( closeObjectView && (!(errorFound)) )
		{
			// Updates the views of the object to correctly show the
			// current info.
			ObjectView view = PrimeMain.getObjectView(currentObject);
			this.dispose();
		}


		DesktopCanvasManagment.canvasCleanUp(canvas);
	}



	/**
	 * Saves the information in the general view. Checks the fields and gives
	 * the user feedback if the name field is
	 * 
	 * @return
	 */
	private boolean saveGeneralView()
	{
		// Gets the String from the JTextField
		String viewNameText = view.genObjView.nametext.getText();

		// Validates the name
		if ( checkLogic.validateName(viewNameText) )
		{
			// Updates the name of the WidgetObject on the Scene
			currentObject = WorkareaCanvasActions.updateWidgetObjectCanvasName(
					PrimeMain.canvases, widgetObj, viewNameText);

			if ( currentObject != null )
			{
				// Sets the new name as the Widgets tooltip
				widgetObj.setToolTipText(viewNameText);

				// If the description in the JTextArea is different then the
				// objects current description
				if ( !currentObject.getDescription().equals(
						view.genObjView.textarea.getText()) )
				{
					currentObject.setDescription(view.genObjView.textarea
							.getText());
				}


				PrimeMain.updateCanvasAndObjectInfo();
				PrimeMain.updatePropertiesObjectArea(widgetObj.getObject(),
						true);

				PrimeMain.removeObjectView(currentObject);

				return true;
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,
					PrimeMain.texts.getString("saveSpecifyNameErrorMsg"),
					PrimeMain.texts.getString("error"),
					JOptionPane.ERROR_MESSAGE);

			// Focuses on the name JTextField
			view.genObjView.nametext.requestFocusInWindow();

			return false;
		}

		return false;
	}



	/**
	 * TODO - Description
	 */
	private boolean saveNetworkView()
	{
		WidgetNetworkInfo info = widgetObj.getWidgetNetworkInfo();

		boolean errorFound = false;


		// Validates IP address and then sets it. If false is returned, the
		// user will be given a error message.
		if ( !(info.setIp(view.netObjView.widgetIPfield.getText())) )
		{
			JOptionPane.showMessageDialog(null,
					PrimeMain.texts.getString("saveNetworkNotValidIPerrorMsg"),
					PrimeMain.texts.getString("error"),
					JOptionPane.ERROR_MESSAGE);

			errorFound = true;
		}

		// Validates and sets subnet
		if ( !(info.setNetmask(view.netObjView.widgetNetmaskField.getText())) )
		{
			// If not other error has been given
			if ( !(errorFound) )
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("saveNetworkNotValidNetmaskErrorMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				errorFound = true;
			}
		}

		// Validates and sets MAC address
		if ( !(info.setMAC(view.netObjView.widgetMacField.getText())) )
		{
			// If not other error has been given
			if ( !(errorFound) )
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("saveNetworkNotValidMacErrorMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				errorFound = true;
			}
		}

		// Validates and sets Default gateway
		if ( !(info.setDefaultGateway(view.netObjView.widgetDefaultGatewayField
				.getText())) )
		{
			// If not other error has been given
			if ( !(errorFound) )
			{
				JOptionPane
						.showMessageDialog(
								null,
								PrimeMain.texts
										.getString("saveNetworkNotValidDefaultGatewayErrorMsg"),
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);

				errorFound = true;
			}
		}

		// Validates and sets widget name
		if ( view.netObjView.widgetNetworkNameField.getText().equals("")
				|| checkLogic
						.validateName(view.netObjView.widgetNetworkNameField
								.getText()) )
		{
			info.setNetworkName(view.netObjView.widgetNetworkNameField
					.getText());
		}
		else
		{
			// If not other error has been given
			if ( !(errorFound) )
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("saveNetworkNotValidNetworkNameErrorMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				errorFound = true;
			}
		}




		boolean exempted = view.netObjView.exemptedNetworkRules.isSelected();

		if ( !(exempted) && currentObject.isExemptedNetworkRules() )
		{
			String question = PrimeMain.texts
					.getString("rulesNoLongerExemptedMsg")
					+ System.getProperty("line.separator")
					+ PrimeMain.texts
							.getString("rulesPortsConnectionsChangeMsg");



			// Custom button text
			String[] options = { PrimeMain.texts.getString("yes"),
					PrimeMain.texts.getString("no") };


			int i = JOptionPane.showOptionDialog(null, question,
					PrimeMain.texts.getString("confirm"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			// If the answer is yes
			if ( i == 0 && PrimeMain.currentCanvas != null )
			{
				currentObject.setExemptedNetworkRules(false);

				RulesManagment.processRulesChange(PrimeMain.currentCanvas);
			}
			else
			{
				view.netObjView.exemptedNetworkRules.setSelected(true);
			}
		}
		else
		{
			currentObject.setExemptedNetworkRules(exempted);
		}




		// Sets the widget notes (can be anything)
		info.setWidgetNotes(view.netObjView.widgetNotesArea.getText());


		// SAVING THE PERMISSIONS PART
		if ( currentObject instanceof Clients
				|| currentObject instanceof Servers
				|| currentObject instanceof ExternalHardware )
		{
			view.netObjView.objPermPanel.saveObjectPermissions();
		}







		return errorFound;
	}


	/**
	 * This method calls the UpdateTabInfo method in the ObjectViewTabbed class
	 * to update the information about the current object.
	 */
	public void updateViewInfo()
	{
		view.updateTabInfo();
	}


	/**
	 * @return Returns the ObjectViewTabbed that contains the object views.
	 */
	public ObjectViewTabbed getObjectView()
	{
		return view;
	}


	/**
	 * Gets the object that is currently i view.
	 * 
	 * @return The in view object.
	 */
	public Object getObject()
	{
		return currentObject;
	}


	/**
	 * Returns the {@link WorkareaCanvas} that this class is working from.
	 */
	public WorkareaCanvas getCanvas()
	{
		return canvas;
	}
}
