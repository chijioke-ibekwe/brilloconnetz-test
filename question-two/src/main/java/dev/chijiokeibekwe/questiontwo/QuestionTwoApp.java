package dev.chijiokeibekwe.questiontwo;

import dev.chijiokeibekwe.questiontwo.util.InputUtil;
import dev.chijiokeibekwe.questiontwo.util.PancakeHelper;

import java.util.concurrent.ExecutionException;

public class QuestionTwoApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        InputUtil.welcomeCustomers();
        String serviceMethod = InputUtil.getServiceMethod();
        int slots = InputUtil.getNumberOfServingSlots();

        PancakeHelper helper = new PancakeHelper(serviceMethod, slots);
        helper.servePancakes();
    }
}
