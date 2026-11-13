# Team roster and responsibilities

Six members contribute to the **Functional Programming Workshop** (Java 25). The full task breakdown and test expectations are maintained in the [team tasks](docs/team-tasks.md) and [testing guide](docs/testing-responsibilities.md).

| Member | Name | GitHub | Implementation | Tests | Other responsibility | Status |
|---|---|---|---|---|---|---|
| **1 — Lead** | Anas | [@jackaldzn-blip](https://github.com/jackaldzn-blip) | `Subscriber.java`, `Plan.java` | `SubscriberModelTest.java` | Coordination, reviews and integration | **Coding complete** — model/tests merged in [#1](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/1) and [#4](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/4); final report approved via [#21](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/21) |
| **2** | Veselina | [@vlinakonsa-svg](https://github.com/vlinakonsa-svg) | `SubscriberDAO.java` | `SubscriberDaoTest.java` | Data-access checks | DAO and tests merged |
| **3** | Jay | [@JayB111](https://github.com/JayB111) | `SubscriberFilter.java`, `SubscriberFilters.java` | `SubscriberFiltersTest.java`; scenarios 1–2 | Selection rules | Filters + 15 tests merged in [#10](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/10); scenarios 1–2 merged in [#18](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/18) |
| **4** | Tuku | [@tukue](https://github.com/tukue) | `SubscriberAction.java`, `SubscriberActions.java` | `SubscriberActionsTest.java`; scenarios 4–5 | Subscription updates | Actions merged in [#8](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/8); scenarios 4–5 passed via #11 |
| **5** | Carl | [@Matojotam](https://github.com/Matojotam) | `SubscriberProcessor.java` | `SubscriberProcessorTest.java`; scenario 3 | Processing and integration | Processor + scenario 3 merged in [#11](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/11) |
| **6** | Padma | [@Padmaqaauto](https://github.com/Padmaqaauto) | `Main.java` | `PlanScenarioTest.java`; scenario 6 | Console demonstration and shared report coordination | Console menu + scenario 6 merged in [#17](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/17); final report approved and merged via [#21](https://github.com/jackaldzn-blip/functional-programming-workshop/pull/21) |

**Progress:** All six scenarios and the interactive console are merged; **53 tests passed** in Java 25 CI on 13 November 2026. See [README.md](README.md#current-progress). The [final report](docs/final-report.md) was approved in PR #21.

Status refers to the **assigned work**, not overall project completion. Contributions and reviews are tracked through GitHub branches and pull requests.

## Working together

Each member implements their own component, writes the assigned tests, and submits work through a pull request for review. Everyone supplies a short implementation summary and **actual** test results for the shared report. Padma assembles the report from all six contributions; Anas conducts the final review.

See the [individual task briefs](docs/team-tasks.md) and [contribution workflow](CONTRIBUTING.md) for instructions.
