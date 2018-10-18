package com.yobijoss.designpatterns.repository

import androidx.lifecycle.MutableLiveData
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.model.DogSize
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class DogRepository {

    fun getDogList(): MutableLiveData<List<Dog>> {
        val dogListLiveData: MutableLiveData<List<Dog>> = MutableLiveData()

        launch {
            delay(1000)
            val dogList = listOf(
                    Dog("Juanito", 2, "Tranquilo", DogSize.LARGE),
                    Dog("Angelito", 3, "Curioso", DogSize.MEDIUM),
                    Dog("Puchurrin", 6, "Enojón", DogSize.SMALL, true, listOf("reflujo", "enanismo")),
                    Dog("Sparky", 6, "Flojo", DogSize.LARGE, true, listOf("reflujo", "enanismo")),
                    Dog("Pepino", 3, "Flojo", DogSize.MEDIUM),
                    Dog("Kenia", 1, "Triste", DogSize.SMALL, true, listOf("Ceguera")),
                    Dog("Pepinillo", 4, "Hiperactivo", DogSize.MEDIUM),
                    Dog("Calabacin", 1, "Hiperactivo", DogSize.SMALL)
            )
            withContext(Dispatchers.Main) {
                dogListLiveData.value = dogList;
            }
        }

        return dogListLiveData
    }
}
