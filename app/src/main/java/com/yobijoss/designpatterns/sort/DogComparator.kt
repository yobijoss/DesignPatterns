package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog

//Component
interface DogComparator : Comparator<Dog> {
    override fun compare(dog1: Dog, dog2: Dog) : Int
}