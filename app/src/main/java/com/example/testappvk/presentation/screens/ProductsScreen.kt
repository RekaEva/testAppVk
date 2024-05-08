package com.example.testappvk.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testappvk.R
import com.example.testappvk.presentation.viewmodel.ProductsViewModel
import java.net.ConnectException


@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel,
) {
    val uiState by productsViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getProductsList()
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
            )
        }
    } else if (uiState.list.products.isNotEmpty()) {
        LazyColumn {
            items(uiState.list.products) { product ->
                ProductCard(
                    id = product.id,
                    title = product.title,
                    thumbnail = product.thumbnail,
                    description = product.description,
                    price = product.price,
                    rating = product.rating,
                )
            }
        }
    } else {
        uiState.error?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 10.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = errorMessage(it),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = { productsViewModel.getProductsList() },
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                        .border(
                            2.dp,
                            Color.Black,
                            RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(R.string.update_button),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    id: Int?,
    title: String?,
    thumbnail: String?,
    description: String?,
    price: Int?,
    rating: Double?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (thumbnail != null) {
                    AsyncImage(
                        model = thumbnail,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .clip(CircleShape),
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    if (title != null) {
                        Text(
                            text = title,
                            fontSize = 16.sp
                        )
                    }
                    Row {
                        if (price != null) {
                            Text(
                                text = "$price$",
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                        if (rating != null) {
                            Text(
                                text = " — ★ $rating",
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                    if (description != null) {
                        Text(
                            text = description,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun errorMessage(errorMessage: Exception): String {
    return when (errorMessage) {
        is ConnectException ->
            stringResource(R.string.internet_connection_error)
        else -> stringResource(R.string.other_error) + errorMessage.toString()
    }
}
