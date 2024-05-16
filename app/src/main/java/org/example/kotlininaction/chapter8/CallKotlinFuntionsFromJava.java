package org.example.kotlininaction.chapter8;

import kotlin.Unit;

public class CallKotlinFuntionsFromJava {

    public static void main(String[] args) {
        HigerOrderFunctionsThatAcceptOtherFunctionsTutorialKt.printResult(number -> {
            System.out.println("printResult outer behavior. call printResult function from Java: " + number);
            return Unit.INSTANCE; // !!!! In Java, we should return Unit directly
        });

    }
}
