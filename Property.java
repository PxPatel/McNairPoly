public class Property extends Space
{
    private boolean isTax;
    private boolean isDoubleLunch;
    private boolean isDetention;
    private boolean isStudying;

    private int tax;

    private int cost;
    private boolean isOwned;
    private Player owner;
    private int rent;
    
    //A special space constructor
    public Property(String name, int loc, boolean isTax, boolean isDoubleLunch, boolean isDetention, boolean isStudying)
    {
        super(name, loc, true);
        this.isTax = isTax;
        this.isDoubleLunch = isDoubleLunch;
        this.isDetention = isDetention;
        this.isStudying = isStudying;
        
        if(isTax && name.indexOf("Small Tax") != -1)
        {
            tax = 15;
        }
        else if(isTax)
        {
            tax = 25;
        }

        cost = -1;
        isOwned = false;
        owner = null;
        rent = -1;
    }

    //A classroom space constructor
    public Property(String name, int loc, int cost, int rent)
    {
        super(name, loc, false);
        isTax = false;
        isDoubleLunch = false;
        isDetention = false;
        isStudying = false;

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

    public boolean isTax()
    {
        return isTax;
    }

    public boolean isDoubleLunch() 
    {
        return isDoubleLunch;
    }

    public boolean isDetention() 
    {
        return isDetention;
    }

    public boolean isStudying() 
    {
        return isStudying;
    }

    public int getTax()
    {
        return tax;
    }

    public String toString() 
    {
        if(!super.isSpecial() && isOwned)
        {
            return super.getName() + " [Owner = " + owner + ", Rent = " + rent + "]";
        }
        else if(!super.isSpecial())
        {
            return super.getName() + " [Cost = " + cost + ", Rent = " + rent + "]";
        }
        else if(super.isSpecial() && isTax)
        {
            return super.getName() + " [Tax = " + tax + "]";
        }
        else 
        {
            return super.getName();
        }
    } 
}
