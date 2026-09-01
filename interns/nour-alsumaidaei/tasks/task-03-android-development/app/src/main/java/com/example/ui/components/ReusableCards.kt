package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BgCanvas
import com.example.ui.theme.GlowPastel
import com.example.ui.theme.SparkleGold
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.cos
import kotlin.math.sin

/**
 * Reusable KPI Card Component for Dashboard.
 * Used for Balance, Income, Expenses, Savings.
 */
@Composable
fun KpiCard(
    title: String,
    amount: Double,
    accentColor: Color,
    pastelBgColor: Color,
    icon: ImageVector,
    isPulsing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    trendLabel: String = "+3.4% this mo",
    testTag: String = "kpi_card_$title"
) {
    val haptic = LocalHapticFeedback.current
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale.US).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }
    }

    // Soft fade/pulse transition (not a flash)
    val pulseScale by animateFloatAsState(
        targetValue = if (isPulsing) 1.03f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
        label = "pulse_scale"
    )

    val contentAlpha by animateFloatAsState(
        targetValue = if (isPulsing) 0.55f else 1.0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "pulse_alpha"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(pulseScale)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(22.dp),
                ambientColor = accentColor.copy(alpha = 0.15f),
                spotColor = Color(0x1A000000)
            )
            .testTag(testTag),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left icon pill and metric details
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Soft pastel icon container
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(pastelBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "$title Icon",
                        tint = accentColor,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Title and Amount with pulse animation
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary
                        )

                        // Small subtle pill for category
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(pastelBgColor)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = trendLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = accentColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = currencyFormatter.format(amount),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary.copy(alpha = contentAlpha)
                        ),
                        maxLines = 1
                    )
                }
            }

            // Refresh button: updates the number with soft fade/pulse
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onRefresh()
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(pastelBgColor.copy(alpha = 0.7f))
                    .testTag("refresh_button_$title")
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh $title",
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * Reusable Report Card Component for Reports Screen.
 * Used for Spending by Category, Top Merchants, Cash Flow Report, Monthly Summary.
 */
@Composable
fun ReportCard(
    title: String,
    description: String,
    icon: ImageVector,
    pastelAccent: Color,
    pastelBg: Color,
    isReviewed: Boolean,
    onToggle: () -> Unit,
    showSparkle: Boolean,
    onSparkleComplete: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "report_card_$title"
) {
    val haptic = LocalHapticFeedback.current

    // Bounce/scale animation on the icon when reviewed
    val iconScale = remember { Animatable(1f) }

    LaunchedEffect(isReviewed) {
        if (isReviewed) {
            // Trigger haptic feedback if supported
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            // Brief bounce/scale animation on icon
            iconScale.animateTo(1.35f, animationSpec = tween(120, easing = FastOutSlowInEasing))
            iconScale.animateTo(1.0f, animationSpec = spring(dampingRatio = 0.45f, stiffness = 300f))
        } else {
            iconScale.snapTo(1.0f)
        }
    }

    // Border transition: unreviewed has no border, reviewed has soft colored glow border
    val borderStroke = if (isReviewed) {
        BorderStroke(1.5.dp, pastelAccent.copy(alpha = 0.6f))
    } else {
        BorderStroke(1.dp, Color(0xFFF1F5F9))
    }

    val shadowAmbient = if (isReviewed) pastelAccent.copy(alpha = 0.25f) else Color(0x0D000000)

    Box(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = if (isReviewed) 5.dp else 2.dp,
                    shape = RoundedCornerShape(22.dp),
                    ambientColor = shadowAmbient,
                    spotColor = if (isReviewed) pastelAccent.copy(alpha = 0.2f) else Color(0x10000000)
                )
                .clip(RoundedCornerShape(22.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = pastelAccent.copy(alpha = 0.2f))
                ) {
                    onToggle()
                }
                .testTag(testTag),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isReviewed) SurfaceCard else Color(0xFFFCFDFE)
            ),
            border = borderStroke,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Icon and titles
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // Document/Report Icon
                    // Dimmed icon when Unreviewed, full color with bounce when Reviewed
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (isReviewed) pastelBg else Color(0xFFF1F5F9)
                            )
                            .scale(iconScale.value),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "$title Report Icon",
                            tint = if (isReviewed) pastelAccent else TextTertiary.copy(alpha = 0.65f),
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isReviewed) TextPrimary else TextSecondary
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextTertiary,
                            maxLines = 1
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Reviewed status pill badge: Tapping toggles between "Unreviewed" and "✓ Reviewed"
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (isReviewed) pastelBg else Color(0xFFF8FAFC),
                    border = BorderStroke(
                        1.dp,
                        if (isReviewed) pastelAccent.copy(alpha = 0.4f) else Color(0xFFE2E8F0)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (isReviewed) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Reviewed status",
                                tint = pastelAccent,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Reviewed",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = pastelAccent
                            )
                        } else {
                            Text(
                                text = "Unreviewed",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Medium,
                                color = TextTertiary
                            )
                        }
                    }
                }
            }
        }

        // Soft sparkle effect that bursts and fades out when marked Reviewed
        if (showSparkle) {
            SparkleBurstEffect(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(22.dp)),
                accentColor = pastelAccent,
                onComplete = onSparkleComplete
            )
        }
    }
}

/**
 * Totals summary bar at the very top showing all 4 values in one compact row.
 */
@Composable
fun TotalsSummaryBar(
    balance: Double,
    income: Double,
    expenses: Double,
    savings: Double,
    modifier: Modifier = Modifier
) {
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale.US).apply {
            maximumFractionDigits = 0
            minimumFractionDigits = 0
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(18.dp),
                ambientColor = Color(0x10000000),
                spotColor = Color(0x0A000000)
            )
            .testTag("totals_summary_bar"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SummaryItem(
                label = "Bal",
                amount = currencyFormatter.format(balance),
                dotColor = Color(0xFF3B82F6)
            )
            SummaryDivider()
            SummaryItem(
                label = "Inc",
                amount = currencyFormatter.format(income),
                dotColor = Color(0xFF10B981)
            )
            SummaryDivider()
            SummaryItem(
                label = "Exp",
                amount = currencyFormatter.format(expenses),
                dotColor = Color(0xFFF43F5E)
            )
            SummaryDivider()
            SummaryItem(
                label = "Sav",
                amount = currencyFormatter.format(savings),
                dotColor = Color(0xFFF59E0B)
            )
        }
    }
}

@Composable
private fun SummaryItem(
    label: String,
    amount: String,
    dotColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary,
                fontSize = 11.sp
            )
        }
        Text(
            text = amount,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun SummaryDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(24.dp)
            .background(Color(0xFFE2E8F0))
    )
}

/**
 * Animated soft sparkle effect that bursts and fades out smoothly.
 */
@Composable
fun SparkleBurstEffect(
    accentColor: Color,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 650, easing = FastOutSlowInEasing)
        )
        onComplete()
    }

    Canvas(modifier = modifier) {
        val p = progress.value
        val alpha = (1f - p).coerceIn(0f, 1f)
        val center = Offset(size.width * 0.85f, size.height * 0.5f)

        // 8 radiating sparkle particles
        val numParticles = 8
        for (i in 0 until numParticles) {
            val angle = (i.toDouble() / numParticles) * 2 * Math.PI
            val distance = (10f + p * 38f)
            val px = center.x + (cos(angle) * distance).toFloat()
            val py = center.y + (sin(angle) * distance).toFloat()
            val radius = (4f * (1f - p * 0.6f)).coerceAtLeast(1f)

            drawCircle(
                color = if (i % 2 == 0) accentColor.copy(alpha = alpha * 0.9f) else SparkleGold.copy(alpha = alpha * 0.9f),
                radius = radius,
                center = Offset(px, py)
            )
        }

        // Soft central glowing ring
        drawCircle(
            color = accentColor.copy(alpha = alpha * 0.35f),
            radius = (15f + p * 25f),
            center = center,
            style = Stroke(width = 2f)
        )
    }
}
