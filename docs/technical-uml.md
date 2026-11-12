# Technical UML — Markdown Mermaid diagram

The diagram below is in this `.md` file so GitHub renders it directly. **The original starter contained empty classes/interfaces**; each assigned member implements the agreed methods. Original workshop constructs are `Subscriber`, `Plan`, `SubscriberDAO`, `SubscriberFilter`, `SubscriberAction` and `SubscriberProcessor`. The `SubscriberFilters`, `SubscriberActions`, `Main` classes and their additional members are the **team's design**, not additions required by the instructor's conceptual UML. The team has also approved an **interactive console menu** inside `Main`; this is presentation/input behavior, not a new UML domain class.

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
| 6 — Padma | `Main` (interactive console menu, shown), `PlanScenarioTest` (test class not shown), shared final report coordination |

**Important:** In the original UML, `applyToMatching` returns a list but its contents are unspecified. We have chosen to return the **matched, processed subscribers**. See [contracts.md](contracts.md). The `Subscriber.toString()` override is a team convenience for console readability, not part of the instructor's original UML. Keep this diagram synchronized with the final Java API.
