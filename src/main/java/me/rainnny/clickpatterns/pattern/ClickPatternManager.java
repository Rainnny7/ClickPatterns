package me.rainnny.clickpatterns.pattern;

import me.rainnny.clickpatterns.pattern.impl.TestPattern;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;

/**
 * @author Braydon
 */
public class ClickPatternManager implements Listener {
    private final List<ClickPattern> patterns = new ArrayList<>();

    public ClickPatternManager(JavaPlugin plugin) {
        // Adding all of our click patterns into a list so they can be
        // used later on
        patterns.add(new TestPattern());

        // Registering our class as a Bukkit listener
        getPluginManager().registerEvents(this, plugin);
    }

    /**
     * When a player interacts, we loop through all of
     * our click patterns to get the player's progress
     * for that pattern. Once we have the progress, we get
     * the next click type and then we compare it with the
     * player's given click type. If the player's click type
     * and the expecting click type are different, we reset
     * the player's progress so they can start again. If the
     * player's click type is the same as the expecting click
     * type, we increment their progress with the {@code pattern.increment}
     * method
     */
    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ClickType clickType = ClickType.of(event.getAction().name());
        if (clickType == null)
            return;
        for (ClickPattern pattern : patterns) {
            int progress = pattern.getProgress(player);
            ClickType nextClick = pattern.getNext(progress);

            if (clickType != nextClick)
                pattern.reset(player);
            else pattern.incrementProgress(player);

            if (pattern.hasReached(player)) {
                pattern.run(player);
                pattern.reset(player);
            }
        }
    }

    /**
     * When a player leaves the server, we loop through all
     * of our click patterns to remove their progress
     */
    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        for (ClickPattern pattern : patterns) {
            pattern.reset(event.getPlayer());
        }
    }
}