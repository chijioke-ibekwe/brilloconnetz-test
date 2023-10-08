package dev.chijiokeibekwe.questiontwo.util;

import java.util.*;

public final class InputUtil {

    private InputUtil(){}

    private static final Scanner scanner = new Scanner(System.in);
    public static final String S = "S";
    public static final String C = "C";

    public static void welcomeCustomers() {
        System.out.println("=== Welcome to the pancake shop ===\n");
    }

    public static String getServiceMethod() {

        System.out.print("Would you like to serve the customers sequentially (S) or concurrently (C). Enter 'S' or 'C' to make a choice: ");
        boolean validChoice;
        String choice = scanner.next();

        do {
            validChoice = true;
            if (!choice.equalsIgnoreCase(C) && !choice.equalsIgnoreCase(S)) {
                validChoice = false;
                System.out.print("Invalid choice. Please enter 'S' or 'C' to proceed: ");
                choice = scanner.next();
            }
        } while (!validChoice);

        return choice;
    }

    public static int getNumberOfServingSlots() {
        System.out.print("How many 30 secs slots do you want to process: ");
        int slotNumber = 0;
        boolean validChoice;

        do {
            validChoice = true;
            try {
                slotNumber = scanner.nextInt();
                if(slotNumber > 5) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                validChoice = false;
                System.out.print("Invalid choice. Please enter a small integer between 1 and 5: ");
            }
        } while (!validChoice);

        return slotNumber;
    }

    public static void close() {
        scanner.close();
    }
}