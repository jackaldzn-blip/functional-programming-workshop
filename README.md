# Functional Programming Workshop — Java 25 Team Starter

**Repository:** https://github.com/jackaldzn-blip/functional-programming-workshop

A six-person Java 25 + Maven workshop for implementing the subscription list management system from the [original Lexicon assignment](assignment/Functional_Programming_Workshop.md).

**The original Java starter contained empty class shells; work is now progressing through individual member contributions.** Review the current source and Pull Requests for implementation status.

## Start here

1. [TEAM.md — member names, ownership, PR status](TEAM.md)
2. [Detailed task table and individual briefs](docs/team-tasks.md)
3. [Technical UML in Markdown (Mermaid)](docs/technical-uml.md)
4. [Agreed Java API contracts](docs/contracts.md)
5. [Shared test ownership and scenario expectations](docs/testing-responsibilities.md)
6. [Sample records and expected results](docs/example-scenarios.md)
7. [Coverage of original workshop requirements](docs/workshop-coverage.md)
8. [Git collaboration workflow](CONTRIBUTING.md)

## Work assignment

| Member (GitHub) | Code to implement | JUnit test ownership | Extra responsibility |
|---|---|---|---|
| **1 — [Anas](https://github.com/jackaldzn-blip) (@jackaldzn-blip)** | `Subscriber`, `Plan` | Model tests | Team Lead, reviews and integration |
| **2 — [Veselina](https://github.com/vlinakonsa) (@vlinakonsa)** | `SubscriberDAO` | DAO tests | Data-access checks |
| **3 — [Jay](https://github.com/JayB111) (@JayB111)** | `SubscriberFilter`, `SubscriberFilters` | Unit tests + **scenarios 1,2** | Five filter rules |
| **4 — [Tuku](https://github.com/tukue) (@tukue)** | `SubscriberAction`, `SubscriberActions` | Unit tests + **scenarios 4,5** | Two actions |
| **5 — [Carl](https://github.com/Matojotam) (@Matojotam)** | `SubscriberProcessor` | Unit tests + **scenario 3** | Processing integration |
| **6 — [Padma](https://github.com/Padmaqaauto) (@Padmaqaauto)** | `Main` (interactive console menu) | **Scenario 6** + manual menu checks | User-facing demonstration + shared final report |

**Report contributions are shared:** every member sends their own implementation explanation, PR URL, test evidence and limitations to Member 6. Padma (Member 6) compiles the final report; Anas (Member 1, Team Lead) approves it.

## Original requirements and design choices

Part 1 requires six conceptual types and seven rules. Part 2 offers six **optional** JUnit test scenarios; the team elects to implement them across Members 3–6. Additional helpers (`SubscriberFilters`, `SubscriberActions`, `Main`), constructor details, storage and return semantics are documented **team design decisions**, not instructor-mandated features. **The interactive console menu is an optional team-approved extension** owned by Padma; it does not change the workshop UML or the original JUnit scenario allocation.

## Environment

- **JDK 25** (Temurin or another compatible distribution)
- Maven
- JUnit 5 (configured in `pom.xml`)

The project targets Java 25 through `pom.xml` (`maven.compiler.release=25`), `.java-version` and GitHub Actions Temurin 25. Java 25-specific syntax is not necessary for the exercise.

```bash
java -version
mvn clean compile
mvn test
mvn clean verify
mvn exec:java
```

`mvn exec:java` starts Padma's interactive menu once `Main.main` and the dependent classes are implemented. Check the actual JUnit test count when running `mvn clean verify`; successful compilation alone does not prove the workshop rules work.

## Interactive console extension (Member 6 — Padma)

Padma has agreed to make `Main.java` an **interactive terminal menu**, using a simple Java input loop. The menu offers: show all, active, expiring, active-and-expiring, filter by plan, paying subscribers, extend eligible active paying expiring subscriptions by **3 example months**, deactivate expired FREE subscribers, and exit (`0`). The menu should handle invalid input and keep changes during the current session. It must call the DAO, processor, filters and actions written by the other members rather than duplicate their rules. See [Padma's detailed task brief](docs/member-tasks/member-06-main-report.md) for acceptance checks.

## Integration order

Model/API → DAO, filters, actions → Processor → Scenario integration tests and Main demo → Shared report and lead review. Branches may be worked on concurrently, but dependent scenario tests should not be reported as passing before all required components are merged.
