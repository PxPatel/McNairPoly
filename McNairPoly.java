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
    private final int BOARD_SIZE = 40 ; //11 x 11

    private final int GPA_TO_WIN = 400;
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
        numUsers = num;

        //To consume the empty line and not cause errors regarding the Scanner class
        scan.nextLine();

        players = new Player[numUsers];
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

    private void initializeBoard()
    {
        board = new Card[BOARD_SIZE];

        board[0] = new Special("Go", 0, false, false, false, true);
        board[1] = new Property("Gym", 1, 5, 3);
        board[2] = new Chance(2, "GPA");
        board[3] = new Property("Creative Writing",3, 7, 4);
        board[4] = new Special("Small Tax - Forgot HW", 4, true, false, false, false);
        board[5] = new Teleporter(5, 1);
        board[6] = new Property("French 1", 6, 10, 5);
        board[7] = new Chance(7, "Property");
        board[8] = new Property("Driver\'s Ed", 8, 11, 7);
        board[9] = new Property("Lit 3", 9, 13, 8);
        board[10] = new Special("Big Tax - Dress Coded", 10, true, false, false, false);

        board[11] = new Property("Chem Honors", 11, 15, 9);
        board[12] = new Chance(12, "Property");
        board[13] = new Property("AP Environmental Science", 13, 15, 10);
        board[14] = new Property("JROTC", 14, 17, 10);
        board[15] = new Teleporter(15, 2);
        board[16] = new Property("Anatomy", 16, 20, 13);
        board[17] = new Chance(17, "GPA");
        board[18] = new Property("AP USH", 18, 22, 16);
        board[19] = new Property("Mythology", 19, 25, 19);

        board[20] = new Special("Double Lunch", 20, false, true, false, false);
        board[21] = new Property("Financial Literacy", 21, 29, 19);
        board[22] = new Chance(22, "GPA");
        board[23] = new Property("AP Biology", 23, 31, 20); 
        board[24] = new Property("French 3", 24, 36, 21); 
        board[25] = new Teleporter(25, 3);
        board[26] = new Property("Human Psychology", 26, 40, 22); 
        board[27] = new Special("Small Tax - Late to Class", 27, true, false, false, false);
        board[28] = new Chance(28, "Property");
        board[29] = new Property("AP Spanish", 29, 42, 17); 
        board[30] = new Special("Detention", 30, false, false, true, false);

        board[31] = new Property("AP Calc AB", 31, 47, 18); 
        board[32] = new Property("AP Government", 32, 50, 19); 
        board[33] = new Chance(33, "Property");
        board[34] = new Property("AP Environmental Science", 34, 55, 20); 
        board[35] = new Teleporter(35, 4);
        board[36] = new Chance(36, "GPA");
        board[37] = new Property("AP Chem", 37, 64, 25);
        board[38] = new Special("Small Tax - Forgot Project", 38, true, false, false, false);
        board[39] = new Property("AP Calc BC", 39, 70, 30);
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
        if(playerAtTurn.getIsInGame() && playerAtTurn.isInJail())
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
            else if(((Special) landed).isDoubleLunch())
            {
                System.out.println("\nEnjoy the Free Period!");
            }
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

          System.out.println("\n" + playerAtTurn.getName() + " will be teleported to " + board[refinedLoc]);
          
          action();
        }

        else if(landed.isChance())
        {
            String type = ((Chance) landed).getChanceType();

            if(type.equals("GPA"))
            {
                ((Chance) landed).chanceGPA(playerAtTurn);
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
                if(((Property) landed).getOwner().getName().equals(playerAtTurn.getName()))
                {
                    System.out.println("\n[NOTHING] " + playerAtTurn.getName() + " landed on their own property, so nothing happens :|");
                }
                else if(playerAtTurn.getGPA() > ((Property) landed).getRent())
                {
                    playerAtTurn.payRent(landed);
                    sleep();
                    System.out.println("\n[RENT] " + playerAtTurn.getName() + " has payed $" + ((Property) landed).getRent() + " to " + ((Property) landed).getOwner().getName());
                    ((Property) landed).incrementRentLevel();
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
            System.out.println("\n\n[STILL IN JAIL] You didn't make it out of jail :(");
            playerAtTurn.setDaysInJail(playerAtTurn.getDaysInJail() + 1);
            sleep();
        }
    }


    public void checkBankrupcy()
  {
    if(playerAtTurn.getGPA() < 0 && playerAtTurn.getIsInGame())
    {
      playerAtTurn.setInGame(false);
      playerAtTurn.bankruptToBank();
      sleep();
      System.out.println("\n" + playerAtTurn.getName() + " is BANKRUPT!");
    }
  }

    public boolean checkIfWinner()
    {
        boolean winnerByGPA = false;
        int numPlayersInGame = 0;
        Player saveFirstPlayer = null;

        for(Player p : players)
        {
            if(p.getIsInGame())
            {
                numPlayersInGame++;
                if(numPlayersInGame == 1)
                {
                    saveFirstPlayer = p;
                }

                if(p.getGPA() >= GPA_TO_WIN)
                {
                    winnerByGPA = true;
                    System.out.println("\n" + p.getName() + " HAS WON!");
                }
            }
        }

        if(numPlayersInGame == 1)
        {
            System.out.println("\n" + saveFirstPlayer.getName() + " HAS WON!");
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

    public boolean isGameOver() 
    {
        return gameOver;
    }
  
    public void choices()
    {
        System.out.println(
        "\n" + playerAtTurn.getName() + ", choose what you would like to do: \n1) Roll and Move\n\n2) Check your Stats\n\n3) Check all players' Stats\n\n4) End Game");
        
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
              break; 
            }

        }
    }
}