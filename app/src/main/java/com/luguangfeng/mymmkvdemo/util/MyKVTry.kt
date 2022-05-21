package com.luguangfeng.mymmkvdemo.util

object MyKVTry {
    @JvmStatic
    external fun stringFromJNI() : String

    @JvmStatic
    external fun writeKV(key: String, value: String)
}