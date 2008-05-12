package hardware;
import java.io.Serializable;
import objects.Hardware;


/**
 * This class represents a CPU within a {@link  objects.Servers  server} or 
 * {@link  objects.Clients  client} machine. 
 * It can be a server, a desktop or a laptop. It contains information on what kind 
 * of capability the CPU has and what kind of system it can fit into.
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class CPU extends Hardware implements Serializable 
{

	
	// The company that produces the CPU. Intel, AMD and so on.
	private String producer;
	
	// The socket type of the CPU, Socket 775,Socket 940 and so on. 
	private String socket;
	
	// The cache size in MB.
	private int cacheSize;
	
	// The speed of the CPU
	private int speed;
	
	// The number of cores on the CPU
	private int cores;
	
	// The bus speed of the CPU
	private int busSpeed;
	

	
	/**
	 * Constructor of a CPU hardware. 
	 *
	 * @param Name The name of the CPU.
	 * @param Desc The description of the CPU.
	 * @param CPUsocket The socket type of the CPU. Socket 775,Socket 940 and so on. 
	 * @param CPUcacheSize The cache size in MB. Normally 2 - 8 MB.
	 */
	public CPU(String Name, String Desc, String CPUsocket,int CPUcacheSize)
	{
		super(Name,Desc);

		socket = CPUsocket;
		cacheSize = CPUcacheSize;
	}
	
	
	// Get and Set methodes for retrieving all datafields.
	
	// GET METHODES

	/**
	 * Get producer of the CPU.
	 * 
	 */
	public String getProducer()
	{
		return producer;
	}
		

	/**
	 * Get the socket of the CPU.
	 * 
	 */
	public String getSocket()
	{
		return socket;
	}	
	

	/**
	 * Get the cache size of the CPU.
	 * 
	 */
	public int getCacheSize()
	{
		return cacheSize;
	}	
	

	/**
	 * Get the speed of the CPU.
	 * 
	 */
	public int getSpeed()
	{
		return speed;
	}	
	

	/**
	 * Get the number of cores in the CPU.
	 * 
	 */
	public int getCores()
	{
		return cores;
	}
	
	// Get the busSpeed of the CPU
	/**
	 * Get the bus speed of the CPU. The speed of transfere between the main 
	 * components of the system.
	 * 
	 */
	public int getBusSpeed()
	{
		return busSpeed;
	}
	
	
	// SET METHODES
	/**
	 * Set method for producers of the CPU.
	 * 
	 */
	public void setProducer(String CPUProducer)
	{
		producer = CPUProducer;
	}
	

	/**
	 * Set method for the socket of the CPU.
	 * 
	 */
	public void setSocketType(String CPUsocket)
	{
		socket = CPUsocket;
	}
	

	/**
	 * Set method for cache size of the CPU.
	 * 
	 */
	public void setCacheSize(int CPUcacheSize)
	{
		cacheSize = CPUcacheSize;
	}
	

	/**
	 * Set method for speed of the CPU.
	 * 
	 */
	public void setSpeed(int CPUspeed)
	{
		speed = CPUspeed;
	}
	

	/**
	 * Set method for number of cores of the CPU.
	 * 
	 */
	public void setCPUcores(int CPUcores)
	{
		cores = CPUcores;
	}
	

	/**
	 * Set method for RPM of the CPU.
	 * 
	 */
	public void setRPM(int CPUbusSpeed) 
	{
		busSpeed = CPUbusSpeed;
	}
	
}
