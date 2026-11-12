# Member 2 — Veselina — SubscriberDAO / data access

**Assigned member:** Veselina — [@vlinakonsa](https://github.com/vlinakonsa)  
**Suggested branch:** `feature/subscriber-dao`  
**Edit only:** `src/main/java/com/workshop/dao/SubscriberDAO.java`, `src/test/java/com/workshop/SubscriberDaoTest.java`.

## Your goal

Implement a simple in-memory place to store and retrieve `Subscriber` objects. The original UML requires **three** methods: save, findAll and findById. You do **not** need a database, file persistence or console menu.

## Implement in this order

1. Read `Subscriber` fields/getters and the agreed [contracts](../contracts.md). Wait for the model API to be stable.
2. Choose a private in-memory collection for subscribers. Our team contract requires **insertion order**, so a `List` is straightforward.
3. Implement `save(Subscriber subscriber): void`:
   - Reject a null subscriber.
   - New ID → store the subscriber.
   - Existing ID → replace the stored subscriber rather than creating duplicates; keep that ID's position in the list.
4. Implement `findAll(): List<Subscriber>`:
   - Return subscribers in insertion order.
   - Return a new list; modifying the returned collection must **not** remove a stored record.
5. Implement `findById(int id): Optional<Subscriber>`:
   - If ID exists, return `Optional.of(foundSubscriber)` (or equivalent).
   - If it does not, return `Optional.empty()`, never null.
6. Implement JUnit 5 tests in `SubscriberDaoTest.java` for empty storage, missing ID, duplicates, order, and copying the returned list.

## Examples to reason through

- Save IDs 1,2,3 → `findAll` returns 1,2,3.
- `findById(2)` → present subscriber with ID 2.
- `findById(99)` → empty Optional.
- Save another subscriber with ID 2 → `findAll` still has three records, in order 1,2,3; record 2 has the latest values.
- Clear the list returned by `findAll` → DAO's saved list still contains three subscribers.

## Dependencies and boundaries

- Requires Member 1's `Subscriber` API.
- You own your DAO-focused JUnit tests; Scenario tests are shared among Members 3–6; Padma (Member 6) coordinates the final report.
- No SQL, no external dependencies, no method signature changes.

## Shared testing and report evidence

Follow [testing responsibilities](../testing-responsibilities.md). Implement and run your owned JUnit tests. Send Member 6 your PR link, a short implementation explanation and **actual** test results; Member 6 will coordinate the report, not test your code for you.

## Definition of done

- [ ] All three UML operations implemented with correct types.
- [ ] Null, empty, lookup miss, duplicate ID, ordered results covered with JUnit assertions.
- [ ] No database needed; no duplicate stored IDs.
- [ ] `mvn clean compile` passes with merged model; PR explains checks and design choice.
