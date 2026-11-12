package com.workshop;

import com.workshop.functional.SubscriberFilters;
import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import com.workshop.service.SubscriberProcessor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanScenarioTest {

    @Test
    void scenario6FiltersSubscribersByEachPlan() {
        List<Subscriber> subscribers = exampleSubscribers();
        SubscriberProcessor processor = new SubscriberProcessor();

        assertEquals(List.of(1, 4, 7), ids(processor.findSubscribers(
                subscribers, SubscriberFilters.byPlan(Plan.FREE))));
        assertEquals(List.of(2, 5), ids(processor.findSubscribers(
                subscribers, SubscriberFilters.byPlan(Plan.BASIC))));
        assertEquals(List.of(3, 6), ids(processor.findSubscribers(
                subscribers, SubscriberFilters.byPlan(Plan.PRO))));
    }

    private static List<Subscriber> exampleSubscribers() {
        return List.of(
                new Subscriber(1, "alex@example.test", Plan.FREE, true, 0),
                new Subscriber(2, "blair@example.test", Plan.BASIC, true, 1),
                new Subscriber(3, "casey@example.test", Plan.PRO, true, 6),
                new Subscriber(4, "devon@example.test", Plan.FREE, false, 0),
                new Subscriber(5, "ellis@example.test", Plan.BASIC, false, 1),
                new Subscriber(6, "frankie@example.test", Plan.PRO, true, 0),
                new Subscriber(7, "gray@example.test", Plan.FREE, true, 2));
    }

    private static List<Integer> ids(List<Subscriber> subscribers) {
        return subscribers.stream().map(Subscriber::getId).toList();
    }
}
