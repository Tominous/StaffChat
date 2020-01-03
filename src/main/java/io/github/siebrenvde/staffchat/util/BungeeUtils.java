package io.github.siebrenvde.staffchat.util;

import com.moandjiezana.toml.Toml;
import io.github.siebrenvde.staffchat.Bungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.File;

public class BungeeUtils {

    public static void sendPermissionMessage(String message, String permission) {

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if(player.hasPermission(permission)) {
                player.sendMessage(new TextComponent(message));
            }
        }

    }

    public static String translateCC(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    public static String removeCC(String msg) {

        return msg.replaceAll("&0", "")
                .replaceAll("&1", "")
                .replaceAll("&2", "")
                .replaceAll("&3", "")
                .replaceAll("&4", "")
                .replaceAll("&5", "")
                .replaceAll("&6", "")
                .replaceAll("&7", "")
                .replaceAll("&8", "")
                .replaceAll("&9", "")
                .replaceAll("&a", "").replaceAll("&A", "")
                .replaceAll("&e", "").replaceAll("&E", "")
                .replaceAll("&r", "").replaceAll("&R", "")
                .replaceAll("&o", "").replaceAll("&O", "")
                .replaceAll("&d", "").replaceAll("&D", "")
                .replaceAll("&f", "").replaceAll("&F", "")
                .replaceAll("&k", "").replaceAll("&K", "")
                .replaceAll("&l", "").replaceAll("&L", "")
                .replaceAll("&m", "").replaceAll("&M", "")
                .replaceAll("&c", "").replaceAll("&C", "")
                .replaceAll("&b", "").replaceAll("&B", "");

    }

    public static String permissionMessage = ChatColor.RED + "You don't have permission to do this!";

    public static String spicordPrefix() {
        File file;
        file = new File(ProxyServer.getInstance().getPluginsFolder() + "/Spicord/config.toml");
        Toml config = new Toml().read(file);
        return config.getString("bots[" + Bungee.plugin.configNum() + "].command_prefix");
    }

}