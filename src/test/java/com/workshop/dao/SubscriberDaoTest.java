
package com.workshop.dao;

import com.workshop.model.Plan;
import com.workshop.model.Subscriber;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberDaoTest {

    @Test
    void findAllShouldReturnEmptyListWhenNoSubscribersExist() {
        SubscriberDAO dao = new SubscriberDAO();

        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void saveShouldAddNewSubscriber() {
        SubscriberDAO dao = new SubscriberDAO();

        Subscriber subscriber = new Subscriber(
                1, "anna@example.com", Plan.FREE, true, 2
        );

        dao.save(subscriber);

        assertEquals(1, dao.findAll().size());
        assertEquals(subscriber, dao.findAll().get(0));
    }

    @Test
    void findByIdShouldReturnSubscriberWhenFound() {
        SubscriberDAO dao = new SubscriberDAO();

        Subscriber subscriber = new Subscriber(
                1, "anna@example.com", Plan.BASIC, true, 3
        );

        dao.save(subscriber);

        Optional<Subscriber> result = dao.findById(1);

        assertTrue(result.isPresent());
        assertEquals("anna@example.com", result.get().getEmail());
    }

    @Test
    void findByIdShouldReturnEmptyWhenIdDoesNotExist() {
        SubscriberDAO dao = new SubscriberDAO();

        assertEquals(Optional.empty(), dao.findById(99));
    }

    @Test
    void saveShouldUpdateExistingSubscriberWithoutDuplicates() {
        SubscriberDAO dao = new SubscriberDAO();

        Subscriber first = new Subscriber(
                1, "anna@example.com", Plan.FREE, true, 2
        );

        Subscriber second = new Subscriber(
                2, "ben@example.com", Plan.BASIC, true, 1
        );

        Subscriber updatedSecond = new Subscriber(
                2, "ben.new@example.com", Plan.PRO, true, 5
        );

        dao.save(first);
        dao.save(second);
        dao.save(updatedSecond);

        List<Subscriber> result = dao.findAll();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals("ben.new@example.com", result.get(1).getEmail());
        assertEquals(Plan.PRO, result.get(1).getPlan());
    }

    @Test
    void findAllShouldPreserveInsertionOrder() {
        SubscriberDAO dao = new SubscriberDAO();

        dao.save(new Subscriber(3, "c@example.com", Plan.PRO, true, 3));
        dao.save(new Subscriber(1, "a@example.com", Plan.FREE, true, 2));
        dao.save(new Subscriber(2, "b@example.com", Plan.BASIC, true, 1));

        List<Subscriber> result = dao.findAll();

        assertEquals(
                List.of(3, 1, 2),
                result.stream().map(Subscriber::getId).toList()
        );
    }

    @Test
    void findAllShouldReturnIndependentList() {
        SubscriberDAO dao = new SubscriberDAO();

        dao.save(new Subscriber(1, "anna@example.com", Plan.FREE, true, 2));
        dao.save(new Subscriber(2, "ben@example.com", Plan.PRO, true, 3));

        List<Subscriber> result = dao.findAll();

        result.clear();

        assertEquals(2, dao.findAll().size());
    }

    @Test
    void saveShouldRejectNullSubscriber() {
        SubscriberDAO dao = new SubscriberDAO();

        assertThrows(
                NullPointerException.class,
                () -> dao.save(null)
        );

        assertTrue(dao.findAll().isEmpty());
    }
}
