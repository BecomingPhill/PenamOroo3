package com.thetechguys.penamoroo1p3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.thetechguys.penamoroo1p3.utils.StorageType;

/**
 * Created by Evan Anger on 8/13/14.
 */
public class PenamOrooApplicationSettings {
    SharedPreferences mSharedPreferences;

    public PenamOrooApplicationSettings(Context context) {


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        }

    public String getStoragePreference() {
        return mSharedPreferences.getString("Storage", StorageType.INTERNAL);
    }

    public void setSharedPreferences(String storageType) {
        mSharedPreferences
                .edit()
                .putString("Storage", storageType)
                .apply();


    }
}





