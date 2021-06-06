package com.deevvdd.tmdb.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
object ImageBinding {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(
            view
        )
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadCircleImage(view: CircleImageView, url: String?) {
        Glide.with(view.context).load(url).into(
            view
        )
    }
}
