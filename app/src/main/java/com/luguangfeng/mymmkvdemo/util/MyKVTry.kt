package com.luguangfeng.mymmkvdemo.util

object MyKVTry {
    @JvmStatic
    external fun stringFromJNI() : String

    @JvmStatic
    external fun writeKV(key: String, value: String)

    @JvmStatic
    external fun readK(key: String) : String

    @JvmStatic
    external fun deleteK(key: String) : Boolean
}