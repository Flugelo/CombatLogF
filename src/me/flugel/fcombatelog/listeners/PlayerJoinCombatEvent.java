package me.flugel.fcombatelog.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinCombatEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private Player target;
    private boolean isCancellable;

    public PlayerJoinCombatEvent(Player player, Player target) {
        this.player = player;
        this.target = target;
        this.isCancellable = false;
    }

    public PlayerJoinCombatEvent(boolean isAsync, Player player, Player target) {
        super(isAsync);
        this.player = player;
        this.target = target;
        this.isCancellable = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancellable;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancellable = b;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
