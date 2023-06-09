package me.flugel.fcombatelog.objects;

import org.bukkit.entity.Player;

public class CombatLog {

    private Player player;
    int timer;

    public CombatLog(Player player, int timer) {
        this.player = player;
        this.timer = timer;

        me.flugel.fcombatelog.CombatLog.getInstance().getCombatLogManagers().add(this);
    }

    public Player getPlayer() {
        return player;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void resetTime(){
        setTimer(me.flugel.fcombatelog.CombatLog.TimePvp);
    }
}
