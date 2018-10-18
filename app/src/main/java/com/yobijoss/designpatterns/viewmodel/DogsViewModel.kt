package com.yobijoss.designpatterns.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.repository.DogRepository

class DogsViewModel : ViewModel() {

    var dogList = MutableLiveData<List<Dog>>()

    var dogRepository = DogRepository()

    fun updateDogList() {
        dogList = dogRepository.getDogList()
    }
}
