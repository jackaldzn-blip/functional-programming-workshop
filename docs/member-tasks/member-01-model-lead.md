# Member 1 — Anas — Subscriber model, Plan enum and team lead

**Assigned member:** Anas — [@jackaldzn-blip](https://github.com/jackaldzn-blip)  
**Suggested branch:** `feature/subscriber-model`  
**Edit only:** `src/main/java/com/workshop/model/Subscriber.java`, `Plan.java`, `src/test/java/com/workshop/model/SubscriberModelTest.java` (plus team coordination docs).

## Your goal

Create the fundamental data types that everyone else relies on. A `Subscriber` represents one subscription and a `Plan` represents its tier. You also coordinate method contracts and merge reviewed contributions.

## Implement in this order

1. Open the [technical UML](../technical-uml.md). In `Plan.java`, implement **exactly** the enum constants `FREE`, `BASIC`, and `PRO`.
2. In `Subscriber.java`, declare the five **private** fields from the UML, with correct Java types: id, email, plan, active, monthsRemaining.
3. Implement the public five-argument constructor as agreed in [contracts](../contracts.md), assigning all fields.
4. Implement five getters: `getId`, `getEmail`, `getPlan`, `isActive`, `getMonthsRemaining`.
5. Implement four setters: `setEmail`, `setPlan`, `setActive`, `setMonthsRemaining`. **Do not** provide `setId`; ID must remain stable.
6. Enforce team-agreed validation: positive ID, nonblank email, non-null Plan, nonnegative months. Keep these checks consistent in constructor and setters.
7. Implement `@Override public String toString()` to display the current subscriber ID, email, plan, active status, and remaining months for Padma's console interface. This is a team convenience beyond the original workshop UML.
8. Implement focused JUnit 5 tests in `SubscriberModelTest.java`: constructor/getters, setters, invalid input, stable ID, and `toString()` output before/after state changes. Also manually verify a newly created subscriber has the correct values and that an action can later update months and active status through your setters.

## Examples to reason through

- Input: `id=2, email=blair@example.test, plan=BASIC, active=true, months=1` → getters must return these exact values.
- Set months from 1 to 4 → getter returns 4.
- Set active from true to false → `isActive()` is false.
- Construct with -1 months or null plan → validation rejects it.

## As team lead

- The names and usernames are confirmed in [`TEAM.md`](../../TEAM.md). Create and coordinate one GitHub Issue per member.
- Agree on [contracts](../contracts.md) **before** others import model API; review any suggested signature change.
- Ensure the model PR is merged first, then review each member's PR and ensure CI/tests are meaningful.
- Ask each member for their short implementation summary and pass them to Member 6.

## Shared testing and report evidence

Follow [testing responsibilities](../testing-responsibilities.md). Implement and run your owned JUnit tests. Send Member 6 your PR link, a short implementation explanation and **actual** test results; Member 6 will coordinate the report, not test your code for you.

## Definition of done

- [x] Three plan enum values; five private fields; correct constructor, getters and setters; readable `toString()` override.
- [x] Validation for invalid input; no subscription/business-rule logic in the model.
- [x] `mvn clean compile` succeeds once merged; Your own `SubscriberModelTest` passes.
- [x] Other members can compile against the model without modifying your class.
- [ ] PR includes a short description and manual check performed.
