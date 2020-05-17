package engine;

import java.io.*;
import java.util.*;

import objects.Item;
import objects.Location;

/**
 * The GameWorld object that contains all the game's content created in the editor
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class GameWorld implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Array of items the user carries
	 */
	private Vector<Item> inventory;
	/**
	 * Array of flags
	 */
	private Vector<Flag> flags;
	/**
	 * The room/location the player is currently in
	 */
	private Location currentLocation;
	/**
	 * Title card of the game, which is displayed on launch
	 */
	private String title;
	/**
	 * Whether a rudimentary plural detection is enabled
	 */
	private boolean plural;
	/**
	 * The main formatted output stream
	 */
	transient private WidthLimitedOutputStream output;
	
	/**
	 * GameWorld Constructor which sets a default output stream width of 80 characters
	 */
	public GameWorld()
	{
		this(80);
	}
	
	/**
	 * Initializes the variables
	 * 
	 * @param charWidth The output stream character width
	 */
	public GameWorld(int charWidth)
	{
		inventory = new Vector<Item>();
		flags = new Vector<Flag>();
		currentLocation = null;
		plural = false;
		
		output = new WidthLimitedOutputStream(System.out, charWidth);
	}
	
	/**
	 * Gets the formatted output stream
	 * @return WidthLimitedOutputStream
	 */
	public WidthLimitedOutputStream getOutput()
	{
		return output;
	}
	
	/**
	 * Gets the current room/location
	 * @return Current Location
	 */
	public Location getCurrentLocation()
	{
		return currentLocation;
	}
	
	/**
	 * Gets the inventory
	 * 
	 * @return Inventory
	 */
	public Vector<Item> getInventory()
	{
		return inventory;
	}
	
	/**
	 * Sets the current location
	 * 
	 * @param newLocation The new room/location
	 */
	public void setCurrentLocation(Location newLocation)
	{
		this.currentLocation = newLocation;
	}
	
	/**
	 * Creates a formatted output stream
	 * 
	 * @param out The main output stream
	 * @param width The maximum character width
	 */
	public void setOutputStream(OutputStream out, int width)
	{
		this.output = new WidthLimitedOutputStream(out, width);
	}
	
	/**
	 * Gets the title string that is displayed at launch
	 * 
	 * @return The game's title card
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Sets the title string that is displayed at launch
	 * 
	 * @param title The text that will be displayed at launch
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Gets whether plural detection is enabled
	 * 
	 * @return Whether plural detection is enabled
	 */
	public boolean getPlural()
	{
		return plural;
	}
	
	/**
	 * Enables/disables plural detection
	 * 
	 * @param plural Enabled: true/false
	 */
	public void setPlural(boolean plural)
	{
		this.plural = plural;
	}
	
	/**
	 * Adds a flag
	 * 
	 * @param title The flag's name
	 * @param state The flag's state
	 */
	public void addFlag(String title, boolean state)
	{
		Flag flag = new Flag(title, state);
		
		if(!flags.contains(flag))
			flags.add(flag);
	}
	
	/**
	 * Gets the flags
	 * 
	 * @return Flags
	 */
	public Vector<Flag> getFlags()
	{		
		return flags;
	}
	
	/**
	 * Prints the room description
	 * 
	 * @param withTitle Show title: true/false
	 */
	public void showLocation(boolean withTitle)
	{
		output.println();
		
		// Whether the title should be displayed, for example when entering a location
		if (withTitle)
			output.println("   " + currentLocation.getTitle().toUpperCase());
		
		// Print the description
		output.println(currentLocation.getDescription());
		
		// Check if there are items in the location
		if (currentLocation.getItems().size() > 0)
		{
			Vector<String> temp = new Vector<String>();
			
			String placedItems = "";
			
			for (Enumeration<Item> e = currentLocation.getItems().elements(); e.hasMoreElements();)
			{
				Item item = e.nextElement();
				
				// Check if the item was dropped or has no custom description
				if (item.isDropped() || item.getPlacement().length() == 0)
					temp.add("a " + item.getTitle().toLowerCase());
				else
					placedItems += item.getPlacement() + " ";
			}
			
			// Print custom descriptions
			if (placedItems.length() > 0)
				output.println(placedItems);
		
			String items = "";
			
			for (int i = 0; i < temp.size(); i++)
			{
				if (i == 0)
					items += temp.elementAt(i);
				else if (i == temp.size() - 1)
					items += "and " + temp.elementAt(i);
				else
					items += ", " + temp.elementAt(i);
			}
			
			// Print list of items
			if (items.length() > 0)
				output.println("There's " + items + " lying on the floor.");
		}
		
		output.println();
	}
	
	/**
	 * Drops an item from inventory to the location
	 * 
	 * @param item Item to drop
	 * @return Whether an item was dropped
	 */
	public boolean dropItem(Item item)
	{
		// Check if user has the item in their inventory
		if(inventory.contains(item))
		{
			inventory.remove(item);
			item.drop();
			currentLocation.addItem(item);
			return true;
		}
		
		return false;
	}
}
