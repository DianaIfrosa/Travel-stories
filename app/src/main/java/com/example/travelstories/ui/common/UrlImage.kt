package com.example.travelstories.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.AsyncImage


@Composable
fun UrlImage(
    url: String,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    if (LocalInspectionMode.current) {
        Box(
            modifier = modifier
                .aspectRatio(1.5f)
                .background(color = Color.Magenta)
        )
    } else {
        AsyncImage(
            model = url,
            contentDescription = contentDescription,
            placeholder = placeholder,
            modifier = modifier,
            contentScale = contentScale,
        )
    }

}

