package model

import com.google.cloud.storage.Storage
import com.google.cloud.storage.Bucket
import com.google.firebase.cloud.StorageClient
import org.json.JSONArray
import org.json.JSONObject
import userinterface.AudioBar
import userinterface.Video
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class UploadManager {
    private val storage: Bucket = StorageClient.getInstance().bucket()

    init {
        instance = this;
    }

    companion object {
        var instance: UploadManager? = null;
    }

    fun uploadFiles(audioFilePath: String, imageFilePath: String, user: String) {
        val audioFile = File(audioFilePath)
        val imageFile = File(imageFilePath)
        val folderName = audioFile.nameWithoutExtension
        val audioBlob = storage.create("users/$user/uploads/$folderName/${audioFile.name}", audioFile.inputStream())
        val imageBlob = storage.create("users/$user/uploads/$folderName/${imageFile.name}", imageFile.inputStream())

        println("Audio File uploaded to Firebase Storage with URL: ${audioBlob.mediaLink}")
        println("Image File uploaded to Firebase Storage with URL: ${imageBlob.mediaLink}")
    }


    fun updatePlaylist() {
        val user = AccountManager.instance?.getUser();
        var playlist = PlaylistManager.instance?.GetPlaylist();
        var json = JSONArray(playlist);

        val blob = storage.create("users/$user/Playlist", json.toString().byteInputStream())
        println("Updated playlist on firebase ${blob.mediaLink}" );
    }

    fun getPlaylist() {
        try{
            val user = AccountManager.instance?.getUser();
            val playlist = storage.get("users/$user/Playlist");
            val reader = BufferedReader(InputStreamReader(playlist.getContent().inputStream()))
            val json = JSONArray(reader.readLine());
            val list = mutableListOf<Video>();
            println("Parsing: " + json.toString())
            for (i in 0 until json.length()) {
                val obj = json.getJSONObject(i)
                val id = obj.getInt("id");
                val title = obj.getString("title");
                val description = obj.getString("description");
                val thumbnail = obj.getString("thumbnail");
                val playerId = obj.getString("playerId");
                val videoId = obj.getInt("videoId");
                val bloburl = obj.getString("bloburl");
                list.add(Video(id, title, description,thumbnail, playerId, videoId, bloburl));
            }
            println(list);
            PlaylistManager.instance?.SetPlaylist(list);
        } catch (e :Exception){
            println(e);
        }
        println("Done");
    }

    // SOURCE CODE CITATION: The delete audio file code was inspired/taken from the following StackOverflow article: https://stackoverflow.com/questions/53657627/deleting-multiple-blobs-from-google-cloud-storage-efficiently
    fun deleteFiles(url: String, user: String) {
        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            if (blob.name.substringAfterLast('/') == url) {
                println("Blob Match: ${blob.name}")
                blob.delete()
            }
        }
    }

    fun getAudioFiles(user: String): List<String> {
        val files = mutableListOf<String>()

        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            val fileName = blob.name.substringAfterLast('/')
            if (fileName.endsWith(".wav")) {
                files.add(fileName)
            }
        }

        return files
    }

    fun getAudioLinks(user: String): List<String> {
        val files = mutableListOf<String>()

        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            val fileName = blob.name.substringAfterLast('/')
            if (fileName.endsWith(".wav")) {
                files.add(blob.mediaLink)
            }
        }

        return files
    }

    fun getImageLinks(user: String): List<String> {
        val files = mutableListOf<String>()

        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            val fileName = blob.name.substringAfterLast('/')
            if (fileName.endsWith(".jpg")) {
                files.add(blob.mediaLink)
            }
        }

        println(files)
        return files
    }
}