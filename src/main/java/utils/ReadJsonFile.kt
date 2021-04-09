package utils

import org.json.simple.JSONObject
import org.json.simple.JSONArray
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import java.io.FileReader
import java.io.IOException


object ReadJsonFile {
    var postsList = arrayListOf<PostObject>()
    fun readJson() {
        val parser = JSONParser()
        try {
            val obj = parser.parse(FileReader("C:\\Users\\ImNuro\\Desktop\\Development\\Java\\RedditBot\\src\\main\\java\\utils\\posts.json"))
            val jsonObject = obj as JSONObject
            val jsonArray = jsonObject["posts"] as JSONArray
            for (o in jsonArray) {
                val posts = o as JSONObject
                val post = PostObject()
                post.title = posts["Title"] as String?
                post.author = posts["Author"] as String?
                post.subreddit = posts["Subreddit"] as String?
                post.url = posts["URL"] as String?
                post.path = posts["Path"] as String?
                postsList.add(post)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}