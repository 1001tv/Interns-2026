package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.DataPulseViewModel
import com.example.ui.ReportType
import com.example.ui.components.ReportCard
import com.example.ui.theme.BgCanvas
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueBg
import com.example.ui.theme.PastelCoralAccent
import com.example.ui.theme.PastelCoralBg
import com.example.ui.theme.PastelMintAccent
import com.example.ui.theme.PastelMintBg
import com.example.ui.theme.PastelYellowAccent
import com.example.ui.theme.PastelYellowBg
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

data class ReportItem(
    val type: ReportType,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val accentColor: Color,
    val bgColor: Color
)

@Composable
fun ReportsScreen(
    viewModel: DataPulseViewModel,
    modifier: Modifier = Modifier
) {
    // Observing database state ensures live recomposition
    val appData by viewModel.appDataState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.reportSearchQuery.collectAsStateWithLifecycle()
    val sparkleTrigger by viewModel.sparkleReportTrigger.collectAsStateWithLifecycle()

    // Calculated directly from the 4 boolean variables as required
    val reviewedCount = viewModel.getReviewedCount()
    val progressFraction = reviewedCount / 4f

    val allReports = remember {
        listOf(
            ReportItem(
                type = ReportType.SPENDING,
                title = "Spending by Category",
                description = "Analysis of expenses across utilities, food, and essentials",
                icon = Icons.Default.PieChart,
                accentColor = PastelCoralAccent,
                bgColor = PastelCoralBg
            ),
            ReportItem(
                type = ReportType.MERCHANTS,
                title = "Top Merchants",
                description = "Highest frequency retailers, subscriptions, and stores",
                icon = Icons.Default.Storefront,
                accentColor = PastelBlueAccent,
                bgColor = PastelBlueBg
            ),
            ReportItem(
                type = ReportType.CASH_FLOW,
                title = "Cash Flow Report",
                description = "Net liquidity velocity, deposit cycles, and reserve margins",
                icon = Icons.Default.SwapHoriz,
                accentColor = PastelMintAccent,
                bgColor = PastelMintBg
            ),
            ReportItem(
                type = ReportType.SUMMARY,
                title = "Monthly Summary",
                description = "Consolidated financial health digest and milestone tracking",
                icon = Icons.Default.Description,
                accentColor = PastelYellowAccent,
                bgColor = PastelYellowBg
            )
        )
    }

    // Filter reports based on search query (allowing user to verify empty state when query doesn't match)
    val filteredReports = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            allReports
        } else {
            allReports.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(BgCanvas)
            .testTag("reports_screen"),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Screen Header
        item(key = "header") {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Reports",
                            style = MaterialTheme.typography.displayLarge.copy(fontSize = 28.sp),
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Audit and review financial statements",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }

                    // Top progress counter: "X / 4 Reviewed" styled as a soft rounded pill badge
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (reviewedCount == 4) PastelMintBg else PastelBlueBg,
                        shadowElevation = 2.dp,
                        modifier = Modifier.testTag("progress_pill_badge")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = if (reviewedCount == 4) Icons.Default.CheckCircle else Icons.Default.Assignment,
                                contentDescription = "Reviewed Counter",
                                tint = if (reviewedCount == 4) PastelMintAccent else PastelBlueAccent,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "$reviewedCount / 4 Reviewed",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = if (reviewedCount == 4) PastelMintAccent else PastelBlueAccent
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Soft pastel linear progress bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFE2E8F0))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = progressFraction)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (reviewedCount == 4) PastelMintAccent else PastelBlueAccent
                            )
                    )
                }
            }
        }

        // Search Filter Bar (clean pastel text field)
        item(key = "search_bar") {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateReportSearch(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("report_search_field"),
                placeholder = {
                    Text(
                        text = "Search reports...",
                        color = TextTertiary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = TextTertiary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateReportSearch("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search",
                                tint = TextTertiary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SurfaceCard,
                    unfocusedContainerColor = SurfaceCard,
                    focusedBorderColor = PastelBlueAccent,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    cursorColor = PastelBlueAccent
                ),
                singleLine = true
            )
        }

        // Empty state check: "If Reports ever has no items to show, display 'No reports yet — check back soon' with a small icon instead of a blank screen."
        if (filteredReports.isEmpty()) {
            item(key = "empty_state") {
                EmptyReportsView(onClearFilter = { viewModel.updateReportSearch("") })
            }
        } else {
            // Scrollable list of 4 report cards, all using the same reusable card component
            items(
                count = filteredReports.size,
                key = { index -> filteredReports[index].type.name }
            ) { index ->
                val report = filteredReports[index]
                // Directly check boolean state from the 4 variables
                val isReviewed = when (report.type) {
                    ReportType.SPENDING -> viewModel.isSpendingReviewed
                    ReportType.MERCHANTS -> viewModel.isMerchantsReviewed
                    ReportType.CASH_FLOW -> viewModel.isCashFlowReviewed
                    ReportType.SUMMARY -> viewModel.isSummaryReviewed
                }

                ReportCard(
                    title = report.title,
                    description = report.description,
                    icon = report.icon,
                    pastelAccent = report.accentColor,
                    pastelBg = report.bgColor,
                    isReviewed = isReviewed,
                    onToggle = { viewModel.toggleReport(report.type) },
                    showSparkle = sparkleTrigger == report.type,
                    onSparkleComplete = { viewModel.clearSparkleTrigger() },
                    testTag = "report_card_${report.type.name.lowercase()}"
                )
            }
        }

        item(key = "bottom_spacer") {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/**
 * Empty state component: displays "No reports yet — check back soon" with a small icon.
 */
@Composable
fun EmptyReportsView(
    onClearFilter: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(22.dp),
                ambientColor = Color(0x0A000000)
            )
            .testTag("empty_reports_view"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Empty Reports Icon",
                    tint = TextTertiary,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "No reports yet — check back soon",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Try clearing your search filter or check back after the next reporting cycle.",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                onClick = onClearFilter,
                shape = RoundedCornerShape(12.dp),
                color = PastelBlueBg,
                modifier = Modifier.testTag("clear_filter_button")
            ) {
                Text(
                    text = "Clear Filter",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = PastelBlueAccent,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
