package me.rainnny.clickpatterns.pattern;

/**
 * @author Braydon
 * This class can be added onto in the future to allow the use
 * of other click types, such as shifting
 */
public enum ClickType {
    LEFT, RIGHT;

    public static ClickType of(String actionName) {
        switch (actionName) {
            case "LEFT_CLICK_BLOCK":
            case "LEFT_CLICK_AIR": {
                return LEFT;
            }
            case "RIGHT_CLICK_BLOCK":
            case "RIGHT_CLICK_AIR": {
                return RIGHT;
            }
            default: {
                return null;
            }
        }
    }
}