package org.example;

import java.util.List;

public class ConvertJavaToKotlinMain {
    public static void main(String[] args) {
        List<Account> accounts = List.of(new Account(), new Account());
        accounts.forEach(System.out::println);
        accounts.remove(0);
        accounts.forEach(System.out::println);
    }
}
