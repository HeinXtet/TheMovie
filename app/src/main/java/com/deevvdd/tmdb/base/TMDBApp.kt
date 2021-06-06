package com.deevvdd.tmdb.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@HiltAndroidApp
class TMDBApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
