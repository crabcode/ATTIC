package objects;

import java.io.Serializable;

/**
 * The Exit object containing the directions and connected locations the user can go to
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class Exit implements Serializable
{
	/**
	 * Serializable Class Version Number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * UNDEFINED direction, only for initialization
	 */
	public static final int UNDEFINED = 0;
	/**
	 * Direction NORTH
	 */
	public static final int NORTH = 1;
	/**
	 * Direction SOUTH
	 */
	public static final int SOUTH = 2;
	/**
	 * Direction EAST
	 */
	public static final int EAST = 3;
	/**
	 * Direction WEST
	 */
	public static final int WEST = 4;
	/**
	 * Direction UP
	 */
	public static final int UP = 5;
	/**
	 * Direction DOWN
	 */
	public static final int DOWN = 6;
	/**
	 * Direction NORTHEAST
	 */
	public static final int NORTHEAST = 7;
	/**
	 * Direction NORTHWEST
	 */
	public static final int NORTHWEST = 8;
	/**
	 * Direction SOUTHEAST
	 */
	public static final int SOUTHEAST = 9;
	/**
	 * Direction SOUTHWEST
	 */
	public static final int SOUTHWEST = 10;
	/**
	 * Direction IN
	 */
	public static final int IN = 11;
	/**
	 * Direction OUT
	 */
	public static final int OUT = 12;
	
	/**
	 * Full direction name array
	 */
	public static final String[] dirName =
	{
			"UNDEFINED",
			"NORTH",
			"SOUTH",
			"EAST",
			"WEST",
			"UP",
			"DOWN",
			"NORTHEAST",
			"NORTHWEST",
			"SOUTHEAST",
			"SOUTHWEST",
			"IN",
			"OUT"
	};

	/**
	 * Shortened direction name array
	 */
	public static final String[] shortDirName =
	{
			"NULL",
			"N",
			"S",
			"E",
			"W",
			"U",
			"D",
			"NE",
			"NW",
			"SE",
			"SW",
			"I",
			"O"
	};
	
	/**
	 * Connected Location
	 */
	private Location leadsTo;
	/**
	 * Exit direction
	 */
	private String directionName;
	/**
	 * Exit shortened direction
	 */
	private String shortDirectionName;
	
	/**
	 * Initializes empty Exit
	 */
	public Exit()
	{
		this.directionName = dirName[UNDEFINED];
		this.shortDirectionName = shortDirName[UNDEFINED];
		this.leadsTo = null;
	}
	
	/**
	 * Initializes empty Exit
	 * 
	 * @param direction Exit direction
	 * @param leadsTo Connected Location
	 */
	public Exit(int direction, Location leadsTo)
	{
		if(direction <= dirName.length)
			directionName = dirName[direction];
		if(direction <= shortDirName.length)
			shortDirectionName = shortDirName[direction];

		this.leadsTo = leadsTo;
	}
	
	/**
	 * Gets direction name
	 * 
	 * @return Direction name
	 */
	public String toString()
	{
		return directionName;
	}

	/**
	 * Sets Exit direction from predefined array
	 * 
	 * @param direction Direction name
	 */
	public void setDirection(int direction)
	{
		if(direction <= dirName.length)
			directionName = dirName[direction];
		if(direction <= shortDirName.length)
			shortDirectionName = shortDirName[direction];
	}

	/**
	 * Sets custom Exit direction
	 * 
	 * @param directionName Direction name
	 * @param shortDirectionName Shortened direction name
	 */
	public void setDirection(String directionName, String shortDirectionName)
	{
		this.directionName = directionName;
		this.shortDirectionName = shortDirectionName;
	}

	/**
	 * Gets direction name
	 * 
	 * @return Direction name
	 */
	public String getDirection()
	{
		return directionName;
	}

	/**
	 * Gets direction name
	 * 
	 * @return Shortened direction name
	 */
	public String getShortDirection()
	{
		return shortDirectionName;
	}
	
	/**
	 * Sets the connected Location
	 * 
	 * @param leadsTo The connected Location
	 */
	public void setLocation(Location leadsTo)
	{
		this.leadsTo = leadsTo;
	}
	
	/**
	 * Gets the connected Location
	 * 
	 * @return Connected Location
	 */
	public Location getLocation()
	{
		return leadsTo;
	}
}
