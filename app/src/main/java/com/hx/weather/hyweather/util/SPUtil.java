package com.hx.weather.hyweather.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import java.util.Map;

/**
 * Created by win10 on 2017/9/21.
 */

public class SPUtil {
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static final String spKey = "weather";

    private static SPUtil defaultInstance;
    private SPUtil() {}

//    private RxSocket() {
//        readSubject = new SerializedSubject(PublishSubject.create());
//        connectStatus = new SerializedSubject(PublishSubject.create());
//    }
//    public static RxSocket getInstance() {
//        RxSocket rxSocket = defaultInstance;
//        if (defaultInstance == null) {
//            synchronized (RxSocket.class) {
//                rxSocket = defaultInstance;
//                if (defaultInstance == null) {
//                    rxSocket = new RxSocket();
//                    defaultInstance = rxSocket;
//                }
//            }
//        }
//        return rxSocket;
//    }

    public static void init(Context context) {
        sp = context.getSharedPreferences(spKey, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SPUtil getInstance() {
        SPUtil spUtil = defaultInstance;
        if(defaultInstance == null) {
            synchronized (SPUtil.class) {
                spUtil = defaultInstance;
                if (defaultInstance == null) {
                    spUtil = new SPUtil();
                    defaultInstance = spUtil;
                }
            }
        }
        return spUtil;
    }

    public static String getString(String key, String value) {
        return sp.getString(key, value);
    }
    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static int getInt(String key, int value) {
        return sp.getInt(key, value);
    }
    public static int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public static float getFloat(String key, float value) {
        return sp.getFloat(key, value);
    }
    public static float getFloat(String key) {
        return sp.getFloat(key, -1);
    }

    public static boolean getBoolean(String key, boolean value) {
        return sp.getBoolean(key, value);
    }
    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static long getLong(String key, long value) {
        return sp.getLong(key, value);
    }
    public static long getLong(String key) {
        return sp.getLong(key, -1);
    }

    public static Map<String, ?> getAll() {
        return sp.getAll();
    }
    public static boolean contains(String key) {
        return sp.contains(key);
    }


    public static void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public static void remove(String key) {
        editor.remove(key);
        editor.apply();
    }

    public static void clear() {
        editor.clear();
        editor.apply();
    }
}
