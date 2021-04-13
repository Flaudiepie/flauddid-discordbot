import net.dv8tion.jda.api.JDA
import kotlin.jvm.JvmStatic
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.TextChannel
import utils.CommandListener
import javax.security.auth.login.LoginException


object Main {
    var jda: JDA? = null // Instance of the Discord Bot
    var redditChannel = listOf<TextChannel>() // Reddit Text Channel to post Reddit posts
    @JvmStatic
    fun main(args: Array<String>) { // Main
        val builder = JDABuilder.createDefault("ODMwMDA1Nzk0NjczMDAwNDk5.YHAZIg.g9S1rUBaiFlLV4pZJJMtxzpLmFY") // Creates the Bot with the bot token
        builder.setActivity(Activity.watching("Reddit")) // Sets the activity to "watch Reddit"
        builder.addEventListeners(CommandListener()) // Adds an new Listener
        try {
            jda = builder.build() // Builds the bot
        } catch (e: LoginException) {
            e.printStackTrace()
        }

    }
}