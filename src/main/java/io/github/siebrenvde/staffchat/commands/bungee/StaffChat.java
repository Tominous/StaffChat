package io.github.siebrenvde.staffchat.commands.bungee;

import io.github.siebrenvde.staffchat.Bungee;
import io.github.siebrenvde.staffchat.discord.BungeeAddon;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;

public class StaffChat extends Command {

    private Bungee plugin;

    public StaffChat(Bungee pl) {
        super("staffchat", "", "sc", "staffc", "schat");
        plugin = pl;
    }

    public void execute(CommandSender sender, String[] strings) {

        BungeeAddon addon = BungeeAddon.getInstance();
        String msg = String.join(" ", strings);
        String channel = plugin.config.getString("staff-channel");

        if(sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) sender;

            if(player.hasPermission("staffchat.use")) {

                if(strings.length < 1) {
                    player.sendMessage(new TextComponent(ChatColor.RED + "Please enter a message!"));
                } else {
                    String server = player.getServer().getInfo().getName();
                    BungeeUtils.sendPermissionMessage(plugin.generalLayout(msg, player.getName(), player.getDisplayName(), server),"staffchat.see");
                    addon.sendMessage(plugin.discordLayout(msg, player.getName(), player.getDisplayName(), server), channel);
                }

            }

            else{ player.sendMessage(new TextComponent(BungeeUtils.permissionMessage)); }

        }

        else {

            BungeeUtils.sendPermissionMessage(plugin.generalLayout(msg, "CONSOLE", "CONSOLE", "CONSOLE"), "staffchat.see");
            addon.sendMessage(plugin.discordLayout(msg, "CONSOLE", "CONSOLE", "CONSOLE"), channel);

        }

    }

}
