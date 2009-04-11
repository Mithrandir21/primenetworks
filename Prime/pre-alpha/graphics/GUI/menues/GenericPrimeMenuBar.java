/**
 * 
 */
package graphics.GUI.menues;


import graphics.ImageLocator;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import actions.ActionCopy;
import actions.ActionCut;
import actions.ActionNew;
import actions.ActionOpenfile;
import actions.ActionPaste;
import actions.ActionSave;
import actions.ActionSaveAll;
import actions.toolbar.ActionAllMessageView;
import actions.toolbar.ActionConnectionMessage;
import actions.toolbar.ActionHardwareMessage;
import actions.toolbar.ActionNetworkMessage;
import actions.toolbar.ActionSettings;
import actions.toolbar.ActionSoftwareMessage;


/**
 * This class creates the a generic {@link JMenuBar JMenuBar} for the main windows of the program. This JMenuBar will
 * include the menues "File", "Edit", "Tools" and "Help". (More menues should be added at a later point.)
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class GenericPrimeMenuBar extends JMenuBar
{



	ImageIcon icon = ImageLocator.getImageIconObject("java");

	// The menu buttons at the top of the screen
	private JMenu file, edit, view, tools, help;


	/**
	 * This function class all the functions that initiate the creation of the different menues.
	 */
	public GenericPrimeMenuBar()
	{
		initMenuFile();

		initMenuEdit();

		initMenuView();

		initMenuTools();

		initMenuHelp();
	}




	// SETUP FOR THE MENUES

	/**
	 * This function creates the JMenu "File". It adds the actions "New", "Open", "Save", "Save All" and "Exit". (More
	 * actions will be added at a later stage.)
	 */
	private void initMenuFile()
	{
		// File menu
		file = new JMenu("File");
		file.setMnemonic('F');

		// Adds different items to the "File" menu

		JMenuItem newFile = new JMenuItem(new ActionNew("New"));
		newFile.setIcon(null);
		file.add(newFile);

		JMenuItem openFile = new JMenuItem(new ActionOpenfile("Open File"));
		openFile.setIcon(null);
		file.add(openFile);

		JMenuItem saveFile = new JMenuItem(new ActionSave("Save"));
		saveFile.setIcon(null);
		file.add(saveFile);

		JMenuItem saveAll = new JMenuItem(new ActionSaveAll("Save All"));
		saveAll.setIcon(null);
		file.add(saveAll);

		// Adds a separator to the menu
		file.addSeparator();

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);


		this.add(file);
	}


	/**
	 * This function creates the JMenu "File". It adds the actions "Undo", "Cut", "Copy" and "Paste". (More actions will
	 * be added at a later stage.)
	 */
	private void initMenuEdit()
	{
		// Edit menu
		edit = new JMenu("Edit");
		edit.setMnemonic('E');

		// Adds different items to the "Edit" menu
		JMenuItem undo = new JMenuItem(new ActionSaveAll("Save All"));
		undo.setIcon(null);
		edit.add(undo);

		// Adds a separator to the menu
		edit.addSeparator();

		JMenuItem cut = new JMenuItem(new ActionCut("Cut"));
		cut.setIcon(null);
		edit.add(cut);

		JMenuItem copy = new JMenuItem(new ActionCopy("Copy"));
		copy.setIcon(null);
		edit.add(copy);

		JMenuItem paste = new JMenuItem(new ActionPaste("Paste"));
		paste.setIcon(null);
		edit.add(paste);




		this.add(edit);
	}


	/**
	 * This function creates the JMenu "Views". (More actions will be added at a later stage.)
	 */
	private void initMenuView()
	{
		// View menu
		view = new JMenu("Views");
		view.setMnemonic('V');


		JMenu submenu = new JMenu("Message Views");
		
		
		JMenuItem allViews = new JMenuItem(new ActionAllMessageView("All Message Views"));
		allViews.setIcon(null);
		submenu.add(allViews);
		

		// Adds a separator to the menu
		submenu.addSeparator();

		
		
		JMenuItem netMsg = new JMenuItem(new ActionNetworkMessage("Network Messages"));
		netMsg.setIcon(null);
		submenu.add(netMsg);
		
		
		JMenuItem conMsg = new JMenuItem(new ActionConnectionMessage("Connection Messages"));
		conMsg.setIcon(null);
		submenu.add(conMsg);
		
		
		JMenuItem softMsg = new JMenuItem(new ActionSoftwareMessage("Software Messages"));
		softMsg.setIcon(null);
		submenu.add(softMsg);
		
		
		JMenuItem hardMsg = new JMenuItem(new ActionHardwareMessage("Hardware Messages"));
		hardMsg.setIcon(null);
		submenu.add(hardMsg);

		
		view.add(submenu);



		this.add(view);
	}



	/**
	 * This function creates the JMenu "Tools". (More actions will be added at a later stage.)
	 */
	private void initMenuTools()
	{
		// Tools menu
		tools = new JMenu("Tools");
		tools.setMnemonic('T');


		JMenuItem openSettings = new JMenuItem(new ActionSettings("Settings"));
		openSettings.setIcon(null);
		tools.add(openSettings);



		this.add(tools);
	}


	/**
	 * This function creates the JMenu "Help". (More actions will be added at a later stage.)
	 */
	private void initMenuHelp()
	{
		// Help menu
		help = new JMenu("Help");
		help.setMnemonic('H');


		this.add(help);
	}


	/**
	 * This function sets the keystroke association with the spesific action. Eksampel: "Undo" with "Ctrl + Z" It sets
	 * the keystroke to the default keystroke choosen, which normally is the "Alt" key.
	 */
	private JMenuItem setKeystroke(JMenuItem item, char key)
	{
		item.setAccelerator(KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));

		return item;
	}


	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = getClass().getResource(path);
		if ( imgURL != null )
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
