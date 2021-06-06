package com.deevvdd.tmdb.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
abstract class BaseFragment<VM : BaseViewModel<E>, E> :
    Fragment {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.filterTouchesWhenObscured = true
        getOrCreateViewModel().streamEvents().observeEvent(viewLifecycleOwner, ::renderEvent)
    }

    abstract fun getOrCreateViewModel(): VM

    abstract fun renderEvent(event: E)
}
