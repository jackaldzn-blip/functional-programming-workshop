
package com.workshop.functional;

import com.workshop.model.Subscriber;
@FunctionalInterface
public interface SubscriberFilter {
    boolean matches(Subscriber subscriber);
}
