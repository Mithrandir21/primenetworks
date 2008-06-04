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


/**
 * This class creates the a generic {@link  JMenuBar  JMenuBar} for the main
 * windows of the program. This JMenuBar will include the menues "File", "Edit",
 * "Tools" and "Help". (More menues should be added at a later point.)
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class GenericPrimeMenuBar extends JMenuBar
{



	ImageIcon icon = ImageLocator.createImageIcon(this.getClass().getResource("images/java.jpg"));

	// The menu buttons at the top of the screen
	private JMenu file, edit, tools, help;


	// Temp ImageIcon for holding the menu Icon.
	private ImageIcon tempIcon = null;


	/**
	 * This function class all the functions that initiate the creation of the
	 * different menues.
	 */
	public GenericPrimeMenuBar()
	{
		initMenuFile();

		initMenuEdit();

		initMenuTools();

		initMenuHelp();
	}




	// SETUP FOR THE MENUES

	/**
	 * This function creates the JMenu "File". It adds the actions "New",
	 * "Open", "Save", "Save All" and "Exit". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuFile()
	{
		// File menu
		file = new JMenu("File");
		file.setMnemonic('F');

		// Adds different items to the "File" menu

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/new.jpg"));
		JMenuItem newFile = new JMenuItem(new ActionNew("New", tempIcon));
		newFile.setIcon(null);
		file.add(newFile);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/open.jpg"));
		JMenuItem openFile = new JMenuItem(new ActionOpenfile("Open File", tempIcon));
		openFile.setIcon(null);
		file.add(openFile);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/save.jpg"));
		JMenuItem saveFile = new JMenuItem(new ActionSave("Save", tempIcon));
		saveFile.setIcon(null);
		file.add(saveFile);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/save_all.jpg"));
		JMenuItem saveAll = new JMenuItem(new ActionSaveAll("Save All", tempIcon));
		saveAll.setIcon(null);
		file.add(saveAll);

		// Adds a separator to the menu
		file.addSeparator();

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);


		this.add(file);
		tempIcon = null;
	}


	/**
	 * This function creates the JMenu "File". It adds the actions "Undo",
	 * "Cut", "Copy" and "Paste". (More actions will be added at a later stage.)
	 */
	private void initMenuEdit()
	{
		// Edit menu
		edit = new JMenu("Edit");
		edit.setMnemonic('E');

		// Adds different items to the "Edit" menu
		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/save_all.jpg"));
		JMenuItem undo = new JMenuItem(new ActionSaveAll("Save All", null));
		undo.setIcon(null);
		edit.add(undo);

		// Adds a separator to the menu
		edit.addSeparator();

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/cut.jpg"));
		JMenuItem cut = new JMenuItem(new ActionCut("Cut", tempIcon));
		cut.setIcon(null);
		edit.add(cut);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/copy.jpg"));
		JMenuItem copy = new JMenuItem(new ActionCopy("Copy", tempIcon));
		copy.setIcon(null);
		edit.add(copy);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource(
				"images/buttonIcons/paste.jpg"));
		JMenuItem paste = new JMenuItem(new ActionPaste("Paste", tempIcon));
		paste.setIcon(null);
		edit.add(paste);




		this.add(edit);
		tempIcon = null;
	}


	/**
	 * This function creates the JMenu "Tools". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuTools()
	{
		// Tools menu
		tools = new JMenu("Tools");
		tools.setMnemonic('T');


		this.add(tools);
	}


	/**
	 * This function creates the JMenu "Help". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuHelp()
	{
		// Help menu
		help = new JMenu("Help");
		help.setMnemonic('H');


		this.add(help);
	}


	/**
	 * This function sets the keystroke association with the spesific action.
	 * Eksampel: "Undo" with "Ctrl + Z" It sets the keystroke to the default
	 * keystroke choosen, which normally is the "Alt" key. 
	 */
	private JMenuItem setKeystroke(JMenuItem item, char key)
	{
		item.setAccelerator(KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit()
				.getMenuShortcutKeyMask(), false));

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
