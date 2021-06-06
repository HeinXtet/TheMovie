package com.deevvdd.tmdb.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
abstract class BaseActivity<VM : BaseViewModel<E>, E> : AppCompatActivity() {
    var skipInjection = false
    override fun onCreate(savedInstanceState: Bundle?) {
        if (!skipInjection)
            inject()
        super.onCreate(savedInstanceState)

        setContentView()

        getOrCreateViewModel().streamEvents().observeEvent(this, ::renderEvent)
    }

    abstract fun setContentView()

    abstract fun inject()

    abstract fun getOrCreateViewModel(): VM

    abstract fun renderEvent(event: E)
}
