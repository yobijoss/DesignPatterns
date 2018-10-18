package com.yobijoss.designpatterns.model

data class Dog(val name: String, val age: Int, val nature: String, val size: DogSize, val hasDiseases: Boolean = false, val diseaseList: List<String> = ArrayList())