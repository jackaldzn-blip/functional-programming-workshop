# Project status — 12 November 2026

This is a **dated, verified snapshot** of the `main` branch, not a prediction of final submission readiness. Recheck GitHub PRs and CI before relying on it later.

**Baseline:** `main` at [`a2207d7ca470`](https://github.com/jackaldzn-blip/functional-programming-workshop/commit/a2207d7ca470d18e40cf64e7c59a298432f407a5). Its [Java 25 CI run](https://github.com/jackaldzn-blip/functional-programming-workshop/actions/runs/43246869717) passed `mvn -B --no-transfer-progress clean verify` with **48 tests, zero failures, zero errors, zero skipped**. No PR was open at the time of this snapshot.

## Components and member ownership

| Member | Merged evidence | Verified status | Next action |
|---|---|---|---|
| **1 — Anas** | [PR #1](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/1), [#4](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/4), [#9](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/9) | Subscriber/Plan model, `toString()`, **12 model tests**, CI coordination | Lead final reviews; send report contribution evidence |
| **2 — Veselina** | [PR #6](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/6) | In-memory DAO, **8 DAO tests** | Send report contribution evidence |
| **3 — Jay** | [PR #10](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/10) | Five reusable filters, **15 filter unit tests** | Add integrated scenario tests **1–2** in `SubscriberFiltersTest.java`; send report evidence |
| **4 — Tuku** | [PR #8](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/8); scenario tests included in [#11](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/11) | Actions, optional `activate()` helper, **9 action tests including scenarios 4–5** | Send report contribution evidence; do not take over another member's scenario |
| **5 — Carl** | [PR #11](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/11) | Processor, **4 processor tests including scenario 3** | Send report contribution evidence |
| **6 — Padma** | [PR #3](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/3) | Initial `Main.java` prints 12 sample subscribers; **no scenario-6 test on `main`** | Implement scenario **6**, complete agreed console menu, assemble `docs/final-report.md` from everyone's evidence |

**Test totals on `main`:** model 12 + DAO 8 + filters 15 + actions 9 + processor 4 = **48**.

## Six workshop scenarios

| Scenario | Owner | State on `main` | Evidence or remaining work |
|---|---|---|---|
| **1 — Active** | Jay | **Pending** | Add integration test with `SubscriberProcessor.findSubscribers`; expect IDs `1,2,3,6,7` |
| **2 — Expiring** | Jay | **Pending** | Add integration test; expect IDs `1,2,4,5,6` including inactive subscribers |
| **3 — Active + expiring** | Carl | **Passed** | `scenario3FindsActiveAndExpiringSubscribers` in `SubscriberProcessorTest` |
| **4 — Extend eligible paid** | Tuku | **Passed** | `scenario4ExtendsOnlyActivePayingExpiringSubscribers` in `SubscriberActionsTest` |
| **5 — Deactivate expired FREE** | Tuku | **Passed** | `scenario5DeactivatesOnlyExpiredFreeSubscribers` in `SubscriberActionsTest` |
| **6 — By plan** | Padma | **Pending on main** | `PlanScenarioTest.java` is an empty shell on `main`; add assertions for FREE/BASIC/PRO |

Tuku's [PR #13](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/13) for scenario 6 **passed 49 tests on its branch**, but was **closed without merging**, and its remote branch was subsequently deleted. That test is **not** part of the 48 passing tests on `main`. Scenario 6 remains Padma's task. [PR #12](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/12) was also closed without merging; scenarios 4–5 are already included through PR #11.

## Final integration and submission

- **Jay:** add scenarios 1–2 in her owned test class and open a PR for lead review.
- **Padma:** implement scenario 6 in `src/test/java/com/workshop/PlanScenarioTest.java` (**keep it in the `com.workshop` test root**), complete the agreed interactive console menu `0–8`, demonstrate it with evidence, and prepare `docs/final-report.md` using the [template](final-report-template.md).
- **All six members:** send Padma a short explanation of their individual contribution, links to their merged PR(s), and actual test names/results. Do not invent manual testing or report evidence.
- **Team Lead:** review the remaining PRs, run full `mvn clean verify`, manually check console behavior, verify all six scenarios and the final report, then approve submission.

## Workflow and scope

Members push their own branches and **open PRs**. The **Team Lead handles final merges** and coordinates decisions to close another member's PR. Help across components is welcome with the owner and lead informed, but does not transfer task ownership. Follow [CONTRIBUTING.md](../CONTRIBUTING.md).

The original [workshop assignment](../assignment/Functional_Programming_Workshop.md) is unchanged. The interactive console is a **team-agreed extra**, not part of the supplied conceptual UML. The original assignment's optional Part 2 scenarios are goals the team elected to implement.
