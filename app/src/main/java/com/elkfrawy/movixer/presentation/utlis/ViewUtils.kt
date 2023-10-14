package com.elkfrawy.movixer.presentation.utlis

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.elkfrawy.movixer.R
import kotlin.math.sign

fun loadImage(context: Context, imageView: ImageView, url: String?) {

    val img = if (url == null) R.drawable.images else "https://image.tmdb.org/t/p/w500$url"

    Glide.with(context)
        .load(img)
        .thumbnail(Glide.with(context).load(R.drawable.spinner))
        .error(R.drawable.no_img)
        .into(imageView)
}

fun loadVideoThumbnail(context: Context, imageView: ImageView, url: String?){
    val img = "https://img.youtube.com/vi/${url}/maxresdefault.jpg"
    Glide.with(context)
        .load(img)
        .placeholder(R.drawable.spinner)
        .error(R.drawable.unavaliable_youtube)
        .fallback(R.drawable.unavaliable_youtube)
        .into(imageView)
}

fun loadUserImage(context: Context, imageView: ImageView, url: String?){

    val img = if (url == null) R.drawable.user else "https://image.tmdb.org/t/p/w500$url"
    val rb = Glide.with(context).asDrawable().sizeMultiplier(0.6f)

    Glide.with(context)
        .load(img)
        .thumbnail(rb)
        .thumbnail(Glide.with(context).load(R.drawable.spinner))
        .error(R.drawable.user)
        .thumbnail()
        .into(imageView)
}
fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}

fun popularityFormat(pop: Float): String{
    val number = pop.toInt().toString()

    return when (number.length) {
        3 or 2 or 1 -> "(${number}K)"
        4 -> "(${number[0]}.${number[1]}M)"
        else -> "($number)"
    }
}