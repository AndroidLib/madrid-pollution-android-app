package com.greenlionsoft.pollution.madrid.dependencies;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import permissions.IPermissionsRepository;

public class PermissionsRepository implements IPermissionsRepository {

    private Context mContext;

    public PermissionsRepository() {

        mContext = AppInjector.getInstance().getApplicationContext();
    }


    @Override
    public boolean hasAppPermissionsToShowUserLocation() {


        return PackageManager.PERMISSION_DENIED != ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);

    }


}
