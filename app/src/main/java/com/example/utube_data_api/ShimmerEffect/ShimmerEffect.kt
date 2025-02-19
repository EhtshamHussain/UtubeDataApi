package com.example.utube_data_api.ShimmerEffect

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerListItem(){
    val shimmerColors = listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f) ,
        Color.LightGray.copy(0.6f)
    )
    val Transition = rememberInfiniteTransition()
    val TranslateAnima = Transition.animateFloat(
        initialValue = 0f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ),
        label = "",
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = TranslateAnima.value , y = TranslateAnima.value)
    )

    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(brush: Brush) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(brush),

    ) {
        repeat(3) {
            Box(
                Modifier
//                .fillMaxWidth()
                    .width(190.dp)
                    .height(150.dp)
                    .background(brush)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(30.dp)
                    .background(brush)
                    .clip(shape = CircleShape)
            )
        }
    }
}

