package me.rainnny.clickpatterns;

import me.rainnny.clickpatterns.pattern.ClickPatternManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Braydon
 */
public class ClickPatterns extends JavaPlugin {
    @Override
    public void onEnable() {
        new ClickPatternManager(this);
    }
}