package io.github.siebrenvde.staffchat.discord;

import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListenerSpigot extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();

        if(event.isFromType(ChannelType.TEXT)) {

            TextChannel channel = event.getTextChannel();

            if(channel.getId().equals(Spigot.plugin.getConfig().getString("staff-channel"))) {

                if(!Spigot.plugin.getConfig().getBoolean("enable-discord-commands")) {

                    if (!author.isBot()) {

                        SpigotUtils.sendPermissionMessage(Spigot.plugin.minecraftLayout(msg, author), "staffchat.see");

                    }

                }

            }

        }

    }

}
