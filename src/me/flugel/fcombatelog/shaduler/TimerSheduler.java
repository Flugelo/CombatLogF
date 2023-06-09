package me.flugel.fcombatelog.shaduler;

import me.flugel.fcombatelog.CombatLog;
import me.flugel.fcombatelog.listeners.PlayerExitCombatEvent;
import me.flugel.fcombatelog.objects.Corpo;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerSheduler extends BukkitRunnable {
    @Override
    public void run() {
        for (int i = 0; i < CombatLog.getInstance().getCombatLogManagers().getCombatLogList().size(); i++) {
            me.flugel.fcombatelog.objects.CombatLog combatLog = CombatLog.getInstance().getCombatLogManagers().getCombatLogList().get(i);
            if(combatLog.getTimer() > 0){
                combatLog.setTimer(combatLog.getTimer() - 1);
                Player player = combatLog.getPlayer();
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(CombatLog.Timer.replace("(timer)", String.valueOf(combatLog.getTimer()))));
            }else {
                Player player = combatLog.getPlayer();
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(CombatLog.ExitPvp));
                CombatLog.getInstance().getCombatLogManagers().remove(combatLog);
                PlayerExitCombatEvent playerExitCombatEvent = new PlayerExitCombatEvent(player, false);
                Bukkit.getPluginManager().callEvent(playerExitCombatEvent);
            }
        }

        for (int i = 0; i < CombatLog.getInstance().getCorpoManager().getCorpos().size(); i++) {
            Corpo corpo = CombatLog.getInstance().getCorpoManager().getCorpos().get(i);
            if(corpo.getTimer() > 0){
                 corpo.setTimer(corpo.getTimer() - 1);
            }else{
                ItemStack[] itens = corpo.getItens();
                Location location = corpo.getLocation().clone();
                for (ItemStack iten : itens) {
                    Bukkit.getScheduler().runTask(CombatLog.getInstance(), () ->location.getWorld().dropItem(location, iten));
                }
                corpo.deleted();
            }
        }
    }
}
