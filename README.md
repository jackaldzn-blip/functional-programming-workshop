# Functional Programming Workshop

A six-member **Java 25 / Maven** project implementing a subscription list management system based on the [workshop assignment](assignment/Functional_Programming_Workshop.md).

The exercise demonstrates object-oriented modeling and **functional programming** through reusable selection filters, actions and a processor. Development is shared across the team; see [TEAM.md](TEAM.md) for owners and progress.

## Workshop scope

- **Part 1:** Model subscribers and plans, store and retrieve subscribers, apply five selection rules (active, expiring, active and expiring, by plan, paying), and perform two actions (extend and deactivate).
- **Part 2 (optional):** Six JUnit 5 scenarios. The team has agreed to implement them, with tests distributed across members.
- **Team design:** Small helper classes, in-memory storage and a console demonstration. These implementation choices extend the supplied conceptual UML without changing its required types or rules.

## Project components

| Component | Purpose |
|---|---|
| `Subscriber`, `Plan` | Subscriber data and plan types |
| `SubscriberDAO` | Store and retrieve subscribers |
| `SubscriberFilter`, `SubscriberFilters` | Functional selection rules |
| `SubscriberAction`, `SubscriberActions` | Functional subscription updates |
| `SubscriberProcessor` | Apply filters and actions to subscriber lists |
| `Main` | Run and demonstrate the system |
| JUnit 5 tests | Verify models, components and workshop scenarios |

## Build and run

Requires **JDK 25** and **Maven**. The project targets Java 25 in `pom.xml` and GitHub Actions.

```bash
mvn clean verify
mvn exec:java
```

`mvn clean verify` compiles the project and runs implemented tests. `mvn exec:java` launches the current `Main` application. Functional coverage depends on which team contributions have been integrated; a successful build alone does not establish that every scenario is complete.

## Teamwork and documentation

Each member owns implementation and tests, works on a feature branch and submits a pull request to `main`. Team members contribute their results to the shared report.

- [Team roster and responsibilities](TEAM.md)
- [Task allocations and individual briefs](docs/team-tasks.md)
- [Technical UML](docs/technical-uml.md) and [Java API contracts](docs/contracts.md)
- [Testing responsibilities](docs/testing-responsibilities.md), [shared sample cases](docs/example-scenarios.md) and [workshop coverage](docs/workshop-coverage.md)
- [Contribution workflow](CONTRIBUTING.md) and [report template](docs/final-report-template.md)

Detailed implementation instructions—including console behavior—belong in the relevant member brief, not in this project overview.
