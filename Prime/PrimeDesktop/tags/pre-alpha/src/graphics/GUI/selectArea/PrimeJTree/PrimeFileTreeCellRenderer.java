/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;


import java.awt.Component;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;


/**
 * Renderer for the file tree.
 * 
 * @author Kirill Grouchnikov Expanded by Bahram Malaekeh
 */
public class PrimeFileTreeCellRenderer extends DefaultTreeCellRenderer
{
	/**
	 * Icon cache to speed the rendering.
	 */
	private Map<String, Icon> iconCache = new HashMap<String, Icon>();

	/**
	 * Root name cache to speed the rendering.
	 */
	private Map<File, String> rootNameCache = new HashMap<File, String>();

	/**
	 * File system view.
	 */
	protected static FileSystemView fsv = FileSystemView.getFileSystemView();


	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent (javax.swing.JTree, java.lang.Object,
	 * boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
			boolean leaf, int row, boolean hasFocus)
	{
		FileTreeNode ftn = (FileTreeNode) value;
		File file = ftn.getFile();
		String filename = "";

		if ( file != null )
		{
			if ( ftn.isFileSystemRoot() )
			{
				filename = this.rootNameCache.get(file);
				if ( filename == null )
				{
					filename = fsv.getSystemDisplayName(file);
					this.rootNameCache.put(file, filename);
				}
			}
			else
			{
				filename = file.getName();
			}
		}

		JLabel result = (JLabel) super.getTreeCellRendererComponent(tree, filename, sel, expanded, leaf, row, hasFocus);

		if ( file != null )
		{
			Icon icon = this.iconCache.get(filename);
			if ( icon == null )
			{
				icon = fsv.getSystemIcon(file);
				this.iconCache.put(filename, icon);
			}

			result.setIcon(icon);
		}

		return result;
	}
}
