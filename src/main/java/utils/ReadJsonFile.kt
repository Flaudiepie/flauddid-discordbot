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
            val jsonArray = parser.parse(FileReader("src/main/java/files/posts.json")) as JSONArray //read the Json File
            for (jsonPost in jsonArray) {
                val post = PostObject()
                val posts = jsonPost as JSONObject
                post.title = posts["title"] as String?
                post.author = posts["author"] as String?
                post.subreddit = posts["subreddit"] as String?
                post.url = posts["url"] as String?
                post.path = posts["path"] as String?
                post.removed = posts["removed"] as Boolean?
                post.upVotes = posts["upVotes"] as Long?
                post.downVotes = posts["downVotes"] as Long?
                post.postID = posts["id"] as String?
                postsList.add(post)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}