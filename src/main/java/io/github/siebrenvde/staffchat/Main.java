package io.github.siebrenvde.staffchat;

import eu.mcdb.spicord.Spicord;
import io.github.siebrenvde.staffchat.commands.StaffChat;
import io.github.siebrenvde.staffchat.discord.Addon;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerConfig();
        registerCommands();
        Spicord.getInstance().getAddonManager().registerAddon(new Addon());
    }

    private void registerCommands() {
        getCommand("staffchat").setExecutor(new StaffChat(this));
    }

    private void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
