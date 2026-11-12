package com.workshop.functional;

import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    private static Subscriber subscriber(int id, boolean active, int monthsRemaining) {
        return new Subscriber(id, "subscriber" + id + "@example.test", Plan.BASIC, active, monthsRemaining);
    }
}
