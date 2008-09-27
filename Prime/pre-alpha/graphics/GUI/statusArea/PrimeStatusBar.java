/**
 * 
 */
package graphics.GUI.statusArea;


import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXStatusBar;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class PrimeStatusBar extends JXStatusBar
{

	public PrimeStatusBar()
	{

		JLabel statusLabel = new JLabel("Ready");
		JXStatusBar.Constraint c1 = new JXStatusBar.Constraint();
		c1.setFixedWidth(100);
		this.add(statusLabel, c1);

		this.add(new JSeparator(SwingConstants.VERTICAL));

		JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL);
		JProgressBar pbar = new JProgressBar();

		this.add(pbar, c2);
	}
}
