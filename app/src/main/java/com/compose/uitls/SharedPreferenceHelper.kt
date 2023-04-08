package com.compose.uitls

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceHelper  @Inject constructor(@ApplicationContext val context: Context){

    fun getString(key:String,defaultVal:String=""){
        getDefaultSharedPreferences().getString(key,defaultVal)
    }
    fun setString(key:String, value: String){

        getDefaultSharedPreferencesEdit().putString(key,value).apply()
    }
    private fun getDefaultSharedPreferences(): SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    private fun getDefaultSharedPreferencesEdit(): SharedPreferences.Editor{
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }
}