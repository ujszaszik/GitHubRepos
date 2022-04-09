package com.withintent.githubrepos.application

import com.jakewharton.threetenabp.AndroidThreeTen
import com.withintent.network.NetworkUtil
import com.withintent.githubrepos.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class GitHubReposApplication : android.app.Application() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        networkUtil.registerNetworkCallback()
    }
}