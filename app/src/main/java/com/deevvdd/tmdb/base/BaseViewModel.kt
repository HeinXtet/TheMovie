package com.deevvdd.tmdb.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
abstract class BaseViewModel<E> : ViewModel() {

    private val events = MutableLiveEvent<E>()

    /**
     * expose for testing. no need to use in production code
     */
    fun getEvents(): LiveEvent<E> = events

    /**
     *  emit one time events to the view
     *  */
    protected fun emitEvent(event: E) {
        if (shouldEmit(event)) {
            viewModelScope.launch {
                events.value = Event(event)
            }
        }
    }

    /**
     * override this to skip a particular event type
     */
    protected open fun shouldEmit(event: E): Boolean {
        return true
    }

    /**
     * method to observe event
     */
    fun streamEvents(): LiveData<Event<E>> = events
}
