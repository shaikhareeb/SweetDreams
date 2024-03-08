package model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import com.google.firebase.auth.UserRecord.CreateRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject

var currentAccounts = Array(1) { arrayOf("john","smith","john123","john@gmail.com","johnpwd") }

class AccountManager {
    lateinit var auth: FirebaseAuth;
    init {
        auth = FirebaseAuth.getInstance();
    }

    fun validateUser(email: String, password: String): Boolean {
        val json = JSONObject()
        json.put("email", email)
        json.put("password", password)
        json.put("returnSecureToken", true)
        var result = model.GetHttpBody(
            json,
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + model.apikey);

        return result != "NULL";
    }

    fun addUser(fname: String, lname: String, uname: String, email: String, pwd: String, pwd2: String): String {
        if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || email.isEmpty() || pwd.isEmpty() || pwd2.isEmpty()) {
            return "Fill in all of the fields before registering"
        }
        if (pwd != pwd2) return "Passwords do not match. Please try again";
        var record = UserRecord.CreateRequest();
        record.setEmail(email);
        record.setPassword(pwd);

        auth.createUser(record);
        return "";
    }
}