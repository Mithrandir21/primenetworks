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

import objects.Object;
import widgetManipulation.WidgetObject;


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
		super("Object View");

		widgetObj = obj;

		currentObject = obj.getObject();

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) scrnsize.getWidth()) / 2;

		int height = ((int) scrnsize.getHeight()) / 2;

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

		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button("cancel");
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
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
	 * 
	 */
	public void save(boolean closeObjectView)
	{
		// Saves the information in the general view
		if( saveGeneralView() )
		{
			
		}
		
		
		// If closeObjectView is true, this JFrame is closed
		if ( closeObjectView )
		{
			this.dispose();
		}

	}



	/**
	 * Saves the information in the general view. Checks the fields and gives the user
	 * feedback if the name field is 
	 * 
	 * @return
	 */
	private boolean saveGeneralView()
	{
		// Gets the String from the JTextField
		String viewNameText = view.genObjView.nametext.getText();

		// If the name of the object is anything other then ""
		if ( !viewNameText.equals("") )
		{
			// Updates the name of the WidgetObject on the Scene
			currentObject = GraphicalFunctions.updateWidgetObjectCanvasName(currentObject, widgetObj, viewNameText);

			// Sets the new name as the Widgets tooltip
			widgetObj.setToolTipText(viewNameText);

			// If the description in the JTextArea is different then the objects current description
			if ( !currentObject.getDescription().equals(view.genObjView.textarea.getText()) )
			{
				currentObject.setDescription(view.genObjView.textarea.getText());
			}


			PrimeMain1.updateCanvasAndObjectInfo();
			PrimeMain1.updatePropertiesObjectArea(widgetObj.getObject());

			PrimeMain1.removeObjectView(currentObject);
			
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must specify a name for this Object.", "Error",
					JOptionPane.ERROR_MESSAGE);

			// Focuses on the name JTextField
			view.genObjView.nametext.requestFocusInWindow();
			
			return false;
		}
	}
	
	
	
	private boolean saveNetworkView()
	{
		
	}



	/**
	 * This method calls the UpdateTabInfo method in the ObjectViewTabbed class to update the information about the
	 * current object.
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
