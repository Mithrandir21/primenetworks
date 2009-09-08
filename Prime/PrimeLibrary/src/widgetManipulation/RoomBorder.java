/**
 * 
 */
package widgetManipulation;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import objects.Room;


/**
 * A class representing a {@link Border} specially created to be drawn to
 * represent {@link Room Rooms}.
 * 
 * @author Bahram Malaekeh
 */
public class RoomBorder extends LineBorder
{

	/**
	 * A constructor for this class that takes as parameter the color that the
	 * border.
	 * 
	 * @param color
	 *            The {@link Color} that will be drawn as the border.
	 */
	public RoomBorder(Color color)
	{
		super(color, 2, false);
	}



	/**
	 * Paints the border for the specified component with the specified position
	 * and size.
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
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height)
	{
		// The color of the border
		Color oldColor = g.getColor();

		g.setColor(lineColor);
		for ( int i = 0; i < thickness; i++ )
		{
			g.drawRoundRect(x + i, y + i, width - i - i - 1,
					height - i - i - 1, 7, 7);
		}
		g.setColor(oldColor);
	}

}
