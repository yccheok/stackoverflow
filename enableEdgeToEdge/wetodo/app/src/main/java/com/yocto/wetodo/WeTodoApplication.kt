package com.yocto.wetodo

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager

class WeTodoApplication : MultiDexApplication(), DefaultLifecycleObserver {
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)

        WeTodoOptions.saveToSharedPreferences();
    }

    companion object {
        lateinit var instance: WeTodoApplication private set
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super<MultiDexApplication>.onCreate()

        instance = this

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        initLifecycleObserver()
    }

    private fun initLifecycleObserver() {
        val lifecycle: Lifecycle = ProcessLifecycleOwner.get().lifecycle
        lifecycle.removeObserver(this)
        lifecycle.addObserver(this)
    }

    fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }
}