package model

import com.google.cloud.storage.Storage
import com.google.cloud.storage.Bucket
import com.google.firebase.cloud.StorageClient
import java.io.File

class UploadManager {
    private val storage: Bucket = StorageClient.getInstance().bucket()
    fun uploadAudioFile(filePath: String, user: String) {
        val file = File(filePath)
        val blob = storage.create("users/$user/uploads/${file.name}", file.inputStream())

        println("File uploaded to Firebase Storage with URL: ${blob.mediaLink}")
    }

    fun getAudioFiles(user: String): List<String> {
        val files = mutableListOf<String>()

        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            val fileName = blob.name.substringAfterLast('/')
            files.add(fileName)
        }

        return files
    }

    fun getAudioLinks(user: String): List<String> {
        val files = mutableListOf<String>()

        val blobs = storage.list(Storage.BlobListOption.prefix("users/$user/uploads/")).iterateAll()
        blobs.forEach { blob ->
            files.add(blob.mediaLink)
        }

        return files
    }}