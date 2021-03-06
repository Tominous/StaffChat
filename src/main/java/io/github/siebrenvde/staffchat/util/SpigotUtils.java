package io.github.siebrenvde.staffchat.util;

import com.moandjiezana.toml.Toml;
import io.github.siebrenvde.staffchat.Spigot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;

public class SpigotUtils {

    public static String translateCC(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    public static void sendPermissionMessage(String message, String permission) {

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission(permission) || player.isOp()) {
                player.sendMessage(message);
            }
        }

    }

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
        file = new File(Bukkit.getPluginManager().getPlugin("Spicord").getDataFolder() + "/config.toml");
        Toml config = new Toml().read(file);
        return config.getString("bots[" + Spigot.plugin.configNum() + "].command_prefix");
    }

}
