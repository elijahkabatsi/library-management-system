package com.company.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiPatternDemo {
    private static final Logger log = LoggerFactory.getLogger(AntiPatternDemo.class);

    // Anti-Pattern 1: Silent Exception Swallowing in finally
    public static String returnInFinally() {
        try {
            throw new RuntimeException("Critical Business Failure!");
        } finally {
            // SILENTLY SWALLOWS the exception above!
            return "Everything is fine!";
        }
    }

    // Anti-Pattern 2: Log and Continue leaving state half-updated
    public static class Account {
        private String name;
        private double balance;

        public void updateAccount(String newName, double amountToAdd) {
            this.name = newName; // Step 1 succeeded

            try {
                if (amountToAdd < 0) {
                    throw new IllegalArgumentException("Negative addition not allowed");
                }
                this.balance += amountToAdd;
            } catch (Exception e) {
                // Catches, logs, and CONTINUES - leaving object half-updated
                log.error("Failed to update balance for {}", newName, e);
            }
        }

        public String getName() { return name; }
        public double getBalance() { return balance; }
    }
}