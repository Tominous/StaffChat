package io.github.siebrenvde.staffchat.commands.spigot;

import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.discord.SpigotAddon;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    private Spigot plugin;
    public StaffChat(Spigot pl) { plugin = pl; }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(player.hasPermission("staffchat.use")) {

                if(strings.length < 1) {
                    player.sendMessage(ChatColor.RED + "Please enter a message!");
                } else {
                    SpigotAddon addon = SpigotAddon.getInstance();
                    String msg = String.join(" ", strings);
                    String channel = plugin.getConfig().getString("staff-channel");
                    SpigotUtils.sendPermissionMessage(plugin.generalLayout(msg, player), "staffchat.see");
                    addon.sendMessage(plugin.discordLayout(msg, player), channel);
                }

            } else { player.sendMessage(SpigotUtils.permissionMessage); }

        } else { commandSender.sendMessage("The console can not use the staff chat!"); }

        return false;
    }

}
