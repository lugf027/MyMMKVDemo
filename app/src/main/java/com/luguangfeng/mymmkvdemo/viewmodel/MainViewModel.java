package com.luguangfeng.mymmkvdemo.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.luguangfeng.mymmkvdemo.io.IOMMKVImpl;
import com.luguangfeng.mymmkvdemo.io.IOMyKVImpl;
import com.luguangfeng.mymmkvdemo.io.IOSPImpl;
import com.luguangfeng.mymmkvdemo.io.IReadWrite;
import com.luguangfeng.mymmkvdemo.util.IMuteType;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<String>> mLogsLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> mRootDirLiveData = new MutableLiveData<>("");
    private final MutableLiveData<Integer> mMenuType = new MutableLiveData<>(0);

    private final HashMap<Integer, IReadWrite> mIOMap = new HashMap<>();

    public void init(Context context) {
        mRootDirLiveData.setValue(MMKV.initialize(context));
        mIOMap.put(IMuteType.MENU_TYPE_SP, new IOSPImpl(context));
        mIOMap.put(IMuteType.MENU_TYPE_MMKV, new IOMMKVImpl());
        mIOMap.put(IMuteType.MENU_TYPE_MY_KV, new IOMyKVImpl());
    }

    public void setMenuType(@IMuteType int menuType) {
        appendLogs("[setMenuType] pre:" + mMenuType.getValue() + " newMenuType:" + menuType);
        mMenuType.setValue(menuType);
    }

    public void handleBtnWriteClick(String key, String value) {
        if (checkKVParamInvalid(key, "key") || checkKVParamInvalid(value, "value")) {
            return;
        }
        Objects.requireNonNull(mIOMap.get(mMenuType.getValue())).writeKV(key, value);
        appendLogs("[handleBtnWriteClick] key:" + key + " value:" + value);

        handleBtnWriteTest1000Times();
    }

    public void handleBtnReadClick(String key) {
        if (checkKVParamInvalid(key, "key")) {
            return;
        }
        String value = Objects.requireNonNull(mIOMap.get(mMenuType.getValue())).readK(key);
        appendLogs("[handleBtnReadClick] key:" + key + " value:" + value);
    }

    public void handleBtnDeleteClick(String key) {
        if (checkKVParamInvalid(key, "key")) {
            return;
        }
        Objects.requireNonNull(mIOMap.get(mMenuType.getValue())).deleteKey(key);
        appendLogs("[handleBtnDeleteClick] removeKey:" + key);
    }

    private boolean checkKVParamInvalid(String param, String type) {
        if (TextUtils.isEmpty(param)) {
            appendLogs("[checkKVParamInvalid] type:" + type);
            return true;
        }
        return false;
    }

    // 测试写入 1000 次
    public void handleBtnWriteTest1000Times() {
        for (IReadWrite io : mIOMap.values()) {
            long time = io.write1000Times();
            appendLogs("[handleBtnWriteTest1000Times] class:" + io.getClass().getName() +
                    " timeConsume:" + time);
        }
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
