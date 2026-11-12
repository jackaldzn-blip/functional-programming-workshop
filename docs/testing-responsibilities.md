# Testing responsibilities — shared across the team

No single member owns all testing. **Each developer unit-tests their own code**, and the six workshop Part 2 scenarios are split between Members 3–6. Padma (Member 6) collects test results for the report; they are not required to write other members' tests.

## Exact ownership

| Member | Test file | Individual unit tests | Assigned workshop scenario(s) |
|---|---|---|---|
| Anas (Member 1) | `SubscriberModelTest.java` | Fields, getters/setters, valid/invalid constructor arguments, nonnegative remaining months | None — model tests |
| Veselina (Member 2) | `SubscriberDaoTest.java` | Save new/existing ID, findAll order and copy, findById found/missing, null save | None — DAO tests |
| Jay (Member 3) | `SubscriberFiltersTest.java` | Five filter factories, zero/one/two-month boundary, inactive cases | **1 — Active; 2 — Expiring** |
| Tuku (Member 4) | `SubscriberActionsTest.java` | Extend, invalid duration, deactivate, repeat action, no premature mutation | **4 — Extend eligible paid; 5 — Deactivate expired FREE** |
| Carl (Member 5) | `SubscriberProcessorTest.java` | Selection order, no matches, only matching mutations, once-per-match action | **3 — Active AND expiring** |
| Padma (Member 6) | `PlanScenarioTest.java` | By-plan selection through processor using each enum value; manual console menu acceptance checks | **6 — Filter by plan** |

## Shared seven-subscriber fixture

Use [example-scenarios.md](example-scenarios.md). Each test creates fresh `Subscriber` objects to prevent one mutation test from affecting another.

## Required assertions by workshop scenario

| Scenario | Owner | What must be asserted |
|---|---|---|
| **1 — Show active** | Jay (Member 3) | Filtering returns IDs `1,2,3,6,7` in order |
| **2 — Show expiring** | Jay (Member 3) | Months 0/1 return IDs `1,2,4,5,6`; inactive expiring are included |
| **3 — Active and expiring** | Carl (Member 5) | Processor returns IDs `1,2,6`; filter excludes inactive and non-expiring |
| **4 — Extend subscriptions for active paying expiring** | Tuku (Member 4) | Only IDs `2,6` match; +3 team-example months changes `1→4`, `0→3`; all others unchanged |
| **5 — Deactivate expired FREE subscribers** | Tuku (Member 4) | Only IDs `1,4` match; both end inactive; others unchanged |
| **6 — Filter by plan** | Padma (Member 6) | FREE `1,4,7`; BASIC `2,5`; PRO `3,6`, checked independently |

**Integration dependency:** Scenarios 3–6 require the processor and the filters/actions they exercise. Members 4 and 6 may draft tests but cannot verify them until these are merged; Member 3's scenarios may use the processor after it is merged for consistent integration-level validation.

## How to record evidence

Each member sends Padma (Member 6) their JUnit test class and method names, actual `mvn test` or `mvn clean verify` result, executed test count and PR link. Padma also records manual checks for the `Main` console as specified in her [task brief](member-tasks/member-06-main-report.md). If not run yet, explicitly say **Not yet run**. The team lead checks the aggregate test count and results before claiming Part 2 is complete.

```text
Member:
Files implemented:
Test file and test methods:
Workshop scenario(s) owned:
Command run and actual result:
PR link:
Known limitations:
3–5 sentence implementation explanation:
```

Do not treat empty test classes and a successful compilation as passing tests.
