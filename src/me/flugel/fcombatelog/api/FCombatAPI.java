package me.flugel.fcombatelog.api;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.entity.Player;

public class FCombatAPI {

    public static boolean isPvpPlayer(Player player){
        me.flugel.fcombatelog.objects.CombatLog byCombat = CombatLog.getInstance().getCombatLogManagers().getByCombat(player);

        if(byCombat != null){
            return true;
        }else return false;
    }

    public static void removePvP(Player player){
        me.flugel.fcombatelog.objects.CombatLog byCombat = CombatLog.getInstance().getCombatLogManagers().getByCombat(player);

        if(byCombat != null){
            CombatLog.getInstance().getCombatLogManagers().remove(byCombat);
        }
    }

    public static void addPvp(Player player, int timer){
        new me.flugel.fcombatelog.objects.CombatLog(player, timer);
    }
}
