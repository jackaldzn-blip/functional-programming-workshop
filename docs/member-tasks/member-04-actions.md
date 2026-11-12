# Member 4 — Tuku — Subscription actions + workshop scenarios 4 and 5

**Assigned member:** Tuku — [@tukue](https://github.com/tukue)  
**Branch:** `feature/subscriber-actions`  
**Owned:** `src/main/java/com/workshop/functional/SubscriberAction.java`, `SubscriberActions.java`, `src/test/java/com/workshop/SubscriberActionsTest.java`.

## Goal

Implement the two Part 1 state-changing actions as Java lambdas; then verify the **conditional** actions from Part 2 in integration tests. Action code changes its subscriber but does not decide *which* subscribers qualify.

## Build your component step by step

1. Define `@FunctionalInterface SubscriberAction` with exactly one abstract method: `void run(Subscriber subscriber)`.
2. Implement static `extendByMonths(int months)` in `SubscriberActions` returning `SubscriberAction`.
   - Require `months > 0` (team rule).
   - When `run` executes, increase subscriber's remaining months by that amount.
   - Creating an action must **not** change any subscriber.
3. Implement static `deactivate()` returning `SubscriberAction`; when run, set `active=false`.
4. Write unit tests in `SubscriberActionsTest.java` for extension +3, invalid duration, deactivation, repeated deactivation, and delayed mutation.

## Your Part 2 scenario tests (you own these)

- **Scenario 4: Extend subscriptions for active paying subscribers that are expiring.** Compose the *filter* `active AND paying AND expiring` and call `SubscriberProcessor.applyToMatching` with `extendByMonths(3)` (3 is a team example, not specified by the teacher). Only IDs **2,6** qualify. Assert ID 2 changes **1→4** months and ID 6 **0→3**; all others remain unchanged, and returned IDs are 2,6.
- **Scenario 5: Deactivate expired FREE subscribers.** Filter `plan==FREE AND monthsRemaining==0`. Only IDs **1,4** qualify (ID 4 was already inactive); both should be false afterward, all other states unchanged, and returned IDs are 1,4.
- Use **fresh subscriber lists** for scenarios 4 and 5; they mutate state. Write named JUnit tests with assertions against IDs and all relevant subscriber states.

## Dependencies

Unit tests depend on Member 1 model. The two scenario tests additionally need Member 3 filters and Member 5 processor. Add the scenario assertions after those components are merged; don't change colleagues' code to make your tests compile.

## Definition of done

- [ ] One-method action functional interface; extension and deactivation actions.
- [ ] Unit tests pass; action creation does not prematurely mutate state.
- [ ] **Scenario 4 and 5** JUnit assertions pass with all nonmatching subscribers untouched.
- [ ] PR records commands, expected/observed results, and scenario details.
- [ ] Send Member 6 technical summary, test evidence, and PR URL.
