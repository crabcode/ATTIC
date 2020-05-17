package objects;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Crabman
 * @version 1.0.0
 */
public class Location implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The location name
	 */
	private String roomTitle;
	/**
	 * The location description
	 */
	private String roomDescription;
	/**
	 * The Exits from the location
	 */
	private Vector<Exit> exits;
	/**
	 * The Items in the location
	 */
	private Vector<Item> items;
	/**
	 * The objects in the location
	 */
	private Vector<PlainObject> plainObjects;
	/**
	 * The doors in the location
	 */
	private Vector<Door> doors;
	
	/**
	 * Initializes an empty Location
	 */
	public Location()
	{
		this.roomTitle = new String();
		this.roomDescription = new String();
		this.exits = new Vector<Exit>();
		this.items = new Vector<Item>();
		this.plainObjects = new Vector<PlainObject>();
		this.doors = new Vector<Door>();
	}
	
	/**
	 * Initializes a Location with a name
	 * 
	 * @param roomTitle The location's name
	 */
	public Location(String roomTitle)
	{
		this.roomTitle = roomTitle;
		this.roomDescription = new String();
		this.exits = new Vector<Exit>();
		this.items = new Vector<Item>();
		this.plainObjects = new Vector<PlainObject>();
		this.doors = new Vector<Door>();
	}

	/**
	 * Initializes a Location with a name and description
	 * 
	 * @param roomTitle The location's name
	 * @param roomDescription The location's description
	 */
	public Location(String roomTitle, String roomDescription)
	{
		this.roomTitle = roomTitle;
		this.roomDescription = roomDescription;
		this.exits = new Vector<Exit>();
		this.items = new Vector<Item>();
		this.plainObjects = new Vector<PlainObject>();
		this.doors = new Vector<Door>();
	}
	
	/**
	 * Gets the location's name
	 * 
	 * @return The location's name
	 */
	public String toString()
	{		
		return roomTitle;
	}

	/**
	 * Sets the location's name
	 * 
	 * @param roomTitle The location's name
	 */
	public void setTitle(String roomTitle)
	{
		this.roomTitle = roomTitle;
	}

	/**
	 * Gets the location's name
	 * 
	 * @return The location's name
	 */
	public String getTitle()
	{
		return roomTitle; 
	}
	

	/**
	 * Sets the location description
	 * 
	 * @param roomDescription The item description
	 */
	public void setDescription(String roomDescription)
	{
		this.roomDescription = roomDescription;
	}

	/**
	 * Gets the location description
	 * 
	 * @return The item description
	 */
	public String getDescription()
	{
		return roomDescription; 
	}
	
	/**
	 * Adds an Exit to the location
	 * 
	 * @param exit The Exit to add
	 * @return Whether the adding was successful
	 */
	public boolean addExit(Exit exit)
	{
		return exits.add(exit);
	}

	/**
	 * Removes an Exit from the location
	 * 
	 * @param exit The Exit to remove
	 * @return Whether the removing was successful
	 */
	public boolean removeExit(Exit exit)
	{
		if (exits.contains(exit))
		{
			exits.removeElement(exit);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets a list of the location's Exits
	 * 
	 * @return List of the location's Exits
	 */
	@SuppressWarnings("unchecked")
	public Vector<Exit> getExits()
	{
		return (Vector<Exit>) exits.clone();
	}

	/**
	 * Adds an Item to the location
	 * 
	 * @param item The Item to add
	 * @return Whether the adding was successful
	 */
	public boolean addItem(Item item)
	{
		if (!items.contains(item))
			return items.add(item);
		
		return false;
	}

	/**
	 * Removes an Item from the location
	 * 
	 * @param item The Item to remove
	 * @return Whether the removing was successful
	 */
	public boolean removeItem(Item item)
	{
		if (items.contains(item))
		{
			items.removeElement(item);
			return true;
		}
		
		return false;
	}

	/**
	 * Gets a list of the location's Items
	 * 
	 * @return List of the location's Items
	 */
	@SuppressWarnings("unchecked")
	public Vector<Item> getItems()
	{
		return (Vector<Item>) items.clone();
	}

	/**
	 * Adds an object to the location
	 * 
	 * @param plainObject The object to add
	 * @return Whether the adding was successful
	 */
	public boolean addPlainObject(PlainObject plainObject)
	{
		if (!plainObjects.contains(plainObject))
			return plainObjects.add(plainObject);
		
		return false;
	}

	/**
	 * Removes an object from the location
	 * 
	 * @param plainObject The object to remove
	 * @return Whether the removing was successful
	 */
	public boolean removePlainObject(PlainObject plainObject)
	{
		if (plainObjects.contains(plainObject))
		{
			plainObjects.removeElement(plainObject);
			return true;
		}
		
		return false;
	}

	/**
	 * Gets a list of the location's objects
	 * 
	 * @return List of the location's objects
	 */
	@SuppressWarnings("unchecked")
	public Vector<PlainObject> getPlainObjects()
	{
		return (Vector<PlainObject>) plainObjects.clone();
	}

	/**
	 * Adds a Door to the location
	 * 
	 * @param door The Door to add
	 * @return Whether the adding was successful
	 */
	public boolean addDoor(Door door)
	{
		if (!doors.contains(door))
			return doors.add(door);
		
		return false;
	}

	/**
	 * Removes a Door from the location
	 * 
	 * @param door The Door to remove
	 * @return Whether the removing was successful
	 */
	public boolean removeDoor(Door door)
	{
		if (doors.contains(door))
		{
			doors.removeElement(door);
			return true;
		}
		
		return false;
	}

	/**
	 * Gets a list of the location's Doors
	 * 
	 * @return List of the location's Doors
	 */
	public Vector<Door> getDoors()
	{
		return doors;
	}
}
