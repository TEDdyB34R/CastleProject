
/**
 * Write a description of class SpecialRoom here.
 * 
 * @author Ethan Dowerl
 * @version prototype room with a lever
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
    
    public void pull(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Pull what?");
            return;
        }
        
        else if(command.getSecondWord().equals("lever"))
        {
            System.out.println("you pull the lever and a staircase slowly extends from the wall \n" +
                                "you see a door at the top"); //desc of what happens
            
            changeDesc("a room with a staircase that extends from the wall");
            Room e2 = new Room("a seemingly simple room with a staircase leading down");
            e2.setExit("down", this);
            e2.addItem("small sword", Game.armory.get("small sword"));
            setExit("up", e2);
            System.out.println(this.getLongDescription());
        }
    }
    
}
