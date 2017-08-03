package com.ducnd.grantpermission;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ducnd.grantpermission.ultis.PermissionUltis;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int REQUEST_CODE_PERMISSON_STORE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_check_permissioin_store).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_permissioin_store:
                if (PermissionUltis.checkPermissionReadWriteExternalStore(this, REQUEST_CODE_PERMISSON_STORE, false)) {
                    Toast.makeText(this, "permission store ok", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_fragment:
                Intent intent = new Intent();
                intent.setClass(this, DemoWithFragmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isOkAllPermissionRequest = PermissionUltis.checkPermission(this, permissions, grantResults);
        if (isOkAllPermissionRequest) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSON_STORE:
                    Toast.makeText(this, "permission grant store ok", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PERMISSON_STORE) {
            if (PermissionUltis.checkPermissionOnResult(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE})) {
                Toast.makeText(this, "permission grant store ok from open setting", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "permission denied store ok from open setting", Toast.LENGTH_LONG).show();
            }
        }
    }
}
