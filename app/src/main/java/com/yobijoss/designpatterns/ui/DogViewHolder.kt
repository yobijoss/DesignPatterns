package com.yobijoss.designpatterns.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yobijoss.designpatterns.model.Dog

abstract class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dog: Dog);
}
