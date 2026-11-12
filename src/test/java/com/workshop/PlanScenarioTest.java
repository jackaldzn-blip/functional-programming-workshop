package com.workshop;

import com.workshop.functional.SubscriberFilters;
import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import com.workshop.service.SubscriberProcessor;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanScenarioTest {

    private final SubscriberProcessor processor =
            new SubscriberProcessor();

    // Scenario 6: Filter subscribers by plan
    // Fresh sample data is created inside every test.

    private List<Integer> idsForPlan(Plan plan) {

        List<Subscriber> subscribers = Main.createSampleSubscribers();

        return processor.findSubscribers(
                        subscribers,
                        SubscriberFilters.byPlan(plan))
                .stream()
                .map(Subscriber::getId)
                .toList();
    }

    @Test
    void shouldFindFreeSubscribers() {
        assertEquals(List.of(1, 4, 7), idsForPlan(Plan.FREE));
    }

    @Test
    void shouldFindBasicSubscribers() {
        assertEquals(List.of(2, 5), idsForPlan(Plan.BASIC));
    }

    @Test
    void shouldFindProSubscribers() {
        assertEquals(List.of(3, 6), idsForPlan(Plan.PRO));
    }
}