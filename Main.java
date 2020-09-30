package hotpotato;

import java.util.Arrays;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random RAND_NUM_GEN = new Random();

    public static void main(String[] args) {



        CircularlyLinkedList<String> circularlyLinkedList = new CircularlyLinkedList<String>();//creates new object
        int numberOfPlayers;//number of players in the game
        int index = 1;   //position of player
        Scanner input = new Scanner(System.in);
        //tests for exceptions
        while(true) {
            try {
                System.out.println("Enter the number of players");
                numberOfPlayers = input.nextInt();
                while(numberOfPlayers < 0){
                    System.out.println("Enter positive numbers ");
                    System.out.println("Enter number of players ");
                    numberOfPlayers = input.nextInt();
                }
                break;
            }catch (InputMismatchException e) {
                System.out.println("Enter only integers");
                input.nextLine();
            }
        }
        input.nextLine(); //clear the enter key

        //asks the user to implements players wanted
        while (circularlyLinkedList.size() < numberOfPlayers) {
            System.out.println("Enter player " + index + ": ");
            String playerName = input.nextLine();
            circularlyLinkedList.addLast(playerName);//adds each node
            index += 1;   //increases the position by one
        }
        System.out.println(circularlyLinkedList);//prints the toString method

        int p =0;
        while(p < 9) {
            System.out.println("Switch who ");
            String child1 = input.nextLine();
            System.out.println("With ");
            String child2 = input.nextLine();
            circularlyLinkedList.exchange(child1, child2);
            System.out.println("Players: " + circularlyLinkedList);
            System.out.println("================================");
            p += 1;
        }

        int stopNumber = RAND_NUM_GEN.nextInt(20);


        while (numberOfPlayers > 1) {
            System.out.println("Random Time: " + stopNumber);
            while (stopNumber >= 0) {
                circularlyLinkedList.rotate();//passes the potato
                System.out.println("Has Potato: " + circularlyLinkedList.last());
                stopNumber -= 1;
            }
            stopNumber = RAND_NUM_GEN.nextInt(20);//randomly selects integer in the bound
            int number = 0;

            //while loop to put whoever has the potato has the first
            while(number <= circularlyLinkedList.size() - 2){
                circularlyLinkedList.rotate();
                number+=1;
            }
            circularlyLinkedList.removeFirst();//removes each player who has the potato
            System.out.println("STOP!");
            System.out.println("Players left: " + circularlyLinkedList);//prints the players lef
            System.out.println("==============================");
            numberOfPlayers-=1;
        }
        System.out.println("GameOver!");
        System.out.println("Winner is " + circularlyLinkedList.first());//declares the winner eventually
    }


}

