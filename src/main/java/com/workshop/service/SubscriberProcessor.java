package com.workshop.service;

import com.workshop.functional.SubscriberFilter;
import com.workshop.functional.SubscriberAction;
import com.workshop.model.Subscriber;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriberProcessor {

    /**
     * Filters subscribers based on the provided filter criteria.
     * Returns a new list of matching subscribers in their original order.
     */
    public List<Subscriber> findSubscribers(List<Subscriber> list, SubscriberFilter filter) {
        return list.stream()
                .filter(filter::matches)
                .collect(Collectors.toList());
    }

    /**
     * Applies the given action to matching subscribers based on the filter.
     * Returns a new list containing only the processed subscribers in input order.
     */
    public List<Subscriber> applyToMatching(List<Subscriber> list, SubscriberFilter filter, SubscriberAction action) {
        List<Subscriber> matchingSubscribers = findSubscribers(list, filter);
        matchingSubscribers.forEach(action::run);
        return matchingSubscribers;
    }
}
