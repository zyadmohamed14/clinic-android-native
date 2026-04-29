package com.careline.clinicapp.feature.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    count: Int,
    current: Int
) {
    Row {
        repeat(count) { i ->
            val isActive = i == current

            Box(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .height(8.dp)
                    .width(if (isActive) 28.dp else 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        if (isActive)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
            )
        }
    }
}