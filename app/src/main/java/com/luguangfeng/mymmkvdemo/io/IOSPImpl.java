package com.luguangfeng.mymmkvdemo.io;

import android.content.Context;
import android.content.SharedPreferences;

public class IOSPImpl implements IReadWrite {
    private final SharedPreferences mSP;

    public IOSPImpl(Context context) {
        mSP = context.getSharedPreferences(IOSPImpl.class.getName(), Context.MODE_PRIVATE);
    }

    @Override public boolean writeKV(String key, String value) {
        return false;
    }

    @Override public String readK(String key) {
        return null;
    }

    @Override public boolean deleteKey(String key) {
        return false;
    }

    @Override public long write1000Times() {
        SharedPreferences.Editor editor = mSP.edit();
        long timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            editor.putString("key_" + i, "value_" + i);
            editor.commit();
        }
        long timeEnd = System.nanoTime();
        return timeEnd - timeStart;
    }
}
