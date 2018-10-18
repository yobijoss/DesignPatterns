package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog

class DogAgeComparator(comparator: DogComparator) : DogComparatorDecorator(comparator) {

    override fun compare(dog1: Dog, dog2: Dog): Int {
        val result = dog1.age.compareTo(dog2.age)
        return if (result == 0) result else super.compare(dog1, dog2)
    }
}
