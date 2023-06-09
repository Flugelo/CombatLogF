package me.flugel.fcombatelog.listeners;

import me.flugel.fcombatelog.CombatLog;
import me.flugel.fcombatelog.objects.Corpo;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class InterectCadaver implements Listener {
    @EventHandler
    public void aoCliclar(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            Corpo byCorpoEntity = CombatLog.getInstance().getCorpoManager().getByCorpoEntity(e.getRightClicked());
            if (byCorpoEntity != null) {
                e.setCancelled(true);
                ItemStack[] itens = byCorpoEntity.getItens().clone();
                for (ItemStack iten : itens) {
                    byCorpoEntity.getLocation().getWorld().dropItem(byCorpoEntity.getLocation(), iten);
                }
                byCorpoEntity.deleted();
            }
        }
    }
}
