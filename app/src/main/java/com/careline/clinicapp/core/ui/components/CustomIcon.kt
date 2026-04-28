package com.careline.clinicapp.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@SuppressLint("LocalContextResourcesRead")
@Composable

fun CustomIcon(
    source: String,
    size: Float? = null,
    color: Color? = null,
    fit: ContentScale = ContentScale.Fit,
    isImage: Boolean = false,
    noColor: Boolean = false,
    onTap: (() -> Unit)? = null,
) {
    val context = LocalContext.current

    val modifier = Modifier
        .then(
            if (onTap != null) Modifier.clickable { onTap() }
            else Modifier
        )

    if (isImage) {

        // 🖼️ Local drawable (R.drawable.xxx as String = "res:123")
        Image(
            painter = androidx.compose.ui.res.painterResource(
                id = source.toInt()
            ),
            contentDescription = null,
            modifier = modifier,
            contentScale = fit,
        )

    } else {

        // 🌐 Remote OR file OR any URI
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(source) // 👈 now supports URL or file path
                .build(),
            contentDescription = null,
            modifier = modifier,
            contentScale = fit,
            colorFilter = if (noColor) {
                null
            } else {
                ColorFilter.tint(color ?: Color.Unspecified)
            }
        )
    }
}