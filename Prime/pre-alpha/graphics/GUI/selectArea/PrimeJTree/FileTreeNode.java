/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;


import java.io.File;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;


/**
 * A node in the file tree.
 * 
 * @author Kirill Grouchnikov Expanded by Bahram Malaekeh
 */
public class FileTreeNode extends DefaultMutableTreeNode
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


	/**
	 * Returns the file.
	 * 
	 * @return
	 */
	public File getFile()
	{
		return file;
	}


	/**
	 * Indication whether this node corresponds to a file system root.
	 * 
	 * @return
	 */
	public boolean isFileSystemRoot()
	{
		return isFileSystemRoot;
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
