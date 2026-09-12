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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.DataPulseViewModel
import com.example.ui.KpiType
import com.example.ui.components.BalanceTrendChart
import com.example.ui.components.KpiCard
import com.example.ui.components.TotalsSummaryBar
import com.example.ui.theme.BgCanvas
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueBg
import com.example.ui.theme.PastelCoralAccent
import com.example.ui.theme.PastelCoralBg
import com.example.ui.theme.PastelMintAccent
import com.example.ui.theme.PastelMintBg
import com.example.ui.theme.PastelYellowAccent
import com.example.ui.theme.PastelYellowBg
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun DashboardScreen(
    viewModel: DataPulseViewModel,
    modifier: Modifier = Modifier
) {
    val appData by viewModel.appDataState.collectAsStateWithLifecycle()
    val pulseStates by viewModel.kpiPulseStates.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(BgCanvas)
            .testTag("dashboard_screen"),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // App Title & Welcome Header
        item(key = "header") {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "DataPulse",
                            style = MaterialTheme.typography.displayLarge.copy(fontSize = 28.sp),
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Welcome, ${appData.analystName.ifBlank { "Player" }}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }

                    // Soft status indicator badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(PastelMintBg)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(PastelMintAccent)
                            )
                            Text(
                                text = "Live Analytics",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = PastelMintAccent
                            )
                        }
                    }
                }
            }
        }

        // Totals summary bar at the very top showing all 4 values in one compact row
        item(key = "summary_bar") {
            TotalsSummaryBar(
                balance = appData.balance,
                income = appData.income,
                expenses = appData.expenses,
                savings = appData.savings
            )
        }

        // Small line chart near the top showing a 6-month balance trend, styled in soft pastel blue
        item(key = "trend_chart") {
            BalanceTrendChart(currentBalance = appData.balance)
        }

        // Section Title: Key Metrics
        item(key = "kpi_title") {
            Text(
                text = "Key Metrics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
            )
        }

        // Scrollable list of 4 KPI cards, all using the exact same reusable card component:
        // 1. Balance — pastel blue accent
        item(key = "kpi_balance") {
            val isPulsing = pulseStates[KpiType.BALANCE]?.isPulsing == true
            KpiCard(
                title = "Balance",
                amount = appData.balance,
                accentColor = PastelBlueAccent,
                pastelBgColor = PastelBlueBg,
                icon = Icons.Default.AccountBalanceWallet,
                isPulsing = isPulsing,
                trendLabel = "+4.8% trend",
                onRefresh = { viewModel.refreshKpi(KpiType.BALANCE) },
                testTag = "kpi_card_balance"
            )
        }

        // 2. Income — pastel mint accent
        item(key = "kpi_income") {
            val isPulsing = pulseStates[KpiType.INCOME]?.isPulsing == true
            KpiCard(
                title = "Income",
                amount = appData.income,
                accentColor = PastelMintAccent,
                pastelBgColor = PastelMintBg,
                icon = Icons.Default.ArrowDownward,
                isPulsing = isPulsing,
                trendLabel = "+2.9% mo",
                onRefresh = { viewModel.refreshKpi(KpiType.INCOME) },
                testTag = "kpi_card_income"
            )
        }

        // 3. Expenses — pastel coral accent
        item(key = "kpi_expenses") {
            val isPulsing = pulseStates[KpiType.EXPENSES]?.isPulsing == true
            KpiCard(
                title = "Expenses",
                amount = appData.expenses,
                accentColor = PastelCoralAccent,
                pastelBgColor = PastelCoralBg,
                icon = Icons.Default.ArrowUpward,
                isPulsing = isPulsing,
                trendLabel = "-1.2% mo",
                onRefresh = { viewModel.refreshKpi(KpiType.EXPENSES) },
                testTag = "kpi_card_expenses"
            )
        }

        // 4. Savings — pastel yellow accent
        item(key = "kpi_savings") {
            val isPulsing = pulseStates[KpiType.SAVINGS]?.isPulsing == true
            KpiCard(
                title = "Savings",
                amount = appData.savings,
                accentColor = PastelYellowAccent,
                pastelBgColor = PastelYellowBg,
                icon = Icons.Default.Savings,
                isPulsing = isPulsing,
                trendLabel = "+6.5% goal",
                onRefresh = { viewModel.refreshKpi(KpiType.SAVINGS) },
                testTag = "kpi_card_savings"
            )
        }

        // Bottom spacing for comfortable scrolling above navigation bar
        item(key = "bottom_space") {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
