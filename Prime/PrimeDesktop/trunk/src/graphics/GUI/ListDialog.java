/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 
 * - Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 
 * - Neither the name of Sun Microsystems nor the names of its
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package graphics.GUI;


import graphics.PrimeMain;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


/*
 * ListDialog.java is meant to be used by programs such as ListDialogRunner. It
 * requires no additional files.
 */

/**
 * Use this modal dialog to let the user choose one string from a long list. See
 * ListDialogRunner.java for an example of
 * using ListDialog. The basics:
 * 
 * <pre>
 * String[] choices = { &quot;A&quot;, &quot;long&quot;, &quot;array&quot;, &quot;of&quot;, &quot;strings&quot; };
 * 
 * String selectedName = ListDialog.showDialog(componentInControllingFrame,
 * 		locatorComponent, &quot;A description of the list:&quot;, &quot;Dialog Title&quot;,
 * 		choices, choices[0]);
 * </pre>
 */
public class ListDialog extends JDialog implements ActionListener
{
	private static ListDialog dialog;

	private static String value = "";

	private JList list;

	/**
	 * Set up and show the dialog. The first Component argument determines which
	 * frame the dialog depends on; it should
	 * be a component in the dialog's controlling frame. The second Component
	 * argument should be null if you want the
	 * dialog to come up with its left corner in the center of the screen;
	 * otherwise, it should be the component on top
	 * of which the dialog should appear.
	 */
	public static String showDialog(Component frameComp,
			Component locationComp, String labelText, String title,
			String[] possibleValues, String initialValue, String longValue)
	{
		Frame frame = JOptionPane.getFrameForComponent(frameComp);
		dialog = new ListDialog(frame, locationComp, labelText, title,
				possibleValues, initialValue, longValue);
		dialog.setVisible(true);
		return value;
	}


	/**
	 * Sets the value field of this class.
	 * 
	 * @param newValue
	 */
	private void setValue(String newValue)
	{
		value = newValue;
		this.list.setSelectedValue(value, true);
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param frame
	 * @param locationComp
	 * @param labelText
	 * @param title
	 * @param data
	 * @param initialValue
	 * @param longValue
	 */
	private ListDialog(Frame frame, Component locationComp, String labelText,
			String title, Object[] data, String initialValue, String longValue)
	{
		super(frame, title, true);

		// Create and initialize the buttons.
		JButton cancelButton = new JButton(PrimeMain.texts.getString("cancel"));
		cancelButton.setActionCommand(PrimeMain.texts.getString("cancel"));
		cancelButton.addActionListener(this);

		//
		final JButton setButton = new JButton(
				PrimeMain.texts
						.getString("actionCreateConnectionDescriptionText"));
		setButton.setActionCommand(PrimeMain.texts
				.getString("actionCreateConnectionDescriptionText"));
		setButton.addActionListener(this);
		getRootPane().setDefaultButton(setButton);


		// main part of the dialog
		this.list = new JList(data);
		this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		if ( longValue != null )
		{
			this.list.setPrototypeCellValue(longValue); // get extra space
		}
		this.list.setLayoutOrientation(JList.VERTICAL);
		// list.setVisibleRowCount(-1);
		this.list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if ( e.getClickCount() == 2 )
				{
					setButton.doClick(); // emulate button click
				}
			}
		});


		JScrollPane listScroller = new JScrollPane(this.list);
		listScroller.setPreferredSize(new Dimension(150, 80));
		listScroller.setAlignmentX(LEFT_ALIGNMENT);


		// Create a container so that we can add a title around
		// the scroll pane. Can't add a title directly to the
		// scroll pane because its background would be white.
		// Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel(labelText);
		label.setLabelFor(this.list);
		listPane.add(label);
		listPane.add(Box.createRigidArea(new Dimension(0, 5)));
		listPane.add(listScroller);
		listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(setButton);

		// Put everything together, using the content pane's BorderLayout.
		Container contentPane = getContentPane();
		contentPane.add(listPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

		// Initialize values.
		setValue(initialValue);
		pack();
		setLocationRelativeTo(locationComp);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JDialog#processWindowEvent(java.awt.event.WindowEvent)
	 */
	@Override
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);

		if ( e.getID() == WindowEvent.WINDOW_CLOSING )
		{
			switch ( getDefaultCloseOperation() )
			{
			case HIDE_ON_CLOSE:
			{
				ListDialog.value = "Closed";
				setVisible(false);
				break;
			}
			case DISPOSE_ON_CLOSE:
			{
				dispose();
				break;
			}
			case DO_NOTHING_ON_CLOSE:
			default:
				break;
			}
		}
	}


	// Handle clicks on the Set and Cancel buttons.
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals(
				PrimeMain.texts
						.getString("actionCreateConnectionDescriptionText")) )
		{
			ListDialog.value = (String) (this.list.getSelectedValue());
		}
		else if ( e.getActionCommand().equals(
				PrimeMain.texts.getString("cancel")) )
		{
			ListDialog.value = "Cancelled";
		}
		ListDialog.dialog.setVisible(false);
	}
}