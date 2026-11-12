# Functional Programming Workshop — Java 25 Team Starter

**Repository:** https://github.com/jackaldzn-blip/functional-programming-workshop

A six-person Java 25 + Maven workshop for implementing the subscription list management system from the [original Lexicon assignment](assignment/Functional_Programming_Workshop.md).

**All Java source and JUnit test class shells are deliberately empty.** The team implements the required methods and rules itself; the starter contains documentation, not solutions.

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

| Member | Code to implement | JUnit test ownership | Extra responsibility |
|---|---|---|---|
| **1 — Lead** | `Subscriber`, `Plan` | Model tests | Integrate and review PRs |
| **2** | `SubscriberDAO` | DAO tests | Data-access checks |
| **3** | `SubscriberFilter`, `SubscriberFilters` | Unit tests + **scenarios 1,2** | Five filter rules |
| **4** | `SubscriberAction`, `SubscriberActions` | Unit tests + **scenarios 4,5** | Two actions |
| **5** | `SubscriberProcessor` | Unit tests + **scenario 3** | Processing integration |
| **6** | `Main` | **Scenario 6** | Demo + assemble report |

**Report contributions are shared:** every member sends their own implementation explanation, PR URL, test evidence and limitations to Member 6. Member 6 compiles the final report; the lead approves it.

## Original requirements and design choices

Part 1 requires six conceptual types and seven rules. Part 2 offers six **optional** JUnit test scenarios; the team elects to implement them across Members 3–6. Additional helpers (`SubscriberFilters`, `SubscriberActions`, `Main`), constructor details, storage and return semantics are documented **team design decisions**, not instructor-mandated features.

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

`mvn exec:java` cannot run until Member 6 implements `Main.main`. A successful `mvn clean verify` of an **empty starter** is only a compile check; **zero tests means no functionality has been verified**.

## Integration order

Model/API → DAO, filters, actions → Processor → Scenario integration tests and Main demo → Shared report and lead review. Branches may be worked on concurrently, but dependent scenario tests should not be reported as passing before all required components are merged.
