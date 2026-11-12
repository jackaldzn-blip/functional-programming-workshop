
package com.workshop.dao;

import com.workshop.model.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriberDAO {

    private final List<Subscriber> subscribers = new ArrayList<>();

    // Save a new subscriber or update an existing subscriber
    public void save(Subscriber subscriber) {

        Objects.requireNonNull(subscriber, "Subscriber cannot be null");

        for (int i = 0; i < subscribers.size(); i++) {

            if (subscribers.get(i).getId() == subscriber.getId()) {
                subscribers.set(i, subscriber);
                return;
            }
        }

        subscribers.add(subscriber);
    }

    // Return all subscribers in insertion order
    public List<Subscriber> findAll() {

        return new ArrayList<>(subscribers);
    }

    // Find a subscriber by ID
    public Optional<Subscriber> findById(int id) {

        for (Subscriber subscriber : subscribers) {

            if (subscriber.getId() == id) {
                return Optional.of(subscriber);
            }
        }

        return Optional.empty();
    }
}
