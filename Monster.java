
/**
 * Write a description of class Monster here.
 * 
 * @author Ethan Dowler 
 * @version prototype for Monster class
 */
public class Monster extends Item
{
    private int maxHealth;
    private int currentHP;

    /**
     * Constructor for objects of class Monster
     */
    public Monster(String pDesc, int pHealth, int pPower, int pDefense)
    {
        super(pDesc, pPower, pDefense);
        maxHealth = pHealth;
        currentHP = maxHealth;
    }
    
    public int getHealth()
    {
        return currentHP;
    }
    
    //this is used in the damage calculation method in Game
    public void takeDamage(int pDamage)
    {
        if(pDamage > 0)
        {
            currentHP -= pDamage;
        }
    }
    
    public void print()
    {
        System.out.print("Monster: ");
        super.print();
        System.out.println("Health: "+this.currentHP);
        System.out.println();
    }
    
    public boolean isMonster()
    {
        return true;
    }
    
    public void refreshHealth()
    {
        currentHP = maxHealth;
    }
    
}
