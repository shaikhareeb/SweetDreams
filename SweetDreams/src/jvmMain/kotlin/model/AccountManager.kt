package model

var currentAccounts = Array(1) { arrayOf("john","smith","john123","john@gmail.com","johnpwd") }

class AccountManager {
    fun validateUser(username: String, pwd: String): Boolean {
        for (acct in currentAccounts) {
            if (acct[2] == username && acct[4] == pwd) {
                return true;
            }
        }
        return false;
    }

    fun addUser(fname: String, lname: String, uname: String, email: String, pwd: String, pwd2: String): String {
        if (pwd != pwd2) return "Passwords do not match. Please try again";
        currentAccounts += arrayOf(fname, lname, uname, email, pwd)
        return "";
    }
}