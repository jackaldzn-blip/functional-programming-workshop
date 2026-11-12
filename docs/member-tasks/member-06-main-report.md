# Member 6 — Padma — Main demonstration + workshop scenario 6 + report coordination

**Assigned member:** Padma — [@Padmaqaauto](https://github.com/Padmaqaauto)  
**Branch:** `feature/main-demo-and-report`  
**Owned:** `src/main/java/com/workshop/Main.java`, `src/test/java/com/workshop/PlanScenarioTest.java`, `docs/final-report.md` (created from template).

## Goal

Write a **runnable Java demonstration** of the workshop using everybody's code, implement **one** JUnit scenario (by plan), and assemble the report from other members' supplied explanations and test results. **You are not responsible for writing the other five scenarios or doing every developer's testing.**

## Part A — Main.java (your primary coding task)

1. Add a Java `main(String[] args)` entrypoint to the currently empty `Main.java`.
2. Construct the [seven-record sample dataset](../example-scenarios.md), optionally saving to and retrieving from `SubscriberDAO`.
3. Create and use `SubscriberProcessor`, the filters and the actions via their agreed [contracts](../contracts.md). **Do not reimplement those members' rules in `Main`.**
4. Print clear labels and results for all **six** workshop demonstration scenarios:
   1. Active IDs **1,2,3,6,7**.
   2. Expiring IDs **1,2,4,5,6**.
   3. Active and expiring IDs **1,2,6**.
   4. Extend active + paying + expiring by **3 example months**: IDs **2 (1→4)** and **6 (0→3)** only.
   5. Deactivate FREE with **0 months**: IDs **1 and 4**; both inactive afterward.
   6. Filter by plan: FREE **1,4,7**, BASIC **2,5**, PRO **3,6**.
5. **Reset/recreate data** before mutation demonstrations so scenario 4 does not distort scenario 5 or the display of other results.
6. Run `mvn exec:java` after all dependencies are merged. Record real console outcomes, not imagined results.

## Part B — Your JUnit ownership (scenario 6 only)

7. Implement meaningful JUnit 5 tests in `PlanScenarioTest.java` verifying plan-based selection through `SubscriberProcessor.findSubscribers`:
   - `Plan.FREE` → IDs **1,4,7**.
   - `Plan.BASIC` → IDs **2,5**.
   - `Plan.PRO` → IDs **3,6**.
   - Use fresh input; assert membership and order, not console output.
8. Members 3,4,5 write scenarios 1–5 in their **own** test classes. Do not duplicate or assume responsibility for them. See [testing responsibilities](../testing-responsibilities.md).

## Part C — Report coordination (shared writing work)

9. Copy [final-report-template.md](../final-report-template.md) to `docs/final-report.md` after implementations are integrated.
10. Collect **3–5 sentences from EACH teammate** explaining the component they wrote; also collect names, GitHub PR links, exact test commands and real results. Members are responsible for providing their own content.
11. Assemble objective, UML link, design choices, seven rules, all six scenarios and verification evidence. Use actual total JUnit tests run, failures/errors and any limitations.
12. Request corrections directly from component owners. Submit the report to the Team Lead for final approval; do not silently invent contributions or test outcomes.

## Dependencies

`Main` and plan scenario tests require the model, DAO/filters/actions and processor to be available. Prepare structure/report outline earlier, but verify integration only when merged.

## Definition of done

- [ ] `Main.main` executes and demonstrates all six named scenarios with correct outputs.
- [ ] **Scenario 6** is covered by meaningful JUnit assertions for all three plans.
- [ ] `mvn exec:java` and integrated `mvn clean verify` actually run successfully (if true); collect evidence.
- [ ] Report truthfully includes all six contributions and shared test evidence, with no unverified placeholders presented as results.
- [ ] Team Lead approves code PR and final report.
