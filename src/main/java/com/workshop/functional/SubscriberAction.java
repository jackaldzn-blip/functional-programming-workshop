package com.workshop.functional;

import com.workshop.model.Subscriber;

/** An operation that can be performed on a subscriber. */
@FunctionalInterface
public interface SubscriberAction {

    void run(Subscriber subscriber);
}
