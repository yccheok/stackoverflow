package com.yocto.wetodo

import Constants.PREFERRED_THEME
import com.squareup.moshi.Moshi
import com.yocto.wetodo.extension.isNullOrTrimEmpty
import com.yocto.wetodo.theme.Theme


object WeTodoOptions {
    private const val THEME = "THEME"

    var theme: Theme = PREFERRED_THEME

    init {
        val sharedPreferences = WeTodoApplication.instance.getSharedPreferences()
        val moshi = Moshi.Builder().build()

        val jsonTheme = sharedPreferences.getString(THEME, null)

        if (!jsonTheme.isNullOrTrimEmpty()) {
            moshi.adapter(Theme::class.java).fromJson(jsonTheme)?.let {
                this.theme = it
            }
        }
    }

    fun saveToSharedPreferences(): Boolean {
        val sharedPreferences = WeTodoApplication.instance.getSharedPreferences()
        val editor = sharedPreferences.edit()
        // Create a Moshi instance
        val moshi = Moshi.Builder().build()

        val jsonTheme = moshi.adapter(Theme::class.java).toJson(theme)

        editor.putString(THEME, jsonTheme)

        editor.apply()

        return true
    }
}