import java.util.HashMap;
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
    private HashMap<String, int, int> weaponsInventory;

    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String pDesc, int pPower, int pDefense)
    {
        super(pDesc, pPower, pDefense);
    }
    
    private void fillWeaponsInventory()
    {
        weaponsInventory.put("Small Sword", 7, 2);
        weaponsInventory.put("Gold Sword", 25, 5);
        weaponsInventory.put("Gun", 20, 4);
        weaponsInventory.put("Throwing Star", 10, 0);
        weaponsInventory.put("Bomb", 25, 0);
        weaponsInventory.put("Axe", 12, 2);
        weaponsInventory.put("Bow and Aarow", 15, 5);
        weaponsInventory.put("Wood Shield", 0, 10);
        weaponsInventory.put("Gold Shield", 5, 30);
        weaponsInventory.put("Helmet", 2, 10);
        weaponsInventory.put("Body Armor", 5, 25);
        
    }
    public void print()
    {
        System.out.print("Weapon: " ); 
        super.print();
    }

    public boolean isWeapson()
    {
        return true;
    };
}
