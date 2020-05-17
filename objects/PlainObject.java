package objects;

import java.io.Serializable;
import java.util.HashMap;

import engine.StringTokenizer;

/**
 * The PlainObject is an object that is neither object, Door, nor anything else.
 * It's only interaction is basically looking at it.
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class PlainObject implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The object's name
	 */
	private String title;
	/**
	 * The object's description
	 */
	private String description;
	/**
	 * A map of the object's customized action responses
	 */
	private HashMap<String, String> responses = new HashMap<String, String>();

	/**
	 * Initializes an empty object
	 */
	public PlainObject()
	{
		this.title = new String();
		this.description = new String();
	}

	/**
	 * Initializes object with name
	 * 
	 * @param title The object's name
	 */
	public PlainObject(String title)
	{
		this.title = title;
		this.description = new String();
	}

	/**
	 * Initializes object with name and description
	 * 
	 * @param title The object's name
	 * @param description The object's description
	 */
	public PlainObject(String title, String description)
	{
		this.title = title;
		this.description = description;
	}

	/**
	 * Sets the object's name
	 * 
	 * @param title The object's name
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the object's name
	 * 
	 * @return The object's name
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Gets the object's name, processed for plural detection
	 * 
	 * @param processed Whether the name should be processed for plural detection
	 * @return The object's name
	 */
	public String getTitle(boolean processed)
	{
		if (processed)
			return StringTokenizer.process(this.title);
		else
			return this.title;
	}

	/**
	 * Sets the object description
	 * 
	 * @param description The object description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the object description
	 * 
	 * @return The object description
	 */
	public String getDescription()
	{
		if (description.length() == 0)
			return "There's nothing special to see.";
		else
			return description;
	}
	
	/**
	 * Adds a customized response to an action command
	 * 
	 * @param command The command to respond to
	 * @param response The object's response
	 */
	public void addResponse(String command, String response)
	{
		responses.put(command.toUpperCase(), response);
	}
	
	/**
	 * Gets the customized response to an action command
	 * 
	 * @param command The command to respond to
	 * @return response The object's response
	 */
	public String getResponse(String command)
	{
		return responses.get(command.toUpperCase());
	}

	/**
	 * Gets the object's name
	 * 
	 * @return The object's name
	 */
	public String toString()
	{
		return this.title;
	}
}
