package com.workshop.model;

/** Represents a subscriber and their current subscription state. */
public class Subscriber {
    private final int id;
    private String email;
    private Plan plan;
    private boolean active;
    private int monthsRemaining;

    /**
     * Creates a subscriber with a stable positive ID, a nonblank email,
     * a non-null plan, and a nonnegative number of remaining months.
     *
     * @throws IllegalArgumentException if any required value is invalid
     */
    public Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining) {
        if (id <= 0) {
            throw new IllegalArgumentException("Subscriber ID must be positive");
        }
        this.id = id;
        setEmail(email);
        setPlan(plan);
        setActive(active);
        setMonthsRemaining(monthsRemaining);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Plan getPlan() {
        return plan;
    }

    public boolean isActive() {
        return active;
    }

    public int getMonthsRemaining() {
        return monthsRemaining;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or blank");
        }
        this.email = email;
    }

    public void setPlan(Plan plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Subscription plan must not be null");
        }
        this.plan = plan;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMonthsRemaining(int monthsRemaining) {
        if (monthsRemaining < 0) {
            throw new IllegalArgumentException("Months remaining must not be negative");
        }
        this.monthsRemaining = monthsRemaining;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", plan=" + plan +
                ", active=" + active +
                ", monthsRemaining=" + monthsRemaining +
                '}';
    }
}
