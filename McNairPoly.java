import java.util.Scanner;

public class McNairPoly
{
    private Player[] players;
    private int numUsers;
    private int turn;
    private Player playerAtTurn;
    private boolean gameOver;
  
    private Scanner scan = new Scanner(System.in);
    
    private Card[] board;
    private final int BOARD_SIZE = 24; //6 x 6 board lies

    private final int waitTimer = 750; 

    public McNairPoly()
    {
        turn = 0;

        int num = 100;
        while(num <= 1 || num > 6)
        {
            System.out.print("How many people are playing (> 1 and < 7)? ");
            num = scan.nextInt(); 
        }

        //To consume the empty line and not cause errors regarding the Scanner class
        scan.nextLine();

        players = new Player[num];
        numUsers = num;
         // Asks for player names and assigns them
		for (int i = 0; i < numUsers; i++) 
        {
			System.out.print("What is Player " + (i + 1) + "'s name? ");
			String name = scan.nextLine();
			players[i] = new Player(name);
		}

        //Initalizes the array and creates a Property objects
        initializeBoard();

        playerAtTurn = players[0];
        gameOver = false;
    }

    public void initializeBoard()
    {
        board = new Card[BOARD_SIZE];

        board[0] = new Special("Go", 0, false, false, false, true);
        board[1] = new Property("Gym", 1, 5, 3);
        board[2] = new Property("Mythology", 2, 7, 4);
        board[3] = new Special("Small Tax - Forgot HW", 3, true, false, false, false);
        board[4] = new Property("French 1", 4, 10, 5);
        board[5] = new Special("Big Tax - Dress Coded", 5, true, false, false, false);

        board[6] = new Property("Driver\'s Ed", 6, 13, 7);
        board[7] = new Property("Lit 3", 7, 16, 8);
        board[8] = new Property("Chem Honors", 8, 18, 9);
        board[9] = new Property("AP Environmental Science", 8, 20, 10);

        board[10] = new Special("Detention", 9, false, false, true, false);
        board[11] = new Property("AP Lit", 10, 30, 15);
        board[12] = new Special("Small Tax - Forgot Project", 11, true, false, false, false);
        board[13] = new Property("AP USH", 12, 35, 18);
        board[14] = new Property("AP Calc AB", 13, 37, 19);
        board[15] = new Special("Double Lunch", 14, false, true, false, false);
        
        board[16] = new Special("Small Tax - Late to Class", 16, true, false, false, false);
        board[17] = new Property("AP Physics 1", 17, 56, 28);
        board[18] = new Property("AP Chem", 18, 64, 32);
        board[19] = new Property("AP Calc BC", 19, 70, 35); 
        board[20] = new Teleporter(20, 1);
        board[21] = new Teleporter(21, 2);
        board[22] = new Teleporter(22, 3);
        board[23] = new Teleporter(23, 4);
        
    }

    private void sleep()
    {
        try
        {
            Thread.sleep(waitTimer);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private int[] roll()
    {
        int a = (int)(Math.random() * 6) + 1;
        int b = (int)(Math.random() * 6) + 1;
        int[] total = {a,b};
        System.out.print("[Rolling...] ==> ");

        sleep();

        System.out.print(playerAtTurn.getName() + " rolled a [" + a + "] [" + b + "]");

        return total;
    }

    public void move()
    {
        System.out.println("\n[New Turn] " + playerAtTurn.getName() + ", would you like to roll? [ENTER TO CONTINUE]");
        scan.nextLine();

        int[] dice = roll();
        int roll = dice[0] + dice[1];
        System.out.println(" ==> " + roll);

        int currPos = playerAtTurn.getPos();
        int newPos = (currPos + roll) % board.length;
        playerAtTurn.setPos(newPos);
        
        playerAtTurn.setCurrent(board[newPos]);

        if(currPos > newPos)
        {
            playerAtTurn.passGo();
        }
        sleep();
        System.out.println("\n" + playerAtTurn.getName() + " landed on " + board[newPos]);
    }

    public boolean isPlayerInJail()
    {
        if(playerAtTurn.getIsInGame() && playerAtTurn.isInJail()) //Change
        {
            return true;
        }
        return false;
    }
  
    public void action()
    {
        Card landed = board[playerAtTurn.getPos()];
 
        sleep();
        if(landed.isSpecial())
        {
       
            if(((Special) landed).isTax())
            {
                playerAtTurn.payTax(landed);
            }
            // else if(landed.isDoubleLunch)
            // {
            //     mainPlayer.inDoubleLunch();
            // }
            else if(((Special) landed).isDetention())
            {
                playerAtTurn.putInDetention();
                sleep();
                System.out.println("\n[DETENTION] OH NO! You are now in Detention!");
            }
            
        }

        else if(landed.isTeleporter())
        {
          int rawLoc = ((Teleporter) landed).teleport();
          int refinedLoc = rawLoc % BOARD_SIZE;

          playerAtTurn.setPos(refinedLoc);
          playerAtTurn.setCurrent(board[refinedLoc]);

          System.out.println("\n" + playerAtTurn.getName() + " will be teleported to " + board[refinedLoc].getName());
          
          action();
        }

        else if(landed.isChance())
        {
            String type = ((Chance) landed).getChanceType();

            if(type.equals("GPA"))
            {
                ((Chance) landed).chanceGPA(playerAtTurn);
                if(playerAtTurn.getGPA() < 0)
                {
                    playerAtTurn.bankruptToBank();
                    sleep();
                    System.out.println(playerAtTurn.getName() + " is BANKRUPT!");
                }

            }
            else if(type.equals("Property"))
            {
                ((Chance) landed).chanceProperty(playerAtTurn, board);
            }
        }
        
        else if(landed.isProperty())
        {
            if(!((Property) landed).getIsOwned())
            {
                while(true)
                {
                    System.out.print("\nWould you like to buy {" + landed.getName() + "}? (Y/N) "); 
                    String choice = scan.nextLine().toUpperCase();
                    if(choice.equals("Y") && playerAtTurn.getGPA() >= ((Property) landed).getCost())
                    {
                        players[turn].buy(landed);
                        sleep();
                        
                        System.out.println("\n[BOUGHT] " + playerAtTurn.getName() + " just bought " + landed.getName() + "!");
                        break;
                    }
                    else if(choice.equals("Y"))
                    {
                        sleep();
                        System.out.println("\n[NOT ENOUGH] Sorry, you don't have enough GPA to buy " + landed.getName());
                        break;
                    }
                    else if(choice.equals("N"))
                    {
                        break;
                    }
                    
                }
                
            }
            else if(((Property) landed).getIsOwned())
            {
                if(playerAtTurn.getGPA() > ((Property) landed).getRent())
                {
                    playerAtTurn.payRent(landed);
                    sleep();
                    System.out.println("\n[RENT] " + playerAtTurn.getName() + " has payed $" + ((Property) landed).getRent() + " to " + ((Property) landed).getOwner().getName());
                }
                else
                {
                    playerAtTurn.bankrupt(((Property) landed).getOwner());
                    sleep();
                    System.out.println(playerAtTurn.getName() + " is BANKRUPT!");
                }
            }
        }
    }

    public void inJailAction()
    {
        System.out.println("\n[New Turn] " + playerAtTurn.getName() + ", would you like to roll? [ENTER TO CONTINUE]");
        scan.nextLine();
    
        int[] dice = roll();
        boolean isDouble = dice[0] == dice[1];

        if(isDouble)
        {
            playerAtTurn.setInJail(false);
            playerAtTurn.setDaysInJail(0);
            sleep();
            System.out.println("\n\n[FREEDOM] You rolled a double and are now out of Detention!");
        }
        else if(playerAtTurn.getDaysInJail() == 2)
        {
            playerAtTurn.setInJail(false);
            playerAtTurn.setDaysInJail(0);
            sleep();
            System.out.println("\n\n[FREEDOM] You spent 2 days and are now out of Detention!");
        }
        else
        {
            playerAtTurn.setDaysInJail(playerAtTurn.getDaysInJail() + 1);
            sleep();
        }
        
    }

    public boolean checkIfWinner()
    {
        boolean winnerByGPA = false;
        int numPlayersInGame = 0;

        for(Player p : players)
        {
            if(p.getIsInGame())
            {
                numPlayersInGame++;
                if(p.getGPA() >= 375)
                {
                    winnerByGPA = true;
                }
            }
        }
        return winnerByGPA || numPlayersInGame == 1;
    }
    
    
    private void checkGlobalStats()
    {
        String contain = "";
        for(Player user : players)
        {
            contain += user.toString() + "\n";
        }
        
        System.out.println(contain);
    }
    
    private void checkPlayerStats()
    {
        System.out.println(playerAtTurn.toString());
    }

    public void nextTurn()
    {
        sleep();
        int temp = (turn + 1) % numUsers;

        while(true)
        {
            if(players[temp].getIsInGame())
            {
                turn = temp;
                playerAtTurn = players[turn];
                break;
            }
            else
            {
                temp = (temp + 1) % numUsers;
            }
        }
    }

    public void divider()
    {
        System.out.println("\n------------------------------------------------------");
    }
    
    public Player[] getPlayers()
    {
        return players;
    }

    public void getBoard()
    {
        for(Card i : board)
        {
            System.out.println(i);
        }
    }

    public boolean isGameOver() //Change
    {
    return gameOver;
    }
  
    public void choices()
    {
        System.out.println(
        "\n" + playerAtTurn.getName() + ", choose what you would like to do: \n1) Roll and Move\n\n2) Check your Stats\n\n3) Check all players' Stats\n\n4) Quit Game");
        
        while(true)
        {
            System.out.print("\nEnter choice : ");
            String ans = scan.nextLine();

            if(ans.equals("1"))
            {
                break;
            }

            else if(ans.equals("2"))
            {
                this.checkPlayerStats(); 
            }

            else if(ans.equals("3"))
            {
                this.checkGlobalStats();
            }

            else if(ans.equals("4"))
            {
              gameOver = true;
              break; //Change
            }

        }
    }
}
