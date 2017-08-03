package com.ducnd.grantpermission;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ducnd.grantpermission.ultis.PermissionUltis;

/**
 * Created by ducnd on 8/4/17.
 */

public class DemoWithFragmentActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_with_fragmetn);

        FragmentDemo fragmentDemo = new FragmentDemo();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content, fragmentDemo, FragmentDemo.class.getName());
        transaction.commit();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUltis.checkPermission(this, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
