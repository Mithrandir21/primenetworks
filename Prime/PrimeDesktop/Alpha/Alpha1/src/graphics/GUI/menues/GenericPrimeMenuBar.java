/**
 * 
 */
package graphics.GUI.menues;


import graphics.ImageLocator;
import graphics.PrimeMain1;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import actions.ActionCopy;
import actions.ActionCut;
import actions.ActionPaste;
import actions.systemActions.ActionNew;
import actions.systemActions.ActionOpenfile;
import actions.systemActions.ActionSave;
import actions.systemActions.ActionSaveAll;
import actions.toolbar.ActionAllMessageView;
import actions.toolbar.ActionConnectionMessage;
import actions.toolbar.ActionHardwareMessage;
import actions.toolbar.ActionNetworkMessage;
import actions.toolbar.ActionSettings;
import actions.toolbar.ActionSoftwareMessage;


/**
 * This class creates the a generic {@link JMenuBar JMenuBar} for the main
 * windows of the program. This JMenuBar will include the menues "File", "Edit",
 * "Tools" and "Help". (More menues should be added at a later point.)
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
	 * This function class all the functions that initiate the creation of the
	 * different menues.
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
	 * This function creates the JMenu "File". It adds the actions "New",
	 * "Open", "Save", "Save All" and "Exit". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuFile()
	{
		// File menu
		file = new JMenu(PrimeMain1.texts.getString("fileLabel"));
		file.setMnemonic('F');

		// Adds different items to the "File" menu

		JMenuItem newFile = new JMenuItem(new ActionNew(PrimeMain1.texts
				.getString("newLabel")));
		newFile.setIcon(null);
		file.add(newFile);

		JMenuItem openFile = new JMenuItem(new ActionOpenfile(PrimeMain1.texts
				.getString("openFileLabel")));
		openFile.setIcon(null);
		file.add(openFile);

		JMenuItem saveFile = new JMenuItem(new ActionSave(PrimeMain1.texts
				.getString("saveLabel")));
		saveFile.setIcon(null);
		file.add(saveFile);

		JMenuItem saveAll = new JMenuItem(new ActionSaveAll(PrimeMain1.texts
				.getString("saveAllLabel")));
		saveAll.setIcon(null);
		file.add(saveAll);

		// Adds a separator to the menu
		file.addSeparator();

		JMenuItem exit = new JMenuItem(PrimeMain1.texts.getString("exitLabel"));
		file.add(exit);


		this.add(file);
	}

	/**
	 * This function creates the JMenu "File". It adds the actions "Undo",
	 * "Cut", "Copy" and "Paste". (More actions will be added at a later stage.)
	 */
	private void initMenuEdit()
	{
		// Edit menu
		edit = new JMenu(PrimeMain1.texts.getString("editLabel"));
		edit.setMnemonic('E');

		// Adds different items to the "Edit" menu
		JMenuItem undo = new JMenuItem(new ActionSaveAll(PrimeMain1.texts
				.getString("saveAllLabel")));
		undo.setIcon(null);
		edit.add(undo);

		// Adds a separator to the menu
		edit.addSeparator();

		JMenuItem cut = new JMenuItem(new ActionCut(PrimeMain1.texts
				.getString("cutLabel")));
		cut.setIcon(null);
		edit.add(cut);

		JMenuItem copy = new JMenuItem(new ActionCopy(PrimeMain1.texts
				.getString("copyLabel")));
		copy.setIcon(null);
		edit.add(copy);

		JMenuItem paste = new JMenuItem(new ActionPaste(PrimeMain1.texts
				.getString("pasteLabel")));
		paste.setIcon(null);
		edit.add(paste);


		this.add(edit);
	}


	/**
	 * This function creates the JMenu "Views". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuView()
	{
		// View menu
		view = new JMenu(PrimeMain1.texts.getString("viewLabel"));
		view.setMnemonic('V');


		JMenu submenu = new JMenu(PrimeMain1.texts
				.getString("messagesSupermenuLabel"));


		JMenuItem allViews = new JMenuItem(new ActionAllMessageView(
				PrimeMain1.texts.getString("messagesSupermenuAllMessages")));
		allViews.setIcon(null);
		submenu.add(allViews);


		// Adds a separator to the menu
		submenu.addSeparator();



		JMenuItem netMsg = new JMenuItem(new ActionNetworkMessage(
				PrimeMain1.texts.getString("messagesSupermenuNetworkMessages")));
		netMsg.setIcon(null);
		submenu.add(netMsg);


		JMenuItem conMsg = new JMenuItem(new ActionConnectionMessage(
				PrimeMain1.texts
						.getString("messagesSupermenuConnectionMessages")));
		conMsg.setIcon(null);
		submenu.add(conMsg);


		JMenuItem softMsg = new JMenuItem(
				new ActionSoftwareMessage(PrimeMain1.texts
						.getString("messagesSupermenuSoftwareMessages")));
		softMsg.setIcon(null);
		submenu.add(softMsg);


		JMenuItem hardMsg = new JMenuItem(
				new ActionHardwareMessage(PrimeMain1.texts
						.getString("messagesSupermenuHardwareMessages")));
		hardMsg.setIcon(null);
		submenu.add(hardMsg);


		view.add(submenu);



		this.add(view);
	}



	/**
	 * This function creates the JMenu "Tools". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuTools()
	{
		// Tools menu
		tools = new JMenu(PrimeMain1.texts.getString("toolsLabel"));
		tools.setMnemonic('T');


		JMenuItem openSettings = new JMenuItem(new ActionSettings(
				PrimeMain1.texts.getString("settingsMenuLabel")));
		openSettings.setIcon(null);
		tools.add(openSettings);



		this.add(tools);
	}


	/**
	 * This function creates the JMenu "Help". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuHelp()
	{
		// Help menu
		help = new JMenu(PrimeMain1.texts.getString("helpLabel"));
		help.setMnemonic('H');


		this.add(help);
	}


	/**
	 * This function sets the keystroke association with the spesific action.
	 * Eksampel: "Undo" with "Ctrl + Z" It sets the keystroke to the default
	 * keystroke chosen, which normally is the "Alt" key.
	 */
	private JMenuItem setKeystroke(JMenuItem item, char key)
	{
		item.setAccelerator(KeyStroke.getKeyStroke(key, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask(), false));

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
