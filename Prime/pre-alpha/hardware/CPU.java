package hardware;


import java.io.Serializable;

import objects.Hardware;


/**
 * This class represents a CPU within a {@link objects.Servers server} or
 * {@link objects.Clients client} machine. It can be a server, a desktop or a
 * laptop. It contains information on what kind of capability the CPU has and
 * what kind of system it can fit into.
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

	// The level 1 cache size in cpu.
	private int level1cacheSize;

	// The level 2 cache size in cpu.
	private int level2cacheSize;

	// The speed of the CPU in megahertz(Mhz).
	private int speed;

	// The number of cores on the CPU.
	private int cores;

	// The bus speed of the CPU.
	private int frontBusSpeed;

	// The nanometer of the CPU.
	private int nanometer;

	// The 64 bit variable.
	private boolean bit64;



	/**
	 * Constructor of a CPU hardware.
	 * 
	 * @param Name
	 *            The name of the CPU.
	 * @param Desc
	 *            The description of the CPU.
	 * @param CPUsocket
	 *            The socket type of the CPU. Socket 775,Socket 940 and so on.
	 */
	public CPU(String Name, String Desc, String CPUsocket)
	{
		super(Name, Desc);

		producer = "";
		socket = CPUsocket;
		level1cacheSize = 0;
		level2cacheSize = 0;
		frontBusSpeed = 0;
		nanometer = 0;
		bit64 = false;
	}



	/**
	 * Constructor of a CPU hardware.
	 * 
	 * @param Name
	 *            The name of the CPU.
	 * @param Desc
	 *            The description of the CPU.
	 * @param CPUsocket
	 *            The socket type of the CPU. Socket 775,Socket 940 and so on.
	 * @param CPUcacheSize
	 *            The cache size in MB. Normally 2 - 8 MB.
	 */
	public CPU(String Name, String Desc, String CPUsocket, int CPUcacheSize)
	{
		super(Name, Desc);

		producer = "";
		socket = CPUsocket;
		level1cacheSize = CPUcacheSize;
		level2cacheSize = 0;
		frontBusSpeed = 0;
		nanometer = 0;
		bit64 = false;
	}



	/**
	 * Constructor of a CPU hardware.
	 * 
	 * @param Name
	 *            The name of the CPU.
	 * @param Desc
	 *            The description of the CPU.
	 * @param CPUsocket
	 *            The socket type of the CPU. Socket 775,Socket 940 and so on.
	 * @param level1CPUcache
	 *            The cache size in KB. Normally 16 - 64 KB.
	 * @param level2CPUcache
	 *            The cache size in KB. Normally 256 - 1024 KB.
	 */
	public CPU(String Name, String Desc, String CPUsocket, int level1CPUcache, int level2CPUcache)
	{
		super(Name, Desc);

		producer = "";
		socket = CPUsocket;
		level1cacheSize = level1CPUcache;
		level2cacheSize = level2CPUcache;
		frontBusSpeed = 0;
		nanometer = 0;
		bit64 = false;
	}



	/**
	 * Constructor of a CPU hardware.
	 * 
	 * @param Name
	 *            The name of the CPU.
	 * @param Desc
	 *            The description of the CPU.
	 * @param CPUsocket
	 *            The socket type of the CPU. Socket 775,Socket 940 and so on.
	 * @param level1CPUcache
	 *            The cache size in KB. Normally 16 - 64 KB.
	 * @param level2CPUcache
	 *            The cache size in KB. Normally 256 - 1024 KB.
	 * @param fsb
	 *            The front side bus of the CPU.
	 */
	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param CPUsocket
	 * @param level1CPUcache
	 * @param level2CPUcache
	 * @param fsb
	 */
	public CPU(String Name, String Desc, String CPUsocket, int level1CPUcache, int level2CPUcache,
			int fsb)
	{
		super(Name, Desc);

		producer = "";
		socket = CPUsocket;
		level1cacheSize = level1CPUcache;
		level2cacheSize = level2CPUcache;
		frontBusSpeed = fsb;
		nanometer = 0;
		bit64 = false;
	}



	/**
	 * Constructor of a CPU hardware.
	 * 
	 * @param Name
	 *            The name of the CPU.
	 * @param Desc
	 *            The description of the CPU.
	 * @param CPUsocket
	 *            The socket type of the CPU. Socket 775,Socket 940 and so on.
	 * @param level1CPUcache
	 *            The cache size in KB. Normally 16 - 64 KB.
	 * @param level2CPUcache
	 *            The cache size in KB. Normally 256 - 1024 KB.
	 * @param fsb
	 *            The front side bus of the CPU.
	 * @param nm
	 *            The nanometer of the CPU. Its the size that one block takes
	 *            up.
	 */
	public CPU(String Name, String Desc, String CPUsocket, int level1CPUcache, int level2CPUcache,
			int fsb, int nm)
	{
		super(Name, Desc);

		producer = "";
		socket = CPUsocket;
		level1cacheSize = level1CPUcache;
		level2cacheSize = level2CPUcache;
		frontBusSpeed = fsb;
		nanometer = nm;
		bit64 = false;
	}



	/**
	 * Constructor of a CPU hardware.
	 * 
	 * @param Name
	 *            The name of the CPU.
	 * @param Desc
	 *            The description of the CPU.
	 * @param CPUsocket
	 *            The socket type of the CPU. Socket 775,Socket 940 and so on.
	 * @param level1CPUcache
	 *            The cache size in KB. Normally 16 - 64 KB.
	 * @param level2CPUcache
	 *            The cache size in KB. Normally 256 - 1024 KB.
	 * @param fsb
	 *            The front side bus of the CPU.
	 * @param nm
	 *            The nanometer of the CPU. Its the size that one block takes
	 *            up.
	 * @param bit
	 *            The boolean variable that tells if the CPU is a 64 bit CPU.
	 */
	public CPU(String Name, String Desc, String CPUsocket, int level1CPUcache, int level2CPUcache,
			int fsb, int nm, boolean bit)
	{
		super(Name, Desc);

		producer = "";
		socket = CPUsocket;
		level1cacheSize = level1CPUcache;
		level2cacheSize = level2CPUcache;
		frontBusSpeed = fsb;
		nanometer = nm;
		bit64 = bit;
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
	 * Get the level 1 cache size of the CPU.
	 * 
	 */
	public int getLevel1CacheSize()
	{
		return level1cacheSize;
	}


	/**
	 * Get the level 2 cache size of the CPU.
	 * 
	 */
	public int getLevel2CacheSize()
	{
		return level2cacheSize;
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
	 * Get the bus speed of the CPU. The speed of transfer between the main
	 * components of the system.
	 * 
	 */
	public int getBusSpeed()
	{
		return frontBusSpeed;
	}


	/**
	 * Get the nanometer(size of one block) of the CPU.
	 * 
	 */
	public int getNanometer()
	{
		return nanometer;
	}


	/**
	 * Returns a boolean on whether or not the CPU is a 64 bit CPU.
	 * 
	 */
	public boolean is64Bit()
	{
		return bit64;
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	public boolean isDualCore()
	{
		if ( cores == 2 )
		{
			return true;
		}

		return false;
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	public boolean isQuadCore()
	{
		if ( cores == 4 )
		{
			return true;
		}

		return false;
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
	 * Set method for level 1 cache size of the CPU.
	 * 
	 */
	public void setLevel1CacheSize(int CPUcacheSize)
	{
		level1cacheSize = CPUcacheSize;
	}


	/**
	 * Set method for level 2 cache size of the CPU.
	 * 
	 */
	public void setLevel2CacheSize(int CPUcacheSize)
	{
		level2cacheSize = CPUcacheSize;
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
	 * Set method for FSB of the CPU.
	 * 
	 */
	public void setFSB(int CPUbusSpeed)
	{
		frontBusSpeed = CPUbusSpeed;
	}


	/**
	 * Sets the nanometer(size of one block) of the CPU.
	 * 
	 */
	public void setNanometer(int nm)
	{
		nanometer = nm;
	}


	/**
	 * Sets a boolean on whether or not the CPU is a 64 bit CPU.
	 * 
	 */
	public void set64Bit(boolean bit)
	{
		bit64 = bit;
	}

}
