package com.p.andrews.dogggs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.p.andrews.feature.image.BreedImagesScreen
import com.p.andrews.feature.list.BreedListScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "breeds") {
        composable("breeds") {
            BreedListScreen { breed ->
                navController.navigate("images/$breed")
            }
        }
        composable("images/{breed}") { backStackEntry ->
            val breed = backStackEntry.arguments?.getString("breed") ?: ""
            BreedImagesScreen(breed = breed) {
                navController.popBackStack()
            }
        }
    }
}