package graphics.GUI.objectView;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;

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
import managment.ComponentsManagment;
import objects.Object;
import widgetManipulation.WidgetNetworkInfo;
import widgets.WidgetObject;


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


	/**
	 * TODO - Description NEEDED!
	 */
	public ObjectView(WidgetObject obj)
	{
		super(PrimeMain1.texts.getString("objectViewLabel"));

		widgetObj = obj;

		currentObject = obj.getObject();

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(650, 525);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		view = new ObjectViewTabbed(obj);

		panel.add(view);



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button(PrimeMain1.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button(PrimeMain1.texts.getString("apply"));
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button(PrimeMain1.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);



		c.add(panel);

		this.setSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(new Dimension(650, 525));
		this.setVisible(true);


		// Resets the objectView object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				PrimeMain1.removeObjectView(currentObject);
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

			PrimeMain1.removeObjectView(currentObject);

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


		// Sets the supported connection interfaces for the Object
		currentObject.setSupportedConnectionInterfaces(ComponentsManagment
				.getSupportedInterfaces(currentObject));


		// If closeObjectView is true, this JFrame is closed
		if ( closeObjectView && (errorFound == false) )
		{
			this.dispose();
		}

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
			currentObject = GraphicalFunctions.updateWidgetObjectCanvasName(
					currentObject, widgetObj, viewNameText);

			// Sets the new name as the Widgets tooltip
			widgetObj.setToolTipText(viewNameText);

			// If the description in the JTextArea is different then the
			// objects current description
			if ( !currentObject.getDescription().equals(
					view.genObjView.textarea.getText()) )
			{
				currentObject
						.setDescription(view.genObjView.textarea.getText());
			}


			PrimeMain1.updateCanvasAndObjectInfo();
			PrimeMain1.updatePropertiesObjectArea(widgetObj.getObject(), true);

			PrimeMain1.removeObjectView(currentObject);

			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, PrimeMain1.texts
					.getString("saveSpecifyNameErrorMsg"), PrimeMain1.texts
					.getString("error"), JOptionPane.ERROR_MESSAGE);

			// Focuses on the name JTextField
			view.genObjView.nametext.requestFocusInWindow();

			return false;
		}
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
		if ( !(view.netObjView.widgetIPfield.getText().equals("")) )
		{
			if ( !(info.setIp(view.netObjView.widgetIPfield.getText())) )
			{
				JOptionPane.showMessageDialog(null, PrimeMain1.texts
						.getString("saveNetworkNotValidIPerrorMsg"),
						PrimeMain1.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				errorFound = true;
			}
		}

		// Validates and sets subnet
		if ( !(view.netObjView.widgetSubnetField.getText().equals("")) )
		{
			if ( !(info.setNetmask(view.netObjView.widgetSubnetField.getText())) )
			{
				// If not other error has been given
				if ( errorFound != true )
				{
					JOptionPane.showMessageDialog(null, PrimeMain1.texts
							.getString("saveNetworkNotValidNetmaskErrorMsg"),
							PrimeMain1.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					errorFound = true;
				}
			}
		}

		// Validates and sets MAC address
		if ( !(view.netObjView.widgetMacField.getText().equals("")) )
		{
			if ( !(info.setMAC(view.netObjView.widgetMacField.getText())) )
			{
				// If not other error has been given
				if ( errorFound != true )
				{
					JOptionPane.showMessageDialog(null, PrimeMain1.texts
							.getString("saveNetworkNotValidMacErrorMsg"),
							PrimeMain1.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					errorFound = true;
				}
			}
		}

		// Validates and sets Default gateway
		if ( !(view.netObjView.widgetDefaultGatewayField.getText().equals("")) )
		{
			if ( !(info
					.setDefaultGateway(view.netObjView.widgetDefaultGatewayField
							.getText())) )
			{
				// If not other error has been given
				if ( errorFound != true )
				{
					JOptionPane
							.showMessageDialog(
									null,
									PrimeMain1.texts
											.getString("saveNetworkNotValidDefaultGatewayErrorMsg"),
									PrimeMain1.texts.getString("error"),
									JOptionPane.ERROR_MESSAGE);

					errorFound = true;
				}
			}
		}

		// Validates and sets widget name
		if ( !(view.netObjView.widgetNetworkNameField.getText().equals("")) )
		{
			if ( checkLogic.validateName(view.netObjView.widgetNetworkNameField
					.getText()) )
			{
				info.setNetworkName(view.netObjView.widgetNetworkNameField
						.getText());
			}
			else
			{
				// If not other error has been given
				if ( errorFound != true )
				{
					JOptionPane
							.showMessageDialog(
									null,
									PrimeMain1.texts
											.getString("saveNetworkNotValidNetworkNameErrorMsg"),
									PrimeMain1.texts.getString("error"),
									JOptionPane.ERROR_MESSAGE);

					errorFound = true;
				}
			}
		}


		// Sets the widget notes (can be anything)
		info.setWidgetNotes(view.netObjView.widgetNotesArea.getText());


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
}
