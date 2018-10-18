package com.yobijoss.designpatterns.ui

import android.view.View
import com.yobijoss.designpatterns.R
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.model.DogSize
import kotlinx.android.synthetic.main.item_sick_dog.view.*

class SickDogViewHolder(private val inflatedView: View) : DogViewHolder(inflatedView) {

    override fun bind(dog: Dog) {
        inflatedView.nameTextView.text = dog.name
        inflatedView.ageTextView.text = dog.age.toString()
        inflatedView.titleEstado.setBackgroundResource(android.R.color.holo_red_light)

        inflatedView.sicknessTextView.text = dog.diseaseList.joinToString { it }

        //No damos perros chiquitos en adopción.
        val size = when (dog.size) {

            DogSize.LARGE -> R.string.large_size
            DogSize.MEDIUM -> R.string.medium_size
            DogSize.SMALL -> R.string.small_size
        }

        inflatedView.sizeTextView.setText(size)

        val title = "Perrito Enfermito ${dog.nature}"

        inflatedView.titleEstado.text = title

    }

}
