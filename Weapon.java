
/**
 * Write a description of class Weapon here.
 * 
 * @author Ethan Dowler 
 * @version prototype Weapon class
 */
public class Weapon extends Item
{
    private String desc;
    private int power;
    private int defense;

    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String pDesc, int pPower, int pDefense)
    {
        super(pDesc, pPower, pDefense);
    }

    public void print()
    {
        System.out.print("Weapon: ");
        super.print();
    }

    public boolean isWeapson()
    {
        return true;
    };
}
