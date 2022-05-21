package com.luguangfeng.mymmkvdemo.io;

public interface IReadWrite {
    /**
     * 写入 key 映射
     *
     * @return false if write fail
     */
    boolean writeKV(String key, String value);

    /**
     * 读取 key 映射
     *
     * @return null if read fail or no such key
     */
    String readK(String key);

    /**
     * 删除 key 映射
     *
     * @return false if delete fail or no such key
     */
    boolean deleteKey(String key);

    /**
     * 连续写入 1000 次
     *
     * @return time consume during write
     */
    long write1000Times();
}
