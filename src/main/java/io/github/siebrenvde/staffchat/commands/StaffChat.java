package io.github.siebrenvde.staffchat.commands;

import io.github.siebrenvde.staffchat.Main;
import io.github.siebrenvde.staffchat.discord.Addon;
import io.github.siebrenvde.staffchat.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    private Main plugin;
    public StaffChat(Main pl) { plugin = pl; }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(player.hasPermission("staffchat.use")) {

                if(strings.length < 1) {
                    player.sendMessage(ChatColor.RED + "Please enter a message!");
                } else {
                    Addon addon = Addon.getInstance();
                    String sender = commandSender.getName();
                    String args = String.join(" ", strings);
                    String msg = Utils.translateCC(args);
                    String dscMsg = Utils.removeCC(args);
                    String channel = plugin.getConfig().getString("staff-channel");
                    Utils.sendPermissionMessage(ChatColor.DARK_RED + "StaffChat " + ChatColor.RED + sender + ChatColor.DARK_RED + ": " + ChatColor.GREEN + msg, "staffchat.see");
                    addon.sendMessage("**[StaffChat] " + sender + "**: " + dscMsg, channel);
                }

            } else { player.sendMessage(Utils.permissionMessage); }

        } else { commandSender.sendMessage("The console can not use the staffchat!"); }

        return false;
    }

}
