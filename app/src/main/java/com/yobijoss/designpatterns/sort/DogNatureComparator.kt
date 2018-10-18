package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog

class DogNatureComparator(comparator: DogComparator) : DogComparatorDecorator(comparator) {

    override fun compare(dog1: Dog, dog2: Dog): Int {
        val result = dog1.nature.compareTo(dog2.nature)
        return if (result == 0) result else super.compare(dog1, dog2)
    }
}
