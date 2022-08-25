package com.example.a4month1hw

import android.content.Context

class Prefs(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    fun saveBoardState() {
        preferences.edit().putBoolean("isBoardShown", true).apply()
    }

    fun isBoardShown(): Boolean {
        return preferences.getBoolean("isBoardShown", false)
    }
    fun saveEditText(name: String?) {
        preferences.edit().putString("text", name).apply()
    }

    fun isEditText(): String? {
        return preferences.getString("text", "")
    }

    fun saveImageView(image: String?) {
        preferences.edit().putString("image", image).apply()
    }

    fun isImageView(): String? {
        return preferences.getString("image", "")
    }


}