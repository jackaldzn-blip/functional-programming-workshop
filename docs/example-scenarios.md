# Example dataset and expected outcomes (no implementation code)

The workshop provides rules but **no example subscriber records** or fixed subscription-extension duration. The following shared dataset is a **team testing convention**, not an instructor-provided dataset. Use these exact seven records when comparing expected outputs, with freshly created objects for each mutation test.

| ID | Email | Plan | Active | Months remaining |
|---:|---|---|---|---:|
| 1 | alex@example.test | FREE | true | 0 |
| 2 | blair@example.test | BASIC | true | 1 |
| 3 | casey@example.test | PRO | true | 6 |
| 4 | devon@example.test | FREE | false | 0 |
| 5 | ellis@example.test | BASIC | false | 1 |
| 6 | frankie@example.test | PRO | true | 0 |
| 7 | gray@example.test | FREE | true | 2 |

## Expected filter results (subscriber IDs in input order)

| Rule | Matching IDs | Explanation |
|---|---|---|
| Active | `1, 2, 3, 6, 7` | Active true |
| Expiring | `1, 2, 4, 5, 6` | Months 0 or 1, including inactive |
| Active and expiring | `1, 2, 6` | Both conditions |
| By plan — FREE | `1, 4, 7` | FREE only |
| By plan — BASIC | `2, 5` | BASIC only |
| By plan — PRO | `3, 6` | PRO only |
| Paying | `2, 3, 5, 6` | BASIC or PRO, including inactive |

## Expected action scenarios

**Scenario 4 — extend subscription for active, paying, expiring users:** eligible IDs are `2, 6` only. With a **team-chosen extension of +3 months**, ID 2 changes `1 → 4`, and ID 6 changes `0 → 3`. All other IDs keep their previous month values. The `applyToMatching` return list contains `2, 6`.

**Scenario 5 — deactivate expired FREE subscribers:** eligible IDs are `1, 4` (both FREE with 0 months). ID 1 changes `active: true → false`; ID 4 remains false (idempotent). All other active states remain unchanged. The `applyToMatching` return list contains `1, 4`.

## Boundary cases Member 6 should test

- 0 months = expiring and expired; 1 month = expiring but not expired; 2 months = not expiring.
- Inactive subscriber may still be expiring; `activeAndExpiring` should not match them.
- `paying()` matches BASIC and PRO, never FREE; it does not by itself require `active`.
- No matches → empty list; action must not run.
- `findById` absent ID → `Optional.empty()`; same ID saved again → update instead of duplicate.
- Returned lists preserve input order; no matches are skipped or processed twice.
- Updating one subscriber via an action must not change any unmatched subscriber.

Tests should use assertions for state and IDs; don't assert solely on printed text from `Main`.
