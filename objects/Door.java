package objects;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

import engine.StringTokenizer;

/**
 * The door/container object. Doors act as both gates to exits as well as containers, which can be locked, open or closed.
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class Door implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * OPEN state
	 */
	public final int OPEN = 0;
	/**
	 * CLOSED state
	 */
	public final int CLOSED = 1;
	/**
	 * LOCKED state
	 */
	public final int LOCKED = 2;
	
	/**
	 * The door/container's name
	 */
	private String title;
	/**
	 * The door/container's response when open
	 */
	private String stateOpen;
	/**
	 * The door/container's response when closed
	 */
	private String stateClosed;
	/**
	 * The door/container's response when locked
	 */
	private String stateLocked;
	/**
	 * The Exit behind the door
	 */
	private Exit exit;
	/**
	 * The key if locked
	 */
	private Item key;
	/**
	 * The containing items if container
	 */
	private Vector<Item> items;
	/**
	 * The door's open/closed state
	 */
	private boolean open;
	/**
	 * The door's locked/unlocked state
	 */
	private boolean locked;
	/**
	 * Whether a door or container
	 */
	private boolean container;
	
	/**
	 * Initializes empty Door
	 */
	public Door()
	{
		this.title = new String();
		this.exit = new Exit();
		this.items = new Vector<Item>();
		this.key = new Item();
		
		this.stateOpen = new String();
		this.stateClosed = new String();
		this.stateLocked = new String();
		
		this.open = false;
		this.locked = false;
		this.container = false;
	}

	/**
	 * Initializes Door with name
	 * 
	 * @param title The door's name
	 */
	public Door(String title)
	{
		this.title = title;
		this.exit = new Exit();
		this.items = new Vector<Item>();
		this.key = new Item();
		
		this.stateOpen = new String();
		this.stateClosed = new String();
		this.stateLocked = new String();
		
		this.open = false;
		this.locked = false;
		this.container = false;
	}

	/**
	 * Initializes Door
	 * 
	 * @param title The door's name
	 * @param exit The connected Location
	 */
	public Door(String title, Exit exit)
	{
		this.title = title;
		this.exit = exit;
		this.items = new Vector<Item>();
		this.key = new Item();
		
		this.stateOpen = new String();
		this.stateClosed = new String();
		this.stateLocked = new String();
		
		this.open = false;
		this.locked = false;
		this.container = false;
	}

	/**
	 * Initializes door/container
	 * 
	 * @param title The door/container's name
	 * @param container Whether door or container
	 * @param open Whether opened or closed
	 */
	public Door(String title, boolean container, boolean open)
	{
		this.title = title;
		this.exit = new Exit();
		this.items = new Vector<Item>();
		this.key = new Item();
		
		this.stateOpen = new String();
		this.stateClosed = new String();
		this.stateLocked = new String();
		
		this.locked = false;
		this.open = open;
		this.container = container;
	}


	/**
	 * Initializes door
	 * 
	 * @param title The door's name
	 * @param exit The connected Location
	 * @param open Whether opened or closed
	 */
	public Door(String title, Exit exit, boolean open)
	{
		this.title = title;
		this.exit = exit;
		this.items = new Vector<Item>();
		this.key = new Item();
		
		this.stateOpen = new String();
		this.stateClosed = new String();
		this.stateLocked = new String();
		
		this.open = open;
		this.locked = false;
		this.container = false;
	}
	
	/**
	 * Gets the door/container's name
	 * 
	 * @return The door/container's name
	 */
	public String toString()
	{
		return this.title;
	}
	
	/**
	 * Sets open and closed responses
	 * 
	 * @param stateOpen Text to display when open
	 * @param stateClosed Text to display when closed
	 */
	public void setMessages(String stateOpen, String stateClosed)
	{
		this.stateOpen = stateOpen;
		this.stateClosed = stateClosed;
	}
	
	/**
	 * Sets the door/container's locked state and message
	 * 
	 * @param stateLocked Text to display when locked
	 * @param locked Whether the door/container is locked
	 */
	public void setLocked(String stateLocked, boolean locked)
	{
		this.stateLocked = stateLocked;
		this.locked = locked;
	}

	/**
	 * Sets the door/container's name
	 * 
	 * @param title The door/container's name
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the door/container's name
	 * 
	 * @return The door/container's name
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Gets the door/container's name, processed for plural detection
	 * 
	 * @param processed Whether the name should be processed for plural detection
	 * @return The door/container's name
	 */
	public String getTitle(boolean processed)
	{
		if (processed)
			return StringTokenizer.process(this.title);
		else
			return this.title;
	}

	/**
	 * Gets the door/container's description
	 * 
	 * @return The door/container's description
	 */
	public String getDescription()
	{
		// Check if it's locked and return custom message, if one exists
		if (isLocked())
			return getState(this.LOCKED).length() > 0 ? getState(this.LOCKED) : "There's nothing special to see.";
		else if (isOpen())
		{
			// Check if container and print items contained
			if (isContainer())
			{
				Vector<String> temp = new Vector<String>();
				
				for (Enumeration<Item> e = getItems().elements(); e.hasMoreElements();)
				{
					Item item = (Item) e.nextElement();
					temp.add("a " + item.getTitle().toLowerCase());
				}
			
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
				
				if (items.length() > 0)
				{
					items = "There's " + items + " lying in it.";
					return getState(this.OPEN).length() > 0 ? getState(this.OPEN) + "| " + items : items;
				}
			}

			// If not container or has no items, print custom message, if one exists
			return getState(this.OPEN).length() > 0 ? getState(this.OPEN) : "There's nothing special to see.";
		}
		else
			return getState(this.CLOSED).length() > 0 ? getState(this.CLOSED) : "There's nothing special to see.";
	}
	
	/**
	 * Gets a specified state message
	 * 
	 * @param state State to check
	 * @return The state message
	 */
	public String getState(int state)
	{
		switch (state)
		{
			case OPEN:
				return this.stateOpen;
			case CLOSED:
				return this.stateClosed;
			case LOCKED:
				return this.stateLocked;
		}
		
		return null;
	}
	
	/**
	 * Add items to the container
	 * 
	 * @param item The item to add
	 */
	public void addItem(Item item)
	{
		if (!items.contains(item))
			items.add(item);
	}

	/**
	 * Remove items from the container
	 * 
	 * @param item The item to remove
	 */
	public void removeItem(Item item)
	{
		if (items.contains(item))
			items.remove(item);
	}
	
	/**
	 * Gets all items in the container
	 * 
	 * @return The items in the container
	 */
	@SuppressWarnings("unchecked")
	public Vector<Item> getItems()
	{
		return (Vector<Item>) items.clone();
	}
	
	/**
	 * Set the exit behind the door
	 * 
	 * @param exit The exit behind the door
	 */
	public void setExit(Exit exit)
	{
		this.exit = exit;
	}
	
	/**
	 * Gets the exit behind the door
	 * 
	 * @return The exit behind the door
	 */
	public Exit getExit()
	{
		return this.exit;
	}
	
	/**
	 * Set the key needed to unlock the door/container
	 * 
	 * @param key The key item
	 */
	public void setKey(Item key)
	{
		this.key = key;
	}
	
	/**
	 * Gets the key needed to unlock the door/container
	 * 
	 * @return The key item
	 */
	public Item getKey()
	{
		return this.key;
	}
	
	/**
	 * Set door/container to open/closed
	 * 
	 * @param open Open: true/false
	 */
	public void setOpen(boolean open)
	{
		this.open = open;
	}

	/**
	 * Set door/container to locked/unlocked
	 * 
	 * @param locked Locked: true/false
	 */
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}
	
	/**
	 * Gets whether the door/container is open
	 * 
	 * @return Whether the door/container is open
	 */
	public boolean isOpen()
	{
		return this.open;
	}

	/**
	 * Gets whether the door/container is locked
	 * 
	 * @return Whether the door/container is locked
	 */
	public boolean isLocked()
	{
		return this.locked;
	}

	/**
	 * Gets whether the object is a container
	 * 
	 * @return Whether the object is a container
	 */
	public boolean isContainer()
	{
		return this.container;
	}
}
