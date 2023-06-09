package me.flugel.fcombatelog.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerExitCombatEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private boolean isCancellable;


    public PlayerExitCombatEvent(Player player, boolean isCancellable) {
        this.player = player;
        this.isCancellable = isCancellable;
    }

    public PlayerExitCombatEvent(boolean isAsync, Player player, boolean isCancellable) {
        super(isAsync);
        this.player = player;
        this.isCancellable = isCancellable;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancellable;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancellable = b;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }
}
