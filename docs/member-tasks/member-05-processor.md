# Member 5 — Carl — SubscriberProcessor + workshop scenario 3

**Assigned member:** Carl — [@Matojotam](https://github.com/Matojotam)  
**Branch:** `feature/subscriber-processor`  
**Owned:** `src/main/java/com/workshop/service/SubscriberProcessor.java`, `src/test/java/com/workshop/service/SubscriberProcessorTest.java`.

## Goal

Connect functional filters and actions without hardcoding any business rule into the processor. **Member 6**, not you, owns `Main.java` and its console demonstration.

## Build your component step by step

1. Implement `findSubscribers(List<Subscriber> list, SubscriberFilter filter): List<Subscriber>`:
   - Visit input subscribers in order and evaluate the supplied filter.
   - Return a **new list of matching subscribers** in their original order.
   - Do not mutate subscriber state or remove elements from the input.
2. Implement `applyToMatching(List<Subscriber> list, SubscriberFilter filter, SubscriberAction action): List<Subscriber>`:
   - Evaluate the filter **once per original subscriber**.
   - If it matches, execute the action **once** for that subscriber.
   - Return a **new list containing only the processed subscribers** in input order (our team's interpretation of UML return type).
   - Never process a nonmatch or alter the original list's membership.
3. Follow [contracts](../contracts.md). Use functional-interface parameters, not hardcoded `active`/`paying` logic.
4. Write processor unit tests in `SubscriberProcessorTest.java` for no matches, input/result order, new result-list identity, only matching action execution, and no duplicate action execution.

## Your Part 2 scenario test (you own this)

- **Scenario 3: Active AND expiring subscribers.** With the [shared dataset](../example-scenarios.md), call your processor with `SubscriberFilters.activeAndExpiring()` and assert IDs **1,2,6** in order. Inactive but expiring IDs 4,5 must be excluded.
- Use a named JUnit `@Test` with explicit assertions, not printed results.

## Dependencies

Requires Member 1 model and Member 3/4 functional interfaces. You can implement processor logic against the agreed interfaces; run integrated tests after the interfaces and filters are merged. Coordinate with Member 6 so `Main` calls the correct two method signatures.

## Definition of done

- [x] Two UML methods implemented and behave as documented.
- [x] Unit tests cover order, no matches and action invoked once per match.
- [x] **Scenario 3** test passes with IDs 1,2,6.
- [ ] PR #11 was merged with passing CI; **send Member 6 your personal implementation summary and actual test evidence** for the report.
