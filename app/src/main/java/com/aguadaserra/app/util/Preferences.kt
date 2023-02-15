package com.aguadaserra.app.util

import android.content.Context
import com.aguadaserra.app.global_model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Preferences(context: Context?) {

    private var preferences = context?.getSharedPreferences("high.preference", 0)
    private var editor = preferences?.edit()

    operator fun set(key: String?, value: String?) {
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun setListNotifyDataCompare(list: List<User>) {
        val gson = Gson()
        val json = gson.toJson(list)
        set("arrayNotifyCompare", json)
    }

    fun getListNotifyDataCompare(): List<User>? {
        var arrayItems: List<User>? = null
        val serializedObject = preferences?.getString("arrayNotifyCompare", "")
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<User>>() {}.type
            arrayItems = gson.fromJson<List<User>>(serializedObject, type)
        }
        return arrayItems
    }

    fun saveInstanceTokenFcm(key: String?, value: String) {
        editor?.putString(key, value)
        editor!!.commit()
    }

    fun getInstanceTokenFcm(): String {
        return preferences!!.getString("token", "")!!
    }

    fun storeInt(key: String?, value: Int) {
        editor?.putInt(key, value)
        editor!!.commit()
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return preferences!!.getInt(key, defaultValue)
    }


    fun setUserData(user: User?) {
        val data = Gson().toJson(user)
        editor!!.putString("getData", data)
        editor!!.commit()
    }

    fun getUserData(): User? {
        val user: User
        val gson = Gson()
        val data = preferences!!.getString("getData", "")
        return if (data != null && data.isNotEmpty()) {
            user = gson.fromJson(data, User::class.java)
            user
        } else null
    }

    fun setLogin(enable: Boolean){
        editor!!.putBoolean("login", enable)
        editor!!.commit()
    }

    fun getLogin(): Boolean{
        return preferences?.getBoolean("login", false)!!
    }

    fun clearUserData(){
        editor?.remove("getData")
        editor?.remove("login")
        editor?.remove("token")
        editor?.remove("arrayNotifyCompare")
        editor!!.commit()
    }


    companion object {
        /**
         * Preferences
         */

        const val ENTERING_FIRST_TIME = "EnteringFirstTime"
    }
}