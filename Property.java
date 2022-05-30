public class Property extends Card
{
    private int cost;
    private boolean isOwned;
    private Player owner;
    private int baseRent;

    private final int[] rentMultiplier = {1, 2, 3, 4, 5};
    private int rentLevel;

    public Property(String name, int loc, int cost, int baseRent)
    {
        super(name, loc, true, false, false, false);

        this.cost = cost;
        this.isOwned = false;
        this.owner = null;
        this.baseRent = baseRent;

        this.rentLevel = 0;
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

    public void setIsOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getRent() {
        return baseRent * rentMultiplier[rentLevel];
    }

    public void setRent(int baseRent) {
        this.baseRent = baseRent;
    }

    public int[] getRentMultiplier() {
        return rentMultiplier;
    }

    public int getRentLevel() {
        return rentLevel;
    }

    public void setRentLevel(int rentLevel) {
            this.rentLevel = rentLevel;
    }

    public void incrementRentLevel()
    {
        if(rentLevel < 4)
        {
            this.rentLevel++;
        }
    }

    @Override
    public String toString()
    {
        if(isOwned)
        {
            return super.getName() + " [Owner = " + owner.getName() + ", Rent = " + this.getRent() + "]";
        }
        else
        {
            return super.getName() + " [Cost = " + cost + ", Rent = " + baseRent + "]";
        }
    }
}