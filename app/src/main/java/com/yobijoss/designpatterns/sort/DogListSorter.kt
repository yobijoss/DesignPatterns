package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog
import java.util.*

class DogListSorter {
    fun sortDogList(sort: Sort, dogList: List<Dog>) {
        when (sort) {
            Sort.NAME -> sortByName(dogList)
            Sort.AGE -> sortByAge(dogList)
            Sort.NATURE -> sortByNature(dogList)
            Sort.SIZE -> sortBySize(dogList)
        }
    }

    private fun sortByName(dogList: List<Dog>) {
        Collections.sort(dogList, DogNameComparator())
    }

    private fun sortByAge(dogList: List<Dog>) {
        Collections.sort(dogList, DogAgeComparator(DogNameComparator()))
    }

    private fun sortByNature(dogList: List<Dog>) {
        Collections.sort(dogList, DogNatureComparator(DogAgeComparator(DogNameComparator())))
    }

    private fun sortBySize(dogList: List<Dog>) {
        Collections.sort(dogList, DogSizeComparator(DogAgeComparator(DogNameComparator())))
    }

}