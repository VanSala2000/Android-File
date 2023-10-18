package com.example.phillipinetouristaide

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    companion object {
        private const val PREF_NAME = "UserSession"
        private const val KEY_PASSWORD = "password"
        private const val KEY_EMAIL = "email"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    fun createLoginSession(email: String, password: String) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    fun getPassword(): String? {
        return pref.getString(KEY_PASSWORD, null)
    }

    fun setRememberMe(remember: Boolean) {
        pref.edit().putBoolean("rememberMe", remember).apply()
    }

    fun getRememberMe(): Boolean {
        return pref.getBoolean("rememberMe", false)
    }

    fun getEmail(): String? {
        return pref.getString(KEY_EMAIL, null)
    }

    fun logoutUser() {
        editor.clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
