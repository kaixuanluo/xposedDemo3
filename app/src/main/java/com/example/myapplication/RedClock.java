package com.example.myapplication;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.fakeLocation.fake.FakeLocation;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class RedClock implements IXposedHookLoadPackage {

    private String TAG = getClass().getSimpleName();

    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.example.myapplication")) {
            Log.d(TAG, "lpparam.packageName.equals( com.example.myapplication )");
        } else {
            Log.d(TAG, "lpparam.packageName not equals( com.example.myapplication )");
            return;
        }

        Class clazz = lpparam.classLoader.loadClass("com.example.myapplication.MainActivity");

        findAndHookMethod(clazz, "setMainTestText", TextView.class, String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "afterHookedMethod(MethodHookParam param) " + param.toString());
                TextView tv = (TextView) param.args[0];
                String text = "已经被xPosed 了";
                tv.setText(text + " :)");
                tv.setTextColor(Color.RED);
            }
        });

        new FakeLocation().handleLoadPackage(lpparam);
    }
}
