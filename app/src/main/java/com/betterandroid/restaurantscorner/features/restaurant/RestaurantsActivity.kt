package com.betterandroid.restaurantscorner.features.restaurant

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.betterandroid.restaurantscorner.*
import com.betterandroid.restaurantscorner.domain.models.restaurant.Restaurant
import com.betterandroid.restaurantscorner.api.restaurant.RestaurantsRestClient
import com.betterandroid.restaurantscorner.business.restaurant.RestaurantRules
import com.betterandroid.restaurantscorner.data.restaurant.RestaurantParser
import kotlinx.android.synthetic.main.activity_restaurants.*

class RestaurantsActivity : AppCompatActivity() {

    private val restaurantClient = RestaurantsRestClient()

    private var restaurantsAdapter: RestaurantsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        restaurantsAdapter = RestaurantsAdapter()
        recyclerViewRestaurants.apply {
            layoutManager = LinearLayoutManager(
                    context!!,
                    LinearLayoutManager.VERTICAL,
                    false
            )
       
            this.adapter = restaurantsAdapter
        }
        showRestaurants()
    }

    override fun onDestroy() {
        super.onDestroy()
        restaurantClient.stopStream()
    }

    private fun showRestaurants() {
       restaurantClient.getRestaurants { response ->
            // Parsing, filtering, displaying
            val restaurantParser = RestaurantParser()
            val restaurantRules = RestaurantRules()
            val parsedRestaurants = restaurantParser.parseRestaurants(response)
            val filteredRestaurants = restaurantRules.filterRestaurants(parsedRestaurants)
            displayRestaurants(filteredRestaurants)
        }
    }


    private fun displayRestaurants(restaurants: List<Restaurant>) {
//        to look for an item within a list try
//            val foundResturant = restaurants.find { restaurant -> restaurant.id == 100 }


//        val adapter = restaurantsAdapter
          val viewModel = RestaurantsViewModel()


            restaurantsAdapter!!.restaurants = viewModel.getDisplayRestaurant(restaurants)
            restaurantsAdapter!!.clickListener =
                    object : RestaurantsAdapter.RestaurantClickListener {
                        override fun onRestaurantClicked(restaurantId: Int) {
                            Toast.makeText(
                                    this@RestaurantsActivity,
                                    "Pressed a restaurant!",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }
    }
