package com.example.a4month1hw.glide

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.GlideYu(uri: String){
   Glide.with(this).load(uri).circleCrop().into(this)
}