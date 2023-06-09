package me.flugel.fcombatelog.listeners;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ExitServer implements Listener {
    @EventHandler
    public void aoSair(PlayerQuitEvent e){
        me.flugel.fcombatelog.objects.CombatLog byCombat = CombatLog.getInstance().getCombatLogManagers().getByCombat(e.getPlayer());

        if(byCombat != null){
            PlayerExitCombatEvent playerExitCombatEvent = new PlayerExitCombatEvent(byCombat.getPlayer(),false);
            Bukkit.getPluginManager().callEvent(playerExitCombatEvent);

            CombatLog.getInstance().getCombatLogManagers().remove(byCombat);

            if(CombatLog.mensage){
                Bukkit.broadcastMessage(CombatLog.ExitServer.replace("(player)", e.getPlayer().getName()));
            }

            if(CombatLog.command){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), CombatLog.comando.replace("(player)", e.getPlayer().getName()));
            }

            if(CombatLog.drop){
                e.getPlayer().setHealth(0);
            }
        }
    }
}
