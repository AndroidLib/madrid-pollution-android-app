package com.greenlionsoft.pollution.madrid.dependencies.compatibility;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

import compatibility.ICompatibility;

public class Compatibility implements ICompatibility {

    private Context mContext;

    public Compatibility() {
        mContext = AppInjector.getInstance().getApplicationContext();
    }


    @Override
    public int isThisDeviceCompatible() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(mContext);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                return COMPATIBILITY_USER_RESOLVABLE;
            } else {
                return COMPATIBILITY_FAILED;
            }
        }
        return COMPATIBILITY_OK;
    }
}
