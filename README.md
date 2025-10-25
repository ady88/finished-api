# finished-core

An API-only Java module that defines immutable model types and small abstractions (interfaces, enums) for the "Finished" card game engine. This project intentionally contains no runtime implementations or game logic — just records, interfaces, enums, and simple utilities to help you build engines and UIs on top.

- Immutability-first: domain objects are Java records or expose data read-only by design.
- UI-agnostic: events and decision prompts are modeled via minimal interfaces.
- No tests or runtime implementations are included by design.

## Contents
- Model (records): Card, DrawStack, PresentArea, PastArea, FutureArea, FinishedPile, GameState
- Abilities:
  - AbilitySpec (enum) — catalog of abilities and their metadata
  - AbilityPhase (enum) — lifecycle/phase classification
  - AbilityContext (record) — state + ability + decision provider
  - AbilityExecutor (functional interface) — pure transform: AbilityContext → GameState
  - AbilityExecutors (utility) — decorators (sync/async listeners)
  - AbilityExecutionListener (interface) — receive post-execution events
  - AbilityExecutedEvent (record) — immutable payload for listener callbacks
  - DecisionProvider (interface) — obtain user choices synchronously
  - DecisionProviders (utility) — default no-op provider
- OS
  - OSConfigStrategy (interface) — small example, unrelated to core engine

## Requirements
- Java 17+ (records, sealed APIs supported)
- Maven (to build)

## Build
```bash
mvn -q -e -DskipTests package
```

## Java Modules
A Java module descriptor is included:

```java
module com.adrian.finished.core {
    exports com.adrian.finished.model;
    exports com.adrian.finished.model.abilities;
    exports com.adrian.finished.os;
}
```

If you consume this via modules, depend on the module name `com.adrian.finished.core`.

## Quick usage
- Wrap an executor to publish UI events after each ability execution:
```java
AbilityExecutor base = ctx -> ctx.state(); // your pure implementation
AbilityExecutionListener ui = evt -> System.out.println("After: " + evt.after());
AbilityExecutor exec = AbilityExecutors.withListeners(base, ui);
```

- Provide interactive choices (terminal or GUI) via DecisionProvider:
```java
DecisionProvider provider = DecisionProviders.noOp(); // replace with your UI-backed implementation
GameState next = exec.apply(new AbilityContext(state, AbilitySpec.CARDS_INTO_PAST, provider));
```

## Notes
- This module is API-only. Engine rules, persistence, or UI implementations belong in separate modules.
- Collections returned by records should be treated as immutable by callers.
