package com.adrian.finished.model.abilities;

import com.adrian.finished.model.GameState;

import java.util.List;

/**
 * Provides synchronous, UI-agnostic decisions required by certain manual abilities.
 *
 * Implementations may block until the user provides input (e.g., reading from console
 * or waiting for a UI selection). Ability executors can use this interface to obtain
 * required selections without knowing which UI is driving the game.
 *
 * The API is intentionally minimal; extend with new methods only when a concrete
 * interaction is needed by abilities. Keep methods deterministic and side-effect free
 * except for synchronously waiting for input.
 */
public interface DecisionProvider {
    /**
     * Selects {@code count} cards from the Present area by index (0-based), relative to the
     * current order in {@link GameState#present()}.
     *
     * Contract:
     * - Must return exactly {@code count} distinct indices in range [0, present.size()).
     * - Indices should be unique and in any order (executor decides usage order).
     * - Implementations may block until selection is made.
     */
    List<Integer> selectPresentCardIndices(GameState state, int count);
}
