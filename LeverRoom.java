
/**
 * Write a description of class SpecialRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeverRoom extends Room
{

    /**
     * Constructor for objects of class SpecialRoom
     */
    public LeverRoom(String pDesc)
    {
        super(pDesc);
    }
    
    /*public void pull(Command command)
    {
        if(command.getSecondWord().equals("lever"))
        {
            System.out.println("you pull the lever and a staircase slowly extends from the wall \n" +
                                "you see a door at the top"); //desc of what happens
            setExit("up", e2);
            //e1.changeDesc("");
            Room e2 = new Room("a seemingly simple room with a staircase leading down");
            e2.setExit("down", this);
            //e2.changeDesc("");
        }
    }
    */
}
