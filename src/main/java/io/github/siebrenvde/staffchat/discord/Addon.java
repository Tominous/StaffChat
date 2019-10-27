package io.github.siebrenvde.staffchat.discord;

import eu.mcdb.spicord.api.addon.SimpleAddon;
import eu.mcdb.spicord.bot.DiscordBot;
import eu.mcdb.spicord.bot.command.DiscordBotCommand;
import io.github.siebrenvde.staffchat.Main;
import io.github.siebrenvde.staffchat.util.Utils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;

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

    public void sendEmbed(String title, String description, String channelID) {
        TextChannel tc = bot.getJda().getTextChannelById(channelID);
        tc.sendMessage(new EmbedBuilder().setTitle(title).setDescription(description).build()).queue();
    }

    private void staffChat(DiscordBotCommand command) {
        User user = command.getMessage().getAuthor();
        String msg = command.getMessage().getContentRaw();
        if(msg.length() < 2) {
            command.getMessage().getChannel().sendMessage("**Usage**: ***-sc <message>***").queue();
        } else {
            Utils.sendPermissionMessage(Main.plugin.minecraftLayout(msg, user), "staffchat.see");
        }
    }

}
