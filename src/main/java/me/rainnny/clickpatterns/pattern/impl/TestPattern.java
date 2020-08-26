package me.rainnny.clickpatterns.pattern.impl;

import me.rainnny.clickpatterns.pattern.ClickPattern;
import me.rainnny.clickpatterns.pattern.ClickType;
import org.bukkit.entity.Player;

/**
 * @author Braydon
 * This is a test click pattern. To activate this click pattern in-game, you must
 * click left twice, right twice, and then left again
 */
public class TestPattern extends ClickPattern {
    public TestPattern() {
        super(ClickType.LEFT, ClickType.LEFT, ClickType.RIGHT, ClickType.RIGHT, ClickType.LEFT);
    }

    @Override
    public void run(Player player) {
        player.sendMessage("You used the test pattern!");
    }
}