# Workshop requirements coverage

This maps the [original assignment](../assignment/Functional_Programming_Workshop.md) to team responsibilities. It distinguishes original requirements from the team's implementation. For the verified **13 November 2026** progress baseline, see [README progress](../README.md#current-progress): all six conceptual types, seven rules, six scenario tests and the console are merged; **53 JUnit tests passed** on `main`.

## Original conceptual UML

| Required type | Required member(s) | Coding owner |
|---|---|---|
| `Subscriber` | `id:int`, `email:String`, `plan:Plan`, `active:boolean`, `monthsRemaining:int` | **Anas (1)** |
| `Plan` | Enum values FREE, BASIC, PRO | **Anas (1)** |
| `SubscriberDAO` | `save(Subscriber):void`, `findAll():List<Subscriber>`, `findById(int):Optional<Subscriber>` | **Veselina (2)** |
| `SubscriberFilter` | Functional interface: `matches(Subscriber):boolean` | **Jay (3)** |
| `SubscriberAction` | Functional interface: `run(Subscriber):void` | **Tuku (4)** |
| `SubscriberProcessor` | `findSubscribers(List,Filter):List`, `applyToMatching(List,Filter,Action):List` | **Carl (5)** |

## Part 1 — All seven required rules

| Rule | Implementation owner | Implemented method |
|---|---|---|
| Active | 3 | `SubscriberFilters.active()` |
| Expiring (0/1 month) | 3 | `SubscriberFilters.expiring()` |
| Active AND expiring | 3 | `SubscriberFilters.activeAndExpiring()` |
| By plan | 3 | `SubscriberFilters.byPlan(Plan)` |
| Paying: BASIC/PRO | 3 | `SubscriberFilters.paying()` |
| Extend subscription | 4 | `SubscriberActions.extendByMonths(int)` |
| Deactivate | 4 | `SubscriberActions.deactivate()` |

Member 5 combines filters and actions using the two required processor operations. Member 6 demonstrates the results in `Main.java`.

## Part 2 — Six OPTIONAL JUnit challenge scenarios (team elected to complete)

| Original scenario | JUnit owner | Test file | Current `main` status (13 Nov 2026) |
|---|---|---|---|
| 1. Show active | **Jay (3)** | `SubscriberProcessorTest.java` | **Passed** via PR #18 |
| 2. Show expiring | **Jay (3)** | `SubscriberProcessorTest.java` | **Passed** via PR #18 |
| 3. Show active and expiring | **Carl (5)** | `SubscriberProcessorTest.java` | **Passed** via PR #11 |
| 4. Extend active, paying, expiring | **Tuku (4)** | `SubscriberActionsTest.java` | **Passed** via PR #11 |
| 5. Deactivate expired FREE | **Tuku (4)** | `SubscriberActionsTest.java` | **Passed** via PR #11 |
| 6. Filter by plan | **Padma (6)** | `PlanScenarioTest.java` | **Passed** via PR #17 |

Each member tested their assigned component. Member 6 implemented the console, documented its manual checks and coordinated the approved [final report](final-report.md). Padma confirmed the report and repository were shared with instructors on 13 November 2026. See [testing responsibilities](testing-responsibilities.md) and [team tasks](team-tasks.md).

## Decisions made by our team, not required by instructor

- Supporting filter/action helpers and a console entry point in `Main.java`.
- Constructors, getters/setters, validation, in-memory DAO and duplicate ID handling.
- `applyToMatching` returns a new list of matched/processed subscribers; the assignment only specifies the return **type**.
- Sample dataset and +3 month extension example; the workshop does not specify either.
- Report, PR workflow and detailed collaboration roles.

## Java toolchain

- Java 25, Maven, JUnit 5; `pom.xml` release 25 and GitHub Actions Temurin 25.
- A successful build does not prove that all workshop scenarios have meaningful passing tests.
