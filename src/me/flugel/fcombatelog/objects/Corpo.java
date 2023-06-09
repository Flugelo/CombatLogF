package me.flugel.fcombatelog.objects;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Corpo {

    private ArmorStand armorStand;
    private ItemStack[] itens;
    private Location location;
    private Player player;
    private int timer;

    public Corpo(ArmorStand armorStand, ItemStack[] itens, Location location, Player player) {
        this.armorStand = armorStand;
        this.itens = itens;
        this.location = location;
        this.player = player;
        this.timer = CombatLog.timerArmorSand;
        CombatLog.getInstance().getCorpoManager().add(this);
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public ItemStack[] getItens() {
        return itens;
    }

    public void setItens(ItemStack[] itens) {
        this.itens = itens;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void deleted(){
        this.armorStand.remove();
        CombatLog.getInstance().getCorpoManager().remove(this);
    }
}
