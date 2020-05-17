package objects;

import java.io.Serializable;

import engine.StringTokenizer;

/**
 * The Item object
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class Item implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The item's name
	 */
	private String title;
	/**
	 * The item's description
	 */
	private String description;
	/**
	 * If the item is placed in a location, this custom description can be set.
	 * Otherwise, it will default to a listing with just the item's name.
	 */
	private String placement;
	/**
	 * Whether the item has been dropped
	 */
	private boolean dropped;
	/**
	 * The item weight. Not fully implemented yet.
	 */
	private int weight;
	
	/**
	 * Initializes an empty Item
	 */
	public Item()
	{
		this.title = new String();
		this.description = new String();
		this.placement = new String();
		this.dropped = false;
		this.weight = 0;
	}

	/**
	 * Initializes Item with name
	 * 
	 * @param title The Item's name
	 */
	public Item(String title)
	{
		this.title = title;
		this.description = new String();
		this.placement = new String();
		this.dropped = false;
		this.weight = 0;
	}

	/**
	 * Initializes Item with name and description
	 * 
	 * @param title The Item's name
	 * @param description The Item's description
	 */
	public Item(String title, String description)
	{
		this.title = title;
		this.description = description;
		this.placement = new String();
		this.dropped = false;
		this.weight = 0;
	}

	/**
	 * Initializes Item with name, weight and description
	 * 
	 * @param title The Item's name
	 * @param description The Item's description
	 * @param weight The Item's weight
	 */
	public Item(String title, String description, int weight)
	{
		this.title = title;
		this.description = description;
		this.dropped = false;
		this.weight = weight;
	}

	/**
	 * Initializes Item
	 * 
	 * @param title The Item's name
	 * @param description The Item's description
	 * @param placement The Item's description in its initial location
	 * @param weight The Item's weight
	 */
	public Item(String title, String description, String placement, int weight)
	{
		this.title = title;
		this.description = description;
		this.placement = placement;
		this.dropped = false;
		this.weight = weight;
	}

	/**
	 * Gets the item's name
	 * 
	 * @return The item's name
	 */
	public String toString()
	{
		return title;
	}

	/**
	 * Sets the item's name
	 * 
	 * @param title The item's name
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the item's name
	 * 
	 * @return The item's name
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Gets the item's name, processed for plural detection
	 * 
	 * @param processed Whether the name should be processed for plural detection
	 * @return The item's name
	 */
	public String getTitle(boolean processed)
	{
		if (processed)
			return StringTokenizer.process(this.title);
		else
			return this.title;
	}
	
	/**
	 * Sets the item description
	 * 
	 * @param description The item description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Gets the item description
	 * 
	 * @return The item description
	 */
	public String getDescription()
	{
		String text;
		
		// If not set, return default
		if (description.length() > 0)
			text = description;
		else
			text = "There's nothing special to see.";
		
		return text;
	}
	
	/**
	 * When the item is first placed in a location, a custom description
	 * can be set. Otherwise defaults to a standard listing.
	 * 
	 * @param placement The description text
	 */
	public void setPlacement(String placement)
	{
		this.placement = placement;
	}
	
	/**
	 * Gets the placement description text
	 * 
	 * @return The description text
	 */
	public String getPlacement()
	{
		return placement; 
	}
	
	/**
	 * Sets the item weight
	 * 
	 * @param weight The item weight
	 */
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	/**
	 * Gets the item weight
	 * 
	 * @return The item weight
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * Sets the item to dropped
	 */
	public void drop()
	{
		this.dropped = true;
	}
	
	/**
	 * Gets whether the item has been dropped
	 * 
	 * @return Whether the item has been dropped
	 */
	public boolean isDropped()
	{
		return dropped;
	}
}
