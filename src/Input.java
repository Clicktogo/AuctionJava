// Filip Garcia
/*

//Moved to IO.

import java.util.Scanner;

public class Input {

    private static final Scanner INPUT = new Scanner(System.in); // den ska vara static. det ska bara finnas en.

    public String userInputString(String askInput) {
        System.out.print(askInput);
        return INPUT.nextLine().toLowerCase().trim();
    }

    public int userInputInt(String askInput) {
            int userAnswer;
            System.out.print(askInput);

            while(!INPUT.hasNextInt()){
                System.out.println("Error, please enter an integer");
                INPUT.next();
            }

            userAnswer = INPUT.nextInt();
            INPUT.nextLine();
            return userAnswer;
    }

    public double userInputDouble(String askInput) {
            double userAnswer;
            System.out.print(askInput);
            while (!INPUT.hasNextDouble()){
                System.out.println("Error! Please enter a number and you can only use \",\" and not \".\"");
                System.out.println("Why you might ask? Because the system don't like it =)");
                INPUT.next();
            }
            userAnswer = INPUT.nextDouble();
            INPUT.nextLine();
            return userAnswer;
    }
}
*/




