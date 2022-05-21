package com.luguangfeng.mymmkvdemo.io;

import android.content.Context;
import com.tencent.mmkv.MMKV;

public class IOMMKVImpl implements IReadWrite{
    private final MMKV mMMKV;

    public IOMMKVImpl() {
        mMMKV = MMKV.defaultMMKV();
    }

    @Override public boolean writeKV(String key, String value) {
        mMMKV.encode(key, value);
        return true;
    }

    @Override public String readK(String key) {
        return mMMKV.decodeString(key);
    }

    @Override public boolean deleteKey(String key) {
        if (!mMMKV.containsKey(key)) {
            return false;
        }
        mMMKV.removeValueForKey(key);
        return true;
    }

    @Override public long write1000Times() {
        long timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            mMMKV.putString("key_" + i, "value_" + i);
        }
        long timeEnd = System.nanoTime();
        return timeEnd - timeStart;
    }
}
