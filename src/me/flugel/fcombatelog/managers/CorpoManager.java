package me.flugel.fcombatelog.managers;

import me.flugel.fcombatelog.objects.Corpo;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CorpoManager {
    private List<Corpo> corpos;

    public CorpoManager() {
        corpos = new ArrayList<>();

    }

    public List<Corpo> getCorpos() {
        return corpos;
    }

    public Corpo getByCorpo(Location location) {
        return this.corpos.stream().filter(p -> p.getLocation().equals(location)).findFirst().orElse(null);
    }

    public Corpo getByCorpoPlayer(Player player) {
        return this.corpos.stream().filter(p -> p.getPlayer().equals(player)).findFirst().orElse(null);
    }

    public Corpo getByCorpoString(String name) {
        return this.corpos.stream().filter(p -> p.getArmorStand().getCustomName().equals(name)).findFirst().orElse(null);
    }

    public Corpo getByCorpoEntity(Entity entity) {
        return this.corpos.stream().filter(p -> p.getArmorStand().equals(entity)).findFirst().orElse(null);
    }

    public void add(Corpo corpo) {
        this.corpos.add(corpo);
    }

    public void remove(Corpo corpo) {
        this.corpos.remove(corpo);
    }
}
