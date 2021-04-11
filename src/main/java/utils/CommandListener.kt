package utils

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import utils.ReadJsonFile.postsList
import utils.ReadJsonFile.readJson
import java.awt.Color
import java.util.*

class CommandListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.textChannel.name.equals("reddit", ignoreCase = true)) {
            val args = event.message.contentRaw.split(" ").toTypedArray()
            if (args[0].equals("!rdc", ignoreCase = true)) {
                if (args[1].equals("addsubreddit", ignoreCase = true)) {
                    if (args.size == 3) {
                        event.channel.sendMessage(Objects.requireNonNull(event.member)!!.asMention + " The Subreddit \"" + args[2] + "\" has been added!").queue()
                    } else {
                        event.channel.sendMessage("use !rdc addsubreddit <Name> ").queue()
                    }
                } else if (args[1].equals("post", ignoreCase = true)) {
                    readJson()
                    for(post in postsList) {
                        if(post.removed != true) {
                            var message: EmbedBuilder? = EmbedBuilder()
                            message?.setTitle(post.title, "https://reddit.com${post.path}")
                            message?.setColor(Color.red)
                            message?.setColor(Color(0xF40C0C))
                            message?.setColor(Color(255, 0, 54))
                            message?.setDescription("posted by u/${post.author}\non r/${post.subreddit}")
                            message?.setAuthor("RedditBot", null, "https://cdn.discordapp.com/attachments/369413293988642827/830191218276958258/Unbenannt-1.png")
                            message?.setImage(post.url)
                            event.channel.sendMessage(message?.build()!!).queue()
                        }
                    }
                }
            }
        }
    }
}