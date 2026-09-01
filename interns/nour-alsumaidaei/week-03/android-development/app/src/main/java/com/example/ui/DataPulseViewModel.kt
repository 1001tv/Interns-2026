package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDataDatabaseHelper
import com.example.data.AppDataEntity
import com.example.data.AppDataRepository
import com.example.data.AppDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class KpiType {
    BALANCE,
    INCOME,
    EXPENSES,
    SAVINGS
}

enum class ReportType {
    SPENDING,
    MERCHANTS,
    CASH_FLOW,
    SUMMARY
}

data class PulseCardState(
    val isPulsing: Boolean = false,
    val lastRefreshed: Long = 0L
)

class DataPulseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppDataRepository = AppDataDatabaseHelper.getRepository(application)

    // Direct boolean variables for the 4 reports to strictly adhere to requirements:
    // "Implement this toggle using one boolean variable per card (isSpendingReviewed, isMerchantsReviewed, isCashFlowReviewed, isSummaryReviewed), each starting false."
    var isSpendingReviewed: Boolean = false
        private set
    var isMerchantsReviewed: Boolean = false
        private set
    var isCashFlowReviewed: Boolean = false
        private set
    var isSummaryReviewed: Boolean = false
        private set

    // Reports search query (to also satisfy empty state testability: "If Reports ever has no items to show...")
    private val _reportSearchQuery = MutableStateFlow("")
    val reportSearchQuery: StateFlow<String> = _reportSearchQuery.asStateFlow()

    // Pulse animation state map for each KPI card (Balance, Income, Expenses, Savings)
    private val _kpiPulseStates = MutableStateFlow<Map<KpiType, PulseCardState>>(emptyMap())
    val kpiPulseStates: StateFlow<Map<KpiType, PulseCardState>> = _kpiPulseStates.asStateFlow()

    // Sparkle trigger event for reports
    private val _sparkleReportTrigger = MutableStateFlow<ReportType?>(null)
    val sparkleReportTrigger: StateFlow<ReportType?> = _sparkleReportTrigger.asStateFlow()

    val appDataState: StateFlow<AppDataEntity> = repository.appDataFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppDataEntity.DEFAULT
        )

    init {
        viewModelScope.launch {
            repository.appDataFlow.collect { data ->
                // Sync direct boolean variables from database
                isSpendingReviewed = data.isSpendingReviewed
                isMerchantsReviewed = data.isMerchantsReviewed
                isCashFlowReviewed = data.isCashFlowReviewed
                isSummaryReviewed = data.isSummaryReviewed
            }
        }
    }

    // Reviewed count is calculated live from the 4 boolean variables
    fun getReviewedCount(): Int {
        var count = 0
        if (isSpendingReviewed) count++
        if (isMerchantsReviewed) count++
        if (isCashFlowReviewed) count++
        if (isSummaryReviewed) count++
        return count
    }

    fun isReportReviewed(type: ReportType): Boolean {
        return when (type) {
            ReportType.SPENDING -> isSpendingReviewed
            ReportType.MERCHANTS -> isMerchantsReviewed
            ReportType.CASH_FLOW -> isCashFlowReviewed
            ReportType.SUMMARY -> isSummaryReviewed
        }
    }

    /**
     * Direct toggle of the individual boolean variable per card.
     * Always check/update these variables directly — never infer state from icon color/opacity.
     */
    fun toggleReport(type: ReportType) {
        val currentEntity = appDataState.value
        val newState = when (type) {
            ReportType.SPENDING -> {
                isSpendingReviewed = !isSpendingReviewed
                isSpendingReviewed
            }
            ReportType.MERCHANTS -> {
                isMerchantsReviewed = !isMerchantsReviewed
                isMerchantsReviewed
            }
            ReportType.CASH_FLOW -> {
                isCashFlowReviewed = !isCashFlowReviewed
                isCashFlowReviewed
            }
            ReportType.SUMMARY -> {
                isSummaryReviewed = !isSummaryReviewed
                isSummaryReviewed
            }
        }

        if (newState) {
            // Trigger sparkle and bounce effect
            _sparkleReportTrigger.value = type
        }

        viewModelScope.launch {
            repository.updateReports(
                spending = isSpendingReviewed,
                merchants = isMerchantsReviewed,
                cashFlow = isCashFlowReviewed,
                summary = isSummaryReviewed,
                currentData = currentEntity
            )
        }
    }

    fun clearSparkleTrigger() {
        _sparkleReportTrigger.value = null
    }

    /**
     * Refresh KPI with a soft fade/pulse transition simulating live financial data.
     */
    fun refreshKpi(type: KpiType) {
        val current = appDataState.value
        viewModelScope.launch {
            // Set pulsing state
            _kpiPulseStates.update { map ->
                map + (type to PulseCardState(isPulsing = true, lastRefreshed = System.currentTimeMillis()))
            }

            // Generate realistic slight fluctuation (+/- $10 to $250)
            val delta = Random.nextDouble(-120.0, 280.0)
            when (type) {
                KpiType.BALANCE -> {
                    val updated = (current.balance + delta).coerceAtLeast(100.0)
                    repository.updateKpi(balance = Math.round(updated * 100.0) / 100.0, currentData = current)
                }
                KpiType.INCOME -> {
                    val updated = (current.income + Random.nextDouble(50.0, 300.0)).coerceAtLeast(100.0)
                    repository.updateKpi(income = Math.round(updated * 100.0) / 100.0, currentData = current)
                }
                KpiType.EXPENSES -> {
                    val updated = (current.expenses + Random.nextDouble(-80.0, 140.0)).coerceAtLeast(50.0)
                    repository.updateKpi(expenses = Math.round(updated * 100.0) / 100.0, currentData = current)
                }
                KpiType.SAVINGS -> {
                    val updated = (current.savings + Random.nextDouble(-60.0, 180.0)).coerceAtLeast(50.0)
                    repository.updateKpi(savings = Math.round(updated * 100.0) / 100.0, currentData = current)
                }
            }

            // Soft pulse duration
            delay(500)
            _kpiPulseStates.update { map ->
                map + (type to PulseCardState(isPulsing = false, lastRefreshed = System.currentTimeMillis()))
            }
        }
    }

    fun updateAnalystName(name: String) {
        val current = appDataState.value
        viewModelScope.launch {
            repository.updateAnalystName(name, current)
        }
    }

    fun updateReportSearch(query: String) {
        _reportSearchQuery.value = query
    }

    fun resetProgress() {
        // Clears all saved data back to defaults:
        // All reports back to Unreviewed
        // KPI values back to starting defaults
        // Name field cleared / Player default
        isSpendingReviewed = false
        isMerchantsReviewed = false
        isCashFlowReviewed = false
        isSummaryReviewed = false
        _reportSearchQuery.value = ""
        _sparkleReportTrigger.value = null

        viewModelScope.launch {
            repository.resetProgress()
        }
    }
}
