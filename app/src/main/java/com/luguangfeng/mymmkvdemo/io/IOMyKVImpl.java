package com.luguangfeng.mymmkvdemo.io;

import com.luguangfeng.mymmkvdemo.util.MyKVTry;

public class IOMyKVImpl implements IReadWrite {
    @Override public boolean writeKV(String key, String value) {
        MyKVTry.writeKV(key, value);
        return true;
    }

    @Override public String readK(String key) {
        return MyKVTry.readK(key);
    }

    @Override public boolean deleteKey(String key) {
        return MyKVTry.deleteK(key);
    }

    @Override public long write1000Times() {
        long timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            MyKVTry.writeKV("key_" + i, "value_" + i);
        }
        long timeEnd = System.nanoTime();
        return timeEnd - timeStart;
    }
}
