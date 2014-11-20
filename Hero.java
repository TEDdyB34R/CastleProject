
/**
 * Write a description of class Hero here.
 * 
 * @author Ethan Dowler
 * @version prototype Character class
 */
public class Hero extends Item
{
    private int maxHealth;
    private int exp;
    private int level;

    /**
     * Constructor for objects of class Character
     */
    public Hero(String heroName, int Hp, int pow, int def)
    {
        super(heroName, pow, def);
        maxHealth = Hp;
        int exp = 0;
        int level = 1;
    }
    
        public void print()
    {
        System.out.print("Hero: ");
        super.print();
        System.out.println("Max Health: "+maxHealth);
        System.out.println();
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    public int getExp()
    {
        return exp;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public void addMaxHealth(int amount)
    {
        maxHealth += amount;
    }
    
    public void gainExp(int amount)
    {
        exp += amount;
        System.out.println("You have gained "+amount+" experience points from battle");
    }
    
    public void levelUp()
    {
        level++;
        System.out.println("Congratulations! You are now level "+level);
        System.out.println("You have become stronger and wiser in battle");
    }
}
