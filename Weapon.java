
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
        power = pPower;
        defense = pDefense;
    }
    
    public void print()
    {
        System.out.print("Weapon: " ); 
        super.print();
    }

    public boolean isWeapon()
    {
        return true;
    }
    
    public int getPower()
    {
        return power;
    }

    public int getDefense()
    {
       return defense; 
    }
}
