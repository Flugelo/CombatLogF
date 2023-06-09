package me.flugel.fcombatelog.listeners;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CancelCommand implements Listener {
    @EventHandler
    public void cancel(PlayerCommandPreprocessEvent e){
        me.flugel.fcombatelog.objects.CombatLog byCombat = CombatLog.getInstance().getCombatLogManagers().getByCombat(e.getPlayer());

        if(byCombat != null){
            for (String blockCommand : CombatLog.getInstance().getBlockCommands()) {
                if(e.getMessage().contains(blockCommand)){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(CombatLog.NotCommandExecuter);
                }
            }
        }
    }
}
