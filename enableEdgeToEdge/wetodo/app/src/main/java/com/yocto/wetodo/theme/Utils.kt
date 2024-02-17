package com.yocto.wetodo.theme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.yocto.wetodo.R
import com.yocto.wetodo.WeTodoOptions

fun updateTheme(context: Context) {
    when (WeTodoOptions.theme) {
        Theme.WHITE -> {
            // Will always pick the theme from values/themes.xml (Theme.WeTodo)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            context.setTheme(R.style.Theme_WeTodo_NoActionBar)
        }
        Theme.DARK -> {
            // Will always pick the theme from values-night/themes.xml (Theme.WeTodo)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            context.setTheme(R.style.Theme_WeTodo_NoActionBar)

        }
        Theme.SYSTEM -> {
            // Will either pick theme from  values/themes.xml or values-night/themes.xml (Theme.WeTodo)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            context.setTheme(R.style.Theme_WeTodo_NoActionBar)
        }
    }
}