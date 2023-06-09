package me.flugel.fcombatelog.listeners;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageByEntityEvent implements Listener {
    @EventHandler
    public void aoHitar(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        if (e.getEntity().getCustomName() != null) {
            if (e.getEntity() instanceof ArmorStand) {
                if (e.getEntity().getCustomName().equals("armorstand25565170102"))
                    e.setCancelled(true);
                return;
            }
        }

        if (e.getDamager() instanceof Player)
            if (e.getEntity() instanceof Player) {
                Player target = (Player) e.getEntity();
                Player player = (Player) e.getDamager();

                PlayerJoinCombatEvent playerJoinCombatEvent = new PlayerJoinCombatEvent(player, target);
                Bukkit.getPluginManager().callEvent(playerJoinCombatEvent);

                if (!playerJoinCombatEvent.isCancelled()) {

                    if (player.hasPermission("fcombat.staff")) {
                        return;
                    }

                    me.flugel.fcombatelog.objects.CombatLog byCombat = CombatLog.getInstance().getCombatLogManagers().getByCombat(target);
                    me.flugel.fcombatelog.objects.CombatLog byplayer = CombatLog.getInstance().getCombatLogManagers().getByCombat(player);


                    if (byCombat != null) {
                        byCombat.resetTime();
                    } else {
                        CombatLog.getInstance().addCombatLog(target, player);
                    }

                    if (byplayer != null) {
                        byplayer.resetTime();
                    } else {
                        CombatLog.getInstance().addCombatLog(player, target);
                    }
                }

            }
    }
}
