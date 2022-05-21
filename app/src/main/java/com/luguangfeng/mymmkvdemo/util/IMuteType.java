package com.luguangfeng.mymmkvdemo.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import androidx.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(SOURCE)
@Target({PARAMETER, FIELD, METHOD})
@IntDef(value = {IMuteType.MENU_TYPE_TEST, IMuteType.MENU_TYPE_SP,
        IMuteType.MENU_TYPE_MMKV, IMuteType.MENU_TYPE_MY_KV})
public @interface IMuteType {
    int MENU_TYPE_TEST = 1;
    int MENU_TYPE_SP = 2;
    int MENU_TYPE_MMKV = 3;
    int MENU_TYPE_MY_KV = 4;
}
