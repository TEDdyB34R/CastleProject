
/**
 * Write a description of class Monster here.
 * 
 * @author Ethan Dowler 
 * @version prototype for Monster class
 */
public class Monster extends Item
{
    private int maxHealth;
    private int currentHealth;

    /**
     * Constructor for objects of class Monster
     */
    public Monster(String pDesc, int pHealth, int pPower, int pDefense)
    {
        super(pDesc, pPower, pDefense);
        maxHealth = pHealth;
        currentHealth = maxHealth;
    }
    
    public int getHealth()
    {
        return currentHealth;
    }
    
    //this is used in the damage calculation method in Game
    public void takeDamage(int pDamage)
    {
        int damage = pDamage - super.getDefense();
        if(damage > 0)
        {
            currentHealth -= damage;
        }
    }
    
    public void print()
    {
        System.out.print("Monster: ");
        super.print();
        System.out.println("Health: "+currentHealth);
        System.out.println();
    }
    
    public boolean isMonster()
    {
        return true;
    }
    
    public void refreshHealth()
    {
        currentHealth = maxHealth;
    }
    
}
