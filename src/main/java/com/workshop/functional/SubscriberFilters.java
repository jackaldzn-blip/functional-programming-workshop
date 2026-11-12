
package com.workshop.functional;

import com.workshop.model.Plan;
import com.workshop.model.Subscriber;

public class SubscriberFilters {
    public static SubscriberFilter active() {
        return Subscriber::isActive;
    }
    public static SubscriberFilter expiring() {
        return subscriber -> subscriber.getMonthsRemaining() == 0
                || subscriber.getMonthsRemaining() == 1;
    }
    public static SubscriberFilter byPlan(Plan plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Plan cannot be null");
        }
        return subscriber -> subscriber.getPlan() == plan;
    }
    public static SubscriberFilter paying() {
        return subscriber -> subscriber.getPlan() == Plan.BASIC
                || subscriber.getPlan() == Plan.PRO;
    }
    public static SubscriberFilter activeAndExpiring() {
        return subscriber -> subscriber.isActive()
                && (subscriber.getMonthsRemaining() == 0
                || subscriber.getMonthsRemaining() == 1);
    }
}
