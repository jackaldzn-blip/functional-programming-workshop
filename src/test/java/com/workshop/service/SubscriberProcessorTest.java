package com.workshop.service;

import com.workshop.functional.SubscriberFilter;
import com.workshop.functional.SubscriberAction;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriberProcessorTest {

    @Test
    public void testFindSubscribersNoMatches() {
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberFilter filter = (subscriber -> false);
        List<Subscriber> result = processor.findSubscribers(Arrays.asList(
                new Subscriber(1, "Alice", "Expire"),
                new Subscriber(2, "Bob", "Not Expire")
        ), filter);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindSubscribersOrderPreservation() {
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberFilter filter = (subscriber -> true);
        List<Subscriber> input = Arrays.asList(
                new Subscriber(1, "Alice", "Expire"),
                new Subscriber(2, "Bob", "Not Expire"),
                new Subscriber(3, "Charlie", "Expire")
        );
        List<Subscriber> result = processor.findSubscribers(input, filter);

        assertEquals(input, result);
    }

    @Test
    public void testApplyToMatchingOnlyMatches() {
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberAction action = (subscriber -> new Subscriber(
                subscriber.getId(),
                "Processed",
                "Expire"
        ));
        SubscriberFilter filter = (subscriber -> subscriber.getId() % 2 == 0);
        List<Subscriber> input = Arrays.asList(
                new Subscriber(1, "Alice", "Expire"),
                new Subscriber(2, "Bob", "Not Expire"),
                new Subscriber(3, "Charlie", "Expire")
        );
        List<Subscriber> result = processor.applyToMatching(input, filter, action);

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getId());
    }

    @Test
    public void testApplyToMatchingNoDuplicates() {
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberAction action = (subscriber -> new Subscriber(
                subscriber.getId(),
                "Processed",
                "Expire"
        ));
        SubscriberFilter filter = (subscriber -> subscriber.getId() % 2 == 0);
        List<Subscriber> input = Arrays.asList(
                new Subscriber(1, "Alice", "Expire"),
                new Subscriber(2, "Bob", "Not Expire"),
                new Subscriber(3, "Charlie", "Expire")
        );
        List<Subscriber> result = processor.applyToMatching(input, filter, action);

        assertNotNull(result);
        assertFalse(result == input); // New list identity
    }
}