class Main 
{
    public static void main(String[] args)
    {
        McNairPoly g = new McNairPoly();
        


        //Runs game while no winner
        while(!g.checkIfWinner())
        {
            if(!g.isPlayerInJail())
            {
                g.move();
                g.action();
                g.nextTurn();
            }

            else
            {
                g.inJailAction();
                g.nextTurn();
            }
        }

    }
}