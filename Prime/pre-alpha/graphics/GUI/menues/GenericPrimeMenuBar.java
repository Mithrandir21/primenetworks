/**
 * 
 */
package graphics.GUI.menues;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import actions.*;

/**
 * This class creates the a generic {@link  JMenuBar  JMenuBar} for the main windows of the program.
 * This JMenuBar will include the menues "File", "Edit", "Tools" and "Help".
 * (More menues should be added at a later point.) 
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class GenericPrimeMenuBar extends JMenuBar implements ActionListener
{
	
	
	
	ImageIcon icon = createImageIcon("images/java.jpg");
	
	// The menu buttons at the top of the screen
	private JMenu file,edit,tools,help;

	
	
	/**
	 * This function class all the functions that initiate the creation of the different menues.
	 *
	 */
	public GenericPrimeMenuBar()
	{
		initMenuFile();
		
		initMenuEdit();
		
		initMenuTools();
		
		initMenuHelp();
	}
	
	
	
	
//	 SETUP FOR THE MENUES
	
	/**
	 * This function creates the JMenu "File". It adds the actions "New", "Open", "Save", "Save All" and "Exit".
	 * (More actions will be added at a later stage.)
	 * 
	 */
	private void initMenuFile()
	{
		// File menu
		file = new JMenu("File");
		file.setMnemonic( 'F' );
		
		// Adds different items to the "File" menu
		
		JMenuItem newFile = new JMenuItem(new ActionNew("New",createImageIcon("images/buttonIcons/new.jpg")));
		newFile.setIcon(null);
		file.add(newFile);
		
		JMenuItem openFile = new JMenuItem(new ActionOpenfile("Open File",
				createImageIcon("images/buttonIcons/open.jpg")));
		openFile.setIcon(null);
		file.add(openFile);
		
		JMenuItem saveFile = new JMenuItem(new ActionSave("Save",
				createImageIcon("images/buttonIcons/save.jpg")));
		saveFile.setIcon(null);
		file.add(saveFile);
		
		JMenuItem saveAll = new JMenuItem(new ActionSaveAll("Save All",
				createImageIcon("images/buttonIcons/save_all.jpg")));
		saveAll.setIcon(null);
		file.add(saveAll);
		
		// Adds a separator to the menu
		file.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		
		
		this.add(file);
	}
	
	
	/**
	 * This function creates the JMenu "File". It adds the actions "Undo", "Cut", "Copy" and "Paste".
	 * (More actions will be added at a later stage.)
	 * 
	 */
	private void initMenuEdit()
	{
		// Edit menu
		edit = new JMenu("Edit");
		edit.setMnemonic( 'E' );
		
		// Adds different items to the "Edit" menu
		
		JMenuItem undo = new JMenuItem(new ActionSaveAll("Save All",null));
		undo.setIcon(null);
		edit.add(undo);
		
		// Adds a separator to the menu
		edit.addSeparator();
		
		JMenuItem cut = new JMenuItem(new ActionCut("Cut",
				createImageIcon("images/buttonIcons/cut.jpg")));
		cut.setIcon(null);
		edit.add(cut);
		
		JMenuItem copy = new JMenuItem(new ActionCopy("Copy",
				createImageIcon("images/buttonIcons/copy.jpg")));
		copy.setIcon(null);
		edit.add(copy);
		
		JMenuItem paste = new JMenuItem(new ActionPaste("Paste",
				createImageIcon("images/buttonIcons/paste.jpg")));
		paste.setIcon(null);
		edit.add(paste);

		
		
		
		this.add(edit);
	}
	
	
	/**
	 * This function creates the JMenu "Tools".
	 * (More actions will be added at a later stage.)
	 * 
	 */
	private void initMenuTools()
	{
		// Tools menu
		tools = new JMenu("Tools");
		tools.setMnemonic( 'T' );
		
		
		this.add(tools);
	}
	
	
	/**
	 * This function creates the JMenu "Help".
	 * (More actions will be added at a later stage.)
	 * 
	 */
	private void initMenuHelp()
	{
		// Help menu
		help = new JMenu("Help");
		help.setMnemonic( 'H' );
		
		
		this.add(help);
	}	
	
	
	/**
	 * This function sets the keystroke association with the spesific action.
	 * Eksampel:
	 * "Undo" with "Ctrl + Z" 
	 *
	 * It sets the keystroke to the default keystroke choosen, which normally is the "Ctrl" key.
	 * 
	 * It also adds an actionlistner to the Item.
	 */
	private JMenuItem setKeystroke(JMenuItem item, char key)
	{
		item.setAccelerator(KeyStroke.getKeyStroke(key,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		
		item.addActionListener(this);
		
		return item;
	}




	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		JMenuItem source = (JMenuItem) e.getSource();
		
		source.getName();
		
		if(source.getText().equals("undo"));
		{
			JOptionPane.showMessageDialog(null,"You want to perform a \"Undo33\" action.");
		}
	}
	


	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
