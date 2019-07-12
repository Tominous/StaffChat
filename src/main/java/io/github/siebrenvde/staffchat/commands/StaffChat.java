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
                    String msg = String.join(" ", strings);
                    String channel = plugin.getConfig().getString("staff-channel");
                    Utils.sendPermissionMessage(plugin.generalLayout(msg, player), "staffchat.see");
                    addon.sendMessage(plugin.discordLayout(msg, player), channel);
                }

            } else { player.sendMessage(Utils.permissionMessage); }

        } else { commandSender.sendMessage("The console can not use the staffchat!"); }

        return false;
    }

}
