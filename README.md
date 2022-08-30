# Remote-Configuration-Template

Fetch Remote Configurations in just few steps. Let the configuration class do your work

Initialize configuration class

    private val remoteConfiguration by lazy { RemoteConfiguration(this) }

Call function and get it's completion listener

    remoteConfiguration.checkRemoteConfig(internetHandler.isInternetConnected) {
            // Completion Listener
    }
