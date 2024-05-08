package com.example.testappvk.domain.repository

import com.example.testappvk.domain.models.Products

interface ProductRepository {

    suspend fun getProductsList(): Products
}

