# Detailed team tasks — start here

This is the **rebalanced six-member assignment**. Every member contributes Java code and participates in testing. The workshop's Part 2 JUnit challenge is optional in the assignment; **our team has elected to complete it** and share its six scenarios.

Before starting, read the [original workshop](../assignment/Functional_Programming_Workshop.md), [technical UML](technical-uml.md), [API contracts](contracts.md), [example data](example-scenarios.md) and [testing guide](testing-responsibilities.md). All starter Java classes are intentionally empty.

| Member / owner | Java coding task | Tests and scenarios | Detailed brief |
|---|---|---|---|
| **1 — [Anas](https://github.com/jackaldzn-blip) (@jackaldzn-blip)** | `Subscriber.java`, `Plan.java` | `SubscriberModelTest.java`; getters, setters, constructor, validation | [Anas's task](member-tasks/member-01-model-lead.md) |
| **2 — [Veselina](https://github.com/vlinakonsa) (@vlinakonsa)** | `SubscriberDAO.java` | `SubscriberDaoTest.java`; save, findAll, findById | [Veselina's task](member-tasks/member-02-dao.md) |
| **3 — [Jay](https://github.com/JayB111) (@JayB111)** | `SubscriberFilter.java`, `SubscriberFilters.java` | `SubscriberFiltersTest.java`; unit tests + **scenario 1 (active)** and **scenario 2 (expiring)** | [Jay's task](member-tasks/member-03-filters.md) |
| **4 — [Tuku](https://github.com/tukue) (@tukue)** | `SubscriberAction.java`, `SubscriberActions.java` | `SubscriberActionsTest.java`; unit tests + **scenario 4 (extend)** and **scenario 5 (deactivate)** | [Tuku's task](member-tasks/member-04-actions.md) |
| **5 — [Carl](https://github.com/Matojotam) (@Matojotam)** | `SubscriberProcessor.java` | `SubscriberProcessorTest.java`; unit tests + **scenario 3 (active AND expiring)** | [Carl's task](member-tasks/member-05-processor.md) |
| **6 — [Padma](https://github.com/Padmaqaauto) (@Padmaqaauto)** | `Main.java` | `PlanScenarioTest.java`; **scenario 6 (by plan)** | [Padma's task](member-tasks/member-06-main-report.md); also assembles the shared report |

## Integration and test sequence

1. **Model first:** Member 1 implements and merges `Plan` and `Subscriber`, and freezes the agreed public API.
2. **Component work:** Members 2, 3 and 4 implement DAO, filters and actions, each with local unit tests.
3. **Processing:** Member 5 implements and tests the processor after the functional interfaces are available.
4. **Cross-component scenarios:** Members 3, 4 and 5 add their assigned workshop scenario assertions once `SubscriberProcessor` and other dependencies are merged. No one should change another member's test file without agreement.
5. **Demo and report:** Member 6 implements `Main.java` displaying all six scenarios, adds scenario 6 JUnit tests, and starts the report using evidence provided by all members.
6. **Final review:** Lead reviews PRs, resolves integration problems with owners, confirms six scenarios and nonzero JUnit test count, and approves the report.

Work may be prepared on separate branches in parallel; **do not claim integration tests passed until all dependencies exist**.

## Submission checklist for EVERY member

- [ ] Verify your assigned name and GitHub username in [TEAM.md](../TEAM.md).
- [ ] Read your own task brief, [contracts](contracts.md), UML and [test expectations](testing-responsibilities.md).
- [ ] Implement only your owned Java files; agree on cross-file changes before editing.
- [ ] Write meaningful JUnit tests and assertions for your assigned scope.
- [ ] Run relevant checks (`mvn test`, `mvn clean verify` once integrated); record exact results, not assumptions.
- [ ] Open a Pull Request linking your GitHub Issue.
- [ ] Send Padma (Member 6) a **3–5 sentence implementation summary**, PR link, and test evidence/limitations for the final report.

## Instructor requirements vs team design

The original workshop specifies six conceptual types, seven business rules and an optional six-scenario JUnit challenge. Additional helper classes, constructors, validation, in-memory DAO behavior, return conventions and sample records are **team implementation decisions**, not instructions claimed to be in the workshop.
