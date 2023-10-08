package dev.chijiokeibekwe.questiontwo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PancakeHelper {

    private final boolean concurrentServing;
    private final int numberOfTimeSlots;

    private static LocalDateTime startTime = LocalDateTime.now();

    public PancakeHelper(String serviceMethod, int numberOfTimeSlots) {
        this.concurrentServing = serviceMethod.equalsIgnoreCase(InputUtil.C);
        this.numberOfTimeSlots = numberOfTimeSlots;
    }

    public void servePancakes() throws ExecutionException, InterruptedException {
        int chefMax = 12;
        int customerMax = 5;

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTimeSlots; i++) {
            if(!this.concurrentServing) {
                determineOutcome(chefMax, customerMax, i + 1, startTime);
            } else {
                int _i = i;
                futures.add(CompletableFuture.runAsync(() -> determineOutcome(chefMax, customerMax, _i + 1, startTime)));
            }
        }

        if(this.concurrentServing) {
            futures.forEach(CompletableFuture::join);
        }
    }

    private void determineOutcome(int chefMax, int customerMax, int slotNumber, LocalDateTime start) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime end = start.plusSeconds(30);
        Random random = new Random();

        //Pancakes ordered ranging from 0 (min) to 5 (max)
        int orderedByCustomerOne = random.nextInt(customerMax + 1);
        int orderedByCustomerTwo = random.nextInt(customerMax + 1);
        int orderedByCustomerThree = random.nextInt(customerMax + 1);
        int totalPancakesOrdered = orderedByCustomerOne + orderedByCustomerTwo + orderedByCustomerThree;

        //Pancakes made ranging from 0 (min) to 12 (max)
        int pancakesMade = random.nextInt(chefMax + 1);

        //Pancakes eventually eaten
        int eatenByCustomerOne;
        int eatenByCustomerTwo;
        int eatenByCustomerThree;

        eatenByCustomerOne = Math.min(orderedByCustomerOne, pancakesMade);
        eatenByCustomerTwo = Math.min(orderedByCustomerTwo, pancakesMade - eatenByCustomerOne);
        eatenByCustomerThree = Math.min(orderedByCustomerThree, pancakesMade - (eatenByCustomerOne + eatenByCustomerTwo));
        int totalPancakesEaten = eatenByCustomerOne + eatenByCustomerTwo + eatenByCustomerThree;

        System.out.println("Slot " + slotNumber + " >>> Customer 1 ordered " + orderedByCustomerOne + " pancake(s)");
        System.out.println("Slot " + slotNumber + " >>> Customer 2 ordered " + orderedByCustomerTwo + " pancake(s)");
        System.out.println("Slot " + slotNumber + " >>> Customer 3 ordered " + orderedByCustomerThree + " pancake(s)");
        System.out.println("Slot " + slotNumber + " >>> Pancake making start time: " + formatter.format(start));
        System.out.println("Slot " + slotNumber + " >>> Pancake making end time: " + formatter.format(end));
        System.out.println("Slot " + slotNumber + " >>> Number of pancakes made: " + pancakesMade);
        System.out.println("Slot " + slotNumber + " >>> Customer 1 ate " + eatenByCustomerOne + " pancake(s)");
        System.out.println("Slot " + slotNumber + " >>> Customer 2 ate " + eatenByCustomerTwo + " pancake(s)");
        System.out.println("Slot " + slotNumber + " >>> Customer 3 ate " + eatenByCustomerThree + " pancake(s)");

        if(eatenByCustomerOne == orderedByCustomerOne) {
            System.out.println("Slot " + slotNumber + " >>> Shopkeeper met the needs of customer 1");
        } else {
            System.out.println("Slot " + slotNumber + " >>> Shopkeeper didn't meet the needs of customer 1");
        }

        if(eatenByCustomerTwo == orderedByCustomerTwo) {
            System.out.println("Slot " + slotNumber + " >>> Shopkeeper met the needs of customer 2");
        } else {
            System.out.println("Slot " + slotNumber + " >>> Shopkeeper didn't meet the needs of customer 2");
        }

        if(eatenByCustomerThree == orderedByCustomerThree) {
            System.out.println("Slot " + slotNumber + " >>> Shopkeeper met the needs of customer 3");
        } else {
            System.out.println("Slot " + slotNumber + " >>> Shopkeeper didn't meet the needs of customer 3");
        }

        if(totalPancakesEaten < pancakesMade) {
            System.out.println("Slot " + slotNumber + " >>> Total number of pancakes wasted: " +
                    (pancakesMade - totalPancakesEaten));
        } else {
            System.out.println("Slot " + slotNumber + " >>> No pancakes were wasted!");
        }

        if(totalPancakesOrdered > pancakesMade) {
            System.out.println("Slot " + slotNumber + " >>> Total number of unmet pancake orders: " +
                    (totalPancakesOrdered - pancakesMade) + "\n");
        } else {
            System.out.println("Slot " + slotNumber + " >>> No pancake orders were unmet!\n");
        }

        startTime = end;
    }
}
