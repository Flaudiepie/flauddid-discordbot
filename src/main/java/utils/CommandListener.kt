package utils

import Main.jda
import Main.redditChannel
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import utils.ReadJsonFile.postsList
import utils.ReadJsonFile.readJson
import utils.UpdatePosts.updatePosts
import java.awt.Color
import java.util.*

class CommandListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.textChannel.name.equals("reddit", ignoreCase = true)) {
            if (event.message.contentRaw.startsWith("!rdc ", ignoreCase = true)) {
                val command = event.message.contentRaw.split(" ").toTypedArray()


                if (command[1].equals("addsubreddit", ignoreCase = true)) {
                    if (command.size == 3) {
                        updatePosts(command[2])
                        event.channel.sendMessage(Objects.requireNonNull(event.member)!!.asMention + " The Subreddit \"" + command[2] + "\" has been added!").queue()
                    } else {
                        event.channel.sendMessage((event.member)!!.asMention + " Use !rdc addSubreddit <Name> ").queue()
                    }


                } else if(command[1].equals("removesubreddit", ignoreCase = true)) {
                    if (command.size == 3) {
                        event.channel.sendMessage((event.member)!!.asMention + " The Subreddit \"" + command[2] + "\" has been removed!").queue()
                    } else {
                        event.channel.sendMessage((event.member)!!.asMention + " Use !rdc removeSubreddit <Name> ").queue()
                    }


                } else if(command[1].equals("updateposts", ignoreCase = true)) {
                    if (command.size == 3) {
                        event.channel.sendMessage((event.member)!!.asMention + " The Update Time has been set to \"" + command[2] + "\" min!").queue()
                    } else {
                        event.channel.sendMessage((event.member)!!.asMention + " Use !rdc updatePost <Time in minute> ").queue()
                    }


                }else if (command[1].equals("post", ignoreCase = true)) {
                    readJson()
                    var thread = Thread {
                        for (post in postsList) {
                            if (post.removed != true) {
                                var message: EmbedBuilder? = EmbedBuilder()
                                message?.setTitle(post.title, "https://reddit.com${post.path}")
                                message?.setColor(Color(255, 69, 0))
                                message?.setDescription("posted by u/${post.author}\non r/${post.subreddit}")
                                message?.setAuthor("RedditBot", null, "https://cdn.discordapp.com/attachments/369413293988642827/830191218276958258/Unbenannt-1.png")
                                message?.setImage(post.url)
                                message?.setFooter("⬆️️${post.upVotes}  ⬇️${post.downVotes}")
                                event.channel.sendMessage(message?.build()!!).queue()
                            }
                        }
                    }
                    thread.start()


                } else if(command[1].equals("test", ignoreCase = true)) {
                    redditChannel = jda?.getTextChannelsByName("reddit", true)!!
                    redditChannel.get(0).sendMessage("${event.member?.asMention} bist ein kek").queue()
                }
            }
        }
    }
}