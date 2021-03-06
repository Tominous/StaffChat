package io.github.siebrenvde.staffchat.discord;

import eu.mcdb.spicord.api.addon.SimpleAddon;
import eu.mcdb.spicord.bot.DiscordBot;
import eu.mcdb.spicord.bot.command.DiscordBotCommand;
import io.github.siebrenvde.staffchat.Bungee;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class BungeeAddon extends SimpleAddon {

    private static BungeeAddon instance;
    private DiscordBot bot;
    public static BungeeAddon getInstance() {
        return instance;
    }

    public BungeeAddon() {
        super("StaffChat","staffchat","Siebrenvde");
        instance = this;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        this.bot = bot;
        enableCommands();
        bot.getJda().addEventListener(new MessageListenerBungee());
    }

    private void enableCommands() {
        if(Bungee.plugin.config.getBoolean("enable-discord-commands")) {
            bot.onCommand("sc", this::staffChat);
            bot.onCommand("staffchat", this::staffChat);
            bot.onCommand("schat", this::staffChat);
            bot.onCommand("staffc", this::staffChat);
        }
    }

    private String prefix = BungeeUtils.spicordPrefix();

    public void sendMessage(String message) {
        TextChannel tc = bot.getJda().getTextChannelById(Bungee.plugin.config.getString("staff-channel"));
        tc.sendMessage(message).queue();
    }

    public void sendEmbed(String title, String description) {
        TextChannel tc = bot.getJda().getTextChannelById(Bungee.plugin.config.getString("staff-channel"));
        tc.sendMessage(new EmbedBuilder().setTitle(title).setDescription(description).build()).queue();
    }

    private void staffChat(DiscordBotCommand command) {
        User user = command.getMessage().getAuthor();
        String msg = command.getMessage().getContentRaw();
        if(msg.length() < 2) {
            command.getMessage().getChannel().sendMessage("**Usage**: ***" + prefix + " <message>***").queue();
        } else {
            BungeeUtils.sendPermissionMessage(Bungee.plugin.minecraftLayout(msg, user), "staffchat.see");
        }
    }

}
