package com.careline.clinicapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/// 1. HeadlineText
@Composable
fun HeadlineText(
    text: String,
    color: Color? = null,
    maxLines: Int? = null,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium.copy(
            color = color ?: MaterialTheme.colorScheme.onBackground,
            ),
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}
/// 2. SectionTitle
@Composable
fun SectionTitle(
    title: String,
    count: Int? = null,
    accentColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .width(3.dp)
                .height(18.dp)
                .background(accentColor)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (count != null) {
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .background(accentColor.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = accentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

/// BodyText
@Composable
fun BodyText(
    text: String,
    loose: Boolean = false,
    color: Color? = null,
    maxLines: Int? = null,
    textAlign: TextAlign? = null,
    bold: Boolean = false
) {
    var style = MaterialTheme.typography.bodyMedium

    if (loose) {
        style = style.copy(lineHeight = 24.sp)
    }

    if (bold) {
        style = style.copy(fontWeight = FontWeight.SemiBold)
    }

    Text(
        text = text,
        style = style.copy(color = color ?: style.color),
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}
/// CaptionText
@Composable
fun CaptionText(
    text: String,
    color: Color? = null,
    maxLines: Int? = null,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            color = color ?: MaterialTheme.colorScheme.onSurfaceVariant
        ),
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}
/// 4
@Composable
fun BadgeLabel(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = color.copy(alpha = 0.1f)
) {
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = color,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
fun PriceLabel(
    amount: Double,
    currency: String = "ج.م"
) {
    Text(
        text = "${amount.toInt()} $currency",
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
}


@Composable
fun RatingLabel(
    rating: Double,
    reviewCount: Int? = null
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(14.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.labelSmall
        )

        if (reviewCount != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "($reviewCount)",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


@Composable
fun EmptyStateText(
    title: String,
    subtitle: String,
    titleColor: Color? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = titleColor ?: MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}