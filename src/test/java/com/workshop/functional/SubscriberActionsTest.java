package com.workshop.functional;

import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import com.workshop.service.SubscriberProcessor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubscriberActionsTest {

    @Test
    void extendByMonthsAddsRequestedMonthsWhenRun() {
        Subscriber subscriber = subscriber(1, true, 1);

        SubscriberActions.extendByMonths(3).run(subscriber);

        assertEquals(4, subscriber.getMonthsRemaining());
    }

    @Test
    void extendByMonthsRejectsZeroOrNegativeDuration() {
        assertThrows(IllegalArgumentException.class, () -> SubscriberActions.extendByMonths(0));
        assertThrows(IllegalArgumentException.class, () -> SubscriberActions.extendByMonths(-1));
    }

    @Test
    void creatingExtensionActionDoesNotMutateSubscriber() {
        Subscriber subscriber = subscriber(1, true, 1);

        SubscriberAction action = SubscriberActions.extendByMonths(3);

        assertEquals(1, subscriber.getMonthsRemaining());
        action.run(subscriber);
        assertEquals(4, subscriber.getMonthsRemaining());
    }

    @Test
    void deactivateMarksSubscriberInactive() {
        Subscriber subscriber = subscriber(1, true, 5);

        SubscriberActions.deactivate().run(subscriber);

        assertFalse(subscriber.isActive());
        assertEquals(5, subscriber.getMonthsRemaining());
    }

    @Test
    void deactivatingAlreadyInactiveSubscriberIsSafe() {
        Subscriber subscriber = subscriber(1, false, 0);

        SubscriberActions.deactivate().run(subscriber);

        assertFalse(subscriber.isActive());
        assertEquals(0, subscriber.getMonthsRemaining());
    }

    @Test
    void activateMarksSubscriberActive() {
        Subscriber subscriber = subscriber(1, false, 5);

        SubscriberActions.activate().run(subscriber);

        assertTrue(subscriber.isActive());
        assertEquals(5, subscriber.getMonthsRemaining());
    }

    @Test
    void actionCanBeAssignedToFunctionalInterface() {
        SubscriberAction action = subscriber -> subscriber.setActive(true);
        Subscriber subscriber = subscriber(1, false, 1);

        action.run(subscriber);

        assertTrue(subscriber.isActive());
    }

    @Test
    void scenario4ExtendsOnlyActivePayingExpiringSubscribers() {
        List<Subscriber> subscribers = exampleSubscribers();
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberFilter active = SubscriberFilters.active();
        SubscriberFilter paying = SubscriberFilters.paying();
        SubscriberFilter expiring = SubscriberFilters.expiring();
        SubscriberFilter eligible = subscriber -> active.matches(subscriber)
                && paying.matches(subscriber)
                && expiring.matches(subscriber);

        List<Subscriber> processed = processor.applyToMatching(
                subscribers, eligible, SubscriberActions.extendByMonths(3));

        assertNotSame(subscribers, processed);
        assertEquals(List.of(2, 6), ids(processed));
        assertEquals(List.of(0, 4, 6, 0, 1, 3, 2),
                subscribers.stream().map(Subscriber::getMonthsRemaining).toList());
        assertEquals(List.of(true, true, true, false, false, true, true),
                subscribers.stream().map(Subscriber::isActive).toList());
    }

    @Test
    void scenario5DeactivatesOnlyExpiredFreeSubscribers() {
        List<Subscriber> subscribers = exampleSubscribers();
        SubscriberProcessor processor = new SubscriberProcessor();
        SubscriberFilter free = SubscriberFilters.byPlan(Plan.FREE);
        SubscriberFilter freeAndExpired = subscriber ->
                free.matches(subscriber)
                        && subscriber.getMonthsRemaining() == 0;

        List<Subscriber> processed = processor.applyToMatching(
                subscribers, freeAndExpired, SubscriberActions.deactivate());

        assertNotSame(subscribers, processed);
        assertEquals(List.of(1, 4), ids(processed));
        assertEquals(List.of(false, true, true, false, false, true, true),
                subscribers.stream().map(Subscriber::isActive).toList());
        assertEquals(List.of(0, 1, 6, 0, 1, 0, 2),
                subscribers.stream().map(Subscriber::getMonthsRemaining).toList());
    }

    private static Subscriber subscriber(int id, boolean active, int monthsRemaining) {
        return new Subscriber(id, "subscriber" + id + "@example.test", Plan.BASIC, active, monthsRemaining);
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
