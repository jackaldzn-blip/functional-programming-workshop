# Final report template — Functional Programming Workshop

**Every member supplies their own implementation summary, PR link and test evidence. Padma (Member 6) assembles this shared report and Anas (Team Lead) approves it.** Do not submit placeholders as results.

## 1. Cover

- Project: Subscription List Management System
- Repository: https://github.com/jackaldzn-blip/functional-programming-workshop
- Course / group: _fill in_
- Submission date: _fill in_
- Team: Anas (@jackaldzn-blip), Veselina (@vlinakonsa), Jay (@JayB111), Tuku (@tukue), Carl (@Matojotam), Padma (@Padmaqaauto). See [TEAM.md](../TEAM.md) for ownership.

## 2. Workshop objective

Explain the supplied conceptual UML, required business rules and optional JUnit challenge chosen by the team.

## 3. Design / architecture

Link to [technical-uml.md](technical-uml.md), explain `Subscriber`, `Plan`, `SubscriberDAO`, `SubscriberFilter`, `SubscriberAction` and `SubscriberProcessor` and additional team helper classes. Explain how functional interfaces/lambdas are used.

## 4. Implementation decisions

Document in-memory storage, validation, duplicate ID policy, `applyToMatching` return policy, example extension duration and any agreed changes to [contracts](contracts.md). Describe Padma's optional console menu separately as a **team enhancement**, not an instructor requirement.

## 5. Scenario verification

| Scenario | Expected outcome | Actual outcome | Evidence (test name / console output) | Status |
|---|---|---|---|---|
| 1 — Active | IDs 1,2,3,6,7 (shared fixture) | _to fill_ | _to fill_ | Not verified |
| 2 — Expiring | IDs 1,2,4,5,6 | _to fill_ | _to fill_ | Not verified |
| 3 — Active + expiring | IDs 1,2,6 | _to fill_ | _to fill_ | Not verified |
| 4 — Extend eligible paid | IDs 2,6; +3 months | _to fill_ | _to fill_ | Not verified |
| 5 — Deactivate expired FREE | IDs 1,4 inactive | _to fill_ | _to fill_ | Not verified |
| 6 — By plan | FREE 1,4,7 / BASIC 2,5 / PRO 3,6 | _to fill_ | _to fill_ | Not verified |

## 6. Interactive console demonstration (team enhancement)

Padma should describe the menu options (`0–8`), the way `Main.java` reuses the team components, and the result of **manual** acceptance checks: valid selections, invalid numbers/text/plans, exit behavior, and whether updates remain visible during the same session. Include the real execution command (`mvn exec:java`) and observed output or transcript. Keep this separate from the original six JUnit scenarios; do not claim unexecuted menu checks passed.

## 7. Test results

Member ownership: scenarios **1–2 by Member 3**, **3 by Member 5**, **4–5 by Member 4**, **6 by Member 6**. Model/DAO tests are written by Members 1–2. Member 6 compiles the actual evidence that each member supplies; see [testing responsibilities](testing-responsibilities.md).

- Java / Maven versions: _to fill_
- Command run: _to fill_
- Number of JUnit tests run: _to fill_
- Failures/errors/skipped: _to fill_
- Any known issues and resolutions: _to fill_

## 8. Individual contributions

| Member | Name | What they implemented | PR link |
|---|---|---|---|
| 1 | Anas (@jackaldzn-blip) | Model + lead duties | _ |
| 2 | Veselina (@vlinakonsa) | DAO | _ |
| 3 | Jay (@JayB111) | Filters | _ |
| 4 | Tuku (@tukue) | Actions | _ |
| 5 | Carl (@Matojotam) | Processor + its tests + scenario 3 | _ |
| 6 | Padma (@Padmaqaauto) | Interactive console menu + scenario 6 + shared report coordination | _ |

## 9. Lessons, limitations and conclusion

Describe real challenges (integration, boundary cases, API coordination), what worked, and what was not implemented.

## 10. References

Cite the workshop Markdown and any legitimately consulted external sources.
