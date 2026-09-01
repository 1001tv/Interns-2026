package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataRepository(private val dao: AppDataDao) {

    val appDataFlow: Flow<AppDataEntity> = dao.getAppData().map { entity ->
        entity ?: AppDataEntity.DEFAULT
    }

    suspend fun save(data: AppDataEntity) {
        dao.insertOrUpdate(data)
    }

    suspend fun updateAnalystName(name: String, currentData: AppDataEntity) {
        val updated = currentData.copy(analystName = name.ifBlank { "Player" })
        dao.insertOrUpdate(updated)
    }

    suspend fun updateKpi(
        balance: Double? = null,
        income: Double? = null,
        expenses: Double? = null,
        savings: Double? = null,
        currentData: AppDataEntity
    ) {
        val updated = currentData.copy(
            balance = balance ?: currentData.balance,
            income = income ?: currentData.income,
            expenses = expenses ?: currentData.expenses,
            savings = savings ?: currentData.savings
        )
        dao.insertOrUpdate(updated)
    }

    suspend fun updateReports(
        spending: Boolean? = null,
        merchants: Boolean? = null,
        cashFlow: Boolean? = null,
        summary: Boolean? = null,
        currentData: AppDataEntity
    ) {
        val updated = currentData.copy(
            isSpendingReviewed = spending ?: currentData.isSpendingReviewed,
            isMerchantsReviewed = merchants ?: currentData.isMerchantsReviewed,
            isCashFlowReviewed = cashFlow ?: currentData.isCashFlowReviewed,
            isSummaryReviewed = summary ?: currentData.isSummaryReviewed
        )
        dao.insertOrUpdate(updated)
    }

    suspend fun resetProgress() {
        // Only the "Reset Progress" button should clear this saved data.
        // Resets all reports back to Unreviewed, KPI values back to starting defaults, name field cleared (or Player default)
        val defaultReset = AppDataEntity.DEFAULT.copy(analystName = "")
        dao.insertOrUpdate(defaultReset)
    }
}
