package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog

//Decorator
abstract class DogComparatorDecorator(private val comparator : DogComparator) : DogComparator {
    override fun compare(dog1: Dog, dog2: Dog): Int {
        return comparator.compare(dog1, dog2)
    }
}

