/**
 * 
 */
package agentPluginInterface;


import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.annotations.events.Init;
import utils.ImplementationType;




/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public interface PrimeAgentPluginInterface extends Plugin, Runnable
{
	/**
	 * The name of the plugin.
	 * 
	 * <b>MUST match the server plugin name!</b>
	 */
	public String getPluginName();


	/**
	 * The description of the plugin.
	 */
	public String getPluginDescription();


	/**
	 * TODO - Description
	 * 
	 */
	public String getPluginAuthor();


	/**
	 * TODO - Description
	 * 
	 */
	public ImplementationType getImplementationType();


	/**
	 * The version of the plugin.
	 * 
	 * <b>MUST match the server plugin version!</b>
	 */
	public String getPluginVersion();


	/**
	 * TODO - Description
	 * 
	 */
	@Init
	public void initiatePluging();


	/**
	 * TODO - Description
	 * 
	 */
	public void destroy();


	/**
	 * TODO - Description
	 * 
	 */
	public String getPluginOutput();


	/**
	 * TODO - Description
	 * 
	 */
	public void setFinished();


	/**
	 * TODO - Description
	 * 
	 */
	public boolean isFinished();


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run();
}
