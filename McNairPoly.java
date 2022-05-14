import java.util.Scanner;

public class McNairPoly
{
    private Player[] players;
    private int numUsers;
    private Scanner scan = new Scanner(System.in);
    private int turn;
    private Property[] board;
    private final int BOARD_SIZE = 22;


    public McNairPoly()
    {
        turn = 0;

        int num = 100;
        while(num < 0 || num > 6)
        {
            System.out.print("How many people are playing (1-6)? ");
            num = scan.nextInt();
        }

        //To consume the empty line and not cause errors regarding the Scanner class
        scan.nextLine();

        players = new Player[num];
        numUsers = num;

        // Asks for player names and assigns them
		for (int i = 0; i < numUsers; i++) 
        {
			System.out.print("What is player " + (i + 1) + "'s name? ");
			String name = scan.nextLine();
			players[i] = new Player(name);
		}

        //Initalizes the array and creates a Property object 
        board = new Property[BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            board[i] = new Property(i);
        }
    }

    private int roll()
    {
        int a = (int)(Math.random() * 12) + 1;
        int b = (int)(Math.random() * 12) + 1;
        return a + b;
    }

    public void move()
    {
        int roll = roll();
        int currPos = players[turn].getPos();
        int newPos = (currPos + roll) % board.length;
        players[turn].setPos(newPos);

        if(currPos > newPos)
        {
            // players[turn].passGo();
        }
    }

    public boolean isOwned()
    {
        int currPos = players[turn].getPos();
        boolean ans = board[currPos].isOwned();
        return ans;
    }

    public void action()
    {
        Player mainPlayer = players[turn]; 
        Property landed = board[mainPlayer.getPos()];
        if(landed.isSpecial())
        {
            /*
            if(landed.isTax)
            {
                mainPlayer.payTax();
            }
            else if(landed.isDoubleLunch)
            {
                mainPlayer.inDoubleLunch();
            }
            else if(landed.isDetention)
            {
                mainPlayer.inDetention();
            }
            */
        }
        else if(!landed.isOwned())
        {
            System.out.print("Would you like to buy " + landed.getName() + "? (Y/N) ");
            String choice = scan.nextLine();
            if(choice.equals("Y"))
            {
                players[turn].buy(landed);
                System.out.println(mainPlayer.getName() + " just bought " + landed.getName() + "!");
            }
        }
        else if(landed.isOwned())
        {
            if(players[turn].getGPA() >= landed.getRent())
            {
                players[turn].payRent(landed);
                System.out.println(mainPlayer.getName() + " has payed $" + landed.getRent() + " to " + landed.getOwner().getName());
            }
            else
            {
                players[turn].bankrupt(landed.getOwner());
                System.out.println(mainPlayer.getName() + " is BANKRUPT!");
            }
        }
    }

    public boolean checkIfWinner()
    {
        for(Player p : players)
        {
            if(p.isInGame() && p.getGPA() > 1000)
            {
                return true;
            }
        }

        return false;
    }

    public void nextTurn()
    {
        int temp = (turn + 1) % numUsers;

        while(true)
        {
            if(players[temp].isInGame())
            {
                turn = (turn + 1) % numUsers;
                break;
            }
            else
            {
                temp = (turn + 1) % numUsers;
            }
        }
    }
    
    public Player[] getPlayers()
    {
        return players;
    }
}