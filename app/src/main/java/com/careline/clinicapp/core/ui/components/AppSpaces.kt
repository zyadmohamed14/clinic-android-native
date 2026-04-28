package com.careline.clinicapp.core.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppHeightSpace(value: Dp = 8.dp) {
    Spacer(modifier = Modifier.height(value))
}

@Composable
fun AppWidthSpace(value: Dp = 8.dp) {
    Spacer(modifier = Modifier.width(value))
}