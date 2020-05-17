package engine;

import java.io.*;

/**
 * Flags to set states during the game. Not yet fully implemented.
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class Flag implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The flag's name
	 */
	private String title;
	/**
	 * The flag's state
	 */
	private boolean state;
	
	/**
	 * Initializes an empty flag
	 */
	public Flag()
	{
		title = new String();
		state = false;
	}
	
	/**
	 * Initializes a flag with ID, defaults to false
	 * 
	 * @param title The flag's name
	 */
	public Flag(String title)
	{
		this.title = title;
		state = false;
	}

	
	/**
	 * Initializes a flag
	 * 
	 * @param title The flag's name
	 * @param state The flag's state
	 */
	public Flag(String title, boolean state)
	{
		this.title = title;
		this.state = state;
	}

	/**
	 * Gets the flag's name
	 * 
	 * @return The flag's name
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Sets the flag's name
	 * 
	 * @param title The flag's name
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the flag's state
	 * 
	 * @return The flag's state
	 */
	public boolean getState()
	{
		return state;
	}

	/**
	 * Sets the flag's state
	 * 
	 * @param state The flag's state
	 */
	public void setState(boolean state)
	{
		this.state = state;
	}
}
