package com.betterandroid.restaurantscorner.business.restaurant

import android.location.Location
import com.betterandroid.restaurantscorner.domain.models.restaurant.Restaurant
import com.betterandroid.restaurantscorner.mocks.MockCreator

class RestaurantRules {

    fun filterRestaurants(restaurants: List<Restaurant>): List<Restaurant> {

        return restaurants
                .filter { restaurant -> restaurant.closingHour <6 }
                .map {restaurants->

                    val userLat = MockCreator.getUserLatitude()
                    val userLong = MockCreator.getUserLongitude()
                    val distance = FloatArray(2)
                    Location.distanceBetween(
                            userLat, userLong,
                            restaurants.location.latitude,
                            restaurants.location.longitude,
                            distance
                    )
                    val distanceResult = distance[0] / 1000
                    restaurants.distance = distanceResult.toInt()
                    return@map restaurants

                }.sortedBy {restaurants-> restaurants.distance}

    }

}