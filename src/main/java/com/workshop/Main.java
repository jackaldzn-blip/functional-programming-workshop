package com.workshop;

import com.workshop.dao.SubscriberDAO;
import com.workshop.functional.SubscriberFilters;
import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import com.workshop.service.SubscriberProcessor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // 1. Create shared sample data
    public static List<Subscriber> createSampleSubscribers() {

        return new ArrayList<>(List.of(
                new Subscriber(1, "alice@example.com", Plan.FREE, true, 5),
                new Subscriber(2, "bob@example.com", Plan.BASIC, true, 1),
                new Subscriber(3, "charlie@example.com", Plan.PRO, true, 0),
                new Subscriber(4, "david@example.com", Plan.BASIC, false, 3),
                new Subscriber(5, "emma@example.com", Plan.PRO, true, 8),
                new Subscriber(6, "frank@example.com", Plan.FREE, false, 0),
                new Subscriber(7, "grace@example.com", Plan.BASIC, true, 0),
                new Subscriber(8, "henry@example.com", Plan.PRO, false, 1),
                new Subscriber(9, "ivy@example.com", Plan.FREE, true, 1),
                new Subscriber(10, "jack@example.com", Plan.BASIC, false, 0),
                new Subscriber(11, "kate@example.com", Plan.PRO, true, 2),
                new Subscriber(12, "liam@example.com", Plan.FREE, false, 6)
        ));
    }



    // 4. Application entry point
    public static void main(String[] args) {

        List<Subscriber> subscribers = createSampleSubscribers();

        System.out.println("\n === Sample Subscriber Data ===");

        subscribers.forEach(System.out::println);

        System.out.println("\n Total subscribers: " + subscribers.size());
    }
}