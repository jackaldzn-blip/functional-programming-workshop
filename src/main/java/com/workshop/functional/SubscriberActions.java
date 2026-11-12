package com.workshop.functional;

import com.workshop.model.Subscriber;

import java.util.Objects;

/** Factory methods for common subscriber state changes. */
public class SubscriberActions {

    private SubscriberActions() {
    }

    /**
     * Creates an action that extends a subscriber's subscription when run.
     *
     * @param months number of months to add; must be positive
     * @return an action that adds the requested number of months
     */
    public static SubscriberAction extendByMonths(int months) {
        if (months <= 0) {
            throw new IllegalArgumentException("Months to extend must be positive");
        }
        return subscriber -> {
            Objects.requireNonNull(subscriber, "subscriber");
            subscriber.setMonthsRemaining(subscriber.getMonthsRemaining() + months);
        };
    }

    /** Creates an action that marks a subscriber inactive when run. */
    public static SubscriberAction deactivate() {
        return subscriber -> {
            Objects.requireNonNull(subscriber, "subscriber");
            subscriber.setActive(false);
        };
    }

    /** Creates an action that marks a subscriber active when run. */
    public static SubscriberAction activate() {
        return subscriber -> {
            Objects.requireNonNull(subscriber, "subscriber");
            subscriber.setActive(true);
        };
    }
}
