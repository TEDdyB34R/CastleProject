import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * This is where you play the Game!
 * 
 * @author Ethan Dowler
 * @version this is where i'm working on our main code
 */
public class Game
{

    private Hero thisHero;
    private Weapon thisWeapon;
    private Monster thisMonster;
    private Room thisRoom;

    private int currentHealth;
    private Weapon currentWeapon;
    private Room currentRoom;
    private ArrayList<Room> currentFloor;


    private Random monsterSpawn;
    private HashMap<String, Weapon> armory;
    private HashMap<String, Monster> monsterpedia;
    
    private Parser parser;
    
    private ArrayList<Room> floor1;

    
    public Game()
    {
        //creates an armory HashMap full of weapons
        armory = new HashMap<String, Weapon>();
        createArmory();
        
        //creates our monsters in a HashMap
        monsterpedia = new HashMap<String, Monster>();
        createMonsterpedia();
        monsterSpawn = new Random();
        
        createRooms();
        currentRoom = floor1.get(0);
        parser = new Parser();
    }

    private void attack()
    {
        //damage calculations
        int damageToHero = thisMonster.getPower() - thisHero.getDefense() - currentWeapon.getDefense();
        int damageToMonster = thisHero.getPower() + currentWeapon.getPower() - thisMonster.getDefense();

        //loop that will run until the monster is dead
        while(thisMonster.getHealth() > 0)
        {
            //monster takes damage; see Monster class
            thisMonster.takeDamage(damageToMonster);

            //Hero takes damage
            if(damageToHero > 0)
            {
                currentHealth -= damageToHero;
                System.out.println("you take "+damageToHero+" damage");
            }
            else
            {
                System.out.println("you take 0 damage");
            }

            //Battle results; prints the damage Hero deals
            if(damageToMonster > 0)
            {
                System.out.println("and deal "+damageToMonster+" damage");
            }
            else
            {
                System.out.println("and deal 0 damage");
            }

            //if your health drops below zero, you lose
            if(currentHealth <= 0)
            {
                System.out.println();
                System.out.println("You have been slain! Better luck next time...");
                System.out.println("GAME OVER!!!! MUAHAHAHAHAHAHAH!!!!");
                return;
                //we need to add an "end game" methond here or something
            }
        }

        //victory condition
        System.out.println("The monster has been slain!");
        System.out.println("your Hp = "+currentHealth);
        System.out.println();

        //maybe add some sort of reward here? i.e.-experience points, bonus stats
    }

    //this is a method used with potions, elixers, etc.
    private void heal(int healAmount)
    {
        currentHealth += healAmount;
        int excessHeal = currentHealth - thisHero.getMaxHealth();
        if(currentHealth <= thisHero.getMaxHealth())
        {
            System.out.println("You have been healed "+healAmount+" health!");
        }
        if(currentHealth > thisHero.getMaxHealth())
        {
            currentHealth = thisHero.getMaxHealth();
            System.out.println("You have been healed "+excessHeal+" health!");
        }
        System.out.println("New Hp: "+currentHealth);
    }

    //this method will be used to randomly spawn a monster in a room
    private Monster spawn(Monster mon, int rate)
    {
        int x = monsterSpawn.nextInt(rate);
        if(x != 0)
        {
            mon = null;
        }
        return mon;
    }
    
    //this method sets 'thisHero' to the hero of the players choice
    private void chooseHero(String heroType)
    {
        switch(heroType.toLowerCase())
        {
            case "assassin":
            thisHero = new Hero("Assassin",80, 15, 7);
            break;
            case "rouge":
            thisHero = new Hero("Rouge",90, 13, 8);
            break;
            case "knight":
            thisHero = new Hero("Knight",100, 10, 10);
            break;
            case "paladin":
            thisHero = new Hero("Paladin", 110, 7, 12);
            break;
            case "tank":
            thisHero = new Hero("Tank",120, 3, 15);
            break;
            case "ducky":
            thisHero = new Hero("Ducky, the destroyer of worlds",300, 100, 50);
            break;
            default:
            thisHero = new Hero("Peasant",50, 0, 0);
        }
    }
    
    //Welcome message..
    private void printWelcome()
    {
        if(thisHero.getDesc().equals("Peasant"))
        {
            System.out.println("You must be an illiterate peasant who can't read the README file");
            System.out.println("This Hero is very weak. :(");
            System.out.println("You may want to restart your game and choose a stronger Hero class;");
            System.out.println("unless you want to die a quick painful death...");
            System.out.println("Available Hero classes are written in the README file");

        }
        else
        {
            System.out.println("You are a mighty "+thisHero.getDesc()+"!");
            System.out.println("Armed with only your "+currentWeapon.getDesc());
            System.out.println("Your goal is to rescue the princess at the top of the castle");
        }
        System.out.println("");
        System.out.println("You have just enterd the Dark Castle, and you begin to look around");
        System.out.println(currentRoom.getExitString());
    }
    
    //this method will create a list of weapons/shields we can pull from
    // o# = realative strength of offensive itme; d# = relative strength of defensive item
    // s# = special item that has unique stats/qualities
    // this will help us keep track of the order of weapon power
    private void createArmory() 
    {
        thisWeapon = new Weapon("fists", 0, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("dull knife", 2, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("weak shield", 0, 2);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("switchblade", 4, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("oak shield", 0, 4);
        armory.put(thisWeapon.getDesc(),thisWeapon);
    }
    
    private void createMonsterpedia()
    {
        thisMonster = new Monster("whisp", 50, 0, 0, 4);
        monsterpedia.put(thisMonster.getDesc(), thisMonster);
        thisMonster = new Monster("giant roach", 50, 3, 5, 4);
        monsterpedia.put(thisMonster.getDesc(), thisMonster);
    }
    
    //****************************************************************************
    //the methods below this line are from the Zuul game and are under construction
    /**
     * /**
     * Select your Hero class. Avaiable classes include:
     * className     Health         Power          Defense
     * assassin           80             15             7
     * rouge              90             13             8
     * knight             100            10             10
     * paladin            110            7              12
     * tank               120            3              15
     */
    public void play(String heroType) 
    {            
        //I modified the printWelcome() (incomplete) and added chooseHero()
        chooseHero(heroType);
        currentHealth = thisHero.getMaxHealth();
        currentWeapon = armory.get("fists");

        printWelcome();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.       
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("attack")) {
            attack();
        }
        // else command not recognised.
        return wantToQuit;
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You must hurry to save the princess!");
        System.out.println("Here are your available commands");
        parser.showCommands();
    }
    
    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            
            
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        createFloor1();
    }
    
    private void createFloor1()
    {
        floor1 = new ArrayList<Room>();
        //creating rooms
        Room a, b, c, d, e, f, g;
        a = new Room("room #1");
        b = new Room("room #2");
        c = new Room("room #3");
        d = new Room("room #4");
        e = new Room("room #5");
        f = new Room("room #6");
        g = new Room("room #7");
        //setting exits
        a.setExit("north", b);
        b.setExit("north", c);
        b.setExit("south", a);
        c.setExit("north", d);
        c.setExit("south", b);
        c.setExit("east", e);
        d.setExit("south", c);
        e.setExit("east", f);
        e.setExit("west", d);
        f.setExit("north", g);
        f.setExit("west", e);
        g.setExit("south",f);
        //this is where we will establish what is in each room
        //this is just a sample item
        c.setContents(monsterpedia.get("whisp"), null);
        
        //adding the floors to the ArrayList
        floor1.add(a);
        floor1.add(b);
        floor1.add(c);
        floor1.add(d);
        floor1.add(e);
        floor1.add(f);
        floor1.add(g);
    }
}
