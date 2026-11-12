package com.workshop;

import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubscriberModelTest {

    @Test
    void planContainsExactlyFreeBasicAndPro() {
        assertArrayEquals(new Plan[] {Plan.FREE, Plan.BASIC, Plan.PRO}, Plan.values());
    }

    @Test
    void constructorStoresAllFiveSubscriberFields() {
        Subscriber subscriber = new Subscriber(2, "blair@example.test", Plan.BASIC, true, 1);

        assertEquals(2, subscriber.getId());
        assertEquals("blair@example.test", subscriber.getEmail());
        assertEquals(Plan.BASIC, subscriber.getPlan());
        assertTrue(subscriber.isActive());
        assertEquals(1, subscriber.getMonthsRemaining());
    }

    @Test
    void settersUpdateSubscriptionWithoutChangingId() {
        Subscriber subscriber = new Subscriber(2, "blair@example.test", Plan.BASIC, true, 1);

        subscriber.setEmail("updated@example.test");
        subscriber.setPlan(Plan.PRO);
        subscriber.setActive(false);
        subscriber.setMonthsRemaining(4);

        assertEquals(2, subscriber.getId());
        assertEquals("updated@example.test", subscriber.getEmail());
        assertEquals(Plan.PRO, subscriber.getPlan());
        assertFalse(subscriber.isActive());
        assertEquals(4, subscriber.getMonthsRemaining());
    }

    @Test
    void constructorRejectsZeroOrNegativeId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subscriber(0, "a@example.test", Plan.FREE, true, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new Subscriber(-1, "a@example.test", Plan.FREE, true, 0));
    }

    @Test
    void constructorRejectsNullOrBlankEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subscriber(1, null, Plan.FREE, true, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new Subscriber(1, "   ", Plan.FREE, true, 0));
    }

    @Test
    void setterRejectsNullOrBlankEmailWithoutChangingCurrentValue() {
        Subscriber subscriber = new Subscriber(1, "initial@example.test", Plan.FREE, true, 0);

        assertThrows(IllegalArgumentException.class, () -> subscriber.setEmail(null));
        assertThrows(IllegalArgumentException.class, () -> subscriber.setEmail("\t\n"));
        assertEquals("initial@example.test", subscriber.getEmail());
    }

    @Test
    void constructorAndSetterRejectNullPlan() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subscriber(1, "a@example.test", null, true, 0));

        Subscriber subscriber = new Subscriber(1, "a@example.test", Plan.BASIC, true, 0);
        assertThrows(IllegalArgumentException.class, () -> subscriber.setPlan(null));
        assertEquals(Plan.BASIC, subscriber.getPlan());
    }

    @Test
    void constructorAndSetterRejectNegativeMonths() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subscriber(1, "a@example.test", Plan.FREE, true, -1));

        Subscriber subscriber = new Subscriber(1, "a@example.test", Plan.FREE, true, 2);
        assertThrows(IllegalArgumentException.class, () -> subscriber.setMonthsRemaining(-3));
        assertEquals(2, subscriber.getMonthsRemaining());
    }

    @Test
    void zeroMonthsIsAllowedForExpiredSubscription() {
        Subscriber subscriber = new Subscriber(1, "a@example.test", Plan.FREE, false, 0);

        assertEquals(0, subscriber.getMonthsRemaining());
        assertFalse(subscriber.isActive());
        subscriber.setMonthsRemaining(0);
        assertEquals(0, subscriber.getMonthsRemaining());
    }

    @Test
    void subscribersCanBeModifiedIndependently() {
        Subscriber first = new Subscriber(1, "one@example.test", Plan.FREE, true, 0);
        Subscriber second = new Subscriber(2, "two@example.test", Plan.PRO, true, 1);

        first.setActive(false);
        second.setMonthsRemaining(4);

        assertFalse(first.isActive());
        assertEquals(0, first.getMonthsRemaining());
        assertTrue(second.isActive());
        assertEquals(4, second.getMonthsRemaining());
    }
    @Test
    void toStringDisplaysAllSubscriberFields() {
        Subscriber subscriber = new Subscriber(2, "blair@example.test", Plan.BASIC, true, 1);

        assertEquals("Subscriber{id=2, email='blair@example.test', plan=BASIC, active=true, monthsRemaining=1}",
                subscriber.toString());
    }

    @Test
    void toStringReflectsUpdatedSubscriberState() {
        Subscriber subscriber = new Subscriber(2, "blair@example.test", Plan.BASIC, true, 1);
        subscriber.setPlan(Plan.PRO);
        subscriber.setActive(false);
        subscriber.setMonthsRemaining(4);

        assertEquals("Subscriber{id=2, email='blair@example.test', plan=PRO, active=false, monthsRemaining=4}",
                subscriber.toString());
    }

}
