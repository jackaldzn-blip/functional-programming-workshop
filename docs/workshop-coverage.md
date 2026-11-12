# Workshop coverage map — Java 25 revised team allocation

This is a **requirements-to-owner plan**, not proof that the workshop code is implemented. The project started with empty Java class and JUnit shells; verify the current code and PRs for actual implementation status. Source: [original assignment](../assignment/Functional_Programming_Workshop.md).

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

| Rule | Implementation owner | Planned method |
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

| Original scenario | JUnit owner | Test file |
|---|---|---|
| 1. Show active | **Jay (3)** | `SubscriberFiltersTest.java` |
| 2. Show expiring | **Jay (3)** | `SubscriberFiltersTest.java` |
| 3. Show active and expiring | **Carl (5)** | `SubscriberProcessorTest.java` |
| 4. Extend active, paying, expiring | **Tuku (4)** | `SubscriberActionsTest.java` |
| 5. Deactivate expired FREE | **Tuku (4)** | `SubscriberActionsTest.java` |
| 6. Filter by plan | **Padma (6)** | `PlanScenarioTest.java` |

All six also write **component-level tests** for the Java code they own. Padma (Member 6) additionally builds and manually validates the **approved interactive console menu** and coordinates the **shared** final report. See [testing responsibilities](testing-responsibilities.md) and [team task table](team-tasks.md).

## Decisions made by our team, not required by instructor

- `SubscriberFilters` and `SubscriberActions` helper classes; `Main.java` interactive console menu (team-approved extra, not required by the original workshop).
- Constructors, getters/setters, validation, in-memory DAO and duplicate ID handling.
- `applyToMatching` returns a new list of matched/processed subscribers; the assignment only specifies the return **type**.
- Sample dataset and +3 month extension example; the workshop does not specify either.
- Report, PR workflow and detailed collaboration roles.

## Java toolchain

- Java 25, Maven, JUnit 5; `pom.xml` release 25 and GitHub Actions Temurin 25.
- An empty starter passing compilation **does not** mean any requirement is implemented or tested.
