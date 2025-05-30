package com.p.andrews.dogggs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.p.andrews.feature.image.BreedImagesScreen
import com.p.andrews.feature.list.BreedListScreen

import android.net.Uri

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "breeds") {

        composable("breeds") {
            BreedListScreen { breedPath ->
                val encoded = Uri.encode(breedPath)
                navController.navigate("images/$encoded")
            }
        }

        composable("images/{breed}") { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("breed") ?: ""
            val decodedBreed = Uri.decode(encoded)
            BreedImagesScreen(breed = decodedBreed) {
                navController.popBackStack()
            }
        }
    }
}