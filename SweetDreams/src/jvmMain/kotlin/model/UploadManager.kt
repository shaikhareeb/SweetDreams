package model

import com.google.cloud.storage.Bucket
import com.google.firebase.cloud.StorageClient
import java.io.File

class UploadManager {
    fun uploadAudioFile(filePath: String, user: String) {
        val file = File(filePath)
        val storage = StorageClient.getInstance().bucket()
        val blob = storage.create("users/$user/uploads/${file.name}", file.inputStream())

        println("File uploaded to Firebase Storage with URL: ${blob.mediaLink}")
    }
}