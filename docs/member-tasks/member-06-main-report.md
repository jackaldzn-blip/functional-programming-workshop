# Member 6 — Padma — Interactive console, scenario 6 and report coordination

**Assigned member:** Padma — [@Padmaqaauto](https://github.com/Padmaqaauto)  
**Initial merged branch:** `feature/main` ([PR #3](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/3)); create a new branch/PR for unfinished work  
**Owned:** `src/main/java/com/workshop/Main.java`, `src/test/java/com/workshop/PlanScenarioTest.java`, `docs/final-report.md` (created from the template).

## Goal

Build a **simple interactive Java console menu** for the workshop using the DAO, processor, filters and actions supplied by your teammates. The team discussed this optional enhancement and **Padma agreed to take responsibility for it**. Implement **one** JUnit workshop scenario (filter by plan), and coordinate the shared final report from everyone's contributions.

**Scope:** Menu and presentation logic only; do not reimplement filters, actions or DAO operations, create a GUI, add a database, or write the other members' tests.

## Part A — Interactive console application in Main.java

Extend the existing Java `public static void main(String[] args)` entry point to display the following menu repeatedly. You may keep the **12-subscriber demonstration list** already present in `Main.java`, or use the [shared seven-subscriber dataset](../example-scenarios.md). The **seven-record shared fixture** is required for the specific expected-ID assertions in scenario tests; do not silently compare the 12-record demo list against those seven-record expectations.

```text
=== Subscription Management System ===
1. Show all subscribers
2. Show active subscribers
3. Show expiring subscriptions
4. Show active and expiring subscribers
5. Find subscribers by plan
6. Show paying subscribers
7. Extend active, paying and expiring subscriptions (+3 months)
8. Deactivate expired FREE subscribers
0. Exit
Enter your choice:
```

### Option-by-option behavior

| Option | What the console should do | Existing code to reuse |
|---|---|---|
| 1. Show all | Print subscribers and their ID, email, plan, active status and remaining months; `Subscriber.toString()` is available for readable output | `SubscriberDAO.findAll()` |
| 2. Show active | Print only active subscribers | `SubscriberFilters.active()` + `SubscriberProcessor.findSubscribers()` |
| 3. Show expiring | Print subscribers with **0 or 1** month remaining, including inactive ones | `SubscriberFilters.expiring()` + processor |
| 4. Active and expiring | Print only active subscribers with **0 or 1** month remaining | `SubscriberFilters.activeAndExpiring()` + processor |
| 5. By plan | Prompt for `FREE`, `BASIC` or `PRO` and print matching subscribers | `SubscriberFilters.byPlan(plan)` + processor |
| 6. Show paying | Print `BASIC` and `PRO` subscribers regardless of active status | `SubscriberFilters.paying()` + processor |
| 7. Extend eligible subscriptions | Select **active AND paying AND expiring** subscribers and extend by **3 months** (team example). Show the affected subscribers and updated months | Compose existing filters and call `SubscriberProcessor.applyToMatching(..., SubscriberActions.extendByMonths(3))` |
| 8. Deactivate expired FREE | Select `Plan.FREE` subscribers with **exactly 0** months remaining and deactivate them; show the affected subscribers | Compose existing filters and call `applyToMatching(..., SubscriberActions.deactivate())` |
| 0. Exit | End the program normally | Console control flow |

**Important:** Option 7 includes **paying**, as required by optional workshop scenario 4. Do not extend every active expiring subscriber, since that would also extend FREE plans. The extra options (show all, show paying, and interactive selections) support the console but do not replace the six workshop scenarios.

### Console behavior and boundaries

1. A simple `Scanner` and `switch` loop in `Main.java` is sufficient; no additional UI framework is needed.
2. Continue showing the menu after each operation until the user chooses `0`.
3. Handle unknown menu numbers, nonnumeric input and an invalid plan without crashing; show a useful message and redisplay the menu.
4. Show a meaningful message when a search has no results, or an action finds nobody eligible.
5. **Changes made by options 7 or 8 remain visible during the same console session.** Do not silently reset the list after each action. No persistence across program restarts is required.
6. For independent scenario demonstrations and JUnit assertions, create **fresh sample data** before each mutation scenario; do not rely on the order that a human selected console options.
7. Print subscribers in a readable format. Anas's `Subscriber.toString()` override is available, so you can print subscriber objects directly without duplicating formatting logic.
8. Keep business rules in the members' functional classes. `Main` is responsible for user input, calling those APIs and showing results, **not duplicating their implementations**.
9. Avoid changing shared method signatures or other members' files without agreement. Use the [agreed contracts](../contracts.md).

### Example initial results (before any changes)

**For the shared seven-record fixture only**, using the documented [shared dataset](../example-scenarios.md) (the existing 12-record `Main` demo will give different IDs):

- Active IDs: **1,2,3,6,7**
- Expiring IDs (0/1 month): **1,2,4,5,6**
- Active AND expiring IDs: **1,2,6**
- Paying IDs: from the plans in the shared fixture (`BASIC` or `PRO`)
- Eligible for +3-month extension: **2 (1→4)** and **6 (0→3)**
- Expired FREE to deactivate: **1,4**
- By plan: FREE **1,4,7**; BASIC **2,5**; PRO **3,6**

After an action runs, later menu results may change. The IDs above describe the **initial** shared dataset, not the permanently expected state of an interactive session.

### Manual console acceptance checks

After all dependencies are integrated, run `mvn exec:java` and verify:

- [ ] A menu appears and returns after selecting option `1`.
- [ ] Options `2`, `3`, `4` and `6` show the correct initial subscribers.
- [ ] Option `5` accepts each of `FREE`, `BASIC` and `PRO` and rejects an invalid plan without exiting.
- [ ] Option `7` changes only eligible active paying expiring subscribers by +3 months.
- [ ] Option `8` deactivates only expired FREE subscribers.
- [ ] Option `1` after an action reflects updated in-session subscriber state.
- [ ] An unknown number and nonnumeric input do not crash the application.
- [ ] Option `0` exits normally.

Record **actual** outputs or a short console transcript for the final report. The console is an **extra team-agreed feature**, not an instructor-mandated UI requirement.

## Part B — JUnit ownership: workshop scenario 6 only

Implement meaningful JUnit 5 tests in `PlanScenarioTest.java` using `SubscriberProcessor.findSubscribers`:

- `Plan.FREE` → IDs **1,4,7**.
- `Plan.BASIC` → IDs **2,5**.
- `Plan.PRO` → IDs **3,6**.

Create fresh test data and assert membership and order. JUnit tests must **not** require keyboard input or depend on console output.

Jay, Tuku and Carl implement workshop scenarios 1–5 in their own test classes. The new console menu does **not** transfer these tests to Padma.

## Part C — Shared report coordination

1. Copy [final-report-template.md](../final-report-template.md) to `docs/final-report.md` once integration is ready.
2. Collect from **each** teammate a 3–5 sentence implementation explanation, PR link and **actual** test results. Everyone writes their own section; you assemble them.
3. Include a short section describing the interactive console extension, its menu options and actual demonstration/acceptance evidence.
4. Describe the original UML, seven business rules and the six JUnit challenge scenarios separately from the optional console enhancement.
5. Request corrections from component owners and submit the report to **Anas** for final review. Do not invent results or claim tests passed without evidence.

## Dependencies

All required model, DAO, filter, action and processor dependencies are **now merged**. Implement the menu against these APIs and record real manual acceptance evidence; a passing compilation by itself does not prove the menu works.

## Definition of done

- [ ] All options `0–8` work through the documented menu and invoke the existing components.
- [ ] Invalid input is handled; action effects persist during the current session.
- [ ] `PlanScenarioTest.java` asserts all three plan categories (workshop scenario 6).
- [ ] Java 25 `mvn exec:java` and `mvn clean verify` run successfully after integration, with actual evidence.
- [ ] All six workshop scenarios are supported through the menu; no teammate's rules or tests have been duplicated.
- [ ] Shared final report includes the console extension, real tests, member contributions and remaining limitations.
- [ ] Pull Request is reviewed and the report approved by the Team Lead.
