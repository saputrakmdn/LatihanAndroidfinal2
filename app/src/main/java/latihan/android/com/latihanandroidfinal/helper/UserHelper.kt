package latihan.android.com.latihanandroidfinal.helper

import android.content.Context
import android.preference.Preference
import android.preference.PreferenceManager

class UserHelper(ctx: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
    var StatusSplash = preferences.getBoolean("SPLASH", false)
        set(value) = preferences.edit().putBoolean("SPLASH", value).apply()

    var statusLogin = preferences.getBoolean("LOGIN", false)
    set(value) = preferences.edit().putBoolean("LOGIN", value).apply()
}