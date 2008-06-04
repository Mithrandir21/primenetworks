/**
 * 
 */
package graphics;


import graphics.GUI.selectArea.ImageSelection;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.TransferHandler;



/**
 * The WidgetIcon class represents an JLabel based icon that represents
 * an {@link  objects.Object  Object} within the system. 
 * 
 * This is a dragable object that can be dropped within the 
 * {@link  graphics.GUI.workareaCanvas.WorkareaCanvas  Canvas}. 
 *
 * @author Bahram Malaekeh
 * 
 */
public class WidgetIcon extends JLabel
{
	/**
	 * This represents the class type what the icon represents.
	 * Like {@link  clients.Desktop  Desktop} or 
	 * {@link  servers.Server  Server}.
	 */
	Class classType;

	/**
	 * This is the decription of the object that is represents.
	 */
	String description = "";

	/**
	 * The icon image.
	 */
	Image img = null;




	/**
	 * A constructor that creates a JLabel icon with the given ImageIcon
	 * and sets the classType of the represented {@link  objects.Object  Object}.
	 * 
	 * The constructor also adds a mouseListener and sets a transferhandler
	 * for the JLabel icon. 
	 *
	 * @param icon The icon that is to represent the {@link  objects.Object  Object}.
	 * @param objectType The object class of the represented {@link  objects.Object  Object}.
	 */
	public WidgetIcon(ImageIcon icon, Class objectType)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
		widgetIconSetup();
	}



	/**
	 * A constructor that creates a JLabel icon with the given ImageIcon,
	 * sets the classType of the represented {@link  objects.Object  Object} and
	 * gives the JLabel icon a name.
	 *
	 * The constructor also adds a mouseListener and sets a transferhandler
	 * for the JLabel icon. 
	 *
	 * @param icon The icon that is to represent the {@link  objects.Object  Object}.
	 * @param objectType The object class of the represented {@link  objects.Object  Object}.
	 * @param name The name of the JLabel. 
	 */
	public WidgetIcon(ImageIcon icon, Class objectType, String name)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
		setText(name);
		widgetIconSetup();
	}


	/**
	 * A constructor that creates a JLabel icon with the given ImageIcon,
	 * sets the classType of the represented {@link  objects.Object  Object}, 
	 * gives the JLabel icon a name and sets a description of the object representer.
	 *
	 * The constructor also adds a mouseListener and sets a transferhandler
	 * for the JLabel icon. 
	 *
	 * @param icon The icon that is to represent the {@link  objects.Object  Object}.
	 * @param objectType The object class of the represented {@link  objects.Object  Object}.
	 * @param name The name of the JLabel. 
	 * @param desc The description of of the represented 
	 */
	public WidgetIcon(ImageIcon icon, Class objectType, String name, String desc)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
		setText(name);
		description = desc;
		widgetIconSetup();
	}


	// GETTERS

	/**
	 * @return The class of the {@link  objects.Object  Object} that the JLabel icon represents.
	 */
	public Class getClassType()
	{
		return classType;
	}


	/**
	 * @return The description of the {@link  objects.Object  Object} that the JLabel icon represents.
	 */
	public String getDescription()
	{
		return description;
	}



	/**
	 * @return Returns the image that is shown as the JLabel icon.
	 */
	public Image getIconImage()
	{
		return img;
	}



	/**
	 * This method sets the widgetIcons, JLabel icon, transferhandler which takes care
	 * of the drag and drop functionality. It also adds an mouselistener to the JLabel.
	 */
	private void widgetIconSetup()
	{
		addMouseListener(mouseListener);
		setTransferHandler(new ImageSelection());
	}



	/**
	 * This is the classes mouseListener.
	 * It gets the source of the mouse event, gets the transferhandler for the
	 * source and sets the action for the handler to exportAsDrag.
	 * 
	 * Contains only a "mousePressed" method.
	 */
	private MouseListener mouseListener = new MouseAdapter()
	{
		public void mousePressed(MouseEvent e)
		{
			JLabel comp = (JLabel) e.getSource();
			TransferHandler handler = comp.getTransferHandler();
			handler.exportAsDrag(comp, e, TransferHandler.COPY);
		}
	};
}
