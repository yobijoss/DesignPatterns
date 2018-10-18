package com.yobijoss.designpatterns.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yobijoss.designpatterns.R
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.sort.DogListSorter
import com.yobijoss.designpatterns.sort.Sort

class DogAdapter(private val dogs: List<Dog>, sort: Sort) : RecyclerView.Adapter<DogViewHolder>() {
    private val sickDog = 0;
    private val healthyDog = 1;

    init {
        DogListSorter().sortDogList(sort, dogs);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val viewRes = when (viewType) {
            sickDog -> R.layout.item_sick_dog
            else -> R.layout.item_healthy_dog
        }

        val inflatedView = LayoutInflater.from(parent.context).inflate(viewRes, parent, false)

        return when (viewType) {
            sickDog -> SickDogViewHolder(inflatedView)
            else -> HealthyDogViewHolder(inflatedView)
        }
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogs[position])
    }

    override fun getItemViewType(position: Int): Int = when {
        dogs[position].hasDiseases -> sickDog
        else -> healthyDog
    }

    override fun getItemCount(): Int = dogs.size
}
