public class Property extends Space
{

    private boolean isSpecial;
    private int cost;
    private boolean isOwned;
    private Player owner;
    private int rent;


    public Property(int loc)
    {
        super("Blank", loc);
        cost = 1;
        isOwned = false;
        owner = null;
        isSpecial = false;
        rent = 2;
    }
    
    public Property(String name, int loc, boolean isSpecial)
    {
        super(name, loc);
        this.isSpecial = isSpecial;
        this.cost = -1;
        this.isOwned = false;
        this.owner = null;
        this.rent = -1;
    }

    public Property(String name, int loc, boolean isSpecial, int cost, int rent)
    {
        super(name, loc);
        this.isSpecial = isSpecial;
        this.cost = cost;
        isOwned = false;
        owner = null;
        this.rent = rent;
        
    }

    public int getCost() 
    {
        return cost;
    }

    public void setCost(int cost) 
    {
        this.cost = cost;
    }

    public boolean isOwned() 
    {
        return isOwned;
    }

    public void setOwned(boolean isOwned) 
    {
        this.isOwned = isOwned;
    }

    public boolean isSpecial() 
    {
        return isSpecial;
    }

    public Player getOwner() 
    {
        return owner;
    }

    public void setOwner(Player owner) 
    {
        this.owner = owner;
    }

    public int getRent() 
    {
        return rent;
    }

    public void setRent(int rent) 
    {
        this.rent = rent;
    }

    @Override
    public String toString() 
    {
        return "Property [name="  + super.getName() + ", cost=" + cost + "]";
    } 
    
    
}
