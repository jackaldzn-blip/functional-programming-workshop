package com.workshop;

import com.workshop.dao.SubscriberDAO;
import com.workshop.functional.SubscriberAction;
import com.workshop.functional.SubscriberActions;
import com.workshop.functional.SubscriberFilter;
import com.workshop.functional.SubscriberFilters;
import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import com.workshop.service.SubscriberProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int EXTENSION_MONTHS = 3;

    private final SubscriberDAO subscriberDAO;
    private final SubscriberProcessor processor;
    private final Scanner scanner;

    public Main() {
        subscriberDAO = new SubscriberDAO();
        processor = new SubscriberProcessor();
        scanner = new Scanner(System.in);

        // Load shared sample data into DAO
        createSampleSubscribers().forEach(subscriberDAO::save);
    }

    public static void main(String[] args) {
        Main application = new Main();
        application.run();
    }

    // 1. Create shared sample data (fresh objects on every call)
    public static List<Subscriber> createSampleSubscribers() {

        return new ArrayList<>(List.of(
                new Subscriber(1, "alice@example.com", Plan.FREE, true, 0),
                new Subscriber(2, "bob@example.com", Plan.BASIC, true, 1),
                new Subscriber(3, "charlie@example.com", Plan.PRO, true, 6),
                new Subscriber(4, "david@example.com", Plan.FREE, false, 0),
                new Subscriber(5, "emma@example.com", Plan.BASIC, false, 1),
                new Subscriber(6, "frank@example.com", Plan.PRO, true, 0),
                new Subscriber(7, "grace@example.com", Plan.FREE, true, 4)
        ));
    }

    // 2. Display menu repeatedly until the user exits (or input ends)
    public void run() {

        boolean running = true;

        while (running) {
            displayMenu();

            System.out.print("Enter your choice: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();

            Integer choice = parseChoice(input);

            if (choice == null) {
                System.out.println("Invalid input. Please enter a number.");
            } else {
                running = handleChoice(choice);
            }

            System.out.println();
        }
    }

    // Parse the menu input separately so errors inside actions are never
    // reported as "not a number".
    private Integer parseChoice(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    // Returns false when the program should exit
    private boolean handleChoice(int choice) {

        switch (choice) {
            case 1 -> showAllSubscribers();
            case 2 -> showActiveSubscribers();
            case 3 -> showExpiringSubscribers();
            case 4 -> showActiveAndExpiringSubscribers();
            case 5 -> showSubscribersByPlan();
            case 6 -> showPayingSubscribers();
            case 7 -> extendEligibleSubscriptions();
            case 8 -> deactivateExpiredFreeSubscribers();
            case 0 -> {
                System.out.println(
                        "Exiting Subscription Management System. Goodbye!");
                return false;
            }
            default -> System.out.println(
                    "Invalid choice. Please select between 0 and 8.");
        }

        return true;
    }

    // 3. Display menu
    private void displayMenu() {

        System.out.println("\n\n=== Subscription Management System ===");
        System.out.println("1. Show all subscribers");
        System.out.println("2. Show active subscribers");
        System.out.println("3. Show expiring subscriptions");
        System.out.println("4. Show active and expiring subscribers");
        System.out.println("5. Find subscribers by plan");
        System.out.println("6. Show paying subscribers");
        System.out.println("7. Extend active, paying and expiring subscriptions (+3 months)");
        System.out.println("8. Deactivate expired FREE subscribers");
        System.out.println("0. Exit");
    }

    // Option 1: Show all subscribers
    private void showAllSubscribers() {

        System.out.println("\n--- All Subscribers ---");

        displaySubscribers(subscriberDAO.findAll());
    }

    // Option 2: Show active subscribers
    private void showActiveSubscribers() {

        System.out.println("\n--- Active Subscribers ---");

        showFilteredSubscribers(SubscriberFilters.active());
    }

    // Option 3: Show expiring subscribers
    private void showExpiringSubscribers() {

        System.out.println("\n--- Expiring Subscribers ---");

        showFilteredSubscribers(SubscriberFilters.expiring());
    }

    // Option 4: Show active and expiring subscribers
    private void showActiveAndExpiringSubscribers() {

        System.out.println("\n--- Active and Expiring Subscribers ---");

        showFilteredSubscribers(SubscriberFilters.activeAndExpiring());
    }

    // Option 5: Find subscribers by plan
    private void showSubscribersByPlan() {

        System.out.println("\n--- Find Subscribers By Plan ---");
        System.out.print("Enter plan (FREE, BASIC, PRO): ");

        if (!scanner.hasNextLine()) {
            return;
        }

        String planInput = scanner.nextLine().trim().toUpperCase();

        Plan plan;
        try {
            plan = Plan.valueOf(planInput);
        } catch (IllegalArgumentException exception) {
            System.out.println(
                    "Invalid plan. Please enter FREE, BASIC or PRO.");
            return;
        }

        showFilteredSubscribers(SubscriberFilters.byPlan(plan));
    }

    // Option 6: Show paying subscribers
    private void showPayingSubscribers() {

        System.out.println("\n--- Paying Subscribers (BASIC and PRO) ---");

        showFilteredSubscribers(SubscriberFilters.paying());
    }

    // Option 7: Extend active, paying and expiring subscribers
    private void extendEligibleSubscriptions() {

        System.out.println("\n--- Extend Eligible Subscriptions ---");

        List<Subscriber> subscribers = subscriberDAO.findAll();

        SubscriberFilter eligibleFilter = subscriber ->
                SubscriberFilters.active().matches(subscriber)
                        && SubscriberFilters.paying().matches(subscriber)
                        && SubscriberFilters.expiring().matches(subscriber);

        SubscriberAction extendAction =
                SubscriberActions.extendByMonths(EXTENSION_MONTHS);

        List<Subscriber> updatedSubscribers =
                processor.applyToMatching(
                        subscribers,
                        eligibleFilter,
                        extendAction
                );

        if (updatedSubscribers.isEmpty()) {
            System.out.println("No subscribers are eligible for extension.");
        } else {
            System.out.println(
                    "The following subscriptions were extended by "
                            + EXTENSION_MONTHS + " months:");

            displaySubscribers(updatedSubscribers);
        }
    }

    // Option 8: Deactivate expired FREE subscribers
    // Per the brief: FREE plan with exactly 0 months remaining
    // (active status is NOT part of the selection).
    private void deactivateExpiredFreeSubscribers() {

        System.out.println("\n--- Deactivate Expired FREE Subscribers ---");

        List<Subscriber> subscribers = subscriberDAO.findAll();

        SubscriberFilter expiredFreeFilter = subscriber ->
                SubscriberFilters.byPlan(Plan.FREE).matches(subscriber)
                        && subscriber.getMonthsRemaining() == 0;

        SubscriberAction deactivateAction =
                SubscriberActions.deactivate();

        List<Subscriber> deactivatedSubscribers =
                processor.applyToMatching(
                        subscribers,
                        expiredFreeFilter,
                        deactivateAction
                );

        if (deactivatedSubscribers.isEmpty()) {
            System.out.println("No expired FREE subscribers found.");
        } else {
            System.out.println("Processed expired FREE subscribers:");

            displaySubscribers(deactivatedSubscribers);
        }
    }

    // Reuse SubscriberProcessor for filtering
    private void showFilteredSubscribers(SubscriberFilter filter) {

        List<Subscriber> subscribers = subscriberDAO.findAll();

        List<Subscriber> filteredSubscribers =
                processor.findSubscribers(subscribers, filter);

        displaySubscribers(filteredSubscribers);
    }

    // Display results
    private void displaySubscribers(List<Subscriber> subscribers) {

        if (subscribers.isEmpty()) {
            System.out.println("No subscribers found.");
            return;
        }

        subscribers.forEach(System.out::println);
    }
}