/**
 * 
 */
package graphics.GUI.workareaCanvas;

import graphics.PrimeMain1;

import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @author Bam
 *
 */
public class CanvasMenu
{
	private static JPopupMenu popup = new JPopupMenu();
	
	
	public static void createPopupMenu(WorkareaCanvas canvas)
	{
		InitialMenues(canvas);
		

		popup.addSeparator();
		
		
		ObjectCreationMenues(canvas);

		// Add listener to the text area so the popup menu can come up.
		MouseListener popupListener = new PopupListener(popup);
		PrimeMain1.myView.addMouseListener(popupListener);
	}
	
	
	
	private static void InitialMenues(WorkareaCanvas canvas)
	{
		JMenuItem menuItem;
		
		menuItem = new JMenuItem("A popup menu item");
		menuItem.setName("CreateNewItem");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);
		
		menuItem = new JMenuItem("Another popup menu item");
		menuItem.setName("CreateNewItem");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);

		menuItem = new JMenuItem("Yet Another popup menu item");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);
	}
	
	
	private static void ObjectCreationMenues(WorkareaCanvas canvas)
	{
		JMenuItem menuItem;
		
		JMenuItem submenu = new JMenu("Add devices");
		submenu.addActionListener(canvas);

		menuItem = new JMenuItem("Add Standard Desktop");
		menuItem.setName("CreateNewST_Desktop_Item");
		menuItem.addActionListener(canvas);
		submenu.add(menuItem);

		menuItem = new JMenuItem("Add Standard Laptop");
		menuItem.setName("CreateNewST_Laptop_Item");
		menuItem.addActionListener(canvas);
		submenu.add(menuItem);
        
		menuItem = new JMenuItem("Add Standard Web Server");
		menuItem.setName("CreateNewST_HTTPServer_Item");
		menuItem.addActionListener(canvas);
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Add Standard Backup Server");
		menuItem.setName("CreateNewST_BackupServer_Item");
		menuItem.addActionListener(canvas);
		submenu.add(menuItem);
		
		
		
        popup.add(submenu);
	}
}
