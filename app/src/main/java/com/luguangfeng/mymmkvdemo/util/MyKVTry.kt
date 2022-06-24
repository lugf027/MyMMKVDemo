package com.luguangfeng.mymmkvdemo.util

import android.content.Context

object MyKVTry {
    @JvmStatic
    fun initIOFile(context: Context) {
        initIOFilePath(context.filesDir.absolutePath + "/my_kv")
    }

    @JvmStatic
    private external fun initIOFilePath(filePath: String)

    @JvmStatic
    external fun stringFromJNI() : String

    @JvmStatic
    external fun writeKV(key: String, value: String)

    @JvmStatic
    external fun readK(key: String) : String

    @JvmStatic
    external fun deleteK(key: String) : Boolean
}