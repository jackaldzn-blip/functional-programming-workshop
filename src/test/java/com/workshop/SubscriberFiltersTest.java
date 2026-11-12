
package com.workshop;

import com.workshop.functional.SubscriberFilters;
import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SubscriberFiltersTest {
    @Test
    void active_shouldMatchActiveSubscribers() {
        Subscriber subscriber = new Subscriber(
                1, "amina@email.com", Plan.BASIC, true, 5
        );
        assertTrue(SubscriberFilters.active().matches(subscriber));
    }
    @Test
    void active_shouldNotMatchInactiveSubscribers() {
        Subscriber subscriber = new Subscriber(
                2, "erik@email.com", Plan.FREE, false, 5
        );
        assertFalse(SubscriberFilters.active().matches(subscriber));
    }
    @Test
    void expiring_shouldMatchSubscribersWithZeroMonthsRemaining() {
        Subscriber subscriber = new Subscriber(
                3, "sara@email.com", Plan.PRO, true, 0
        );
        assertTrue(SubscriberFilters.expiring().matches(subscriber));
    }
    @Test
    void expiring_shouldMatchSubscribersWithOneMonthRemaining() {
        Subscriber subscriber = new Subscriber(
                4, "omar@email.com", Plan.BASIC, true, 1
        );
        assertTrue(SubscriberFilters.expiring().matches(subscriber));
    }
    @Test
    void expiring_shouldNotMatchSubscribersWithMoreThanOneMonthRemaining() {
        Subscriber subscriber = new Subscriber(
                5, "lisa@email.com", Plan.PRO, true, 3
        );
        assertFalse(SubscriberFilters.expiring().matches(subscriber));
    }
    @Test
    void byPlan_shouldMatchSubscribersWithSelectedPlan() {
        Subscriber subscriber = new Subscriber(
                6, "noah@email.com", Plan.PRO, true, 5
        );
        assertTrue(SubscriberFilters.byPlan(Plan.PRO).matches(subscriber));
    }
    @Test
    void paying_shouldMatchBasicAndProSubscribers() {
        Subscriber basicSubscriber = new Subscriber(
                7, "basic@email.com", Plan.BASIC, true, 5
        );
        Subscriber proSubscriber = new Subscriber(
                8, "pro@email.com", Plan.PRO, true, 5
        );
        assertTrue(SubscriberFilters.paying().matches(basicSubscriber));
        assertTrue(SubscriberFilters.paying().matches(proSubscriber));
    }
    @Test
    void paying_shouldNotMatchFreeSubscribers() {
        Subscriber subscriber = new Subscriber(
                9, "free@email.com", Plan.FREE, true, 5
        );
        assertFalse(SubscriberFilters.paying().matches(subscriber));
    }
    @Test
    void activeAndExpiring_shouldMatchActiveSubscriberWithZeroMonthsRemaining() {
        Subscriber subscriber = new Subscriber(
                10, "test1@email.com", Plan.BASIC, true, 0
        );

        assertTrue(SubscriberFilters.activeAndExpiring().matches(subscriber));
    }
    @Test
    void activeAndExpiring_shouldMatchActiveSubscriberWithOneMonthRemaining() {
        Subscriber subscriber = new Subscriber(
                11, "test2@email.com", Plan.PRO, true, 1
        );

        assertTrue(SubscriberFilters.activeAndExpiring().matches(subscriber));
    }
    @Test
    void activeAndExpiring_shouldNotMatchInactiveSubscriber() {
        Subscriber subscriber = new Subscriber(
                12, "test3@email.com", Plan.FREE, false, 0
        );
        assertFalse(SubscriberFilters.activeAndExpiring().matches(subscriber));
    }
    @Test
    void expiring_shouldNotMatchSubscribersWithTwoMonthsRemaining() {
        Subscriber subscriber = new Subscriber(
                13, "boundary@email.com", Plan.BASIC, true, 2
        );
        assertFalse(SubscriberFilters.expiring().matches(subscriber));
    }
    @Test
    void filters_shouldNotModifySubscriber() {
        Subscriber subscriber = new Subscriber(
                14, "unchanged@email.com", Plan.BASIC, true, 1
        );
        SubscriberFilters.active().matches(subscriber);
        SubscriberFilters.expiring().matches(subscriber);
        SubscriberFilters.activeAndExpiring().matches(subscriber);
        SubscriberFilters.byPlan(Plan.BASIC).matches(subscriber);
        SubscriberFilters.paying().matches(subscriber);
        assertEquals(Plan.BASIC, subscriber.getPlan());
        assertTrue(subscriber.isActive());
        assertEquals(1, subscriber.getMonthsRemaining());
    }
    @Test
    void expiring_shouldMatchInactiveSubscribers() {
        Subscriber subscriber = new Subscriber(
                15, "inactive@email.com", Plan.FREE, false, 0
        );

        assertTrue(SubscriberFilters.expiring().matches(subscriber));
    }
}
