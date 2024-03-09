package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {

    // Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final int scope;
    private final int attempts;
    public int currentAttempt = 0;


    public GuessNumberGame(String argument) {
        int m;
        try {
            m = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        if (m < 1 || m > 400) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        this.scope = m;
        this.attempts = (int) (Math.log(m) / Math.log(2)) + 1;
    }

    public String printProgressBar() {
        return "[" + "*".repeat(currentAttempt + 1) + ".".repeat(attempts - currentAttempt  - 1) + "]";
    }

    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + scope + ">");
        int targetNumber = new Random().nextInt(scope) + 1;

        try (Scanner scanner = new Scanner(System.in)) {
            while (currentAttempt < attempts) {
                System.out.println("Twoje próby: " + printProgressBar());
                System.out.println(UsefulConstants.GIVE_ME + " liczbę :");

                String input = scanner.next();

                try {
                    int guess = Integer.parseInt(input);

                    if (guess == targetNumber) {
                        System.out.println(UsefulConstants.YES + "!");
                        System.out.println(UsefulConstants.CONGRATULATIONS);
                        return;
                    } else if (guess < targetNumber) {
                        System.out.println("To " + UsefulConstants.TO_LESS);
                    } else {
                        System.out.println("To " + UsefulConstants.TO_MUCH);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Hmm, '" + input + "' to " + UsefulConstants.NOT_A_NUMBER);
                }

                currentAttempt++;

            }

            System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + attempts + ") do odgadnięcia liczby " + targetNumber);
        }
    }


}
