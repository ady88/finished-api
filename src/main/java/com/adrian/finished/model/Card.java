package com.adrian.finished.model;

/**
 * Immutable representation of a playing card in Finished! game.
 * Cards are numbered from 1 to 48.
 *
 * Additional fields support per-card ability usage caps:
 * - abilitiesTriggered: how many times abilities on this card were triggered this turn.
 * - maxAbilities: the maximum number of times abilities on this card may be triggered this turn.
 *   Example: card 47 (drawOne3x) can be triggered up to 3 times; most others are 0 or 1.
 */
public record Card(int number, int abilitiesTriggered, int maxAbilities) {
    public Card {
        if (number < 1 || number > 48) {
            throw new IllegalArgumentException("Card number must be between 1 and 48, got: " + number);
        }
        if (maxAbilities < 0) {
            throw new IllegalArgumentException("maxAbilities cannot be negative, got: " + maxAbilities);
        }
        if (abilitiesTriggered < 0) {
            throw new IllegalArgumentException("abilitiesTriggered cannot be negative, got: " + abilitiesTriggered);
        }
        if (abilitiesTriggered > maxAbilities) {
            throw new IllegalArgumentException("abilitiesTriggered (" + abilitiesTriggered + ") cannot exceed maxAbilities (" + maxAbilities + ")");
        }
    }
}