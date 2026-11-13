# Team contribution workflow

**Repository:** https://github.com/jackaldzn-blip/functional-programming-workshop

1. Check your confirmed name, GitHub username and assigned task in [`TEAM.md`](TEAM.md). Anas (Team Lead) will coordinate GitHub Issues. Report any spelling mistakes before starting.
2. Read your specific [member task brief](docs/team-tasks.md), the [Markdown technical UML](docs/technical-uml.md) and [contracts](docs/contracts.md).
3. Clone repository, and create **your own branch from `main`**:

```bash
git clone https://github.com/jackaldzn-blip/functional-programming-workshop.git
cd functional-programming-workshop
git switch -c feature/your-task-name
```

4. Edit files in your assigned ownership area, commit your own changes and push branch:

```bash
git add .
git commit -m "Implement assigned component"
git push -u origin feature/your-task-name
```

5. Open a **Pull Request targeting `main`** and link your Issue. Include: what changed, requirements met, commands run, expected outcomes, limitations, and proof if available.
6. The Team Lead reviews your PR; address feedback on your branch. **Only the Team Lead merges approved PRs into `main` after reviewing CI results.** Members should not self-merge or push directly to `main`, even if GitHub permissions allow it. Do not close **someone else's** PR or take over a member's task without coordinating with the Team Lead; the lead can explicitly authorize a closure or cross-member contribution.
7. Share your PR link, 3–5 sentence contribution summary **and your actual test evidence** with Padma (Member 6) for the final report. Every member supplies content; Member 6 coordinates it.

## Test package layout

Place unit tests in the **same Java package** as the class they test, under `src/test/java`. For example, the model test lives in `com.workshop.model`, DAO tests in `com.workshop.dao`, filter/action tests in `com.workshop.functional`, and processor tests in `com.workshop.service`. Application-level scenario tests can remain in `com.workshop`. Keep `package` declarations consistent with directory paths; Maven/JUnit discovers tests across these packages.

## Collaboration rules

- Don't edit another member's class, signatures or tests without talking first.
- **Final project status:** all components, six scenarios, console and report are complete, and the repository and approved report were submitted to the instructors on 13 November 2026 (confirmed by Padma). See [README progress](README.md#current-progress). The steps above remain a record of the team's contribution workflow.
- Member 1 stabilized the model first. DAO, filters and actions followed, then the processor. Member 6's interactive Main reuses the merged APIs; all six workshop scenario tests are now verified on `main`.
- UML lives in [`docs/technical-uml.md`](docs/technical-uml.md), **not in a separate exported image**.
- Every member writes JUnit tests for their own component. **Six integrated scenario tests are distributed**: Member 3 scenarios 1–2; Member 4 scenarios 4–5; Member 5 scenario 3; Member 6 scenario 6. Member 6 also implements `Main.java` and assembles the **shared** final report using evidence provided by all six; see [testing guide](docs/testing-responsibilities.md). Everyone addresses bugs found in their own code.
- No AI-generated or copied solution should be committed without understanding and team agreement; members are responsible for their implementation.
- A passing compile with **0 tests** is not validation of the workshop behavior.
