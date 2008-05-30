/**
 * 
 */
package graphics;

import graphics.GUI.selectArea.ImageSelection;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 * @author Bam
 *
 */
public class WidgetIcon extends JLabel
{
	Class classType;
	
	String description = "";
	
	Image img = null;
	
	
	
	/**
	 * @param icon
	 * @param objectType
	 */
	public WidgetIcon(ImageIcon icon, Class objectType)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
		widgetIconSetup();
	}
	
	
	/**
	 * @param icon
	 * @param objectType
	 * @param name
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
	 * @param icon
	 * @param objectType
	 * @param name
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
	 * @return the classType
	 */
	public Class getClassType() 
	{
		return classType;
	}
	
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	
	
	/**
	 * @return
	 */
	public Image getIconImage()
	{
		return img;
	}
	

	
	
	/**
	 * 
	 */
	private void widgetIconSetup()
	{
		addMouseListener(mouseListener);
		setTransferHandler(new ImageSelection());
	}
	
	
	
	/**
	 * 
	 */
	MouseListener mouseListener = new MouseAdapter() 
	{
	      public void mousePressed(MouseEvent e) 
	      {
	        JComponent comp = (JComponent) e.getSource();
	        TransferHandler handler = comp.getTransferHandler();
	        handler.exportAsDrag(comp, e, TransferHandler.COPY);
	      }
	};
}
