package utils

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class NewPostEvent {
   inline fun timer(

    ) {
       val client = HttpClient.newBuilder().build();
       val request = HttpRequest.newBuilder()
               .uri(URI.create("http://reddit.boese.ovh"))
               .build()

       val response = client.send(request, HttpResponse.BodyHandlers.ofString())
       println(response.body())
   }
}