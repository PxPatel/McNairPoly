import java.util.ArrayList;

public class Player 
{
    private String name;
    private int pos;
    private int GPA;
    private boolean isInGame;
    private ArrayList<Property> propertiesOwned;
    // private int numOfPropertiesOwned;

    public Player(String name)
    {
        this.name = name;
        pos = 0;
        GPA = 100;
        isInGame = true;
        propertiesOwned = new ArrayList<Property>();
    }

    public void bankrupt(Player obj)
    {
        this.isInGame = false;

        obj.addGPA(GPA);
        for(Property prop : propertiesOwned)
        {
            obj.addProperty(prop);
        }
    }

    public void buy(Property prop)
    {
        propertiesOwned.add(prop);
        prop.setOwner(this);
    }

    public void payRent(Property prop)
    {
        Player propOwner = prop.getOwner();
        propOwner.addGPA(prop.getCost());
        this.GPA -= prop.getCost();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getGPA() {
        return GPA;
    }

    public void addGPA(int GPA) {
        this.GPA += GPA;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public ArrayList<Property> getPropertiesOwned() 
    {
        return propertiesOwned;
    }

    public void addProperty(Property prop) 
    {
        propertiesOwned.add(prop);
    }

    @Override
    public String toString() 
    {
        if(isInGame)
        {
            return "Player [GPA=" + GPA + ", isInGame=" + isInGame + ", name=" + name + ", pos=" + pos + ", Properties: " + propertiesOwned + "]";
        }
        else
        {
            return name + " has gone bankrupt :(";
        }
    }

    
}
