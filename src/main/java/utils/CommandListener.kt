package utils


import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import utils.ReadJsonFile.postsList

import utils.ReadJsonFile.readJson
import java.awt.Color
import java.util.*
import javax.annotation.Nullable

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
                    if (postsList != null) {
                        var post = postsList[0]
                        var message : EmbedBuilder? = EmbedBuilder()
                        message?.setTitle(post.title, null)

                        message?.setColor(Color.red);
                        message?.setColor(Color(0xF40C0C))
                        message?.setColor(Color(255, 0, 54))

                        message?.setDescription("posted by ${post.author}")
                        message?.addField("Subreddit:", "r/${post.subreddit}", false)
                        message?.addBlankField(false)

                        message?.setAuthor("RedditBot", null, "https://cdn.discordapp.com/attachments/369413293988642827/830191218276958258/Unbenannt-1.png")
                        message?.setImage(post.url)

                        event.channel.sendMessage(message?.build()!!).queue()
                    }
                } else if(args[1].equals("test", ignoreCase = true)) {

                }
            }
        }
    }
}