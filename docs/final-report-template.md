# Final report template — Functional Programming Workshop

**Every member supplies their own implementation summary, PR link and test evidence. Padma (Member 6) assembles this shared report and Anas (Team Lead) approves it.** Do not submit placeholders as results.

**Verified implementation baseline (13 November 2026):** [README progress](../README.md#current-progress) records **53 passing tests on `main`** with scenarios 1–6 covered and the console merged. Padma has marked the manual console checks complete. This template is **not** the completed team report: console transcript, individual member write-ups and final approval are still required.

## 1. Cover

- Project: Subscription List Management System
- Repository: https://github.com/jackaldzn-blip/functional-programming-workshop
- Course / group: _fill in_
- Submission date: _fill in_
- Team: Anas (@jackaldzn-blip), Veselina (@vlinakonsa-svg), Jay (@JayB111), Tuku (@tukue), Carl (@Matojotam), Padma (@Padmaqaauto). See [TEAM.md](../TEAM.md) for ownership.

## 2. Workshop objective

Explain the supplied conceptual UML, required business rules and optional JUnit challenge chosen by the team.

## 3. Design / architecture

Link to [technical-uml.md](technical-uml.md), explain `Subscriber`, `Plan`, `SubscriberDAO`, `SubscriberFilter`, `SubscriberAction` and `SubscriberProcessor` and additional team helper classes. Explain how functional interfaces/lambdas are used.

## 4. Implementation decisions

Document in-memory storage, validation, duplicate ID policy, `applyToMatching` return policy, example extension duration and any agreed changes to [contracts](contracts.md). Include team enhancements, such as the console demonstration, without presenting them as original workshop requirements.

## 5. Scenario verification

| Scenario | Expected outcome | Actual outcome | Evidence (test name / console output) | Status |
|---|---|---|---|---|
| 1 — Active | IDs 1,2,3,6,7 | IDs 1,2,3,6,7 | `SubscriberProcessorTest.scenario1FindsActiveSubscribers` | Verified in 13 Nov `main` CI |
| 2 — Expiring | IDs 1,2,4,5,6 | IDs 1,2,4,5,6 | `SubscriberProcessorTest.scenario2FindsExpiringSubscribers` | Verified in 13 Nov `main` CI |
| 3 — Active + expiring | IDs 1,2,6 | IDs 1,2,6 | `SubscriberProcessorTest.scenario3FindsActiveAndExpiringSubscribers` | Verified in 13 Nov `main` CI |
| 4 — Extend eligible paid | IDs 2,6; +3 months | IDs 2,6; months 1→4 and 0→3 | `SubscriberActionsTest.scenario4ExtendsOnlyActivePayingExpiringSubscribers` | Verified in 13 Nov `main` CI |
| 5 — Deactivate expired FREE | IDs 1,4 inactive | IDs 1,4 processed; inactive afterward | `SubscriberActionsTest.scenario5DeactivatesOnlyExpiredFreeSubscribers` | Verified in 13 Nov `main` CI |
| 6 — By plan | FREE 1,4,7 / BASIC 2,5 / PRO 3,6 | FREE 1,4,7 / BASIC 2,5 / PRO 3,6 | `PlanScenarioTest.shouldFindFreeSubscribers`, `shouldFindBasicSubscribers`, `shouldFindProSubscribers` | Verified in 13 Nov `main` CI |

## 6. Test results

Member ownership: scenarios **1–2 by Member 3**, **3 by Member 5**, **4–5 by Member 4**, **6 by Member 6**. Model/DAO tests are written by Members 1–2. Member 6 compiles the actual evidence that each member supplies; see [testing responsibilities](testing-responsibilities.md).

- Java: **25** (verified by GitHub Actions); Maven version for local runs: _add if recorded_
- CI command: `mvn -B --no-transfer-progress clean verify`
- JUnit tests on merged `main`: **53**
- Failures/errors/skipped: **0/0/0**
- Known issues and resolutions: _add member-authored account_

**Verified CI evidence:** GitHub Actions [run #43396578998](https://github.com/jackaldzn-blip/functional-programming-workshop/actions/runs/43396578998) executed Java 25 `mvn -B --no-transfer-progress clean verify` after PR #17 merged: **53 tests passed, 0 failures, 0 errors, 0 skipped**. The report must still include actual manual console evidence, six individual write-ups and Team Lead approval.

## 7. Individual contributions

| Member | Name | What they implemented | PR link |
|---|---|---|---|
| 1 | Anas (@jackaldzn-blip) | Model + lead duties | [#1](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/1), [#4](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/4), [#9](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/9) |
| 2 | Veselina (@vlinakonsa-svg) | DAO | [#6](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/6) |
| 3 | Jay (@JayB111) | Filters and scenarios 1–2 | [#10](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/10), [#18](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/18) |
| 4 | Tuku (@tukue) | Actions and scenarios 4–5 | [#8](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/8), [#11](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/11) |
| 5 | Carl (@Matojotam) | Processor + its tests + scenario 3 | [#11](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/11) |
| 6 | Padma (@Padmaqaauto) | Interactive console and scenario 6; report coordination pending | [#3](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/3), [#17](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/17) |

## 8. Lessons, limitations and conclusion

Describe real challenges (integration, boundary cases, API coordination), what worked, and what was not implemented.

## 9. References

Cite the workshop Markdown and any legitimately consulted external sources.