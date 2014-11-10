import java.util.ArrayList;
/**
 * Write a description of class Castle here.
 * 
 * @author Ethan Dowler 
 * @version this is an experiment for potential castle creation
 */
public class FloorGenerator
{
    // instance variables - replace the example below with your own
    private Room thisRoom;
    private ArrayList<Room> floor1;
    private ArrayList<Room> floor2;
    private ArrayList<Room> floor3;
    
    /**
     * Constructor for objects of class Castle
     */
    public FloorGenerator()
    {
        createFloor2();
    }
    
    private void createFloor2()
    {
        floor2 = new ArrayList<Room>();
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
        Item thisItem = new Item("ball",0,0);
        a.addItem("ball",thisItem);
        //adding the floors to the ArrayList
        floor2.add(a);
        floor2.add(b);
        floor2.add(c);
        floor2.add(d);
        floor2.add(e);
        floor2.add(f);
        floor2.add(g);
    }
    
    public ArrayList getFloor2()
    {
        return floor2;
    }
}
