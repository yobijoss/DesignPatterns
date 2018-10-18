package com.yobijoss.designpatterns.ui

import android.view.View
import com.yobijoss.designpatterns.R
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.model.DogSize
import kotlinx.android.synthetic.main.item_healthy_dog.view.*

class HealthyDogViewHolder(private val inflatedView: View) : DogViewHolder(inflatedView) {

    override fun bind(dog: Dog) {

        inflatedView.nameTextView.text = dog.name
        inflatedView.ageTextView.text = dog.age.toString()

        val size = when (dog.size) {
            DogSize.LARGE -> R.string.large_size
            DogSize.MEDIUM -> R.string.medium_size
            DogSize.SMALL -> R.string.small_size
        }

        inflatedView.sizeTextView.setText(size)


        val color = if (dog.size == DogSize.SMALL) {
            android.R.color.holo_blue_light
        } else {
            android.R.color.holo_blue_dark
        }

        inflatedView.titleEstado.setBackgroundResource(color)

        val title = "Perrito ${inflatedView.context.getString(size)} Muy Sano :)"
        inflatedView.titleEstado.text = title
    }

}