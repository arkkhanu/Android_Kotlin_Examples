package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Random

object FakeRepository {
    private val fruiteNames : List<String> = listOf(
        "Apple","Orange","Kiwi","Grapes","Fig","Pear","Strawberry","Raspberry")

    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        get() = _currentRandomFruitName

    init {
        _currentRandomFruitName.value = fruiteNames.first()
    }

    fun getRandomFruitName():String{
        val random = Random()
        return fruiteNames[random.nextInt(fruiteNames.size)]
    }

    fun changeCurrentRandomFruitName(){
        _currentRandomFruitName.value = getRandomFruitName()
    }

}