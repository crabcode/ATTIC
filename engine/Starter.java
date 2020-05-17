package engine;

import java.io.*;
import java.util.*;

import objects.*;

/**
 * The engine starter and class to call to run the game
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class Starter
{
	/**
	 * The path to the GameWorld file that will be provided by the main arguments
	 */
	private static String fileName;
	/**
	 * The default save file name, generated from the GameWorld file name
	 */
	private static String saveGame;
	
	/**
	 * The GameWorld object that will be loaded from file
	 */
	private GameWorld game;
	/**
	 * The Object Input Stream used to load the GameWorld file
	 */
	private ObjectInputStream objectIn;
	
	/**
	 * Loads the game data and sets the Output Stream
	 * 
	 * @throws IOException If the provided file isn't found or not a GameWorld file
	 * @throws ClassNotFoundException If the provided file isn't a GameWorld file
	 */
	public Starter() throws IOException, ClassNotFoundException
	{
		FileInputStream fin = new FileInputStream(fileName);
		objectIn = new ObjectInputStream(fin);
		
		game = (GameWorld) objectIn.readObject();
		game.setOutputStream(System.out, 60);
	}
	
	/**
	 * The main method. Runs the Starter and launches the game.
	 * 
	 * @param args Path to game file
	 * @throws IllegalArgumentException If no arguments, i.e. no game file path is provided
	 * @throws IOException If the provided file isn't found or not a GameWorld file
	 * @throws ClassNotFoundException If the provided file isn't a GameWorld file
	 */
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException
	{
		if (args.length == 0)
			throw new IllegalArgumentException("Path to game file must be provided");

		fileName = args[0];
		
		try
		{
			// Check if the given path is valid and generate default save file name
			File data = new File(fileName);
			saveGame = data.getName().substring(0, data.getName().lastIndexOf('.')) + ".sav";
			
			new Starter().play();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: The specified game file could not be found: " + fileName);
		}
		catch (Exception e)
		{
			System.err.println("ERROR: The specified file is not a valid game file: " + fileName);
		}
	}
	
	/**
	 * Starts the game loop and handles the input and game logic
	 */
	public void play()
	{
		// Setup variables
		boolean validCommand = true;
		boolean newLocation = true;
		String command = null;		
		BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
		
		// Print game title
		System.out.println(game.getTitle());
		
		for (;;)
		{
			if (newLocation)
			{
				// On new location, print location description including the title
				game.showLocation(true);
				newLocation = false;
			}
			
			// User prompt symbol
			System.out.print(">");
			
			try
			{
				// Wait for user input
				while ((command = din.readLine()) == null) { }
			}
			catch (IOException e) { }
			
			// Will be set to true if a valid command is found
			validCommand = false;
			
			command = command.trim();
			
			if (command.length() == 0)
			{
				System.out.println("What can I do for you?");
				continue;
			}
			
			command = command.toUpperCase();
			
			/* ================================================================ *
			 * Check if command is a direction and an exit or door is available *
			 * ================================================================ */
			
			// ----- EXIT -----
			for (Enumeration<Exit> e = game.getCurrentLocation().getExits().elements(); e.hasMoreElements();)
			{
				Exit exit = e.nextElement();

				String temp = command;
				
				// Let user type "go to the west" etc.
				if (temp.startsWith("GO TO THE ")) temp = command.substring(10);
				if (temp.startsWith("GO TO ")) temp = command.substring(6);
				if (temp.startsWith("GO ")) temp = command.substring(3);
				
				if ((exit.getDirection().compareTo(temp) == 0) || (exit.getShortDirection().compareTo(temp) == 0))
				{
					game.setCurrentLocation(exit.getLocation());
					validCommand = true;
					newLocation = true;
					break;
				}
			}

			// ----- DOOR -----
			for (Enumeration<Door> e = game.getCurrentLocation().getDoors().elements(); e.hasMoreElements();)
			{
				Door door = e.nextElement();
				
				if (door.getExit().getDirection().compareTo(command) == 0 || door.getExit().getShortDirection().compareTo(command) == 0)
				{
					validCommand = true;
					
					if (door.isLocked())
					{
						System.out.println();
						game.getOutput().println(door.getState(door.LOCKED));
						System.out.println();
					}
					else
					{
						game.setCurrentLocation(door.getExit().getLocation());
						newLocation = true;
					}
					
					break;
				}
			}

			/* ================================================================ *
			 * Check for action commands                                        *
			 * ================================================================ */
			
			// ----- OPEN -----
			if (command.length() > 4 ? command.startsWith("OPEN ") : command.startsWith("OPEN"))
			{
				if (command.length() < 6)
					System.out.println("Open what?");
				else
				{
					boolean found = false;
					
					for (StringTokenizer tokenizer = new StringTokenizer(command, game.getPlural()); tokenizer.hasMoreElements();)
					{
						// Name of the object the user wants to open
						String token = tokenizer.nextToken().toUpperCase();
						
						// Check if there are doors by that name in the location
						for (Enumeration<Door> e = game.getCurrentLocation().getDoors().elements(); e.hasMoreElements();)
						{
							Door door = e.nextElement();
							
							if (token.compareTo(door.getTitle(game.getPlural()).toUpperCase()) == 0)
							{
								if (door.isLocked())
								{
									// If the door doesn't have a key, it can never be opened
									if (door.getKey().getTitle().length() == 0)
									{
										System.out.println();
										game.getOutput().println("It's closed firmly. Nothing you can do about it.");
										System.out.println();
									}
									else
									{										
										boolean foundItem = false;
										String keyName = door.getKey().getTitle(game.getPlural()).toUpperCase();
										
										// Check whether the user wants to use an item in their inventory
										while (tokenizer.hasMoreElements())
										{
											String nextToken = tokenizer.nextToken().toUpperCase();

											for (Enumeration<Item> a = game.getInventory().elements(); a.hasMoreElements();)
											{
												Item item = a.nextElement();
												String itemName = item.getTitle(game.getPlural()).toUpperCase();
												
												if (itemName.compareTo(nextToken) == 0)
												{
													// Check if the item is the right key
													if (itemName.compareTo(keyName) == 0)
													{
														door.setLocked(false);
														door.setOpen(true);
														System.out.println("Opened.");
													}
													else
													{
														System.out.println();
														game.getOutput().println("You can't unlock the door with that.");
														System.out.println();
													}
													
													foundItem = true;
													break;
												}
											}
											
											if (foundItem)
												break;
										}
										
										if (!foundItem)
										{
											System.out.println();
											game.getOutput().println("You can't open it without the right key.");
											System.out.println();
										}
									}
								}
								else
								{
									if (door.isOpen())
										System.out.println("It's already open.");
									else
									{
										door.setOpen(true);
										System.out.println("Opened.");
									}
								}
								
								found = true;
								break;
							}
						}
						
						if (!found)
						{
							// Check if user is trying to open an object
							for (Enumeration<PlainObject> e = game.getCurrentLocation().getPlainObjects().elements(); e.hasMoreElements();)
							{
								PlainObject plainObject = e.nextElement();
								String response;
								
								if (token.compareTo(plainObject.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									// Check if the object has a custom response, otherwise the default will be used
									if ((response = plainObject.getResponse(Command.OPEN)) != null)
									{
										System.out.println();
										game.getOutput().println(response);
										System.out.println();
										found = true;
									}
									
									break;
								}
							}
						}
						
						if (found)
							break;
					}
					
					// Default response
					if (!found)
						System.out.println("You can't open that.");
				}

				continue;
			}

			// ----- CLOSE -----
			if (command.length() > 5 ? command.startsWith("CLOSE ") : command.startsWith("CLOSE"))
			{
				if (command.length() < 7)
					System.out.println("Close what?");
				else
				{
					boolean found = false;
					
					for (StringTokenizer tokenizer = new StringTokenizer(command, game.getPlural()); tokenizer.hasMoreElements();)
					{
						// Name of the object the user wants to close
						String token = tokenizer.nextToken().toUpperCase();
						
						// Check if there are doors by that name in the location
						for (Enumeration<Door> e = game.getCurrentLocation().getDoors().elements(); e.hasMoreElements();)
						{
							Door door = e.nextElement();
							
							if (token.compareTo(door.getTitle(game.getPlural()).toUpperCase()) == 0)
							{
								door.setOpen(false);
								System.out.println("Closed.");
								found = true;
								break;
							}
						}
						
						if (!found)
						{
							// Check if user is trying to close an object
							for (Enumeration<PlainObject> e = game.getCurrentLocation().getPlainObjects().elements(); e.hasMoreElements();)
							{
								PlainObject plainObject = e.nextElement();
								String response;

								// Check if the object has a custom response, otherwise the default will be used
								if (token.compareTo(plainObject.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									if ((response = plainObject.getResponse(Command.CLOSE)) != null)
									{
										System.out.println();
										game.getOutput().println(response);
										System.out.println();
										found = true;
									}
									
									break;
								}
							}
						}
						
						if (found)
							break;
					}

					// Default response
					if (!found)
						System.out.println("You can't close that.");
				}

				continue;
			}

			// ----- TAKE -----
			if (command.length() > 4 ? command.startsWith("TAKE ") : command.startsWith("TAKE"))
			{
				if (command.length() < 6)
					System.out.println("Take what?");
				else
				{
					boolean found = false;
					
					for (StringTokenizer tokenizer = new StringTokenizer(command, game.getPlural()); tokenizer.hasMoreElements();)
					{
						// Name of the object the user wants to take
						String token = tokenizer.nextToken().toUpperCase();
						
						// Check if there are items by that name in the location
						for (Enumeration<Item> e = game.getCurrentLocation().getItems().elements(); e.hasMoreElements();)
						{
							Item item = e.nextElement();
							
							if (token.compareTo(item.getTitle(game.getPlural()).toUpperCase()) == 0)
							{
								game.getInventory().add(item);
								game.getCurrentLocation().removeItem(item);
								System.out.println("Taken.");
								found = true;
								break;
							}
						}

						// Check if there are doors by that name in the location ('doors' here refers to container lids)
						for (Enumeration<Door> e = game.getCurrentLocation().getDoors().elements(); e.hasMoreElements();)
						{
							Door door = e.nextElement();
							
							for (Enumeration<Item> a = door.getItems().elements(); a.hasMoreElements();)
							{
								Item item = a.nextElement();
								
								if (token.compareTo(item.getTitle(game.getPlural()).toUpperCase()) == 0 && door.isOpen())
								{
									game.getInventory().add(item);
									door.removeItem(item);
									System.out.println("Taken.");
									found = true;
									break;
								}
							}
							
							if (found)
								break;
						}
						
						if (!found)
						{
							// Check if user is trying to take an object
							for (Enumeration<PlainObject> e = game.getCurrentLocation().getPlainObjects().elements(); e.hasMoreElements();)
							{
								PlainObject plainObject = e.nextElement();
								String response;
								
								if (token.compareTo(plainObject.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									// Check if the object has a custom response, otherwise the default will be used
									if ((response = plainObject.getResponse(Command.TAKE)) != null)
									{
										System.out.println();
										game.getOutput().println(response);
										System.out.println();
										found = true;
									}
									
									break;
								}
							}
						}
						
						if (!found)
						{
							// Check if the user has the item in their inventory already
							for (Enumeration<Item> a = game.getInventory().elements(); a.hasMoreElements();)
							{
								Item item = a.nextElement();
								
								if (token.compareTo(item.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									System.out.println("You already have it.");
									found = true;
									break;
								}
							}
						}
						
						if (found)
							break;
					}
					
					// Default response
					if (!found)
						System.out.println("You can't take that.");
				}
				
				continue;
			}

			// ----- DROP -----
			if (command.length() > 4 ? command.startsWith("DROP ") : command.startsWith("DROP"))
			{
				if (command.length() < 6)
					System.out.println("Drop what?");
				else
				{
					boolean found = false;
					
					for (StringTokenizer tokenizer = new StringTokenizer(command, game.getPlural()); tokenizer.hasMoreElements();)
					{
						// Name of the object the user wants to drop
						String token = tokenizer.nextToken().toUpperCase();
						
						// Check if there are items by that name in the user's inventory
						for (Enumeration<Item> e = game.getInventory().elements(); e.hasMoreElements();)
						{
							Item item = e.nextElement();
							
							if (token.compareTo(item.getTitle(game.getPlural()).toUpperCase()) == 0)
							{
								game.dropItem(item);
								System.out.println("Dropped.");
								found = true;
								break;
							}
						}
						
						if (!found)
						{
							// Check if user is trying to drop an object
							for (Enumeration<PlainObject> e = game.getCurrentLocation().getPlainObjects().elements(); e.hasMoreElements();)
							{
								PlainObject plainObject = e.nextElement();
								String response;
								
								if (token.compareTo(plainObject.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									// Check if the object has a custom response, otherwise the default will be used
									if ((response = plainObject.getResponse(Command.DROP)) != null)
									{
										System.out.println();
										game.getOutput().println(response);
										System.out.println();
										found = true;
									}
									
									break;
								}
							}
						}
						
						if (found)
							break;
					}
					
					// Default response
					if (!found)
						System.out.println("You don't have that.");
				}
				
				continue;
			}

			// ----- LOOK -----
			if (command.length() > 4 ? command.startsWith("LOOK ") : command.startsWith("LOOK"))
			{
				if (command.length() < 6 || command.compareTo("LOOK AROUND") == 0)
				{
					// To look around, show the location description without the title
					game.showLocation(false);
				}
				else
				{
					boolean found = false;
						
					for (StringTokenizer tokenizer = new StringTokenizer(command, game.getPlural()); tokenizer.hasMoreElements();)
					{
						// Name of the object the user wants to look at
						String token = tokenizer.nextToken().toUpperCase();
						
						// Check if there are items by that name in the inventory
						for (Enumeration<Item> e = game.getInventory().elements(); e.hasMoreElements();)
						{
							Item item = e.nextElement();
							
							if (token.compareTo(item.getTitle(game.getPlural()).toUpperCase()) == 0)
							{
								System.out.println();
								game.getOutput().println(item.getDescription());
								System.out.println();
								found = true;
								break;
							}
						}

						if (!found)
						{
							// Check if there are doors by that name in the location
							for (Enumeration<Door> e = game.getCurrentLocation().getDoors().elements(); e.hasMoreElements();)
							{
								Door door = e.nextElement();
								
								if (token.compareTo(door.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									System.out.println();
									game.getOutput().println(door.getDescription());
									System.out.println();
									found = true;
									break;
								}
							}
						}

						if (!found)
						{
							// Check if there are objects by that name in the location
							for (Enumeration<PlainObject> e = game.getCurrentLocation().getPlainObjects().elements(); e.hasMoreElements();)
							{
								PlainObject plainObject = e.nextElement();
								
								if (token.compareTo(plainObject.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									System.out.println();
									game.getOutput().println(plainObject.getDescription());
									System.out.println();
									found = true;
									break;
								}
							}
						}

						if (!found)
						{
							// Check if there are items by that name in the location
							for (Enumeration<Item> e = game.getCurrentLocation().getItems().elements(); e.hasMoreElements();)
							{
								Item item = e.nextElement();
								
								if (token.compareTo(item.getTitle(game.getPlural()).toUpperCase()) == 0)
								{
									System.out.println();
									game.getOutput().println(item.getDescription());
									System.out.println();
									found = true;
									break;
								}
							}
						}
					}
					
					// Default response
					if (!found)
						System.out.println("There's nothing by that name to look at.");
				}
				
				continue;
			}

			// ----- INVENTORY -----
			if (command.compareTo("INVENTORY") == 0)
			{
				Vector<Item> items = game.getInventory();
				
				if (items.size() == 0)
					System.out.println("Your inventory is empty.");
				else
				{
					System.out.println("\n   Your inventory contains:");
					
					for (int i = 0; i < items.size(); i++)
					{
						System.out.println("      " + items.elementAt(i));
					}
					
					System.out.println();
				}
				
				continue;
			}

			// ----- SAVE -----
			if (command.compareTo("SAVE") == 0)
			{
				// Ask for a save file name
				System.out.print("Choose a name for the savegame. (Default is " + saveGame.toUpperCase() + "): ");
				
				try
				{
					// Wait for user input
					while ((command = din.readLine()) == null) {}
				}
				catch (IOException e) { }
				
				// If user enters nothing, save as default
				if (command.length() == 0)
					save(saveGame); 
				else
				{
					if (!command.endsWith(".sav"))
						command += ".sav";
					
					save(command);
				}
				
				continue;
			}

			// ----- RESTORE -----
			if (command.compareTo("RESTORE") == 0)
			{
				// Ask for save file name
				System.out.print("Which savegame would you like to restore? (Default is " + saveGame.toUpperCase() + "): ");

				try
				{
					// Wait for user input
					while ((command = din.readLine()) == null) {}
				}
				catch (IOException e) { }
				
				// If user enters nothing, load default
				if (command.length() == 0)
					newLocation = restore(saveGame); 
				else
				{
					if (!command.endsWith(".sav"))
						command += ".sav";
					newLocation = restore(command);
				}
				
				continue;
			}

			// ----- QUIT -----
			if (command.compareTo("QUIT") == 0)
			{
				// Ask for confirmation
				System.out.print("Are you sure you want to leave? (y/n): ");

				try
				{
					// Wait for user input
					while ((command = din.readLine()) == null) {}
				}
				catch (IOException e) { }
				
				// Only quit if user explicitly confirms
				if (command.toUpperCase().compareTo("Y") == 0)
					System.exit(0);
				else if (command.toUpperCase().compareTo("N") == 0)
					System.out.println("Ah good, you came to your senses.");
				else
					System.out.println("That's not an answer! You don't seem very sure...");

				continue;
			}

			// ----- nothing valid -----
			if (!validCommand)
			{
				boolean dir = false;
				
				// Check if command was a direction you couldn't go in
				for (int i = 0; i < Exit.dirName.length; i++)
				{
					if ((command.compareTo(Exit.dirName[i]) == 0) || (command.compareTo(Exit.shortDirName[i]) == 0))
					{
						dir = true;
						break;
					}
				}
				
				if (dir)
					System.out.println("You can't go that way.");
				else
					System.out.println("I don't understand.");
			}
		}
	}
	
	/**
	 * Saves the game to file
	 * 
	 * @param saveGame The save file name
	 */
	public void save(String saveGame)
	{
		try
		{
			File destination = new File("save");
			
			if (!destination.exists())
				destination.mkdir();
			
			FileOutputStream out = new FileOutputStream("save/" + saveGame);
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			
			objectOut.writeObject(game);
			objectOut.close();
			
			System.out.println("Game saved successfully.");
		}
		catch (IOException e)
		{
			System.out.println("Game could not be saved.");
		}
	}
	
	/**
	 * Loads the game from file
	 * 
	 * @param saveGame The save file name
	 * @return Whether load was successful
	 */
	public boolean restore(String saveGame)
	{
		try
		{
			FileInputStream fin = new FileInputStream("save/" + saveGame);
			objectIn = new ObjectInputStream(fin);
			
			try
			{
				this.game = (GameWorld) objectIn.readObject();
				this.game.setOutputStream(System.out, 60);
				System.out.println("Save game restored successfully.");
				return true;
			}
			catch (Exception e)
			{
				System.out.println("Save file could not be read.");
			}
		}
		catch (IOException e)
		{
			System.out.println("Save file could not be found: " + saveGame);
		}
		
		return false;
	}
}
