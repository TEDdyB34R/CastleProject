import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes; modified by Ethan Dowler
 * @version 2011.08.08
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Monster roomMonster;
    private Weapon roomWeapon;
    private Item[] roomContents;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
   public Room(String description)
    {
        Monster roomMonster = null;
        Weapon roomWeapon = null;
        this.description = description;
        exits = new HashMap<String, Room>();
        Item[] roomContents = new Item[] {roomMonster, roomWeapon};
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are in " + description + ".\n" + getContentDesc() + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    public void press(Command command)
    {
        System.out.println("Do What?");
    }
    public void changeDescription(String newDescription)
    {
        description = newDescription;
    }
    
    //**********************************************************************************
    //below this line are the methods I have added
    
    public void setContents(Monster newMonster, Weapon newWeapon)
    {
        Item[] contents = new Item[] {newMonster, newWeapon};
        roomContents = contents;
    }
    
    public String getContentDesc()
    {
        String returnString = "";
        if(roomContents[0] != null)
        {
                returnString += "There is a "+roomContents[0].getDesc()+" in the room \n";
        }
        if(roomContents[1] != null)
        {
            returnString += "There is a "+roomContents[1].getDesc()+" in the room \n";
        }
        return returnString;
    }
    
}

