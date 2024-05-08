package com.example.testappvk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testappvk.di.DaggerApplicationComponent
import com.example.testappvk.presentation.ScreensName.Companion.PRODUCTS_SCREEN
import com.example.testappvk.presentation.screens.ProductsScreen
import com.example.testappvk.presentation.viewmodel.ProductsViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var productsViewModel: ProductsViewModel
    private val component = DaggerApplicationComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = PRODUCTS_SCREEN
            ) {
                composable(PRODUCTS_SCREEN) {
                    ProductsScreen(productsViewModel = productsViewModel)
                }
            }
        }
    }
}

class ScreensName {
    companion object {
        const val PRODUCTS_SCREEN = "productsScreen"
    }
}
