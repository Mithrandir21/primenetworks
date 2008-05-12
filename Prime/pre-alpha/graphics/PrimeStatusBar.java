/**
 * 
 */
package graphics;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import org.jdesktop.swingx.JXStatusBar;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version
 */
public class PrimeStatusBar extends JXStatusBar
{

	public PrimeStatusBar()
	{
		
		JLabel statusLabel = new JLabel("Ready");
	    JXStatusBar.Constraint c1 = new JXStatusBar.Constraint();
	    c1.setFixedWidth(100);
	    this.add(statusLabel, c1);
	    
	    this.add(new JSeparator(JSeparator.VERTICAL));
	    
	    JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(JXStatusBar.Constraint.ResizeBehavior.FILL);
	    JProgressBar pbar = new JProgressBar();
	    
	    this.add(pbar,c2);
	}
}
