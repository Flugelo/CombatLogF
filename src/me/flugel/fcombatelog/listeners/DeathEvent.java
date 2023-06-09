package me.flugel.fcombatelog.listeners;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathEvent implements Listener {
    @EventHandler
    public void aomorrer(PlayerDeathEvent e){
        me.flugel.fcombatelog.objects.CombatLog byCombat = CombatLog.getInstance().getCombatLogManagers().getByCombat(e.getEntity());
        if(byCombat != null){
            PlayerExitCombatEvent playerExitCombatEvent = new PlayerExitCombatEvent(byCombat.getPlayer(),false);
            Bukkit.getPluginManager().callEvent(playerExitCombatEvent);
            if(CombatLog.drop){
                if(CombatLog.armorStand){
                    CombatLog.getInstance().cadaver(e.getEntity(), e.getDrops().toArray(new ItemStack[0]));
                    e.getDrops().clear();
                }
            }else if(CombatLog.armorStand){
                CombatLog.getInstance().cadaver(e.getEntity(), e.getDrops().toArray(new ItemStack[0]));
            }
        }
    }
}
