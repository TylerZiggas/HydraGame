package edu.umsl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class hydragame {

    public static void menuSelect() { // Menu select for moves, information, and giving up
        System.out.println("Knight PyPy, what move shall you use to attack the terrifying Hydra?");
        System.out.println("1) Cut off exactly ONE of the Hydra's Heads");
        System.out.println("2) Cut off exactly ONE of the Hydra's Tails");
        System.out.println("3) Cut off exactly TWO of the Hydra's Tails");
        System.out.println("4) Cut off exactly TWO of the Hydra's Heads");
        System.out.println("5) View current Tail and Head count");
        System.out.println("6) See minimum number of attacks to win");
        System.out.println("7) View this menu again");
        System.out.println("-1) Give up");
    }

    public static int attackSelect(int attackNumber, hydra hydra) { // Select attack against the Hydra
        boolean retry;
        do {
            do {
                try {
                    retry = false;
                    Scanner input = new Scanner(System.in);
                    attackNumber = input.nextInt();
                    if (attackNumber == 5) { // Want to reset for options 5, 6, 7 so turn retry to true
                        hydra.currentBoth();
                        retry = true;
                    } else if (attackNumber == 6) {
                        System.out.println("The minimum number of moves is S=" + hydra.minimumMoves() + "\n");
                        retry = true;
                    } else if (attackNumber == 7) {
                        menuSelect();
                        retry = true;
                    } else if (attackNumber == -1) { // Since the user gives up, simply leave here and go back to main
                        return -1;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("That is not one of your moves Knight PyPy!"); // Make sure it is a number
                    retry = true;
                }
            } while (retry);
            if (attackNumber <= 0 || attackNumber >= 8) {
                System.out.println("You have no attacks of that number, try an attack you do know!"); // Tell user if the input is invalid
            }
        } while (attackNumber <= 0 || attackNumber >= 8);
        return attackNumber;
    }

    public static void main(String args[]) {
        System.out.println("Princess Perly has been kidnapped by a magical Hydra! Please save her Knight PyPy!");
        int attackNumber = 0;
        hydra hydra = new hydra();
        hydra.hydraSetUp(); // Set up the Hydra
        int tempHeads; // Keep track of progress through heads and tails
        int tempTails;
        int numOfTurns = 0;
        do {
            menuSelect(); // Go to the menu
            attackNumber = attackSelect(attackNumber, hydra); // Pick the attack
            if (attackNumber == -1){ // If the user has given up, also makes sure congratulations doesn't happen at end
                tempHeads = hydra.currentHeads();
                tempTails = hydra.currentTails();
                System.out.println("You have given up, there is no way to help the princess now.");
                break;
            }
            boolean validTurn = hydra.checkHydra(attackNumber); // Check validity of attack
            if (validTurn) { // Attack if valid
                hydra.attackHydra(attackNumber);
                numOfTurns++;
            }
            tempHeads = hydra.currentHeads();
            tempTails = hydra.currentTails();
            if (tempHeads == 1 && tempTails == 0) { // Check to see if it is impossible to win now
                System.out.println("There is no longer a way to defeat this horrible beast.");
                break;
            }
        } while (tempHeads != 0 || tempTails != 0);
        if (tempHeads == 0 && tempTails == 0) { // Congratulate the player if they succeeded
            System.out.println("Congratulations!  You have defeated the Hydra and saved Princess Perly!");
            System.out.println("It took you a total of " + numOfTurns + " turns!");
        }
    }
}
