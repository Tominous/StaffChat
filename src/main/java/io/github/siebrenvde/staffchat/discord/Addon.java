package io.github.siebrenvde.staffchat.discord;

import eu.mcdb.spicord.api.addon.SimpleAddon;
import eu.mcdb.spicord.bot.DiscordBot;
import eu.mcdb.spicord.bot.command.DiscordBotCommand;
import io.github.siebrenvde.staffchat.util.Utils;
import net.dv8tion.jda.core.entities.TextChannel;
import org.bukkit.ChatColor;

public class Addon extends SimpleAddon {

    private static Addon instance;
    private DiscordBot bot;
    public static Addon getInstance() {
        return instance;
    }

    public Addon() {
        super("StaffChat","staffchat","Siebrenvde");
        instance = this;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        this.bot = bot;
        bot.onCommand("sc", this::staffChat);
        bot.onCommand("staffchat", this::staffChat);
        bot.onCommand("schat", this::staffChat);
        bot.onCommand("staffc", this::staffChat);
    }

    public void sendMessage(String message, String channelID) {
        TextChannel tc = bot.getJda().getTextChannelById(channelID);
        tc.sendMessage(message).queue();
    }

    private void staffChat(DiscordBotCommand command) {
        String sender = command.getMessage().getAuthor().getAsTag();
        String rawmsg = command.getMessage().getContentRaw().replaceFirst("-sc", "").replaceFirst("-staffchat", "").replaceFirst("-schat", "").replaceFirst("-staffc", "");
        String msg = Utils.translateCC(rawmsg);
        if(rawmsg.length() < 1) {
            command.getMessage().getChannel().sendMessage("**Usage**: ***-sc <message>***").queue();
        } else {
            Utils.sendPermissionMessage(ChatColor.BLUE + "Discord " + ChatColor.DARK_RED + "StaffChat " + ChatColor.RED + sender + ChatColor.DARK_RED + ":" + ChatColor.GREEN + msg, "staffchat.see");
        }
    }

}
