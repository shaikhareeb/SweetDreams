package model

import com.google.api.client.http.HttpResponseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord.CreateRequest
import org.json.JSONObject
import userinterface.AudioBar

var currentAccounts = Array(1) { arrayOf("john","smith","john123","john@gmail.com","johnpwd") }

class AccountManager {
    lateinit var auth: FirebaseAuth;
    lateinit var email: String;
    lateinit var password: String;

    init {
        auth = FirebaseAuth.getInstance();
        instance = this;
    }

    companion object {
        var instance: AccountManager? = null;
    }

    fun setUser(email : String, password: String) {
        this.email = email;
        this.password = password;
    }

    fun getUser(): String {
        return this.email
    }

    fun validateCurrentUser(email: String, password: String): Boolean {
        return (email == this.email && password == this.password)
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
            setUser(email, password);
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

        record.setDisplayName(fname + " " + lname)
        record.setUid(uname)
        record.setEmail(email);
        record.setPassword(pwd);

        try {
            auth.createUser(record);
        } catch (e: FirebaseAuthException) {
            return "This email/username has already been used."
        }
        return "";
    }

    fun getUserDisplayName() : String {
        if (auth.getUserByEmail(this.email).displayName == null) {
            return "Account Created Before Name Support"
        }
        return auth.getUserByEmail(this.email).displayName
    }

    fun getUsername() : String {
        if (auth.getUserByEmail(this.email).uid == null || auth.getUserByEmail(this.email).uid.length > 20) {
            return "Account Created Before Username Support"
        }
        return auth.getUserByEmail(this.email).uid
    }

    fun deleteUser(password: String): String {
        if (password != this.password) {
            return "Password is incorrect!"
        }
        var uid = auth.getUserByEmail(this.email)

        try {
            auth.deleteUser(uid.uid)
        } catch (e: HttpResponseException) {
            return "It is not currently possible to delete your account!"
        }
        return ""
    }

    fun forgotPassword() : String {
        return "Reset Password Link: ${auth.generatePasswordResetLink(this.email)}"
    }

    fun forgotPasswordEmail(mail: String) : String {
        return "Reset Password Link: ${auth.generatePasswordResetLink(mail)}"
    }
}