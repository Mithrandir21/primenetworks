/**
 * 
 */
package serverPluginInterface;


import net.xeoh.plugins.base.Plugin;
import objects.Object;
import utils.ImplementationType;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public interface PrimeServerPluginInterface extends Plugin
{
	/**
	 * The {@link Object} that will be running the agent plugin.
	 */
	public Object mainObject = null;


	/**
	 * The name of the plugin.
	 * 
	 * <b>MUST match the agent plugin name!</b>
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
	 * <b>MUST match the agent plugin version!</b>
	 */
	public String getPluginVersion();


	/**
	 * TODO - Description
	 * 
	 */
	public void initiatPlugin(Object obj);


	/**
	 * TODO - Description
	 * 
	 */
	public void agentInputProcessing(String input);
}
