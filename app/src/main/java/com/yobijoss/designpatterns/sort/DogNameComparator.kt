package com.yobijoss.designpatterns.sort

import com.yobijoss.designpatterns.model.Dog

//ConcreteComponent
class DogNameComparator : DogComparator {
    override fun compare(dog1: Dog, dog2: Dog): Int {
        return dog1.name.compareTo(dog2.name);
    }
}