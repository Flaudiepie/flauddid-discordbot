package utils

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import java.io.FileReader
import java.io.IOException

object ReadJsonFile {
    var postsList = arrayListOf<PostObject>()
    fun readJson() {
        val parser = JSONParser()
        try {
            val jsonArray = parser.parse(FileReader("src/main/java/utils/posts.json")) as JSONArray
            for(jsonPost in jsonArray) {
                val post = PostObject()
                val posts = jsonPost as JSONObject
                post.title = posts["Title"] as String?
                post.author = posts["Author"] as String?
                post.subreddit = posts["Subreddit"] as String?
                post.url = posts["URL"] as String?
                post.path = posts["Path"] as String?
                post.removed = posts["Removed"] as Boolean?
                postsList.add(post)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}