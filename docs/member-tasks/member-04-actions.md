# Member 4 — Tuku — Subscription actions + workshop scenarios 4 and 5

**Assigned member:** Tuku — [@tukue](https://github.com/tukue)  
**Branch:** `feature/subscriber-actions`  
**Owned:** `src/main/java/com/workshop/functional/SubscriberAction.java`, `SubscriberActions.java`, `src/test/java/com/workshop/functional/SubscriberActionsTest.java`.

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

Unit tests depend on Member 1 model. The two scenario tests additionally need Member 3 filters and Member 5 processor. Those dependencies are now merged; scenarios 4–5 are already implemented. Do not change colleagues' code or take over another task without coordination.

## Definition of done

- [x] One-method action functional interface; extension and deactivation actions. An additional `activate()` action is also implemented.
- [x] Unit tests and integration scenarios pass: **9 action tests** within **48 project tests**, 0 failures, in Java 25 `main` CI (12 November 2026).
- [x] **Scenario 4 and 5** JUnit assertions pass with all nonmatching subscribers untouched (merged through PR #11).
- [ ] Provide Padma a complete personal implementation summary and verified evidence for the shared report. PR #8's brief description does not replace that contribution.
- [ ] Send Member 6 technical summary, test evidence, and PR URL.

**Current status:** [PR #8](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/8) and the scenario tests contributed through [PR #11](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/11) are merged. The action component provides `extendByMonths`, `deactivate`, and optional `activate` (not part of the original assignment). **Scenarios 4 and 5 are verified in `main` CI**; remaining obligation is to supply final report evidence.
