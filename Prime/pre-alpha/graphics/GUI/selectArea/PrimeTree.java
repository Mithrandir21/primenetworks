/**
 * 
 */
package graphics.GUI.selectArea;


import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class PrimeTree extends JPanel implements TreeSelectionListener
{
	/**
	 * Creates the file tree panel.
	 */
	public PrimeTree()
	{
		this.setLayout(new BorderLayout());

//		File[] roots = File.listRoots();
		File root = new File(".\\Data");
		FileTreeNode rootTreeNode = new FileTreeNode(root);
		this.tree = new JTree(rootTreeNode);
		this.tree.setCellRenderer(new FileTreeCellRenderer());
		this.tree.setRootVisible(false);
		final JScrollPane jsp = new JScrollPane(this.tree);
//		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.add(jsp, BorderLayout.CENTER);
	}


	/**
	 * File system view.
	 */
	protected static FileSystemView fsv = FileSystemView.getFileSystemView();

	/**
	 * Renderer for the file tree.
	 * 
	 * @author Kirill Grouchnikov
	 */
	private static class FileTreeCellRenderer extends DefaultTreeCellRenderer
	{
		/**
		 * Icon cache to speed the rendering.
		 */
		private Map<String, Icon> iconCache = new HashMap<String, Icon>();

		/**
		 * Root name cache to speed the rendering.
		 */
		private Map<File, String> rootNameCache = new HashMap<File, String>();

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent
		 * (javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int,
		 * boolean)
		 */
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
				boolean expanded, boolean leaf, int row, boolean hasFocus)
		{
			FileTreeNode ftn = (FileTreeNode) value;
			File file = ftn.file;
			String filename = "";
			if ( file != null )
			{
				if ( ftn.isFileSystemRoot )
				{
					// long start = System.currentTimeMillis();
					filename = this.rootNameCache.get(file);
					if ( filename == null )
					{
						filename = fsv.getSystemDisplayName(file);
						this.rootNameCache.put(file, filename);
					}
					// long end = System.currentTimeMillis();
					// System.out.println(filename + ":" + (end - start));
				}
				else
				{
					filename = file.getName();
				}
			}
			JLabel result = (JLabel) super.getTreeCellRendererComponent(tree, filename, sel,
					expanded, leaf, row, hasFocus);
			if ( file != null )
			{
				Icon icon = this.iconCache.get(filename);
				if ( icon == null )
				{
					// System.out.println("Getting icon of " + filename);
					icon = fsv.getSystemIcon(file);
					this.iconCache.put(filename, icon);
				}
				result.setIcon(icon);
			}
			return result;
		}
	}

	/**
	 * A node in the file tree.
	 * 
	 * @author Kirill Grouchnikov
	 */
	private static class FileTreeNode implements TreeNode
	{
		/**
		 * Node file.
		 */
		private File file;

		/**
		 * Children of the node file.
		 */
		private File[] children;

		/**
		 * Parent node.
		 */
		private TreeNode parent;

		/**
		 * Indication whether this node corresponds to a file system root.
		 */
		private boolean isFileSystemRoot;

		/**
		 * Creates a new file tree node.
		 * 
		 * @param file
		 *            Node file
		 * @param isFileSystemRoot
		 *            Indicates whether the file is a file system root.
		 * @param parent
		 *            Parent node.
		 */
		public FileTreeNode(File file, boolean isFileSystemRoot, TreeNode parent)
		{
			this.file = file;
			this.isFileSystemRoot = isFileSystemRoot;
			this.parent = parent;
			this.children = this.file.listFiles();
			if ( this.children == null )
				this.children = new File[0];
		}

		/**
		 * Creates a new file tree node.
		 * 
		 * @param children
		 *            Children files.
		 */
		public FileTreeNode(File[] children)
		{
			this.file = null;
			this.parent = null;
			this.children = children;
		}

		public FileTreeNode(File root)
		{
			this.file = null;
			this.parent = null;
			File[] children = { root };
			this.children = children;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#children()
		 */
		public Enumeration<?> children()
		{
			final int elementCount = this.children.length;
			return new Enumeration<File>()
			{
				int count = 0;

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Enumeration#hasMoreElements()
				 */
				public boolean hasMoreElements()
				{
					return this.count < elementCount;
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Enumeration#nextElement()
				 */
				public File nextElement()
				{
					if ( this.count < elementCount )
					{
						return FileTreeNode.this.children[this.count++];
					}
					throw new NoSuchElementException("Vector Enumeration");
				}
			};

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getAllowsChildren()
		 */
		public boolean getAllowsChildren()
		{
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getChildAt(int)
		 */
		public TreeNode getChildAt(int childIndex)
		{
			return new FileTreeNode(this.children[childIndex], this.parent == null, this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getChildCount()
		 */
		public int getChildCount()
		{
			return this.children.length;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
		 */
		public int getIndex(TreeNode node)
		{
			FileTreeNode ftn = (FileTreeNode) node;
			for ( int i = 0; i < this.children.length; i++ )
			{
				if ( ftn.file.equals(this.children[i]) )
					return i;
			}
			return -1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getParent()
		 */
		public TreeNode getParent()
		{
			return this.parent;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#isLeaf()
		 */
		public boolean isLeaf()
		{
			return (this.getChildCount() == 0);
		}
	}

	/**
	 * The file tree.
	 */
	private JTree tree;

	@Override
	public void valueChanged(TreeSelectionEvent arg0)
	{
		// TODO Auto-generated method stub

	}


	// private JTree tree;
	//
	//
	// public PrimeTree()
	// {
	// // Create a tree that allows several selections at a time.
	// if ( System.getProperty("os.name").toUpperCase().contains("WINDOWS") )
	// {
	// tree = new JTree(addNodes(null, new File(System
	// .getProperty("user.dir")
	// + "\\servers")));
	// }
	// else
	// {
	// tree = new JTree(addNodes(null, new File(System
	// .getProperty("user.dir")
	// + "/servers")));
	// }
	//
	// tree.getSelectionModel().setSelectionMode(
	// TreeSelectionModel.SINGLE_TREE_SELECTION);
	//
	// tree.setDragEnabled(true);
	//
	//
	// // Listen for when the selection changes.
	// tree.addTreeSelectionListener(this);
	//
	//
	//
	// this.add(tree);
	//
	// this.setPreferredSize(new Dimension(300, 300));
	//
	// }
	//
	//
	// public void valueChanged(TreeSelectionEvent e)
	// {
	// DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
	// .getLastPathComponent();
	// System.out.println("You selected " + node);
	// }
	//
	//
	//
	//
	// /** Add nodes from under "dir" into curTop. Highly recursive. */
	// DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir)
	// {
	// String curPath = dir.getPath();
	//
	// DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
	//
	// if ( curTop != null )
	// { // should only be null at root
	// curTop.add(curDir);
	// }
	//
	// Vector<String> ol = new Vector<String>();
	//
	// String[] tmp = dir.list();
	//
	// for ( int i = 0; i < tmp.length; i++ )
	// {
	// ol.addElement(tmp[i]);
	// }
	//
	// Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
	//
	// File f;
	//
	// Vector<String> files = new Vector<String>();
	//
	// // Make two passes, one for Dirs and one for Files. This is #1.
	// for ( int i = 0; i < ol.size(); i++ )
	// {
	// String thisObject = ol.elementAt(i);
	// String newPath;
	//
	// if ( curPath.equals(".") )
	// {
	// newPath = thisObject;
	// }
	// else
	// {
	// newPath = curPath + File.separator + thisObject;
	// }
	// if ( (f = new File(newPath)).isDirectory() )
	// {
	// addNodes(curDir, f);
	// }
	// else
	// {
	// files.addElement(thisObject);
	// }
	// }
	//
	// // Pass two: for files.
	// for ( int fnum = 0; fnum < files.size(); fnum++ )
	// {
	// curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
	// }
	//
	// return curDir;
	// }

}
