package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog

class DogSizeComparator(comparator: DogComparator) : DogComparatorDecorator(comparator) {

    override fun compare(dog1: Dog, dog2: Dog): Int {
        val result = dog1.size.compareTo(dog2.size)
        return if (result == 0) result else super.compare(dog1, dog2)
    }
}
