package edu.umsl;
import java.util.Scanner;
import java.util.InputMismatchException;
import lombok.Getter;
import lombok. Setter;

public class hydra extends hydragame {
    @Getter
    @Setter
    private int numOfHeads;
    @Getter
    @Setter
    private int numOfTails;

    public void hydraSetUp() {
        boolean retry;
        do {
            try {
                retry = false;
                Scanner input = new Scanner(System.in);
                System.out.println("How many heads does the Hydra you see have?");
                numOfHeads = input.nextInt();
                System.out.println("And how many tails does it have?");
                numOfTails = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("That is not a number!");
                retry = true;
            }
        } while (retry);
    }

    public int currentHeads() {
        return numOfHeads;
    }

    public int currentTails() {
        return numOfTails;
    }

    public void currentBoth() {
        System.out.println("The number of heads is " + numOfHeads);
        System.out.println("The number of tails is " + numOfTails);
    }

    public int minimumMoves() {
        int minimumMoves = 0;
        int minTails = numOfTails;
        int minHeads = numOfHeads;

        do {
            if (minTails > 1) {
                minTails = minTails - 2;
                minHeads++;
                minimumMoves++;
            } else if (minTails == 1) {
                minTails++;
                minimumMoves++;
            }
            if (minHeads > 1) {
                minHeads = minHeads - 2;
                minimumMoves++;
            }
            if (minHeads == 1 && minTails == 0) {
                minimumMoves = -1;
                break;
            }
        } while (minHeads != 0 || minTails != 0);
        return minimumMoves;
    }

    public boolean checkHydra(int attackNumber) {
        switch(attackNumber) {
            case 1:
                if (numOfHeads < 1) {
                    System.out.println("You tried to swing at a head, but the Hydra doesn't have any!");
                    return false;
                }
                break;
            case 2:
                if (numOfTails < 1) {
                    System.out.println("You tried to swing at a tail, but the Hydra currently has none!");
                    return false;
                }
                break;
            case 3:
                if (numOfTails < 2) {
                    System.out.println("You tried to swing at two of the Hydra's tails, but it doesn't even have two!");
                    return false;
                }
                break;
            case 4:
                if (numOfHeads < 2) {
                    System.out.println("You tried to swing at two of the Hydra's heads, but it doesn't even have two!");
                    return false;
                }
                break;
            default:
                System.out.println("What should I use against this monster?");
                break;
        }
        return true;
    }

    public void attackHydra(int attackNumber) {
        if (attackNumber == 1) {
            System.out.println("You have cut off a Hydra head, but a new head grew in its place!");
        } else if (attackNumber == 2) {
            numOfTails++;
            System.out.println("You have cut off a Hydra tail, but two new tails grew in its place!");
        } else if (attackNumber == 3) {
            numOfTails = numOfTails - 2;
            numOfHeads++;
            System.out.println("You have cut off exactly two of the Hydra's tails, but a new head grew in their place!");
        } else if (attackNumber == 4) {
            numOfHeads= numOfHeads - 2;
            System.out.println("You have cut off exactly two of the Hydra's heads!");
        } else {
            System.out.println("How should I attack this creature?");
        }
    }
}
