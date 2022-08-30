package dev.epegasus.remoteconfigurationtemplate.helper.manager

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dev.epegasus.remoteconfigurationtemplate.R
import dev.epegasus.remoteconfigurationtemplate.helper.utils.LogUtils.showLog
import dev.epegasus.remoteconfigurationtemplate.helper.utils.LogUtils.showRemoteConfigLog

class RemoteConfiguration(private val context: Context) {

    private val dummyValue = "dummyValue"

    fun checkRemoteConfig(isInternetConnected: Boolean, callback: () -> Unit) {
        if (isInternetConnected) {
            val remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 2 }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
            fetchRemoteValues(callback)
        } else {
            showLog(context, "checkRemoteConfig", "RemoteConfigUtil", "Internet Not Found!")
            callback.invoke()
        }
    }

    private fun fetchRemoteValues(callback: () -> Unit) {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener { updateRemoteValues(callback) }
    }

    private fun updateRemoteValues(callback: () -> Unit) {
        val remoteConfig = Firebase.remoteConfig

        // Save this value anywhere
        val value = remoteConfig[dummyValue].asLong().toInt()
        showRemoteConfigLog(context, "updateRemoteValues", "RemoteConfigUtil", "INTER_SPLASH: $value")

        showLog(context, "updateRemoteValues", "RemoteConfigUtil", "Fetched Successfully")
        callback.invoke()
    }
}