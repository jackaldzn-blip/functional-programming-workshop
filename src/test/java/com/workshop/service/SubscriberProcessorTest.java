package com.workshop.service;

import com.workshop.functional.SubscriberAction;
import com.workshop.functional.SubscriberFilter;
import com.workshop.functional.SubscriberFilters;
import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubscriberProcessorTest {

    private final SubscriberProcessor processor = new SubscriberProcessor();

    @Test
    void findSubscribersReturnsMatchingSubscribersInInputOrder() {
        List<Subscriber> subscribers = exampleSubscribers();

        List<Subscriber> matching = processor.findSubscribers(
                subscribers, subscriber -> subscriber.getId() % 2 == 0);

        assertNotSame(subscribers, matching);
        assertEquals(List.of(2, 4, 6), ids(matching));
    }

    @Test
    void findSubscribersReturnsEmptyListWhenThereAreNoMatches() {
        List<Subscriber> subscribers = exampleSubscribers();

        List<Subscriber> matching = processor.findSubscribers(subscribers, subscriber -> false);

        assertNotSame(subscribers, matching);
        assertTrue(matching.isEmpty());
    }

    @Test
    void applyToMatchingRunsActionOnceForEachMatchAndLeavesNonmatchesAlone() {
        List<Subscriber> subscribers = exampleSubscribers();
        AtomicInteger filterCalls = new AtomicInteger();
        AtomicInteger actionCalls = new AtomicInteger();
        SubscriberFilter evenIds = subscriber -> {
            filterCalls.incrementAndGet();
            return subscriber.getId() % 2 == 0;
        };
        SubscriberAction deactivate = subscriber -> {
            actionCalls.incrementAndGet();
            subscriber.setActive(false);
        };

        List<Subscriber> processed = processor.applyToMatching(subscribers, evenIds, deactivate);

        assertNotSame(subscribers, processed);
        assertEquals(List.of(2, 4, 6), ids(processed));
        assertEquals(subscribers.size(), filterCalls.get());
        assertEquals(3, actionCalls.get());
        assertEquals(List.of(true, false, true, false, true, false, true),
                subscribers.stream().map(Subscriber::isActive).toList());
    }

    @Test
    void scenario3FindsActiveAndExpiringSubscribers() {
        List<Subscriber> subscribers = exampleSubscribers();

        List<Subscriber> matching = processor.findSubscribers(
                subscribers, SubscriberFilters.activeAndExpiring());

        assertEquals(List.of(1, 2, 6), ids(matching));
    }

    private static List<Subscriber> exampleSubscribers() {
        return new ArrayList<>(List.of(
                new Subscriber(1, "alex@example.test", Plan.FREE, true, 0),
                new Subscriber(2, "blair@example.test", Plan.BASIC, true, 1),
                new Subscriber(3, "casey@example.test", Plan.PRO, true, 6),
                new Subscriber(4, "devon@example.test", Plan.FREE, false, 0),
                new Subscriber(5, "ellis@example.test", Plan.BASIC, false, 1),
                new Subscriber(6, "frankie@example.test", Plan.PRO, true, 0),
                new Subscriber(7, "gray@example.test", Plan.FREE, true, 2)));
    }

    private static List<Integer> ids(List<Subscriber> subscribers) {
        return subscribers.stream().map(Subscriber::getId).toList();
    }
}
