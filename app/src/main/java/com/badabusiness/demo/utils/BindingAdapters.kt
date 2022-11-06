package com.badabusiness.demo.utils

import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.badabusiness.demo.R
import com.badabusiness.demo.model.Note


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: AppCompatImageView,imageUrl:String?){
        if (imageUrl.isNullOrEmpty()){
            view.isVisible = false
            return
        }
        view.isVisible = true
        view.load(imageUrl){
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    @JvmStatic
    @BindingAdapter("updateMaxLine")
    fun handleMaxLine(view: TextView,note: Note?){
        view.text = note?.description
        if (note ==null ||note.imageUrl.isNullOrEmpty()){

            view.run {
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 2
            }
        }else{
            view.run {
                ellipsize = null
                maxLines = Int.MAX_VALUE
            }
        }
    }



}