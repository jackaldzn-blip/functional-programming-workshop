package com.workshop.service;

import com.workshop.functional.SubscriberFilter;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Scenario3Test {

    @Test
    public void testActiveAndExpiringSubscribers() {
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberFilter filter = SubscriberFilters.activeAndExpiring();

        List<Subscriber> input = Arrays.asList(
                new Subscriber(1, "Active", "Expire"),
                new Subscriber(2, "Active", "Expire"),
                new Subscriber(3, "Inactive", "Expire"),
                new Subscriber(4, "Inactive", "Expire"),
                new Subscriber(5, "Inactive", "Expire"),
                new Subscriber(6, "Active", "Expire")
        );

        List<Subscriber> result = processor.findSubscribers(input, filter);

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(6, result.get(2).getId());
    }
}