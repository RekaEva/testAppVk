package com.example.testappvk.domain.usecase

import com.example.testappvk.domain.models.Products
import com.example.testappvk.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsList @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun getProductsList(): Products {
        return productRepository.getProductsList()
    }
}

