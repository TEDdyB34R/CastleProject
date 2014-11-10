
/**
 * Write a description of class Character here.
 * 
 * @author Ethan Dowler
 * @version prototype Character class
 */
public class Hero extends Item
{
    private int maxHealth;

    /**
     * Constructor for objects of class Character
     */
    public Hero(String heroName, int Hp, int pow, int def)
    {
        super(heroName, pow, def);
        maxHealth = Hp;
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void print()
    {
        System.out.print("Hero: ");
        super.print();
        System.out.println("Max Health: "+maxHealth);
        System.out.println();
    }
    
    public void addMaxHealth(int amount)
    {
        maxHealth += amount;
    }
}
