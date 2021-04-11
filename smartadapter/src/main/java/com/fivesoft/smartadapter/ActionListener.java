package com.fivesoft.smartadapter;

/**
 * Interface to handle events on {@link Item}.
 * @param <T> Type of {@link Item} data.
 */

public interface ActionListener<T> {

    /**
     * Example action. Use it in {@link #onAction(int, int, Object)}
     * when user clicks the item.
     */

    int ACTION_CLICK = 1;

    /**
     * Example action. Use it in {@link #onAction(int, int, Object)}
     * when user long clicks the item.
     */

    int ACTION_LONG_CLICK = 2;

    /**
     * Example action. Use it in {@link #onAction(int, int, Object)}
     * when user swipes the item to the left.
     */

    int ACTION_SWIPED_LEFT = 3;

    /**
     * Example action. Use it in {@link #onAction(int, int, Object)}
     * when user swipes the item to the right.
     */

    int ACTION_SWIPED_RIGHT = 4;

    /**
     * Should be called when something happened on {@link Item}.
     * <br><br>
     * Check example actions:
     * <ul>
     *     <li>{@link #ACTION_CLICK}</li>
     *     <li>{@link #ACTION_LONG_CLICK}</li>
     *     <li>{@link #ACTION_SWIPED_LEFT}</li>
     *     <li>{@link #ACTION_SWIPED_RIGHT}</li>
     * </ul>
     * @param action Event id. Use this param to
     * @param position Position of item where action took place.
     * @param data It is recommended to simply put here {@link Item#getData()} method.
     */

    void onAction(int action, int position, T data);

}
