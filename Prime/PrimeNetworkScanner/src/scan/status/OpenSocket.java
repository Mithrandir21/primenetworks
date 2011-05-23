package scan.status;


import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;


/**
 * @author rakudave
 */

// TODO - @XStreamAlias("OpenSocket")
public class OpenSocket implements PingMethod
{
	private int port;

	public OpenSocket(int port) throws IllegalArgumentException
	{
		if ( port < 0 | port > 65535 )
			throw new IllegalArgumentException();
		this.port = port;
	}

	@Override
	public Status getStatus(InetAddress address)
	{
		try
		{
			Socket sock = new java.net.Socket(address, port);
			sock.close();
			return Status.UP;
		}
		catch ( ConnectException e )
		{
			return Status.DOWN;
		}
		catch ( IOException e )
		{
			return Status.UNKNOWN;
		}
	}

	public int getPort()
	{
		return port;
	}

	@Override
	public String toString()
	{
		return "TCP Port";
	}
}
