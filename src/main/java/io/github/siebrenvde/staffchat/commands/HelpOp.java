package io.github.siebrenvde.staffchat.commands;

import io.github.siebrenvde.staffchat.Main;
import io.github.siebrenvde.staffchat.discord.Addon;
import io.github.siebrenvde.staffchat.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpOp implements CommandExecutor {

    private Main plugin;
    public HelpOp(Main pl) { plugin = pl; }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Addon addon = Addon.getInstance();
        Player player = (Player) commandSender;

        if(strings.length < 1) {
            player.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.RED + "/helpop <message>");
        }

        else {

            String channel = Utils.getChannel("staff-channel");
            String playerName = player.getName();
            String message = String.join(" ", strings);

            if(player.hasPermission("helpop.see")) {
                Utils.sendPermissionMessage(plugin.homLayout(message, playerName), "helpop.see");
            }

            else {
                player.sendMessage(plugin.homLayout(message, playerName));

                Utils.sendPermissionMessage(plugin.homLayout(message, playerName), "helpop.see");
            }

            if(plugin.getConfig().getBoolean("use-embed")) {
                addon.sendEmbed(
                        "**HelpOp**",
                        "\n**Player**: " + playerName +
                                "\n**Message**: `" + message + "`",
                        channel);
            }
            else {
                addon.sendMessage(plugin.homdLayout(message, playerName), channel);
            }

        }

        return false;
    }
}
