package dev.epegasus.remoteconfigurationtemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.epegasus.remoteconfigurationtemplate.databinding.ActivityMainBinding
import dev.epegasus.remoteconfigurationtemplate.helper.manager.InternetHandler
import dev.epegasus.remoteconfigurationtemplate.helper.manager.RemoteConfiguration
import dev.epegasus.remoteconfigurationtemplate.helper.utils.GeneralUtils.showToast

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val internetHandler by lazy { InternetHandler(this) }
    private val remoteConfiguration by lazy { RemoteConfiguration(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        remoteConfiguration.checkRemoteConfig(internetHandler.isInternetConnected) {
            // Completion Listener
            showToast(this, "Fetched Successfully")
        }
    }
}