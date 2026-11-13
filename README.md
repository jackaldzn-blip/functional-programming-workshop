# Functional Programming Workshop

A six-member **Java 25 / Maven** project implementing a subscription list management system based on the [workshop assignment](assignment/Functional_Programming_Workshop.md).

The exercise demonstrates object-oriented modeling and **functional programming** through reusable selection filters, actions and a processor. Development is shared across the team; see [TEAM.md](TEAM.md) for owners and progress.

## Current progress

**Verified 13 November 2026:** All core components, the six agreed JUnit workshop scenarios (**1–6**), and the interactive console menu (**0–8**) are merged into `main`. Java 25 GitHub Actions ran `mvn -B --no-transfer-progress clean verify`: **53 tests passed, 0 failures, 0 errors, 0 skipped** ([post-merge run](https://github.com/jackaldzn-blip/functional-programming-workshop/actions/runs/43396578998)). The console and scenario 6 were merged in [PR #17](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/17); scenarios 1–2 were merged in [PR #18](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/18).

**Final report:** Padma's [Group 4 completion report](docs/final-report.md) consolidates member contributions, PR links, JUnit evidence, console screenshots and the team retrospective. Reviewed and approved through [PR #21](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/21).

**Project status: Completed and submitted (13 November 2026).** All assigned implementation, tests, documentation and team-lead review are complete. Padma confirmed that she shared the approved final report and GitHub repository with instructors **Elnaz and Mehrdad** on behalf of Group 4. This records the team's submission, not an instructor grading or acceptance decision.

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

`mvn clean verify` compiles the project and runs the JUnit suite; `mvn exec:java` launches the completed interactive `Main` application. The integrated Java 25 CI run verified **53 passing tests** across the completed project.

## Teamwork and documentation

Each member owns implementation and tests, works on a feature branch and submits a pull request to `main`. Team members contribute their results to the shared report.

- [Team roster and responsibilities](TEAM.md)
- [Task allocations and individual briefs](docs/team-tasks.md)
- [Technical UML](docs/technical-uml.md) and [Java API contracts](docs/contracts.md)
- [Testing responsibilities](docs/testing-responsibilities.md), [shared sample cases](docs/example-scenarios.md) and [workshop coverage](docs/workshop-coverage.md)
- [Final project report — Group 4](docs/final-report.md)
- [Contribution workflow](CONTRIBUTING.md) and [reusable report template](docs/final-report-template.md)

Detailed implementation instructions and acceptance checks are in the individual member briefs.
