package com.example.cal;

public class Cal {
    private static String firstNumber = "";
    private static String secondNumber = "";
    private static String lastOperation = "";
    private static boolean lastInputWasOperation = false;

    public static String getFirstNum(){
        return firstNumber;
    }
    public static String getSecondNum(){
        return secondNumber;
    }
    public static String getLastOperation() {
        return lastOperation;
    }
    public static void setFirstNum(String num) {
        firstNumber = num;
    }
    public static void setSecondNum(String num) {
        secondNumber = num;
    }
    public static void setLastOperation(String operation) {
        lastOperation = operation;
    }
    public static void setLastInputWasOperation(boolean wasOperation) {
        lastInputWasOperation = wasOperation;
    }

    public static boolean canUseOperation() {
        return (!lastInputWasOperation || lastOperation.isEmpty()) && !firstNumber.isEmpty();
    }
}
