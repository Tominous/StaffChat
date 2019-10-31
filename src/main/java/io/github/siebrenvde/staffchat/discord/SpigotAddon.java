package io.github.siebrenvde.staffchat.discord;

import eu.mcdb.spicord.api.addon.SimpleAddon;
import eu.mcdb.spicord.bot.DiscordBot;
import eu.mcdb.spicord.bot.command.DiscordBotCommand;
import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SpigotAddon extends SimpleAddon {

    private static SpigotAddon instance;
    private DiscordBot bot;
    public static SpigotAddon getInstance() {
        return instance;
    }

    public SpigotAddon() {
        super("StaffChat","staffchat","Siebrenvde");
        instance = this;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        this.bot = bot;
        enableCommands();
        bot.getJda().addEventListener(new MessageListenerSpigot());
    }

    private void enableCommands() {
        if(Spigot.plugin.getConfig().getBoolean("enable-discord-commands")) {
            bot.onCommand("sc", this::staffChat);
            bot.onCommand("staffchat", this::staffChat);
            bot.onCommand("schat", this::staffChat);
            bot.onCommand("staffc", this::staffChat);
        }
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
            SpigotUtils.sendPermissionMessage(Spigot.plugin.minecraftLayout(msg, user), "staffchat.see");
        }
    }

}
