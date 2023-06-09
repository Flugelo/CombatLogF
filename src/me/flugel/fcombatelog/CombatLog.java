package me.flugel.fcombatelog;

import com.sun.istack.internal.NotNull;
import me.flugel.fcombatelog.commands.command;
import me.flugel.fcombatelog.listeners.*;
import me.flugel.fcombatelog.managers.CombatLogManagers;
import me.flugel.fcombatelog.managers.CorpoManager;
import me.flugel.fcombatelog.objects.Corpo;
import me.flugel.fcombatelog.shaduler.TimerSheduler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.List;

public class CombatLog extends JavaPlugin {

    private static CombatLog instance;
    private CombatLogManagers combatLogManagers;
    private CorpoManager corpoManager;


    public static String JoinPvp;
    public static String ExitPvp;
    public static String Timer;
    public static String ExitServer;
    public static String NotCommandExecuter;
    public static String comando;
    public static int timerArmorSand;
    public static int TimePvp;

    public static boolean command;
    public static boolean mensage;
    public static boolean drop;
    public static boolean armorStand;

    private List<String> blockCommands;

    @Override
    public void onEnable() {
        instance = this;
        combatLogManagers = new CombatLogManagers();
        corpoManager = new CorpoManager();

        saveDefaultConfig();

        getCommand("fcombat").setExecutor(new command());


        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ExitServer(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InterectCadaver(), this);
        Bukkit.getPluginManager().registerEvents(new CancelCommand(), this);

        new TimerSheduler().runTaskTimerAsynchronously(this, 0, 20);


        reiniciarConnfig();
        blockCommands = new ArrayList<>();

        getBlockCommandToConfig();

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof ArmorStand) {
                    if (entity.getCustomName() != null) {
                        if (entity.getCustomName().equals("armorstand25565170102")) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }

    public CorpoManager getCorpoManager() {
        return corpoManager;
    }

    public static CombatLog getInstance() {
        return instance;
    }

    public CombatLogManagers getCombatLogManagers() {
        return combatLogManagers;
    }

    public void addCombatLog(Player player, Player target) {
        new me.flugel.fcombatelog.objects.CombatLog(player, TimePvp);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(JoinPvp.replace("(target)", target.getName())));
        target.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(JoinPvp.replace("(target)", target.getName())));
    }


    public void reiniciarConnfig() {
        reloadConfig();
        JoinPvp = getInstance().getConfig().getString("Config.mensagens.JoinPvp").replace("&", "§");
        ExitPvp = getInstance().getConfig().getString("Config.mensagens.ExitPvp").replace("&", "§");
        Timer = getInstance().getConfig().getString("Config.mensagens.Timer").replace("&", "§");
        ExitServer = getInstance().getConfig().getString("Config.mensagens.ExitServer").replace("&", "§");
        NotCommandExecuter = getInstance().getConfig().getString("Config.mensagens.NotCommandExecuter").replace("&", "§");

        command = getInstance().getConfig().getBoolean("Config.pena.command");
        comando = getInstance().getConfig().getString("Config.pena.comando");
        mensage = getInstance().getConfig().getBoolean("Config.pena.mensage");
        drop = getInstance().getConfig().getBoolean("Config.pena.drop");
        armorStand = getInstance().getConfig().getBoolean("Config.armorStand");
        timerArmorSand = getInstance().getConfig().getInt("Config.TimerStand");
        TimePvp = getInstance().getConfig().getInt("Config.TimePvp");
    }

    public void cadaver(Player player, @NotNull ItemStack[] itens) {

        ArmorStand armorStand = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation().clone().add(0, -0.45, 0), EntityType.ARMOR_STAND);

        armorStand.setCustomName("armorstand25565170102");

        ItemStack itemStack = SkullCreator.itemFromName(player.getName());

        armorStand.getEquipment().setHelmet(itemStack);
        armorStand.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        armorStand.setBasePlate(false);
        armorStand.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        armorStand.setArms(true);
        armorStand.setGravity(false);
        armorStand.setCanPickupItems(false);
        armorStand.setInvulnerable(true);

        EulerAngle eulerAngle1 = armorStand.getHeadPose();
        EulerAngle eulerAngle2 = armorStand.getBodyPose();
        EulerAngle eulerAngle3 = armorStand.getLeftArmPose();
        EulerAngle eulerAngle4 = armorStand.getRightArmPose();
        EulerAngle eulerAngle5 = eulerAngle1.add(0.49000000953674316D, 0.2199999988079071D, 0);
        EulerAngle eulerAngle6 = eulerAngle2.add(1.2999999523162842D, 0.1599999964237213D, 0.0D);
        EulerAngle eulerAngle7 = eulerAngle3.add(4.860000133514404D, 0.0D, 0.0D);
        EulerAngle eulerAngle8 = eulerAngle4.add(4.860000133514404D, 0.20000000298023224D, 0.0D);

        armorStand.setBodyPose(eulerAngle5);
        armorStand.setHeadPose(eulerAngle6);
        armorStand.setLeftLegPose(eulerAngle7);
        armorStand.setRightLegPose(eulerAngle8);

        new Corpo(armorStand, itens, player.getLocation(), player);
    }

    private void getBlockCommandToConfig() {
        for (String s : getConfig().getStringList("Config.blockCommand")) {
            getBlockCommands().add(s);
        }

    }

    public List<String> getBlockCommands() {
        return blockCommands;
    }
}
