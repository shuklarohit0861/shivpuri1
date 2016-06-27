package com.mcshivpuri.shivpurimunicipalcouncil;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by thero on 24-06-2016.
 */
public class Settings extends PreferenceActivity implements Preference.OnPreferenceChangeListener {


    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
