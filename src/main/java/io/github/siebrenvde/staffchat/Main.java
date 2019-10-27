package io.github.siebrenvde.staffchat;

import eu.mcdb.spicord.Spicord;
import io.github.siebrenvde.staffchat.commands.HelpOp;
import io.github.siebrenvde.staffchat.commands.Report;
import io.github.siebrenvde.staffchat.commands.StaffChat;
import io.github.siebrenvde.staffchat.discord.Addon;
import io.github.siebrenvde.staffchat.util.Utils;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;

    public void onEnable() {
        saveDefaultConfig();
        registerCommands();
        Spicord.getInstance().getAddonManager().registerAddon(new Addon());
        plugin = this;
    }

    private void registerCommands() {
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("report").setExecutor(new Report(this));
        getCommand("helpop").setExecutor(new HelpOp(this));
    }

    private FileConfiguration config = getConfig();

    public String generalLayout(String msg, Player player) {

        String ccMsg = Utils.translateCC(msg);

        String rawMsg = config.getString("general-layout")
                .replace("%displayname%", player.getDisplayName())
                .replace("%username%", player.getName())
                .replace("%message%", ccMsg);

        String message = Utils.translateCC(rawMsg);

        return message;
    }

    public String minecraftLayout(String msg, User user) {

        String dscMsg = msg.replaceFirst("-sc ", "").replaceFirst("-staffchat ", "").replaceFirst("-schat ", "").replaceFirst("-staffc ", "");

        String rawMsg = config.getString("minecraft-layout")
                .replace("%username%", user.getName())
                .replace("%usertag%", user.getAsTag())
                .replace("%message%", dscMsg);

        String message = Utils.translateCC(rawMsg);

        return message;
    }

    public String discordLayout(String msg, Player player) {

        String dscMsg = Utils.removeCC(msg);

        String message = config.getString("discord-layout")
                .replace("%displayname%", player.getDisplayName())
                .replace("%username%", player.getName())
                .replace("%message%", dscMsg);

        return message;
    }

    public String rmdLayout(String msg, String reporter, String reported) {

        String dscMsg = Utils.removeCC(msg);

        String message = config.getString("report-message-discord")
                .replace("%reporter%", reporter)
                .replace("%reported%", reported)
                .replace("%reason%", dscMsg);

        return message;
    }

    public String rmLayout(String msg, String reporter, String reported) {

        String ccMsg = Utils.translateCC(msg);

        String rawMsg = config.getString("report-message")
                .replace("%reporter%", reporter)
                .replace("%reported%", reported)
                .replace("%reason%", ccMsg);

        String message = Utils.translateCC(rawMsg);

        return message;
    }

    public String homdLayout(String msg, String player) {

        String dscMsg = Utils.removeCC(msg);

        String message = config.getString("helpop-message-discord")
                .replace("%player%", player)
                .replace("%message%", dscMsg);

        return message;
    }

    public String homLayout(String msg, String player) {

        String ccMsg = Utils.translateCC(msg);

        String rawMsg = config.getString("helpop-message")
                .replace("%player%", player)
                .replace("%message%", ccMsg);

        String message = Utils.translateCC(rawMsg);

        return message;
    }

}
