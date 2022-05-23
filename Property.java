public class Property extends Card
{
    private int cost;
    private boolean isOwned;
    private Player owner;
    private int rent;

    public Property(String name, int loc, int cost, int rent)
    {
        super(name, loc, false);

        this.cost = cost;
        isOwned = false;
        owner = null;
        this.rent = rent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean getIsOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    @Override
    public String toString()
    {
        if(isOwned)
        {
            return super.getName() + " [Owner = " + owner.getName() + ", Rent = " + rent + "]";
        }
        else
        {
            return super.getName() + " [Cost = " + cost + ", Rent = " + rent + "]";
        }
    }
}