import java.util.ArrayList;

public class Chance extends Card
{

    private String chanceType;

    public Chance(int loc, String chanceType)
    {
        super("Chance! " + chanceType, loc, false, false, false, true);
        this.chanceType = chanceType;
    }


    public void chanceProperty(Player user, Card[] board)
    {
        int addOrSub = (int)(Math.random() * 2);
        ArrayList<Property> copyOfProperties = user.getPropertiesOwned();

        if(addOrSub == 0)
        {
            while(true)
            { 
                int idx = (int)(Math.random() * board.length); // [0, board.length)
                
                if(board[idx].isProperty())
                {
                    Card prop = board[idx];

                    if(! ((Property) prop).getIsOwned() )
                    {
                        user.addProperty((Property) prop);
                        ((Property) prop).setIsOwned(true);
                        ((Property) prop).setOwner(user);
                      
                        System.out.println("\n[LUCKY] " + user.getName() + " just got {" + prop.getName() + "} out of Chance!" );
                        break;
                    }
                }
            }

            
        }
        else if(addOrSub == 1)
        {
            if(copyOfProperties.size() > 0)
            {
                int randPropIndex = (int)(Math.random() * copyOfProperties.size()); // [0, userCurrentProperties.size())

                user.getPropertiesOwned().get(randPropIndex).setOwner(null);
                user.getPropertiesOwned().get(randPropIndex).setIsOwned(false);
              
                Property prop = copyOfProperties.remove(randPropIndex);
              
                user.setPropertiesOwned(copyOfProperties);

                System.out.println("\n[UNLUCKY] " + user.getName() + " just lost {" + prop.getName() + "} out of Chance!" );
            }

            else
            {
                System.out.println("\n[NOTHING TO LOSE] " + user.getName() + " has no properties to lose");
            }
        }
    }

    public void chanceGPA(Player user)
    {
        int addOrSub = (int)(Math.random() * 2);
        int amountOfGPA = (int)(Math.random() * 100);
        int userCurrentGPA = user.getGPA();

        if(addOrSub == 0)
        {
            user.setGPA(userCurrentGPA + amountOfGPA);
            System.out.println("\n[LUCKY] " + user.getName() + " just got {" + amountOfGPA + "} GPA out of Chance!" );
        }
        else if(addOrSub == 1)
        {
            user.setGPA(userCurrentGPA - amountOfGPA);
            System.out.println("\n[UNLUCKY] " + user.getName() + " just lost {" + amountOfGPA + "} GPA out of Chance!" );
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
        return super.getName();
    }
}