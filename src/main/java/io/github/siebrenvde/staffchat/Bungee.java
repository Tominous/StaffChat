package io.github.siebrenvde.staffchat;

import com.moandjiezana.toml.Toml;
import eu.mcdb.spicord.Spicord;
import io.github.siebrenvde.staffchat.commands.bungee.HelpOp;
import io.github.siebrenvde.staffchat.commands.bungee.Report;
import io.github.siebrenvde.staffchat.commands.bungee.StaffChat;
import io.github.siebrenvde.staffchat.discord.BungeeAddon;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.dv8tion.jda.core.entities.User;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bungee extends Plugin {

    public static Bungee plugin;

    public void onEnable() {
        registerCommands();
        registerConfig();
        Spicord.getInstance().getAddonManager().registerAddon(new BungeeAddon());
        plugin = this;
    }

    private void registerCommands(){
        getProxy().getPluginManager().registerCommand(this, new StaffChat(this));
        getProxy().getPluginManager().registerCommand(this, new Report(this));
        getProxy().getPluginManager().registerCommand(this, new HelpOp(this));
    }

    private File file;
    public Configuration config;

    private void registerConfig() {

        try {
            Files.createDirectories(Paths.get(ProxyServer.getInstance().getPluginsFolder() + "/StaffChat"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        file = new File(ProxyServer.getInstance().getPluginsFolder() + "/StaffChat/config.yml");

        try {
            if(!file.exists()) {
                Files.copy(getClass().getResourceAsStream("/bungeeconfig.yml"), Paths.get(ProxyServer.getInstance().getPluginsFolder() + "/StaffChat/config.yml"));
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String generalLayout(String msg, String player, String playerDN, String server) {

        String ccMsg = BungeeUtils.translateCC(msg);

        String rawMsg = config.getString("general-layout")
                .replace("%displayname%", playerDN)
                .replace("%username%", player)
                .replace("%server%", server)
                .replace("%message%", ccMsg);

        String message = BungeeUtils.translateCC(rawMsg);

        return message;
    }

    public String minecraftLayout(String msg, User user) {

        String p = BungeeUtils.spicordPrefix();
        String dscMsg = msg.replaceFirst(p + "sc ", "").replaceFirst(p + "staffchat ", "").replaceFirst(p + "schat ", "").replaceFirst(p + "staffc ", "");

        String rawMsg = config.getString("minecraft-layout")
                .replace("%username%", user.getName())
                .replace("%usertag%", user.getAsTag())
                .replace("%message%", dscMsg);

        String message = BungeeUtils.translateCC(rawMsg);

        return message;
    }

    public String discordLayout(String msg, String player, String playerDN, String server) {

        String dscMsg = BungeeUtils.removeCC(msg);

        String message = config.getString("discord-layout")
                .replace("%displayname%", playerDN)
                .replace("%username%", player)
                .replace("%server%", server)
                .replace("%message%", dscMsg);

        return message;
    }

    public String rmdLayout(String msg, String reporter, String reported, String server) {

        String dscMsg = BungeeUtils.removeCC(msg);

        String message = config.getString("report-message-discord")
                .replace("%reporter%", reporter)
                .replace("%reported%", reported)
                .replace("%server%", server)
                .replace("%reason%", dscMsg);

        return message;
    }

    public String rmLayout(String msg, String reporter, String reported, String server) {

        String ccMsg = BungeeUtils.translateCC(msg);

        String rawMsg = config.getString("report-message")
                .replace("%reporter%", reporter)
                .replace("%reported%", reported)
                .replace("%server%", server)
                .replace("%reason%", ccMsg);

        String message = BungeeUtils.translateCC(rawMsg);

        return message;
    }

    public String homdLayout(String msg, String player, String server) {

        String dscMsg = BungeeUtils.removeCC(msg);

        String message = config.getString("helpop-message-discord")
                .replace("%player%", player)
                .replace("%server%", server)
                .replace("%message%", dscMsg);

        return message;
    }

    public String homLayout(String msg, String player, String server) {

        String ccMsg = BungeeUtils.translateCC(msg);

        String rawMsg = config.getString("helpop-message")
                .replace("%player%", player)
                .replace("%server%", server)
                .replace("%message%", ccMsg);

        String message = BungeeUtils.translateCC(rawMsg);

        return message;
    }

    public static Integer configNum() {
        File spFile;
        spFile = new File(ProxyServer.getInstance().getPluginsFolder() + "/Spicord/config.toml");
        Toml cfg = new Toml().read(spFile);

        int num = 0;
        int i = 0;

        while(i == 0) {
            if(cfg.getList("bots[" + num + "].addons").contains("staffchat")) {
                i++;
            }
            else {
                num++;
            }
            return num;
        }
        return num;
    }

}
