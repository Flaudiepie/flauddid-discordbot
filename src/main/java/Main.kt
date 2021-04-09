import net.dv8tion.jda.api.JDA
import kotlin.jvm.JvmStatic
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import utils.CommandListener
import utils.ReadJsonFile.readJson
import javax.security.auth.login.LoginException

object Main {
    var jda: JDA? = null
    @JvmStatic
    fun main(args: Array<String>) {
        val builder = JDABuilder.createDefault("ODMwMDA1Nzk0NjczMDAwNDk5.YHAZIg.g9S1rUBaiFlLV4pZJJMtxzpLmFY")
        builder.setActivity(Activity.watching("Reddit"))
        builder.addEventListeners(CommandListener())
        try {
            jda = builder.build()
        } catch (e: LoginException) {
            e.printStackTrace()
        }
    }
}