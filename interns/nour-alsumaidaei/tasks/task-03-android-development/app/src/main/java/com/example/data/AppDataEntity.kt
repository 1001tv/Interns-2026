package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_data")
data class AppDataEntity(
    @PrimaryKey val id: Int = 1,
    val analystName: String = "Player",
    val balance: Double = 12450.00,
    val income: Double = 5280.00,
    val expenses: Double = 2140.00,
    val savings: Double = 3140.00,
    val isSpendingReviewed: Boolean = false,
    val isMerchantsReviewed: Boolean = false,
    val isCashFlowReviewed: Boolean = false,
    val isSummaryReviewed: Boolean = false
) {
    companion object {
        val DEFAULT = AppDataEntity(
            id = 1,
            analystName = "Player",
            balance = 12450.00,
            income = 5280.00,
            expenses = 2140.00,
            savings = 3140.00,
            isSpendingReviewed = false,
            isMerchantsReviewed = false,
            isCashFlowReviewed = false,
            isSummaryReviewed = false
        )
    }
}
