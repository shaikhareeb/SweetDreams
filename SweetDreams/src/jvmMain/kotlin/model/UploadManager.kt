package model

import com.google.cloud.storage.Storage
import com.google.firebase.cloud.StorageClient
import java.io.File

class UploadManager {
    fun uploadAudioFile(filePath: String, user: String) {
        val file = File(filePath)
        val storage = StorageClient.getInstance().bucket()
        val blob = storage.create("users/$user/uploads/${file.name}", file.inputStream())

        println("File uploaded to Firebase Storage with URL: ${blob.mediaLink}")
    }

    fun getAudioFiles(user: String): List<String> {
        val storage = StorageClient.getInstance().bucket()
        val files = mutableListOf<String>()

        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            files.add(blob.name)
        }

        return files
    }
}