/**
 * 
 */
package tests;


import java.net.InetAddress;
import java.util.Map;

import scan.NetworkScan;
import scan.Subnet;
import scan.status.PingMethod;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ScanTest
{
	private static NetworkScan netScan;

	public static void main(String[] args)
	{
		ScanTest();
	}

	/**
	 * TODO - Description NEEDED!
	 */
	public static void ScanTest()
	{
		netScan = new NetworkScan(new Subnet("192.168.0.150", "255.255.255.0"));

		netScan.start(false);

		while ( !netScan.isDone() )
		{
			// Wait
		}

		Map<InetAddress, PingMethod> m = netScan.getFoundHosts();

		for ( Map.Entry<InetAddress, PingMethod> entry : m.entrySet() )
		{
			System.out.println(entry.getKey() + "/" + entry.getValue()
					+ " - Is Up");
		}
	}
}
