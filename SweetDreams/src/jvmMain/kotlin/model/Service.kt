package model

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.FileInputStream

val apikey = "AIzaSyCQT048S2I6bzN00IS6uhmEYnzpE9priNk";

fun Initialize() {
    val serviceAccount = FileInputStream("src/jvmMain/Resources/key.json")
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()
    FirebaseApp.initializeApp(options)
}

fun GetHttpBody(json : JSONObject, url : String) : String {
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = json.toString().toRequestBody(mediaType)
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    val client = OkHttpClient()
    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) return "NULL";
        val responseBody = response.body?.string()
        if (responseBody != null) {
            return responseBody;
        }
    }
    return "NULL"
}