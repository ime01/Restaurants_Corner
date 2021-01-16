package com.betterandroid.restaurantscorner.domain.models.restaurant

import com.betterandroid.restaurantscorner.domain.models.restaurant.ResturantType

data class RestaurantDisplayItem(
    val id: Int,
    val displayName: String,
    val displayDistance: String,
    val imageUrl: String,
    var type: ResturantType
)