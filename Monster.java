
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
    private int mSpawnRate;

    /**
     * Constructor for objects of class Monster
     */
    public Monster(String pDesc, int pHealth, int pPower, int pDefense,  int pSpawnRate)
    {
        super(pDesc, pPower, pDefense);
        maxHealth = pHealth;
        currentHealth = maxHealth;
        mSpawnRate = pSpawnRate;
    }
    
    public int getHealth()
    {
        return maxHealth;
    }
    
    //this is the likelihood that a monster will appear in a room
    // rate = (1 / mSpawnRate); the spawnrate is used in conjunction with Random
    public int getSpawnRate()
    {
        return mSpawnRate;
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
