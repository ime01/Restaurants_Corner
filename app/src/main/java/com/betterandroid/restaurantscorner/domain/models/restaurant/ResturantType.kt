package com.betterandroid.restaurantscorner.domain.models.restaurant

import com.betterandroid.restaurantscorner.R

enum class ResturantType(val text:String, val drawableId: Int, val textColour:Int) {

    TAKE_AWAY("Take away", R.drawable.take_away, R.color.orange),
    EAT_IN("Eat in", R.drawable.eat_in, R.color.brown),
    DRIVE_THROUGH("Drive THROUGH", R.drawable.drive_through, R.color.colorAccent)

}