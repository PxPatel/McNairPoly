import java.util.ArrayList;

public class Chance extends Card
{

    private String chanceType;

    public Chance(int loc, String chanceType)
    {
        super(" ", loc, false, false, false, true);
        this.chanceType = chanceType;
    }


    public void chanceProperty(Player user, Card[] board)
    {
        int addOrSub = (int)(Math.random() + 1);
        ArrayList<Property> copyOfProperties = user.getPropertiesOwned();

        if(addOrSub == 0)
        {
            while(true)
            { 
                int idx = (int)(Math.random() * board.length); // [0, board.length]
                
                if(board[idx].isProperty())
                {
                    Card prop = board[idx];

                    if(! ((Property) prop).getIsOwned() )
                    {
                        user.addProperty((Property) prop);
                        break;
                    }
                }
            }

            //Add Print
        }
        else if(addOrSub == 1)
        {
            int randPropIndex = (int)(Math.random() * copyOfProperties.size()); // [0, userCurrentProperties.size()] 
            copyOfProperties.remove(randPropIndex);

            user.setPropertiesOwned(copyOfProperties);

            //Add Print
        }
    }

    public void chanceGPA(Player user)
    {
        int addOrSub = (int)(Math.random() + 1);
        int amountOfGPA = (int)(Math.random() * 100);
        int userCurrentGPA = user.getGPA();

        if(addOrSub == 0)
        {
            user.setGPA(userCurrentGPA + amountOfGPA);

            //Add Print State
        }
        else if(addOrSub == 1)
        {
            user.setGPA(userCurrentGPA - amountOfGPA);
            //Add Print State
        }
    }

    public String getChanceType() 
    {
        return chanceType;
    }

    public void setChanceType(String chanceType) 
    {
        this.chanceType = chanceType;
    }


    @Override
    public String toString() {
        return "Chance [chanceType=" + chanceType + "]";
    }
}
