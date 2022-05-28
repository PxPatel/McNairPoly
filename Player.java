import java.util.ArrayList;

public class Player
{
    private String name;
    private int pos;
    private int GPA;
    private boolean isInGame;
    private ArrayList<Property> propertiesOwned;
    private Card current; 

    private boolean inJail;
    private int daysInJail;

    public Player(String name)
    {
        this.name = name.toUpperCase();
        pos = 0;
        GPA = 100;
        isInGame = true;
        propertiesOwned = new ArrayList<Property>();
        current = new Special("Go", 0, false, false, false, true);
        
        inJail = false;
        daysInJail = 0;

    }

    public void bankrupt(Player obj)
    {
        isInGame = false;

        obj.addGPA(GPA);
        for(Card prop : propertiesOwned)
        {
            ((Property) prop).setOwner(obj);

            obj.addProperty((Property) prop);
        }
    }
    
    public void passGo()
    {
        GPA += 25;
        System.out.println("\n[GO] You passed go and collected 25 GPA");
    }

    public void buy(Card prop)
    {
        propertiesOwned.add((Property) prop);
        ((Property) prop).setOwner(this);
        ((Property) prop).setOwned(true);
        GPA -= ((Property) prop).getCost();
    }

    public void payRent(Card prop)
    {
        Player propOwner = ((Property) prop).getOwner();
        propOwner.addGPA(((Property)prop).getCost());
        this.GPA -= ((Property)prop).getCost();
    }
    
    public void payTax(Card prop)
    {
        GPA -= ((Special) prop).getTax();
    }
    
    public void putInDetention()
    {
       if(!inJail)
       {
            inJail = true;
       }
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

    public void setGPA(int gPA) 
    {
        GPA = gPA;
    }

    public boolean getIsInGame() {
        return isInGame;
    }

    public void setInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public ArrayList<Property> getPropertiesOwned() 
    {
        return propertiesOwned;
    }
    
    public void setPropertiesOwned(ArrayList<Property> propertiesOwned) 
    {
        this.propertiesOwned = propertiesOwned;
    }

    public void addProperty(Property prop) 
    {
        propertiesOwned.add(prop);
    }
    
    public void setCurrent(Card card)
    {
        current = card;   
    }
    
    public Card getCurrent()
    {
        return current;   
    }

    public boolean isInJail() 
    {
        return inJail;
    }

    public void setInJail(boolean inJail) 
    {
        this.inJail = inJail;
    }

    public int getDaysInJail() 
    {
        return daysInJail;
    }

    public void setDaysInJail(int daysInJail) 
    {
        this.daysInJail = daysInJail;
    }

    @Override
    public String toString() 
    {
        if(isInGame)
        {
            String propOwned = "";
            
            int i = 1;
            for(Property prop : propertiesOwned)
            {
                propOwned += i + ") " + prop.getName() + " ";
                i++;
            }
            
            return "\n" + name + " - [GPA = " + GPA + ", Currently on = " + current.getName() + ", Properties: " + propOwned + "]";
        }
        else
        {
            return name + " has gone bankrupt :(";
        }
    }

    
}
