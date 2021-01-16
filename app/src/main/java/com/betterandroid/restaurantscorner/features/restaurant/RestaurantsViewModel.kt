package com.betterandroid.restaurantscorner.features.restaurant

import com.betterandroid.restaurantscorner.domain.models.restaurant.RestaurantDisplayItem
import com.betterandroid.restaurantscorner.domain.models.restaurant.ResturantType
import com.betterandroid.restaurantscorner.domain.models.restaurant.Restaurant

class RestaurantsViewModel {
    fun getDisplayRestaurant(restaurants: List<Restaurant>): List<RestaurantDisplayItem>{

      return restaurants.map { restaurant ->

            return@map RestaurantDisplayItem(
                    id = restaurant.id,
                    displayName = "Restaurant ${restaurant.name}",
                    displayDistance = "at ${restaurant.distance} KM distance",
                    imageUrl = restaurant.imageUrl,
                    type = when (restaurant.type) {
                        "EAT_IN" -> ResturantType.EAT_IN
                        "TAKE_AWAY" -> ResturantType.TAKE_AWAY
                        else -> ResturantType.DRIVE_THROUGH
                    }
            )
        }



    }
}