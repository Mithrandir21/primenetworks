package scan.status;


import java.awt.Color;


/**
 * @author rakudave
 */
public enum Status
{
	UP("online", Color.GREEN), DOWN("offline", Color.RED), NOT_FOUND(
			"not found", Color.ORANGE), UNKNOWN("unknown", Color.GRAY);

	private String message;

	private Color color;

	Status(String message, Color color)
	{
		this.message = message;
		this.color = color;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color c)
	{
		color = c;
		// TODO - Settings.put("status.color." + toString().toLowerCase(),
		// color.getRGB());
	}

	public String getMessage()
	{
		return message;
	}
}
