package com.example.testappvk.presentation.viewmodel.uiState

import com.example.testappvk.domain.models.Products

data class ProductsUiState(
    val list: Products = Products(products = emptyList()),
    val error: Exception? = null,
    val isLoading: Boolean = false
)