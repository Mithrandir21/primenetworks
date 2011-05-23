/**
 * 
 */
package tests;


import scan.NetworkScan;
import scan.Subnet;


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

		netScan.getFoundHosts();
	}
}
