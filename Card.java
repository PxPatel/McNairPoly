public class Card
{
    private String name;
    private int location;
    private boolean isSpecial;
    private boolean isTeleporter;

    //A classroom space constructor
    public Card(String name, int loc, boolean isSpecial, boolean isTeleporter)
    {
        this.name = name;
        this.location = loc;
        this.isSpecial = isSpecial;
        this.isTeleporter = isTeleporter;
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
  
    public String toString() 
    {
        return "Card [isSpecial=" + isSpecial + ", location=" + location + ", name=" + name + "]";
    }
}
