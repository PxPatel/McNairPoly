public class Card
{
    private String name;
    private int location;
    private boolean isProperty;
    private boolean isSpecial;
    private boolean isTeleporter;
    private boolean isChance;

    //A classroom space constructor
    public Card(String name, int loc, boolean isProperty, boolean isSpecial, boolean isTeleporter, boolean isChance)
    {
        this.name = name;
        this.location = loc;
        this.isProperty = isProperty;
        this.isSpecial = isSpecial;
        this.isTeleporter = isTeleporter;
        this.isChance = isChance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isProperty() {
        return isProperty;
    }

    public void setProperty(boolean isProperty) {
        this.isProperty = isProperty;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public boolean isTeleporter() {
        return isTeleporter;
    }

    public void setTeleporter(boolean isTeleporter) {
        this.isTeleporter = isTeleporter;
    }
  
    public boolean isChance() {
        return isChance;
    }

    public void setChance(boolean isChance) {
        this.isChance = isChance;
    }

    public String toString() 
    {
        return "Card [isSpecial=" + isSpecial + ", location=" + location + ", name=" + name + "]";
    }
}
