package com.example.testappvk.data.repository

import com.example.testappvk.data.network.ProductApi
import com.example.testappvk.domain.models.Product
import com.example.testappvk.domain.models.Products
import com.example.testappvk.domain.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getProductsList(): Products {
        val limit = LIMIT
        var skip = SKIP
        val resultProducts = mutableListOf<Product>()
        while (true) {
            val products = api.getProductByPage(limit = limit, skip = skip)
            resultProducts.addAll(products.products)
            skip += limit
            if (skip >= products.total) {
                break
            }
        }
        return Products(resultProducts)
    }

    companion object {
        const val LIMIT = 20
        const val SKIP = 0
    }
}
