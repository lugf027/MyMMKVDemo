package com.luguangfeng.mymmkvdemo.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.luguangfeng.mymmkvdemo.util.IMuteType;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<String>> mLogsLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> mRootDirLiveData = new MutableLiveData<>("");
    private final MutableLiveData<Integer> mMenuType = new MutableLiveData<>(0);

    private MMKV mMMKV;
    private SharedPreferences mSP;

    public void init(Context context) {
        mRootDirLiveData.setValue(MMKV.initialize(context));
        mMMKV = MMKV.defaultMMKV();
        mSP = context.getSharedPreferences("MyMMKVTry_SP", Context.MODE_PRIVATE);
    }

    public void setMenuType(@IMuteType int menuType) {
        appendLogs("[setMenuType] pre:" + mMenuType.getValue() + " newMenuType:" + menuType);
        mMenuType.setValue(menuType);
    }

    public void handleBtnWriteClick(String key, String value) {
        if (checkKVParamInvalid(key, "key") || checkKVParamInvalid(value, "value")) {
            return;
        }
        mMMKV.encode(key, value);
        appendLogs("[handleBtnWriteClick] key:" + key + " value:" + value);

        handleBtnWriteTest1000Times();
    }

    public void handleBtnReadClick(String key) {
        if (checkKVParamInvalid(key, "key")) {
            return;
        }
        String value = mMMKV.decodeString(key);
        appendLogs("[handleBtnReadClick] key:" + key + " value:" + value);
    }

    public void handleBtnDeleteClick(String key) {
        if (checkKVParamInvalid(key, "key")) {
            return;
        }
        if (!mMMKV.containsKey(key)) {
            appendLogs("[handleBtnDeleteClick] key not exists:" + key);
        }
        mMMKV.removeValueForKey(key);
        appendLogs("[handleBtnDeleteClick] removeKey:" + key);
        if (mMMKV.containsKey(key)) {
            appendLogs("[handleBtnDeleteClick] key still exists:" + key);
        }
    }

    private boolean checkKVParamInvalid(String param, String type) {
        if (TextUtils.isEmpty(param)) {
            appendLogs("[checkKVParamInvalid] type:" + type);
            return true;
        }
        return false;
    }

    // 测试写入 1000 次
    private void handleBtnWriteTest1000Times() {
        writeMMKV1000Times();
        writeSP1000Times();
    }

    public void writeMMKV1000Times() {
        long timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            mMMKV.putString("key_" + i, "value_" + i);
        }
        long timeEnd = System.nanoTime();
        appendLogs("[writeMMKV1000Times] consume ns:" + (timeEnd - timeStart));
    }

    public void writeSP1000Times() {
        SharedPreferences.Editor editor = mSP.edit();
        long timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            editor.putString("key_" + i, "value_" + i);
            editor.commit();
        }
        long timeEnd = System.nanoTime();
        appendLogs("[writeSP1000Times] consume ns:" + (timeEnd - timeStart));
    }

    // 实时行为记录
    private void appendLogs(String log) {
        ArrayList<String> logsNow = mLogsLiveData.getValue();
        if (logsNow == null) {
            logsNow = new ArrayList<>();
        }
        if (logsNow.size() > 15) {
            logsNow.remove(0);
        }
        logsNow.add(log);
        mLogsLiveData.setValue(logsNow);
    }

    // 对外接口
    public MutableLiveData<String> getRootDirLiveData() {
        return mRootDirLiveData;
    }

    public MutableLiveData<ArrayList<String>> getLogsLiveData() {
        return mLogsLiveData;
    }
}
