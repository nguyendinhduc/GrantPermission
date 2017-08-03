package com.ducnd.grantpermission.ultis;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ducnd.grantpermission.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducnd on 8/3/17.
 */

public class PermissionUltis {
    public static boolean checkPermissionReadWriteExternalStore(Fragment fragment, int requestCode, boolean backCancelancelIf) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> pers = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(fragment.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                if (SharfUltis.getNumberDeniedPermission(fragment.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) > 0) {
                    showDialogConfirmOpenSetting(fragment, requestCode, backCancelancelIf);
                    return false;
                }
            }
            pers.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }else{
            SharfUltis.saveNumberDeniedPermission(fragment.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, 0);
        }

        if (ActivityCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(fragment.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (SharfUltis.getNumberDeniedPermission(fragment.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) > 0) {
                    showDialogConfirmOpenSetting(fragment, requestCode, backCancelancelIf);
                    return false;
                }
            }
            pers.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{
            SharfUltis.saveNumberDeniedPermission(fragment.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE, 0);
        }

        if (pers.size() == 0) {
            return true;
        }
        String arrs[] = new String[pers.size()];
        int index = 0;
        for (String per : pers) {
            arrs[index] = per;
            index++;
        }
        fragment.requestPermissions(arrs, requestCode);
        return false;
    }

    public static boolean checkPermissionReadWriteExternalStore(Activity activity, int requestCode, boolean backCancelancelIf) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> pers = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                if (SharfUltis.getNumberDeniedPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) > 0) {
                    showDialogConfirmOpenSetting(activity, requestCode, backCancelancelIf);
                    return false;
                }
            }
            pers.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }else{
            SharfUltis.saveNumberDeniedPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE, 0);
        }

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (SharfUltis.getNumberDeniedPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) > 0) {
                    showDialogConfirmOpenSetting(activity, requestCode, backCancelancelIf);
                    return false;
                }
            }
            pers.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{
            SharfUltis.saveNumberDeniedPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, 0);
        }

        if (pers.size() == 0) {
            return true;
        }
        String arrs[] = new String[pers.size()];
        int index = 0;
        for (String per : pers) {
            arrs[index] = per;
            index++;
        }
        ActivityCompat.requestPermissions(activity, arrs, requestCode);
        return false;
    }


    private static void showDialogConfirmOpenSetting(final Fragment fragment, final int requestCode, final boolean backCancelancelIf) {
        Dialog dialog = new ConfirmDialog(fragment.getActivity(), R.string.You_need_open_setting_to_grant_permission, new ConfirmDialog.IConfirmDialog() {
            @Override
            public void onClickCancel() {
                if (backCancelancelIf) {
                    fragment.getActivity().onBackPressed();
                }
            }

            @Override
            public void onClickOk() {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + fragment.getActivity().getPackageName()));
                fragment.startActivityForResult(intent, requestCode);
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    private static void showDialogConfirmOpenSetting(final Activity activity, final int requestCode, final boolean backCancelancelIf) {
        Dialog dialog = new ConfirmDialog(activity, R.string.You_need_open_setting_to_grant_permission, new ConfirmDialog.IConfirmDialog() {
            @Override
            public void onClickCancel() {
                if (backCancelancelIf) {
                    activity.onBackPressed();
                }
            }

            @Override
            public void onClickOk() {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, requestCode);
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static boolean checkPermission(Activity activity, String[] pernissions, int[] granted) {
        boolean result = true;
        for (int i = 0; i < granted.length; i++) {
            if (granted[i] == PackageManager.PERMISSION_DENIED) {
                result = false;
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, pernissions[i])) {
                    SharfUltis.inncreadNumberDeniedPermission(activity, pernissions[i], 1);
                }
            } else {
                SharfUltis.saveNumberDeniedPermission(activity, pernissions[i], 0);
            }
        }

        return result;
    }

    public static boolean checkPermissionOnResult(Activity activity, String[] pernissions) {
        boolean isSuccess = true;
        for (int i = 0; i < pernissions.length; i++) {
            if (ActivityCompat.checkSelfPermission(activity, pernissions[i])
                    == PackageManager.PERMISSION_DENIED) {
                isSuccess = false;
            }else {
                SharfUltis.saveNumberDeniedPermission(activity, pernissions[i], 0);
            }
        }

        return isSuccess;
    }

}
