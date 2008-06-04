package graphics.GUI.selectArea;



/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */


import graphics.ImageLocator;
import graphics.PrimeMain1;

import javax.swing.*;

public class TabbedSelection extends JTabbedPane
{
    public TabbedSelection() 
    {
        
        
        ImageIcon icon = PrimeMain1.JavaIcon;
        
        
        
        JComponent panel1 = new PrimeTree();
        this.addTab("Network Models", icon, panel1, "Network Models");
        
        
        
        JComponent panel4 = new ObjectSelection();
        JScrollPane scrollArea = new JScrollPane(panel4);
        this.addTab("unit Area", icon, scrollArea, "Unit Area");
        
        
        
        
        //The following line enables to use scrolling tabs.
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}