package me.flugel.fcombatelog.managers;

import me.flugel.fcombatelog.objects.CombatLog;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CombatLogManagers {
    private List<me.flugel.fcombatelog.objects.CombatLog> combatLogList;

    public CombatLogManagers(){
        this.combatLogList = new ArrayList<>();
    }

    public List<CombatLog> getCombatLogList() {
        return combatLogList;
    }

    public me.flugel.fcombatelog.objects.CombatLog getByCombat(Player player){
        return this.combatLogList.stream().filter(p -> p.getPlayer().equals(player)).findFirst().orElse(null);
    }

    public void add(CombatLog player){
        this.combatLogList.add(player);
    }

    public void remove(CombatLog player){
        this.combatLogList.remove(player);
    }
}
