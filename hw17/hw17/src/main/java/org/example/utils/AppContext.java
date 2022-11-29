package org.example.utils;

import jakarta.persistence.EntityManager;
import lombok.Getter;

import java.util.Random;
import java.util.Scanner;

public class AppContext {
    @Getter
    private final static Scanner SCANNER = new Scanner(System.in);
    @Getter
    private final static Random RANDOM = new Random();
    private final static  EntityManager ENTITY_MANAGER = HibernateUtils.getEntityManagerFactory().createEntityManager();

    public static boolean confirm() {
        System.out.println("Do you confirm?");
        String input;
        while (true) {
            System.out.print("(y:yes n:no): ");
            input = SCANNER.next();
            switch (input.toLowerCase()) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("invalid input.");
            }
        }
    }
}