package com.adrian.finished.model.abilities;

/**
 * Functional interface describing how an ability would transform a GameState.
 * Implementations should be pure and return a new immutable GameState.
 */
@FunctionalInterface
public interface AbilityExecutor {
    com.adrian.finished.model.GameState apply(AbilityContext context);

    /**
     * Use AbilityExecutors.withListeners(delegate, listeners) instead.
     */
    @Deprecated(forRemoval = false, since = "1.0")
    static AbilityExecutor withListeners(AbilityExecutor delegate, AbilityExecutionListener... listeners) {
        return AbilityExecutors.withListeners(delegate, listeners);
    }
}