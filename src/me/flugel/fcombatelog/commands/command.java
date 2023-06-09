package me.flugel.fcombatelog.commands;

import me.flugel.fcombatelog.CombatLog;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if(!(sender instanceof Player))
            return false;




        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("fcombat")){
            if(args.length == 0){
                if(p.hasPermission("fcombat.staff")){
                    p.sendMessage("");
                    p.sendMessage("§7===============================");
                    p.sendMessage("§e/fcombat reload §7Para dar reload na config");
                    p.sendMessage("§7===============================");
                    p.sendMessage("");
                    CombatLog.getInstance().cadaver(p,null);
                }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("reload")){
                    if(p.hasPermission("fcombat.staff")){
                        CombatLog.getInstance().reiniciarConnfig();
                    }
                }
            }
        }
        return false;
    }
}
