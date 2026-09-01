package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueBg
import com.example.ui.theme.PastelBlueBorder
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import java.text.NumberFormat
import java.util.Locale

data class MonthData(
    val month: String,
    val value: Double
)

@Composable
fun BalanceTrendChart(
    currentBalance: Double,
    modifier: Modifier = Modifier
) {
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale.US).apply {
            maximumFractionDigits = 0
            minimumFractionDigits = 0
        }
    }

    // Historical 6 months trend, ending at the current live balance
    val months = remember(currentBalance) {
        listOf(
            MonthData("Oct", 9800.0),
            MonthData("Nov", 10450.0),
            MonthData("Dec", 11200.0),
            MonthData("Jan", 10900.0),
            MonthData("Feb", 11850.0),
            MonthData("Mar", currentBalance)
        )
    }

    var selectedIndex by remember { mutableIntStateOf(5) }
    val selectedData = months.getOrElse(selectedIndex) { months.last() }

    // Chart entrance animation
    val animProgress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        animProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, easing = FastOutSlowInEasing)
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(22.dp),
                ambientColor = PastelBlueAccent.copy(alpha = 0.12f),
                spotColor = Color(0x12000000)
            )
            .testTag("balance_trend_chart"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header: Title and Growth Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "6-Month Balance Trend",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Selected: ${selectedData.month} • ${currencyFormatter.format(selectedData.value)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }

                // Pastel growth badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(PastelBlueBg)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = "Growth",
                            tint = PastelBlueAccent,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "+14.2%",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = PastelBlueAccent
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Smooth Canvas Line Chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .pointerInput(months) {
                            detectTapGestures { offset ->
                                val stepX = size.width / (months.size - 1)
                                val nearestIndex = (offset.x / stepX).toInt().coerceIn(0, months.size - 1)
                                selectedIndex = nearestIndex
                            }
                        }
                ) {
                    val width = size.width
                    val height = size.height
                    val paddingBottom = 24.dp.toPx()
                    val usableHeight = height - paddingBottom - 12.dp.toPx()

                    val minVal = months.minOf { it.value } * 0.92
                    val maxVal = months.maxOf { it.value } * 1.05
                    val valRange = (maxVal - minVal).coerceAtLeast(1.0)

                    // Compute points
                    val points = months.mapIndexed { index, item ->
                        val x = index * (width / (months.size - 1))
                        val normalizedY = ((item.value - minVal) / valRange).toFloat()
                        val y = (height - paddingBottom) - (normalizedY * usableHeight * animProgress.value)
                        Offset(x, y)
                    }

                    // Soft horizontal background guideline
                    drawLine(
                        color = Color(0xFFF1F5F9),
                        start = Offset(0f, height - paddingBottom),
                        end = Offset(width, height - paddingBottom),
                        strokeWidth = 1.dp.toPx()
                    )

                    // Draw soft pastel blue gradient fill under line
                    if (points.isNotEmpty()) {
                        val fillPath = Path().apply {
                            moveTo(points.first().x, height - paddingBottom)
                            points.forEach { point ->
                                lineTo(point.x, point.y)
                            }
                            lineTo(points.last().x, height - paddingBottom)
                            close()
                        }

                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    PastelBlueAccent.copy(alpha = 0.25f),
                                    PastelBlueLight.copy(alpha = 0.08f),
                                    Color.Transparent
                                ),
                                startY = 0f,
                                endY = height - paddingBottom
                            )
                        )

                        // Draw smooth curved line
                        val linePath = Path().apply {
                            moveTo(points.first().x, points.first().y)
                            for (i in 0 until points.size - 1) {
                                val p0 = points[i]
                                val p1 = points[i + 1]
                                val controlX = (p0.x + p1.x) / 2
                                cubicTo(controlX, p0.y, controlX, p1.y, p1.x, p1.y)
                            }
                        }

                        drawPath(
                            path = linePath,
                            color = PastelBlueAccent,
                            style = Stroke(
                                width = 3.dp.toPx(),
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )

                        // Draw Point dots
                        points.forEachIndexed { idx, pt ->
                            val isSelected = idx == selectedIndex
                            if (isSelected) {
                                // Outer subtle glow
                                drawCircle(
                                    color = PastelBlueAccent.copy(alpha = 0.25f),
                                    radius = 10.dp.toPx(),
                                    center = pt
                                )
                                // Solid inner dot
                                drawCircle(
                                    color = PastelBlueAccent,
                                    radius = 5.dp.toPx(),
                                    center = pt
                                )
                                drawCircle(
                                    color = Color.White,
                                    radius = 2.5.dp.toPx(),
                                    center = pt
                                )
                            } else {
                                drawCircle(
                                    color = Color.White,
                                    radius = 4.dp.toPx(),
                                    center = pt
                                )
                                drawCircle(
                                    color = PastelBlueLight,
                                    radius = 4.dp.toPx(),
                                    center = pt,
                                    style = Stroke(width = 2.dp.toPx())
                                )
                            }
                        }
                    }
                }
            }

            // Month labels row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                months.forEachIndexed { index, monthData ->
                    val isSelected = index == selectedIndex
                    Text(
                        text = monthData.month,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) PastelBlueAccent else TextTertiary,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
