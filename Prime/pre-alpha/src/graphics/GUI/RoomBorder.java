/**
 * 
 */
package graphics.GUI;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.border.LineBorder;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class RoomBorder extends LineBorder
{

	public RoomBorder(Color color)
	{
		super(color, 2, true);
	}



	/**
	 * Paints the border for the specified component with the specified position and size.
	 * 
	 * @param c
	 *            the component for which this border is being painted
	 * @param g
	 *            the paint graphics
	 * @param x
	 *            the x position of the painted border
	 * @param y
	 *            the y position of the painted border
	 * @param width
	 *            the width of the painted border
	 * @param height
	 *            the height of the painted border
	 */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		// The color of the border
		Color oldColor = g.getColor();

		g.setColor(lineColor);
		for ( int i = 0; i < thickness; i++ )
		{
			g.drawRoundRect(x + i, y + i, width - i - i - 1, height - i - i - 1, 7, 7);
		}
		g.setColor(oldColor);
	}

}
