package me.rainnny.clickpatterns.pattern;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Braydon
 */
@Getter
public abstract class ClickPattern {
    private final ClickType[] clickTypes;

    /**
     * The progress map keeps track of all of progress each player has
     * for this pattern. If the player gets a click type correct, their
     * progress will be incremented by one. Once the player's progress
     * reaches the size of {@code clickTypes}, it will run the
     * {@code run} method
     */
    private final Map<Player, Integer> progress = new HashMap<>();

    public abstract void run(Player player);

    /**
     * @param clickTypes - The clicks the player must complete for the {@code run} method to be called
     */
    public ClickPattern(ClickType... clickTypes) {
        this.clickTypes = clickTypes;
    }

    /**
     * Returns the next click type in the {@code clickTypes} array, null if none
     * @param progress - The current progress
     * @return the next click type in the {@code clickTypes} array
     */
    public ClickType getNext(int progress) {
        if (progress >= clickTypes.length)
            return null;
        return clickTypes[progress];
    }

    /**
     * This will increment the provided player's progress by one
     * @param player - The player you would like to increment progress for
     */
    public void incrementProgress(Player player) {
        progress.put(player, getProgress(player) + 1);
    }

    /**
     * Returns whether or not the provided player has reached the required amount of
     * progress
     * @param player - The player you would like to check
     * @return whether or not the provided player has reached the required amount of progress
     */
    public boolean hasReached(Player player) {
        return getProgress(player) >= clickTypes.length;
    }

    /**
     * Returns the provided player's progress
     * @param player - The player you would like to get progress for
     * @return the progress of the provided player
     */
    public int getProgress(Player player) {
        return progress.getOrDefault(player, 0);
    }

    /**
     * Resets the provided player's progress
     * @param player - The player you would like to reset progress for
     */
    public void reset(Player player) {
        progress.remove(player);
    }
}