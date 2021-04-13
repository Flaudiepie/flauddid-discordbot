package utils

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object UpdatePosts {
    fun updatePosts(sub: String) {
       val client = HttpClient.newBuilder().build()
       val request = HttpRequest.newBuilder()
               .uri(URI.create("http://boese.ovh"))
               .build()
       val response = client.send(request, HttpResponse.BodyHandlers.ofString())
       if(response == null)
       println(response?.body())
   }
}