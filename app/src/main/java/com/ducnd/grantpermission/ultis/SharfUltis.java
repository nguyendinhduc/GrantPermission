package com.ducnd.grantpermission.ultis;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by ducnd on 8/3/17.
 */

public class SharfUltis {
    public static final String SYSTEM_CONFIG = "SYSTEM_CONFIG";

    public static int getNumberDeniedPermission(Context context, String permissionType) {
        SharedPreferences sf = context.getSharedPreferences(SYSTEM_CONFIG, Context.MODE_PRIVATE);
        Log.d("SharfUltis", "getNumberDeniedPermission type: " + permissionType + " number: " + sf.getInt(permissionType, 0));
        return sf.getInt(permissionType, 0);
    }

    public static void inncreadNumberDeniedPermission(Context context, String permissionType, int numberIncrease) {
        SharedPreferences sf = context.getSharedPreferences(SYSTEM_CONFIG, Context.MODE_PRIVATE);
        int numberCurrent = sf.getInt(permissionType, 0);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt(permissionType, numberIncrease + numberCurrent);
        editor.apply();
    }

    public static void saveNumberDeniedPermission(Context context, String permissionType, int number) {
        SharedPreferences sf = context.getSharedPreferences(SYSTEM_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt(permissionType, number);
        editor.apply();
    }
}

