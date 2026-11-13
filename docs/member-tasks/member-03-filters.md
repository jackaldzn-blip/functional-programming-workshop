# Member 3 — Jay — Functional filters + workshop scenarios 1 and 2

**Assigned member:** Jay — [@JayB111](https://github.com/JayB111)  
**Merged work:** filters in [PR #10](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/10); scenarios 1–2 in [PR #18](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/18)  
**Owned:** `src/main/java/com/workshop/functional/SubscriberFilter.java`, `SubscriberFilters.java`, `src/test/java/com/workshop/functional/SubscriberFiltersTest.java`.

## Goal

Implement the functional predicate interface and **all five** Part 1 selection rules. A filter returns `true` or `false` for a subscriber and **never changes** their state.

## Build your component step by step

1. Define `@FunctionalInterface SubscriberFilter` with **one** abstract method `boolean matches(Subscriber subscriber)`.
2. In `SubscriberFilters`, define these static factories, each returning a lambda-compatible `SubscriberFilter`:
   - `active()` → `isActive()` is true.
   - `expiring()` → `monthsRemaining` is **0 or 1** (even if inactive).
   - `activeAndExpiring()` → active AND months 0/1.
   - `byPlan(Plan plan)` → subscriber plan equals the provided enum value.
   - `paying()` → plan is BASIC or PRO (active flag irrelevant).
3. Keep rules independent of printing, DAO storage or actions. Follow the exact signatures in [contracts](../contracts.md).
4. Create unit tests in `SubscriberFiltersTest.java` covering all five rules and boundary values 0, 1 and 2 months. Verify none mutate the input subscriber.

## Your Part 2 scenario tests (you own these)

- **Scenario 1: Show active subscribers.** With [shared data](../example-scenarios.md), assert returned IDs are **1,2,3,6,7** in that order.
- **Scenario 2: Show expiring subscriptions.** Assert returned IDs are **1,2,4,5,6**; confirm inactive IDs 4 and 5 still match because expiring does not require active.
- Use the **now-merged** `SubscriberProcessor.findSubscribers` to test these scenarios. The independent predicate unit tests are already implemented and passing.
- Include named `@Test` methods with assertions. No console-only tests.

## Dependencies

You need Member 1's model; integration-level scenario tests also need Member 5's processor. Ask the lead when API contracts differ rather than modifying colleagues' classes.

## Definition of done

- [x] One-method functional interface; five correct filter factories.
- [x] Filter unit tests pass, including 0/1/2 and inactive boundary cases.
- [x] **Scenario 1 and 2** assertions pass after integration in `SubscriberProcessorTest.java` (PR #18).
- [x] Both coding PRs are merged; Java 25 `main` CI verified 53 project tests on 13 November 2026.
- [ ] Send Member 6 summary, scenario outcomes, JUnit evidence and PR URL.
