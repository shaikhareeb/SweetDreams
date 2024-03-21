package model

import com.google.api.client.http.HttpResponseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord.CreateRequest
import org.json.JSONObject

var currentAccounts = Array(1) { arrayOf("john","smith","john123","john@gmail.com","johnpwd") }

class AccountManager {
    lateinit var auth: FirebaseAuth;
    lateinit var email: String;

    init {
        auth = FirebaseAuth.getInstance();
    }

    fun setUser(email : String) {
        this.email = email;
    }

    fun getUser(): String {
        return this.email
    }

    fun validateUser(email: String, password: String): Boolean {
        val json = JSONObject()
        json.put("email", email)
        json.put("password", password)
        json.put("returnSecureToken", true)
        var result = PostHttpBody(
            json,
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apikey);
        if (result != "NULL"){
            setUser(email);
        }
        return result != "NULL";
    }

    fun addUser(fname: String, lname: String, uname: String, email: String, pwd: String, pwd2: String): String {
        if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || email.isEmpty() || pwd.isEmpty() || pwd2.isEmpty()) {
            return "Fill in all of the fields before registering"
        }
        if (pwd.length < 8) return "Password is too short"
        if (pwd != pwd2) return "Passwords do not match. Please try again";
        var record = CreateRequest();

        record.setEmail(email);
        record.setPassword(pwd);

        auth.createUser(record);
        return "";
    }

    fun deleteUser(uid: String): String {
        try {
            auth.deleteUser(uid)
        } catch (e: HttpResponseException) {
            return "Wrong user id or not possible to delete account"
        }
        return ""
    }

    fun forgotPassword() : String {
        return auth.generatePasswordResetLink(email)
    }

    fun forgotPasswordEmail(mail: String) : String {
        return auth.generatePasswordResetLink(mail)
    }
}