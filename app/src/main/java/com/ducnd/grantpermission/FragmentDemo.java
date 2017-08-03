package com.ducnd.grantpermission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ducnd.grantpermission.ultis.PermissionUltis;

/**
 * Created by ducnd on 8/4/17.
 */

public class FragmentDemo extends Fragment implements FragmentCompat.OnRequestPermissionsResultCallback, View.OnClickListener {
    private static final int REQUEST_CODE_PERMISSON_STORE_AT_FRAGMENT = 101;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmetn_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_onClick_permission).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_onClick_permission:
                if (PermissionUltis.checkPermissionReadWriteExternalStore(this, REQUEST_CODE_PERMISSON_STORE_AT_FRAGMENT, false)) {
                    Toast.makeText(getContext(), "permission grant store ok at fragment", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSON_STORE_AT_FRAGMENT) {
            if (PermissionUltis.checkPermissionOnResult(getActivity(), new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE})) {
                Toast.makeText(getActivity(), "permission grant store ok from open setting from fragment", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "permission denied store ok from open setting from fragment", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PERMISSON_STORE_AT_FRAGMENT) {
            if (PermissionUltis.checkPermissionOnResult(getActivity(), new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE})) {
                Toast.makeText(getContext(), "permission grant store ok from open setting", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "permission denied store ok from open setting", Toast.LENGTH_LONG).show();
            }
        }
    }
}
