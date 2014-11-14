import java.util.Random;
import java.util.HashMap;
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
    private HashMap<String, Weapon> inventory;

    private Random monsterSpawn;
    private HashMap<String, Weapon> armory;
    private HashMap<String, Monster> monsterpedia;
    
    private Parser parser;
    
    //creating rooms on first floor.  Start at u1
    Room b1, c1, e1, g1, h1, i1, j1, k1, l1, n1, p1, q1, r1, s1, t1, u1;
    //rooms on floor 2
    Room d2, e2, i2, j2, l2, m2, n2, q2, r2, w2, x2;
    //rooms on floor 3
    Room a3, f3, g3, h3, i3, l3, q3, r3, s3, x3;
    
    public Game()
    {
        //creates an armory HashMap full of weapons
        armory = new HashMap<String, Weapon>();
        createArmory();
        
        inventory = new HashMap<String, Weapon>();
        inventory.put(armory.get("fists").getDesc(), armory.get("fists"));
        
        //creates our monsters in a HashMap
        monsterpedia = new HashMap<String, Monster>();
        createMonsterpedia();
        monsterSpawn = new Random();
        
        createRooms();
        createExits();
        currentRoom = u1;
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
            thisMonster.takeDamage(thisHero.getPower()+currentWeapon.getPower());

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
    private void spawn(String potentialMonster, int rate)
    {
        int x = monsterSpawn.nextInt(rate);
        if(x == 0)
        {
            thisMonster = monsterpedia.get(potentialMonster);
            currentRoom.addItem(thisMonster.getDesc(), thisMonster);
        }
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
            case "bulwark":
            thisHero = new Hero("Tank",120, 3, 15);
            break;
            case "nikhil":
            thisHero = new Hero("Nikhil, destroyer of worlds",300, 100, 50);
            break;
            default:
            thisHero = new Hero("Peasant",50, 0, 0);
        }
    }
    
    //Welcome message..
    private void printWelcome()
    {
        System.out.println("Welcome to the World of ___!");
        System.out.println("There are many things to discover");
        System.out.println("         and many adventures to be had");
        System.out.println("May you find great glory in the destiny that awaits you");
        System.out.println();
        if(thisHero.getDesc().equals("Peasant"))
        {
            System.out.println("You must be an illiterate peasant who can't read");
            thisHero.print();
            System.out.println("This Hero is very weak. :(");
            System.out.println("You may want to restart your game and choose a stronger Hero class;");
            System.out.println("unless you want to die a quick painful death...");
            System.out.println("Available Hero classes are written in the README file");

        }
        else
        {
            System.out.println("Armed with only your "+currentWeapon.getDesc());
            System.out.println("Your goal is to rescue the princess");
            System.out.println("               at the top of the castle");
            System.out.println("Your base stats are: ");
            thisHero.print();
            
        }
        System.out.println("");
        System.out.println("You have just enterd the Dark Castle, and you begin to look around");
        System.out.println(currentRoom.getExitString());
    }
    
    //this method will create a list of weapons/shields we can pull from
    private void createArmory() 
    {
        //default weapon
        thisWeapon = new Weapon("fists", 0, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        
        //offensive weapons
        thisWeapon = new Weapon("dull knife", 2, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("switchblade", 4, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("small sword", 6, 0);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("axe", 6, 4);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("recurve bow", 8, 4);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("Crossbow", 10, 5);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("Gold Sword", 12, 6);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        
        //defensive weapons
        thisWeapon = new Weapon("Small Shield", 0, 4);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("Helmet", 0, 6);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("Body Armor", 0, 8);
        armory.put(thisWeapon.getDesc(),thisWeapon);
        thisWeapon = new Weapon("Gold shield", 0, 10);
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
     * bulwark            120            3              15
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
        
        if(currentRoom.hasMonster())
        {
            System.out.println("You cannot leave; a monster is blocking the door!");
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
            if(command.getSecondWord() == "up")
            {
                System.out.println("You have climbed up the ladder to the next floor");
            }
            if(command.getSecondWord() == "down")
            {
                System.out.println("You have climbed down the ladder to the lower floor");
            }
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
     * Create all the rooms.
     */
    private void createRooms()
    {
        createFloor1();
        createFloor2();
        createFloor3();
    }
    
    private void createFloor1()
    {
        //floor one rooms
        b1 = new Room("room #1");
        c1 = new Room("room #2");
        e1 = new Room("Secret Staircase");
        g1 = new Room("room #4");
        h1 = new Room("room #5");
        i1 = new Room("room #6");
        j1 = new Room("room #7");
        k1 = new Room("room #1");
        l1 = new Room("room #2");
        n1 = new Room("Stairs/Boss");
        p1 = new Room("room #4");
        q1 = new Room("room #5");
        r1 = new Room("room #6");
        s1 = new Room("room #7");
        t1 = new Room("room #6");
        u1 = new Room("Castle entrance");
    }
    
    private void createFloor2() {
        //create rooms
        d2 = new Room("room #1");
        e2 = new Room("room #2");
        i2 = new Room("room");
        j2 = new Room("room #4");
        l2 = new Room("room #5");
        m2 = new Room("room #6");
        n2 = new Room("room #7");
        q2 = new Room("room #1");
        r2 = new Room("room #2");
        w2 = new Room("room");
        x2 = new Room("Stairs/Boss");
    }
    
    private void createFloor3() {
        a3 = new Room("Princess");
        f3 = new Room("main boss");
        g3 = new Room("room");
        l3 = new Room("room #4");
        q3 = new Room("room #5");
        h3 = new Room("room #6");
        r3 = new Room("room #7");
        i3 = new Room("hole, fall");
        s3 = new Room("room #2");
        x3 = new Room("Stairs");
    }
    
    private void secretStair() {
        e1.setExit("up", e2);
        e2.setExit("down", e1);
    }
    
    private void createExits() {
        //floor 1 exits
        u1.setExit("north", p1);
        p1.setExit("north", k1);
        p1.setExit("east", q1);
        p1.setExit("south", u1);
        k1.setExit("east", l1);
        k1.setExit("south", p1);
        q1.setExit("north", l1);
        q1.setExit("east", r1);
        q1.setExit("west", p1);
        l1.setExit("north", g1);
        l1.setExit("south", q1);
        l1.setExit("west", k1);
        g1.setExit("north", b1);
        g1.setExit("east", h1);
        g1.setExit("south", l1);
        b1.setExit("east", c1);
        b1.setExit("south", g1);
        r1.setExit("east", s1);
        r1.setExit("west", q1);
        h1.setExit("east", i1);
        h1.setExit("north",c1);
        h1.setExit("west", g1);
        c1.setExit("south", h1);
        c1.setExit("west", b1);
        s1.setExit("east", t1);
        s1.setExit("north",n1);
        s1.setExit("west", r1);
        n1.setExit("south", s1);
        n1.setExit("north", i1);
        n1.setExit("up", n2);
        i1.setExit("west", h1);
        i1.setExit("south", n1);
        i1.setExit("east", j1);
        t1.setExit("west", s1);
        j1.setExit("west", i1);
        e1.setExit("south", j1);
        
        //floor 2 exits
        q2.setExit("north", l2);
        q2.setExit("east", r2);
        l2.setExit("south", q2);
        l2.setExit("east", m2);
        w2.setExit("north", r2);
        w2.setExit("east", x2);
        r2.setExit("north", m2);
        r2.setExit("west", q2);
        r2.setExit("south", w2);
        m2.setExit("south", r2);
        m2.setExit("east", n2);
        m2.setExit("west", l2);
        x2.setExit("west", w2);
        x2.setExit("up", x3);
        n2.setExit("west", m2);
        n2.setExit("down", n1);
        n2.setExit("north", i2);
        i2.setExit("north", d2);
        i2.setExit("south", n2);
        i2.setExit("east", j2);
        d2.setExit("south", i2);
        d2.setExit("east", e2);
        j2.setExit("west", i2);
        j2.setExit("north", e2);
        e2.setExit("west", d2);
        e2.setExit("south", j2);
        
        //floor 3 exits
        a3.setExit("south", f3);
        f3.setExit("east", g3);
        f3.setExit("north", a3);
        g3.setExit("east", h3);
        g3.setExit("south", l3);
        g3.setExit("west", f3);
        h3.setExit("west", g3);
        l3.setExit("south", q3);
        l3.setExit("north", g3);
        q3.setExit("east", r3);
        q3.setExit("north", l3);
        r3.setExit("east", s3);
        r3.setExit("west", q3);
        s3.setExit("south", x3);
        s3.setExit("west", r3);
        x3.setExit("north", s3);
        x3.setExit("down", x2);
    }
}
