package editor;

import java.io.*;

import objects.*;
import engine.*;

/**
 * This is where you create your GameWorld file that will be loaded by the engine starter.
 * 
 * @author Crabman
 * @version 1.0.0
 */
class CreateGameWorld
{
	/**
	 * The output filename for the created GameWorld object
	 */
	public static String fileName = "attic.dat";
	
	/**
	 * This is where you make your game. Create locations, items, objects,
	 * and link them all together.
	 * 
	 * @param game The GameWorld object you're adding your content to
	 */
	private static void SetupWorld(GameWorld game)
	{
		game.setPlural(true);
		game.setTitle("\n" +
				"\n" +
				"                       #### ## ## ####\n" +
				"                        ##  ##### ##*\n" +
				"                        ##  ## ## ####\n" +
				"\n" +
				"                  ######### ######### ######### ### #########\n" +
				"                  ###   ###    ###       ###    ### ###\n" +
				"                  ###   ###    ###       ###    ### ###\n" +
				"                  #########    ###       ###    ### ###\n" +
				"                  ###   ###    ###       ###    ### ###\n" +
				"                  ###   ###    ###       ###    ### ###\n" +
				"                  ###   ###    ###       ###    ### #########\n" +
				"\n" +
				"\n" +
				"                      Copyright (c) 1987 Hypnotic Owl Inc.\n" +
				"\n");
		
		// Creating Locations
		Location deepForest1 = new Location("Deep Forest", "Surrounded by trees, you have no clear indication of which way to turn.");
		Location deepForest2 = new Location("Deep Forest", "You walk and walk, but the scenery doesn't change. Still nothing there to make out where the hell you are.");
		Location deepForest3 = new Location("Deep Forest", "Every tree and every bush looks exactly the same. You seem to be pretty lost.");
		Location deepForest4 = new Location("Deep Forest", "You have been walking through the woods for almost an hour now and yet you feel as if you've been walking in a circle the whole time.");
		Location forest = new Location("Forest", "You're in the middle of the forest. You see nothing but trees and bushes around you, except for something that looks like the beginning of a path leading west.");
		Location houseFront = new Location("House Front", "Standing in a small clearing you look at the house that seems to be built into a wall of trees. The house has a strange quality to it. You feel as if it was both very old and new. It gives you an eerie feeling, but you don't seem to have any other options than to go in the door or walk back the path leading east.");
		Location groundFloor = new Location("Ground Floor", "You're standing in a mid-sized room that seems to be the living area. There's a fireplace in one corner, with a bear pelt and a few chairs around it. Everything appears to be covered in a thin coat of dust. Next to the door leading out is a staircase leading up and another door on the east side of the room.");
		Location kitchen = new Location("Kitchen", "The kitchen is small but all the more strange. Alongside the usual cooking utensils you find lots of herbs and vials, whose contents are unknown to you. The door you came through is to the west.");
		Location upperFloor = new Location("Upper Floor", "Short of a few paintings on the wall, this hallway is rather empty. Besides the doors on the north and west, there are two staircases, one leading up and one leading down.");
		Location study = new Location("Study", "You seem to have stumbled into someone's old study. The strange instruments and books on all kinds of magic let you come to the conclusion that a wizard must have lived here. Judging by the topics of most those books you wouldn't be too unhappy if you didn't make his acquaintance. The door you came through is to the east.");
		Location bedroom = new Location("Bedroom", "This small room contains not much more than a bed. The mattress is rather uncomfortable and smells of rotten straw. A compact walk-in closet is in the northeast corner of the room and the door you came through is leading south.");
		Location closet = new Location("Closet", "The closet is quite small and almost empty.");
		Location atticDoor = new Location("Attic Door", "The door has no handle and is closed shut, and no matter how hard you push, it won't budge a bit. It doesn't even have a keyhole, so it seems you'll have to find another way to open it.");
		Location attic = new Location("The Attic", "You've barely spoken the last syllable when you hear a subtle click and the door opens. As you slowly enter the room, you hear a faint moaning coming from the far corner. There are no windows and except for the light coming through the door, it lies in complete darkness. Very carefully you step closer to the noise, all the while trying to make out shapes in the dark. Finally, you find a spindly man lying on the floor, staring at you with helpless horror in his eyes. You lean closer to understand what he is trying to say.|    \"The... the door...\", he croaks, right as it slams shut.| | [THE END]");

		// Creating Exits
		Exit deepForest1N = new Exit(Exit.NORTH, deepForest2);
		Exit deepForest1S = new Exit(Exit.SOUTH, deepForest2);
		Exit deepForest1E = new Exit(Exit.EAST, deepForest2);
		Exit deepForest1W = new Exit(Exit.WEST, forest);
		Exit deepForest2N = new Exit(Exit.NORTH, forest);
		Exit deepForest2S = new Exit(Exit.SOUTH, deepForest3);
		Exit deepForest2E = new Exit(Exit.EAST, forest);
		Exit deepForest2W = new Exit(Exit.WEST, deepForest3);		
		Exit deepForest3N = new Exit(Exit.NORTH, deepForest4);
		Exit deepForest3S = new Exit(Exit.SOUTH, forest);
		Exit deepForest3E = new Exit(Exit.EAST, forest);
		Exit deepForest3W = new Exit(Exit.WEST, forest);		
		Exit deepForest4N = new Exit(Exit.NORTH, forest);
		Exit deepForest4S = new Exit(Exit.SOUTH, forest);
		Exit deepForest4E = new Exit(Exit.EAST, forest);
		Exit deepForest4W = new Exit(Exit.WEST, forest);

		Exit forestN = new Exit(Exit.NORTH, deepForest1);
		Exit forestS = new Exit(Exit.SOUTH, deepForest1);
		Exit forestE = new Exit(Exit.EAST, deepForest1);
		Exit forestW = new Exit(Exit.WEST, houseFront);
		
		Exit houseFrontE = new Exit(Exit.EAST, forest);

		Exit groundFloorO = new Exit(Exit.OUT, houseFront);
		Exit groundFloorU = new Exit(Exit.UP, upperFloor);
		Exit groundFloorE = new Exit(Exit.EAST, kitchen);
		
		Exit kitchenW = new Exit(Exit.WEST, groundFloor);

		Exit upperFloorU = new Exit(Exit.UP, atticDoor);
		Exit upperFloorD = new Exit(Exit.DOWN, groundFloor);
		Exit upperFloorN = new Exit(Exit.NORTH, bedroom);
		Exit upperFloorW = new Exit(Exit.WEST, study);
		
		Exit studyE = new Exit(Exit.EAST, upperFloor);
		
		Exit bedroomN = new Exit(Exit.NORTH, closet);
		Exit bedroomNE = new Exit(Exit.NORTHEAST, closet);
		Exit bedroomE = new Exit(Exit.EAST, closet);
		Exit bedroomS = new Exit(Exit.SOUTH, upperFloor);

		Exit closetW = new Exit(Exit.WEST, bedroom);
		Exit closetSW = new Exit(Exit.SOUTHWEST, bedroom);
		Exit closetS = new Exit(Exit.SOUTH, bedroom);

		Exit atticDoorD = new Exit(Exit.DOWN, upperFloor);
		Exit atticDoorP = new Exit();
		atticDoorP.setLocation(attic);
		atticDoorP.setDirection("PARTASTI", "PARTASTI");
		
		Exit frontDoor = new Exit(Exit.IN, groundFloor);
		
		// Adding Exits
		deepForest1.addExit(deepForest1N);
		deepForest1.addExit(deepForest1S);
		deepForest1.addExit(deepForest1E);
		deepForest1.addExit(deepForest1W);		
		deepForest2.addExit(deepForest2N);
		deepForest2.addExit(deepForest2S);
		deepForest2.addExit(deepForest2E);
		deepForest2.addExit(deepForest2W);		
		deepForest3.addExit(deepForest3N);
		deepForest3.addExit(deepForest3S);
		deepForest3.addExit(deepForest3E);
		deepForest3.addExit(deepForest3W);		
		deepForest4.addExit(deepForest4N);
		deepForest4.addExit(deepForest4S);
		deepForest4.addExit(deepForest4E);
		deepForest4.addExit(deepForest4W);
		
		forest.addExit(forestN);
		forest.addExit(forestS);
		forest.addExit(forestE);
		forest.addExit(forestW);
		
		houseFront.addExit(houseFrontE);

		groundFloor.addExit(groundFloorO);
		groundFloor.addExit(groundFloorU);
		groundFloor.addExit(groundFloorE);

		kitchen.addExit(kitchenW);

		upperFloor.addExit(upperFloorU);
		upperFloor.addExit(upperFloorD);
		upperFloor.addExit(upperFloorN);
		upperFloor.addExit(upperFloorW);
		
		study.addExit(studyE);
		
		bedroom.addExit(bedroomNE);
		bedroom.addExit(bedroomN);
		bedroom.addExit(bedroomE);
		bedroom.addExit(bedroomS);

		closet.addExit(closetW);
		closet.addExit(closetSW);
		closet.addExit(closetS);

		atticDoor.addExit(atticDoorP);
		atticDoor.addExit(atticDoorD);
		
		// Items
		Item note = new Item("Note", "There is only one word written on the paper:|    \"Partasti\"", "Except for a few dirty wardrobe items, there is just a crumpled note lying on the floor.", 1);
		Item key = new Item("Key");

		// Plain Objects
		PlainObject tree = new PlainObject("Tree", "You've seen that tree before. Haven't you?");
		PlainObject bush = new PlainObject("Bush", "Have you been past this bush before? Who can say anymore.");
		PlainObject bushes = new PlainObject("Bushes", "Have you been past this bush before? Who can say anymore.");

		PlainObject tree2 = new PlainObject("Tree", "There's nothing very remarkable about it.");
		PlainObject bush2 = new PlainObject("Bush", "Bushes. Seen one, seen them all.");
		PlainObject bushes2 = new PlainObject("Bushes", "Bushes. Seen one, seen them all.");

		PlainObject path = new PlainObject("Path", "It's not so much a path as the hint of one. There don't seem to be too many people coming through here.");
		PlainObject staircases = new PlainObject("Staircase", "There's nothing special about it. A simple staircase.");
		PlainObject dust = new PlainObject("Dust", "It's everywhere. A thin layer, soft and light and undisturbed.");
		PlainObject fireplace = new PlainObject("Fireplace", "There's some ash left, nothing else. Much like the rest of the house, this seems to have not been used in quite a while.");
		PlainObject bed = new PlainObject("Bed", "It doesn't look too inviting and the smell makes you want to keep your distance.");
		
		// Plain Objects with Customized Responses
		PlainObject house = new PlainObject("House", "Apart from its odd quality, the house looks quite normal. You see a few windows, a door, and a mailbox in front, nothing else.");
		PlainObject wall = new PlainObject("Wall", "Apart from its odd quality, the house looks quite normal. You see a few windows, a door, and a mailbox in front, nothing else.");
		house.addResponse(Command.OPEN, "Can you be more specific?");
		house.addResponse(Command.CLOSE, "Can you be more specific?");
		wall.addResponse(Command.OPEN, "Can you be more specific?");
		wall.addResponse(Command.CLOSE, "Can you be more specific?");
		
		PlainObject bear = new PlainObject("Bear", "The bear's mouth is opened in a frightening grimace. Its black fur turned grey by the dust.");
		bear.addResponse(Command.TAKE, "It's hidious and you don't feel like carrying it around.");
		bear.addResponse(Command.DROP, "It's already on the ground.");
		PlainObject pelt = new PlainObject("Pelt", "The bear's mouth is opened in a frightening grimace. Its black fur turned grey by the dust.");
		pelt.addResponse(Command.TAKE, "It's hidious and you don't feel like carrying it around.");
		pelt.addResponse(Command.DROP, "It's already on the ground.");
		PlainObject mouth = new PlainObject("Mouth", "Despite it being obviously dead, you'd rather not stick your hand in there. No reason to anyhow.");
		mouth.addResponse(Command.OPEN, "It's already open.");
		mouth.addResponse(Command.CLOSE, "It won't budge. Must be fixed in place.");
		PlainObject chairs = new PlainObject("Chairs", "They're upholstered, with high back rests.");
		chairs.addResponse(Command.TAKE, "They're too heavy to carry around.");
		
		PlainObject utensils = new PlainObject("Utensils", "You spot nothing out of the ordinary. Some pots and pans, just about what you'd expect.");
		utensils.addResponse(Command.TAKE, "There's nothing here that looks useful to you.");
		utensils.addResponse(Command.DROP, "No need to create a mess.");
		PlainObject pans = new PlainObject("Pans", "They seem old and a little banged up.");
		pans.addResponse(Command.TAKE, "They're cast iron and quite heavy. You don't really feel like carrying those around.");
		pans.addResponse(Command.DROP, "You'd rathern not.");
		PlainObject pots = new PlainObject("Pots", "They seem old and a little banged up.");
		pots.addResponse(Command.TAKE, "They're cast iron and quite heavy. You don't really feel like carrying those around.");
		pots.addResponse(Command.DROP, "You'd rathern not.");
		PlainObject herbs = new PlainObject("Herbs", "The herbs are completely crumpled and dry. Were they meant to be this way?");
		herbs.addResponse(Command.TAKE, "You decide to leave them. You wouldn't really know what to do with them anyway.");
		herbs.addResponse(Command.DROP, "They're just fine where they are, no need to mess with them.");
		PlainObject vials = new PlainObject("Vials", "The vials are filled with liquids of varying colors. You'd rather not mess with them.");
		vials.addResponse(Command.TAKE, "You don't have too good a feeling about them. Better not mess with things you don't understand.");
		vials.addResponse(Command.DROP, "You'd rather not. Who knows what would happen if they spilled.");

		PlainObject paintings = new PlainObject("Paintings", "The paintings on the wall are drawn in dark colors and wild shapes, but seem to depict some sort of ritual gathering.");
		paintings.addResponse(Command.TAKE, "You don't really feel like lugging them around.");
		paintings.addResponse(Command.DROP, "That seems unnecessarily rude.");

		PlainObject mattress = new PlainObject("Mattress", "It's barely more than a linen sack stuffed with straw, and it smells rotten.");
		mattress.addResponse(Command.TAKE, "You'd rather leave it right where it is.");
		mattress.addResponse(Command.OPEN, "The straw might need replacement, but that's not your job. You'd rather not touch it.");
		PlainObject straw = new PlainObject("Straw", "It's poking through the mattress here and there and in desperate need of changing.");
		straw.addResponse(Command.TAKE, "You don't have any use for rotten straw, nor are you particularly keen on touching it.");
		PlainObject clo = new PlainObject("Closet", "It's a small, double door closet. It seems there used to be paint on there that has now chipped off.");
		clo.addResponse(Command.OPEN, "It isn't locked.");
		clo.addResponse(Command.CLOSE, "It's already closed.");
		
		PlainObject wardrobe = new PlainObject("Wardrobe", "There are a few robes, worn out and covered in dust. You search their pockets, but there's nothing of interest to you.");
		wardrobe.addResponse(Command.TAKE, "Their pockets contain nothing of interest to you.");
		wardrobe.addResponse(Command.DROP, "They not be your style, but you don't feel the need to throw them on the ground.");
		wardrobe.addResponse(Command.OPEN, "You don't feel like messing with them anymore.");
		wardrobe.addResponse(Command.CLOSE, "You don't feel like messing with them anymore.");
		PlainObject robes = new PlainObject("Robes", "The robes are worn out and covered in dust. You search their pockets, but there's nothing of interest to you.");
		robes.addResponse(Command.TAKE, "Their pockets contain nothing of interest to you.");
		robes.addResponse(Command.DROP, "They not be your style, but you don't feel the need to throw them on the ground.");
		robes.addResponse(Command.OPEN, "You don't feel like messing with them anymore.");
		robes.addResponse(Command.CLOSE, "You don't feel like messing with them anymore.");
		
		PlainObject instruments = new PlainObject("Instruments", "You don't know the first thing about magic, but some of them look intricate and quite a few more than sinister.");
		instruments.addResponse(Command.TAKE, "You wouldn't know what to do with them. Better not mess with things you don't understand.");
		instruments.addResponse(Command.DROP, "Better not.");
		instruments.addResponse(Command.OPEN, "You don't feel like messing with them anymore.");
		instruments.addResponse(Command.CLOSE, "You don't feel like messing with them anymore.");
		PlainObject books = new PlainObject("Books", "You don't even know what language half of these are in, but the pictures in them leave very little to the imagination. These are on black magic, and not too few quite gruesome at that.");
		books.addResponse(Command.TAKE, "You have a pit in your stomach just looking at them. You'd rather not carry them around.");
		books.addResponse(Command.DROP, "They're just books, yet you feel you should treat them with respect.");
		books.addResponse(Command.OPEN, "You can't understand a word, and the pictures are quite unsettling. You'd rather not continue looking through them.");
		books.addResponse(Command.CLOSE, "They're already closed.");
		PlainObject desk = new PlainObject("Desk", "Some books, papers, and strange instruments are strewn across the desk.");
		desk.addResponse(Command.OPEN, "You go through the drawers, but there's nothing useful to be found.");
		desk.addResponse(Command.CLOSE, "They drawers are closed.");
		PlainObject papers = new PlainObject("Papers", "There are sketches and writing in languages you can't understand.");
		papers.addResponse(Command.TAKE, "You wouldn't know what to do with these, you barely understand what you're looking at.");
		papers.addResponse(Command.DROP, "You don't feel the need to create a mess.");
		PlainObject adoor = new PlainObject("Door", "It looks sturdy and thick. No handle, no markings, just a plain slab of dark wood.");
		adoor.addResponse(Command.OPEN, "You push and push, but it won't budge. The lack of a handle or keyhole tells you that you won't get in this way.");
		adoor.addResponse(Command.CLOSE, "It's already closed tightly.");

		// Doors
		Door windows = new Door("Window");
		windows.setLocked("The windows are closed shut.", true);
		
		Door door = new Door("Door", frontDoor);
		door.setMessages("The door stands wide open.", "The door is unlocked, but closed.");
		door.setLocked("You rattle the door, but it turns out to be locked. No chance of getting through without a key.", true);
		door.setKey(key);
		
		Door mailbox = new Door("Mailbox", true, false);
		mailbox.setMessages("", "It's a wooden mailbox.");
		mailbox.addItem(key);
		
		// Adding Everything
		deepForest1.addPlainObject(tree);
		deepForest1.addPlainObject(bush);
		deepForest1.addPlainObject(bushes);
		deepForest2.addPlainObject(tree);
		deepForest2.addPlainObject(bush);
		deepForest2.addPlainObject(bushes);
		deepForest3.addPlainObject(tree);
		deepForest3.addPlainObject(bush);
		deepForest3.addPlainObject(bushes);
		deepForest4.addPlainObject(tree);
		deepForest4.addPlainObject(bush);
		deepForest4.addPlainObject(bushes);

		forest.addPlainObject(path);
		forest.addPlainObject(tree2);
		forest.addPlainObject(bush2);
		forest.addPlainObject(bushes2);
		
		houseFront.addPlainObject(tree2);
		houseFront.addPlainObject(bush2);
		houseFront.addPlainObject(bushes2);
		houseFront.addPlainObject(path);
		houseFront.addPlainObject(house);
		houseFront.addPlainObject(wall);
		houseFront.addDoor(windows);
		houseFront.addDoor(door);
		houseFront.addDoor(mailbox);
		
		groundFloor.addPlainObject(bear);
		groundFloor.addPlainObject(pelt);
		groundFloor.addPlainObject(mouth);
		groundFloor.addPlainObject(chairs);
		groundFloor.addPlainObject(fireplace);
		
		kitchen.addPlainObject(vials);
		kitchen.addPlainObject(herbs);
		kitchen.addPlainObject(utensils);
		kitchen.addPlainObject(pots);
		kitchen.addPlainObject(pans);
		kitchen.addPlainObject(dust);
		
		upperFloor.addPlainObject(paintings);
		upperFloor.addPlainObject(staircases);
		upperFloor.addPlainObject(dust);

		study.addPlainObject(instruments);
		study.addPlainObject(desk);
		study.addPlainObject(books);
		study.addPlainObject(papers);
		study.addPlainObject(dust);
		
		bedroom.addPlainObject(bed);
		bedroom.addPlainObject(mattress);
		bedroom.addPlainObject(straw);
		bedroom.addPlainObject(clo);
		
		closet.addItem(note);
		closet.addPlainObject(wardrobe);
		closet.addPlainObject(robes);
		closet.addPlainObject(dust);
		
		atticDoor.addPlainObject(dust);
		atticDoor.addPlainObject(staircases);
		atticDoor.addPlainObject(adoor);

		// Setting starting location
		game.setCurrentLocation(forest);
	}
	
	/**
	 * The main method. Calls the setup and saves the GameWorld to file.
	 * You don't need to worry about this one unless there's something
	 * specific you want to change on an engine level.
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args)
	{
		GameWorld game = new GameWorld();
		SetupWorld(game);
		
		// Write to file
		try
		{
			FileOutputStream out = new FileOutputStream(fileName);
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			
			objectOut.writeObject(game);
			objectOut.close();
			
			System.out.println("Game data was created under: " + fileName);
		}
		catch (Exception e)
		{
			System.err.println("Unable to create game data");
			e.printStackTrace();
		}
	}
}
