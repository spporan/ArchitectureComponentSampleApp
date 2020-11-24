package com.poran.architecturecomponentsampleapp.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val SAVE_AT="SAVE_AT"
@Suppress("DEPRECATION")
class PreferenceProvider(context:Context) {

    private val appContext=context.applicationContext

    private val preferences:SharedPreferences
             get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveQuoteAt(saveAt:String){
        preferences.edit().putString(SAVE_AT,saveAt).apply()
    }
    fun getSaveAt():String?=preferences.getString(SAVE_AT,null)
}