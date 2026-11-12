# Shared Java API contracts — agreed team design

**Read this before coding.** These method signatures are the team implementation contract, guided by the [technical UML](technical-uml.md); the workshop itself supplies only the conceptual signatures of the six constructs. Do not implement someone else's file or change a signature without agreeing with the lead and consumers.

## Package and required API

**Member 1: `com.workshop.model`**

- `enum Plan { FREE, BASIC, PRO }`
- `class Subscriber` with five **private** fields: `int id`, `String email`, `Plan plan`, `boolean active`, `int monthsRemaining`.
- Public constructor `Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining)`.
- Getters: `getId(): int`, `getEmail(): String`, `getPlan(): Plan`, `isActive(): boolean`, `getMonthsRemaining(): int`.
- Setters: `setEmail(String): void`, `setPlan(Plan): void`, `setActive(boolean): void`, `setMonthsRemaining(int): void`; subscriber ID does not change after construction.
- Team validation: `id > 0`, nonblank email, non-null plan, `monthsRemaining >= 0`. Prefer `IllegalArgumentException` for invalid values. Keep it simple; no email regex needed.

**Member 2: `com.workshop.dao`**

- `class SubscriberDAO` with `save(Subscriber): void`, `findAll(): List<Subscriber>`, `findById(int): Optional<Subscriber>`.
- Store in memory; no external database or files. `save` updates existing ID or appends new ID. Preserve initial position when updating.
- `findAll` returns a **new list** in insertion order (but the subscribers themselves may remain mutable). `findById` returns empty if not found. Reject a null `save` argument.

**Member 3: `com.workshop.functional`**

- `@FunctionalInterface interface SubscriberFilter { boolean matches(Subscriber subscriber); }` exactly **one** abstract method.
- `class SubscriberFilters` with static factory methods returning `SubscriberFilter`:
  - `active()` → `subscriber.isActive()`
  - `expiring()` → remaining months **exactly 0 or 1**
  - `activeAndExpiring()` → active AND months 0 or 1
  - `byPlan(Plan plan)` → matches given enum plan
  - `paying()` → BASIC OR PRO
- Return reusable predicates (e.g., lambdas); no printing and **no modification** of subscribers in filter methods.

**Member 4: `com.workshop.functional`**

- `@FunctionalInterface interface SubscriberAction { void run(Subscriber subscriber); }` exactly **one** abstract method.
- `class SubscriberActions` with static factory methods:
  - `extendByMonths(int months): SubscriberAction` → add `months` to `monthsRemaining` when called; require `months > 0`.
  - `deactivate(): SubscriberAction` → change `active` to `false` when called.
- Return lambda-compatible actions; do not perform actions when creating the action, only when `run` is invoked.

**Member 5: `com.workshop.service`**

- `class SubscriberProcessor` with:
  - `findSubscribers(List<Subscriber> list, SubscriberFilter filter): List<Subscriber>` → select matches; return **new list** in input order; do not mutate subscribers.
  - `applyToMatching(List<Subscriber> list, SubscriberFilter filter, SubscriberAction action): List<Subscriber>` → for each **originally matching** subscriber (evaluate condition once per subscriber), call `action.run` exactly once. Return a **new list containing only processed subscribers**, in input order; do not add/remove entries from original input list.

**Member 6: `com.workshop.Main` and scenario 6 test**

- Member 6 implements `Main.main(String[] args)` to display all six original scenarios, using the other components instead of reimplementing their rules. Main can construct fresh sample data before each mutation scenario to keep results reproducible.
- Member 6 implements `PlanScenarioTest.java` to assert scenario 6 (FREE, BASIC, PRO) through `findSubscribers`.

**JUnit responsibilities (all members code and test)**

- Member 1 owns `SubscriberModelTest.java`; Member 2 owns `SubscriberDaoTest.java`.
- Member 3 owns `SubscriberFiltersTest.java` (**scenarios 1,2**). Member 4 owns `SubscriberActionsTest.java` (**scenarios 4,5**). Member 5 owns `SubscriberProcessorTest.java` (**scenario 3**).
- Member 6 owns `PlanScenarioTest.java` (**scenario 6**) and the runnable Main demonstration; they coordinate the report using every member's evidence. **No one member is responsible for all testing.**
- Full expectations: [testing-responsibilities.md](testing-responsibilities.md).
- Use JUnit 5 (`@Test`, `Assertions`). Tests must create fresh subscribers and never depend on test execution order or console output.

## Important agreed behaviors

- **Expired** means exactly **0 months** remaining. **Expiring** means **0 or 1 month**, including already inactive subscribers unless explicitly combined with active.
- Paying means `Plan.BASIC` or `Plan.PRO` regardless of active flag. The fourth scenario explicitly combines **active AND paying AND expiring**.
- `applyToMatching` returns *matched, processed subscribers*, not all subscribers. This is a team interpretation, because the UML does not define the returned list's content.
- Extend by **3 months in the shared example dataset**; 3 is a **team example**, not a duration required by the assignment.
- This exercise uses mutable Subscriber objects for the provided action interface; lists are copied structurally, not deep-cloned.
- Null `list`, `filter`, `action`, plan factory inputs should be rejected consistently where applicable (e.g., `Objects.requireNonNull`). Avoid introducing an unnecessary framework.
