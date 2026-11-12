# Technical UML — Markdown Mermaid diagram

This Mermaid diagram shows the workshop's six conceptual types—`Subscriber`, `Plan`, `SubscriberDAO`, `SubscriberFilter`, `SubscriberAction` and `SubscriberProcessor`—alongside the team's supporting helpers and entry point. The additional elements are implementation choices, not extra instructor requirements.

**Implementation note (12 November 2026):** the core components below are merged. The `Main` dependency arrows describe the **intended completed console design**; the current `Main.java` still prints sample data without invoking the full interactive workflow. See [project status](project-status.md). The optional `SubscriberActions.activate()` helper is implemented but not drawn as an original workshop rule.

```mermaid
classDiagram
    direction LR

    class Plan {
        <<enumeration>>
        FREE
        BASIC
        PRO
    }

    class Subscriber {
        -int id
        -String email
        -Plan plan
        -boolean active
        -int monthsRemaining
        +Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining)
        +getId() int
        +getEmail() String
        +getPlan() Plan
        +isActive() boolean
        +getMonthsRemaining() int
        +setEmail(String email) void
        +setPlan(Plan plan) void
        +setActive(boolean active) void
        +setMonthsRemaining(int monthsRemaining) void
        +toString() String
    }

    class SubscriberDAO {
        -List~Subscriber~ subscribers
        +save(Subscriber subscriber) void
        +findAll() List~Subscriber~
        +findById(int id) Optional~Subscriber~
    }

    class SubscriberFilter {
        <<functionalinterface>>
        +matches(Subscriber subscriber) boolean
    }

    class SubscriberFilters {
        <<utility>>
        +active() SubscriberFilter
        +expiring() SubscriberFilter
        +activeAndExpiring() SubscriberFilter
        +byPlan(Plan plan) SubscriberFilter
        +paying() SubscriberFilter
    }

    class SubscriberAction {
        <<functionalinterface>>
        +run(Subscriber subscriber) void
    }

    class SubscriberActions {
        <<utility>>
        +extendByMonths(int months) SubscriberAction
        +deactivate() SubscriberAction
    }

    class SubscriberProcessor {
        +findSubscribers(List~Subscriber~ list, SubscriberFilter filter) List~Subscriber~
        +applyToMatching(List~Subscriber~ list, SubscriberFilter filter, SubscriberAction action) List~Subscriber~
    }

    class Main {
        +main(String[] args) void
    }

    Subscriber --> Plan : has
    SubscriberDAO o-- Subscriber : stores
    SubscriberFilters ..> SubscriberFilter : creates
    SubscriberFilters ..> Plan : compares
    SubscriberActions ..> SubscriberAction : creates
    SubscriberProcessor ..> SubscriberFilter : evaluates
    SubscriberProcessor ..> SubscriberAction : runs
    SubscriberProcessor ..> Subscriber : processes
    Main ..> SubscriberDAO : loads
    Main ..> SubscriberProcessor : uses
    Main ..> SubscriberFilters : uses
    Main ..> SubscriberActions : uses
```

## Member-to-class assignment

| Member | Diagram elements |
|---|---|
| 1 | `Plan`, `Subscriber` |
| 2 | `SubscriberDAO` |
| 3 | `SubscriberFilter`, `SubscriberFilters` |
| 4 | `SubscriberAction`, `SubscriberActions` |
| 5 | `SubscriberProcessor`; processor unit tests and scenario 3 |
| 6 | `Main`; `PlanScenarioTest` (test class not shown); shared report coordination |

**Design note:** The workshop leaves the contents of the `applyToMatching` result unspecified; the team returns the matched, processed subscribers. The `Subscriber.toString()` override supports readable output. See [contracts.md](contracts.md) for the agreed API.
